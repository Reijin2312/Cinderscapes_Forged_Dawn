package com.terraformersmc.cinderscapes.gametest;

import com.terraformersmc.cinderscapes.block.AshLayerBlock;
import com.terraformersmc.cinderscapes.block.BrambleBerryBushBlock;
import com.terraformersmc.cinderscapes.block.PhotofernBlock;
import com.terraformersmc.cinderscapes.block.GhastlyEctoplasmBlock;
import com.terraformersmc.cinderscapes.block.PolypiteQuartzBlock;
import com.terraformersmc.cinderscapes.init.CinderscapesBiomes;
import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import com.terraformersmc.cinderscapes.init.CinderscapesConfiguredFeatures;
import com.terraformersmc.cinderscapes.init.CinderscapesItems;
import com.terraformersmc.cinderscapes.init.CinderscapesPlacedFeatures;
import com.terraformersmc.cinderscapes.init.CinderscapesFeatures;
import com.terraformersmc.cinderscapes.init.CinderscapesPlacementModifierTypes;
import com.terraformersmc.cinderscapes.init.CinderscapesSoundEvents;
import com.terraformersmc.cinderscapes.init.CinderscapesTrades;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.property.Properties;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.GameMode;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.loot.entry.LootPoolEntryType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@GameTestHolder("cinderscapes")
@PrefixGameTestTemplate(false)
public final class CinderscapesGameTests {
    private static final String TEMPLATE = "empty";

    private CinderscapesGameTests() {}

