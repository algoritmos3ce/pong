package algo3.pong.app;

import algo3.pong.Pong;
import javafx.application.Application;
import javafx.stage.Stage;

public class PongApp extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        var pong = new Pong();
        var view = new PongView(stage, pong);
        var controller = new PongController(pong, view);
        controller.start();
    }
}