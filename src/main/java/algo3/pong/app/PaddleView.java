package algo3.pong.app;

import algo3.pong.Paddle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaddleView implements View {
    final Paddle paddle;

    public PaddleView(Paddle paddle) {
        this.paddle = paddle;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(
                paddle.side.paddleCenterX - Paddle.WIDTH / 2.0,
                paddle.centerY - Paddle.HEIGHT / 2.0,
                Paddle.WIDTH,
                Paddle.HEIGHT
        );
    }
}