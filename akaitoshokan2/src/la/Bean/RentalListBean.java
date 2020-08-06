package la.Bean;

import java.util.Date;

public class RentalListBean {
	int id;
	String member_id;
	String item_id;
	String name;
	Date rental_date;
	Date return_scheduled;

	public RentalListBean(int id, String member_id, String item_id, String name, Date rental_date,
			Date return_scheduled) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.item_id = item_id;
		this.name = name;
		this.rental_date = rental_date;
		this.return_scheduled = return_scheduled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRental_date() {
		return rental_date;
	}

	public void setRental_date(Date rental_date) {
		this.rental_date = rental_date;
	}

	public Date getReturn_scheduled() {
		return return_scheduled;
	}

	public void setReturn_scheduled(Date return_scheduled) {
		this.return_scheduled = return_scheduled;
	}

}
