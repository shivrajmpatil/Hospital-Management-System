package org.Hospital.Service;
import org.Hopital.Model.DoctorModel;
import org.Hospital.Repository.DoctorRepository;
import java.util.*;

public class DoctorService{
	
	DoctorRepository Dr = new DoctorRepository();
	
	List<DoctorModel> list = new ArrayList<DoctorModel>();
	
	public int isDoctorAdd(DoctorModel model)
	{
		return Dr.addDoctor(model)?1:Dr.isDoctor(model)?0:-1;
	}
	
	
	public int isRemoveDoctor(DoctorModel model)
	{
		return Dr.isDoctorRemove(model)?1:Dr.isDoctor(model)?0:-1;
	}
	
	
	public int checkLogin(DoctorModel model)
	{
		int id = Dr.checkUserPass(model);
		return id>0?id:0;
	}
	
	
	public boolean viewAllDoctor()
	{
		 list =Dr.viewDoctorList();
		 
		 if(list!=null)
		 {
			 System.out.println("Doctor List ::\n=============================================================================================================================");
			 System.out.println("Id\tName\tSpecilization\tGender\tContact\tUserName");
			 for(DoctorModel l : list)
			 {
				 System.out.println(l.getDid()+"\t"+l.getName()+"\t"+l.getSpecilization()+"\t\t"+l.getGender()+"\t"+l.getContact()+"\t"+l.getUsername());
			 }
			 return true;
		 }
		 
		 return false;
	}
	
	
	public int isSpecilizationAdd(DoctorModel model)
	{
		return Dr.addSpecilization(model)?1:Dr.isSpecilization(model)?0:-1;
	}
	
	
	public boolean viewSpecilization()
	{
		list = Dr.specilizationList();
		if(list!=null)
		{
			System.out.println("Specilization List ::\n======================================================================================================================");
			System.out.println("Sid\tSpecilization");
			for(DoctorModel l: list)
			{
				System.out.println(l.getSid()+"\t"+l.getSpecilization());
			}
			return true;
		}
		return false;
		
	}
}


