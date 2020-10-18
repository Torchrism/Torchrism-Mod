package net.atomuze.torchrism.items;

import net.atomuze.torchrism.TorchrismMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
	
    public static final ItemStaff TORCH_STAFF = new ItemStaff();
    
	public static void register() {
        Registry.register(Registry.ITEM, new Identifier("torchrism", "torch_staff"), TORCH_STAFF);
	}
}
