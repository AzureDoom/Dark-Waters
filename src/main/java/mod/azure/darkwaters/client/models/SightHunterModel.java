package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.SightHunterEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SightHunterModel extends AnimatedGeoModel<SightHunterEntity> {

	@Override
	public Identifier getAnimationFileLocation(SightHunterEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/sight_hunter.animation.json");
	}

	@Override
	public Identifier getModelLocation(SightHunterEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/sight_hunter.geo.json");
	}

	@Override
	public Identifier getTextureLocation(SightHunterEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/sight_hunter.png");
	}

}
