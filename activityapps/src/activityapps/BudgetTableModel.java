package activityapps;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class BudgetTableModel extends AbstractTableModel {
	private Budget budget;
	public boolean lastChangeSuccess = true;
	
	private String[] columnNames =  
        { "Name", "UnitPrice", "Amount", "Obtained", "Description", "Collector" };  
	private ArrayList<Item> items;

	public BudgetTableModel(ArrayList<Item> items, Budget budget){
		this.items = items;
		this.budget = budget;
	}
	
	public void updateItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	@Override
	public String getColumnName(int column)  
    {  
        return columnNames[column];  
    }  
	
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public Object getValueAt(int itemIndex, int term) {
		if (itemIndex<0 || itemIndex>items.size()) return null;
		Item item = items.get(itemIndex);
		switch (term) {
		case 0: return item.getName(); 
		case 1: return item.getUnitPrice();
		case 2: return item.getAmount();
		case 3: return item.getObtained();
		case 4: return item.getDescription();
		case 5: return item.getRespondant();
		default : return null;
		}
	}
	
	@Override  
    public boolean isCellEditable(int rowIndex, int columnIndex)  
    {  
        return true;  
    }  
	
	public boolean budgetCheck(double unitPrice, double amount) {
		double cost = unitPrice * amount;
		return budget.getAvailable()>=cost;
	}
	
	@Override  
    public void setValueAt(Object value, int itemIndex, int term)  
    {  
		Item item = items.get(itemIndex);
		switch (term) {
		case 0: item.setName((String) value); break; 
		case 1: 
			try {
				double unitPrice = Double.valueOf((String) value);
				if (lastChangeSuccess = budgetCheck(unitPrice, item.getAmount()))
					item.setUnitPrice(unitPrice);
			}catch (Exception e){
				e.printStackTrace();
			}
			break; 
		case 2: 
			try {
				double amount = Double.valueOf((String) value);
				if (lastChangeSuccess = budgetCheck(item.getUnitPrice(), amount))
					item.setAmount(amount);
			}catch (Exception e){
				e.printStackTrace();
			}
			break; 
		case 3: 
			try {
				double obtained = Double.valueOf((String) value);
				if (lastChangeSuccess = obtained<=item.getAmount())
					item.setObtained(obtained);
			}catch (Exception e){
				e.printStackTrace();
			}
			break; 
		case 4: item.setDescription((String) value); break; 
		case 5: item.setCollector((Staff) value); break; 
		}
		budget.itemChangeNotice();
        fireTableCellUpdated(itemIndex, term);  
    }  

}
