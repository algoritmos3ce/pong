package algo3.pong;

public enum Side {
    LEFT(Paddle.GAP, -1),
    RIGHT(Pong.H - Paddle.GAP, 1);

    public final double paddleCenterX;
    public final double direction;

    Side(double paddleCenterX, double direction) {
        this.paddleCenterX = paddleCenterX;
        this.direction = direction;
    }
}
