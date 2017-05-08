import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.TextField;

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
	TextField userInput = new TextField(10);
	TextField userInput1 = new TextField(10);
	Button submit = new Button("Submit name");
	Button submit1= new Button("Connect to");
    TextField textField = new TextField();
    Button myButton = new Button("OK");
    
    
    public VisualMain() {


//        ...//In the main method:
//        Insets insets = frame.getInsets();
//        frame.setSize(300 + insets.left + insets.right,
//                      125 + insets.top + insets.bottom);
//        //myButton.setLocation(50,50);
//        //myButton.setSize(50,50);
//        //panel.add(myButton);

        


//    	textField.setLocation(25, 50);
//    	textField.setSize(30, 40);
       
//        
//        submit.addActionListener( (e)-> {
//            submitAction();
//        });
//        
//        centerPanel.add(userInput);
        //submit.setLo (30,30,30,30);
        //submit.setLayout(null);

        //centerPanel.add(submit);
//        submit1.addActionListener( (e)-> {
//            submitAction1();
//        });
//        centerPanel.add(userInput1);
//        centerPanel.add(submit1);

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

//    public static void main(String args[]){
//   	
//    	VisualMain m = new VisualMain();
//
//    	m.pack();
//    	m.setLocationRelativeTo(null);
//    	m.setVisible(true);
//   }
}
