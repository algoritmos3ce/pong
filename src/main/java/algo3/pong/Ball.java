package algo3.pong;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;

public class Ball {
    public static final int RADIUS = 5;

    public Vec2D pos;
    public Vec2D vel;

    public Ball() {
        reset();
    }

    private static Vec2D randomVelocity() {
        double vx = random() > 0.5 ? 1 : -1;
        double vy = random();
        var norm = sqrt(vx * vx + vy * vy);
        return new Vec2D(vx / norm, vy / norm);
    }

    public void reset() {
        pos = new Vec2D(Pong.W / 2.0, Pong.H / 2.0);
        vel = randomVelocity();
    }

    public void update(Paddle[] paddles) {
        pos = pos.add(vel.scaled(Pong.VELOCITY));

        // bounce against up/down walls
        if (pos.y() < RADIUS || pos.y() > Pong.H - RADIUS) {
            vel = vel.invertY();
        }

        // bounce against paddles
        if (paddles[Side.LEFT.ordinal()].isHittingBall(this) ||
                paddles[Side.RIGHT.ordinal()].isHittingBall(this)) {
            vel = vel.invertX().add(new Vec2D(0, 2 * (random() - 0.5)));
        }
    }
}
