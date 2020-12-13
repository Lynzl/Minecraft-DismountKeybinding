package fr.lnzl.dkey;

import fr.lnzl.dkey.capability.CapabilityDismount;
import fr.lnzl.dkey.capability.DismountProvider;
import fr.lnzl.dkey.keybinding.Keybinding;
import fr.lnzl.dkey.network.DismountMessage;
import fr.lnzl.dkey.network.Network;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static fr.lnzl.dkey.DismountKeybinding.MOD_ID;

@Mod(MOD_ID)
public class DismountKeybinding {

    public static final String MOD_ID = "dkey";

    public DismountKeybinding() {
        // ✨
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventHandler {

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            CapabilityDismount.register();
            Network.register();
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEventHandler {

        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (!(event.getObject() instanceof PlayerEntity)) return;
            DismountProvider dismountProvider = new DismountProvider();
            event.addCapability(new ResourceLocation(MOD_ID, "dismount"), dismountProvider);
            event.addListener(dismountProvider::invalidate);
        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEventHandler {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Keybinding.register();
        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientForgeEventHandler {

        @SubscribeEvent
        public static void onInput(InputEvent.KeyInputEvent event) {
            if (Keybinding.keybind.isKeyDown() != Keybinding.wantsToDismount) {
                Keybinding.dismountBySneaking =
                        Minecraft.getInstance().gameSettings.keyBindSneak.getKey() == Keybinding.keybind.getKey();
                Network.CHANNEL.sendToServer(new DismountMessage(Keybinding.keybind.isKeyDown(), Keybinding.dismountBySneaking));
                Keybinding.wantsToDismount = Keybinding.keybind.isKeyDown();
            }
        }
    }
}