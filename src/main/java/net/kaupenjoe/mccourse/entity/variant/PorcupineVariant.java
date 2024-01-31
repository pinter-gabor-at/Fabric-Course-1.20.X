package net.kaupenjoe.mccourse.entity.variant;

public enum PorcupineVariant {
	DEFAULT(0),
	GREY(1);

	private final int id;

	PorcupineVariant(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static PorcupineVariant byId(int id) {
		return id == 1 ? GREY : DEFAULT;
	}
}
