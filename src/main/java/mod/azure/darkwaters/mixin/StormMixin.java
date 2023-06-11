package mod.azure.darkwaters.mixin;

import java.util.SplittableRandom;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.util.DarkWatersSounds;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Mixin(Player.class)
public abstract class StormMixin extends LivingEntity {

	protected int cooldown = 0;
	protected BiomeSelectionContext biome;
	@Shadow
	private final Abilities abilities = new Abilities();

	protected StormMixin(EntityType<? extends LivingEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void stormMixin(CallbackInfo ci) {
		SplittableRandom random = new SplittableRandom();
		int r = random.nextInt(0, 4);
		if (level().isThundering() && level().getBiomeManager().getBiome(blockPosition()).is(BiomeTags.IS_OCEAN)
				&& !this.abilities.instabuild) {
			cooldown++;
			this.addEffect(new MobEffectInstance(DarkWatersMod.STORMDARKNESS, 600, 0, true, false, false));
			if (this.cooldown == 5) {
				if (!this.isSilent()) {
					this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(),
							r == 1 ? DarkWatersSounds.STORM_ABIENT1
									: r == 2 ? DarkWatersSounds.STORM_ABIENT2 : DarkWatersSounds.STORM_ABIENT3,
							SoundSource.MUSIC, level().isThundering() ? 0.75F : 0.0F, 1.0F);
				}
				this.cooldown = -500;
			}
		}
	}
}
