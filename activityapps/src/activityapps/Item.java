package activityapps;

public class Item {
	private String name;
	private double unitPrice;
	private double amount;
	private double obtained;
	private String description;
	private Staff respondant = null;
	
	public Item(String name){
		this.name = name;
	}
	
	public Item(String name, double unitPrice, double amount, String description) {
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.obtained = 0;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getObtained() {
		return obtained;
	}

	public void setObtained(double obtained) {
		this.obtained = obtained;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Staff getRespondant() {
		return respondant;
	}

	public void setRespondant(Staff respondant) {
		this.respondant = respondant;
	}

	public String toString(){
		double cost = unitPrice * amount;
		return name + "\t" + 
				cost+"("+unitPrice + "¡Á" + amount+")\t" +
				(100*(obtained/amount)) + "%\t" +
				description;
	}
}
