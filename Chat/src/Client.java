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
	
	
	
	Client() throws IOException{//String n, String ip, int port
		//name = n;
		//ip = ip_server;
		//port_server = port;
		to_send = new LinkedBlockingQueue<Message>();
		reception = new LinkedBlockingQueue<String>();
		
		local = new InetSocketAddress(30014);
		mySocket = DatagramChannel.open();
		mySocket.bind(local);
		
		contacts = new HashSet<String>();
		lock = new ReentrantLock();
		

	}
	

	static void reciever() throws IOException, InterruptedException{
		
		ByteBuffer bbuf = ByteBuffer.allocate(1200);
		
		String str;
	
		while(true){
			mySocket.receive(bbuf);
			bbuf.flip();
			str = StandardCharsets.UTF_16BE.decode(bbuf.duplicate()).toString();
			System.out.println("Recieve this");
			System.out.println(str);
			reception.put(str);
			//Test.msg_analiser();
			bbuf.clear();
		}
	}
	
	static void sender() throws InterruptedException, IOException {
		
		while(true){
			while(to_send.isEmpty())
				Thread.sleep(1000);
			Message msg = to_send.take();
			
			//msg.bbuf.flip();
			//System.out.println("we send this");
			//System.out.println(StandardCharsets.UTF_16BE.decode(msg.bbuf.duplicate()).toString());
			mySocket.send(msg.bbuf.duplicate(), serverSocket);
			
			//System.out.println(msg.bbuf);
		}
	}
	
	
	
	
	void authentification() throws UnsupportedEncodingException, InterruptedException{
		
		serverSocket = new InetSocketAddress(ip_server,port_server);
		String s = new String("A#" + name + "#server#") ;
		ByteBuffer bf = ByteBuffer.allocate(1200);
		//System.out.println("This is my string: " + s);
		bf.put(s.getBytes("UTF-16be"));
		//System.out.println(bf.toString());
		bf.flip();
		//System.out.println(bf.toString());
		//System.out.println("We put : " + bf.position());
		Message m = new Message(name, "server", bf.duplicate());
		//System.out.println("this is my message" + m.bbuf.toString());
		to_send.add(m);
		
		//MainGUI.textField.setText("Connecting to server...");
		
		//lock.lock();
		//while(lock.isLocked())
		//	Thread.sleep(1000);
	};
	
	void connection_to(String n) throws UnsupportedEncodingException{
		if (contacts.contains(n)){
			//Thread.currentThread().interrupt();
		}
		else{
			
			String s = "c#" + name + "#" + n + "#" ;
			ByteBuffer bf = ByteBuffer.allocate(1200);
			bf.put(s.getBytes("UTF-16be"));
			bf.flip();
			Message m = new Message(name, n, bf.duplicate());
			to_send.add(m);
			
		}
		
	}
	
	void connection_from_me(String n) throws UnsupportedEncodingException{
		if (contacts.contains(n)){
			//Thread.currentThread().interrupt();
		}
		else{
			
			String s = "C#" + name + "#" + n + "#" ;
			ByteBuffer bf = ByteBuffer.allocate(1200);
			bf.put(s.getBytes("UTF-16be"));
			bf.flip();
			Message m = new Message(name, n, bf.duplicate());
			to_send.add(m);
		}
		
	}
	
	void send_file(String nm, String file_name){}
	
	void recieve_file(String save_to){};
	
	static void send_msg(String msg) throws UnsupportedEncodingException, InterruptedException{
		
		int i;
		i = msg.indexOf("#", 1);
		String to = msg.substring(1, i);
		String s = "M#" + name + msg;
		ByteBuffer bf = ByteBuffer.allocate(1200);
		bf.put(s.getBytes("UTF-16be"));
		bf.flip();
		Message mm = new Message(name, to, bf.duplicate() );
		to_send.put(mm);
	};
	
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
	

