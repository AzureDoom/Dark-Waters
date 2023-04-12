package mod.azure.darkwaters;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class DarkWaterSpawnEgg extends SpawnEggItem {

	public DarkWaterSpawnEgg(EntityType<? extends Mob> type, int primaryColor, int secondaryColor) {
		super(type, primaryColor, secondaryColor, new Item.Properties().stacksTo(64).tab(DarkWatersMod.GENERAL));
	}

}
