package la.Bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemBorrowingBean {
	private Map<Integer, ItemListBean> items = new HashMap<Integer, ItemListBean>();
	private String itemId;
	private String itemName;
	
	public Map<Integer, ItemListBean> getItems() {
		return items;
	}
	public void addItem(ItemListBean bean, int nums) {
		ItemListBean item = (ItemListBean) items.get(Integer.valueOf(bean.getItem_Id()));
		if (item == null) {
			bean.setQuantity(nums);
			items.put(Integer.valueOf(bean.getItem_Id()), bean);
		} else {
			item.setQuantity(nums + item.getquantity());
		}
	}
		
	public ItemBorrowingBean(String itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}
	public ItemBorrowingBean() {
		super();
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
