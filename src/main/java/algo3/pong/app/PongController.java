package algo3.pong.app;

import algo3.pong.*;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Map;

public class PongController extends AnimationTimer {
    final long NANOSECONDS_PER_FRAME = Pong.MS_PER_FRAME * 1_000_000;

    final static Map<KeyCode, Action> CONTROLS = Map.of(
            KeyCode.Q, new ActionMovePaddle(Side.LEFT, -1),
            KeyCode.A, new ActionMovePaddle(Side.LEFT, +1),
            KeyCode.UP, new ActionMovePaddle(Side.RIGHT, -1),
            KeyCode.DOWN, new ActionMovePaddle(Side.RIGHT, +1),
            KeyCode.R, new ActionReset()
    );

    private final Pong pong;
    private final PongView view;

    // timestamp when handle() was last called (nanoseconds)
    long last = 0;

    public PongController(Pong pong, PongView view) {
        this.pong = pong;
        this.view = view;
    }

    // this function implements javafx.AnimationTimer, and is executed approximately 60 times per second
    @Override
    public void handle(long now) {
        // update the view
        view.render();

        var actions = getActions();

        // make sure we update the physics exactly 60 times per second
        if (last == 0) {
            last = now;
        }
        long dt = now - last;
        while (dt > NANOSECONDS_PER_FRAME) {
            dt -= NANOSECONDS_PER_FRAME;
            pong.update(actions);
            last += NANOSECONDS_PER_FRAME;
        }
    }

    private ArrayList<Action> getActions() {
        var actions = new ArrayList<Action>();
        for (var k : view.getKeysPressed()) {
            var action = CONTROLS.get(k);
            if (action != null) {
                actions.add(action);
            }
        }
        return actions;
    }
}
