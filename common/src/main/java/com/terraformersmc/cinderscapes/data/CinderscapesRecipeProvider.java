package com.terraformersmc.cinderscapes.data;

import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import com.terraformersmc.cinderscapes.init.CinderscapesItems;
import com.terraformersmc.cinderscapes.tag.CinderscapesItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CinderscapesRecipeProvider extends FabricRecipeProvider {
	protected CinderscapesRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void buildRecipes(RecipeOutput exporter) {
		// vanilla recipes
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Items.COMPARATOR, 1)
				.pattern(" T ")
				.pattern("TQT")
				.pattern("SSS")
				.define('T', Items.REDSTONE_TORCH)
				.define('Q', ConventionalItemTags.QUARTZ_GEMS)
				.define('S', Items.STONE)
				.unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(ConventionalItemTags.QUARTZ_GEMS)))
				.save(exporter, ResourceLocation.withDefaultNamespace("comparator"));

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Items.DAYLIGHT_DETECTOR, 1)
				.pattern("GGG")
				.pattern("QQQ")
				.pattern("WWW")
				.define('G', Items.GLASS)
				.define('Q', ConventionalItemTags.QUARTZ_GEMS)
				.define('W', ItemTags.WOODEN_SLABS)
				.unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(ConventionalItemTags.QUARTZ_GEMS)))
				.save(exporter, ResourceLocation.withDefaultNamespace("daylight_detector"));

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Items.OBSERVER, 1)
				.pattern("CCC")
				.pattern("RRQ")
				.pattern("CCC")
				.define('C', Items.COBBLESTONE)
				.define('Q', ConventionalItemTags.QUARTZ_GEMS)
				.define('R', Items.REDSTONE)
				.unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(ConventionalItemTags.QUARTZ_GEMS)))
				.save(exporter, ResourceLocation.withDefaultNamespace("observer"));


		// misc. recipes
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CinderscapesBlocks.ASH, 6)
				.pattern("AAA")
				.define('A', CinderscapesBlocks.ASH_BLOCK)
				.unlockedBy("has_ash_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.ASH_BLOCK))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ASH_BLOCK, 1)
				.pattern("aa")
				.pattern("aa")
				.define('a', CinderscapesItems.ASH_PILE)
				.unlockedBy("has_ash_piles", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesItems.ASH_PILE))
				.save(exporter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1)
				.requires(CinderscapesItemTags.SULFURS)
				.requires(ItemTags.COALS)
				.requires(Items.BONE_MEAL)
				.unlockedBy("has_sulfurs", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(CinderscapesItemTags.SULFURS)))
				.save(exporter);

		nineBlockStorageRecipes(exporter,
				RecipeCategory.MISC, CinderscapesItems.SULFUR,
				RecipeCategory.BUILDING_BLOCKS,
				CinderscapesBlocks.SULFUR_BLOCK);

		oreBlasting(exporter,
				List.of(CinderscapesItems.SULFUR_ORE),
				RecipeCategory.MISC,
				CinderscapesItems.SULFUR,
				0.1f, 100, "blasting");

		oreSmelting(exporter,
				List.of(CinderscapesItems.SULFUR_ORE),
				RecipeCategory.MISC,
				CinderscapesItems.SULFUR,
				0.1f, 200, "smelting");


		// quartz recipes
		chiseled(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.CHISELED_ROSE_QUARTZ_BLOCK, CinderscapesBlocks.ROSE_QUARTZ_SLAB);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.CHISELED_ROSE_QUARTZ_BLOCK, CinderscapesBlocks.ROSE_QUARTZ_BLOCK);
		oreSmelting(exporter, List.of(CinderscapesBlocks.ROSE_QUARTZ_ORE), RecipeCategory.MISC, CinderscapesItems.ROSE_QUARTZ, 0.2f, 200, "misc");
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_BLOCK, 1)
				.pattern("qq")
				.pattern("qq")
				.define('q', CinderscapesItems.ROSE_QUARTZ)
				.unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesItems.ROSE_QUARTZ))
				.save(exporter);
		polished(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_BRICKS, CinderscapesBlocks.ROSE_QUARTZ_BLOCK);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_BRICKS, CinderscapesBlocks.ROSE_QUARTZ_BLOCK);
		oreBlasting(exporter, List.of(CinderscapesBlocks.ROSE_QUARTZ_ORE), RecipeCategory.MISC, CinderscapesItems.ROSE_QUARTZ, 0.2f, 100, "misc");
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_PILLAR, 2)
				.pattern("Q")
				.pattern("Q")
				.define('Q', CinderscapesBlocks.ROSE_QUARTZ_BLOCK)
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.ROSE_QUARTZ_BLOCK))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_PILLAR, CinderscapesBlocks.ROSE_QUARTZ_BLOCK);
		slabBuilder(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_SLAB, Ingredient.of(CinderscapesBlocks.CHISELED_ROSE_QUARTZ_BLOCK, CinderscapesBlocks.ROSE_QUARTZ_BLOCK, CinderscapesBlocks.ROSE_QUARTZ_PILLAR))
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(CinderscapesItemTags.ROSE_QUARTZ_CONVERTIBLES)))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_SLAB, CinderscapesBlocks.ROSE_QUARTZ_BLOCK, 2);
		stairBuilder(CinderscapesBlocks.ROSE_QUARTZ_STAIRS, Ingredient.of(CinderscapesBlocks.CHISELED_ROSE_QUARTZ_BLOCK, CinderscapesBlocks.ROSE_QUARTZ_BLOCK, CinderscapesBlocks.ROSE_QUARTZ_PILLAR))
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(CinderscapesItemTags.ROSE_QUARTZ_CONVERTIBLES)))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.ROSE_QUARTZ_STAIRS, CinderscapesBlocks.ROSE_QUARTZ_BLOCK);
		oreSmelting(exporter, List.of(CinderscapesBlocks.ROSE_QUARTZ_BLOCK), RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_ROSE_QUARTZ, 0.1f, 200, "building_blocks");
		slab(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_SLAB, CinderscapesBlocks.SMOOTH_ROSE_QUARTZ);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_SLAB, CinderscapesBlocks.SMOOTH_ROSE_QUARTZ, 2);
		stairBuilder(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_STAIRS, Ingredient.of(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ))
				.unlockedBy("has_smooth_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_STAIRS, CinderscapesBlocks.SMOOTH_ROSE_QUARTZ);

		chiseled(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.CHISELED_SMOKY_QUARTZ_BLOCK, CinderscapesBlocks.SMOKY_QUARTZ_SLAB);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.CHISELED_SMOKY_QUARTZ_BLOCK, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK);
		oreSmelting(exporter, List.of(CinderscapesBlocks.SMOKY_QUARTZ_ORE), RecipeCategory.MISC, CinderscapesItems.SMOKY_QUARTZ, 0.2f, 200, "misc");
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK, 1)
				.pattern("qq")
				.pattern("qq")
				.define('q', CinderscapesItems.SMOKY_QUARTZ)
				.unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesItems.SMOKY_QUARTZ))
				.save(exporter);
		polished(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_BRICKS, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_BRICKS, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK);
		oreBlasting(exporter, List.of(CinderscapesBlocks.SMOKY_QUARTZ_ORE), RecipeCategory.MISC, CinderscapesItems.SMOKY_QUARTZ, 0.2f, 100, "misc");
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_PILLAR, 2)
				.pattern("Q")
				.pattern("Q")
				.define('Q', CinderscapesBlocks.SMOKY_QUARTZ_BLOCK)
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SMOKY_QUARTZ_BLOCK))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_PILLAR, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK);
		slabBuilder(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_SLAB, Ingredient.of(CinderscapesBlocks.CHISELED_SMOKY_QUARTZ_BLOCK, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK, CinderscapesBlocks.SMOKY_QUARTZ_PILLAR))
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(CinderscapesItemTags.SMOKY_QUARTZ_CONVERTIBLES)))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_SLAB, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK, 2);
		stairBuilder(CinderscapesBlocks.SMOKY_QUARTZ_STAIRS, Ingredient.of(CinderscapesBlocks.CHISELED_SMOKY_QUARTZ_BLOCK, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK, CinderscapesBlocks.SMOKY_QUARTZ_PILLAR))
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(CinderscapesItemTags.SMOKY_QUARTZ_CONVERTIBLES)))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOKY_QUARTZ_STAIRS, CinderscapesBlocks.SMOKY_QUARTZ_BLOCK);
		oreSmelting(exporter, List.of(CinderscapesBlocks.SMOKY_QUARTZ_BLOCK), RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ, 0.1f, 200, "building_blocks");
		slab(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_SLAB, CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_SLAB, CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ, 2);
		stairBuilder(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS, Ingredient.of(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ))
				.unlockedBy("has_smooth_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS, CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ);

		chiseled(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.CHISELED_SULFUR_QUARTZ_BLOCK, CinderscapesBlocks.SULFUR_QUARTZ_SLAB);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.CHISELED_SULFUR_QUARTZ_BLOCK, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK);
		oreSmelting(exporter, List.of(CinderscapesBlocks.SULFUR_QUARTZ_ORE), RecipeCategory.MISC, CinderscapesItems.SULFUR_QUARTZ, 0.2f, 200, "misc");
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK, 1)
				.pattern("qq")
				.pattern("qq")
				.define('q', CinderscapesItems.SULFUR_QUARTZ)
				.unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesItems.SULFUR_QUARTZ))
				.save(exporter);
		polished(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_BRICKS, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_BRICKS, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK);
		oreBlasting(exporter, List.of(CinderscapesBlocks.SULFUR_QUARTZ_ORE), RecipeCategory.MISC, CinderscapesItems.SULFUR_QUARTZ, 0.2f, 100, "misc");
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_PILLAR, 2)
				.pattern("Q")
				.pattern("Q")
				.define('Q', CinderscapesBlocks.SULFUR_QUARTZ_BLOCK)
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SULFUR_QUARTZ_BLOCK))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_PILLAR, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK);
		slabBuilder(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_SLAB, Ingredient.of(CinderscapesBlocks.CHISELED_SULFUR_QUARTZ_BLOCK, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK, CinderscapesBlocks.SULFUR_QUARTZ_PILLAR))
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(CinderscapesItemTags.SULFUR_QUARTZ_CONVERTIBLES)))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_SLAB, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK, 2);
		stairBuilder(CinderscapesBlocks.SULFUR_QUARTZ_STAIRS, Ingredient.of(CinderscapesBlocks.CHISELED_SULFUR_QUARTZ_BLOCK, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK, CinderscapesBlocks.SULFUR_QUARTZ_PILLAR))
				.unlockedBy("has_quartz_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(getItemTagPredicate(CinderscapesItemTags.SULFUR_QUARTZ_CONVERTIBLES)))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SULFUR_QUARTZ_STAIRS, CinderscapesBlocks.SULFUR_QUARTZ_BLOCK);
		oreSmelting(exporter, List.of(CinderscapesBlocks.SULFUR_QUARTZ_BLOCK), RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ, 0.1f, 200, "building_blocks");
		slab(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_SLAB, CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_SLAB, CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ, 2);
		stairBuilder(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_STAIRS, Ingredient.of(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ))
				.unlockedBy("has_smooth_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ))
				.save(exporter);
		stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_STAIRS, CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ);


		// wood recipes
		oneToOneConversionRecipe(exporter, CinderscapesBlocks.SCORCHED_BUTTON, CinderscapesBlocks.SCORCHED_PLANKS, "redstone");
		doorBuilder(CinderscapesBlocks.SCORCHED_DOOR, Ingredient.of(CinderscapesBlocks.SCORCHED_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SCORCHED_PLANKS))
				.save(exporter);
		fenceBuilder(CinderscapesBlocks.SCORCHED_FENCE, Ingredient.of(CinderscapesBlocks.SCORCHED_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SCORCHED_PLANKS))
				.save(exporter);
		fenceGateBuilder(CinderscapesBlocks.SCORCHED_FENCE_GATE, Ingredient.of(CinderscapesBlocks.SCORCHED_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SCORCHED_PLANKS))
				.save(exporter);
		hangingSign(exporter, CinderscapesItems.SCORCHED_HANGING_SIGN, CinderscapesBlocks.STRIPPED_SCORCHED_STEM);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SCORCHED_HYPHAE, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.define('L', CinderscapesBlocks.SCORCHED_STEM)
				.unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SCORCHED_STEM))
				.save(exporter);
		planksFromLogs(exporter, CinderscapesBlocks.SCORCHED_PLANKS, CinderscapesItemTags.SCORCHED_STEMS, 4);
		pressurePlate(exporter, CinderscapesBlocks.SCORCHED_PRESSURE_PLATE, CinderscapesBlocks.SCORCHED_PLANKS);
		signBuilder(CinderscapesItems.SCORCHED_SIGN, Ingredient.of(CinderscapesBlocks.SCORCHED_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SCORCHED_PLANKS))
				.save(exporter);
		slab(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.SCORCHED_SLAB, CinderscapesBlocks.SCORCHED_PLANKS);
		stairBuilder(CinderscapesBlocks.SCORCHED_STAIRS, Ingredient.of(CinderscapesBlocks.SCORCHED_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SCORCHED_PLANKS))
				.save(exporter);
		trapdoorBuilder(CinderscapesBlocks.SCORCHED_TRAPDOOR, Ingredient.of(CinderscapesBlocks.SCORCHED_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.SCORCHED_PLANKS))
				.save(exporter);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.define('L', CinderscapesBlocks.STRIPPED_SCORCHED_STEM)
				.unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.STRIPPED_SCORCHED_STEM))
				.save(exporter);

		oneToOneConversionRecipe(exporter, CinderscapesBlocks.UMBRAL_BUTTON, CinderscapesBlocks.UMBRAL_PLANKS, "redstone");
		doorBuilder(CinderscapesBlocks.UMBRAL_DOOR, Ingredient.of(CinderscapesBlocks.UMBRAL_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.UMBRAL_PLANKS))
				.save(exporter);
		fenceBuilder(CinderscapesBlocks.UMBRAL_FENCE, Ingredient.of(CinderscapesBlocks.UMBRAL_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.UMBRAL_PLANKS))
				.save(exporter);
		fenceGateBuilder(CinderscapesBlocks.UMBRAL_FENCE_GATE, Ingredient.of(CinderscapesBlocks.UMBRAL_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.UMBRAL_PLANKS))
				.save(exporter);
		hangingSign(exporter, CinderscapesItems.UMBRAL_HANGING_SIGN, CinderscapesBlocks.STRIPPED_UMBRAL_STEM);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.UMBRAL_HYPHAE, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.define('L', CinderscapesBlocks.UMBRAL_STEM)
				.unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.UMBRAL_STEM))
				.save(exporter);
		planksFromLogs(exporter, CinderscapesBlocks.UMBRAL_PLANKS, CinderscapesItemTags.UMBRAL_STEMS, 4);
		pressurePlate(exporter, CinderscapesBlocks.UMBRAL_PRESSURE_PLATE, CinderscapesBlocks.UMBRAL_PLANKS);
		signBuilder(CinderscapesItems.UMBRAL_SIGN, Ingredient.of(CinderscapesBlocks.UMBRAL_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.UMBRAL_PLANKS))
				.save(exporter);
		slab(exporter, RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.UMBRAL_SLAB, CinderscapesBlocks.UMBRAL_PLANKS);
		stairBuilder(CinderscapesBlocks.UMBRAL_STAIRS, Ingredient.of(CinderscapesBlocks.UMBRAL_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.UMBRAL_PLANKS))
				.save(exporter);
		trapdoorBuilder(CinderscapesBlocks.UMBRAL_TRAPDOOR, Ingredient.of(CinderscapesBlocks.UMBRAL_PLANKS))
				.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.UMBRAL_PLANKS))
				.save(exporter);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.define('L', CinderscapesBlocks.STRIPPED_UMBRAL_STEM)
				.unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(CinderscapesBlocks.STRIPPED_UMBRAL_STEM))
				.save(exporter);
	}

	// Returns an ItemPredicate matching any item in the provided ItemTag key.
	private static ItemPredicate getItemTagPredicate(TagKey<Item> itemTagKey) {
		return ItemPredicate.Builder.item().of(itemTagKey).build();
	}

	@Override
	protected ResourceLocation getRecipeIdentifier(ResourceLocation identifier) {
		return ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, identifier.getPath());
	}
}
