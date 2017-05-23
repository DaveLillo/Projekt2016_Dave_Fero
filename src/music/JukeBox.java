package music;

import java.io.FileNotFoundException;
import java.io.InputStream;

import ddf.minim.Minim;
import gameState.MenuState;

public class JukeBox {

	private static Minim m;

	public static class MinimHelper {
		public String sketchPath(String fileName) {
			return fileName;
		}

		public InputStream createInput(String fileName) throws FileNotFoundException {
			return JukeBox.class.getResourceAsStream(sketchPath(fileName));
		}
	}

	public static void init() {
		m = new Minim(new MinimHelper());
	}

	public static void play(String path) {
		if (MenuState.soundOptions) {
			m.loadFile(path).loop();
		}
	}

	public static void playOnce(String path) {
		if (MenuState.soundOptions) {
			m.loadFile(path).play();
		}
	}

	public static void stop() {
		m.stop();
	}
}
