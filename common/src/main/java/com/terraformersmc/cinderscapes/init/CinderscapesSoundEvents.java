package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import java.util.LinkedHashMap;
import java.util.Map;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class CinderscapesSoundEvents {
	// Acts as a kind of local registry for sound events added by Cinderscapes
	private static final Map<ResourceLocation, SoundEvent> SOUND_EVENTS = new LinkedHashMap<>();

	public static final SoundEvent ASHY_SHOALS_MUSIC = add(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "music.nether.ashy_shoals")));
	public static final SoundEvent BLACKSTONE_SHALES_MUSIC = add(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "music.nether.blackstone_shales")));
	public static final SoundEvent LUMINOUS_GROVE_MUSIC = add(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "music.nether.luminous_grove")));
	public static final SoundEvent QUARTZ_CAVERN_MUSIC = add(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "music.nether.quartz_cavern")));

	public static final SoundEvent MENU_MUSIC = add(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "music.menu")));

	public static final SoundEvent MUSIC_DISC_LUMINOUS_PLANTATION = add(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "music_disc.luminous_plantation")));
	public static final SoundEvent MUSIC_DISC_CHILLING_IN_HELL = add(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "music_disc.chilling_in_hell")));

	private static <S extends SoundEvent> S add(S sound_event) {
		SOUND_EVENTS.put(sound_event.getLocation(), sound_event);

		return sound_event;
	}

	public static void init() {
		for (ResourceLocation id : SOUND_EVENTS.keySet()) {
			CinderscapesRegistry.register(Registries.SOUND_EVENT, id, SOUND_EVENTS.get(id));
		}
	}
}
