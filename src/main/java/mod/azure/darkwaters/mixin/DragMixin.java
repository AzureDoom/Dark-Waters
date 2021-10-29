package mod.azure.darkwaters.mixin;

import org.spongepowered.asm.mixin.Mixin;

import mod.azure.darkwaters.entity.BaseWaterEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class DragMixin extends Entity {

	protected DragMixin(EntityType<? extends Entity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void updatePassengerPosition(Entity passenger) {
		if (passenger instanceof BaseWaterEntity)
			this.updatePosition(passenger, Entity::setPosition);
		super.updatePassengerPosition(passenger);
	}

	private void updatePosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
		if (this.hasPassenger(passenger)) {
			positionUpdater.accept(this, this.getX(), this.getY() - 0.1D, this.getZ());
		}
	}

}
