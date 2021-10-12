package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.CraekenEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CraekenModel extends AnimatedGeoModel<CraekenEntity> {

	@Override
	public Identifier getAnimationFileLocation(CraekenEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/craeken.animation.json");
	}

	@Override
	public Identifier getModelLocation(CraekenEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/craeken.geo.json");
	}

	@Override
	public Identifier getTextureLocation(CraekenEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/craeken.png");
	}

}
