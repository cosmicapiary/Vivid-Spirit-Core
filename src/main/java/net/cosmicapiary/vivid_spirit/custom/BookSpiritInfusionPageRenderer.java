package net.cosmicapiary.vivid_spirit.custom;

import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import net.minecraft.client.gui.DrawContext;

public class BookSpiritInfusionPageRenderer extends BookRecipePageRenderer<SpiritInfusionRecipe, BookRecipePage<SpiritInfusionRecipe>> {
	public BookSpiritInfusionPageRenderer(BookRecipePage<SpiritInfusionRecipe> page) {
		super(page);
	}

	@Override
	protected int getRecipeHeight() {
		return 0;
	}

	@Override
	protected void drawRecipe(DrawContext guiGraphics, SpiritInfusionRecipe recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

	}
}
