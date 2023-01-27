package mod.azure.darkwaters.client.renders;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.darkwaters.client.models.ManarawModel;
import mod.azure.darkwaters.entity.ManarawEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ManarawRender extends GeoEntityRenderer<ManarawEntity> {

	public ManarawRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new ManarawModel());
	}

	@Override
	protected float getDeathMaxRotation(ManarawEntity entityLivingBaseIn) {
		return 0.0F;
	}
}