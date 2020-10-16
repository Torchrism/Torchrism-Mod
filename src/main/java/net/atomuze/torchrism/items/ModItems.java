package net.atomuze.torchrism.items;

import net.atomuze.torchrism.TorchrismMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
	
    public static final Item TORCH_STAFF = new Item(new FabricItemSettings().group(TorchrismMod.ITEM_GROUP));
    
	public static void register() {
        Registry.register(Registry.ITEM, new Identifier("torchrism", "torch_staff"), TORCH_STAFF);
	}
}
