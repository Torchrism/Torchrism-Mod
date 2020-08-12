package com.atomuze.torchrism.sound;

import com.atomuze.torchrism.TorchrismMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModSound {
	public static SoundEvent 
	ENTITY_FLYINGTORCH_HURT,
	ENTITY_FLYINGTORCH_DEATH,
	ITEM_TORCHONOMICON_OPEN,
	ITEM_TORCHONOMICON_CHANGEPAGE_1,
	ITEM_TORCHONOMICON_CHANGEPAGE_2,
	ITEM_TORCHONOMICON_CHANGEPAGE_3;

	public static void registerSounds() {
		ENTITY_FLYINGTORCH_HURT = registerSound("entity.flyingtorch.hurt");
		ENTITY_FLYINGTORCH_DEATH = registerSound("entity.flyingtorch.death");
		ITEM_TORCHONOMICON_OPEN = registerSound("item.torchonomicon.open");
		ITEM_TORCHONOMICON_CHANGEPAGE_1 = registerSound("item.torchonomicon.change1");
		ITEM_TORCHONOMICON_CHANGEPAGE_2 = registerSound("item.torchonomicon.change2");
		ITEM_TORCHONOMICON_CHANGEPAGE_3 = registerSound("item.torchonomicon.change3");
	}

	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(TorchrismMod.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
