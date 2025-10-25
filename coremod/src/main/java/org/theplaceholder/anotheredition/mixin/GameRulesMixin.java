package org.theplaceholder.anotheredition.mixin;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.theplaceholder.anotheredition.AnotherEdition;

@Mixin(GameRules.class)
public class GameRulesMixin {
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;register(Ljava/lang/String;Lnet/minecraft/world/GameRules$Category;Lnet/minecraft/world/GameRules$Type;)Lnet/minecraft/world/GameRules$Key;"))
    private static void register(Args args) {
        args.set(2, AnotherEdition.getConfig().getDefaultGameRule(args.get(0), args.get(2)));
    }
}
