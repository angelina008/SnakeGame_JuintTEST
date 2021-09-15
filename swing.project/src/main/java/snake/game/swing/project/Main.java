package snake.game.swing.project;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main {
	static Window f1; //had to move it here for testing purposes

	public static boolean upKey() { //added for testing purposes 
		KeyboardListener key = new KeyboardListener();
		KeyEvent k = new KeyEvent( f1, 401, System.currentTimeMillis(), 0, 38, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_STANDARD);
		key.keyPressed(k);
		//System.out.print(key.d3); 
		if (key.d3 == 3)
			return true;
		else return false;
	}
	public static boolean downKey() { //added for testing purposes 
		KeyboardListener key = new KeyboardListener();
		KeyEvent k = new KeyEvent( f1, 401, System.currentTimeMillis(), 0, 40, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_STANDARD);
		key.keyPressed(k);
		//System.out.print(key.d4);
		if (key.d4 == 4)
			return true;
		else return false;
	}
	public static boolean rightKey() { //added for testing purposes 
		KeyboardListener key = new KeyboardListener();
		KeyEvent k = new KeyEvent( f1, 401, System.currentTimeMillis(), 0, 39, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_STANDARD);
		key.keyPressed(k);
		//System.out.print(key.d1);
		if (key.d1 == 1)
			return true; 
		else return false;
	}
	public static boolean leftKey() { //added for testing purposes 
		KeyboardListener key = new KeyboardListener();
		KeyEvent k = new KeyEvent( f1, 401, System.currentTimeMillis(), 0, 37, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_STANDARD);
		key.keyPressed(k);
		//System.out.print(key.d2);
		if (key.d2 == 2)
			return true;
		else return false;
	}
	public static void main(String[] args) throws KeyException {

		//Creating the window with all its awesome snaky features
		f1= new Window();
		
		//Setting up the window settings
		f1.setTitle("Snake");
		f1.setSize(300,300);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		
		
		/*
		 * I cannot stimulate KeyEvent in TestClass because KeyEvent constructor won't work 
		 * with Fixture, I can stimulate keyPress with Robot but I wanted to test the Class
		 * KeyboardListener and not the keys, so I created KeyEvents for all the arrow keys
		 * and did it with simple if-else code in main() with custom exception
		 * */
		if(upKey()==true && leftKey()==true && downKey()==true && rightKey()==true) {}	
		else throw new KeyException();
		
		
		
	}
}
