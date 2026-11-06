package com.terraformersmc.cinderscapes.init.helpers;

import com.terraformersmc.cinderscapes.Cinderscapes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.neoforged.neoforge.registries.RegisterEvent;
import java.util.List;

public class CinderscapesRegistry {
	private static RegisterEvent currentEvent;

	public static void begin(RegisterEvent event) { currentEvent = event; }
	public static void end() { currentEvent = null; }

	public static <R, T extends R> T register(ResourceKey<? extends Registry<R>> registryKey, ResourceLocation id, T value) {
		if (currentEvent == null) throw new IllegalStateException("Registration attempted outside NeoForge RegisterEvent");
		currentEvent.register(registryKey, helper -> helper.register(id, value));
		return value;
	}
	@SuppressWarnings("UnnecessaryReturnStatement")
	public CinderscapesRegistry() {
		return;
	}

	/*
	 * Blocks and Items
	 */

	/**
	 * Registers a block item and associates it with its block.
	 *
	 * @param name Name ({@link ResourceLocation} path string) of the block item
	 * @param block {@link Block} to associate to the block item
	 * @return Newly created {@link BlockItem}
	 */
	public static BlockItem registerBlockItem(String name, Block block) {
		BlockItem item = new BlockItem(block, new Item.Properties());
		return register(name, item);
	}

	/**
	 * Registers an item.
	 *
	 * @param name Name ({@link ResourceLocation} path string) of the item
	 * @param item {@link Item} to be registered
	 * @return Newly registered {@link Item}
	 */
	public static <I extends Item> I register(String name, I item) {
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, blockItem);
		}
		return register(Registries.ITEM, Cinderscapes.id(name), item);
	}

	/**
	 * Registers a block.
	 *
	 * @param name Name ({@link ResourceLocation} path string) of the block
	 * @param block {@link Block} to be registered
	 * @return Newly registered {@link Block}
	 */
	public static <B extends Block> B register(String name, B block) {
		return register(Registries.BLOCK, Cinderscapes.id(name), block);
	}

	/*
	 * Features
	 */

	/**
	 * Registers a feature.
	 *
	 * @param name Name ({@link ResourceLocation} path string) of the feature
	 * @param feature {@link Feature} to be registered
	 * @return Newly registered {@link Feature}
	 */
	public static <T extends Feature<FC>, FC extends FeatureConfiguration> T register(String name, T feature) {
		return register(Registries.FEATURE, Cinderscapes.id(name), feature);
	}

	/**
	 * Creates and registers a configured feature (during datagen).  The configured feature will be created
	 * with the provided feature config and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of configured features
	 * @param key {@link ResourceKey} of {@link ConfiguredFeature} being registered
	 * @param feature {@link Feature} being configured
	 * @param config {@link FeatureConfiguration} to apply to {@link Feature} being registered
	 */
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> registerable, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		FeatureUtils.register(registerable, key, feature, config);
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of placed features
	 * @param key {@link ResourceKey} of {@link PlacedFeature} being registered
	 * @param feature {@link ResourceKey} of {@link ConfiguredFeature} being placed
	 * @param placementModifiers Any {@link PlacementModifier}(s) to apply to {@link ConfiguredFeature} being registered
	 */
	public static void register(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
		register(registerable, key, feature, List.of(placementModifiers));
	}

	/**
	 * Creates and registers a placed feature (during datagen).  The placed feature will be created with
	 * the provided placement modifiers and registered to the provided registerable under the provided key.
	 *
	 * @param registerable A registration-capable abstraction of a registry of placed features
	 * @param key {@link ResourceKey} of {@link PlacedFeature} being registered
	 * @param feature {@link ResourceKey} of {@link ConfiguredFeature} being placed
	 * @param placementModifiers {@link List} of {@link PlacementModifier}(s) to apply to {@link ConfiguredFeature} being registered
	 */
	public static void register(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
		PlacementUtils.register(registerable, key,
				registerable.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature),
				placementModifiers);
	}
}
