package bookstore;

import java.util.*;

public class Book {
	
	// Fields
	private String Name;
	private String Code;
	private String Editorial;
	private List<String> AvailableFormats;
	private int Quantity;
	private double Price;
	
	// Constructors
	public Book(String name, String code, String editorial, List<String> availableFormats, int quantity, double price) {
		
		Name = (name != null) ? name : "S.N.";
		Code = code;
		Editorial = editorial;
		if (availableFormats == null || availableFormats.isEmpty()) {
			AvailableFormats = new ArrayList<String>();
			AvailableFormats.add("eBook");
		}else {
			AvailableFormats = availableFormats;
		}
		
		
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	// Getters and Setters
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getEditorial() {
		return Editorial;
	}

	public void setEditorial(String editorial) {
		Editorial = editorial;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = (quantity >= 0) ? quantity : 0;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = (price >= 0) ? price : 0; // Hay libros gratis
	}
	
	public List<String> getAvailableFormats() {
		return AvailableFormats;
	}

	public void setAvailableFormats(List<String> availableFormats) {
		AvailableFormats = availableFormats;
	}

	// Methods
	public void addBooks(int new_quantity) {
		Quantity = (new_quantity > 0) ? new_quantity + Quantity: Quantity ;
	}
	
	public void restBooks(int new_quantity) {
		Quantity = (new_quantity > 0 && new_quantity <= Quantity) ? Quantity - new_quantity: Quantity ;
	}
	
	public void modifyPrice(double new_price) {
		Price = (new_price >= 0) ? new_price :  Price;
	}
	
	public double getBookTotalAmount() {
		return Price * Quantity;
	}
	
	public double getDiscount(boolean is_applicable, double percentage) {
		double discount = Price - Price * percentage; 
		
		if (is_applicable) {
			Price = discount;
		}
		
		return discount;
	}
	
	public void addNewFormat(String newFormat) {
		if (!AvailableFormats.contains(newFormat))
			AvailableFormats.add(newFormat);
	}
	
	public void removeFormat(String format) {
		if (AvailableFormats.contains(format))
			AvailableFormats.remove(format);
	}
}
