package music;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

import ddf.minim.Minim;

/**
 * Beispiel-Programm zum verwenden der Minim Sound Bibliothek
 * 
 * http://code.compartmental.net/tools/minim/
 * 
 * Damit das Beispiel funktioniert, müssen Sie das Minim-Projekt in eclipse
 * klonen (im gleichen Workspace). Sie arbeiten aber sonst einfach in Ihrem
 * eigenen Projekt:
 * 
 * Git Perspective -> Clone Repo -> URL = https://github.com/ddf/Minim.git ->
 * [X] "Import all existing eclipse projects after cloning"
 * 
 * Legen Sie eine test.mp3 Datei in _Ihrem_ Projekt um zu testen ob Sie alles
 * richtig gemacht haben
 * 
 * @author florian
 *
 */
public class Main {

	public static class MinimHelper {
		public String sketchPath(String fileName) {
			return Paths.get(fileName).toAbsolutePath().toString();
		}

		public InputStream createInput(String fileName) throws FileNotFoundException {
			return new FileInputStream(sketchPath(fileName));
		}
	}

	public static void main(String[] args) {
		Minim m = new Minim(new MinimHelper());

		m.loadFile("Resources/music/level1.mp3").play();
	}
}