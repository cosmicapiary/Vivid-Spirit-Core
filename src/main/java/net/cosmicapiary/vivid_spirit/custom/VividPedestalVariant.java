package net.cosmicapiary.vivid_spirit.custom;

import de.dafuqs.spectrum.api.block.PedestalVariant;
import de.dafuqs.spectrum.recipe.pedestal.PedestalRecipeTier;
import net.cosmicapiary.vivid_spirit.VividSpirit;
import net.minecraft.block.Block;

public enum VividPedestalVariant implements PedestalVariant {
    PRE_GEM(PedestalRecipeTier.BASIC);

    private final PedestalRecipeTier tier;

    VividPedestalVariant(PedestalRecipeTier tier) {
        this.tier = tier;
    }

    @Override
    public PedestalRecipeTier getRecipeTier() {
        return this.tier;
    }

    @Override
    public Block getPedestalBlock() {
        return VividSpirit.PIGMENT_PEDESTAL;
    }
}
