package Lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StockControlPanel extends JPanel{
	//Attributes
	int width, height;
	
	//buttons
	public JButton shiftLeftButton;
	public JButton shiftRightButton;
	
	//list of stock components
	public StockPanel[] stockPanels;
	public final int MAXSTOCKS = 4;

	//selected StockQueue
	StockQueue selectedStockQueue;
	
	public StockControlPanel(int width, int height){
		this.width = width;
		this.height = height;
		setMaximumSize(new Dimension(width, height));
		
		//set up stock panels and add the mouse listener
		stockPanels = new StockPanel[MAXSTOCKS];
		for(int i = 0; i < MAXSTOCKS; i++){
			stockPanels[i] = new StockPanel(i, width/(MAXSTOCKS + 2), height); //2 buttons...
		}
		//updateStockPanels();
		
		//set up buttons
		shiftLeftButton = new JButton("<-");
		shiftRightButton = new JButton("->");
		
		//Add everything to this panel
		add(shiftLeftButton);
		add(Box.createHorizontalGlue());
		add(Box.createHorizontalStrut(10));
		for(int i = 0; i < MAXSTOCKS; i++){
			add(stockPanels[i]);
			add(Box.createHorizontalStrut(5));
		}
		add(Box.createHorizontalStrut(5));
		add(Box.createHorizontalGlue());
		add(shiftRightButton);
	}
	
	public void addListeners(ActionListener scpl, MouseListener stl){
		shiftLeftButton.addActionListener(scpl);
		shiftRightButton.addActionListener(scpl);
		
		for(int i = 0; i < MAXSTOCKS; i++){
			stockPanels[i].addMouseListener(stl);
		}
	}
	
	public void updateStockPanels(StockQueueList stocks) {
		StockQueue temp;
		for(int i = 0; i < MAXSTOCKS; i++){
			if(stocks.getSize() > i){
				temp = stocks.getStockQueueAtIndexFromHead(i);
				stockPanels[i].update(temp.getStockName(), 
					  Double.toString(temp.getTopPrice()), 
					  Double.toString(temp.getTotalQuantity()));
				stockPanels[i].setVisible(true);
			}
			else{
				stockPanels[i].update(" ", " ", " ");
				stockPanels[i].setVisible(false);
			}
			stockPanels[i].selected(false);
		}
	}

	public double getSelectedPrice() {
		return selectedStockQueue.getTopPrice();
	}

	public String getSelectedName() {
		return selectedStockQueue.getStockName();
	}
}
