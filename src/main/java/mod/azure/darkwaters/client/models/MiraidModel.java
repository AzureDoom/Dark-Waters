package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MiraidEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class MiraidModel extends AnimatedTickingGeoModel<MiraidEntity> {

	@Override
	public Identifier getAnimationResource(MiraidEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/miraid.animation.json");
	}

	@Override
	public Identifier getModelResource(MiraidEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/miraid.geo.json");
	}

	@Override
	public Identifier getTextureResource(MiraidEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/miraid.png");
	}

}
