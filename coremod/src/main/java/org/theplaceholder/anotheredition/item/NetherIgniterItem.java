package org.theplaceholder.anotheredition.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import org.theplaceholder.anotheredition.block.AnotherEditionBlocks;
import org.theplaceholder.anotheredition.mixin.accessor.FireBlockAccessor;

public class NetherIgniterItem extends FlintAndSteelItem {
    public NetherIgniterItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        BlockState originalState = world.getBlockState(pos);
        BlockState newState = originalState.getToolModifiedState(context, ItemAbilities.FIRESTARTER_LIGHT, false);

        ActionResult result = ActionResult.FAIL;

        if (newState == null) {
            BlockPos firePos = pos.offset(context.getSide());
            if (AbstractFireBlock.canPlaceAt(world, firePos, context.getHorizontalPlayerFacing())) {
                world.playSound(player, firePos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);

                newState = ((FireBlockAccessor)AnotherEditionBlocks.IGNITED_FIRE.get()).invokeGetStateForPosition(world, pos);

                world.setBlockState(firePos, newState, 11);
                world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
                ItemStack itemstack = context.getStack();

                if (player instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) player, firePos, itemstack);
                    itemstack.damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }

                result = ActionResult.success(world.isClient());
            }
        }

        return result;
    }
}