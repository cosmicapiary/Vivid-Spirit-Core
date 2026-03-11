package net.cosmicapiary.vivid_spirit.custom.malum_compat;

import com.google.common.collect.Lists;
import com.klikli_dev.modonomicon.book.BookTextHolder;
import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.client.gui.book.BookContentScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class BookSpiritInfusionPageRenderer extends AbstractBookMalumRecipePageRenderer<SpiritInfusionRecipe> {
	private static final Identifier BACKGROUND_TEXTURE = MalumMod.malumPath("textures/gui/book/pages/spirit_infusion_page.png");

	public BookSpiritInfusionPageRenderer(BookRecipePage<SpiritInfusionRecipe> page) {
		super(page);
	}

	@Override
	protected int getRecipeHeight() {
		return 120;
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
		//drawContext.drawTexture(BACKGROUND_TEXTURE, recipeX, recipeY, 0, 0, 142, 172, 256, 256);

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
		renderItemFrames(drawContext, spirits.size(), recipeX - 8, recipeY + 48, true);
		if (!extraItems.isEmpty()) {
			renderItemFrames(drawContext, extraItems.size(), recipeX + 80, recipeY + 48, true);
		}

		// spirit and extra item slots
		addItems(parentScreen, drawContext, recipeX - 8, recipeY + 48, true, mouseX, mouseY, spirits);
		addItems(parentScreen, drawContext, recipeX + 80, recipeY + 48, true, mouseX, mouseY, extraItems);

		// main ingredient slot
		parentScreen.renderIngredient(drawContext, recipeX + 36, recipeY + 48, mouseX, mouseY, inputs.get(0));

		// output slot
		parentScreen.renderItemStack(drawContext, recipeX + 36, recipeY + 80, mouseX, mouseY, result);
	}
}
