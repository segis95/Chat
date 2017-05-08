
 
/*
 * AbsoluteLayoutDemo.java requires no other files.
 */
 
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	
	
	static JButton submit_authorisation;
	
	static Client client;
	
	
    public static void addComponentsToPane(Container pane) {
        pane.setLayout(null);
 
        comm = new JLabel("Please enter you name and address of server...");
        
        lname = new JLabel("Your name:");
        lip = new JLabel("Server's IP:");
        lport = new JLabel("Server's port:");
        
        tname = new JTextField();
        tip = new JTextField();
        tport = new JTextField();

    	
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
        
        Insets insets = pane.getInsets();
        
        Dimension size = comm.getPreferredSize();
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
        submit_authorisation.addActionListener(new MyAction());
        
    }
    
	void msg_analiser() throws InterruptedException, UnsupportedEncodingException{
		
		String msg;
		
		while(true){
			
			msg = client.reception.take();
			
			if (msg.charAt(0) == 'F'){
				
			}
			
			if (msg.charAt(0) == 'M'){
				
			}
			
			if (msg.charAt(0) == 'C'){
				
				int i,j,k;
				i = msg.indexOf("#", 2);
				client.connection_from(msg.substring(2, i));
			}
			
			if (msg.charAt(0) == 'c'){
				//start thread...
			}
			
			if (msg.charAt(0) == 'a'){
				client.lock.unlock();
				comm.setText("You are now connected to the server...");
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
            	tname.setText("");
            	String ip = tip.getText();
            	tip.setText("");
            	String p = tport.getText();
            	tport.setText("");
            	
            	client.name = n;
            	client.port_server = Integer.parseInt(p);
            	client.ip_server = ip;
            	comm.setText("Connecting to server...");
            	try {
					client.authentification();
				} catch (UnsupportedEncodingException | InterruptedException e1) {
					e1.printStackTrace();
				}
            	
            	
            }
            else if (c == "=")
            {
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
    	System.out.println(client.local.toString());
        JFrame frame = new JFrame("AbsoluteLayoutDemo");
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
    	t.run();
    	
    	
    	
    	
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
        
        
    }
}