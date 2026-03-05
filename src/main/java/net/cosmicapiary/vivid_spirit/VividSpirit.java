package net.cosmicapiary.vivid_spirit;

import com.google.gson.JsonObject;
import com.klikli_dev.modonomicon.book.BookTextHolder;
import com.klikli_dev.modonomicon.book.conditions.BookCondition;
import com.klikli_dev.modonomicon.book.conditions.BookNoneCondition;
import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.data.LoaderRegistry;
import com.klikli_dev.modonomicon.util.BookGsonHelper;
import com.sammy.malum.common.recipe.AbstractMalumRecipe;
import com.sammy.malum.registry.common.recipe.RecipeTypeRegistry;
import de.dafuqs.spectrum.api.recipe.GatedRecipe;
import de.dafuqs.spectrum.blocks.decoration.CardinalFacingBlock;
import de.dafuqs.spectrum.blocks.gemstone.SpectrumBuddingBlock;
import de.dafuqs.spectrum.blocks.geology.ShimmerstoneOreBlock;
import de.dafuqs.spectrum.blocks.pedestal.BuiltinPedestalVariant;
import de.dafuqs.spectrum.blocks.pedestal.PedestalBlock;
import de.dafuqs.spectrum.compat.modonomicon.pages.BookGatedRecipePage;
import de.dafuqs.spectrum.registries.*;

