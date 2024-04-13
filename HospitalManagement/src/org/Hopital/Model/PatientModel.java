package org.Hopital.Model;

import java.sql.Date;

public class PatientModel {

	private int Pid;
	private String Name;
	private String Bloodgroup;
	private int Age;
	private String Gender;
	private String Contact;
	private String Disease;
	private String Address;
	
	//for patient status
	private Date AppointmentDate;
	private Date AdmmitDate;
	private Date DischargeDate;
	private String Prescription;
	private String Category;
	private int BillAmt;
	private int CoatId;
	private int Did;
	
	public PatientModel()
	{
		
	}
	
	public PatientModel(String name, String blood, int age, String gender, String contact, String disease, String address)
	{
		this.Name=name;
		this.Bloodgroup=blood;
		this.Age=age;
		this.Gender=gender;
		this.Contact=contact;
		this.Disease=disease;
		this.Address=address;
	}
	
	public int getPid() {
	
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBloodgroup() {
		return Bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		Bloodgroup = bloodgroup;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
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
	public String getDisease() {
		return Disease;
	}
	public void setDisease(String disease) {
		Disease = disease;
	}

	public Date getAppointmentDate() {
		return AppointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		AppointmentDate = appointmentDate;
	}

	public Date getAdmmitDate() {
		return AdmmitDate;
	}

	public void setAdmmitDate(Date admmitDate) {
		AdmmitDate = admmitDate;
	}

	public Date getDischargeDate() {
		return DischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		DischargeDate = dischargeDate;
	}

	public String getPrescription() {
		return Prescription;
	}

	public void setPrescription(String prescription) {
		Prescription = prescription;
	}

	public int getBillAmt() {
		return BillAmt;
	}

	public void setBillAmt(int billAmt) {
		BillAmt = billAmt;
	}

	public int getCoatId() {
		return CoatId;
	}

	public void setCoatId(int coatId) {
		CoatId = coatId;
	}

	public int getDid() {
		return Did;
	}

	public void setDid(int did) {
		Did = did;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	
	
}
