package mod.azure.darkwaters.entity.ai.goals;

import mod.azure.darkwaters.entity.BaseWaterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.vehicle.BoatEntity;

public class WaterAttackGoal extends MeleeAttackGoal {
	private final BaseWaterEntity actor;
	private int updateCountdownTicks;
	private int statecheck;
	private int ticksUntilNextAttack;
	private double targetX;
	private double targetY;
	private double targetZ;
	private boolean rideTarget;
	private boolean ridingBoat;

	public WaterAttackGoal(BaseWaterEntity zombie, int state, boolean attach, boolean boatCheck) {
		super(zombie, 1.0D, false);
		this.actor = zombie;
		this.statecheck = state;
		this.rideTarget = attach;
		this.ridingBoat = boatCheck;
	}

	@Override
	public void start() {
		super.start();
		this.updateCountdownTicks = 0;
		this.actor.setAttacking(true);
		this.actor.setAttackingState(0);
	}

	@Override
	public void tick() {
		LivingEntity livingentity = this.actor.getTarget();
		if (livingentity != null) {
			this.mob.getLookControl().lookAt(livingentity, 30.0F, 30.0F);
			double d0 = this.mob.squaredDistanceTo(livingentity.getX(), livingentity.getY(), livingentity.getZ());
			this.updateCountdownTicks = Math.max(this.updateCountdownTicks - 1, 0);
			if (mob.distanceTo(livingentity) < 50.0D && this.ticksUntilNextAttack <= 0
					&& (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D
							|| livingentity.squaredDistanceTo(this.targetX, this.targetY, this.targetZ) >= 2.0D
							|| this.mob.getRandom().nextFloat() < 0.05F)
					&& (this.ridingBoat == true
							? !(livingentity.getVehicle() != null && livingentity.getVehicle() instanceof BoatEntity)
							: (livingentity.isAlive()))) {
				boolean bl = this.actor.getVisibilityCache().canSee(livingentity);
				boolean bl2 = this.updateCountdownTicks > 0;
				if (bl != bl2) {
					this.updateCountdownTicks = 0;
				}

				if (bl) {
					++this.updateCountdownTicks;
				} else {
					--this.updateCountdownTicks;
				}

				this.actor.getNavigation().startMovingTo(livingentity, 1.3D);
				this.attack(livingentity, d0);
				this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 0, 0);
			}
		}
	}

	@Override
	public void stop() {
		super.stop();
		this.updateCountdownTicks = 0;
		this.actor.setAttacking(false);
		this.actor.setAttackingState(0);
	}

	@Override
	protected void attack(LivingEntity livingentity, double squaredDistance) {
		double d0 = this.getSquaredMaxAttackDistance(livingentity);
		if (squaredDistance <= d0 && this.getCooldown() <= 0) {
			this.resetCooldown();
			this.actor.setAttackingState(statecheck);
			if (this.rideTarget == true) {
				this.actor.grabTarget(livingentity);
				if (livingentity.getVehicle() != null && livingentity.getVehicle() instanceof BoatEntity) {
					livingentity.dismountVehicle();
				}			
			}
			this.actor.tryAttack(livingentity);
		}
	}

	@Override
	protected int getMaxCooldown() {
		return 100;
	}

	@Override
	protected double getSquaredMaxAttackDistance(LivingEntity entity) {
		return (double) (this.mob.getWidth() * 2.0F * this.mob.getWidth() * 2.0F + entity.getWidth());
	}

}