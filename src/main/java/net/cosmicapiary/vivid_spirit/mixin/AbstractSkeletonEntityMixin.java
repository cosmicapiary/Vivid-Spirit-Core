package net.cosmicapiary.vivid_spirit.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin {

    @Redirect(method = "initEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/AbstractSkeletonEntity;equipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V"))
    public void modifyInitialEquipment(AbstractSkeletonEntity instance, EquipmentSlot slot, ItemStack stack, @Local(ordinal = 0, argsOnly = true) Random random) {
        if (random.nextFloat() < (0.9f)) {
            int i = random.nextInt(3);
            if (i == 0) {
                instance.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
            } else {
                instance.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
            }
        }
    }
}
