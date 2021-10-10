package mod.azure.darkwaters.client;

import mod.azure.darkwaters.client.renders.AberrationRender;
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
		ClientSidePacketRegistry.INSTANCE.register(EntityPacket.ID, (ctx, buf) -> {
			EntityPacketOnClient.onPacket(ctx, buf);
		});
	}
}
