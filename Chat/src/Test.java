
 
/*
 * AbsoluteLayoutDemo.java requires no other files.
 */
 
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
 
public class Test {
	
	static JLabel comm;
	
	static JButton authorisation;

	
	static JLabel lname;
	static JLabel lip;
	static JLabel lport;
	
	static JTextField tname;
	static JTextField tip;
	static JTextField tport;
	
	static JTextField tperson;
	static JLabel lperson;
	static JButton bperson;
	
	static JButton submit_authorisation;
	
	static Client client;
	
	static Map<String, Chat> chats = new HashMap<String, Chat>();
	
	
    public static void addComponentsToPane(Container pane) {
    	
        pane.setLayout(null);
 
        comm = new JLabel("Please enter you name and address of server...");
        
        lname = new JLabel("Your name:");
        lip = new JLabel("Server's IP:");
        lport = new JLabel("Server's port:");
        
        tname = new JTextField();
        tip = new JTextField();
        tport = new JTextField();
        
        
    	tperson = new JTextField();
    	lperson = new JLabel("");
    	bperson = new JButton("submit");
    	
        submit_authorisation = new JButton("Submit");
        
        JTextField name = new JTextField();
 
        pane.add(comm);
        pane.add(lname);
        pane.add(lip);
        pane.add(lport);
        pane.add(submit_authorisation);
        pane.add(tname);
        pane.add(tip);
        pane.add(tport);
        pane.add(lperson);
        pane.add(bperson);
        pane.add(tperson);
        
        Insets insets = pane.getInsets();
        
        Dimension size = lperson.getPreferredSize();
        lperson.setBounds( 30 + insets.left, 200 + insets.top,
                     size.width + 200, size.height + 20);
        
        size = tperson.getPreferredSize();
        tperson.setBounds(10 + insets.left, 230 + insets.top,
                     size.width + 100 , size.height );
        
        size = bperson.getPreferredSize();
        bperson.setBounds(120 + insets.left, 230 + insets.top,
                     size.width + 50 , size.height );
        
        size = comm.getPreferredSize();
        comm.setBounds(30 + insets.left, 10 + insets.top,
                     size.width + 20, size.height + 20);
        
        size = lname.getPreferredSize();
        lname.setBounds(10 + insets.left, 40+ insets.top,
                     size.width + 20, size.height + 20);
        
        size = lip.getPreferredSize();
        lip.setBounds(10 + insets.left, 70 + insets.top,
                     size.width + 20 , size.height + 20);
        
        size = lport.getPreferredSize();
        lport.setBounds(10 + insets.left, 100 + insets.top,
                     size.width + 20 , size.height + 20);
        
        size = tname.getPreferredSize();
        tname.setBounds(100 + insets.left, 50 + insets.top,
                     size.width + 80, size.height );
        
        size = tip.getPreferredSize();
        tip.setBounds(100 + insets.left, 80 + insets.top,
                     size.width + 80, size.height );
        
        size = tport.getPreferredSize();
        tport.setBounds(100 + insets.left, 110 + insets.top,
                     size.width + 80, size.height );
        
        size = submit_authorisation.getPreferredSize();
        submit_authorisation.setBounds(150 + insets.left, 160 + insets.top,
                     size.width + 80, size.height );
        
        submit_authorisation.setActionCommand("submit");
        bperson.setActionCommand("submit1");
        submit_authorisation.addActionListener(new MyAction());
        bperson.addActionListener(new MyAction());
        
    }
    
	static void msg_analiser() throws InterruptedException, UnsupportedEncodingException{
		
		String msg;
		
		while(true){
			
			//System.out.println(client.reception);
			while(client.reception.isEmpty())
				Thread.sleep(100);
			msg = client.reception.take();
			
			if (msg.charAt(0) == 'F'){
				
			}
			
			if (msg.charAt(0) == 'M'){
				int i = msg.indexOf("#", 2);
				int j = msg.indexOf("#", i + 1);
				String txt = msg.substring(j + 1);
				
				String s = msg.substring(2, i);
				
				String ss = chats.get(s).text.getText();
				chats.get(s).text.setText(ss + "\n" + s + ": " + txt);
				
			}
			
			if (msg.charAt(0) == 'C'){
				
				int i,j,k;
				i = msg.indexOf("#", 2);
				String s = msg.substring(2, i);
				client.connection_to(s);
				if (!chats.containsKey(s)){
					Chat ch = new Chat(s);
					chats.put(s, ch);
					ch.setDaemon(true);
					ch.run();
				}
				
			}
			
			if (msg.charAt(0) == 'c'){
				int i = msg.indexOf("#", 2);
				String s = msg.substring(2, i);
				if (!chats.containsKey(s)){
					Chat ch = new Chat(s);
					chats.put(s, ch);
					ch.setDaemon(true);
				}
				
			}
			
			if (msg.charAt(0) == 'a'){
				//client.lock.unlock();
				comm.setText("You are now connected to the server...");
				lperson.setText("Please choose your contacts...");
			}
			
		}

	}; 
    
    public static class  MyAction implements ActionListener 
    {
    	public void actionPerformed(ActionEvent e) {
            String c = e.getActionCommand();
            if (c == "submit")
            {
            	String n = tname.getText();
            	//tname.setText("");
            	String ip = tip.getText();
            	//tip.setText("");
            	String p = tport.getText();
            	//tport.setText("");
            	
            	client.name = n;
            	client.port_server = Integer.parseInt(p);
            	client.ip_server = ip;
            	
            	comm.setText("Connecting to server...");
            	
            	Thread t1 = new Thread(() -> {
        			try {
        				Client.reciever();
        			} catch (IOException | InterruptedException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		});
            	t1.start();
            	
            	Thread t2 = new Thread(() -> {
        			try {
        				Client.sender();
        			} catch (InterruptedException | IOException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		});
            	
            	t2.start();
            	
            	Thread t3 = new Thread(() -> {
        			try {
        				msg_analiser();
        			} catch (UnsupportedEncodingException | InterruptedException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		});
            	t3.start();
            	//client.lock.lock();
            	
            	try {
					client.authentification();
				} catch (UnsupportedEncodingException | InterruptedException e1) {
					e1.printStackTrace();
				}
            	
            	
            	
            }
            else if (c == "submit1")
            {
            	
            	String s = tperson.getText();
            	tperson.setText("");
            	try {
					client.connection_from_me(s);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	Chat ch = new Chat(s);
            	chats.put(s, ch);
            	ch.run();
            	
            	//s += c;
            	//textField.setText(calculator.castSS(s));
            	//s = "";
            }
            else
            {
            	//s += c;
            	//textField.setText(s);
            }
        }
    };
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * @throws IOException 
     */
    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
    	client = new Client();
 
        JFrame frame = new JFrame("HappyNewChat!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
 
        //Size and display the window.
        Insets insets = frame.getInsets();
        frame.setSize(500 + insets.left + insets.right,
                      500 + insets.top + insets.bottom);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) throws IOException {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	
    	Thread t = new Thread(() -> {
			try {
				createAndShowGUI();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    	t.start();
    	

    	
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
        
        
    }
}