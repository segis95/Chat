import java.nio.ByteBuffer;


public class Message {

	
	String from;
	String to;
	ByteBuffer bbuf;
	
	Message(String f, String t, ByteBuffer b){
		from = f;
		to = t; 
		bbuf = b;
	}
}
