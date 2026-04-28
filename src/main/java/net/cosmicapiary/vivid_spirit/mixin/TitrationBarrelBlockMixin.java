package net.cosmicapiary.vivid_spirit.mixin;

import de.dafuqs.spectrum.blocks.titration_barrel.TitrationBarrelBlock;
import de.dafuqs.spectrum.blocks.titration_barrel.TitrationBarrelBlockEntity;
import de.dafuqs.spectrum.registries.SpectrumSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitrationBarrelBlock.class)
abstract class TitrationBarrelBlockMixin {

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    public boolean VividSpirit$sealWithAnyPlank(ItemStack instance, TagKey<Item> tag) {
        return instance.isIn(ItemTags.PLANKS);
    }

    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lde/dafuqs/spectrum/blocks/titration_barrel/TitrationBarrelBlock$BarrelState;ordinal()I"))
    public void VividSpirit$incrementTimeWithQuicksilver(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (player.getMainHandStack().isOf(Items.FLINT)) {
            player.sendMessage(Text.translatable("block.spectrum.titration_barrel.debug_added_day"), true);
            //barrelEntity.addOneDayOfSealTime();
            world.playSound(null, pos, SpectrumSoundEvents.NEW_RECIPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }


}
