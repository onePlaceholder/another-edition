package org.theplaceholder.anotheredition.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.theplaceholder.anotheredition.AnotherEdition;
import org.theplaceholder.anotheredition.block.AnotherEditionBlocks;
import org.theplaceholder.anotheredition.villager.EncyclopediaUniversalisTrade;

import java.util.List;

@EventBusSubscriber(modid = AnotherEdition.MOD_ID)
public class AnotherEditionEventHandler {
    @SubscribeEvent
    public static void onPortalCreate(BlockEvent.PortalSpawnEvent event) {
        boolean isIgnitedFire = event.getState().isOf(AnotherEditionBlocks.IGNITED_FIRE.get());
        event.setCanceled(!isIgnitedFire);
    }

    @SubscribeEvent
    public static void onVillagerTrade(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<TradeOffers.Factory>> trades = event.getTrades();
            trades.get(3).add(new EncyclopediaUniversalisTrade());
        }
    }
}