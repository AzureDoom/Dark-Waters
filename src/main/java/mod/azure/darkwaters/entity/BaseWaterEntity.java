package mod.azure.darkwaters.entity;

import java.util.SplittableRandom;
import java.util.UUID;
import mod.azure.darkwaters.config.DarkWatersConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
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
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class BaseWaterEntity extends WaterCreatureEntity implements Angerable {

  private static final TrackedData<Integer> ANGER_TIME =
      DataTracker.registerData(BaseWaterEntity.class,
                               TrackedDataHandlerRegistry.INTEGER);
  public static final TrackedData<Integer> STATE = DataTracker.registerData(
      BaseWaterEntity.class, TrackedDataHandlerRegistry.INTEGER);
  public static final TrackedData<Integer> MOVING = DataTracker.registerData(
      BaseWaterEntity.class, TrackedDataHandlerRegistry.INTEGER);
  private static final UniformIntProvider ANGER_TIME_RANGE =
      TimeHelper.betweenSeconds(20, 39);
  public static final TrackedData<Boolean> TEXTURE = DataTracker.registerData(
      BaseWaterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
  private UUID targetUuid;
  public SplittableRandom myrandom = new SplittableRandom();
  public int r = myrandom.nextInt(0, 3);
  public int aliveAfterStorm = 0;

  public BaseWaterEntity(EntityType<? extends BaseWaterEntity> entityType,
                         World world) {
    super(entityType, world);
    this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.5F, false);
    this.lookControl = new YawAdjustingLookControl(this, 10);
    this.ignoreCameraFrustum = true;
  }

  public static boolean
  canSpawnInDarkWater(EntityType<? extends BaseWaterEntity> type,
                      WorldAccess world, SpawnReason reason, BlockPos pos,
                      Random random) {
    if (pos.getY() > 45 && pos.getY() < world.getSeaLevel() &&
        ((World)world).isThundering() &&
        DarkWatersConfig.require_storm_to_spawn == true) {
      return ((World)world).isThundering() &&
          world.getFluidState(pos).isIn(FluidTags.WATER) &&
          world.getDifficulty() != Difficulty.PEACEFUL &&
          world.getBiome(pos).isIn(BiomeTags.IS_OCEAN);
    } else if (DarkWatersConfig.require_storm_to_spawn == false) {
      return world.getBiome(pos).isIn(BiomeTags.IS_OCEAN) &&
          world.getFluidState(pos).isIn(FluidTags.WATER) &&
          world.getDifficulty() != Difficulty.PEACEFUL;
    } else {
      return false;
    }
  }

  @Override
  protected void initGoals() {
    this.goalSelector.add(6,
                          new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.add(6, new LookAroundGoal(this));
    this.goalSelector.add(1, new SwimAroundGoal(this, 1.0D, 10));
    this.goalSelector.add(2, new MoveIntoWaterGoal(this));
    this.targetSelector.add(
        1, new RevengeGoal(this, new Class[0]).setGroupRevenge());
    this.targetSelector.add(
        2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    this.targetSelector.add(
        2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
  }

  public static DefaultAttributeContainer.Builder createMobAttributes() {
    return LivingEntity.createLivingAttributes()
        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0D)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0D)
        .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
  }

  @Override
  public EntityGroup getGroup() {
    return EntityGroup.AQUATIC;
  }

  @Override
  public boolean canBreatheInWater() {
    return true;
  }

  public int getAttckingState() { return this.dataTracker.get(STATE); }

  public void setAttackingState(int time) { this.dataTracker.set(STATE, time); }

  public Boolean getTextureState() { return this.dataTracker.get(TEXTURE); }

  public void setTextureState(Boolean time) {
    this.dataTracker.set(TEXTURE, time);
  }

  public int getMovingState() { return this.dataTracker.get(MOVING); }

  public void setMovingState(int time) { this.dataTracker.set(MOVING, time); }

  @Override
  protected void initDataTracker() {
    super.initDataTracker();
    this.dataTracker.startTracking(ANGER_TIME, 0);
    this.dataTracker.startTracking(STATE, 0);
    this.dataTracker.startTracking(TEXTURE, false);
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

  @Override
  public void tick() {
    super.tick();
    if (!this.world.isThundering()) {
      aliveAfterStorm++;
    }
    if (aliveAfterStorm >= 1200) {
      this.kill();
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

  public boolean canBeLeashedBy(PlayerEntity player) { return false; }

  @Override
  public double getMountedHeightOffset() {
    return 0.5D;
  }

  public void grabTarget(Entity entity) {
    if (entity == this.getTarget() && !entity.hasPassenger(this) &&
        this.isTouchingWater()) {
      entity.startRiding(this);
      if (entity instanceof ServerPlayerEntity) {
        ((ServerPlayerEntity)entity)
            .networkHandler.sendPacket(
                new EntityPassengersSetS2CPacket(entity));
      }
    }
  }

  @Override
  protected boolean isDisallowedInPeaceful() {
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
      this.playSound(soundEvent, 0.05F, this.getSoundPitch());
    }
  }
}