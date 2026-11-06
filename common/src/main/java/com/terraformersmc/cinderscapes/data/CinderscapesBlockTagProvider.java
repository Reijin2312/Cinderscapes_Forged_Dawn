package com.terraformersmc.cinderscapes.data;

import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import com.terraformersmc.cinderscapes.tag.CinderscapesBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

public class CinderscapesBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	protected CinderscapesBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void addTags(HolderLookup.Provider registries) {
		// basic block tags
		tag(BlockTags.ENDERMAN_HOLDABLE)
			.add(CinderscapesBlocks.NODZOL)
			.add(CinderscapesBlocks.UMBRAL_FUNGUS)
			.add(CinderscapesBlocks.UMBRAL_NYLIUM);

		tag(BlockTags.FLOWER_POTS)
			.add(CinderscapesBlocks.POTTED_CRYSTINIUM)
			.add(CinderscapesBlocks.POTTED_LUMINOUS_POD)
			.add(CinderscapesBlocks.POTTED_PHOTOFERN)
			.add(CinderscapesBlocks.POTTED_POLYPITE_QUARTZ)
			.add(CinderscapesBlocks.POTTED_POLYPITE_ROSE_QUARTZ)
			.add(CinderscapesBlocks.POTTED_POLYPITE_SMOKY_QUARTZ)
			.add(CinderscapesBlocks.POTTED_POLYPITE_SULFUR_QUARTZ)
			.add(CinderscapesBlocks.POTTED_PYRACINTH)
			.add(CinderscapesBlocks.POTTED_SCORCHED_SHRUB)
			.add(CinderscapesBlocks.POTTED_SCORCHED_TENDRILS)
			.add(CinderscapesBlocks.POTTED_TWILIGHT_TENDRILS)
			.add(CinderscapesBlocks.POTTED_UMBRAL_FUNGUS);

