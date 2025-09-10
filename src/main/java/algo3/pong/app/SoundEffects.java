package algo3.pong.app;

import algo3.pong.Event;
import javafx.scene.media.AudioClip;

import java.util.EnumMap;

public class SoundEffects {
    private final EnumMap<Event, AudioClip> sfx;

    public SoundEffects() {
        // preload all sound effects
        sfx = new EnumMap<>(Event.class);
        for (Event e : Event.values()) {
            var resourcePath = getResourcePath("%s.mp3".formatted(e.name().toLowerCase()));
            var audio = new AudioClip(resourcePath);
            sfx.put(e, audio);
        }
    }

    public void play(Event e) {
        sfx.get(e).play();
    }

    private String getResourcePath(String path) {
        // any files stored in main/resources/<...> is bundled with the application,
        // and should be referenced as "/<...>"
        var resourcePath = "/%s".formatted(path);
        var resource = getClass().getResource(resourcePath);
        if (resource == null) {
            throw new RuntimeException("could not load resource '%s'".formatted(resourcePath));
        }
        return resource.toExternalForm();
    }
}
