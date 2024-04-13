package org.Hospital.Service;
import org.Hopital.Model.PatientModel;
import org.Hopital.Model.ReceptionistModel;
import org.Hospital.Repository.PatientRepository;
import java.util.*;

public class PatientService {

	PatientRepository pr = new PatientRepository();
	
	List<PatientModel> list = new ArrayList<PatientModel>();
	
	public int isPatientAdd(PatientModel model)
	{
		return pr.addPatient(model)?1:0;
	}
	
	
	public int isRemovePatient(PatientModel model)
	{
		return pr.isPatientRemove(model)?1:0;
	}
	
	
	public boolean viewAllPatient()
	{
		 list =pr.viewPatientList();
		 
		 if(list!=null)
		 {
			 System.out.println("Patient List ::\n==========================================================================================================================");
			 System.out.println("Id\tName\tContact\tGender\tBloodgroup\tAge\tDisease\t");
			 for(PatientModel l : list)
			 {
				 System.out.println(l.getPid()+"\t"+l.getName()+"\t"+l.getContact()+"\t"+l.getGender()+"\t"+l.getBloodgroup()+"\t"+l.getAge()+"\t"+l.getDisease());
			 }
			 return true;
		 }
		 
		 return false;
	}
	
	
	public int setAppointment(PatientModel model)
	{
		return pr.updateAppointment(model)?1:pr.addAppointment(model)?0:-1;
	}
	
	
	public boolean viewAppointedPatient(int did)
	{
		list = pr.viewAppointedPatients(did);
		
		if(list!=null)
		 {
			 System.out.println("Appointed Patient List ::\n=====================================================================================================================");
			 System.out.println("Id\tName\tAppointment Date\tCategory");
			 for(PatientModel l : list)
			 {
				 System.out.println(l.getPid()+"\t"+l.getName()+"\t"+l.getAppointmentDate()+"\t\t"+l.getCategory());
			 }
			 return true;
		 }
		return false;
	}
	
	
	public int isPid(PatientModel model)
	{
		list= pr.getPatient(model);
		if(list!=null)
		{	System.out.println("Patient Details : :\n=====================================================================================================================================");
			for(PatientModel l : list)
				System.out.println("Patient Name : "+l.getName()+"\nBlood group : "+l.getBloodgroup()+"\nAge : "+l.getAge()+"\nCategory : "+l.getCategory()+"\n|------------------------------|");
			return 1;
		}
		else
			return pr.isPid(model)?0:-1;
	}
	
	
	public int setPrescription(PatientModel model)
	{
		return pr.updatePrescription(model)?1:0;
	}
	
	
	public int viewPrescription(PatientModel model)
	{
		list = pr.getPrescription(model);
		if(list!=null)
		{
			for(PatientModel l : list)
				System.out.println("Prescription ::\n"+l.getPrescription());
			return 1;
		}
		else
			return 0;
	}
	
	
	public boolean viewAllAppointedPatient()
	{
		list.clear();
		
		list = pr.viewAllAppointedPatients();
		
		if(list!=null)
		 {
			 System.out.println("Appointed Patient List ::\n================================================================================================================================");
			 System.out.println("P.Id\tName\tAppointment Date\tD.Id\tCategory");
			 for(PatientModel l : list)
			 {
				 System.out.println(l.getPid()+"\t"+l.getName()+"\t"+l.getAppointmentDate()+"\t\t"+l.getDid()+"\t"+l.getCategory());
			 }
			 return true;
		 }
		return false;
	}
	
	
	public int admmitPatient(PatientModel model)
	{
		return pr.admmitPatient(model)?1:0;
	}
	
	
	public int dischargePatinet(PatientModel model)
	{
		return pr.dischagePatient(model)?1:0;
	}
	
	
	public boolean viewAdmmitPatients()
	{
		list.clear();
		
		list = pr.viewAdmmitedPatients();
		
		if(list!=null)
		 {
			 System.out.println("Admmited Patient List ::\n====================================================================================================================================");
			 System.out.println("P.Id\tName\tAdmmit Date\tD.Id");
			 for(PatientModel l : list)
			 {
				 System.out.println(l.getPid()+"\t"+l.getName()+"\t"+l.getAdmmitDate()+"\t"+l.getDid());
			 }
			 return true;
		 }
		return false;
	}
	
	//======================================SearchPatient==========================================================
	public void searchPatientById(int id)
	{
		list.clear();
		
		list =pr.getPatientById(id);
		
		if(list!=null)
		{
			System.out.println("Patient Details::\n==============================================================================================================================================");
			System.out.println("Name\tContact\tAddress");
			for(PatientModel l :list)
				System.out.println(l.getName()+"\t"+l.getContact()+"\t"+l.getAddress());
		}
		else
			System.out.println("Patient Not Found");
	}
	
	
	public void searchPatientByName(String name)
	{
		list.clear();
		
		list =pr.getPatientByName(name);
		
		if(list!=null)
		{
			System.out.println("Patient Details::\n==============================================================================================================================================");
			System.out.println("Id\tContact\tAddress");
			for(PatientModel l :list)
				System.out.println(l.getPid()+"\t"+l.getContact()+"\t"+l.getAddress());
		}
		else
			System.out.println("Patient Not Found");
	}
	
	public void searchPatientByDisease(String disease)
	{
		list.clear();
		
		list =pr.getPatientByDisease(disease);
		
		if(list!=null)
		{
			System.out.println("Patient Details::\n==============================================================================================================================================");
			System.out.println("Id\tName\tContact\tAddress");
			for(PatientModel l :list)
				System.out.println(l.getPid()+"\t"+l.getName()+"\t"+l.getContact()+"\t"+l.getAddress());
		}
		else
			System.out.println("Patient Not Found");
	}
	
	public void searchPatientByCategory(String category)
	{
		list.clear();
		
		list =pr.getPatientByCategory(category);
		
		if(list!=null)
		{
			System.out.println("Patient Details::\n==============================================================================================================================================");
			System.out.println("Id\tName\tContact\tAddress");
			for(PatientModel l :list)
				System.out.println(l.getPid()+"\t"+l.getName()+"\t"+l.getContact()+"\t"+l.getAddress());
		}
		else
			System.out.println("Patient Not Found");
	}
	
	public void searchPatientByDate(String date)
	{
		list.clear();
		
		list =pr.getPatientByDate(date);
		
		if(list!=null)
		{
			System.out.println("Patient Details::\n==============================================================================================================================================");
			System.out.println("Id\tName\tContact\tAddress");
			for(PatientModel l :list)
				System.out.println(l.getPid()+"\t"+l.getName()+"\t"+l.getContact()+"\t"+l.getAddress());
		}
		else
			System.out.println("Patient Not Found");
	}
}
