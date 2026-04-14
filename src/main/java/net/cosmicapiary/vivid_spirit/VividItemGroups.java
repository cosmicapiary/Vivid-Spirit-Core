package net.cosmicapiary.vivid_spirit;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

public class VividItemGroups {
    public static final ItemGroup VIVID_SPIRIT = Registry.register(Registries.ITEM_GROUP,
    new Identifier(VividSpirit.MOD_ID, "vivid_spirit"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.vividspirit"))
            .icon(() -> new ItemStack(VividSpirit.BLACK_ICE)).entries((displayContext, entries) -> {
        entries.add(VividSpirit.BLACK_ICE);
        entries.add(VividSpirit.BLACKSLAG_SILVER_ORE);
        entries.add(VividSpirit.BLACKSLAG_ZINC_ORE);
        entries.add(VividSpirit.PURE_RUBY);
        entries.add(VividSpirit.PURE_RUBY_BLOCK);
        entries.add(VividSpirit.PURE_SILVER);
        entries.add(VividSpirit.PURE_SILVER_BLOCK);
        entries.add(VividSpirit.SPIRIT_BUCKET);
        entries.add(VividSpirit.RAW_SILVER_NUGGET);
        entries.add(VividSpirit.SMALL_RUBY_BUD);
        entries.add(VividSpirit.SMALL_SILVER_BUD);
        entries.add(VividSpirit.LARGE_RUBY_BUD);
        entries.add(VividSpirit.LARGE_SILVER_BUD);
        entries.add(VividSpirit.RUBY_CLUSTER);
        entries.add(VividSpirit.SILVER_CLUSTER);
        entries.add(VividSpirit.BUDDING_ALLURITE_BLOCK);
        entries.add(VividSpirit.SMALL_ALLURITE_BUD);
        entries.add(VividSpirit.MEDIUM_ALLURITE_BUD);
        entries.add(VividSpirit.LARGE_ALLURITE_BUD);
        entries.add(VividSpirit.ALLURITE_CLUSTER);
        entries.add(VividSpirit.BUDDING_LUMIERE_BLOCK);
        entries.add(VividSpirit.SMALL_LUMIERE_BUD);
        entries.add(VividSpirit.MEDIUM_LUMIERE_BUD);
        entries.add(VividSpirit.LARGE_LUMIERE_BUD);
        entries.add(VividSpirit.LUMIERE_CLUSTER);
        entries.add(VividSpirit.ECHO_CLUSTER);
        entries.add(VividSpirit.STARRY_CLUMP);
        entries.add(VividSpirit.LIQUID_PEARLS);
        entries.add(VividSpirit.TOFU);
        entries.add(VividSpirit.SHIMMERSTONE_ORE);
        entries.add(VividSpirit.FLOESTONE_REDSTONE_ORE);
        entries.add(VividSpirit.SAPPHIRE_ICE);
        entries.add(VividSpirit.SAPPHIRE_TILES);
        entries.add(VividSpirit.SAPPHIRE_PACKED_ICE);
        entries.add(VividSpirit.SMALL_SAPPHIRE_TILES);
        entries.add(VividSpirit.LARGE_SAPPHIRE_TILES);
        entries.add(VividSpirit.AMETHYST_INLAID_BASALT_COLUMN);
        entries.add(VividSpirit.AMETHYST_INLAID_BASALT_CREST);
        entries.add(VividSpirit.AMETHYST_INLAID_CALCITE_COLUMN);
        entries.add(VividSpirit.AMETHYST_INLAID_CALCITE_CREST);
        entries.add(VividSpirit.CITRINE_INLAID_BASALT_COLUMN);
        entries.add(VividSpirit.CITRINE_INLAID_BASALT_CREST);
        entries.add(VividSpirit.CITRINE_INLAID_CALCITE_COLUMN);
        entries.add(VividSpirit.CITRINE_INLAID_CALCITE_CREST);
        entries.add(VividSpirit.TOPAZ_INLAID_BASALT_COLUMN);
        entries.add(VividSpirit.TOPAZ_INLAID_BASALT_CREST);
        entries.add(VividSpirit.TOPAZ_INLAID_CALCITE_COLUMN);
        entries.add(VividSpirit.TOPAZ_INLAID_CALCITE_CREST);
        entries.add(VividSpirit.ONYX_INLAID_BASALT_COLUMN);
        entries.add(VividSpirit.ONYX_INLAID_BASALT_CREST);
        entries.add(VividSpirit.ONYX_INLAID_CALCITE_COLUMN);
        entries.add(VividSpirit.ONYX_INLAID_CALCITE_CREST);
        entries.add(VividSpirit.MOONSTONE_INLAID_BASALT_COLUMN);
        entries.add(VividSpirit.MOONSTONE_INLAID_BASALT_CREST);
        entries.add(VividSpirit.MOONSTONE_INLAID_CALCITE_COLUMN);
        entries.add(VividSpirit.MOONSTONE_INLAID_CALCITE_CREST);
        entries.add(VividSpirit.ALLURITE_INLAID_BLOOMED_CALCITE_CREST);
        entries.add(VividSpirit.ALLURITE_INLAID_BLOOMED_CALCITE_COLUMN);
        entries.add(VividSpirit.LUMIERE_INLAID_BLOOMED_CALCITE_COLUMN);
        entries.add(VividSpirit.LUMIERE_INLAID_BLOOMED_CALCITE_CREST);
        entries.add(VividSpirit.POLISHED_BLOOMED_CALCITE_COLUMN);
        entries.add(VividSpirit.POLISHED_BLOOMED_CALCITE_CREST);
        entries.add(VividSpirit.STERE_BLOCK);
        entries.add(VividSpirit.CLAY_DIRT);
        entries.add(VividSpirit.FLUX_BRICK_COLUMN);
        entries.add(VividSpirit.POLYMER_CLAY);
        entries.add(VividSpirit.FROZEN_RUBY);
        entries.add(VividSpirit.FROZEN_RUBY_SHARD);
        entries.add(VividSpirit.ARID_SHARD);
        entries.add(VividSpirit.FLORAL_SHARD);
        entries.add(VividSpirit.JUNGLE_SHARD);
        entries.add(VividSpirit.MOUNTAIN_SHARD);
        entries.add(VividSpirit.SAPPHIC_SHARD);
        entries.add(VividSpirit.PLATEAU_SHARD);
        entries.add(VividSpirit.SNOWY_SHARD);
        entries.add(VividSpirit.WETLAND_SHARD);
        entries.add(VividSpirit.BEDROCK_SCYTHE);
        entries.add(VividSpirit.BEDROCK_KNIFE);
        entries.add(VividSpirit.CEREMONIAL_FALCHION);


    }).build());
    public static void registerItemGroups() {
        VividSpirit.LOGGER.info("Registering item groups for " + VividSpirit.MOD_ID);
    }
}
