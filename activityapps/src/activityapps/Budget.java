package activityapps;

import java.util.ArrayList;

public class Budget {
	private double total;
	private double available;
	protected ArrayList<Item> expenses;
	
	public Budget(double total) {
		this.total = total;
		this.available = total;
		this.expenses = new ArrayList<Item>();
	}

	public double getTotal() {
		return total;
	}

	public double getAvailable() {
		return available;
	}

	public int addItem(Item item){
		double cost = item.getUnitPrice() * item.getAmount();
		if (cost>this.available) {
			return -1; // not enough budget
		}
		available = available - cost;
		expenses.add(item);
		return 0;
	}
	
	public int removeItem(int i){
		if (i<0 || i>=expenses.size()) return -1;
		expenses.remove(i);
		return 0;
	}
	
	public int size(){
		return expenses.size();
	}
	
	public Item get(int i){
		return expenses.get(i);
	}
	
	public void itemChangeNotice(){
		double cost = 0;
		for (Item item : expenses) {
			cost = cost + item.getAmount()*item.getUnitPrice();
		}
		available = total - cost;
	}
	
}
