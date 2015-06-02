package Lab6;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StockPanel extends JPanel{
	final String defaultName = "stock: ";
	final String defaultPrice = "price: $ ";
	final String defaultQuantity = "qty: # ";
	
	JLabel name;
	JLabel price;
	JLabel quantity;
	int index;
	
	public StockPanel(int index, int width, int height){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		name = new JLabel(defaultName);
		price = new JLabel(defaultPrice);
		quantity = new JLabel(defaultQuantity);
		this.index = index;
		
		add(name);
		add(Box.createVerticalGlue());
		add(price);
		add(quantity);
	}
	
	public void update(String name, String price, String quantity){
		//could check for length limitations...
		this.name.setText(defaultName + name);
		this.price.setText(defaultPrice + price);
		this.quantity.setText(defaultQuantity + quantity);
	}
	
	public String getName(){
		return name.getText();
	}
	
	public String getPrice(){
		return price.getText();
	}
	
	public String getQuantity(){
		return quantity.getText();
	}
	
	public int getIndex(){
		return index;
	}

	public void selected(boolean isSelected) {
		if(isSelected)
			setBackground(new Color(255, 255, 255));
		else
			setBackground(new Color(240, 240, 240));
	}
}
