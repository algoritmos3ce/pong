package algo3.pong.app;

import algo3.pong.*;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Map;

public class PongController extends AnimationTimer {
    final long NANOSECONDS_PER_FRAME = Pong.MS_PER_FRAME * 1_000_000;

    // Actions that are fired continuously as long as the keys are pressed
    final static Map<KeyCode, Action> CONTROLS = Map.of(
            KeyCode.Q, new ActionMovePaddle(Side.LEFT, -1),
            KeyCode.A, new ActionMovePaddle(Side.LEFT, +1),
            KeyCode.UP, new ActionMovePaddle(Side.RIGHT, -1),
            KeyCode.DOWN, new ActionMovePaddle(Side.RIGHT, +1)
    );

    // Actions that are fired once when the key is pressed and then released
    final static Map<String, Action> COMMANDS = Map.of(
            "r", new ActionReset()
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

        var controlActions = mapToActions(view.getKeysPressed(), CONTROLS);

        // make sure we update the physics exactly 60 times per second
        if (last == 0) {
            last = now;
        }
        long dt = now - last;
        while (dt > NANOSECONDS_PER_FRAME) {
            dt -= NANOSECONDS_PER_FRAME;
            var commandActions = mapToActions(view.getKeysTyped(), COMMANDS);

            ArrayList<Action> allActions = new ArrayList<>(controlActions);
            allActions.addAll(commandActions);

            var event = pong.update(allActions);
            if (event != null) {
                view.playSound(event);
            }
            last += NANOSECONDS_PER_FRAME;
        }
    }

    private <T> ArrayList<Action> mapToActions(Iterable<T> keys, Map<T, Action> map) {
        var actions = new ArrayList<Action>();
        for (var k : keys) {
            var action = map.get(k);
            if (action != null) {
                actions.add(action);
            }
        }
        return actions;
    }
}
