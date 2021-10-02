import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoanAssistant extends JFrame {

	private JPanel contentPane;
	private JTextField noOfPaymentsTF;
	private JTextField interestRateTF;
	private JTextField loanBalanceTF;
	private JTextField monthlyPaymentsTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanAssistant frame = new LoanAssistant();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JButton XButton1;
	JButton XButton2;
	JButton monthlyPaymentButton;
	JButton newLoanAnalysisButton;
	JButton exitButton;
	JTextArea textArea;
	public LoanAssistant() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Loan Assistant");
		setResizable(false);
		setBounds(100, 100, 650, 280);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel loanBalance = new JLabel("Loan Balance");
		loanBalance.setFont(new Font("Arial", Font.PLAIN, 16));
		loanBalance.setBounds(10, 20, 100, 25);
		contentPane.add(loanBalance);
		
		JLabel interestRate = new JLabel("Interest Rate");
		interestRate.setFont(new Font("Arial", Font.PLAIN, 16));
		interestRate.setBounds(10, 50, 100, 25);
		contentPane.add(interestRate);
		
		JLabel noOfPayments = new JLabel("Number of Payments");
		noOfPayments.setFont(new Font("Arial", Font.PLAIN, 16));
		noOfPayments.setBounds(10, 80, 145, 25);
		contentPane.add(noOfPayments);
		
		JLabel monthlyPayments = new JLabel("Monthly Payments");
		monthlyPayments.setFont(new Font("Arial", Font.PLAIN, 16));
		monthlyPayments.setBounds(10, 110, 145, 25);
		contentPane.add(monthlyPayments);
		
		noOfPaymentsTF = new JTextField();
		noOfPaymentsTF.setBounds(165, 79, 100, 25);
		contentPane.add(noOfPaymentsTF);
		noOfPaymentsTF.setColumns(10);
		
		interestRateTF = new JTextField();
		interestRateTF.setColumns(10);
		interestRateTF.setBounds(165, 49, 100, 25);
		contentPane.add(interestRateTF);
		
		loanBalanceTF = new JTextField();
		loanBalanceTF.setColumns(10);
		loanBalanceTF.setBounds(165, 19, 100, 25);
		contentPane.add(loanBalanceTF);
		
		monthlyPaymentsTF = new JTextField();
		monthlyPaymentsTF.setColumns(10);
		monthlyPaymentsTF.setBounds(165, 109, 100, 25);
		contentPane.add(monthlyPaymentsTF);
		
		JLabel loanAnalysis = new JLabel("Loan Analysis:");
		loanAnalysis.setFont(new Font("Arial", Font.PLAIN, 16));
		loanAnalysis.setBounds(330, 11, 104, 14);
		contentPane.add(loanAnalysis);
		
		textArea = new JTextArea();
		textArea.setBounds(330, 35, 304, 140);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setFocusable(false);
		contentPane.add(textArea);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButtonActionPerformed(e);
			}
		});
		exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
		exitButton.setBounds(441, 186, 70, 23);
		exitButton.setFocusable(false);
		contentPane.add(exitButton);
		
		monthlyPaymentButton = new JButton("Compute Monthly Payment");
		monthlyPaymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computeButtonActionPerformed(e);
			}
		});
		monthlyPaymentButton.setFont(new Font("Arial", Font.BOLD, 16));
		monthlyPaymentButton.setBounds(20, 152, 300, 23);
		contentPane.add(monthlyPaymentButton);
		
		newLoanAnalysisButton = new JButton("New Loan Analysis");
		newLoanAnalysisButton.setEnabled(false);
		newLoanAnalysisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loanButtonActionPerformed(e);			
			}
		});
		newLoanAnalysisButton.setFont(new Font("Arial", Font.BOLD, 16));
		newLoanAnalysisButton.setBounds(54, 186, 197, 23);
		contentPane.add(newLoanAnalysisButton);
		
		XButton1 = new JButton("X");
		XButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monthsButtonActionPerformed(e);
			}
		});
		XButton1.setFont(new Font("Arial", Font.PLAIN, 11));
		XButton1.setBounds(279, 80, 41, 23);
		XButton1.setFocusable(false);
		contentPane.add(XButton1);
		
		XButton2 = new JButton("X");
		XButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentButtonActionPerformed(e);
			}
		});
		XButton2.setVisible(true);
		XButton2.setFont(new Font("Arial", Font.PLAIN, 11));
		XButton2.setBounds(279, 112, 41, 19);
		XButton2.setFocusable(false);
		contentPane.add(XButton2);
		XButton2.doClick();

		
	}
	boolean computePayment;
	private void computeButtonActionPerformed(ActionEvent e)
	{
		double payment,balance,interest,monthlyInterest,multiplier,loanbalance,finalPayment;
		int months;
		if (validateDecimalNumber(loanBalanceTF))
		{
			balance=Double.valueOf(loanBalanceTF.getText());
		}
		else
		{
			JOptionPane.showConfirmDialog(null, "Invalid or empty Loan Balance entry.\nPlease correct.", "Balance Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		return;
		}
		if (validateDecimalNumber(interestRateTF))
		{
			interest=Double.valueOf(interestRateTF.getText());
		}
		else
		{
			JOptionPane.showConfirmDialog(null, "Invalid or empty Interest Rate entry.\nPlease correct.", "Interest Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		monthlyInterest=interest/1200;
		if(computePayment) {
			if (validateDecimalNumber(noOfPaymentsTF))
			{
				months=Integer.valueOf(noOfPaymentsTF.getText());
			}
			else
			{
				JOptionPane.showConfirmDialog(null, "Invalid or empty Number of Payments entry.\nPlease correct.", "Number of Payments Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (interest == 0)
			{
			payment = balance / months;
			}
			else {
				multiplier=Math.pow(1+monthlyInterest, months);
				payment = balance * monthlyInterest * multiplier / (multiplier - 1);
			}
			monthlyPaymentsTF.setText(new DecimalFormat("0.00").format(payment));
		}
		else {
			if (validateDecimalNumber(monthlyPaymentsTF))
			{
				payment=Double.valueOf(monthlyPaymentsTF.getText());
			}
			else
			{
				JOptionPane.showConfirmDialog(null, "Invalid or empty Monthly Payment entry.\nPlease correct.", "Payment Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

			return;
			}

			if (interest == 0)
			{
			months = (int)(balance / payment);
			}
			else
			{
			months=(int)((Math.log(payment)-Math.log(payment-balance*monthlyInterest))/(Math.log(1+monthlyInterest)));
			}
			noOfPaymentsTF.setText(String.valueOf(months));
		}
		payment = Double.valueOf(monthlyPaymentsTF.getText());
		textArea.setText("Loan Balance: $" + new DecimalFormat("0.00").format(balance));
		textArea.append("\n" + "Interest Rate: " + new DecimalFormat("0.00").format(interest) + "%");
		loanbalance=balance;
		for (int paymentNumber = 1; paymentNumber <= months - 1; paymentNumber++)
		{
		loanbalance += loanbalance * monthlyInterest - payment;
		}
		finalPayment = loanbalance;
		if (finalPayment > payment)
		{
		loanbalance += loanbalance * monthlyInterest - payment;
		finalPayment = loanbalance;
		months++;
		noOfPaymentsTF.setText(String.valueOf(months));
	}
	textArea.append("\n\n" + String.valueOf(months - 1) + " Payments of $" + new DecimalFormat("0.00").format(payment));
	textArea.append("\n" + "Final Payment of: $" + new DecimalFormat("0.00").format(finalPayment));
	textArea.append("\n" + "Total Payments: $" + new DecimalFormat("0.00").format((months - 1) * payment + finalPayment));
	textArea.append("\n" + "Interest Paid $" + new DecimalFormat("0.00").format((months - 1) * payment + finalPayment - balance));
	monthlyPaymentButton.setEnabled(false);
	newLoanAnalysisButton.setEnabled(true);
	newLoanAnalysisButton.requestFocus();
	}
	private void loanButtonActionPerformed(ActionEvent e)
	{
		textArea.setText("");
		if (computePayment)
		{
			monthlyPaymentsTF.setText("");
		}
		else {
			noOfPaymentsTF.setText("");
		}
		monthlyPaymentButton.setEnabled(true);
		newLoanAnalysisButton.setEnabled(false);
		loanBalanceTF.requestFocus();
	}
	private void monthsButtonActionPerformed(ActionEvent e)
	{
		computePayment = false;
		XButton2.setVisible(true);
		XButton1.setVisible(false);
		monthlyPaymentsTF.setEditable(true);
		monthlyPaymentsTF.setBackground(Color.WHITE);
		monthlyPaymentsTF.setFocusable(true);
		noOfPaymentsTF.setText("");
		noOfPaymentsTF.setEditable(false);
		noOfPaymentsTF.setFocusable(false);
		noOfPaymentsTF.setBackground(new Color(255, 255, 128));
		monthlyPaymentButton.setText("Compute Number of Payments");
		loanBalanceTF.requestFocus();
	}
	private void paymentButtonActionPerformed(ActionEvent e)
	{
		computePayment = true;
		XButton2.setVisible(false);
		XButton1.setVisible(true);
		noOfPaymentsTF.setEditable(true);
		noOfPaymentsTF.setBackground(Color.WHITE);
		noOfPaymentsTF.setFocusable(true);
		monthlyPaymentsTF.setText("");
		monthlyPaymentsTF.setEditable(false);
		monthlyPaymentsTF.setBackground(new Color(255, 255, 128));
		monthlyPaymentsTF.setFocusable(false);
		monthlyPaymentButton.setText("Compute Monthly Payment");
		loanBalanceTF.requestFocus();
	}
	private void exitButtonActionPerformed(ActionEvent e)
	{
		System.exit(0);
	}
	public boolean validateDecimalNumber(JTextField tf)
	{
	String s = tf.getText().trim();
	boolean hasDecimal = false;
	boolean valid = true;
	if (s.length() == 0)
	{
	valid = false;
	}
	else
	{
	for (int i = 0; i < s.length(); i++)
	{
	char c = s.charAt(i);
	if (c >= '0' && c <= '9')
	{
	continue;
	}
	else if (c == '.' && !hasDecimal)
	{
	hasDecimal = true;
	}
	else
	{
	valid = false;
	}
	}
	}
	tf.setText(s);
	if (!valid)
	{
	tf.requestFocus();
	}
	return (valid);
	}
}
