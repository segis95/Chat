import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VisualMain extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Client client;
	String userWord = "";
	JTextField userInput = new JTextField(10);
	JButton submit = new JButton("Submit name");
	
	JTextField userInput1 = new JTextField(10);
	JButton submit1= new JButton("Connect to");
	
    JTextField textField;
	
    public VisualMain() {
    	super("Chat**Main");
    	
    	JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
    	textField = new JTextField();
        textField. setColumns(23);
        centerPanel.add(textField);
        
        
        
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // This center the window on the screen
        submit.addActionListener( (e)-> {
            submitAction();
        });
        centerPanel.add(userInput);
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        southPanel.add(submit);
        Box theBox = Box.createVerticalBox();
        theBox.add(Box.createVerticalStrut(100));
        theBox.add(centerPanel);
        theBox.add(Box.createVerticalStrut(200));
        theBox.add(southPanel);
        add(theBox);
        
        submit1.addActionListener( (e)-> {
            submitAction1();
        });
        centerPanel.add(userInput1);
        JPanel southPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        southPanel.add(submit1);
        Box theBox1 = Box.createVerticalBox();
        theBox1.add(Box.createVerticalStrut(10));
        theBox1.add(centerPanel);
        theBox1.add(Box.createVerticalStrut(20));
        theBox1.add(southPanel);
        add(theBox1);
    }
    
    
	
    private void submitAction() {
        // You can do some validation here before assign the text to the variable 
        userWord = userInput.getText();
        System.out.println(userWord);
    }
    
    private void submitAction1() {
        // You can do some validation here before assign the text to the variable 
        userWord = userInput1.getText();
        System.out.println(userWord);
    }

    public static void main(String args[]){
   	
    	VisualMain m = new VisualMain();

    	m.pack();
    	m.setLocationRelativeTo(null);
    	m.setVisible(true);
   }
}
