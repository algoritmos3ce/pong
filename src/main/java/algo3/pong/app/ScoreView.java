package algo3.pong.app;

import algo3.pong.GameState;
import algo3.pong.Pong;
import algo3.pong.Side;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class ScoreView implements View {
    private final GameState state;

    public ScoreView(GameState state) {
        this.state = state;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.TOP);
        gc.fillText(
                "%d - %d".formatted(
                        state.getScore(Side.LEFT),
                        state.getScore(Side.RIGHT)
                ),
                Pong.W / 2.0,
                10
        );
    }
}
