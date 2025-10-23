package org.theplaceholder.anotheredition.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.registry.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.function.Supplier;

public class AnotherEditionBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, AnotherEdition.MOD_ID);

    public static final Supplier<FireBlock> IGNITED_FIRE = register(() -> new FireBlock(AbstractBlock.Settings.copy(Blocks.FIRE)), "ignited_fire");

    private static <T extends Block> Supplier<T> register(Supplier<T> block, String id) {
        return BLOCKS.register(id, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
