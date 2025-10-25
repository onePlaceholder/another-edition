package org.theplaceholder.anotheredition;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.theplaceholder.anotheredition.block.AnotherEditionBlocks;
import org.theplaceholder.anotheredition.config.AnotherEditionConfig;
import org.theplaceholder.anotheredition.event.EventHandler;
import org.theplaceholder.anotheredition.item.AnotherEditionItems;

@Mod(AnotherEdition.MOD_ID)
public final class AnotherEdition {
    public static final String MOD_ID = "another_edition";

    private static AnotherEditionConfig CONFIG;

    public AnotherEdition(IEventBus modEventBus) {
        CONFIG = AnotherEditionConfig.register();

        AnotherEditionItems.register(modEventBus);
        AnotherEditionBlocks.register(modEventBus);

        NeoForge.EVENT_BUS.register(EventHandler.class);
    }

    public static AnotherEditionConfig getConfig() {
        return CONFIG;
    }
}
