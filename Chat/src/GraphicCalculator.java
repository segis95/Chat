import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicCalculator extends JFrame {
	Calculator calculator;
	private JTextField textField;

	
	public String s;
	public GraphicCalculator() {
		calculator = new Calculator();
		createGui();	
		s = "";
	}
	
	
	public void createGui()
	{
		JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        textField = new JTextField();
        textField.setColumns(23);
        panel.add(textField);
        

        
        JButton plus = new JButton("+");
        plus.setActionCommand("+");
        panel.add(plus);
        
        JButton minus = new JButton("-");
        minus.setActionCommand("-");
        panel.add(minus);
        
        JButton div = new JButton("/");
        div.setActionCommand("/");
        panel.add(div);
        
        JButton mul = new JButton("*");
        mul.setActionCommand("*");
        panel.add(mul);
        
        JButton eq = new JButton("=");
        eq.setActionCommand("=");
        panel.add(eq);
        
        JButton C = new JButton("C");
        C.setActionCommand("C");
        panel.add(C);
        
        JButton bl = new JButton("(");
        bl.setActionCommand("(");
        panel.add(bl);
        
        JButton br = new JButton(")");
        br.setActionCommand(")");
        panel.add(br);
        
        JButton dot = new JButton(".");
        dot.setActionCommand(".");
        panel.add(dot);
        /////////////////////////////////////
        JButton zero = new JButton("0");
        zero.setActionCommand("0");
        panel.add(zero);
        
        JButton one = new JButton("1");
        one.setActionCommand("1");
        panel.add(one);
        
        JButton two = new JButton("2");
        two.setActionCommand("2");
        panel.add(two);
        
        JButton three = new JButton("3");
        three.setActionCommand("3");
        panel.add(three);
        
        JButton four = new JButton("4");
        four.setActionCommand("4");
        panel.add(four);
        
        JButton five = new JButton("5");
        five.setActionCommand("5");
        panel.add(five);
        
        JButton six = new JButton("6");
        six.setActionCommand("6");
        panel.add(six);
        
        
        JButton seven = new JButton("7");
        seven.setActionCommand("7");
        panel.add(seven);
        
        
        JButton eight = new JButton("8");
        eight.setActionCommand("8");
        panel.add(eight);
        
        
        JButton nine = new JButton("9");
        nine.setActionCommand("9");
        panel.add(nine);
        

        
        
        
        plus.addActionListener(new MyAction());
        minus.addActionListener(new MyAction());
        div.addActionListener(new MyAction());
        mul.addActionListener(new MyAction());
        eq.addActionListener(new MyAction());
        C.addActionListener(new MyAction());
        bl.addActionListener(new MyAction());
        br.addActionListener(new MyAction());
        dot.addActionListener(new MyAction());
        //////////
        zero.addActionListener(new MyAction());
        one.addActionListener(new MyAction());
        two.addActionListener(new MyAction());
        three.addActionListener(new MyAction());
        four.addActionListener(new MyAction());
        five.addActionListener(new MyAction());
        six.addActionListener(new MyAction());
        seven.addActionListener(new MyAction());
        eight.addActionListener(new MyAction());
        nine.addActionListener(new MyAction());
        
        
       

        ActionListener actionListener;

//        plus.addActionListener(actionListener);
//        minus.addActionListener(actionListener);
        

        
        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 320));
	}
	
	
    public class  MyAction implements ActionListener 
    {
    	public void actionPerformed(ActionEvent e) {
            String c = e.getActionCommand();
            if (c == "C")
            {
            	s = "";
            	textField.setText("0.0");
            }
            else if (c == "=")
            {
            	s += c;
            	textField.setText(calculator.castSS(s));
            	s = "";
            }
            else
            {
            	s += c;
            	textField.setText(s);
            }
        }
    };
	public static void main(String[] args) {
	GraphicCalculator calc = new GraphicCalculator();
	calc.pack();
	calc.setLocationRelativeTo(null);
	calc.setVisible(true);
	
} 
	
	

	}