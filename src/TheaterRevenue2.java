import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TheaterRevenue2 extends JFrame{

	private JFrame inputFrame, displayFrame, inputErrorFrame; 
	private JLabel lblAdultPrice, lblAdultSold, lblChildPrice, lblChildSold,
					aGrossRev, aNetRev, cGrossRev, cNetRev, tGrossRev, tNetRev;
	private JTextField tfAdultPrice, tfAdultSold, tfChildPrice, tfChildSold; 
	private JTextArea inputErrorMessage;
	private JButton calculateBtn; //submit button
	private JPanel inputPanel, displayPanel; 
	double aPrice, cPrice, adultGrossRevenue, adultNetRevenue, childGrossRevenue,
			childNetRevenue, totalGrossRevenue, totalNetRevenue;
	int aSold, cSold;
	final double THEATER_CUT = .2;

	public TheaterRevenue() {
		//user enters price per adult ticket
		lblAdultPrice = new JLabel("Price Per Adult Ticket: $");
		tfAdultPrice = new JTextField(10);
		lblAdultPrice.setLabelFor(tfAdultPrice);
		//user enters # of adult tix sold
		lblAdultSold = new JLabel("Number of Adult Tickets Sold: ");
		tfAdultSold = new JTextField(10);
		lblAdultSold.setLabelFor(tfAdultSold);
		//user enters price per child ticket
		lblChildPrice = new JLabel("Price Per Child Ticket: $");
		tfChildPrice = new JTextField(10);
		lblChildPrice.setLabelFor(tfChildPrice);
		//user enters # of child tix sold
		lblChildSold = new JLabel("Number of Child Tickets Sold: ");
		tfChildSold = new JTextField(10);
		lblChildSold.setLabelFor(tfChildSold);
		//submit button
		calculateBtn = new JButton();
		
		calculateBtn.setText("Calculate Revenue");
		
		//user view setup
		inputFrame = new JFrame();
		inputFrame.setTitle("Ticket Revenue Calculator");
		inputFrame.setSize(400,200);
		inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputFrame.setVisible(true);
		
		inputPanel = new JPanel();
		inputPanel.add(lblAdultPrice);
		inputPanel.add(tfAdultPrice);
		inputPanel.add(lblAdultSold);
		inputPanel.add(tfAdultSold);
		inputPanel.add(lblChildPrice);
		inputPanel.add(tfChildPrice);
		inputPanel.add(lblChildSold);
		inputPanel.add(tfChildSold);
		inputPanel.add(calculateBtn);
		
		inputFrame.add(inputPanel);
		
		//when calculate is clicked
		calculateBtn.addActionListener(new submitClicked());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TheaterRevenue();
	}
	
	private class submitClicked implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				//convert text to int/double
				aPrice = Double.parseDouble(tfAdultPrice.getText());
				aSold = Integer.parseInt(tfAdultSold.getText());
				cPrice = Double.parseDouble(tfChildPrice.getText());
				cSold = Integer.parseInt(tfChildSold.getText());
				
				//calculate revenue info:
				adultGrossRevenue = aPrice * aSold;
				adultNetRevenue = adultGrossRevenue - (adultGrossRevenue*THEATER_CUT);
				childGrossRevenue = cPrice * cSold;
				childNetRevenue = childGrossRevenue - (childGrossRevenue*THEATER_CUT);
				totalGrossRevenue = adultGrossRevenue + childGrossRevenue;
				totalNetRevenue = adultNetRevenue + childNetRevenue;
				
				//display revenue totals
				aGrossRev = new JLabel("Gross Revenue for Adult Tickets: $" + String.format("%.2f", adultGrossRevenue));
				aNetRev = new JLabel("Net Revenue for Adult Tickets: $" + String.format("%.2f", adultNetRevenue));
				cGrossRev = new JLabel("Gross Revenue for Child Tickets: $" + String.format("%.2f", childGrossRevenue));
				cNetRev = new JLabel("NetRevenue for Child Tickets: $" + String.format("%.2f", childNetRevenue));
				tGrossRev = new JLabel("Total Gross Revenue: $" + String.format("%.2f", totalGrossRevenue));
				tNetRev = new JLabel("Total Net Revenue: $" + String.format("%.2f", totalNetRevenue));
				
				displayFrame = new JFrame();
				displayFrame.setSize(300,200);
				displayFrame.setVisible(true);
				displayPanel = new JPanel();
				displayPanel.add(aGrossRev);
				displayPanel.add(aNetRev);
				displayPanel.add(cGrossRev);
				displayPanel.add(cNetRev);
				displayPanel.add(tGrossRev);
				displayPanel.add(tNetRev);
				displayFrame.add(displayPanel);
			}

			catch(NumberFormatException nfe) {
				inputErrorFrame = new JFrame();
				inputErrorFrame.setSize(300,200);
				inputErrorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				inputErrorFrame.setVisible(true);
				
				inputErrorMessage = new JTextArea("The information entered cannot be used. "
				+ "Price can only be entered as numbers and one decimal point (no $ sign). "
				+ "Number sold can only be entered as a whole number.");
				inputErrorMessage.setLineWrap(true);
	
				inputErrorFrame.add(inputErrorMessage);
			}
		}				
	}
}
