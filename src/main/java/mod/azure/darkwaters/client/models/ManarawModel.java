package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.ManarawEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ManarawModel extends AnimatedTickingGeoModel<ManarawEntity> {

	@Override
	public Identifier getAnimationResource(ManarawEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/manaraw.animation.json");
	}

	@Override
	public Identifier getModelResource(ManarawEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/manaraw.geo.json");
	}

	@Override
	public Identifier getTextureResource(ManarawEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/manaraw.png");
	}

}
