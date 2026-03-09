package net.cosmicapiary.vivid_spirit.custom.malum_compat;

import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.client.gui.book.BookContentScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.sammy.malum.client.screen.codex.screens.EntryScreen;
import com.sammy.malum.common.recipe.AbstractMalumRecipe;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.recipe.Ingredient;

import java.util.List;

public abstract class AbstractBookMalumRecipePageRenderer<T extends AbstractMalumRecipe> extends BookRecipePageRenderer<T, BookRecipePage<T>> {
	public AbstractBookMalumRecipePageRenderer(BookRecipePage<T> page) {
		super(page);
	}

	public static void addItems(BookContentScreen screen, DrawContext drawContext, int left, int top, boolean vertical, int mouseX, int mouseY, List<Ingredient> ingredients) {
		int slots = ingredients.size();
		if (vertical) {
			top -= 10 * (slots - 1);
		} else {
			left -= 10 * (slots - 1);
		}

		for(int i = 0; i < slots; ++i) {
			int offset = i * 20;
			int offsetLeft = left + 2 + (vertical ? 0 : offset);
			int offsetTop = top + 2 + (vertical ? offset : 0);
			screen.renderIngredient(drawContext, offsetLeft, offsetTop, mouseX, mouseY, ingredients.get(i));
		}
	}

	public static void renderItemFrames(DrawContext drawContext, int slots, int left, int top, boolean vertical) {
		int startingOffset = 10 * (slots - 1);
		if (vertical) {
			top -= startingOffset;
		} else {
			left -= startingOffset;
		}

		int i;
		for(i = 0; i < slots; ++i) {
			int offset = i * 20;
			int oLeft = left + (vertical ? 0 : offset);
			int oTop = top + (vertical ? offset : 0);
			drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, oLeft, oTop, 18.0F, 16.0F, 20, 20, 38, 44);
			if (vertical) {
				int v = i == slots - 1 ? 40 : 37;
				drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, oLeft + 1, oTop + 19, 16.0F, (float)v, 18, 2, 38, 44);
			} else {
				drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, oLeft + 1, top + 19, 16.0F, 40.0F, 18, 2, 38, 44);
				if (slots > 1 && i != slots - 1) {
					drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, oLeft + 19, top, 16.0F, 16.0F, 2, 20, 38, 44);
				}
			}
		}

		i = left + 5 + (vertical ? 0 : startingOffset);
		drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, i, top - 5, 28.0F, 0.0F, 10, 6, 38, 44);
		if (vertical) {
			drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, left - 4, top - 4, 0.0F, 0.0F, 28, 7, 38, 44);
			drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, left - 4, top + 17 + 20 * (slots - 1), 0.0F, 8.0F, 28, 7, 38, 44);
		} else {
			drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, left - 4, top - 4, 0.0F, 16.0F, 7, 28, 38, 44);
			drawContext.drawTexture(EntryScreen.ELEMENT_SOCKET, left + 17 + 20 * (slots - 1), top - 4, 8.0F, 16.0F, 7, 28, 38, 44);
		}

	}
}
