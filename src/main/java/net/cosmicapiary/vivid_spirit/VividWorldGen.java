package net.cosmicapiary.vivid_spirit;

import de.dafuqs.spectrum.registries.SpectrumBiomeTags;
import net.cosmicapiary.vivid_spirit.worldgen.VividPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.id.paradiselost.tag.ParadiseLostStructureTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class VividWorldGen {

    public static void addFeatures() {

        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.SWAMP),
                GenerationStep.Feature.LAKES, VividPlacedFeatures.LAKE_MUD);
        BiomeModifications.addFeature(BiomeSelectors.tag(SpectrumBiomeTags.DD_BIOMES),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.DD_ZINC_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(SpectrumBiomeTags.DD_BIOMES),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.DD_SILVER_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.LAKES, VividPlacedFeatures.PL_LUMISENE_LAKE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.VEGETAL_DECORATION, VividPlacedFeatures.PL_FLOWERS);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.PL_SHIMMERSTONE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.PL_ONYX);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.PL_REDSTONE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.PL_LAPIS);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.PL_SOULSTONE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.PL_BRILLIANT);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, VividPlacedFeatures.PL_SAPPHIC_ICE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, VividPlacedFeatures.PL_SAPPHIC_PACKED_ICE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IN_OVERWORLD),
                GenerationStep.Feature.UNDERGROUND_ORES, VividPlacedFeatures.ALLURITE_GEODE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DEEP_DARK),
                GenerationStep.Feature.VEGETAL_DECORATION, VividPlacedFeatures.ECHO_CLUSTER);

        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_STRUCTURES, VividPlacedFeatures.TOPAZ_GEODE);
    }
}

