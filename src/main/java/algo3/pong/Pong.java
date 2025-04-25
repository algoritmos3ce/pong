package algo3.pong;

public class Pong {
    public static final int H = 300;
    public static final int W = 300;

    public static final int FPS = 60;
    public static final long MS_PER_FRAME = 1000 / FPS;
    public static final double VELOCITY = 150.0 / FPS;

    public final GameState state;

    public Pong() {
        state = new GameState();
    }

    // for testing
    public Pong(GameState gameState) {
        state = gameState;
    }

    public void reset() {
        state.reset();
    }

    public Event update(Iterable<Action> actions) {
        for (var action : actions)
            action.apply(this);
        return state.update();
    }

    public void movePaddle(Side s, int dy) {
        state.getPaddle(s).move(dy);
    }
}
