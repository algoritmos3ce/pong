package algo3.pong;

public class GameState {
    public final Ball ball = new Ball();
    private final Paddle[] paddles = new Paddle[]{
            new Paddle(Side.LEFT),
            new Paddle(Side.RIGHT)
    };
    private final int[] score = new int[]{0, 0};

    public GameState() {
        reset();
    }

    // for testing
    public GameState(Vec2D initialBallPosition, Vec2D initialBallVelocity, double[] initialPaddlePositions, int[] initialScore) {
        ball.pos = initialBallPosition;
        ball.vel = initialBallVelocity;
        getPaddle(Side.LEFT).centerY = initialPaddlePositions[Side.LEFT.ordinal()];
        getPaddle(Side.RIGHT).centerY = initialPaddlePositions[Side.RIGHT.ordinal()];
        score[Side.LEFT.ordinal()] = initialScore[Side.LEFT.ordinal()];
        score[Side.RIGHT.ordinal()] = initialScore[Side.RIGHT.ordinal()];
    }

    public void reset() {
        ball.reset();
        for (Paddle p : paddles) {
            p.reset();
        }
        score[Side.LEFT.ordinal()] = 0;
        score[Side.RIGHT.ordinal()] = 0;
    }

    private void startPoint() {
        ball.reset();
    }

    public Paddle getPaddle(Side s) {
        return paddles[s.ordinal()];
    }

    public int getScore(Side s) {
        return score[s.ordinal()];
    }

    public void incScore(Side s) {
        score[s.ordinal()]++;
    }

    public void update() {
        ball.update(paddles);
        checkGoal();
    }

    private void checkGoal() {
        if (ball.pos.x() > Pong.W) {
            incScore(Side.LEFT);
            startPoint();
        }
        if (ball.pos.x() < 0) {
            incScore(Side.RIGHT);
            startPoint();
        }
    }
}