import net.cosmicapiary.vivid_spirit.custom.*;
import net.fabricmc.api.ModInitializer;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.id.paradiselost.blocks.ParadiseLostBlocks;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VividSpirit implements ModInitializer {
	public static final String MOD_ID = "vivid_spirit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Item TOFU = Registry.register(Registries.ITEM, new Identifier("croptopia", "tofu"),
			new Item(liquidPearlSettings()));
	public static FlowableFluid STILL_SPIRIT;
	public static FlowableFluid FLOWING_SPIRIT;
	public static Item SPIRIT_BUCKET;
	public static Block SPIRIT;
	@Override
	public void onInitialize() {
		VividWorldGen.addFeatures();
		VividItemGroups.registerItemGroups();

		LOGGER.info("Replicating blue goo... (Vivid Spirit)");

		STILL_SPIRIT = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "spirit"),
			new SpiritFluid.Still());

		FLOWING_SPIRIT = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_spirit"),
			new SpiritFluid.Flowing());

		SPIRIT_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spirit_bucket"),
			new BucketItem(STILL_SPIRIT, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

		SPIRIT = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "spirit"),
			new FluidBlock(STILL_SPIRIT, FabricBlockSettings.copyOf(Blocks.WATER).luminance(15).mapColor(MapColor.BRIGHT_TEAL)));

		LoaderRegistry.registerPageLoader(new Identifier("vivid_spirit:malum/spirit_infusion"),
				json -> BookMalumRecipePage.fromJson(new Identifier("vivid_spirit:malum/spirit_infusion"), RecipeTypeRegistry.SPIRIT_INFUSION.get(), json, false),
				buffer -> BookMalumRecipePage.fromNetwork(new Identifier("vivid_spirit:malum/spirit_infusion"), RecipeTypeRegistry.SPIRIT_INFUSION.get(), buffer));
		LoaderRegistry.registerPageLoader(new Identifier("vivid_spirit:malum/spirit_focusing"),
				json -> BookMalumRecipePage.fromJson(new Identifier("vivid_spirit:malum/spirit_focusing"), RecipeTypeRegistry.SPIRIT_FOCUSING.get(), json, false),
				buffer -> BookMalumRecipePage.fromNetwork(new Identifier("vivid_spirit:malum/spirit_focusing"), RecipeTypeRegistry.SPIRIT_FOCUSING.get(), buffer));
	}

	public static final VividToolMaterials.ToolMaterial FAKE_BEDROCK = VividToolMaterials.ToolMaterial.FAKE_BEDROCK;
	public static final VividToolMaterials.ToolMaterial FAKE_CRYSTAL = VividToolMaterials.ToolMaterial.FAKE_CRYSTAL;

	public static final Item BEDROCK_SCYTHE = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bedrock_scythe"),
			new BedrockScytheItem(FAKE_BEDROCK, -3, 0.1F, SpectrumItems.IS.of(Rarity.UNCOMMON).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()))
	);

	public static final Item BEDROCK_KNIFE = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "bedrock_knife"),
			new BedrockKnifeItem(FAKE_BEDROCK, 3, -2F, SpectrumItems.IS.of(Rarity.UNCOMMON).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability())) {
			}
	);

	public static final Item CEREMONIAL_FALCHION = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ceremonial_falchion"),
			new CeremonialFalchionItem(FAKE_CRYSTAL, 4, -2.2F, SpectrumItems.IS.of(Rarity.UNCOMMON).maxDamage(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH.getDurability())) {
			}
	);


	public static final Block BLACK_ICE = registerBlock("black_ice",
			new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
					.mapColor(MapColor.BLACK)
					.nonOpaque()
					.slipperiness(1.05f)
			));
	public static final Item RAW_SILVER_NUGGET = registerItem("raw_silver_nugget",
			new Item(new FabricItemSettings()));
	public static final Item PURE_RUBY = registerItem("pure_ruby",
			new Item(new FabricItemSettings()));
	public static final Item PURE_SILVER = registerItem("pure_silver",
			new Item(new FabricItemSettings()));

	public static final Item FROZEN_RUBY = registerItem("frozen_ruby",
			new Item(new FabricItemSettings()));
	public static final Item FROZEN_RUBY_SHARD = registerItem("frozen_ruby_shard",
			new Item(new FabricItemSettings()));
	public static final Item LIQUID_PEARLS = registerItem("liquid_pearls",
			new Item(liquidPearlSettings()));

	public static final Item ARID_SHARD = registerItem("arid_shard",
			new BiomeEyeItem(new FabricItemSettings()));
	public static final Item FLORAL_SHARD = registerItem("floral_shard",
			new BiomeEyeItem(new FabricItemSettings()));
	public static final Item JUNGLE_SHARD = registerItem("jungle_shard",
			new BiomeEyeItem(new FabricItemSettings()));
	public static final Item MOUNTAIN_SHARD = registerItem("mountain_shard",
			new BiomeEyeItem(new FabricItemSettings()));
	public static final Item SAPPHIC_SHARD = registerItem("sapphic_shard",
			new BiomeEyeItem(new FabricItemSettings()));
	public static final Item SNOWCAP_SHARD = registerItem("snowcap_shard",
			new BiomeEyeItem(new FabricItemSettings()));
	public static final Item SNOWY_SHARD = registerItem("snowy_shard",
			new BiomeEyeItem(new FabricItemSettings()));
	public static final Item WETLAND_SHARD = registerItem("wetland_shard",
			new BiomeEyeItem(new FabricItemSettings()));

	public static final Block FLUX_BRICK_COLUMN = registerBlock("flux_brick_column",
			new PillarBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)
			));
	public static final Block STERE_BLOCK = registerBlock("stere_block",
			new PillarBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_LOG)
			));
	public static final Block POLYMER_CLAY = registerBlock("polymer_clay",
			new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL)
					.sounds(BlockSoundGroup.MUD_BRICKS)
			));

	public static final Block CLAY_DIRT = registerBlock("clay_dirt",
			new Block(FabricBlockSettings.copyOf(Blocks.CLAY)
			));

	public static final Block PIGMENT_PEDESTAL = registerBlock("pigment_pedestal",
			new PedestalBlock(FabricBlockSettings.copyOf(SpectrumBlocks.PEDESTAL_BASIC_TOPAZ).sounds(BlockSoundGroup.STONE),
					VividPedestalVariant.PRE_GEM
			));

	public static final Block TOPAZ_INLAID_BASALT_COLUMN = registerBlock("topaz_inlaid_basalt_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.TOPAZ_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block TOPAZ_INLAID_CALCITE_COLUMN = registerBlock("topaz_inlaid_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.TOPAZ_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block AMETHYST_INLAID_BASALT_COLUMN = registerBlock("amethyst_inlaid_basalt_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.AMETHYST_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block AMETHYST_INLAID_CALCITE_COLUMN = registerBlock("amethyst_inlaid_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.AMETHYST_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block CITRINE_INLAID_BASALT_COLUMN = registerBlock("citrine_inlaid_basalt_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.CITRINE_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block CITRINE_INLAID_CALCITE_COLUMN = registerBlock("citrine_inlaid_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.CITRINE_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block ONYX_INLAID_BASALT_COLUMN = registerBlock("onyx_inlaid_basalt_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.ONYX_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block ONYX_INLAID_CALCITE_COLUMN = registerBlock("onyx_inlaid_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.ONYX_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block MOONSTONE_INLAID_BASALT_COLUMN = registerBlock("moonstone_inlaid_basalt_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.MOONSTONE_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block MOONSTONE_INLAID_CALCITE_COLUMN = registerBlock("moonstone_inlaid_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(SpectrumBlocks.MOONSTONE_CHISELED_CALCITE)
					.luminance(7)
			));

	public static final Block ALLURITE_INLAID_BLOOMED_CALCITE_COLUMN = registerBlock("allurite_inlaid_bloomed_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(ParadiseLostBlocks.BLOOMED_CALCITE)
					.luminance(7)
			));
	public static final Block LUMIERE_INLAID_BLOOMED_CALCITE_COLUMN = registerBlock("lumiere_inlaid_bloomed_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(ParadiseLostBlocks.BLOOMED_CALCITE)
					.luminance(7)
			));
	public static final Block ALLURITE_INLAID_BLOOMED_CALCITE_CREST = registerBlock("allurite_inlaid_bloomed_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(ParadiseLostBlocks.BLOOMED_CALCITE)
					.luminance(7)
			));
	public static final Block LUMIERE_INLAID_BLOOMED_CALCITE_CREST = registerBlock("lumiere_inlaid_bloomed_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(ParadiseLostBlocks.BLOOMED_CALCITE)
					.luminance(7)
			));
	public static final Block POLISHED_BLOOMED_CALCITE_COLUMN = registerBlock("polished_bloomed_calcite_column",
			new PillarBlock(FabricBlockSettings.copyOf(ParadiseLostBlocks.BLOOMED_CALCITE)
			));
	public static final Block POLISHED_BLOOMED_CALCITE_CREST = registerBlock("polished_bloomed_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(ParadiseLostBlocks.BLOOMED_CALCITE)
			));

	public static final Block TOPAZ_INLAID_BASALT_CREST = registerBlock("topaz_inlaid_basalt_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.TOPAZ_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block TOPAZ_INLAID_CALCITE_CREST = registerBlock("topaz_inlaid_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.TOPAZ_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block AMETHYST_INLAID_BASALT_CREST = registerBlock("amethyst_inlaid_basalt_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.AMETHYST_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block AMETHYST_INLAID_CALCITE_CREST = registerBlock("amethyst_inlaid_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.AMETHYST_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block CITRINE_INLAID_BASALT_CREST = registerBlock("citrine_inlaid_basalt_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.CITRINE_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block CITRINE_INLAID_CALCITE_CREST = registerBlock("citrine_inlaid_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.CITRINE_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block ONYX_INLAID_BASALT_CREST = registerBlock("onyx_inlaid_basalt_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.ONYX_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block ONYX_INLAID_CALCITE_CREST = registerBlock("onyx_inlaid_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.ONYX_CHISELED_CALCITE)
					.luminance(7)
			));
	public static final Block MOONSTONE_INLAID_BASALT_CREST = registerBlock("moonstone_inlaid_basalt_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.MOONSTONE_CHISELED_BASALT)
					.luminance(7)
			));
	public static final Block MOONSTONE_INLAID_CALCITE_CREST = registerBlock("moonstone_inlaid_calcite_crest",
			new CardinalFacingBlock(FabricBlockSettings.copyOf(SpectrumBlocks.MOONSTONE_CHISELED_CALCITE)
					.luminance(7)
			));

	public static final Block BLACKSLAG_ZINC_ORE = registerBlock("blackslag_zinc_ore",
			new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
			));
	public static final Block BLACKSLAG_SILVER_ORE = registerBlock("blackslag_silver_ore",
			new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
			));
	public static final Block STARRY_CLUMP = registerBlock("starry_clump",
			new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
			));
	public static final Block SAPPHIRE_TILES = registerBlock("sapphire_tiles",
			new Block(FabricBlockSettings.copyOf(Blocks.BLUE_GLAZED_TERRACOTTA)
			));
	public static final Block SMALL_SAPPHIRE_TILES = registerBlock("small_sapphire_tiles",
			new Block(FabricBlockSettings.copyOf(Blocks.BLUE_GLAZED_TERRACOTTA)
			));
	public static final Block LARGE_SAPPHIRE_TILES = registerBlock("large_sapphire_tiles",
			new Block(FabricBlockSettings.copyOf(Blocks.BLUE_GLAZED_TERRACOTTA)
			));
	public static final Block SAPPHIRE_ICE = registerBlock("sapphire_ice",
			new Block(FabricBlockSettings.copyOf(Blocks.PACKED_ICE).nonOpaque()
			));
	public static final Block SAPPHIRE_PACKED_ICE = registerBlock("sapphire_packed_ice",
			new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE).nonOpaque()
			));
	public static final Block SHIMMERSTONE_ORE = registerBlock("shimmerstone_ore",
			new ShimmerstoneOreBlock(FabricBlockSettings.copyOf(SpectrumBlocks.SHIMMERSTONE_ORE).ticksRandomly(),
					UniformIntProvider.create(2, 4), SpectrumAdvancements.REVEAL_SHIMMERSTONE, ParadiseLostBlocks.FLOESTONE.getDefaultState()
			));
	public static final Block FLOESTONE_REDSTONE_ORE = registerBlock("floestone_redstone_ore",
			new RedstoneOreBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_ORE)
			));
	public static final Block PURE_RUBY_BLOCK = registerBlock("pure_ruby_block",
			new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)
			));
	public static final Block SMALL_RUBY_BUD = registerBlock("small_ruby_bud",
			new AmethystClusterBlock(3, 4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block LARGE_RUBY_BUD = registerBlock("large_ruby_bud",
			new AmethystClusterBlock(5, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block RUBY_CLUSTER = registerBlock("ruby_cluster",
			new AmethystClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block PURE_SILVER_BLOCK = registerBlock("pure_silver_block",
			new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)
			));
	public static final Block SMALL_SILVER_BUD = registerBlock("small_silver_bud",
			new AmethystClusterBlock(3, 4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block LARGE_SILVER_BUD = registerBlock("large_silver_bud",
			new AmethystClusterBlock(5, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block SILVER_CLUSTER = registerBlock("silver_cluster",
			new AmethystClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block SMALL_ALLURITE_BUD = registerBlock("small_allurite_bud",
			new AmethystClusterBlock(3, 4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block MEDIUM_ALLURITE_BUD = registerBlock("medium_allurite_bud",
			new AmethystClusterBlock(4, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block LARGE_ALLURITE_BUD = registerBlock("large_allurite_bud",
			new AmethystClusterBlock(5, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block ALLURITE_CLUSTER = registerBlock("allurite_cluster",
			new AmethystClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block ECHO_CLUSTER = registerBlock("echo_cluster",
			new AmethystClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block BUDDING_ALLURITE_BLOCK = registerBlock("budding_allurite_block",
			new SpectrumBuddingBlock(FabricBlockSettings.copyOf(Blocks.BUDDING_AMETHYST).pistonBehavior(PistonBehavior.DESTROY).ticksRandomly(), SMALL_ALLURITE_BUD, MEDIUM_ALLURITE_BUD, LARGE_ALLURITE_BUD, ALLURITE_CLUSTER, SpectrumSoundEvents.BLOCK_CITRINE_BLOCK_HIT, SpectrumSoundEvents.BLOCK_CITRINE_BLOCK_CHIME));

	public static final Block SMALL_LUMIERE_BUD = registerBlock("small_lumiere_bud",
			new AmethystClusterBlock(3, 4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block MEDIUM_LUMIERE_BUD = registerBlock("medium_lumiere_bud",
			new AmethystClusterBlock(4, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block LARGE_LUMIERE_BUD = registerBlock("large_lumiere_bud",
			new AmethystClusterBlock(5, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block LUMIERE_CLUSTER = registerBlock("lumiere_cluster",
			new AmethystClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	public static final Block BUDDING_LUMIERE_BLOCK = registerBlock("budding_lumiere_block",
			new SpectrumBuddingBlock(FabricBlockSettings.copyOf(Blocks.BUDDING_AMETHYST).pistonBehavior(PistonBehavior.DESTROY).ticksRandomly(), SMALL_LUMIERE_BUD, MEDIUM_LUMIERE_BUD, LARGE_LUMIERE_BUD, LUMIERE_CLUSTER, SpectrumSoundEvents.BLOCK_CITRINE_BLOCK_HIT, SpectrumSoundEvents.BLOCK_CITRINE_BLOCK_CHIME));


	private static Block registerBlock(String name, Block block){
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(VividSpirit.MOD_ID, name), block);
	}
	private static void registerBlockItem(String name, Block block){
		Registry.register(Registries.ITEM, new Identifier(VividSpirit.MOD_ID, name),
				new BlockItem(block, new FabricItemSettings()));
	}
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(VividSpirit.MOD_ID, name), item);
	}

	private static FabricItemSettings liquidPearlSettings() {
		return new FabricItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier((float) 0.4)
				.alwaysEdible()
				.snack().build());
	}

}
