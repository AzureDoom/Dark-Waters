package mod.azure.darkwaters.client;

import mod.azure.darkwaters.client.renders.AberrationRender;
import mod.azure.darkwaters.client.renders.CraekenRender;
import mod.azure.darkwaters.client.renders.ManarawRender;
import mod.azure.darkwaters.client.renders.MiraidHallucinationRender;
import mod.azure.darkwaters.client.renders.MiraidRender;
import mod.azure.darkwaters.client.renders.MohastRender;
import mod.azure.darkwaters.client.renders.SightHunterRender;
import mod.azure.darkwaters.network.EntityPacket;
import mod.azure.darkwaters.network.EntityPacketOnClient;
import mod.azure.darkwaters.util.DarkWatersMobs;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class DarkWatersClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(DarkWatersMobs.ABERRATION, (ctx) -> new AberrationRender(ctx));
		EntityRendererRegistry.register(DarkWatersMobs.CRAEKEN, (ctx) -> new CraekenRender(ctx));
		EntityRendererRegistry.register(DarkWatersMobs.MANARAW, (ctx) -> new ManarawRender(ctx));
		EntityRendererRegistry.register(DarkWatersMobs.MIRAID_HALLUCINATION,
				(ctx) -> new MiraidHallucinationRender(ctx));
		EntityRendererRegistry.register(DarkWatersMobs.MIRAID, (ctx) -> new MiraidRender(ctx));
		EntityRendererRegistry.register(DarkWatersMobs.MOHAST, (ctx) -> new MohastRender(ctx));
		EntityRendererRegistry.register(DarkWatersMobs.SIGHT_HUNTER, (ctx) -> new SightHunterRender(ctx));
		ClientSidePacketRegistry.INSTANCE.register(EntityPacket.ID, (ctx, buf) -> {
			EntityPacketOnClient.onPacket(ctx, buf);
		});
	}
}
