package mod.azure.darkwaters.entity;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import mod.azure.darkwaters.network.EntityPacket;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.AquaticLookControl;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.goal.ChaseBoatGoal;
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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BaseWaterEntity extends WaterCreatureEntity implements IAnimatable, Angerable {

	private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(BaseWaterEntity.class,
			TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> STATE = DataTracker.registerData(BaseWaterEntity.class,
			TrackedDataHandlerRegistry.INTEGER);
	private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
	private UUID targetUuid;
	private AnimationFactory factory = new AnimationFactory(this);

	public BaseWaterEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new AquaticLookControl(this, 10);
		this.ignoreCameraFrustum = true;
	}

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<BaseWaterEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(6, new LookAroundGoal(this));
		this.goalSelector.add(1, new SwimAroundGoal(this, 1.0D, 10));
		this.goalSelector.add(8, new ChaseBoatGoal(this));
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

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(ANGER_TIME, 0);
		this.dataTracker.startTracking(STATE, 0);
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
		if (pos.getY() > 45 && pos.getY() < world.getSeaLevel() && ((World) world).isThundering()) {
			Optional<RegistryKey<Biome>> optional = world.getBiomeKey(pos);
			return (!Objects.equals(optional, Optional.of(BiomeKeys.DEEP_OCEAN))) && ((World) world).isThundering()
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

}