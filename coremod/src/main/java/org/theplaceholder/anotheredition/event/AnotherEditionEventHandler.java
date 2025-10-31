package org.theplaceholder.anotheredition.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.theplaceholder.anotheredition.AnotherEdition;
import org.theplaceholder.anotheredition.block.AnotherEditionBlocks;
import org.theplaceholder.anotheredition.data.AnotherEditionAttachments;
import org.theplaceholder.anotheredition.villager.EncyclopediaUniversalisTrade;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;

@EventBusSubscriber(modid = AnotherEdition.MOD_ID)
public class AnotherEditionEventHandler {
    @SubscribeEvent
    public static void onPortalCreate(BlockEvent.PortalSpawnEvent event) {
        boolean isIgnitedFire = event.getState().isOf(AnotherEditionBlocks.IGNITED_FIRE.get());
        boolean isNether = ((World)event.getLevel()).getDimensionEntry().matchesId(DimensionTypes.THE_NETHER_ID);
        event.setCanceled(!(isIgnitedFire || isNether));
    }

    @SubscribeEvent
    public static void onVillagerTrade(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<TradeOffers.Factory>> trades = event.getTrades();
            trades.get(3).add(new EncyclopediaUniversalisTrade());
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getEntity();

        boolean hasStartingInv = false;
        if (player.hasData(AnotherEditionAttachments.STARTING_INV.get())){
            hasStartingInv = player.getData(AnotherEditionAttachments.STARTING_INV.get());
        }

        if (!hasStartingInv) {
            player.setData(AnotherEditionAttachments.STARTING_INV.get(), true);
            ItemStack patchouliBook = PatchouliAPI.get().getBookStack(Identifier.of(AnotherEdition.MOD_ID, "everything_guide"));
            player.giveItemStack(patchouliBook);
        }
    }
}