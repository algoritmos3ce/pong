package algo3.pong;

import static java.lang.Math.*;

public class Paddle {
    // gap from left/right border
    public static final int GAP = 10;

    // rect dimensions
    public static final int WIDTH = 10;
    public static final int HEIGHT = 60;

    public final Side side;
    public double centerY;

    public Paddle(Side s) {
        this.side = s;
        reset();
    }

    public void reset() {
        centerY = Pong.H / 2.0;
    }

    public boolean isHittingBall(Ball ball) {
        // is the ball coming to the paddle?
        if (signum(ball.vel.x()) != side.direction) {
            return false;
        }

        if (abs(ball.pos.x() - side.paddleCenterX) > WIDTH / 2.0 + Ball.RADIUS)
            return false;
        if (abs(ball.pos.y() - centerY) > HEIGHT / 2.0 + Ball.RADIUS)
            return false;

        return true;
    }

    public void move(int dy) {
        centerY += dy * Pong.VELOCITY * 1.5;
        centerY = clamp(centerY, 0, Pong.H);
    }
}