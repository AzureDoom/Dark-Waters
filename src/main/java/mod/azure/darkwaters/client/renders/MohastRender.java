package mod.azure.darkwaters.client.renders;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.darkwaters.client.models.MohastModel;
import mod.azure.darkwaters.entity.MohastEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MohastRender extends GeoEntityRenderer<MohastEntity> {

	public MohastRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new MohastModel());
	}

	@Override
	protected float getDeathMaxRotation(MohastEntity entityLivingBaseIn) {
		return 0.0F;
	}
}