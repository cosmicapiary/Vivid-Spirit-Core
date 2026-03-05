package net.cosmicapiary.vivid_spirit.custom;

import com.google.common.collect.Lists;
import com.klikli_dev.modonomicon.book.BookTextHolder;
import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.client.gui.book.BookContentScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.ArcanaCodexHelper;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class BookSpiritInfusionPageRenderer extends BookRecipePageRenderer<SpiritInfusionRecipe, BookRecipePage<SpiritInfusionRecipe>> {
	private static final Identifier BACKGROUND_TEXTURE = MalumMod.malumPath("textures/gui/spirit_infusion_jei.png");
	public BookSpiritInfusionPageRenderer(BookRecipePage<SpiritInfusionRecipe> page) {
		super(page);
	}

	@Override
	protected int getRecipeHeight() {
		return 185;
	}

	public void renderTitle(DrawContext drawContext, int recipeY, boolean second) {
		BookTextHolder title = second ? page.getTitle2() : page.getTitle1();
		if (!title.getString().isEmpty()) {
			int titleY = second ? recipeY - (page.getTitle2().isEmpty() ? 10 : 0) - 10 : -5;
			super.renderTitle(drawContext, title, false, BookContentScreen.PAGE_WIDTH / 2, titleY);
		}
	}

	@Override
	protected void drawRecipe(DrawContext drawContext, SpiritInfusionRecipe recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {
		World world = parentScreen.getMinecraft().world;
		if (world == null) return;

		RenderSystem.enableBlend();
		drawContext.drawTexture(BACKGROUND_TEXTURE, recipeX, recipeY, 0, 0, 100, 80, 256, 256);

		renderTitle(drawContext, recipeY, second);

		// the ingredients and result
		final List<Ingredient> inputs = Lists.newArrayList();
		final List<Ingredient> extraItems;
		final List<Ingredient> spirits;
		final ItemStack result = recipe.output;
		inputs.add(Ingredient.ofStacks(recipe.input.getStack()));
		inputs.addAll(extraItems = recipe.extraItems.stream().map((ingredient) -> Ingredient.ofStacks(ingredient.getStack())).toList());
		inputs.addAll(spirits = recipe.spirits.stream().map((spirit) -> Ingredient.ofStacks(spirit.getStack())).toList());

		// input frames
		ArcanaCodexHelper.renderItemFrames(drawContext.getMatrices(), spirits.size(), 19, 48, true);
		if (!extraItems.isEmpty()) {
			ArcanaCodexHelper.renderItemFrames(drawContext.getMatrices(), extraItems.size(), 103, 48, true);
		}

		// spirit and extra item slots
		addItems(drawContext, 19, 48, true, mouseX, mouseY, spirits);
		addItems(drawContext, 103, 48, true, mouseX, mouseY, extraItems);

		// main ingredient slot
		parentScreen.renderIngredient(drawContext, 62, 56, mouseX, mouseY, inputs.get(0));

		// output slot
		parentScreen.renderItemStack(drawContext, 62, 56, mouseX, mouseY, result);
	}

	public void addItems(DrawContext drawContext, int left, int top, boolean vertical, int mouseX, int mouseY, List<Ingredient> ingredients) {
		int slots = ingredients.size();
		if (vertical) {
			top -= 10 * (slots - 1);
		} else {
			left -= 10 * (slots - 1);
		}

		for(int i = 0; i < slots; ++i) {
			int offset = i * 20;
			int offsetLeft = left + 1 + (vertical ? 0 : offset);
			int offsetTop = top + 1 + (vertical ? offset : 0);
			parentScreen.renderIngredient(drawContext, offsetLeft, offsetTop, mouseX, mouseY, ingredients.get(i));
		}

	}
}
