package snake.game.swing.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TestingBasicClasses extends Assert {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private SquarePanel panel;
	private DataOfSquare square;
	private Tuple tuple;

	
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outputStreamCaptor));
		this.panel = new SquarePanel(Color.blue);
		this.square = new DataOfSquare(0);
		this.tuple = new Tuple(3,2);

	}
 

	@After
	public void tearDown() throws Exception {
		System.setOut(standardOut);
	}
	///////DataOfSquare Class////////////////////////////
	@Test
	public void test_DataOfSquare_Valid_0() { //testing the setUp
		assertThat(square.C.get(0)).isEqualTo(Color.darkGray);
	}
	@Test
	public void test_DataOfSquare_Valid_1() { //testing the setUp
		assertThat(square.C.get(1)).isEqualTo(Color.BLUE);
	}
	@Test
	public void test_DataOfSquare_Valid_2() { //testing the setUp
		assertThat(square.C.get(2)).isEqualTo(Color.white);
	}
	
	@Test
	public void test_DataOfSquare_Invalid() { //testing the invalid approach
		assertThatThrownBy(() -> {
		    square.C.get(3);
		}).isInstanceOf(IndexOutOfBoundsException.class)
		  .hasMessageContaining("Index: 3, Size: 3");
	}
	
	@Test
	public void testDataOfSquare_lightMeUp_Valid_0() { //testing the method
		square.lightMeUp(2);
		assertThat(square.square.getBackground()).isEqualTo(Color.white);
	}
	@Test
	public void testDataOfSquare_lightMeUp_Valid_1() { //testing the method
		square.lightMeUp(2);
		assertThat(square.square.getBackground()).isEqualTo(Color.white);
	}
	
	@Test
	public void testDataOfSquare_lightMeUp_Valid_2() {//testing the method
		square.lightMeUp(2);
		assertThat(square.square.getBackground()).isEqualTo(Color.white);
	}
	
	@Test
	public void testDataOfSquare_lightMeUp_InValid_more() { //testing the invalid approach for method 
		assertThatThrownBy(() -> {
			square.lightMeUp(3);
		}).isInstanceOf(IndexOutOfBoundsException.class)
		  .hasMessageContaining("Index: 3, Size: 3");
	}
	
	@Test
	public void testDataOfSquare_lightMeUp_InValid_less() { //testing the invalid approach for method 
		assertThatThrownBy(() -> {
			square.lightMeUp(-1);
		}).isInstanceOf(IndexOutOfBoundsException.class)
		  .hasMessageContaining("-1");
	}
	/*-----------------------------------------------------------------------------------------------*/
	////////////SquarePanel Class//////////////////////////
	/*
	 * I wanted to add swing testing but there weren't any swing elements
	 * besides the window and the panel array(simple colored squares) 
	 * 
	 * */
	@Test
	public void test_SquarePanel_SetUp() { //testing the constructor/the setUp
		assertThat(panel.getBackground()).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void test_SquarePanel_ChangeColor() {  //testing the method/is the color changed
		panel.ChangeColor(Color.BLACK);
		assertThat(panel.getBackground()).isNotEqualTo(Color.BLUE);
	}
	/*-----------------------------------------------------------------------------------------------*/
	/////////Tuple Class////////////////////////////////////
	@Test
	public void testGet_X() { //testing the setUp
		assertThat(tuple.getX()).isEqualTo(3);
	}
	
	@Test
	public void testGet_Y() { //testing the setUp
		assertThat(tuple.getY()).isEqualTo(2);
	}
	
	@Test
	public void testGet_Yf() { //testing the setUp -> isn't set up
		assertThat(tuple.getYf()).isEqualTo(0);
	}
	
	@Test
	public void testGet_Xf() { //testing the setUp -> isn't set up
		assertThat(tuple.getXf()).isEqualTo(0);
	}
	
	@Test
	public void testGet_Yf_Y() { //testing if Yf and Y are same
		assertThat(tuple.getYf()).isNotEqualTo(tuple.getY());
	}
	
	@Test
	public void testGet_Xf_X() { //testing if Xf and X are same
		assertThat(tuple.getXf()).isNotEqualTo(tuple.getX());
	}
	
	@Test
	public void testChangeData_X_notY_checkX() { //change only X and check if X is changed
		tuple.ChangeData(5, 2);
		assertThat(tuple.getX()).isNotEqualTo(3);
	}
	@Test
	public void testChangeData_X_notY_checkY() {//change only X and check if Y is not changed
		tuple.ChangeData(5, 2);
		assertThat(tuple.getY()).isEqualTo(2);
	}
	
	@Test
	public void testChangeData_Y_notX_checkX() { //change only Y and check if X is not changed
		tuple.ChangeData(3, 6);
		assertThat(tuple.getX()).isEqualTo(3);
	}
	
	@Test
	public void testChangeData_Y_notX_checkY() { //change only Y and check if Y is changed
		tuple.ChangeData(3, 6);
		assertThat(tuple.getY()).isNotEqualTo(2);
	}
	
	@Test
	public void testChangeData_X_Y_checkX() { //change both X and Y and check if X is changed
		tuple.ChangeData(5, 6);
		assertThat(tuple.getX()).isNotEqualTo(3);
	}
	
	@Test
	public void testChangeData_X_Y_checkY() { //change both X and Y and check if X is changed
		tuple.ChangeData(5, 6);
		assertThat(tuple.getY()).isNotEqualTo(2);
	}
	/*---------------------------------------------------------------------------------------*/
}
