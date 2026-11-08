package com.terraformersmc.cinderscapes.init.helpers;

import com.terraformersmc.cinderscapes.Cinderscapes;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.RegisterEvent;

public final class CinderscapesRegistry {
    private static RegisterEvent currentEvent;

    private CinderscapesRegistry() {}

    public static void begin(RegisterEvent event) { currentEvent = event; }
    public static void end() { currentEvent = null; }

    public static <R, T extends R> T register(ResourceKey<? extends Registry<R>> registry, Identifier id, T value) {
        if (currentEvent == null) throw new IllegalStateException("Registration attempted outside NeoForge RegisterEvent");
        currentEvent.register(registry, helper -> helper.register(id, value));
        return value;
    }

    public static <S extends SignBlock> S registerSignBlock(String name, Function<BlockBehaviour.Properties, S> factory, BlockBehaviour.Properties properties) {
        return register(name, factory, properties);
    }

    public static <B extends Block> B register(String name, Function<BlockBehaviour.Properties, B> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Cinderscapes.id(name));
        B block = factory.apply(properties.setId(key));
        return register(Registries.BLOCK, key.identifier(), block);
    }

    public static BlockItem registerBlockItem(String name, Block block) {
        return register(name, properties -> new BlockItem(block, properties), new Item.Properties().useBlockDescriptionPrefix());
    }

    public static BlockItem registerBlockItem(String name, Block block, Item.Properties properties) {
        return register(name, configured -> new BlockItem(block, configured), properties.useBlockDescriptionPrefix());
    }

    public static <I extends Item> I register(String name, Function<Item.Properties, I> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Cinderscapes.id(name));
        I item = factory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) blockItem.registerBlocks(Item.BY_BLOCK, blockItem);
        return register(Registries.ITEM, key.identifier(), item);
    }

}
