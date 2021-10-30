package mod.azure.darkwaters.entity;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.network.EntityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.AquaticLookControl;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class BaseWaterEntity extends WaterCreatureEntity implements Angerable {

	private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(BaseWaterEntity.class,
			TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> STATE = DataTracker.registerData(BaseWaterEntity.class,
			TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> MOVING = DataTracker.registerData(BaseWaterEntity.class,
			TrackedDataHandlerRegistry.INTEGER);
	private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
	private UUID targetUuid;
	public SplittableRandom myrandom = new SplittableRandom();
	public int r = myrandom.nextInt(0, 3);

	public BaseWaterEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.5F, true);
		this.lookControl = new AquaticLookControl(this, 10);
		this.ignoreCameraFrustum = true;
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(6, new LookAroundGoal(this));
		this.goalSelector.add(1, new SwimAroundGoal(this, 1.0D, 10));
		this.goalSelector.add(2, new MoveIntoWaterGoal(this));
		this.targetSelector.add(1, new RevengeGoal(this, new Class[0]).setGroupRevenge());
		this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, MerchantEntity.class, true));
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0D)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntityPacket.createPacket(this);
	}

	@Override
	public EntityGroup getGroup() {
		return EntityGroup.AQUATIC;
	}

	@Override
	public boolean canBreatheInWater() {
		return true;
	}

	public int getAttckingState() {
		return this.dataTracker.get(STATE);
	}

	public void setAttackingState(int time) {
		this.dataTracker.set(STATE, time);
	}

	public int getMovingState() {
		return this.dataTracker.get(MOVING);
	}

	public void setMovingState(int time) {
		this.dataTracker.set(MOVING, time);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(ANGER_TIME, 0);
		this.dataTracker.startTracking(STATE, 0);
		this.dataTracker.startTracking(MOVING, 0);
	}

	@Override
	public int getAngerTime() {
		return this.dataTracker.get(ANGER_TIME);
	}

	@Override
	public void setAngerTime(int ticks) {
		this.dataTracker.set(ANGER_TIME, ticks);
	}

	@Override
	public UUID getAngryAt() {
		return this.targetUuid;
	}

	@Override
	public void setAngryAt(@Nullable UUID uuid) {
		this.targetUuid = uuid;
	}

	@Override
	public void chooseRandomAngerTime() {
		this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
	}

	public EntityNavigation createNavigation(World world) {
		return new SwimNavigation(this, world);
	}

	public static boolean canSpawnInDarkWater(EntityType<? extends BaseWaterEntity> type, ServerWorldAccess world,
			SpawnReason spawnReason, BlockPos pos, Random random) {
		Optional<RegistryKey<Biome>> optional = world.getBiomeKey(pos);
		if (pos.getY() > 45 && pos.getY() < world.getSeaLevel() && ((World) world).isThundering()
				&& DarkWatersMod.config.spawning.require_storm_to_spawn == true) {
			return (!Objects.equals(optional, Optional.of(BiomeKeys.DEEP_OCEAN))) && ((World) world).isThundering()
					&& world.getFluidState(pos).isIn(FluidTags.WATER) && world.getDifficulty() != Difficulty.PEACEFUL;
		} else if (DarkWatersMod.config.spawning.require_storm_to_spawn == false) {
			return (!Objects.equals(optional, Optional.of(BiomeKeys.DEEP_OCEAN)))
					&& world.getFluidState(pos).isIn(FluidTags.WATER) && world.getDifficulty() != Difficulty.PEACEFUL;
		} else {
			return false;
		}
	}

	public void travel(Vec3d movementInput) {
		if (this.canMoveVoluntarily() && this.isTouchingWater()) {
			this.updateVelocity(this.getMovementSpeed(), movementInput);
			this.move(MovementType.SELF, this.getVelocity());
			this.setVelocity(this.getVelocity().multiply(0.9D));
			if (this.getTarget() == null) {
				this.setVelocity(this.getVelocity().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(movementInput);
		}
	}

	public boolean canBeLeashedBy(PlayerEntity player) {
		return false;
	}

	public void grabTarget(Entity entity) {
		if (entity == this.getTarget() && !entity.hasPassenger(this) && this.isTouchingWater()) {
			this.startRiding(entity, true);
			if (entity instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) entity).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(entity));
			}
		}
	}
	
	@Override
	protected boolean isDisallowedInPeaceful() {
		return true;
	}

}