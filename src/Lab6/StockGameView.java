package Lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StockGameView extends JFrame{
	//directions
	final int RIGHT = 1;
	final int LEFT = -1;
		
	//View attributes
	int width;
	int height;
	
	//Object attributes
	StockQueueList shop;
	Player user;
	Timeline myTimeline;
	
	//mainPanel
	JPanel mainPanel;
	
	//user sell panel
	TransactionPanel userSellPanel;
	//user stock panel
	StockControlPanel userStockPanel;
	//shop buy panel
	TransactionPanel shopBuyPanel;
	//shop stock panel
	StockControlPanel shopStockPanel;
	//user timeline panel
	TimelinePanel timelinePanel;
	
	public StockGameView(StockQueueList shop, Player user, Timeline myTimeline, int width, int height) {
		// exit on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set the title default for this frame
		this.setTitle("Application");
		
		//width and height
		this.width = width;
		this.height = height;
		
		// set size defaults for this frame
		setPreferredSize(new Dimension(width, height));
		setResizable(false);
		
		// set color defaults for this frame
		setBackground(Color.GRAY);
		
		//Set necessary attributes
		this.shop = shop;
		this.user = user;
		this.myTimeline = myTimeline;
		
		// Create the Primary Panel and add it to the frame
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		
		//Create each required panel
		shopStockPanel = new StockControlPanel(width, height/5);
		userStockPanel = new StockControlPanel(width, height/5); //I don't like this
		shopBuyPanel = new TransactionPanel(width, height/5, "Buy");
		userSellPanel = new TransactionPanel(width, height/5, "Sell");
		timelinePanel = new TimelinePanel(width, height/5);
		
		//Update stock panels with initial data
		shopStockPanel.updateStockPanels(shop);
		userStockPanel.updateStockPanels(user.getMyStockQueueList());
		
		//update timelinePanel with initial data
		timelinePanel.updateCapitalLabel(user.getCurrentCapital());
		timelinePanel.updateProfitLabel(user.getProfit());
		timelinePanel.updateDayLabel(myTimeline.getCurrentDay(), myTimeline.getNumberOfDays());
		
		//Create the listeners that will be used for each panel and add them
		StockControlPanelListener scpListener = new StockControlPanelListener();
		StockControlPanelMouseListener scpMouseListener = new StockControlPanelMouseListener();
		shopStockPanel.addListeners(scpListener, scpMouseListener);
		userStockPanel.addListeners(scpListener, scpMouseListener);
		
		TransactionPanelListener tpListener = new TransactionPanelListener();
		shopBuyPanel.addListener(tpListener);
		userSellPanel.addListener(tpListener);
		
		TimelinePanelListener tListener = new TimelinePanelListener();
		timelinePanel.addListener(tListener);
		
		
		//Add each panel to mainPanel in the correct order
		mainPanel.add(shopStockPanel);
		mainPanel.add(shopBuyPanel);
		mainPanel.add(userStockPanel);
		mainPanel.add(userSellPanel);
		mainPanel.add(timelinePanel);
		
		pack();
	    setVisible(true);
	}
	//Listener for stock panels
	private class StockControlPanelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			boolean isUserPanel = true;
			if(e.getSource() == shopStockPanel.shiftLeftButton){
				shop.shiftStockQueue(LEFT);
				isUserPanel = false;
			}
			else if(e.getSource() == shopStockPanel.shiftRightButton){
				shop.shiftStockQueue(RIGHT);
				isUserPanel = false;
			}
			else if(e.getSource() == userStockPanel.shiftLeftButton)
				user.getMyStockQueueList().shiftStockQueue(LEFT);
			else if(e.getSource() == userStockPanel.shiftRightButton)
				user.getMyStockQueueList().shiftStockQueue(RIGHT);
			 
			 //update stock panels
			if(isUserPanel){
				userStockPanel.updateStockPanels(user.getMyStockQueueList());
				userStockPanel.selectedStockQueue = null;
			}
			else{
				shopStockPanel.updateStockPanels(shop);
				shopStockPanel.selectedStockQueue = null;
			}
		}
	}
	
	private class StockControlPanelMouseListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			int index = ((StockPanel) e.getSource()).getIndex();
			boolean isUserPanel = true;
			for(int i = 0; i < shopStockPanel.MAXSTOCKS; i++){
				if(e.getSource() == shopStockPanel.stockPanels[i])
					isUserPanel = false;
			}
			
			if(isUserPanel){
				if(user.getMyStockQueueList().getSize() > index){
					userStockPanel.updateStockPanels(user.getMyStockQueueList());
					userStockPanel.selectedStockQueue = user.getMyStockQueueList().getStockQueueAtIndexFromHead(index);
					userStockPanel.stockPanels[index].selected(true);
					userSellPanel.updateDetail("");
				}
			}
			else{
				if(shop.getSize() > index){
					shopStockPanel.updateStockPanels(shop);
					shopStockPanel.selectedStockQueue = shop.getStockQueueAtIndexFromHead(index);
					shopStockPanel.stockPanels[index].selected(true);
					shopBuyPanel.updateDetail("");
				}
			}
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	//Listener for transaction panels
	private class TransactionPanelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int amount;
			double price;
			String name;
			if(e.getSource() == shopBuyPanel.transactionButton){
				if(shopStockPanel.selectedStockQueue != null){
					amount = shopBuyPanel.getAmount();
					price = shopStockPanel.getSelectedPrice();
					name = shopStockPanel.getSelectedName();
					
					//Check  that user has enough money
					if(amount*price < user.getCurrentCapital() && amount > 0){
						//Buy the stocks
						user.buyStock(name, amount, price);
						shopBuyPanel.updateDetail("Bought " + amount + " stocks of " + name + " for " + price*amount);
					}
				}
				else
					shopBuyPanel.updateDetail("Unable to buy stocks");
			}
			else if(e.getSource() == userSellPanel.transactionButton){
				if(userStockPanel.selectedStockQueue != null){
					amount = userSellPanel.getAmount();
					name = userStockPanel.getSelectedName();
					//Find the stock in shop
					int index;
					index = shop.findIndexOfStockQueue(name); //The shop should have every stock..
					price = shop.getAtIndex(index).getTopPrice();
					
					//Find the stock in user
					index = user.getMyStockQueueList().findIndexOfStockQueue(name); 
					//Check  that user has enough stocks
					if(amount <= user.getMyStockQueueList().getAtIndex(index).getTotalQuantity() && amount > 0){
						//Sell the stocks
						if(user.sellStock(name, amount, price));
							userSellPanel.updateDetail("Sold " + amount + " stocks of " + name + " for " + price*amount);
					}
				}
				else
					userSellPanel.updateDetail("Unable to sell stocks");
			}
			
			//Update stock panels
			shopStockPanel.selectedStockQueue = null;
			userStockPanel.selectedStockQueue = null;
			shopStockPanel.updateStockPanels(shop);
			userStockPanel.updateStockPanels(user.getMyStockQueueList());
			
			//Update capital
			timelinePanel.updateCapitalLabel(user.getCurrentCapital());
			
			//Update profit
			timelinePanel.updateProfitLabel(user.getProfit());
		}
	}
	
	//Listener for timeline panel
	private class TimelinePanelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == timelinePanel.nextDayButton){
				if(!myTimeline.hasNextDay()){
					//Get the day
					Day day = myTimeline.getNextDay();
					
					//Remove all stocks from shop
					shop = new StockQueueList();
					
					//Add each new stock into shop
					StockQueue tempStockQueue;
					String tempStockName;
					Stock tempStock;
					
					while(day.hasNext()){
						tempStockName = day.getStockName();
						tempStock = new Stock(Integer.MAX_VALUE, day.getStockPrice());
						tempStockQueue = new StockQueue(tempStockName, tempStock);
						shop.add(tempStockQueue);
						day.next();
					}
					//Update all stock panels
					shopStockPanel.updateStockPanels(shop);
					userStockPanel.updateStockPanels(user.getMyStockQueueList());
					
					//Update day
					timelinePanel.updateDayLabel(myTimeline.getCurrentDay(), myTimeline.getNumberOfDays());
				}
				else{
					//Sell all stocks
					
					//Check profit
					double profit = user.getProfit();
					
					//Prepare message
					String message;
					if(profit > 0.0)
						message = "Congrats!\n";
					else
						message = "Better luck next time!\n";
					
					//Create a popup declaring how the game went...
					JOptionPane.showMessageDialog(new JFrame(),
						    message + "Profit: $ " + profit,
						    "End of Game",
						    JOptionPane.INFORMATION_MESSAGE);
					
					//Exit the program
					System.exit(1);
				}
			}
		}
	}
}
