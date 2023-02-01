package mod.azure.darkwaters.entity;

import java.util.SplittableRandom;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import mod.azure.darkwaters.config.DarkWatersConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public class BaseWaterEntity extends WaterAnimal implements NeutralMob {

	private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(BaseWaterEntity.class,
			EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(BaseWaterEntity.class,
			EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> MOVING = SynchedEntityData.defineId(BaseWaterEntity.class,
			EntityDataSerializers.INT);
	private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
	public static final EntityDataAccessor<Boolean> TEXTURE = SynchedEntityData.defineId(BaseWaterEntity.class,
			EntityDataSerializers.BOOLEAN);
	private UUID targetUuid;
	public SplittableRandom myrandom = new SplittableRandom();
	public int r = myrandom.nextInt(0, 3);
	public int aliveAfterStorm = 0;

	public BaseWaterEntity(EntityType<? extends BaseWaterEntity> entityType, Level world) {
		super(entityType, world);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.5F, false);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}

	public static boolean canSpawnInDarkWater(EntityType<? extends BaseWaterEntity> type, ServerLevelAccessor world,
			MobSpawnType reason, BlockPos pos, RandomSource random) {
		if (pos.getY() > 45 && pos.getY() < world.getSeaLevel() && ((Level) world).isThundering()
				&& DarkWatersConfig.require_storm_to_spawn == true) {
			return ((Level) world).isThundering() && world.getFluidState(pos).is(FluidTags.WATER)
					&& world.getDifficulty() != Difficulty.PEACEFUL && world.getBiome(pos).is(BiomeTags.IS_OCEAN);
		} else if (DarkWatersConfig.require_storm_to_spawn == false) {
			return world.getBiome(pos).is(BiomeTags.IS_OCEAN) && world.getFluidState(pos).is(FluidTags.WATER)
					&& world.getDifficulty() != Difficulty.PEACEFUL;
		} else {
			return false;
		}
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 25.0D)
				.add(Attributes.MOVEMENT_SPEED, 1.0D).add(Attributes.ATTACK_KNOCKBACK, 1.0D);
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

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ANGER_TIME, 0);
		this.entityData.define(STATE, 0);
		this.entityData.define(TEXTURE, false);
		this.entityData.define(MOVING, 0);
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
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(this.random));
	}

	public PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level.isThundering() && DarkWatersConfig.require_storm_to_spawn == true)
			aliveAfterStorm++;
		if (aliveAfterStorm >= 1200)
			this.kill();
	}

	public void travel(Vec3 movementInput) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), movementInput);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
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
			if (entity instanceof ServerPlayer) {
				((ServerPlayer) entity).connection.send(new ClientboundSetPassengersPacket(entity));
			}
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
		SoundEvent soundEvent = this.getAmbientSound();
		if (soundEvent != null) {
			this.playSound(soundEvent, 0.05F, this.getVoicePitch());
		}
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0, 10));
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 35) {
			this.remove(Entity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

}