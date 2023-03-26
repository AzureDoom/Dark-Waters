package mod.azure.darkwaters.entity;

import java.util.List;
import java.util.SplittableRandom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager.ControllerRegistrar;
import mod.azure.azurelib.core.animation.Animation.LoopType;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.entity.helper.AttackType;
import mod.azure.darkwaters.entity.tasks.WaterMeleeAttack;
import mod.azure.darkwaters.util.DarkWatersSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.UnreachableTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;

public class AberrationEntity extends BaseWaterEntity implements GeoEntity, SmartBrainOwner<AberrationEntity> {

	private AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

	public AberrationEntity(EntityType<? extends BaseWaterEntity> entityType, Level world) {
		super(entityType, world);
		this.xpReward = DarkWatersConfig.aberration_exp;
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes().add(Attributes.MAX_HEALTH, DarkWatersConfig.aberration_health).add(Attributes.ATTACK_DAMAGE, DarkWatersConfig.aberration_attack_damage);
	}

	@Override
	protected Brain.Provider<?> brainProvider() {
		return new SmartBrainProvider<>(this);
	}

	@Override
	protected void customServerAiStep() {
		tickBrain(this);
	}

	@Override
	public List<ExtendedSensor<AberrationEntity>> getSensors() {
		return ObjectArrayList.of(new NearbyPlayersSensor<>(), new NearbyLivingEntitySensor<AberrationEntity>().setPredicate((target, entity) -> target instanceof Player || !(target instanceof BaseWaterEntity) || target instanceof Villager), new HurtBySensor<>(), new UnreachableTargetSensor<AberrationEntity>());
	}

	@Override
	public BrainActivityGroup<AberrationEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(new LookAtTarget<>(), new MoveToWalkTarget<>());
	}

	@Override
	public BrainActivityGroup<AberrationEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(new FirstApplicableBehaviour<AberrationEntity>(new TargetOrRetaliate<>(), new SetPlayerLookTarget<>().stopIf(target -> !target.isAlive() || target instanceof Player && ((Player) target).isCreative()), new SetRandomLookTarget<>()), new OneRandomBehaviour<>(new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	@Override
	public BrainActivityGroup<AberrationEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(new InvalidateAttackTarget<>().stopIf(target -> !target.isAlive() || target instanceof Player && ((Player) target).isCreative()), new SetWalkTargetToAttackTarget<>().speedMod(1.5F), new WaterMeleeAttack<>(5).whenStarting(entity -> setAggressive(true)).whenStarting(entity -> setAggressive(false)));
	}

	@Override
	public void positionRider(Entity passenger) {
		super.positionRider(passenger);
		if (passenger instanceof LivingEntity) {
			var mob = (LivingEntity) passenger;
			var random = new SplittableRandom();
			var f = Mth.sin(this.yBodyRot * ((float) Math.PI / 180));
			var g = Mth.cos(this.yBodyRot * ((float) Math.PI / 180));
			passenger.setPos(this.getX() + (double) ((-0.45f) * f), this.getY() + (double) (random.nextFloat(0.14F, 0.15F)), this.getZ() - (double) (-0.45f) * g);
			mob.yBodyRot = this.yBodyRot;
		}
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		var isAttacking = this.swinging;
		var isDead = this.dead || this.getHealth() < 0.01 || this.isDeadOrDying();
		controllers.add(new AnimationController<>(this, "idle_controller", 0, event -> {
			if (this.swinging && !isDead)
				return event.setAndContinue(RawAnimation.begin().then(AttackType.animationMappings.get(getCurrentAttackType()), LoopType.PLAY_ONCE));
			if (event.isMoving() && !isDead && !isAttacking)
				return event.setAndContinue(RawAnimation.begin().thenLoop("moving"));
			if (isDead)
				return event.setAndContinue(RawAnimation.begin().thenPlayAndHold("death"));
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public void tick() {
		super.tick();
		if (attackProgress > 0) {
			attackProgress--;
			if (!level.isClientSide && attackProgress <= 0)
				setCurrentAttackType(AttackType.NONE);
		}

		if (attackProgress == 0 && swinging)
			attackProgress = 10;

		if (!level.isClientSide && getCurrentAttackType() == AttackType.NONE)
			setCurrentAttackType(AttackType.GRAB_ATTACK);
	}

	@Override
	public double getMeleeAttackRangeSqr(LivingEntity livingEntity) {
		return this.getBbWidth() * 1.0f * (this.getBbWidth() * 1.0f + livingEntity.getBbWidth());
	}

	@Override
	public boolean isWithinMeleeAttackRange(LivingEntity livingEntity) {
		double d = this.distanceToSqr(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
		return d <= this.getMeleeAttackRangeSqr(livingEntity);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return DarkWatersSounds.ABBERATION_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.GENERIC_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return DarkWatersSounds.ABBERATION_HURT;
	}

}
