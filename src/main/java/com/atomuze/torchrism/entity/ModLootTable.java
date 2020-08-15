package com.atomuze.torchrism.entity;

import com.atomuze.torchrism.Torchrism;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class ModLootTable {
	public static final ResourceLocation FLYINGTORCH = LootTableList.register(new ResourceLocation(Torchrism.MODID, "flying_torch"));
}
