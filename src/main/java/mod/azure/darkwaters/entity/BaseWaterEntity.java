package mod.azure.darkwaters.entity;

import java.util.SplittableRandom;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animation.Animation.LoopType;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.helper.AttackType;
import mod.azure.darkwaters.entity.pathing.AmphibiousNavigation;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public abstract class BaseWaterEntity extends WaterAnimal implements NeutralMob, GeoEntity {

	private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(BaseWaterEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(BaseWaterEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> MOVING = SynchedEntityData.defineId(BaseWaterEntity.class, EntityDataSerializers.INT);
	private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
	public static final EntityDataAccessor<Boolean> TEXTURE = SynchedEntityData.defineId(BaseWaterEntity.class, EntityDataSerializers.BOOLEAN);
	private UUID targetUuid;
	public SplittableRandom myrandom = new SplittableRandom();
	public int r = myrandom.nextInt(0, 3);
	public int aliveAfterStorm = 0;
	public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
	public static final RawAnimation SWIM = RawAnimation.begin().thenLoop("running");
	public static final RawAnimation DEATH = RawAnimation.begin().thenLoop("death");
	public final RawAnimation ATTACK = RawAnimation.begin().then(AttackType.animationMappings.get(getCurrentAttackType()), LoopType.PLAY_ONCE);
	private static final EntityDataAccessor<AttackType> CURRENT_ATTACK_TYPE = SynchedEntityData.defineId(BaseWaterEntity.class, DarkWatersMod.ATTACK_TYPE);
	protected int attackProgress = 0;

	public BaseWaterEntity(EntityType<? extends BaseWaterEntity> entityType, Level world) {
		super(entityType, world);
		setMaxUpStep(1.0f);
		navigation = new AmphibiousNavigation(this, level());
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 1.0f, 0.5f, false);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
		setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
	}

	public static boolean canSpawnInDarkWater(EntityType<? extends WaterAnimal> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
		if (world.getDifficulty() == Difficulty.PEACEFUL)
			return false;
		if ((reason != MobSpawnType.CHUNK_GENERATION && reason != MobSpawnType.NATURAL))
			return world.getFluidState(pos.below()).is(FluidTags.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
		if (DarkWatersMod.config.require_storm_to_spawn == true)
			return ((Level) world).isThundering() && world.getFluidState(pos.below()).is(FluidTags.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
		return world.getFluidState(pos.below()).is(FluidTags.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 25.0D).add(Attributes.MOVEMENT_SPEED, 0.2500000417232513).add(Attributes.ATTACK_KNOCKBACK, 1.0D);
	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	public int getAttckingState() {
		return this.entityData.get(STATE);
	}

	public void setAttackingState(int time) {
		this.entityData.set(STATE, time);
	}

	public Boolean getTextureState() {
		return this.entityData.get(TEXTURE);
	}

	public void setTextureState(Boolean time) {
		this.entityData.set(TEXTURE, time);
	}

	public int getMovingState() {
		return this.entityData.get(MOVING);
	}

	public void setMovingState(int time) {
		this.entityData.set(MOVING, time);
	}

	public AttackType getCurrentAttackType() {
		return entityData.get(CURRENT_ATTACK_TYPE);
	}

	public void setCurrentAttackType(AttackType value) {
		entityData.set(CURRENT_ATTACK_TYPE, value);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ANGER_TIME, 0);
		this.entityData.define(STATE, 0);
		this.entityData.define(TEXTURE, false);
		this.entityData.define(MOVING, 0);
		entityData.define(CURRENT_ATTACK_TYPE, AttackType.NONE);
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.entityData.get(ANGER_TIME);
	}

	@Override
	public void setRemainingPersistentAngerTime(int ticks) {
		this.entityData.set(ANGER_TIME, ticks);
	}

	@Override
	public UUID getPersistentAngerTarget() {
		return this.targetUuid;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable UUID uuid) {
		this.targetUuid = uuid;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	protected void jumpInLiquid(TagKey<Fluid> fluid) {
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(this.random));
	}

	public PathNavigation createNavigation(Level world) {
		return new AmphibiousNavigation(this, world);
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level().isThundering() && DarkWatersMod.config.require_storm_to_spawn == true)
			aliveAfterStorm++;
		if (aliveAfterStorm >= 1200)
			this.kill();
	}

	@Override
	public void travel(Vec3 movementInput) {
		if (this.tickCount % 10 == 0)
			this.refreshDimensions();

		if (isEffectiveAi() && this.isInWater()) {
			moveRelative(getSpeed(), movementInput);
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(!this.wasTouchingWater ? 0.25 : 0.65));
			if (getTarget() == null)
				setDeltaMovement(getDeltaMovement().add(0.0, -0.005, 0.0));
		} else
			super.travel(movementInput);
	}

	@Override
	public boolean canBeLeashed(Player player) {
		return false;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 0.5D;
	}

	public void grabTarget(Entity entity) {
		if (entity == this.getTarget() && !entity.hasPassenger(this) && this.isInWater()) {
			entity.startRiding(this);
			if (entity instanceof ServerPlayer)
				((ServerPlayer) entity).connection.send(new ClientboundSetPassengersPacket(entity));
		}
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	@Override
	public float getSoundVolume() {
		return 0.25F;
	}

	@Override
	public void playAmbientSound() {
		var soundEvent = this.getAmbientSound();
		if (soundEvent != null)
			this.playSound(soundEvent, 0.05F, this.getVoicePitch());
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		this.triggerAnim("idle_controller", "death");
		if (this.deathTime == 35) {
			this.remove(Entity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

}