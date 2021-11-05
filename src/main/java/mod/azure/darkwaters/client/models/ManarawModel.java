package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.ManarawEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ManarawModel extends AnimatedTickingGeoModel<ManarawEntity> {

	@Override
	public Identifier getAnimationFileLocation(ManarawEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/manaraw.animation.json");
	}

	@Override
	public Identifier getModelLocation(ManarawEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/manaraw.geo.json");
	}

	@Override
	public Identifier getTextureLocation(ManarawEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/manaraw.png");
	}

}
