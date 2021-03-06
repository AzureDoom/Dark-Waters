package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MohastEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class MohastModel extends AnimatedTickingGeoModel<MohastEntity> {

	@Override
	public Identifier getAnimationFileLocation(MohastEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/mohast.animation.json");
	}

	@Override
	public Identifier getModelLocation(MohastEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/mohast.geo.json");
	}

	@Override
	public Identifier getTextureLocation(MohastEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/mohast.png");
	}

}
