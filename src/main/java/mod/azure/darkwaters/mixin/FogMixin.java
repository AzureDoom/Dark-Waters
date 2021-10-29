package mod.azure.darkwaters.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import mod.azure.darkwaters.DarkWatersMod;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

@Mixin(BackgroundRenderer.class)
public class FogMixin {

	@Inject(method = "applyFog", at = @At("TAIL"))
	private static void stormMixin(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance,
			boolean thickFog, CallbackInfo ci) {
		Entity entity = camera.getFocusedEntity();
		float y;
		float ab;
		if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(DarkWatersMod.STORMDARKNESS)
				&& !((PlayerEntity) camera.getFocusedEntity()).isSpectator()
				&& !((PlayerEntity) camera.getFocusedEntity()).isCreative()) {
			int m = ((LivingEntity) entity).getStatusEffect(DarkWatersMod.STORMDARKNESS).getDuration();
			float n = MathHelper.lerp(Math.min(1.0F, (float) m / 20.0F), viewDistance, 10.0F);
			if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
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
	@Inject(method = "render", at = @At("HEAD"))
	private static void stormRenderMixin(Camera camera, float tickDelta, ClientWorld world, int i, float f,
			CallbackInfo ci) {
		int af;
		double d = (camera.getPos().y - (double) world.getBottomY())
				* world.getLevelProperties().getHorizonShadingRatio();
		if (camera.getFocusedEntity() instanceof LivingEntity
				&& ((LivingEntity) camera.getFocusedEntity()).hasStatusEffect(DarkWatersMod.STORMDARKNESS)
				&& !((PlayerEntity) camera.getFocusedEntity()).isSpectator()
				&& !((PlayerEntity) camera.getFocusedEntity()).isCreative()) {
			af = ((LivingEntity) camera.getFocusedEntity()).getStatusEffect(DarkWatersMod.STORMDARKNESS).getDuration();
			if (af < 20) {
				d *= (double) (1.0F - (float) af / 10.0F);
			} else {
				d = 0.0D;
			}
		}
	}

}
