package net.kaupenjoe.mccourse.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;

public class Painting extends PaintingVariant {
	private final Identifier id;

	public Painting(Identifier id, int width, int height) {
		super(width, height);
		this.id = id;
	}

	public Identifier getId() {
		return id;
	}
}
