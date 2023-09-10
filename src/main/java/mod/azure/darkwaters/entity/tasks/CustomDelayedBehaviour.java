package mod.azure.darkwaters.entity.tasks;

import java.util.function.Consumer;

import mod.azure.darkwaters.entity.BaseWaterEntity;
import mod.azure.darkwaters.entity.CraekenEntity;
import mod.azure.darkwaters.entity.MiraidEntity;
import net.minecraft.server.level.ServerLevel;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;

public abstract class CustomDelayedBehaviour<E extends BaseWaterEntity> extends ExtendedBehaviour<E> {
	protected final int delayTime;
	protected long delayFinishedAt = 0;
	protected Consumer<E> delayedCallback = entity -> {
	};

	public CustomDelayedBehaviour(int delayTicks) {
		this.delayTime = delayTicks;

		runFor(entity -> Math.max(delayTicks, 60));
	}

	/**
	 * A callback for when the delayed action is called.
	 * 
	 * @param callback The callback
	 * @return this
	 */
	public final CustomDelayedBehaviour<E> whenActivating(Consumer<E> callback) {
		this.delayedCallback = callback;

		return this;
	}

	@Override
	protected final void start(ServerLevel level, E entity, long gameTime) {
		if (this.delayTime > 0) {
			this.delayFinishedAt = gameTime + this.delayTime;
			super.start(level, entity, gameTime);
		} else {
			super.start(level, entity, gameTime);
			doDelayedAction(entity);
		}
		if (entity instanceof MiraidEntity miraid)
			miraid.triggerAnim("idle_controller", switch (miraid.getRandom().nextInt(3)) {
			case 0 -> "grab";
			case 1 -> "bite";
			case 2 -> "attack";
			default -> "grab";
			});
		else if (entity instanceof CraekenEntity craeken)
			craeken.triggerAnim("idle_controller", switch (craeken.getRandom().nextInt(2)) {
			case 0 -> "attack";
			default -> "grab";
			});
		else
			entity.triggerAnim("idle_controller", "attack");
	}

	@Override
	protected final void stop(ServerLevel level, E entity, long gameTime) {
		super.stop(level, entity, gameTime);

		this.delayFinishedAt = 0;
	}

	@Override
	protected boolean shouldKeepRunning(E entity) {
		return this.delayFinishedAt >= entity.level().getGameTime();
	}

	@Override
	protected final void tick(ServerLevel level, E entity, long gameTime) {
		super.tick(level, entity, gameTime);

		entity.setAttackingState(1);
		if (this.delayFinishedAt <= gameTime) {
			doDelayedAction(entity);
			this.delayedCallback.accept(entity);
		}
	}

	/**
	 * The action to take once the delay period has elapsed.
	 *
	 * @param entity The owner of the brain
	 */
	protected void doDelayedAction(E entity) {
	}
}
