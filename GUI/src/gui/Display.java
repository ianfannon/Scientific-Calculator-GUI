/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Ian James Fannon
 */
public class Display implements Runnable, ActionListener {
    
    private JFrame frame;
    private Thread thread;
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton add, subtract, multiply, divide, plusNegative, squareRoot;
    private JButton dot, equals, back, clear;
    private JButton sin, cos, tan, log, PI, factorial;
    private JPanel panel;
    private JPanel panel2;
    private GridBagLayout gb;
    private GridBagConstraints gbc;
    private JTextField txtField;
    private JLabel label;
    private Thread[] threadCount;
    private Dimension d;
    private int width = 375;
    private int height = 400;
    private Border border;
    private int length;
    private int number;
    private StringBuilder _back;
    private String store;
    private int operation;
    private double numeral;
    private double answer;
    private JComboBox box;
    private String[] message; 
    private String strNumber;
    
    /**
     * A constructor for initializing the variables.
     */
    public Display() {
        numeral = 0.0;
        answer = 0.0;
        operation = 0;
        store = "";
        strNumber = "";
        length = 0;
        number = 0;
        thread = new Thread();
        thread.start();
        run();
    }
    /**
     * A method to create the calculator.
     */
    public void createDisplay() {
        frame = new JFrame();
        panel = new JPanel();
        panel2 = new JPanel();
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        txtField = new JTextField(25);
        label = new JLabel();
        d = new Dimension();
        border = BorderFactory.createEtchedBorder(Color.BLUE, Color.CYAN);
        message = new String[] {"On", "Off"};
        box = new JComboBox(message);
        
        // Calculator Buttons
        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        
        // Operations
        dot = new JButton(".");
        add = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton("\u00F7");
        plusNegative = new JButton("\u00B1");
        equals = new JButton("=");
        back = new JButton("<-");
        clear = new JButton("C");
        squareRoot = new JButton("\u221A");
        sin = new JButton("sin");
        cos = new JButton("cos");
        tan = new JButton("tan");
        log = new JButton("log");
        PI = new JButton("\u03C0");
        factorial = new JButton("\u0021");
        
        // Window
        frame.setTitle("A GUI");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().add(panel);
        panel.setLayout(new FlowLayout());
        panel.setBorder(border);
        panel2.setLayout(gb);
        frame.setBackground(Color.BLUE);
        frame.add(panel);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBorder(border);
        panel.add(panel2);
        frame.pack();
        
        gb.columnWidths = new int[] {63, 62, 62, 63};
        gb.rowHeights = new int[] {45,45,45,45,45};
        
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        box.setSelectedIndex(0);
        box.addActionListener(this);
        panel2.add(box);
        
        // The text field
        txtField.setBorder(border);
        txtField.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel2.add(txtField, gbc);
        
        // The label above the text field
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(label, gbc);
        
        // First button row start
        back.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        back.addActionListener(this);
        panel2.add(back, gbc);
        
        clear.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        clear.addActionListener(this);
        panel2.add(clear, gbc);
        
        plusNegative.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 2;
        gbc.gridy = 3;
        plusNegative.addActionListener(this);
        panel2.add(plusNegative, gbc);
        
        squareRoot.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 3;
        gbc.gridy = 3;
        squareRoot.addActionListener(this);
        panel2.add(squareRoot, gbc);
        
        sin.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 4;
        gbc.ipady = 3;
        sin.addActionListener(this);
        panel2.add(sin, gbc);
        // First button row end
        
        // Second button row start
        btn7.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        btn7.addActionListener(this);
        panel2.add(btn7, gbc);
        
        btn8.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        btn8.addActionListener(this);
        panel2.add(btn8, gbc);
        
        btn9.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 2;
        gbc.gridy = 4;
        btn9.addActionListener(this);
        panel2.add(btn9, gbc);
        
        divide.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 3;
        gbc.gridy = 4;
        divide.addActionListener(this);
        panel2.add(divide, gbc);
        
        cos.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 4;
        gbc.gridy = 4;
        cos.addActionListener(this);
        panel2.add(cos, gbc);
        // Second button row end
        
        // Thrid button row start
        btn4.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        btn4.addActionListener(this);
        panel2.add(btn4, gbc);
        
        btn5.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 5;
        btn5.addActionListener(this);
        panel2.add(btn5, gbc);
        
        btn6.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 2;
        gbc.gridy = 5;
        btn6.addActionListener(this);
        panel2.add(btn6, gbc);
        
        multiply.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 3;
        gbc.gridy = 5;
        multiply.addActionListener(this);
        panel2.add(multiply, gbc);
        
        tan.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 4;
        gbc.gridy = 5;
        tan.addActionListener(this);
        panel2.add(tan, gbc);
        // Thrid row end
        
        // Fourth row start
        btn1.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        btn1.addActionListener(this);
        panel2.add(btn1, gbc);
        
        btn2.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        btn2.addActionListener(this);
        panel2.add(btn2, gbc);
        
        btn3.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 2;
        gbc.gridy = 6;
        btn3.addActionListener(this);
        panel2.add(btn3, gbc);
        
        subtract.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 3;
        gbc.gridy = 6;
        subtract.addActionListener(this);
        panel2.add(subtract, gbc);
        
        factorial.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 4;
        gbc.gridy = 6;
        factorial.addActionListener(this);
        panel2.add(factorial, gbc);
        // Fourth row end
        
        // Fifth row start
        equals.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        equals.addActionListener(this);
        panel2.add(equals, gbc);
        
        btn0.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 7;
        btn0.addActionListener(this);
        panel2.add(btn0, gbc);
        
        dot.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 2;
        gbc.gridy = 7;
        dot.addActionListener(this);
        panel2.add(dot, gbc);
        
        add.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 3;
        gbc.gridy = 7;
        add.addActionListener(this);
        panel2.add(add, gbc);
        
        PI.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 4;
        gbc.gridy = 7;
        PI.addActionListener(this);
        panel2.add(PI, gbc);
    }
    /**
     * A method to run the thread in.
     */
    @Override
    public void run() {
        try {
            threadCount = new Thread[Thread.activeCount()];
            System.out.println("Thread running " + Thread.enumerate(threadCount));
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }
    /**
     * A method to listen for changes in the components and give 
     * action to them
     * @param e is the ActionEvent variable.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn0) {
            txtField.setText(txtField.getText() + "0");
            label.setText(label.getText() + "0");
        }
        if (e.getSource() == btn1) {
            txtField.setText(txtField.getText() + "1");
            label.setText(label.getText() + "1");
        }
        if (e.getSource() == btn2) {
            txtField.setText(txtField.getText() + "2");
            label.setText(label.getText() + "2");
        }
        if (e.getSource() == btn3) {
            txtField.setText(txtField.getText() + "3");
            label.setText(label.getText() + "3");
        }
        if (e.getSource() == btn4) {
            txtField.setText(txtField.getText() + "4");
            label.setText(label.getText() + "4");
        }
        if (e.getSource() == btn5) {
            txtField.setText(txtField.getText() + "5");
            label.setText(label.getText() + "5");
        }
        if (e.getSource() == btn6) {
            txtField.setText(txtField.getText() + "6");
            label.setText(label.getText() + "6");
        }
        if (e.getSource() == btn7) {
            txtField.setText(txtField.getText() + "7");
            label.setText(label.getText() + "7");
        }
        if (e.getSource() == btn8) {
            txtField.setText(txtField.getText() + "8");
            label.setText(label.getText() + "8");
        }
        if (e.getSource() == btn9) {
            txtField.setText(txtField.getText() + "9");
            label.setText(label.getText() + "9");
        }
        if (e.getSource() == dot) {
            txtField.setText(txtField.getText() + ".");
            label.setText(label.getText() + ".");
        }
        if (e.getSource() == clear) {
            txtField.setText("");
            label.setText("");
        }
        length = txtField.getText().length();
        number = txtField.getText().length() - 1;
        if (e.getSource() == back) {
            if (length > 0) {
                _back = new StringBuilder(txtField.getText());
                _back.deleteCharAt(number);
                store = _back.toString();
                txtField.setText(store);
                label.setText(store);
            }
        }
        if (e.getSource() == plusNegative) {
            txtField.setText(txtField.getText() + "-");
            label.setText(label.getText() + "-");
        } else if (e.getSource() == plusNegative) {
            txtField.setText(txtField.getText() + "+");
            label.setText(label.getText() + "+");
        } 
        if (e.getSource() == PI) {
            txtField.setText("" + Double.toString(Math.PI));
            label.setText("\u03C0");
        }
        if (e.getSource() == factorial) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("!");
            operation = 9;
        }
        if (e.getSource() == tan) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("tan");
            operation = 8;
        }
        if (e.getSource() == cos) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("cos");
            operation = 7;
        }
        if (e.getSource() == sin) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("sin");
            operation = 6;
        }
        if (e.getSource() == squareRoot) {
            numeral = Double.parseDouble(txtField.getText());
            label.setText("\u221A");
            operation = 5;
        }
        if (e.getSource() == add) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("+");
            operation = 4;
        }
        if (e.getSource() == subtract) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("-");
            operation = 3;
        }
        if (e.getSource() == divide) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("\u00F7");
            operation = 2;
        }
        if (e.getSource() == multiply) {
            numeral = Double.parseDouble(txtField.getText());
            txtField.setText("");
            label.setText("*");
            operation = 1;
        }
        
        if (e.getSource() == equals) {
            calculation();
        }
        if (e.getSource() == box) {
            if (box.getSelectedItem().toString().equals(message[0])) {
                turnOn();
            } else if (box.getSelectedItem().toString().equals(message[1])) {
                turnOff();
            }
        }
    }
    /**
     * A method to perform the calculations on the numbers and operations 
     * clicked.
     */
    public void calculation() {
        switch (operation) {
            case 1:
                answer = numeral * Double.parseDouble(txtField.getText());
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            case 2:
                try {
                    answer = numeral / Double.parseDouble(txtField.getText());
                    txtField.setText(Double.toString(answer));
                    label.setText(Double.toString(answer));
                } catch (ArithmeticException e) {
                    System.out.println("Can't Divide by Zero");
                } 
                break;
            case 3:
                answer = numeral - Double.parseDouble(txtField.getText());
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            case 4:
                answer = numeral + Double.parseDouble(txtField.getText());
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            case 5:
                answer = Math.sqrt(numeral);
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            case 6: 
                answer = Math.sin(numeral);
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            case 7: 
                answer = Math.cos(numeral);
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            case 8:
                answer = Math.tan(numeral);
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            case 9:
                answer = factorialMeth(numeral);
                txtField.setText(Double.toString(answer));
                label.setText(Double.toString(answer));
                break;
            }
    }
    /**
     * A method to perform the factorial of numbers.
     * @param number is the numeral being passed into the method.
     * @return the variable number.
     */
    public double factorialMeth(double number) {
        if (number <= 1) {
            return 1;
        } else {
            return number * factorialMeth(number - 1);
        }
    }
    /**
     * A method to disable the components on the calculator.
     */
    public void turnOff() {
        txtField.setEnabled(false);
        label.setEnabled(false);
        btn0.setEnabled(false);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        dot.setEnabled(false);
        equals.setEnabled(false);
        clear.setEnabled(false);
        back.setEnabled(false);
        cos.setEnabled(false);
        sin.setEnabled(false);
        tan.setEnabled(false);
        factorial.setEnabled(false);
        PI.setEnabled(false);
        add.setEnabled(false);
        subtract.setEnabled(false);
        multiply.setEnabled(false);
        divide.setEnabled(false);
        plusNegative.setEnabled(false);
        squareRoot.setEnabled(false);
    }
    /**
     * A method to enable the methods on the calculator.
     */
    public void turnOn() {
        txtField.setEnabled(true);
        label.setEnabled(true);
        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        dot.setEnabled(true);
        equals.setEnabled(true);
        clear.setEnabled(true);
        back.setEnabled(true);
        cos.setEnabled(true);
        sin.setEnabled(true);
        tan.setEnabled(true);
        factorial.setEnabled(true);
        PI.setEnabled(true);
        add.setEnabled(true);
        subtract.setEnabled(true);
        multiply.setEnabled(true);
        divide.setEnabled(true);
        plusNegative.setEnabled(true);
        squareRoot.setEnabled(true);
    }
}