package net.cosmicapiary.vivid_spirit.worldgen;

import net.cosmicapiary.vivid_spirit.VividSpirit;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class VividPlacedFeatures {

    //PARADISE - ORES
    public static final RegistryKey<PlacedFeature> DD_ZINC_ORE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "dd_zinc_ore"));
    public static final RegistryKey<PlacedFeature> DD_SILVER_ORE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "dd_silver_ore"));
    public static final RegistryKey<PlacedFeature> PL_SHIMMERSTONE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_shimmerstone"));
    public static final RegistryKey<PlacedFeature> PL_ONYX =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_onyx"));
    public static final RegistryKey<PlacedFeature> PL_REDSTONE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_redstone"));
    public static final RegistryKey<PlacedFeature> PL_LAPIS =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_lapis"));
    public static final RegistryKey<PlacedFeature> PL_BRILLIANT =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_brilliant"));
    public static final RegistryKey<PlacedFeature> PL_SOULSTONE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_soulstone"));
    public static final RegistryKey<PlacedFeature> PL_SAPPHIC_ICE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "sapphic_ice"));
    public static final RegistryKey<PlacedFeature> PL_SAPPHIC_PACKED_ICE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "sapphic_packed_ice"));

    public static final RegistryKey<PlacedFeature> TOPAZ_GEODE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "topaz_geode"));

    //PARADISE - MISC
    public static final RegistryKey<PlacedFeature> PL_FLOWERS =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_flowers"));
    public static final RegistryKey<PlacedFeature> PL_LUMISENE_LAKE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "lumisene_lake"));

    //OVERWORLD
    public static final RegistryKey<PlacedFeature> LAKE_MUD =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "lake_mud_surface"));
    public static final RegistryKey<PlacedFeature> ALLURITE_GEODE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "allurite_geode"));
    public static final RegistryKey<PlacedFeature> ECHO_CLUSTER =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "allurite_geode"));

    //MISC
    public static final RegistryKey<PlacedFeature> MANA_TREE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "mana_tree"));

}
