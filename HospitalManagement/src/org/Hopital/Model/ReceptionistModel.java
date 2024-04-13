package org.Hopital.Model;

public class ReceptionistModel {

	private int Rid;
	private String Name;
	private String Gender;
	private String Contact;
	private String Username;
	private String Password;
	
	public ReceptionistModel()
	{
		
	}
	public ReceptionistModel(String name,String gender,String contact,String user,String pass)
	{
		this.Name=name;
		this.Gender=gender;
		this.Contact=contact;
		this.Username=user;
		this.Password=pass;
	}
	
	public int getRid() {
		return Rid;
	}
	public void setRid(int rid) {
		Rid = rid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getContact() {
		return Contact;
	}
	public void setContact(String contact) {
		Contact = contact;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
