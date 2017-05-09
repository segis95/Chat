import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Chat extends Thread {

	String name;
    JTextField write;
	JTextPane text;
	JButton enter;
	
	JScrollPane sp; 
	Chat(String n){
		name = n;
		
	}
	
	 public void addComponentsToPane(Container pane) {
		 pane.setLayout(null);
		 
		 write = new JTextField();
		 text = new JTextPane();
		 enter = new JButton("Enter");
		 
		 JScrollPane sp = new JScrollPane(text);
		 
		 pane.add( sp );
		 pane.add(write);
		 pane.add(text);
		 pane.add(enter);
		 
		 Insets insets = pane.getInsets();
	        
        Dimension size = text.getPreferredSize();
        text.setBounds( 10 + insets.left,  insets.top,
                     size.width + 300, size.height + 300);
        
        size = sp.getPreferredSize();
        sp.setBounds(350 +  insets.left, insets.top + 20,
                     size.width + 10 , size.height + 290);
        
        //sp. getVerticalScrollBar();
        
        
        
        size = enter.getPreferredSize();
        enter.setBounds(330 +  insets.left, 320 + insets.top,
                     size.width , size.height);
        
        size = write.getPreferredSize();
        write.setBounds( 10 + insets.left, 320 + insets.top,
                     size.width + 300, size.height + 20);
	 
        enter.setActionCommand("submit");
        enter.addActionListener(new MyAction(this));
	 }
		 
	 public class  MyAction implements ActionListener 
	    {
		 	Chat ch;
		 	
		 	MyAction(Chat c){
		 		
		 		ch = c;
		 	};
	    	public void actionPerformed(ActionEvent e) {
	            String c = e.getActionCommand();
	            if (c == "submit")
	            {
	            	String s = ch.write.getText() ;
	            	ch.write.setText("");
	            	String ss = ch.text.getText();
	            	ch.text.setText(ss + '\n' + "Me: " + s);
	            	try {
						Client.send_msg("#" + name + "#" + s);
					} catch (UnsupportedEncodingException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	    	}
	    }
	 
	   public void run() {
	        //Create and set up the window.
	    	
		 	
	        JFrame frame = new JFrame("Chat : " + this.name);
	        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );//EXIT_ON_CLOSE
	 
	        //Set up the content pane.
	        addComponentsToPane(frame.getContentPane());
	 
	        //Size and display the window.
	        Insets insets = frame.getInsets();
	        frame.setSize(500 + insets.left + insets.right,
	                      500 + insets.top + insets.bottom);
	        frame.setVisible(true);
	    }	 
		 
	 		 
}
