package la.Bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemCategoryBean implements Serializable {
	private int code;
	private String name;
	private String publisher;

	public ItemCategoryBean() {
		super();
	}

	public ItemCategoryBean(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public ItemCategoryBean(String publisher) {
		super();
		this.publisher = publisher;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
