package algo3.pong.app;

import algo3.pong.Event;
import algo3.pong.Pong;
import algo3.pong.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class PongView extends Group {
    private final Canvas canvas;
    private final List<View> views;
    private final SoundEffects sfx;

    // keys that are being held down
    private final Set<KeyCode> keysPressed = new HashSet<>();

    // list of keys that were typed (pressed and then released) since the last
    // call to getKeysTyped()
    private final List<String> keysTyped = new ArrayList<>();

    public PongView(Stage stage, Pong pong) {
        sfx = new SoundEffects();

        views = List.of(
                new PaddleView(pong.state.getPaddle(Side.LEFT)),
                new PaddleView(pong.state.getPaddle(Side.RIGHT)),
                new BallView(pong.state.ball),
                new ScoreView(pong.state)
        );

        canvas = new Canvas(Pong.W, Pong.H);
        getChildren().add(canvas);

        var scene = new Scene(this, canvas.getWidth(), canvas.getHeight(), Color.BLACK);

        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));
        scene.setOnKeyTyped(e -> keysTyped.add(e.getCharacter()));

        stage.setTitle("Pong");
        stage.setScene(scene);
        stage.show();
    }

    public Set<KeyCode> getKeysPressed() {
        return keysPressed;
    }

    public List<String> getKeysTyped() {
        var copy = new ArrayList<>(keysTyped);
        keysTyped.clear();
        return copy;
    }

    public void render() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (var view : views) {
            view.render(gc);
        }
    }

    public void playSound(Event event) {
        sfx.play(event);
    }
}
