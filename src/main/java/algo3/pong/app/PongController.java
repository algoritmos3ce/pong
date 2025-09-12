package algo3.pong.app;

import algo3.pong.*;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Map;

public class PongController {
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
    private final Timeline updateLoop;
    private final AnimationTimer viewRenderLoop;

    public PongController(Pong pong, PongView view) {
        this.pong = pong;
        this.view = view;

        this.updateLoop = new Timeline();
        updateLoop.setCycleCount(Animation.INDEFINITE);
        updateLoop.getKeyFrames().add(new KeyFrame(Duration.millis(Pong.MS_PER_FRAME), _ -> {
            // this is executed exactly 60 times per second
            updateGame();
        }));
        updateLoop.play();

        this.viewRenderLoop = new AnimationTimer() {
            @Override public void handle(long l) {
                // this executed approximately 60 times per second
                view.render();
            }
        };
    }

    public void start() {
        updateLoop.play();
        viewRenderLoop.start();
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

    private void updateGame() {
        var controlActions = mapToActions(view.getKeysPressed(), CONTROLS);
        var commandActions = mapToActions(view.getKeysTyped(), COMMANDS);

        ArrayList<Action> allActions = new ArrayList<>(controlActions);
        allActions.addAll(commandActions);

        var event = pong.update(allActions);
        if (event != null) {
            view.playSound(event);
        }
    }
}
