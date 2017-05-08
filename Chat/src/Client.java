import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;




public class Client {

	static Set<String> contacts;
	
	static String name;
	static String ip_server;
	static int port_server;
	
	static BlockingQueue<Message> to_send;
	static BlockingQueue<String> reception;
	
	static DatagramChannel mySocket;
	static InetSocketAddress local;
	static InetSocketAddress serverSocket;

	
	Client(String n, String ip, int port) throws IOException{
		name = n;
		ip = ip_server;
		port_server = port;
		to_send = new LinkedBlockingQueue<Message>();
		reception = new LinkedBlockingQueue<String>();
		mySocket = DatagramChannel.open();
		mySocket.bind(local);
		serverSocket = new InetSocketAddress(ip_server,port_server);
		contacts = new HashSet<String>();
	}
	

	static void reciever() throws IOException, InterruptedException{
		
		ByteBuffer bbuf = ByteBuffer.allocate(1200);
		
		String str;
	
		while(true){
			mySocket.receive(bbuf);
			bbuf.flip();
			str = StandardCharsets.UTF_8.decode(bbuf.duplicate()).toString();
			reception.put(str);
			bbuf.clear();
		}
	}
	
	static void sender() throws InterruptedException, IOException {
		
		while(true){
			Message msg = to_send.take();
			msg.bbuf.flip();
			mySocket.send(msg.bbuf, serverSocket);
		}
	}
	
	
	void msg_analiser() throws InterruptedException, UnsupportedEncodingException{
		
		String msg;
		
		while(true){
			
			msg = reception.take();
			
			if (msg.charAt(0) == 'F'){
				
			}
			
			if (msg.charAt(0) == 'M'){
				
			}
			
			if (msg.charAt(0) == 'C'){
				
				int i,j,k;
				i = msg.indexOf("#", 2);
				connection_from(msg.substring(2, i));
			}
			
			if (msg.charAt(0) == 'c'){
				//start thread...
			}
			
		}

	}; 
	
	
	void authentification() throws UnsupportedEncodingException{
		
		String s = "A#" + name + "#server#" ;
		ByteBuffer bf = ByteBuffer.allocate(1200);
		bf.put(s.getBytes("UTF-16be"));
		bf.flip();
		Message m = new Message(name, "server", bf);
		to_send.add(m);
	};
	
	void connection_from(String n) throws UnsupportedEncodingException{
		if (contacts.contains(n)){
			//Thread.currentThread().interrupt();
		}
		else{
			
			String s = "c#" + name + "#" + n + "#" ;
			ByteBuffer bf = ByteBuffer.allocate(1200);
			bf.put(s.getBytes("UTF-16be"));
			bf.flip();
			Message m = new Message(name, n, bf);
			to_send.add(m);
			
		}
		
	}
	
	void connection_to(String n){
		if (contacts.contains(n)){
			
		}
		else{

		}
	};
	
	void send_file(String nm, String file_name){}
	
	void recieve_file(String save_to){};
	
	void send_msg(String msg){};
	
	void log_out(){};
	
	
	
}
