package mod.azure.darkwaters.mixin;

import org.spongepowered.asm.mixin.Mixin;

import mod.azure.darkwaters.entity.BaseWaterEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

@Mixin(LivingEntity.class)
public abstract class DragMixin extends Entity {

	protected DragMixin(EntityType<? extends Entity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void positionRider(Entity passenger) {
		if (passenger instanceof BaseWaterEntity)
			this.updatePosition(passenger, Entity::setPos);
		super.positionRider(passenger);
	}

	private void updatePosition(Entity passenger, Entity.MoveFunction positionUpdater) {
		if (this.hasPassenger(passenger)) {
			positionUpdater.accept(this, this.getX(), this.getY() - 0.1D, this.getZ());
		}
	}

}
