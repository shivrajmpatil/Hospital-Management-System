package org.Hopital.Model;
import java.util.*;

public class PatientStatusModel {

	private int Pid;
	private Date AppointmentDate;
	private String Disease;
	private int Did;
	private int Category;
	private int BillAmt;
	private Date AddmisionDate;
	private Date DischargeDate;
	private int CoatNo;
	
	
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public Date getAppointmentDate() {
		return AppointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		AppointmentDate = appointmentDate;
	}
	public String getDisease() {
		return Disease;
	}
	public void setDisease(String disease) {
		Disease = disease;
	}
	public int getDid() {
		return Did;
	}
	public void setDid(int did) {
		Did = did;
	}
	public int getCategory() {
		return Category;
	}
	public void setCategory(int category) {
		Category = category;
	}
	public int getBillAmt() {
		return BillAmt;
	}
	public void setBillAmt(int billAmt) {
		BillAmt = billAmt;
	}
	public Date getAddmisionDate() {
		return AddmisionDate;
	}
	public void setAddmisionDate(Date addmisionDate) {
		AddmisionDate = addmisionDate;
	}
	public Date getDischargeDate() {
		return DischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		DischargeDate = dischargeDate;
	}
	public int getCoatNo() {
		return CoatNo;
	}
	public void setCoatNo(int coatNo) {
		CoatNo = coatNo;
	}
	
	
}
