package fr.lnzl.dkey.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import static fr.lnzl.dkey.DismountKeybinding.MOD_ID;

public class Network {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int i = 0;
        CHANNEL.registerMessage(i++, DismountMessage.class, DismountMessage::encode, DismountMessage::decode, DismountMessage.Handler::handle);
    }
}
