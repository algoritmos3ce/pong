package algo3.pong.app;

import algo3.pong.Ball;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BallView implements View {
    private final Ball ball;

    public BallView(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(
                ball.pos.x() - Ball.RADIUS,
                ball.pos.y() - Ball.RADIUS,
                Ball.RADIUS * 2,
                Ball.RADIUS * 2
        );
    }
}
