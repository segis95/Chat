import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {

	static DatagramChannel mySocket;
	static InetSocketAddress local;
	static Map<String, SocketAddress> clients;
		
	static BlockingQueue<Message> to_send;
	//static BlockingQueue<String> reception;
	
	static void sender() throws IOException, InterruptedException{
		
		while(true){
			while(to_send.isEmpty())
				Thread.sleep(100);
			Message msg = to_send.take();
			//msg.bbuf.flip();
			
			String str = StandardCharsets.UTF_16BE.decode(msg.bbuf.duplicate()).toString();
			System.out.println("send this");
			System.out.println(str);
			
			mySocket.send(msg.bbuf, clients.get(msg.to));
			
		}
		
		
	}
	
	static void reciever() throws IOException, InterruptedException{
		
		ByteBuffer bbuf = ByteBuffer.allocate(1200);
		
		String str;
	
		while(true){
			
			SocketAddress client_from = mySocket.receive(bbuf);
			bbuf.flip();
			str = StandardCharsets.UTF_16BE.decode(bbuf.duplicate()).toString();

			//System.out.println("Recieve this");
			//System.out.println(str);
			//reception.put(str);
			bbuf.clear();
			
			msg_analiser(client_from, str);
		}
	
	}
	
	Server() throws IOException
	{
		clients = new HashMap<String, SocketAddress>();
		mySocket = DatagramChannel.open();
		local = new InetSocketAddress(30009);
		mySocket.bind(local);
		//System.out.println(local.toString());
		mySocket.socket().setBroadcast(true);
		to_send = new LinkedBlockingQueue<Message>();
		//reception = new LinkedBlockingQueue<String>();
		
	}
	

	
	
	
	
	
	
	static void msg_analiser(SocketAddress add, String msg) throws InterruptedException, UnsupportedEncodingException{
		
		//String msg;
		
	
		
		//msg = reception.take();
		
		if (msg.length() > 0 && msg.charAt(0) == 'F'){
			
		}
		
		if (msg.length() > 0 && msg.charAt(0) == 'M'){
			
			int i,j,k;
			i = msg.indexOf("#", 2);
			j = msg.indexOf("#", i + 1);
			
			String s = msg.substring(i + 1, j);
			
			if (clients.containsKey(s)){
				ByteBuffer bf = ByteBuffer.allocate(1200);
				bf.put(msg.getBytes("UTF-16be"));
				bf.flip();
				Message ms = new Message(msg.substring(2, i), s, bf.duplicate());
				to_send.add(ms);
			}
			
		}
		
		if (msg.length() > 0 && msg.charAt(0) == 'C'){
			
			int i,j,k;
			i = msg.indexOf("#", 2);
			j = msg.indexOf("#", i + 1);
			
			String s = msg.substring(i + 1, j);
			
			if (clients.containsKey(s)){
				ByteBuffer bf = ByteBuffer.allocate(1200);
				bf.put(msg.getBytes("UTF-16be"));
				bf.flip();
				Message ms = new Message(msg.substring(2, i), s, bf.duplicate());
				to_send.add(ms);
			}
			//connection_from(msg.substring(2, i));
		}
		
		if (msg.length() > 0 && msg.charAt(0) == 'c'){
			
			int i,j,k;
			i = msg.indexOf("#", 2);
			j = msg.indexOf("#", i + 1);
			
			String s = msg.substring(i + 1, j);
			
			if (clients.containsKey(s)){
				ByteBuffer bf = ByteBuffer.allocate(1200);
				bf.put(msg.getBytes("UTF-16be"));
				bf.flip();
				Message ms = new Message(msg.substring(2, i), s, bf.duplicate());
				to_send.add(ms);
			}
		}
		
		if (msg.length() > 0 && msg.charAt(0) == 'A'){
			int i = msg.indexOf("#", 2);
			String fr = msg.substring(2, i);
			
			if (!clients.containsKey(fr))
				clients.put(fr, add);
			
			authetification(fr);
		}
		
	

	}; 
	
	
	static void authetification(String name) throws UnsupportedEncodingException{
		
		String s = "a#" + "server" + "#" + name + "#" ;
		ByteBuffer bf = ByteBuffer.allocate(1200);
		bf.put(s.getBytes("UTF-16be"));
		bf.flip();
		Message m = new Message("server", name, bf);
		to_send.add(m);
		
	};
	void connention(String from, String to){};
	
	void transport_file(String from, String to){};
	void transport_msg(String from, String to){};
	void log_out(){};
	
	public static void main(String[] args) throws IOException	{
	
		Server server = new Server();
		Thread t1 = new Thread(() -> {
			try {
				sender();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(() -> {
			try {
				reciever();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
		
		
		//System.out.println(local);
	}
	
	
}
