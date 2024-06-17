package algo3.pong;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class PongTest {
    @Test
    public void testInitialState() {
        var pong = new Pong();

        assertEquals(0, pong.state.getScore(Side.LEFT));
        assertEquals(0, pong.state.getScore(Side.RIGHT));
        assertEquals(Pong.H / 2.0, pong.state.getPaddle(Side.LEFT).centerY, 0);
        assertEquals(Pong.H / 2.0, pong.state.getPaddle(Side.RIGHT).centerY, 0);
        assertEquals(new Vec2D(Pong.W / 2.0, Pong.H / 2.0), pong.state.ball.pos);
        assertNotEquals(Vec2D.ZERO, pong.state.ball.vel);
    }

    @Test
    public void testAdvanceBall() {
        var pong = new Pong();
        var initialBallPos = pong.state.ball.vel;

        pong.update(Collections.emptyList());

        assertNotEquals(initialBallPos, pong.state.ball.pos);
    }

    @Test
    public void testGoalRight() {
        var pong = new Pong(new GameState(
                new Vec2D(Pong.W - 1, Pong.H / 2.0),
                new Vec2D(1, 0),
                new double[]{Pong.H, Pong.H}, // make sure the paddle doesn't catch the ball
                new int[]{0, 0}
        ));

        pong.update(Collections.emptyList());

        assertEquals(1, pong.state.getScore(Side.LEFT));
        assertEquals(0, pong.state.getScore(Side.RIGHT));
        assertEquals(new Vec2D(Pong.W / 2.0, Pong.H / 2.0), pong.state.ball.pos);
        assertNotEquals(Vec2D.ZERO, pong.state.ball.vel);
    }

    // TODO: falta testear:
    // - que se puede hacer gol a la izquierda
    // - que podemos mover las paletas
    // - que podemos resetear el juego
    // - que la pelota choca con las paletas
}
