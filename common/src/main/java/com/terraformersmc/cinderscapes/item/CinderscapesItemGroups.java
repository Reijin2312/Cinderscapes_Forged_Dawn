package com.terraformersmc.cinderscapes.item;

import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.config.CinderscapesConfig;
import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import com.terraformersmc.cinderscapes.init.CinderscapesItems;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CinderscapesItemGroups {
	private static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "items"));
	private static final HashMap<ResourceKey<CreativeModeTab>, HashMap<ItemLike, ItemGroupEntries>> ITEM_GROUP_ENTRY_MAPS;

	static {
		ITEM_GROUP_ENTRY_MAPS = new HashMap<>(8);

		/*
		 * These items are the last Vanilla item of a "similar" type to items we add to Vanilla groups.
		 * Each is used to build a collection of items which will be inserted below the Vanilla item.
		 */
		final Item BUILDING_NETHER_WOOD = Items.WARPED_BUTTON;
		final Item BUILDING_NETHER_QUARTZ = Items.SMOOTH_QUARTZ_SLAB;
		final Item BUILDING_NETHER_STORAGE_BLOCK = Items.NETHERITE_BLOCK;
		final Item FUNCTIONAL_NETHER_SIGN = Items.WARPED_HANGING_SIGN;
		final Item NATURAL_NYLIUM = Items.WARPED_NYLIUM;
		final Item NATURAL_SNOWLIKE = Items.MOSS_CARPET;
		final Item NATURAL_QUARTZ_ORE = Items.NETHER_QUARTZ_ORE;
		final Item NATURAL_FUNGUS = Items.WARPED_FUNGUS;
		final Item NATURAL_STEM = Items.WARPED_STEM;
		final Item NATURAL_WART_BLOCK = Items.WARPED_WART_BLOCK;
		final Item NATURAL_NETHER_VEGETATION = Items.NETHER_SPROUTS;
		final Item NATURAL_TALL_VEGETATION = Items.LARGE_FERN;
		final Item NATURAL_BERRIES = Items.SWEET_BERRIES;
		final Item FOOD_BERRIES = Items.GLOW_BERRIES;
		final Item INGREDIENTS_QUARTZ = Items.QUARTZ;


		/*
		 * For each Vanilla item group, add the same kinds of items Vanilla adds.
		 * Since Minecraft 1.19.3, items are often in multiple item groups...
		 */

		// BUILDING BLOCKS

		// Wood items
		addGroupEntry(CinderscapesBlocks.SCORCHED_STEM, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_HYPHAE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.STRIPPED_SCORCHED_STEM, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_PLANKS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_FENCE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_FENCE_GATE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_DOOR, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_PRESSURE_PLATE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.SCORCHED_BUTTON, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);

		addGroupEntry(CinderscapesBlocks.UMBRAL_STEM, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_HYPHAE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.STRIPPED_UMBRAL_STEM, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_PLANKS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_FENCE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_FENCE_GATE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_DOOR, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_PRESSURE_PLATE, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);
		addGroupEntry(CinderscapesBlocks.UMBRAL_BUTTON, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_WOOD);

		// Quartz Items
		addGroupEntry(CinderscapesBlocks.CRYSTALLINE_QUARTZ, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);

		addGroupEntry(CinderscapesBlocks.SULFUR_QUARTZ_BLOCK, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SULFUR_QUARTZ_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SULFUR_QUARTZ_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.CHISELED_SULFUR_QUARTZ_BLOCK, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SULFUR_QUARTZ_BRICKS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SULFUR_QUARTZ_PILLAR, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.CRYSTALLINE_SULFUR_QUARTZ, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);

		addGroupEntry(CinderscapesBlocks.ROSE_QUARTZ_BLOCK, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.ROSE_QUARTZ_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.ROSE_QUARTZ_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.CHISELED_ROSE_QUARTZ_BLOCK, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.ROSE_QUARTZ_BRICKS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.ROSE_QUARTZ_PILLAR, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.CRYSTALLINE_ROSE_QUARTZ, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);

		addGroupEntry(CinderscapesBlocks.SMOKY_QUARTZ_BLOCK, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOKY_QUARTZ_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOKY_QUARTZ_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.CHISELED_SMOKY_QUARTZ_BLOCK, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOKY_QUARTZ_BRICKS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOKY_QUARTZ_PILLAR, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_SLAB, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);
		addGroupEntry(CinderscapesBlocks.CRYSTALLINE_SMOKY_QUARTZ, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_QUARTZ);

		// Storage Blocks
		addGroupEntry(CinderscapesBlocks.SULFUR_BLOCK, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_NETHER_STORAGE_BLOCK);


		// NATURAL

		// Ores
		addGroupEntry(CinderscapesBlocks.SULFUR_QUARTZ_ORE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_QUARTZ_ORE);
		addGroupEntry(CinderscapesBlocks.ROSE_QUARTZ_ORE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_QUARTZ_ORE);
		addGroupEntry(CinderscapesBlocks.SMOKY_QUARTZ_ORE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_QUARTZ_ORE);
		addGroupEntry(CinderscapesBlocks.SULFUR_ORE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_QUARTZ_ORE);
		addGroupEntry(CinderscapesBlocks.SULFUR_BLOCK, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_QUARTZ_ORE);

		// Snow-like
		addGroupEntry(CinderscapesBlocks.ASH_BLOCK, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SNOWLIKE);
		addGroupEntry(CinderscapesBlocks.ASH, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SNOWLIKE);

		// Nylium
		addGroupEntry(CinderscapesBlocks.UMBRAL_NYLIUM, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NYLIUM);
		if (CinderscapesConfig.INSTANCE.easterEggs) {
			addGroupEntry(CinderscapesBlocks.NODZOL, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NYLIUM);
		}

		// Stems and worldgen Hyphae
		addGroupEntry(CinderscapesBlocks.SCORCHED_STEM, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_STEM);
		addGroupEntry(CinderscapesBlocks.SCORCHED_HYPHAE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_STEM);
		addGroupEntry(CinderscapesBlocks.UMBRAL_STEM, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_STEM);
		addGroupEntry(CinderscapesBlocks.UMBRAL_HYPHAE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_STEM);

		// Fungus
		addGroupEntry(CinderscapesBlocks.UMBRAL_FUNGUS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_FUNGUS);

		// Wart
		addGroupEntry(CinderscapesBlocks.UMBRAL_WART_BLOCK, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_WART_BLOCK);
		addGroupEntry(CinderscapesBlocks.UMBRAL_FLESH_BLOCK, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_WART_BLOCK);

		// Vegetation
		addGroupEntry(CinderscapesBlocks.TWILIGHT_FESCUES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.TWILIGHT_TENDRILS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.SCORCHED_SPROUTS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.SCORCHED_TENDRILS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.PHOTOFERN, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.SCORCHED_SHRUB, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.PYRACINTH, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.CRYSTINIUM, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.POLYPITE_QUARTZ, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.POLYPITE_SULFUR_QUARTZ, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.POLYPITE_ROSE_QUARTZ, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.POLYPITE_SMOKY_QUARTZ, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.TWILIGHT_VINE_BLOCK, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);
		addGroupEntry(CinderscapesBlocks.GHASTLY_ECTOPLASM, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_NETHER_VEGETATION);

		// Tall Plants
		addGroupEntry(CinderscapesBlocks.TALL_PHOTOFERN, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_TALL_VEGETATION);
		addGroupEntry(CinderscapesBlocks.LUMINOUS_POD, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_TALL_VEGETATION);

		// Berries
		addGroupEntry(CinderscapesItems.BRAMBLE_BERRIES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_BERRIES);


		// FUNCTIONAL

		// Wood Items
		addGroupEntry(CinderscapesBlocks.SCORCHED_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_NETHER_SIGN);
		addGroupEntry(CinderscapesBlocks.SCORCHED_HANGING_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_NETHER_SIGN);
		addGroupEntry(CinderscapesBlocks.UMBRAL_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_NETHER_SIGN);
		addGroupEntry(CinderscapesBlocks.UMBRAL_HANGING_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_NETHER_SIGN);


		// REDSTONE


		// HOTBAR


		// SEARCH


		// TOOLS


		// COMBAT


		// FOOD AND DRINK

		// Berries
		addGroupEntry(CinderscapesItems.BRAMBLE_BERRIES, CreativeModeTabs.FOOD_AND_DRINKS, FOOD_BERRIES);


		// INGREDIENTS

		// Quartz
		addGroupEntry(CinderscapesItems.SULFUR_QUARTZ, CreativeModeTabs.INGREDIENTS, INGREDIENTS_QUARTZ);
		addGroupEntry(CinderscapesItems.ROSE_QUARTZ, CreativeModeTabs.INGREDIENTS, INGREDIENTS_QUARTZ);
		addGroupEntry(CinderscapesItems.SMOKY_QUARTZ, CreativeModeTabs.INGREDIENTS, INGREDIENTS_QUARTZ);
		addGroupEntry(CinderscapesItems.SULFUR, CreativeModeTabs.INGREDIENTS, INGREDIENTS_QUARTZ);

		// SPAWN EGGS


		// INVENTORY


		/*
		 * Also add all the items to Cinderscapes' own item group.
		 */
		CinderscapesRegistry.register(Registries.CREATIVE_MODE_TAB, Cinderscapes.id("items"), CreativeModeTab.builder()
				.title(Component.literal("Cinderscapes"))
				.icon(() -> CinderscapesBlocks.UMBRAL_FUNGUS.asItem().getDefaultInstance())
				.displayItems((context, entries) -> {
					ITEM_GROUP_ENTRY_MAPS.values().stream()
							.map(HashMap::values).flatMap(Collection::stream)
							.map(ItemGroupEntries::getCollection).flatMap(Collection::stream)
							.collect(Collectors.groupingByConcurrent(ItemStack::getItem)).keySet().stream()
							.sorted(Comparator.comparing((item) -> item.getDescription().getString())).forEach(entries::accept);
				}).build()
		);
	}

	public static void onBuildContents(BuildCreativeModeTabContentsEvent event) {
		HashMap<ItemLike, ItemGroupEntries> entryMap = ITEM_GROUP_ENTRY_MAPS.get(event.getTabKey());
		if (entryMap == null) return;
		for (var entry : entryMap.entrySet()) {
			ItemStack anchor = entry.getKey() == null ? null : new ItemStack(entry.getKey());
			for (ItemStack stack : entry.getValue().getCollection()) {
				if (anchor == null || !event.getParentEntries().contains(anchor)) {
					event.accept(stack);
				} else {
					event.insertAfter(anchor, stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
					anchor = stack;
				}
			}
		}
	}

	public static void addGroupEntry(ItemLike item, ResourceKey<CreativeModeTab> group) {
		// Appends the item to the bottom of the group.
		addGroupEntry(item, group, null);
	}

	public static void addGroupEntry(ItemLike item, ResourceKey<CreativeModeTab> group, @Nullable ItemLike relative) {
		HashMap<ItemLike, ItemGroupEntries> entryMap = ITEM_GROUP_ENTRY_MAPS.computeIfAbsent(group, (key) -> new HashMap<>(32));
		ItemGroupEntries entries = entryMap.computeIfAbsent(relative, ItemGroupEntries::empty);
		entries.addItem(item);
	}

	public static void init() { }
}
