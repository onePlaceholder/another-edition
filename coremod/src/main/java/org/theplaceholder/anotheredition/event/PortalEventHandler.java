package org.theplaceholder.anotheredition.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.theplaceholder.anotheredition.block.AnotherEditionBlocks;

public class PortalEventHandler {
    @SubscribeEvent
    public static void onPortalCreate(BlockEvent.PortalSpawnEvent event) {
        boolean isIgnitedFire = event.getState().isOf(AnotherEditionBlocks.IGNITED_FIRE.get());
        event.setCanceled(!isIgnitedFire);
    }
}