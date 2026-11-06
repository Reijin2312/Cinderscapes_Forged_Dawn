package com.terraformersmc.cinderscapes.gametest;

import com.terraformersmc.cinderscapes.block.*;
import com.terraformersmc.cinderscapes.init.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@GameTestHolder("cinderscapes")
@PrefixGameTestTemplate(false)
public final class CinderscapesGameTests {
    private static final String TEMPLATE = "empty";
    private CinderscapesGameTests() { }

    @GameTest(template = TEMPLATE)
    public static void allRegistrationsAndWorldgenKeysArePresent(GameTestHelper helper) throws Exception {
        assertRegisteredFields(helper, CinderscapesBlocks.class, Block.class, BuiltInRegistries.BLOCK);
        assertRegisteredFields(helper, CinderscapesItems.class, Item.class, BuiltInRegistries.ITEM);
        assertRegisteredFields(helper, CinderscapesFeatures.class, net.minecraft.world.level.levelgen.feature.Feature.class, BuiltInRegistries.FEATURE);
        assertRegisteredFields(helper, CinderscapesPlacementModifierTypes.class,
                net.minecraft.world.level.levelgen.placement.PlacementModifierType.class, BuiltInRegistries.PLACEMENT_MODIFIER_TYPE);
        assertRegisteredFields(helper, CinderscapesSoundEvents.class, net.minecraft.sounds.SoundEvent.class, BuiltInRegistries.SOUND_EVENT);
        assertDynamicKeys(helper, CinderscapesBiomes.class, Registries.BIOME);
        assertDynamicKeys(helper, CinderscapesConfiguredFeatures.class, Registries.CONFIGURED_FEATURE);
        assertDynamicKeys(helper, CinderscapesPlacedFeatures.class, Registries.PLACED_FEATURE);
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void everyBlockHasItemAndLoot(GameTestHelper helper) {
        for (Block block : BuiltInRegistries.BLOCK) {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
            if (!id.getNamespace().equals("cinderscapes") || id.getPath().startsWith("potted_") || id.getPath().contains("wall_sign")) continue;
            helper.assertTrue(block.asItem() != Items.AIR, "No item for " + id);
            helper.assertTrue(helper.getLevel().getServer().reloadableRegistries().getLootTable(block.getLootTable()) != LootTable.EMPTY,
                    "No loot table for " + id);
        }
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void recipesTagsAndFoodAreLoaded(GameTestHelper helper) {
        long recipes = helper.getLevel().getRecipeManager().getRecipes().stream()
                .filter(r -> r.id().getNamespace().equals("cinderscapes")).count();
        helper.assertTrue(recipes >= 60, "Only " + recipes + " recipes loaded");
        helper.assertTrue(CinderscapesItems.BRAMBLE_BERRIES.getDefaultInstance().getFoodProperties(null) != null, "Berries are not food");
        helper.assertTrue(CinderscapesItems.SCORCHED_STEM.getDefaultInstance().is(ItemTags.LOGS), "Scorched stem absent from logs");
        helper.assertTrue(CinderscapesItems.UMBRAL_STEM.getDefaultInstance().is(ItemTags.LOGS), "Umbral stem absent from logs");
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void allPlantSoilsAndNodzolWork(GameTestHelper helper) {
        BlockPos pos = new BlockPos(1, 2, 1);
        for (Block soil : new Block[]{Blocks.NETHERRACK, Blocks.SOUL_SOIL, CinderscapesBlocks.UMBRAL_NYLIUM}) {
            helper.setBlock(pos.below(), soil);
            for (Block plant : new Block[]{CinderscapesBlocks.PYRACINTH, CinderscapesBlocks.CRYSTINIUM,
                    CinderscapesBlocks.PHOTOFERN, CinderscapesBlocks.TWILIGHT_TENDRILS})
                helper.assertTrue(plant.defaultBlockState().canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                        plant + " cannot survive on " + soil);
        }
        for (Block soil : new Block[]{Blocks.NETHERRACK, Blocks.SOUL_SOIL, Blocks.SOUL_SAND, Blocks.GRAVEL}) {
            helper.setBlock(pos.below(), soil);
            helper.assertTrue(CinderscapesBlocks.BRAMBLE_BERRY_BUSH.defaultBlockState().canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                    "Bramble cannot survive on " + soil);
        }
        helper.setBlock(pos.below(), CinderscapesBlocks.NODZOL);
        helper.assertTrue(Blocks.DANDELION.defaultBlockState().canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                "Vanilla plant cannot survive on nodzol");
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void photofernBonemealCreatesBothTallHalves(GameTestHelper helper) {
        BlockPos pos = new BlockPos(1, 2, 1);
        helper.setBlock(pos.below(), Blocks.NETHERRACK);
        helper.setBlock(pos.above(), Blocks.AIR);
        helper.setBlock(pos, CinderscapesBlocks.PHOTOFERN);
        helper.assertTrue(CinderscapesBlocks.TALL_PHOTOFERN.defaultBlockState()
                        .canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                "Tall photofern precondition failed; floor=" + helper.getBlockState(pos.below()));
        helper.assertTrue(helper.getBlockState(pos.above()).isAir(), "Tall photofern upper position is occupied");
        BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), helper.getLevel(), helper.absolutePos(pos));
        helper.assertTrue(helper.getBlockState(pos).is(CinderscapesBlocks.TALL_PHOTOFERN)
                && helper.getBlockState(pos).getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER,
                "Missing lower tall photofern");
        helper.assertTrue(helper.getBlockState(pos.above()).is(CinderscapesBlocks.TALL_PHOTOFERN)
                && helper.getBlockState(pos.above()).getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER,
                "Missing upper tall photofern");
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void brambleHarvestDamageAndImmunitiesWork(GameTestHelper helper) {
        BlockPos pos = new BlockPos(2, 2, 2);
        BlockState bush = CinderscapesBlocks.BRAMBLE_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3);
        helper.setBlock(pos, bush);
        var player = helper.makeMockPlayer(GameType.SURVIVAL);
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH).useWithoutItem(bush, helper.getLevel(),
                helper.absolutePos(pos), player, hit(helper.absolutePos(pos)));
        helper.assertTrue(helper.getBlockState(pos).getValue(SweetBerryBushBlock.AGE) == 1, "Harvest did not reset age");
        helper.assertItemEntityPresent(CinderscapesItems.BRAMBLE_BERRIES, pos, 2.0);
        bush = bush.setValue(SweetBerryBushBlock.AGE, 2);
        helper.setBlock(pos, bush);
        player.setPos(player.getX() + 0.25, player.getY(), player.getZ());
        player.xOld = player.getX() - 0.25;
        player.invulnerableTime = 0;
        float before = player.getHealth();
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH).entityInside(bush, helper.getLevel(), helper.absolutePos(pos), player);
        helper.assertTrue(player.getHealth() == before - 1, "Bramble did not damage moving player; health=" + player.getHealth());
        Hoglin hoglin = helper.spawn(EntityType.HOGLIN, pos);
        hoglin.setPos(hoglin.getX() + 0.25, hoglin.getY(), hoglin.getZ());
        hoglin.xOld = hoglin.getX() - 0.25;
        hoglin.invulnerableTime = 0;
        before = hoglin.getHealth();
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH).entityInside(bush, helper.getLevel(), helper.absolutePos(pos), hoglin);
        helper.assertTrue(hoglin.getHealth() == before, "Bramble damaged immune hoglin");
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void ashNeverMeltsAndDropsEveryLayer(GameTestHelper helper) {
        BlockPos pos = new BlockPos(2, 2, 2);
        var player = helper.makeMockPlayer(GameType.SURVIVAL);
        for (int layers = 1; layers <= 8; layers++) {
            BlockState state = CinderscapesBlocks.ASH.defaultBlockState().setValue(SnowLayerBlock.LAYERS, layers);
            helper.setBlock(pos, state);
            state.randomTick(helper.getLevel(), helper.absolutePos(pos), helper.getLevel().getRandom());
            helper.assertTrue(helper.getBlockState(pos).equals(state), "Ash melted at " + layers + " layers");
            int count = Block.getDrops(state, helper.getLevel(), helper.absolutePos(pos), null, player,
                    new ItemStack(Items.IRON_SHOVEL)).stream().filter(s -> s.is(CinderscapesItems.ASH_PILE)).mapToInt(ItemStack::getCount).sum();
            helper.assertTrue(count == layers, "Ash dropped " + count + " instead of " + layers);
        }
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void axesStripEveryStemAndHypha(GameTestHelper helper) {
        Block[][] pairs = {{CinderscapesBlocks.SCORCHED_STEM,CinderscapesBlocks.STRIPPED_SCORCHED_STEM},
                {CinderscapesBlocks.SCORCHED_HYPHAE,CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE},
                {CinderscapesBlocks.UMBRAL_STEM,CinderscapesBlocks.STRIPPED_UMBRAL_STEM},
                {CinderscapesBlocks.UMBRAL_HYPHAE,CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE}};
        var player = helper.makeMockPlayer(GameType.CREATIVE);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.DIAMOND_AXE));
        for (int i=0;i<pairs.length;i++) {
            BlockPos pos = new BlockPos(1+i,2,1); helper.setBlock(pos,pairs[i][0]);
            BlockPos abs=helper.absolutePos(pos);
            player.getMainHandItem().useOn(new UseOnContext(player, InteractionHand.MAIN_HAND, hit(abs)));
            helper.assertTrue(helper.getBlockState(pos).is(pairs[i][1]), pairs[i][0]+" did not strip");
        }
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void ectoplasmAndPolypiteSupportRulesWork(GameTestHelper helper) {
        GhastlyEctoplasmBlock ecto=(GhastlyEctoplasmBlock)CinderscapesBlocks.GHASTLY_ECTOPLASM;
        BlockPos p=new BlockPos(3,5,3); helper.setBlock(p.above(),Blocks.NETHERRACK); helper.setBlock(p,ecto);
        helper.setBlock(p.below(),ecto); helper.setBlock(p.below(2),ecto);
        helper.assertTrue(ecto.evaluateState(ecto.defaultBlockState(),helper.absolutePos(p),helper.getLevel()).getValue(GhastlyEctoplasmBlock.TYPE)== GhastlyEctoplasmBlock.Type.TOP,"Wrong ectoplasm top");
        helper.assertTrue(ecto.evaluateState(ecto.defaultBlockState(),helper.absolutePos(p.below()),helper.getLevel()).getValue(GhastlyEctoplasmBlock.TYPE)== GhastlyEctoplasmBlock.Type.MIDDLE,"Wrong ectoplasm middle");
        helper.assertTrue(ecto.evaluateState(ecto.defaultBlockState(),helper.absolutePos(p.below(2)),helper.getLevel()).getValue(GhastlyEctoplasmBlock.TYPE)== GhastlyEctoplasmBlock.Type.BOTTOM,"Wrong ectoplasm bottom");
        for (PolypiteQuartzBlock b : new PolypiteQuartzBlock[]{(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_QUARTZ,(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_ROSE_QUARTZ,(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_SMOKY_QUARTZ,(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_SULFUR_QUARTZ})
            for(Direction d:Direction.values()) { BlockState s=b.stateOf(d); helper.assertTrue(!s.getShape(helper.getLevel(),helper.absolutePos(p)).isEmpty(),"Missing shape "+d); }
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void netherrackBonemealCopiesUmbralNylium(GameTestHelper helper) {
        BlockPos pos=new BlockPos(2,2,2); helper.setBlock(pos,Blocks.NETHERRACK); helper.setBlock(pos.east(),CinderscapesBlocks.UMBRAL_NYLIUM);
        ((BonemealableBlock)Blocks.NETHERRACK).performBonemeal(helper.getLevel(),helper.getLevel().getRandom(),helper.absolutePos(pos),Blocks.NETHERRACK.defaultBlockState());
        helper.assertBlockPresent(CinderscapesBlocks.UMBRAL_NYLIUM,pos); helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void everyCompostableHasItsExactChance(GameTestHelper helper) {
        assertCompost(helper,CinderscapesItems.BRAMBLE_BERRIES,0.3F);
        for(Item i:new Item[]{CinderscapesItems.SCORCHED_SHRUB,CinderscapesItems.SCORCHED_SPROUTS,CinderscapesItems.SCORCHED_TENDRILS,CinderscapesItems.TWILIGHT_FESCUES,CinderscapesItems.TWILIGHT_TENDRILS}) assertCompost(helper,i,0.3F);
        for(Item i:new Item[]{CinderscapesItems.CRYSTINIUM,CinderscapesItems.GHASTLY_ECTOPLASM,CinderscapesItems.LUMINOUS_POD,CinderscapesItems.PHOTOFERN,CinderscapesItems.PYRACINTH,CinderscapesItems.TALL_PHOTOFERN,CinderscapesItems.UMBRAL_FUNGUS}) assertCompost(helper,i,0.65F);
        for(Item i:new Item[]{CinderscapesItems.TWILIGHT_VINE_BLOCK,CinderscapesItems.UMBRAL_FLESH_BLOCK,CinderscapesItems.UMBRAL_WART_BLOCK}) assertCompost(helper,i,0.85F);
        helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void masonTradesAndPiglinBarterExposeAllQuartz(GameTestHelper helper) {
        Int2ObjectOpenHashMap<List<net.minecraft.world.entity.npc.VillagerTrades.ItemListing>> trades=new Int2ObjectOpenHashMap<>();
        for(int i=1;i<=5;i++) trades.put(i,new ArrayList<>());
        NeoForge.EVENT_BUS.post(new VillagerTradesEvent(trades,VillagerProfession.MASON));
        helper.assertTrue(trades.get(4).size()==3 && trades.get(5).size()==6,"Mason trade counts wrong");
        LootTable table=helper.getLevel().getServer().reloadableRegistries().getLootTable(BuiltInLootTables.PIGLIN_BARTERING);
        Piglin piglin=helper.spawn(EntityType.PIGLIN,new BlockPos(2,2,2)); Set<Item> found=new HashSet<>();
        LootParams params=new LootParams.Builder(helper.getLevel()).withParameter(LootContextParams.THIS_ENTITY,piglin).create(LootContextParamSets.PIGLIN_BARTER);
        for(int i=0;i<2000&&found.size()<3;i++) table.getRandomItems(params).stream().map(ItemStack::getItem)
                .filter(x->x==CinderscapesItems.ROSE_QUARTZ||x==CinderscapesItems.SMOKY_QUARTZ||x==CinderscapesItems.SULFUR_QUARTZ).forEach(found::add);
        helper.assertTrue(found.size()==3,"Missing quartz barter variants: "+found); helper.succeed();
    }

    @GameTest(template = TEMPLATE)
    public static void lightAndTransparentBlockPropertiesAreCorrect(GameTestHelper helper) {
        helper.assertTrue(CinderscapesBlocks.UMBRAL_FLESH_BLOCK.defaultBlockState().getLightEmission()==15,"Flesh light wrong");
        helper.assertTrue(CinderscapesBlocks.LUMINOUS_POD.defaultBlockState().getLightEmission()==15,"Pod light wrong");
        helper.assertTrue(CinderscapesBlocks.TWILIGHT_TENDRILS.defaultBlockState().getLightEmission()==15,"Tendril light wrong");
        helper.assertTrue(!CinderscapesBlocks.CRYSTALLINE_QUARTZ.defaultBlockState().isSuffocating(helper.getLevel(),helper.absolutePos(new BlockPos(1,2,1))),"Crystal suffocates");
        helper.succeed();
    }

    private static BlockHitResult hit(BlockPos p){return new BlockHitResult(Vec3.atCenterOf(p),Direction.UP,p,false);}
    private static void assertCompost(GameTestHelper h,Item item,float chance){var data=item.builtInRegistryHolder().getData(NeoForgeDataMaps.COMPOSTABLES);h.assertTrue(data!=null&&data.chance()==chance,item+" compost chance wrong: "+data);}
    @SuppressWarnings({"rawtypes","unchecked"}) private static void assertRegisteredFields(GameTestHelper h,Class<?> owner,Class<?> type,Registry<?> registry)throws Exception{for(Field f:owner.getFields()){if(!Modifier.isStatic(f.getModifiers())||!type.isAssignableFrom(f.getType()))continue;Object v=f.get(null);h.assertTrue(v!=null,owner.getSimpleName()+"."+f.getName()+" is null");h.assertTrue(((Registry)registry).getKey(v).getNamespace().equals("cinderscapes"),f.getName()+" not registered");}}
    @SuppressWarnings({"rawtypes","unchecked"}) private static void assertDynamicKeys(GameTestHelper h,Class<?> owner,ResourceKey<? extends Registry<?>> registryKey)throws Exception{Registry registry=h.getLevel().registryAccess().registryOrThrow((ResourceKey)registryKey);for(Field f:owner.getDeclaredFields()){if(!Modifier.isStatic(f.getModifiers())||!ResourceKey.class.isAssignableFrom(f.getType()))continue;f.setAccessible(true);ResourceKey key=(ResourceKey)f.get(null);if(key!=null&&key.registry().equals(registryKey.location()))h.assertTrue(registry.containsKey(key.location()),"Missing "+key.location());}}
}
