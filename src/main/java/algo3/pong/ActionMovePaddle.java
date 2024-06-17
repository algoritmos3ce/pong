package algo3.pong;

public class ActionMovePaddle implements Action {
    private final Side side;
    private final int dy;

    public ActionMovePaddle(Side s, int dy) {
        this.side = s;
        this.dy = dy;
    }

    @Override
    public void apply(Pong pong) {
        pong.movePaddle(side, dy);
    }
}
