package mod.azure.darkwaters.client.renders;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.darkwaters.client.models.MiraidModel;
import mod.azure.darkwaters.entity.MiraidEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MiraidRender extends GeoEntityRenderer<MiraidEntity> {

	public MiraidRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new MiraidModel());
	}

	@Override
	protected float getDeathMaxRotation(MiraidEntity entityLivingBaseIn) {
		return 0.0F;
	}
}