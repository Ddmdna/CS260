package Lab6;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimelinePanel extends JPanel{
	//Attributes
	int width, height;
	
	JLabel dayLabel;
	JLabel capitalLabel;
	JLabel profitLabel;
	
	JButton nextDayButton;
	
	JPanel level0;
	JPanel level1;
	
	public TimelinePanel(int width, int height){
		this.width = width;
		this.height = height;
		
		dayLabel = new JLabel();
		capitalLabel = new JLabel();
		profitLabel = new JLabel();
		
		level0 = new JPanel();
		level1 = new JPanel();
		
		nextDayButton = new JButton("Next Day");
		
		level0.setLayout(new BoxLayout(level0, BoxLayout.X_AXIS));
		level1.setLayout(new BoxLayout(level1, BoxLayout.X_AXIS));
		
		level0.setPreferredSize(new Dimension(width, (height/5)));
		level1.setPreferredSize(new Dimension(width, (height/5)));
		nextDayButton.setPreferredSize(new Dimension(width/5, (height/5)*3));
		
		level0.add(Box.createHorizontalStrut(width/10));
		level0.add(dayLabel);
		level0.add(Box.createHorizontalGlue());
		level0.add(capitalLabel);
		level0.add(Box.createHorizontalStrut(50));
		
		level1.add(Box.createHorizontalStrut(50));
		level1.add(Box.createHorizontalGlue());
		level1.add(profitLabel);
		level1.add(Box.createHorizontalStrut(50));
		
		add(level0);
		add(level1);
		add(nextDayButton);
		add(Box.createVerticalStrut(10));
	}
	
	public void addListener(ActionListener tpl){
		nextDayButton.addActionListener(tpl);
	}
	
	public void updateDayLabel(int currentDay, int lastDay){
		dayLabel.setText("Day " + currentDay + " of " + lastDay + ".");
	}
	
	public void updateCapitalLabel(double capital){
		capitalLabel.setText("Total: $" + capital);
	}
	
	public void updateProfitLabel(double profit){
		profitLabel.setText("Profit: $" + profit);
	}
}
