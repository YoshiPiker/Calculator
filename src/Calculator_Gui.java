import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Calculator_Gui extends JFrame implements ActionListener,
		KeyListener {
	// 20 buttons needed
	boolean reset = false;
	JTextField output, input;
	ArrayList<JButton> buttons_list = new ArrayList<JButton>();
	Equation math;
	String[] button_labels = { "7", "8", "9", "Del", "C", "4", "5", "6", "(",
			")", "1", "2", "3", "*", "/", ".", "0", "-", "+", "=" };

	public Calculator_Gui() {
		create_board();
	}

	public void create_board() {
		JPanel base = new JPanel(new BorderLayout(2, 1));
		base.setBackground(Color.WHITE);
		base.addKeyListener(this);
		base.setFocusable(true);
		getContentPane().add(base);

		Container text_box = new Container();
		text_box.setLayout(new GridLayout(2, 0));
		
		output = new JTextField(30);
		output.setFont(new Font("Arial", Font.PLAIN, 24));
		output.setHorizontalAlignment(JTextField.RIGHT);
		output.setEditable(false);
		output.addKeyListener(this);
		text_box.add(output);

		input = new JTextField(30);
		input.setFont(new Font("Arial", Font.PLAIN, 24));
		input.setHorizontalAlignment(JTextField.RIGHT);
		input.setEditable(false);
		input.addKeyListener(this);
		text_box.add(input);

		Container board = new Container();
		board.setLayout(new GridLayout(0, 5, 1, 1));

		JButton button;
		for (int idx = 0; idx < button_labels.length; idx++) {
			button = new JButton(button_labels[idx]);
			button.setFont(new Font("Arial", Font.PLAIN, 18));
			button.addActionListener(this);
			button.addKeyListener(this);
			buttons_list.add(button);
			
			board.add(button);
		}

		base.add(text_box, BorderLayout.NORTH);
		base.add(board, BorderLayout.CENTER);
		setTitle("Simple example");
		setSize(350, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void pressbutton(String button_text) {
		if (button_text.equals("C")) {
			input.setText("");
			output.setText("");
		} else if (button_text.equals("Del")) {
			String text = input.getText();
			text = text.substring(0, text.length() - 1);
			input.setText(text);
		} else if (button_text.equals("=")) {
			math = new Equation();
			output.setText(input.getText());
			input.setText(math.solve(input.getText()));
			reset = true;
		} else {
			if (reset == true) {
				input.setText("");
				output.setText("");
				reset = false;
			}
			input.setText(input.getText() + button_text);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String button_text = ((JButton) e.getSource()).getText();
		pressbutton(button_text);

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			pressbutton("Del");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT_PARENTHESIS
				|| (e.isShiftDown()&&e.getKeyCode() == KeyEvent.VK_0)){
			pressbutton(")");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT_PARENTHESIS|| (e.isShiftDown()&&e.getKeyCode() == KeyEvent.VK_9)) {
			pressbutton("(");
		} else if (e.getKeyCode() == KeyEvent.VK_DECIMAL
				|| e.getKeyCode() == KeyEvent.VK_PERIOD) {
			pressbutton(".");
		} else if (e.getKeyCode() == KeyEvent.VK_DIVIDE 
				|| e.getKeyCode() == KeyEvent.VK_SLASH) {
			pressbutton("/");
		} else if (e.getKeyCode() == KeyEvent.VK_ADD
				|| e.getKeyCode() == KeyEvent.VK_PLUS
				|| (e.isShiftDown()&&e.getKeyCode() == KeyEvent.VK_EQUALS)) {
			pressbutton("+");
		} else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY
				|| e.getKeyCode() == KeyEvent.VK_ASTERISK
				|| (e.isShiftDown()&&e.getKeyCode() == KeyEvent.VK_8)) {
			pressbutton("*");
		} else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT
				|| e.getKeyCode() == KeyEvent.VK_MINUS) {
			pressbutton("-");
		} else if (e.getKeyCode() == KeyEvent.VK_EQUALS
				|| e.getKeyCode() == KeyEvent.VK_ENTER) {
			pressbutton("=");
		} else if (e.getKeyCode() == KeyEvent.VK_0
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
			pressbutton("0");
		} else if (e.getKeyCode() == KeyEvent.VK_1
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
			pressbutton("1");
		} else if (e.getKeyCode() == KeyEvent.VK_2
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
			pressbutton("2");
		} else if (e.getKeyCode() == KeyEvent.VK_3
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
			pressbutton("3");
		} else if (e.getKeyCode() == KeyEvent.VK_4
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
			pressbutton("4");
		} else if (e.getKeyCode() == KeyEvent.VK_5
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
			pressbutton("5");
		} else if (e.getKeyCode() == KeyEvent.VK_6
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
			pressbutton("6");
		} else if (e.getKeyCode() == KeyEvent.VK_7
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
			pressbutton("7");
		} else if (e.getKeyCode() == KeyEvent.VK_8
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
			pressbutton("8");
		} else if (e.getKeyCode() == KeyEvent.VK_9
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
			pressbutton("9");
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
