package org.Hospital.Service;

import java.util.*;
import org.Hopital.Model.DoctorModel;
import org.Hopital.Model.ReceptionistModel;
import org.Hospital.Repository.ReceptionistRepository;

public class ReceptionistService {
	
	ReceptionistRepository rr = new ReceptionistRepository();
	
	List<ReceptionistModel> list = new ArrayList<ReceptionistModel>();

	public int isReceptionistAdd(ReceptionistModel model)
	{
		return rr.addReceptionist(model)?1:rr.isReceptionist(model)?0:-1;
	}
	
	
	public int isRemoveReceptionist(ReceptionistModel model)
	{
		return rr.isReceptionistRemove(model)?1:rr.isReceptionist(model)?0:-1;
	}
	
	
	public boolean checkLogin(ReceptionistModel model)
	{
		return rr.checkUserPass(model)?true:false;
	}
	
	
	public boolean viewAllReceptionist()
	{
		 list =rr.viewReceptionistList();
		 
		 if(list!=null)
		 {
			 System.out.println("Receptionist List ::\n==============================================================================================================================");
			 System.out.println("Id\tName\tGender\tContact\tUsername");
			 for(ReceptionistModel l : list)
			 {
				 System.out.println(l.getRid()+"\t"+l.getName()+"\t"+l.getGender()+"\t"+l.getContact()+"\t"+l.getUsername());
			 }
			 return true;
		 }
		 
		 return false;
	}
}
