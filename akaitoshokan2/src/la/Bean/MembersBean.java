package la.Bean;

import java.util.Date;

public class MembersBean {
int id;
String name;
String name_kana;
String address;
String tel;
String email;
Date birthday;
Date regist_date;
Date release_date;
public MembersBean(int id, String name, String email) {
	this.id = id;
	this.name = name;
	this.email = email;
}

public MembersBean(int id, String name, String name_kana, String address, String tel, String email, Date birthday,
		Date regist_date, Date release_date) {
	super();
	this.id = id;
	this.name = name;
	this.name_kana = name_kana;
	this.address = address;
	this.tel = tel;
	this.email = email;
	this.birthday = birthday;
	this.regist_date = regist_date;
	this.release_date = release_date;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getName_kana() {
	return name_kana;
}
public void setName_kana(String name_kana) {
	this.name_kana = name_kana;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public Date getRegist_date() {
	return regist_date;
}
public void setRegist_date(Date regist_date) {
	this.regist_date = regist_date;
}
public Date getRelease_date() {
	return release_date;
}
public void setRelease_date(Date release_date) {
	this.release_date = release_date;
}

}
