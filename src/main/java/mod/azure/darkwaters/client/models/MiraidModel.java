package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MiraidEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MiraidModel extends AnimatedGeoModel<MiraidEntity> {

	@Override
	public Identifier getAnimationFileLocation(MiraidEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/miraid.animation.json");
	}

	@Override
	public Identifier getModelLocation(MiraidEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/miraid.geo.json");
	}

	@Override
	public Identifier getTextureLocation(MiraidEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/miraid.png");
	}

}
