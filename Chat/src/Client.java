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
import java.util.concurrent.locks.ReentrantLock;




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

	static ReentrantLock lock;
	
	VisualMain MainGUI;
	
	Client() throws IOException{//String n, String ip, int port
		//name = n;
		//ip = ip_server;
		//port_server = port;
		to_send = new LinkedBlockingQueue<Message>();
		reception = new LinkedBlockingQueue<String>();
		
		local = new InetSocketAddress(30010);
		mySocket = DatagramChannel.open();
		mySocket.bind(local);
		
		contacts = new HashSet<String>();
		lock = new ReentrantLock();
		
		MainGUI = new VisualMain();
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
	
	
	
	
	void authentification() throws UnsupportedEncodingException, InterruptedException{
		
		serverSocket = new InetSocketAddress(ip_server,port_server);
		String s = "A#" + name + "#server#" ;
		ByteBuffer bf = ByteBuffer.allocate(1200);
		bf.put(s.getBytes("UTF-16be"));
		bf.flip();
		Message m = new Message(name, "server", bf);
		to_send.add(m);
		
		MainGUI.textField.setText("Connecting to server...");
		
		lock.lock();
		while(lock.isLocked())
			Thread.sleep(1000);
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
	
//	public static void main(String args[]) throws IOException{
//		
//		Client client = new Client();
//
//		client.MainGUI.pack();
//		client.MainGUI.setVisible(true);
//		client.MainGUI.setSize(500,500);
//		client.MainGUI.textField.setText("Please enter your name, server's IP and port");
//		//client.MainGUI.setResizable(false);
//		
//		
//
//		
//	}
}
	

