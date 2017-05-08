
public class TestThread {

	static void myfun(){
		System.out.println("HelloTestThread");
		
	}
	
	
	public static void main(String args[]){
		Thread t = new Thread(TestThread::myfun);
		t.run();
		
		
	}
}
