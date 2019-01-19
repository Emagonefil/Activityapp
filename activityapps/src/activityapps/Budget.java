package activityapps;

import java.util.ArrayList;

public class Budget {
	public class BudgetItem{
		public double unitPrice;
		public double amount;
		public String description;
	}
	private double total;
	private double available;
	private ArrayList<BudgetItem> expenses;
	
	public Budget(double total) {
		this.total = total;
		this.available = available;
		this.expenses = new ArrayList<BudgetItem>();
	}

	public double getTotal() {
		return total;
	}

	public double getAvailable() {
		return available;
	}

	
}
