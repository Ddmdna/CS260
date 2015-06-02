package Lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransactionPanel extends JPanel {
	int width, height;
	
	JButton transactionButton;
	JLabel amountLabel;
	JTextField amountTextField; 
	JLabel detailLabel;
	final String defaultDetail = "Detail: ";
	JPanel level0;
	JPanel level1;
	
	public TransactionPanel(int width, int height, String tLabel){
		this.width = width;
		this.height = height;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(width, height));
		level0 = new JPanel();
		level1 = new JPanel();
		
		level0.setLayout(new BoxLayout(level0, BoxLayout.X_AXIS));
		level1.setLayout(new BoxLayout(level1, BoxLayout.X_AXIS));
		transactionButton = new JButton(tLabel);
		amountLabel = new JLabel();
		amountLabel.setText("Amount: ");
		amountTextField = new JTextField();
		amountTextField.setMaximumSize(new Dimension(width/3, height/2));
		
		level0.add(Box.createHorizontalStrut(width/3));
		level0.add(transactionButton);
		level0.add(Box.createHorizontalStrut(width/16));
		level0.add(amountLabel);
		level0.add(amountTextField);
		level0.add(Box.createHorizontalStrut(width/3));
		
		detailLabel = new JLabel();
		detailLabel.setText(defaultDetail);
		
		level1.add(Box.createHorizontalStrut(width/4));
		level1.add(detailLabel);
		level1.add(Box.createHorizontalGlue());
		
		add(Box.createVerticalStrut(height/10));
		add(level0);
		add(level1);
		add(Box.createVerticalStrut(height/3));
		
	}
	
	public void addListener(ActionListener scpl){
		transactionButton.addActionListener(scpl);
	}

	public int getAmount() {
		int amount;
		try {
			amount = Integer.parseInt(amountTextField.getText());
		} catch (NumberFormatException e) {
		      amount = 0;
		}
		return amount;
	}

	public void updateDetail(String message) {
		detailLabel.setText(defaultDetail + message);
	}
}
