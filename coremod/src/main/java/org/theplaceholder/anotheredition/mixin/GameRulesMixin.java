package org.theplaceholder.anotheredition.mixin;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GameRules.class)
public class GameRulesMixin {
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;register(Ljava/lang/String;Lnet/minecraft/world/GameRules$Category;Lnet/minecraft/world/GameRules$Type;)Lnet/minecraft/world/GameRules$Key;"))
    private static void register(Args args) {
        if (args.get(0).equals("announceAdvancements")) {
            args.set(2, GameRules.BooleanRule.create(false));
        }
    }
}
