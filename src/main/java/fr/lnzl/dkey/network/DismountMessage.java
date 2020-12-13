package fr.lnzl.dkey.network;

import fr.lnzl.dkey.capability.CapabilityDismount;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DismountMessage {
    public boolean wantsToDismount;
    public boolean dismountBySneaking;

    public DismountMessage(boolean wantsToDismount, boolean dismountBySneaking) {
        this.wantsToDismount = wantsToDismount;
        this.dismountBySneaking = dismountBySneaking;
    }

    public static void encode(DismountMessage message, PacketBuffer packetBuffer) {
        packetBuffer.writeBoolean(message.wantsToDismount);
        packetBuffer.writeBoolean(message.dismountBySneaking);
    }

    public static DismountMessage decode(PacketBuffer packetBuffer) {
        boolean wantsDismount = packetBuffer.readBoolean();
        boolean dismountBySneaking = packetBuffer.readBoolean();

        return new DismountMessage(wantsDismount, dismountBySneaking);
    }

    public static class Handler {

        public static void handle(final DismountMessage dismountMessage, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                ServerPlayerEntity playerEntity = context.get().getSender();
                if (playerEntity == null) return;
                playerEntity.getCapability(CapabilityDismount.CAPABILITY_DISMOUNT).ifPresent(dismount -> {
                    boolean wantsDismount = dismountMessage.wantsToDismount;
                    boolean dismountBySneaking = dismountMessage.dismountBySneaking;
                    dismount.setWantsToDismount(wantsDismount);
                    dismount.setDismountBySneaking(dismountBySneaking);
                });
            });
            context.get().setPacketHandled(true);
        }
    }
}
