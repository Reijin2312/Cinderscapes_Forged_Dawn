package com.terraformersmc.cinderscapes.init.helpers;

import com.terraformersmc.cinderscapes.Cinderscapes;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.RegisterEvent;

public class CinderscapesRegistry {
	private static RegisterEvent currentEvent;

	public static void begin(RegisterEvent event) {
		currentEvent = event;
	}

	public static void end() {
		currentEvent = null;
	}

	public static <R, T extends R> T register(RegistryKey<? extends Registry<R>> registryKey, Identifier id, T value) {
		if (currentEvent == null) throw new IllegalStateException("Registration attempted outside Forge RegisterEvent");
		currentEvent.register(registryKey, id, () -> value);
		return value;
	}
	@SuppressWarnings("UnnecessaryReturnStatement")
	public CinderscapesRegistry() {
		return;
	}

	public static BlockItem registerBlockItem(String name, Block block) {
		BlockItem item = new BlockItem(block, new Item.Settings());
		return register(name, item);
	}

	public static <I extends Item> I register(String name, I item) {
		if (item instanceof BlockItem blockItem) {
			blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		}
		return register(net.minecraft.registry.RegistryKeys.ITEM, Identifier.of(Cinderscapes.NAMESPACE, name), item);
	}

	public static <B extends Block> B register(String name, B block) {
		return register(net.minecraft.registry.RegistryKeys.BLOCK, Identifier.of(Cinderscapes.NAMESPACE, name), block);
	}
}
