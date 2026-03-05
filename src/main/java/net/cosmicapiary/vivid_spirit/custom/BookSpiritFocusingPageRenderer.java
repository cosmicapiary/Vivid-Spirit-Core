package net.cosmicapiary.vivid_spirit.custom;

import com.klikli_dev.modonomicon.book.BookTextHolder;
import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.client.gui.book.BookContentScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.sammy.malum.common.recipe.SpiritFocusingRecipe;
import net.minecraft.client.gui.DrawContext;

public class BookSpiritFocusingPageRenderer extends BookRecipePageRenderer<SpiritFocusingRecipe, BookRecipePage<SpiritFocusingRecipe>> {
	public BookSpiritFocusingPageRenderer(BookRecipePage<SpiritFocusingRecipe> page) {
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
	protected void drawRecipe(DrawContext guiGraphics, SpiritFocusingRecipe recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

	}
}
