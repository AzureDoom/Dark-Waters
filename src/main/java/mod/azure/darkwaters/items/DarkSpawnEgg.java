package mod.azure.darkwaters.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;

public class DarkSpawnEgg extends SpawnEggItem {

	public DarkSpawnEgg(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor) {
		super(type, primaryColor, secondaryColor, new Item.Settings().maxCount(1).group(ItemGroup.MISC));
	}

}