package net.cosmicapiary.vivid_spirit.custom;

import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.sammy.malum.common.recipe.SpiritFocusingRecipe;
import net.minecraft.client.gui.DrawContext;

public class BookSpiritFocusingPageRenderer extends BookRecipePageRenderer<SpiritFocusingRecipe, BookRecipePage<SpiritFocusingRecipe>> {
	public BookSpiritFocusingPageRenderer(BookRecipePage<SpiritFocusingRecipe> page) {
		super(page);
	}

	@Override
	protected int getRecipeHeight() {
		return 0;
	}

	@Override
	protected void drawRecipe(DrawContext guiGraphics, SpiritFocusingRecipe recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

	}
}
