package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MiraidHallucinationEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class MiraidHallucinationModel extends AnimatedTickingGeoModel<MiraidHallucinationEntity> {

	@Override
	public Identifier getAnimationFileLocation(MiraidHallucinationEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/miraid_hallucination.animation.json");
	}

	@Override
	public Identifier getModelLocation(MiraidHallucinationEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/miraid_hallucination.geo.json");
	}

	@Override
	public Identifier getTextureLocation(MiraidHallucinationEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/"
				+ (object.isAttacking() ? "miraid_hallucination_angry" : "miraid_hallucination") + ".png");
	}

}
