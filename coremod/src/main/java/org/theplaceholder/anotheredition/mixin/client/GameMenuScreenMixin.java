package org.theplaceholder.anotheredition.mixin.client;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 0))
    private <T extends Widget> T removeAdvancementsButton(GridWidget.Adder instance, T widget) {
        return widget;
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 1))
    private <T extends Widget> T removeStatisticsButton(GridWidget.Adder instance, T widget) {
        return widget;
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 2))
    private <T extends Widget> T removeFeedbackButton(GridWidget.Adder instance, T widget) {
        return widget;
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 3))
    private <T extends Widget> T removeBugReportButton(GridWidget.Adder instance, T widget) {
        return widget;
    }

    @Inject(method = "addFeedbackAndBugsButtons", at = @At("HEAD"), cancellable = true)
    private static void removeReportAndFeedbacksButton(Screen parentScreen, GridWidget.Adder gridAdder, CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 6))
    private <T extends Widget> T removePlayerReportButton(GridWidget.Adder instance, T widget) {
        if (this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote()) {
            instance.add(widget);
        }
        return widget;
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 4))
    private <T extends Widget> T scaleConfigButton(GridWidget.Adder instance, T widget){
        if ((!this.client.isIntegratedServerRunning() || this.client.getServer().isRemote()) && widget instanceof ButtonWidget button) {
            button.setWidth(204);
            return instance.add(widget, 2);
        }
        return instance.add(widget);
    }
}
