package la.Bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemListBean implements Serializable{
	private int item_Id;
	private String item_Isbn;
	private String item_Name;
	private String writer_Name;
	private String publisher_Name;
	private String rental_Date;
	private String imageUrl;
	
	
	private int quantity;
	
	public ItemListBean() {
		super();
	}
	public ItemListBean(int item_Id,String item_Isbn, String item_Name, String writer_Name, String publisher_Name, String rental_Date, String imageUrl) {
		super();
		this.item_Id = item_Id;
		this.item_Isbn = item_Isbn;
		this.item_Name = item_Name;
		this.writer_Name = writer_Name;
		this.publisher_Name = publisher_Name;
		this.rental_Date = rental_Date;
		this.imageUrl = imageUrl;
	}

	public int getItem_Id() {
		return item_Id;
	}

	public void setItem_Id(int item_Id) {
		this.item_Id = item_Id;
	}

	public String getItem_Name() {
		return item_Name;
	}

	public String getItem_Isbn() {
		return item_Isbn;
	}
	public void setItem_Isbn(String item_Isbn) {
		this.item_Isbn = item_Isbn;
	}

	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}

	public String getWriter_Name() {
		return writer_Name;
	}

	public void setWriter_Name(String writer_Name) {
		this.writer_Name = writer_Name;
	}

	public String getPublisher_Name() {
		return publisher_Name;
	}

	public void setPublisher_Name(String publisher_Name) {
		this.publisher_Name = publisher_Name;
	}

	public String getRental_Date() {
		return rental_Date;
	}

	public void setRental_Date(String rental_Date) {
		this.rental_Date = rental_Date;
	}
	public int getquantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
