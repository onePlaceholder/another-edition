package org.theplaceholder.anotheredition.villager;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import org.theplaceholder.anotheredition.item.AnotherEditionItems;

import java.util.Optional;

public class EncyclopediaUniversalisTrade implements TradeOffers.Factory {
    @Override
    public TradeOffer create(Entity entity, Random random) {
        ItemStack itemstack = AnotherEditionItems.ENCYCLOPEDIA_UNIVERSALIS.get().getDefaultStack();
        return new TradeOffer(new TradedItem(Items.EMERALD, 5), Optional.of(new TradedItem(Items.BOOK)), itemstack, 12, 15, 0.2F);
    }
}
