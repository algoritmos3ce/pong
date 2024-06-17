package algo3.pong;

public record Vec2D(double x, double y) {
	public static final Vec2D ZERO = new Vec2D(0, 0);

	public Vec2D scaled(double d) {
		return new Vec2D(x * d, y * d);
	}

	public Vec2D add(Vec2D v) {
		return new Vec2D(x + v.x, y + v.y);
	}

	public Vec2D invertX() {
		return new Vec2D(-x, y);
	}

	public Vec2D invertY() {
		return new Vec2D(x, -y);
	}

	@Override
	public String toString() {
		return "(%f, %f)".formatted(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec2D vec2D = (Vec2D) o;
		return Double.compare(x, vec2D.x) == 0 && Double.compare(y, vec2D.y) == 0;
	}

}
