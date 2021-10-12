package mod.azure.darkwaters.entity.ai.goals;

import mod.azure.darkwaters.entity.BaseWaterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class WaterAttackGoal extends MeleeAttackGoal {
	private final BaseWaterEntity actor;
	private int statecheck;
	private int ticks;

	public WaterAttackGoal(BaseWaterEntity zombie, int state) {
		super(zombie, 1.0D, false);
		this.actor = zombie;
		this.statecheck = state;
	}

	public void start() {
		super.start();
		this.ticks = 0;
	}

	public void tick() {
		super.tick();
		++this.ticks;
		if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
			this.actor.setAttacking(true);
		} else {
			this.actor.setAttacking(false);
		}

	}

	public void stop() {
		super.stop();
		this.actor.setAttacking(false);
		this.actor.setAttackingState(0);
	}

	@Override
	protected void attack(LivingEntity livingentity, double squaredDistance) {
		double d0 = this.getSquaredMaxAttackDistance(livingentity);
		if (squaredDistance <= d0 && this.getCooldown() <= 0) {
			this.resetCooldown();
			this.actor.setAttackingState(statecheck);
			this.actor.tryAttack(livingentity);
		}
	}

	@Override
	protected int getMaxCooldown() {
		return 25;
	}

	@Override
	protected double getSquaredMaxAttackDistance(LivingEntity entity) {
		return (double) (this.mob.getWidth() * 1.0F * this.mob.getWidth() * 1.0F + entity.getWidth());
	}

}