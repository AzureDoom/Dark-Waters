package mod.azure.darkwaters.client.renders;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.darkwaters.client.models.CraekenModel;
import mod.azure.darkwaters.entity.CraekenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class CraekenRender extends GeoEntityRenderer<CraekenEntity> {

	public CraekenRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new CraekenModel());
	}

	@Override
	protected float getDeathMaxRotation(CraekenEntity entityLivingBaseIn) {
		return 0.0F;
	}
}