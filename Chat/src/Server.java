import java.util.HashMap;
import java.util.Map;


public class Server {

	
	public class Address {
		
		String addr;
		int port;
		
		Address(String ad, int p)
		{
			addr = ad;
			port = p;
		}
	}
	
	Map clients;
	
	Server()
	{
		clients = new HashMap<String,Address>();
	}
	
	
	void parse_msg_type(String msg){};
	
	
	void authetification(String name){};
	void connention(String from, String to){};
	
	void transport_file(String from, String to){};
	void transport_msg(String from, String to){};
	void log_out(){};
	
	
	
	
}
