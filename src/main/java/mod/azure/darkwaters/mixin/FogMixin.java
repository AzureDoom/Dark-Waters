package mod.azure.darkwaters.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import mod.azure.darkwaters.DarkWatersMod;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@Mixin(FogRenderer.class)
public class FogMixin {

	@Inject(method = "setupFog", at = @At("TAIL"))
	private static void stormMixin(Camera camera, FogMode fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
		var entity = camera.getEntity();
		float y;
		float ab;
		if (entity instanceof LivingEntity && ((LivingEntity) entity).hasEffect(DarkWatersMod.STORMDARKNESS) && !((Player) camera.getEntity()).isSpectator() && !((Player) camera.getEntity()).isCreative()) {
			var m = ((LivingEntity) entity).getEffect(DarkWatersMod.STORMDARKNESS).getDuration();
			var n = Mth.lerp(Math.min(1.0F, (float) m / 20.0F), viewDistance, 10.0F);
			if (fogType == FogRenderer.FogMode.FOG_SKY) {
				y = 0.0F;
				ab = n * 0.8F;
			} else {
				y = n * 0.25F;
				ab = n;
			}
			RenderSystem.setShaderFogStart(y);
			RenderSystem.setShaderFogEnd(ab);
		}
	}

	@SuppressWarnings("unused")
	@Inject(method = "setupColor", at = @At("HEAD"))
	private static void stormRenderMixin(Camera camera, float tickDelta, ClientLevel world, int viewDistance, float skyDarkness, CallbackInfo ci) {
		int af;
		var d = (camera.getPosition().y - (double) world.getMinBuildHeight()) * world.getLevelData().getClearColorScale();
		if (camera.getEntity() instanceof LivingEntity && ((LivingEntity) camera.getEntity()).hasEffect(DarkWatersMod.STORMDARKNESS) && !((Player) camera.getEntity()).isSpectator() && !((Player) camera.getEntity()).isCreative()) {
			af = ((LivingEntity) camera.getEntity()).getEffect(DarkWatersMod.STORMDARKNESS).getDuration();
			if (af < 20)
				d *= (double) (1.0F - (float) af / 10.0F);
			else
				d = 0.0D;
		}
	}

}
