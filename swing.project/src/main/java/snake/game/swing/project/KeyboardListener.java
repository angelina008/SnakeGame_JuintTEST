package snake.game.swing.project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 public class KeyboardListener extends KeyAdapter{
 	
	 	public int d1 = 0;
	 	public int d2 = 0;
	 	public int d3 = 0;
	 	public int d4 = 0;
	 	
 		public void keyPressed(KeyEvent e){
 		    switch(e.getKeyCode()){
		    	case 39:	// -> Right 
		    				//if it's not the opposite direction
		    				if(ThreadsController.directionSnake!=2) 
		    					ThreadsController.directionSnake=1;
		    					d1 = 1;  //added for testing purposes 
		    			  	break;
		    	case 38:	// -> Top
							if(ThreadsController.directionSnake!=4) 
								ThreadsController.directionSnake=3;
								d3 = 3;  //added for testing purposes 
		    				break;
		    				
		    	case 37: 	// -> Left 
							if(ThreadsController.directionSnake!=1)
								ThreadsController.directionSnake=2;
								d2 = 2;  //added for testing purposes 
		    				break;
		    				
		    	case 40:	// -> Bottom
							if(ThreadsController.directionSnake!=3)
								ThreadsController.directionSnake=4;
								d4 = 4;  //added for testing purposes 
		    				break;
		    	
		    	default: 	break;
 		    }
 		    
 		}
 	
 }
