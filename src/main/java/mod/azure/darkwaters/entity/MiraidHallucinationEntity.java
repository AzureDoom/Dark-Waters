package mod.azure.darkwaters.entity;

import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager.ControllerRegistrar;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import mod.azure.darkwaters.util.DarkWatersSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class MiraidHallucinationEntity extends BaseWaterEntity implements GeoEntity {

	private AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

	public MiraidHallucinationEntity(EntityType<? extends BaseWaterEntity> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 5;
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 70.0D).add(Attributes.ATTACK_DAMAGE,
				7.0D);
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "idle_controller", 0, event -> {
			if (event.isMoving())
				return event.setAndContinue(RawAnimation.begin().thenLoop("moving"));
			return event.setAndContinue(RawAnimation.begin().thenLoop("stalking "));
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return DarkWatersSounds.MIRAD_HUM;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.GENERIC_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return DarkWatersSounds.MIRAD_HURT;
	}

	@Override
	public void tick() {
		super.tick();
		final AABB aabb = new AABB(this.blockPosition().above()).inflate(64D, 64D, 64D);
		this.getCommandSenderWorld().getEntities(this, aabb).forEach(e -> {
			if (!(e instanceof MiraidEntity))
				this.kill();
			if (e instanceof MiraidEntity)
				this.setTextureState(((MiraidEntity) e).isAggressive() ? true : false);
		});
	}

}
