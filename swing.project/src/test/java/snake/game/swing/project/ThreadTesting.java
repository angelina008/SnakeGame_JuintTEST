package snake.game.swing.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class ThreadTesting {

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private Tuple tuple;
	private ThreadsController obj;
	ArrayList<Thread> threads;

	@Before
	public void setUp() throws Exception {
		this.tuple = new Tuple(3,3);
		this.threads = new ArrayList<>();
		System.setOut(new PrintStream(outputStreamCaptor));
		this.obj = new ThreadsController(tuple);
		obj.start();
		obj.join();
	}
	@After
	public void tearDown() throws Exception {System.setOut(standardOut);}
	
	@Test
    public void StopTheGameTest() {  
		
		final String newLine = System.lineSeparator();
		obj.stopTheGame(); //the method is actually called and test won't load
						  //because of the pauser() when I comment that part the test is running
        assertThat(outputStreamCaptor.toString()).isEqualTo("Head position changed, right!"+ newLine +"COLISION! \n" + newLine);
        
    }
	/*
	 * I had to use "Head position changed, right!" for every test with standardOut
	 * because when the thread is started there is starting direction and that's right
	 * 
	 * */
	///////////Predikat1////////////////////////////////
	@Test
    public void CollisionTest_TT_Prediakt1() {  //works only with pauser() part commented 
		
		final String newLine = System.lineSeparator();
		Tuple posCritique = obj.positions.get(obj.positions.size()-1);
			
			 boolean biteItself = posCritique.getX()==obj.positions.get(1).getX() && posCritique.getY()==obj.positions.get(1).getY();
			 if(biteItself){
				obj.stopTheGame();
			 } 
			 
			 String c = "Head position changed, right!"+ newLine +"COLISION! \n" +newLine;
        assertThat(outputStreamCaptor.toString()).isEqualTo(c);
        
    }
	@Test
    public void CollisionTest_TF_predikat1() {  //works only with pauser() part commented 
		
		final String newLine = System.lineSeparator();
		Tuple posCritique = obj.positions.get(obj.positions.size()-1);
			
			 boolean biteItself = posCritique.getX()==obj.positions.get(1).getX() && posCritique.getY()==obj.positions.get(0).getY()+1;
			 if(biteItself){                                                           ////added +1 here because it turns out to be same value
				obj.stopTheGame();													///and I need it to be false and I can't access other elements
			 } 
			 
			 String c = "Head position changed, right!"+ newLine +"COLISION! \n" +newLine;
        assertThat(outputStreamCaptor.toString()).isNotEqualTo(c);
        
    }
	@Test
    public void CollisionTest_FT_predikat1() {  //works only with pauser() part commented 
		
		final String newLine = System.lineSeparator();
		Tuple posCritique = obj.positions.get(obj.positions.size()-1);
			
			 boolean biteItself = posCritique.getX()==obj.positions.get(0).getX() && posCritique.getY()==obj.positions.get(1).getY();
			 if(biteItself){
				obj.stopTheGame();
			 } 
			 
			 String c = "Head position changed, right!"+ newLine +"COLISION! \n" +newLine;
        assertThat(outputStreamCaptor.toString()).isNotEqualTo(c);
        
    }
	////////////Predikat2///////////////////////
	@Test
    public void CollisionTest_TT_predikat2() {   
		
		final String newLine = System.lineSeparator();
		Tuple posCritique = new Tuple(19,19);
			
			 boolean eatingFood = posCritique.getX()==obj.foodPosition.y && posCritique.getY()==obj.foodPosition.x;
			 if(eatingFood){
				 System.out.print("Yummy!");
			 }
			
			 String c ="Head position changed, right!"+ newLine + "Yummy!";
        assertThat(outputStreamCaptor.toString()).isEqualTo(c);
        
    }
	@Test
    public void CollisionTest_TF_predikat2() {  
		
		final String newLine = System.lineSeparator();
		Tuple posCritique = new Tuple(19, obj.positions.get(obj.positions.size()-1).getY());
			
			 boolean eatingFood = posCritique.getX()==obj.foodPosition.y && posCritique.getY()==obj.foodPosition.x;
			 if(eatingFood){
				 System.out.print("Yummy!");
			 }
			
			 String c = "Yummy!";
        assertThat(outputStreamCaptor.toString()).isNotEqualTo(c);
        
    }
	@Test
    public void CollisionTest_FT_predikat2() {  
		
		Tuple posCritique = new Tuple(obj.positions.get(obj.positions.size()-1).getY(), 19);
		
		 boolean eatingFood = posCritique.getX()==obj.foodPosition.y && posCritique.getY()==obj.foodPosition.x;
		 if(eatingFood){
			 System.out.print("Yummy!");
		 }
		
		 String c = "Yummy!";
   assertThat(outputStreamCaptor.toString()).isNotEqualTo(c);
        
    }
	
	///////////Predikat3/////////////////////////
	@Test
	public void testValNot_TT_predikat3() {
		Tuple p ;
		Tuple p0;
		 int ranX= obj.positions.get(0).getX();
		 int ranY= obj.positions.get(0).getY();
		 p=new Tuple(ranX,ranY);
		 p0 = p;
		 for(int i = 0;i<=obj.positions.size()-1;i++){
			 if(p.getY()==obj.positions.get(i).getX() && p.getX()==obj.positions.get(i).getY()){
				 ranX= 0 + (int)(Math.random()*19); 
				 ranY= 0 + (int)(Math.random()*19); 
				 p=new Tuple(ranX,ranY);
				 i=0;
			 }
		 }
		 assertThat(p).isNotEqualTo(p0);
	}
	
	@Test
	public void testValNot_TF_predikat3() { //because it's random sometimes it can fail
		Tuple p ;							//just run it again
		Tuple p0;
		int ranX= obj.positions.get(0).getX(); 
		 int ranY= 0 + (int)(Math.random()*19);
		 p=new Tuple(ranX,ranY);
		 p0 = p;
		 for(int i = 0;i<=obj.positions.size()-1;i++){
			 if(p.getY()==obj.positions.get(i).getX() && p.getX()==obj.positions.get(i).getY()){
				 ranX= 0 + (int)(Math.random()*19); 
				 ranY= 0 + (int)(Math.random()*19); 
				 p=new Tuple(ranX,ranY);
				 i=0;
			 }
		 }
		 assertThat(p).isEqualTo(p0);
	}
	
	@Test
	public void testValNot_FT_predikat3() {//because it's random sometimes it can fail
		Tuple p ;						  //just run it again
		Tuple p0; 
		int ranX= 0 + (int)(Math.random()*19);
		int ranY= obj.positions.get(0).getY(); 
		
		 p=new Tuple(ranX,ranY);
		 p0 = p;
		 for(int i = 0;i<=obj.positions.size()-1;i++){
			 if(p.getY()==obj.positions.get(i).getX() && p.getX()==obj.positions.get(i).getY()){
				 ranX= 0 + (int)(Math.random()*19); 
				 ranY= 0 + (int)(Math.random()*19); 
				 p=new Tuple(ranX,ranY);
				 i=0;
			 }
		 }
		 assertThat(p).isEqualTo(p0);
	}
	/////////////////NullPointerException Thrower/////////////////////////
	
	@Test
	public void test_SpawnFood() { // there seems to be a problem threading 
			assertThatThrownBy(() -> {
				obj.spawnFood(new Tuple(1,1));
			}).isInstanceOf(NullPointerException.class);
	}
	@Test
	public void test_moveExterne() { // there seems to be a problem threading 
			assertThatThrownBy(() -> {
				obj.moveExterne();
			}).isInstanceOf(NullPointerException.class);
	}
	////////////////////////////////////////////////////////////////////
	@Test
	public void test_deleteTail() { // there seems to be a problem threading 
		obj.deleteTail();
		final String newLine = System.lineSeparator();
		String c = "Head position changed, right!"+ newLine + "Tail is deleted!" + newLine;
		   assertThat(outputStreamCaptor.toString()).isEqualTo(c);
	}
	///////////////MoveInterne////////////////////////////////////	
	@Test
	public void test_moveInterne_4() {
		obj.moveInterne(4);
		final String newLine = System.lineSeparator();
		String c = "Head position changed, right!"+ newLine + "Head position changed, down!" + newLine;
		   assertThat(outputStreamCaptor.toString()).isEqualTo(c);
	}
	@Test
	public void test_moveInterne_3() {
		obj.moveInterne(3);
		final String newLine = System.lineSeparator();
		String c = "Head position changed, right!"+ newLine + "Head position changed, up!" + newLine;
		   assertThat(outputStreamCaptor.toString()).isEqualTo(c);
	}
	@Test
	public void test_moveInterne_2() {
		obj.moveInterne(2);
		final String newLine = System.lineSeparator();
		String c = "Head position changed, right!"+ newLine + "Head position changed, left!" + newLine;
		   assertThat(outputStreamCaptor.toString()).isEqualTo(c);
	}
	@Test
	public void test_moveInterne_1() {
		obj.moveInterne(1);
		final String newLine = System.lineSeparator();
		String c = "Head position changed, right!"+ newLine + "Head position changed, right!" + newLine;
		   assertThat(outputStreamCaptor.toString()).isEqualTo(c);
	}
	
	////////ThreadTests//////////////////////
	@Test
	public void threadTest() throws Exception { //testing if it actually starts 
	
		Thread thread = new ThreadsController(
			      new Tuple(10,10));
			    thread.start();
			    thread.join(1000);
	}
	
	@Test
	public void ThreadArray_test() throws InterruptedException { //it only gets to the second thread
		
			assertThat(obj.positions.size()).isEqualTo(2);
		
	}
	@Test
	public void ThreadArray_test_2() throws InterruptedException { //it only gets to the second thread
		
		for(int i = 0; i <20; i++) {
			
		Thread t = new Thread(() -> {
			assertThat(obj.positions.size()).isEqualTo(2);
		});
		
		threads.add(t);
		t.start();
		t.join();
		
		}
		assertConcurrent("Failures are found", threads, 120);
		
		
	}
	/*
	 * This code is actually not thread-safe;
	 * The swing framework itself isn't thread safe so 
	 * making the interface to be array of threads is wrong approach 
	 * 
	 * */

	void assertConcurrent(final String message, final ArrayList<? extends Thread> runnables, final int maxTimeoutSeconds) throws InterruptedException {
        final int numThreads = runnables.size();
        final List exceptions = (List) Collections.synchronizedList(new ArrayList<>());
        final ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
        try {
            final CountDownLatch allThreadsReady = new CountDownLatch(numThreads);
            final CountDownLatch afterInitBlocker = new CountDownLatch(1);
            final CountDownLatch allThreadsAreDone = new CountDownLatch(numThreads);
            for (final Runnable submittedTestRunnable : runnables) {
                threadPool.submit(() -> {
                    allThreadsReady.countDown();
                    try {
                        afterInitBlocker.await();
                        submittedTestRunnable.run();
                    } catch (final Exception e) {
                        exceptions.add(e.getMessage());
                    } finally {
                        allThreadsAreDone.countDown();
                    }
                });
            }
            assertTrue("Timeout during threads initializing threads.", 
                    allThreadsReady.await(runnables.size() * maxTimeoutSeconds, TimeUnit.MILLISECONDS));
            
            afterInitBlocker.countDown();
            assertTrue(message +" timeout! More than" + maxTimeoutSeconds + "seconds", 
                    allThreadsAreDone.await(maxTimeoutSeconds, TimeUnit.SECONDS));
        } finally {
            threadPool.shutdownNow();
        }
        assertTrue(message + "failed with errors" + exceptions, exceptions.isEmpty());
}
	
	/*
	 * the method above is copied from this link
	 * 
	 * https://www.itarray.net/splitting-and-execution-of-junit-test-in-multiple-threads/
	 * I tried testing with multiple threads
	 * 
	 * 
	 * */
}