    @GameTest(templateName = TEMPLATE)
    public static void allStaticRegistrationsArePresent(TestContext test) throws IllegalAccessException {
        assertRegisteredFields(test, CinderscapesBlocks.class, Block.class, Registries.BLOCK);
        assertRegisteredFields(test, CinderscapesItems.class, Item.class, Registries.ITEM);
        assertRegisteredFields(test, CinderscapesFeatures.class, Feature.class, Registries.FEATURE);
        assertRegisteredFields(test, CinderscapesPlacementModifierTypes.class, PlacementModifierType.class,
                Registries.PLACEMENT_MODIFIER_TYPE);
        assertRegisteredFields(test, CinderscapesSoundEvents.class, SoundEvent.class, Registries.SOUND_EVENT);
        assertRegisteredFields(test, CinderscapesTrades.class, LootPoolEntryType.class, Registries.LOOT_POOL_ENTRY_TYPE);
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void allDynamicWorldgenEntriesAreLoaded(TestContext test) throws IllegalAccessException {
        assertDynamicKeys(test, CinderscapesBiomes.class, RegistryKeys.BIOME);
        assertDynamicKeys(test, CinderscapesConfiguredFeatures.class, RegistryKeys.CONFIGURED_FEATURE);
        assertDynamicKeys(test, CinderscapesPlacedFeatures.class, RegistryKeys.PLACED_FEATURE);
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void everyBlockHasAnItemAndLootTable(TestContext test) {
        for (Block block : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(block);
            if (!"cinderscapes".equals(id.getNamespace()) || id.getPath().startsWith("potted_")
                    || id.getPath().contains("wall_sign")) continue;
            test.assertTrue(block.asItem() != Items.AIR, "No item for block " + id);
            test.assertTrue(test.getWorld().getServer().getLootManager().getLootTable(block.getLootTableId()) != LootTable.EMPTY,
                    "No loot table for block " + id);
        }
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void recipesAndTagsAreLoaded(TestContext test) {
        long recipes = test.getWorld().getRecipeManager().values().stream()
                .filter(recipe -> "cinderscapes".equals(recipe.getId().getNamespace())).count();
        test.assertTrue(recipes >= 60, "Only " + recipes + " Cinderscapes recipes loaded");
        test.assertTrue(CinderscapesItems.BRAMBLE_BERRIES.getDefaultStack().isFood(), "Bramble berries are not food");
        test.assertTrue(CinderscapesItems.SCORCHED_STEM.getDefaultStack().isIn(net.minecraft.registry.tag.ItemTags.LOGS),
                "Scorched stem is absent from logs tag");
        test.assertTrue(CinderscapesItems.UMBRAL_STEM.getDefaultStack().isIn(net.minecraft.registry.tag.ItemTags.LOGS),
                "Umbral stem is absent from logs tag");
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void netherPlantsRespectAllSupportedSoils(TestContext test) {
        BlockPos plantPos = new BlockPos(1, 2, 1);
        for (Block soil : new Block[]{Blocks.NETHERRACK, Blocks.SOUL_SOIL, CinderscapesBlocks.UMBRAL_NYLIUM}) {
            test.setBlockState(plantPos.down(), soil);
            for (Block plant : new Block[]{CinderscapesBlocks.PYRACINTH, CinderscapesBlocks.CRYSTINIUM,
                    CinderscapesBlocks.PHOTOFERN, CinderscapesBlocks.TWILIGHT_TENDRILS}) {
                test.assertTrue(plant.getDefaultState().canPlaceAt(test.getWorld(), test.getAbsolutePos(plantPos)),
                        Registries.BLOCK.getId(plant) + " cannot survive on " + Registries.BLOCK.getId(soil));
            }
        }
        for (Block soil : new Block[]{Blocks.NETHERRACK, Blocks.SOUL_SOIL, Blocks.SOUL_SAND, Blocks.GRAVEL}) {
            test.setBlockState(plantPos.down(), soil);
            test.assertTrue(CinderscapesBlocks.BRAMBLE_BERRY_BUSH.getDefaultState()
                            .canPlaceAt(test.getWorld(), test.getAbsolutePos(plantPos)),
                    "Bramble cannot survive on " + Registries.BLOCK.getId(soil));
        }
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void photofernBonemealCreatesCorrectDoublePlant(TestContext test) {
        BlockPos pos = new BlockPos(1, 2, 1);
        test.setBlockState(pos.down(), CinderscapesBlocks.UMBRAL_NYLIUM);
        test.setBlockState(pos, CinderscapesBlocks.PHOTOFERN);
        ((PhotofernBlock) CinderscapesBlocks.PHOTOFERN).grow(test.getWorld(), test.getWorld().getRandom(),
                test.getAbsolutePos(pos), CinderscapesBlocks.PHOTOFERN.getDefaultState());
        BlockState lower = test.getBlockState(pos);
        BlockState upper = test.getBlockState(pos.up());
        test.assertTrue(lower.isOf(CinderscapesBlocks.TALL_PHOTOFERN)
                        && lower.get(Properties.DOUBLE_BLOCK_HALF) == net.minecraft.block.enums.DoubleBlockHalf.LOWER,
                "Photofern did not grow a lower tall-photofern half");
        test.assertTrue(upper.isOf(CinderscapesBlocks.TALL_PHOTOFERN)
                        && upper.get(Properties.DOUBLE_BLOCK_HALF) == net.minecraft.block.enums.DoubleBlockHalf.UPPER,
                "Photofern did not grow an upper tall-photofern half");
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void brambleBushHarvestResetsAgeAndDropsBerries(TestContext test) {
        BlockPos pos = new BlockPos(1, 2, 1);
        test.setBlockState(pos.down(), Blocks.NETHERRACK);
        test.setBlockState(pos, CinderscapesBlocks.BRAMBLE_BERRY_BUSH.getDefaultState()
                .with(SweetBerryBushBlock.AGE, 3));
        PlayerEntity player = test.createMockSurvivalPlayer();
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH).onUse(test.getBlockState(pos), test.getWorld(),
                test.getAbsolutePos(pos), player, Hand.MAIN_HAND,
                new BlockHitResult(Vec3d.ofCenter(test.getAbsolutePos(pos)), Direction.UP, test.getAbsolutePos(pos), false));
        test.assertTrue(test.getBlockState(pos).get(SweetBerryBushBlock.AGE) == 1, "Harvest did not reset bush age to 1");
        test.expectItemAt(CinderscapesItems.BRAMBLE_BERRIES, pos, 2.0);
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void brambleSlowsAndHurtsMobsExceptHoglins(TestContext test) {
        BlockPos pos = new BlockPos(2, 2, 2);
        BlockState bush = CinderscapesBlocks.BRAMBLE_BERRY_BUSH.getDefaultState().with(SweetBerryBushBlock.AGE, 2);
        test.setBlockState(pos, bush);
        PlayerEntity zombie = test.createMockSurvivalPlayer();
        zombie.setPosition(test.getAbsolutePos(pos).getX() + 0.75, test.getAbsolutePos(pos).getY(),
                test.getAbsolutePos(pos).getZ() + 0.5);
        zombie.lastRenderX = zombie.getX() - 0.25;
        zombie.timeUntilRegen = 0;
        float zombieHealth = zombie.getHealth();
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH)
                .onEntityCollision(bush, test.getWorld(), test.getAbsolutePos(pos), zombie);
        test.assertTrue(zombie.getHealth() == zombieHealth - 1.0F, "Bramble did not hurt a moving zombie");
        HoglinEntity hoglin = test.spawnEntity(EntityType.HOGLIN, pos);
        hoglin.setPosition(hoglin.getX() + 0.25, hoglin.getY(), hoglin.getZ());
        hoglin.lastRenderX = hoglin.getX() - 0.25;
        hoglin.timeUntilRegen = 0;
        float hoglinHealth = hoglin.getHealth();
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH)
                .onEntityCollision(bush, test.getWorld(), test.getAbsolutePos(pos), hoglin);
        test.assertTrue(hoglin.getHealth() == hoglinHealth, "Bramble hurt an immune hoglin");
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void ectoplasmChainsChooseTopMiddleBottomAndNeedCeiling(TestContext test) {
        GhastlyEctoplasmBlock ectoplasm = (GhastlyEctoplasmBlock) CinderscapesBlocks.GHASTLY_ECTOPLASM;
        BlockPos top = new BlockPos(2, 4, 2);
        test.setBlockState(top.up(), Blocks.NETHERRACK);
        test.setBlockState(top, ectoplasm);
        test.setBlockState(top.down(), ectoplasm);
        test.setBlockState(top.down(2), ectoplasm);
        test.assertTrue(ectoplasm.evaluateState(ectoplasm.getDefaultState(), test.getAbsolutePos(top), test.getWorld())
                .get(GhastlyEctoplasmBlock.TYPE) == GhastlyEctoplasmBlock.Type.TOP, "Ectoplasm top type is wrong");
        test.assertTrue(ectoplasm.evaluateState(ectoplasm.getDefaultState(), test.getAbsolutePos(top.down()), test.getWorld())
                .get(GhastlyEctoplasmBlock.TYPE) == GhastlyEctoplasmBlock.Type.MIDDLE, "Ectoplasm middle type is wrong");
        test.assertTrue(ectoplasm.evaluateState(ectoplasm.getDefaultState(), test.getAbsolutePos(top.down(2)), test.getWorld())
                .get(GhastlyEctoplasmBlock.TYPE) == GhastlyEctoplasmBlock.Type.BOTTOM, "Ectoplasm bottom type is wrong");
        test.setBlockState(top.up(), Blocks.AIR);
        test.setBlockState(top.down(), Blocks.AIR);
        test.assertTrue(ectoplasm.evaluateState(ectoplasm.getDefaultState(), test.getAbsolutePos(top), test.getWorld()).isAir(),
                "Unsupported ectoplasm did not break");
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void polypiteSupportsEveryDirectionAndBreaksWithoutSupport(TestContext test) {
        PolypiteQuartzBlock[] blocks = { (PolypiteQuartzBlock) CinderscapesBlocks.POLYPITE_QUARTZ,
                (PolypiteQuartzBlock) CinderscapesBlocks.POLYPITE_ROSE_QUARTZ,
                (PolypiteQuartzBlock) CinderscapesBlocks.POLYPITE_SMOKY_QUARTZ,
                (PolypiteQuartzBlock) CinderscapesBlocks.POLYPITE_SULFUR_QUARTZ };
        BlockPos pos = new BlockPos(3, 4, 3);
        for (PolypiteQuartzBlock block : blocks) {
            for (Direction supportDirection : Direction.values()) {
                test.setBlockState(pos.offset(supportDirection), Blocks.NETHERRACK);
                BlockState state = block.stateOf(supportDirection);
                test.setBlockState(pos, state);
                test.assertTrue(!state.getOutlineShape(test.getWorld(), test.getAbsolutePos(pos)).isEmpty(),
                        "Missing polypite shape for " + supportDirection);
                test.setBlockState(pos.offset(supportDirection), Blocks.AIR);
                BlockState updated = state.getStateForNeighborUpdate(supportDirection, Blocks.AIR.getDefaultState(),
                        test.getWorld(), test.getAbsolutePos(pos), test.getAbsolutePos(pos.offset(supportDirection)));
                test.assertTrue(updated.isAir(), "Unsupported polypite did not break for " + supportDirection);
            }
        }
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void netherrackBonemealCopiesNearbyNyliumAndNodzolSupportsPlants(TestContext test) {
        BlockPos pos = new BlockPos(2, 2, 2);
        test.setBlockState(pos, Blocks.NETHERRACK);
        test.setBlockState(pos.east(), CinderscapesBlocks.UMBRAL_NYLIUM);
        ((net.minecraft.block.Fertilizable) Blocks.NETHERRACK).grow(test.getWorld(), test.getWorld().getRandom(),
                test.getAbsolutePos(pos), Blocks.NETHERRACK.getDefaultState());
        test.assertTrue(test.getBlockState(pos).isOf(CinderscapesBlocks.UMBRAL_NYLIUM),
                "Netherrack did not copy nearby umbral nylium");
        test.setBlockState(pos, CinderscapesBlocks.NODZOL);
        test.assertTrue(Blocks.DANDELION.getDefaultState().canPlaceAt(test.getWorld(), test.getAbsolutePos(pos.up())),
                "Vanilla plants cannot survive on nodzol");
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void ashLayersNeverMeltAndDropCorrectCounts(TestContext test) {
        BlockPos pos = new BlockPos(1, 2, 1);
        PlayerEntity player = test.createMockSurvivalPlayer();
        test.setBlockState(pos.down(), Blocks.NETHERRACK);
        for (int layers = 1; layers <= 8; layers++) {
            BlockState state = CinderscapesBlocks.ASH.getDefaultState().with(AshLayerBlock.LAYERS, layers);
            test.setBlockState(pos, state);
            ((AshLayerBlock) CinderscapesBlocks.ASH).randomTick(state, test.getWorld(), test.getAbsolutePos(pos),
                    test.getWorld().getRandom());
            test.assertTrue(test.getBlockState(pos).equals(state), "Ash changed during random tick at " + layers + " layers");
            List<ItemStack> drops = Block.getDroppedStacks(state, test.getWorld(), test.getAbsolutePos(pos), null, player,
                    new ItemStack(Items.IRON_SHOVEL));
            int count = drops.stream().filter(s -> s.isOf(CinderscapesItems.ASH_PILE)).mapToInt(ItemStack::getCount).sum();
            test.assertTrue(count == layers, "Ash dropped " + count + " piles instead of " + layers);
        }
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void axesStripEveryCinderscapesLog(TestContext test) {
        Block[][] pairs = {
                {CinderscapesBlocks.SCORCHED_STEM, CinderscapesBlocks.STRIPPED_SCORCHED_STEM},
                {CinderscapesBlocks.SCORCHED_HYPHAE, CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE},
                {CinderscapesBlocks.UMBRAL_STEM, CinderscapesBlocks.STRIPPED_UMBRAL_STEM},
                {CinderscapesBlocks.UMBRAL_HYPHAE, CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE}
        };
        PlayerEntity player = test.createMockCreativePlayer();
        player.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.DIAMOND_AXE));
        for (int i = 0; i < pairs.length; i++) {
            BlockPos pos = new BlockPos(1 + i, 2, 1);
            test.setBlockState(pos, pairs[i][0]);
            BlockPos absolutePos = test.getAbsolutePos(pos);
            player.getMainHandStack().useOnBlock(new ItemUsageContext(player, Hand.MAIN_HAND,
                    new BlockHitResult(Vec3d.ofCenter(absolutePos), Direction.UP, absolutePos, false)));
            test.assertTrue(test.getBlockState(pos).isOf(pairs[i][1]), Registries.BLOCK.getId(pairs[i][0]) + " did not strip");
        }
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void compostablesMatchVanillaCategories(TestContext test) {
        assertCompost(test, CinderscapesItems.BRAMBLE_BERRIES, Items.SWEET_BERRIES);
        for (Item item : new Item[]{CinderscapesItems.SCORCHED_SHRUB, CinderscapesItems.SCORCHED_SPROUTS,
                CinderscapesItems.SCORCHED_TENDRILS, CinderscapesItems.TWILIGHT_FESCUES,
                CinderscapesItems.TWILIGHT_TENDRILS}) assertCompost(test, item, Items.GRASS);
        for (Item item : new Item[]{CinderscapesItems.CRYSTINIUM, CinderscapesItems.GHASTLY_ECTOPLASM,
                CinderscapesItems.LUMINOUS_POD, CinderscapesItems.PHOTOFERN, CinderscapesItems.PYRACINTH,
                CinderscapesItems.TALL_PHOTOFERN}) assertCompost(test, item, Items.POPPY);
        assertCompost(test, CinderscapesItems.UMBRAL_FUNGUS, Items.CRIMSON_FUNGUS);
        for (Item item : new Item[]{CinderscapesItems.TWILIGHT_VINE_BLOCK, CinderscapesItems.UMBRAL_FLESH_BLOCK,
                CinderscapesItems.UMBRAL_WART_BLOCK}) assertCompost(test, item, Items.NETHER_WART_BLOCK);
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void masonTradesAreActuallyInjected(TestContext test) {
        Int2ObjectOpenHashMap<List<TradeOffers.Factory>> trades = new Int2ObjectOpenHashMap<>();
        for (int level = 1; level <= 5; level++) trades.put(level, new ArrayList<>());
        MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(trades, VillagerProfession.MASON));
        test.assertTrue(trades.get(4).size() == 3, "Expected three level-4 mason trades, got " + trades.get(4).size());
        test.assertTrue(trades.get(5).size() == 6, "Expected six level-5 mason trades, got " + trades.get(5).size());
        Set<Item> expected = Set.of(CinderscapesItems.ROSE_QUARTZ, CinderscapesItems.SMOKY_QUARTZ,
                CinderscapesItems.SULFUR_QUARTZ);
        Set<Item> inputs = new java.util.HashSet<>();
        trades.get(4).forEach(factory -> inputs.add(factory.create(null, test.getWorld().getRandom()).getOriginalFirstBuyItem().getItem()));
        test.assertTrue(inputs.equals(expected), "Mason level-4 trade inputs are wrong: " + inputs);
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void piglinBarteringCanProduceAllQuartzVariants(TestContext test) {
        LootTable table = test.getWorld().getServer().getLootManager()
                .getLootTable(new Identifier("minecraft", "gameplay/piglin_bartering"));
        Set<Item> found = new java.util.HashSet<>();
        PiglinEntity piglin = test.spawnEntity(EntityType.PIGLIN, new BlockPos(1, 2, 1));
        for (int i = 0; i < 2000 && found.size() < 3; i++) {
            table.generateLoot(new LootContextParameterSet.Builder(test.getWorld())
                            .add(LootContextParameters.THIS_ENTITY, piglin).build(LootContextTypes.BARTER), i)
                    .stream().map(ItemStack::getItem)
                    .filter(item -> item == CinderscapesItems.ROSE_QUARTZ || item == CinderscapesItems.SMOKY_QUARTZ
                            || item == CinderscapesItems.SULFUR_QUARTZ).forEach(found::add);
        }
        test.assertTrue(found.size() == 3, "Piglin bartering did not expose all quartz variants: " + found);
        test.complete();
    }

    @GameTest(templateName = TEMPLATE)
    public static void lightAndFirePropertiesMatchMechanics(TestContext test) {
        test.assertTrue(CinderscapesBlocks.UMBRAL_FLESH_BLOCK.getDefaultState().getLuminance() == 15,
                "Umbral flesh is not full-bright");
        test.assertTrue(CinderscapesBlocks.LUMINOUS_POD.getDefaultState().getLuminance() == 15,
                "Luminous pod is not full-bright");
        test.assertTrue(CinderscapesBlocks.TWILIGHT_TENDRILS.getDefaultState().getLuminance() == 15,
                "Twilight tendrils are not full-bright");
        test.assertTrue(CinderscapesBlocks.PYRACINTH.getDefaultState().canPlaceAt(test.getWorld(),
                test.getAbsolutePos(new BlockPos(1, 2, 1))) == false, "Pyracinth survives without valid soil");
        test.complete();
    }

    private static void assertRegisteredFields(TestContext test, Class<?> owner, Class<?> type, Registry<?> registry)
            throws IllegalAccessException {
        for (Field field : owner.getFields()) {
            if (!Modifier.isStatic(field.getModifiers()) || !type.isAssignableFrom(field.getType())) continue;
            Object value = type.cast(field.get(null));
            test.assertTrue(value != null, owner.getSimpleName() + "." + field.getName() + " is null");
            test.assertTrue("cinderscapes".equals(((Registry) registry).getId(value).getNamespace()),
                    owner.getSimpleName() + "." + field.getName() + " is not registered");
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void assertDynamicKeys(TestContext test, Class<?> owner, RegistryKey<? extends Registry<?>> registryKey)
            throws IllegalAccessException {
        Registry registry = test.getWorld().getRegistryManager().get((RegistryKey) registryKey);
        for (Field field : owner.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers()) || !RegistryKey.class.isAssignableFrom(field.getType())) continue;
            field.setAccessible(true);
            RegistryKey key = (RegistryKey) field.get(null);
            if (key != null && key.getRegistry().equals(registryKey.getValue())) {
                test.assertTrue(registry.containsId(key.getValue()), "Missing dynamic registry entry " + key.getValue());
            }
        }
    }

    private static void assertCompost(TestContext test, Item actual, Item vanillaReference) {
        float actualChance = ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.getFloat(actual);
        float expectedChance = ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.getFloat(vanillaReference);
        test.assertTrue(actualChance > 0 && actualChance == expectedChance,
                Registries.ITEM.getId(actual) + " compost chance " + actualChance + " != " + expectedChance);
    }
}