		tag(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
			.add(CinderscapesBlocks.ASH)
			.add(CinderscapesBlocks.SCORCHED_SPROUTS)
			.add(CinderscapesBlocks.SCORCHED_TENDRILS)
			.add(CinderscapesBlocks.TWILIGHT_FESCUES)
			.add(CinderscapesBlocks.TWILIGHT_TENDRILS);

		tag(BlockTags.LOGS)
			.add(CinderscapesBlocks.SCORCHED_HYPHAE)
			.add(CinderscapesBlocks.SCORCHED_STEM)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_STEM)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_STEM)
			.add(CinderscapesBlocks.UMBRAL_HYPHAE)
			.add(CinderscapesBlocks.UMBRAL_STEM);

		tag(BlockTags.MUSHROOM_GROW_BLOCK)
			.add(CinderscapesBlocks.NODZOL);

		tag(BlockTags.NETHER_CARVER_REPLACEABLES)
			.add(CinderscapesBlocks.UMBRAL_NYLIUM)
			.add(CinderscapesBlocks.UMBRAL_WART_BLOCK);

		tag(BlockTags.NYLIUM)
			.add(CinderscapesBlocks.UMBRAL_NYLIUM);

		tag(BlockTags.PLANKS)
			.add(CinderscapesBlocks.UMBRAL_PLANKS)
			.add(CinderscapesBlocks.SCORCHED_PLANKS);

		tag(BlockTags.CEILING_HANGING_SIGNS)
			.add(CinderscapesBlocks.SCORCHED_HANGING_SIGN)
			.add(CinderscapesBlocks.UMBRAL_HANGING_SIGN);

		tag(BlockTags.WALL_HANGING_SIGNS)
			.add(CinderscapesBlocks.SCORCHED_WALL_HANGING_SIGN)
			.add(CinderscapesBlocks.UMBRAL_WALL_HANGING_SIGN);

		tag(BlockTags.STANDING_SIGNS)
			.add(CinderscapesBlocks.SCORCHED_SIGN)
			.add(CinderscapesBlocks.UMBRAL_SIGN);

		tag(BlockTags.WALL_SIGNS)
			.add(CinderscapesBlocks.SCORCHED_WALL_SIGN)
			.add(CinderscapesBlocks.UMBRAL_WALL_SIGN);

		tag(BlockTags.SLABS)
			.add(CinderscapesBlocks.ROSE_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_SLAB);

		tag(BlockTags.SMALL_FLOWERS)
			.add(CinderscapesBlocks.CRYSTINIUM)
			.add(CinderscapesBlocks.PYRACINTH)
			.add(CinderscapesBlocks.SCORCHED_TENDRILS)
			.add(CinderscapesBlocks.TWILIGHT_TENDRILS);

		tag(BlockTags.STAIRS)
			.add(CinderscapesBlocks.ROSE_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_STAIRS);

		tag(BlockTags.WART_BLOCKS)
			.add(CinderscapesBlocks.UMBRAL_WART_BLOCK);

		tag(BlockTags.WOODEN_BUTTONS)
			.add(CinderscapesBlocks.SCORCHED_BUTTON)
			.add(CinderscapesBlocks.UMBRAL_BUTTON);

		tag(BlockTags.WOODEN_DOORS)
			.add(CinderscapesBlocks.SCORCHED_DOOR)
			.add(CinderscapesBlocks.UMBRAL_DOOR);

		tag(BlockTags.WOODEN_FENCES)
			.add(CinderscapesBlocks.SCORCHED_FENCE)
			.add(CinderscapesBlocks.UMBRAL_FENCE);

		tag(BlockTags.WOODEN_PRESSURE_PLATES)
			.add(CinderscapesBlocks.SCORCHED_PRESSURE_PLATE)
			.add(CinderscapesBlocks.UMBRAL_PRESSURE_PLATE);

		tag(BlockTags.WOODEN_SLABS)
			.add(CinderscapesBlocks.SCORCHED_SLAB)
			.add(CinderscapesBlocks.UMBRAL_SLAB);

		tag(BlockTags.WOODEN_STAIRS)
			.add(CinderscapesBlocks.SCORCHED_STAIRS)
			.add(CinderscapesBlocks.UMBRAL_STAIRS);

		tag(BlockTags.WOODEN_TRAPDOORS)
			.add(CinderscapesBlocks.SCORCHED_TRAPDOOR)
			.add(CinderscapesBlocks.UMBRAL_TRAPDOOR);


		// tool block tags
		tag(BlockTags.MINEABLE_WITH_AXE)
			.add(CinderscapesBlocks.SCORCHED_HYPHAE)
			.add(CinderscapesBlocks.SCORCHED_STEM)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_STEM)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_STEM)
			.add(CinderscapesBlocks.TWILIGHT_VINE_BLOCK)
			.add(CinderscapesBlocks.UMBRAL_FUNGUS)
			.add(CinderscapesBlocks.UMBRAL_HYPHAE)
			.add(CinderscapesBlocks.UMBRAL_STEM);

		tag(BlockTags.MINEABLE_WITH_HOE)
			.add(CinderscapesBlocks.UMBRAL_FLESH_BLOCK)
			.add(CinderscapesBlocks.UMBRAL_WART_BLOCK);

		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(CinderscapesBlocks.CHISELED_ROSE_QUARTZ_BLOCK)
			.add(CinderscapesBlocks.CHISELED_SMOKY_QUARTZ_BLOCK)
			.add(CinderscapesBlocks.CHISELED_SULFUR_QUARTZ_BLOCK)
			.add(CinderscapesBlocks.CRYSTALLINE_QUARTZ)
			.add(CinderscapesBlocks.CRYSTALLINE_ROSE_QUARTZ)
			.add(CinderscapesBlocks.CRYSTALLINE_SMOKY_QUARTZ)
			.add(CinderscapesBlocks.CRYSTALLINE_SULFUR_QUARTZ)
			.add(CinderscapesBlocks.NODZOL)
			.add(CinderscapesBlocks.POLYPITE_QUARTZ)
			.add(CinderscapesBlocks.POLYPITE_ROSE_QUARTZ)
			.add(CinderscapesBlocks.POLYPITE_SMOKY_QUARTZ)
			.add(CinderscapesBlocks.POLYPITE_SULFUR_QUARTZ)
			.add(CinderscapesBlocks.ROSE_QUARTZ_BLOCK)
			.add(CinderscapesBlocks.ROSE_QUARTZ_BRICKS)
			.add(CinderscapesBlocks.ROSE_QUARTZ_ORE)
			.add(CinderscapesBlocks.ROSE_QUARTZ_PILLAR)
			.add(CinderscapesBlocks.ROSE_QUARTZ_SLAB)
			.add(CinderscapesBlocks.ROSE_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_BLOCK)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_BRICKS)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_ORE)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_PILLAR)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ)
			.add(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOOTH_ROSE_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ)
			.add(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ)
			.add(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SMOOTH_SULFUR_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.SULFUR_ORE)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_BLOCK)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_BRICKS)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_ORE)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_PILLAR)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_SLAB)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_STAIRS)
			.add(CinderscapesBlocks.UMBRAL_NYLIUM);

		tag(BlockTags.MINEABLE_WITH_SHOVEL)
			.add(CinderscapesBlocks.ASH)
			.add(CinderscapesBlocks.ASH_BLOCK);


		// conventional tags
		tag(ConventionalBlockTags.ORES)
			.add(CinderscapesBlocks.SULFUR_ORE);

		tag(ConventionalBlockTags.QUARTZ_ORES)
			.add(CinderscapesBlocks.ROSE_QUARTZ_ORE)
			.add(CinderscapesBlocks.SMOKY_QUARTZ_ORE)
			.add(CinderscapesBlocks.SULFUR_QUARTZ_ORE);

		tag(CinderscapesBlockTags.STRIPPED_LOGS)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_STEM)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_STEM);

		tag(CinderscapesBlockTags.STRIPPED_WOOD)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE);


		// local block tags
		tag(CinderscapesBlockTags.ASH_PERMEABLE)
			.addTag(BlockTags.NYLIUM)
			.add(CinderscapesBlocks.ASH)
			.add(CinderscapesBlocks.ASH_BLOCK)
			.add(Blocks.BASALT)
			.add(Blocks.BEDROCK)
			.add(Blocks.BLACKSTONE)
			.add(Blocks.GLOWSTONE)
			.add(Blocks.GRAVEL)
			.add(Blocks.LAVA)
			.add(Blocks.NETHERRACK)
			.add(CinderscapesBlocks.NODZOL)
			.add(Blocks.SOUL_SAND)
			.add(Blocks.SOUL_SOIL);

		tag(CinderscapesBlockTags.SCORCHED_STEMS)
			.add(CinderscapesBlocks.SCORCHED_HYPHAE)
			.add(CinderscapesBlocks.SCORCHED_STEM)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE)
			.add(CinderscapesBlocks.STRIPPED_SCORCHED_STEM);

		tag(CinderscapesBlockTags.UMBRAL_STEMS)
			.add(CinderscapesBlocks.UMBRAL_HYPHAE)
			.add(CinderscapesBlocks.UMBRAL_STEM)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE)
			.add(CinderscapesBlocks.STRIPPED_UMBRAL_STEM);
	}
}
