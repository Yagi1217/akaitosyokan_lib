package la.Bean;

public class ItemBackdoorBean {
	
	//item用
	private int item_Id;
	private String item_Isbn;
	private String item_Name;
	private String rental_date;
	private String arrival_date;
	private String discard_date;
	private String remarks;

	//member用
	private int member_Id;
	private String member_Name;
	private String member_Name_Kana;
	private String member_Address;
	private String member_Tel;
	private String member_Email;
	private String member_Birthday;
	private String member_Regist_date;
	private String member_Release_date;	
	
	public ItemBackdoorBean() {
		super();
	}
	public ItemBackdoorBean(int item_Id, String item_Isbn, String item_Name, String rental_date, String arrival_date,
			String discard_date, String remarks) {
		super();
		this.item_Id = item_Id;
		this.item_Isbn = item_Isbn;
		this.item_Name = item_Name;
		this.rental_date = rental_date;
		this.arrival_date = arrival_date;
		this.discard_date = discard_date;
		this.remarks = remarks;
	}
	public int getItem_Id() {
		return item_Id;
	}
	public void setItem_Id(int item_Id) {
		this.item_Id = item_Id;
	}
	public String getItem_Isbn() {
		return item_Isbn;
	}
	public void setItem_Isbn(String item_Isbn) {
		this.item_Isbn = item_Isbn;
	}
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}
	public String getRental_date() {
		return rental_date;
	}
	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;
	}
	public String getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}
	public String getDiscard_date() {
		return discard_date;
	}
	public void setDiscard_date(String discard_date) {
		this.discard_date = discard_date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	//以下member用
	public ItemBackdoorBean(int member_Id, String member_Name, String member_Name_Kana, String member_Address,
			String member_Tel, String member_Email, String member_Birthday, String member_Regist_date,
			String member_Release_date) {
		super();
		this.member_Id = member_Id;
		this.member_Name = member_Name;
		this.member_Name_Kana = member_Name_Kana;
		this.member_Address = member_Address;
		this.member_Tel = member_Tel;
		this.member_Email = member_Email;
		this.member_Birthday = member_Birthday;
		this.member_Regist_date = member_Regist_date;
		this.member_Release_date = member_Release_date;
	}
	public int getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(int member_Id) {
		this.member_Id = member_Id;
	}
	public String getMember_Name() {
		return member_Name;
	}
	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}
	public String getMember_Name_Kana() {
		return member_Name_Kana;
	}
	public void setMember_Name_Kana(String member_Name_Kana) {
		this.member_Name_Kana = member_Name_Kana;
	}
	public String getMember_Address() {
		return member_Address;
	}
	public void setMember_Address(String member_Address) {
		this.member_Address = member_Address;
	}
	public String getMember_Tel() {
		return member_Tel;
	}
	public void setMember_Tel(String member_Tel) {
		this.member_Tel = member_Tel;
	}
	public String getMember_Email() {
		return member_Email;
	}
	public void setMember_Email(String member_Email) {
		this.member_Email = member_Email;
	}
	public String getMember_Birthday() {
		return member_Birthday;
	}
	public void setMember_Birthday(String member_Birthday) {
		this.member_Birthday = member_Birthday;
	}
	public String getMember_Regist_date() {
		return member_Regist_date;
	}
	public void setMember_Regist_date(String member_Regist_date) {
		this.member_Regist_date = member_Regist_date;
	}
	public String getMember_Release_date() {
		return member_Release_date;
	}
	public void setMember_Release_date(String member_Release_date) {
		this.member_Release_date = member_Release_date;
	}

}
