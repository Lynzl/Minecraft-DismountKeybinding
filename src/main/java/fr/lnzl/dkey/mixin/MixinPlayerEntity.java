package fr.lnzl.dkey.mixin;

import fr.lnzl.dkey.capability.CapabilityDismount;
import fr.lnzl.dkey.capability.DismountDefaultProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends Entity {

    public MixinPlayerEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Inject(
            method = "wantsToStopRiding",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onWantsToStopRiding(CallbackInfoReturnable<Boolean> cir) {
        boolean wantsToDismount = this.getCapability(CapabilityDismount.CAPABILITY_DISMOUNT)
                .orElseGet(DismountDefaultProvider::new)
                .getWantsToDismount();
        cir.setReturnValue(wantsToDismount);
    }

    @Redirect(
            method = "updateRidden",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setSneaking(Z)V")
    )
    public void setSneaking(PlayerEntity playerEntity, boolean keyDownIn) {
        boolean dismountBySneaking = this.getCapability(CapabilityDismount.CAPABILITY_DISMOUNT)
                .orElseGet(DismountDefaultProvider::new)
                .getDismountBySneaking();
        if (dismountBySneaking) playerEntity.setSneaking(keyDownIn);
    }
}
