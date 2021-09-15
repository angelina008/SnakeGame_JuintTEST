package snake.game.swing.project;

public class KeyException extends Exception {

	/**
	 * This exception is created for testing KeyboardListener class!
	 */
	private static final long serialVersionUID = 1L;
	public KeyException () {
		super("KeyboardListener class and keyPressed method are NOT working good!");
	}

}
