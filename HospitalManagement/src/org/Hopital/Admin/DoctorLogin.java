package org.Hopital.Admin;
import java.util.*;
import org.Hopital.Model.DoctorModel;
import org.Hopital.Model.PatientModel;
import org.Hospital.Service.DoctorService;
import org.Hospital.Service.PatientService;

public class DoctorLogin {

	Scanner scan = new Scanner(System.in);
	
	DoctorService ds = new DoctorService();
	PatientService ps = new PatientService();
	
	public int checkDoctor(DoctorModel model)
	{
		int id = ds.checkLogin(model);
		return id>0?id:0;
	}
	
	public void doctorLogin(int did)
	{	
		int no=1;
		while(no!=0)
		{
			System.out.println("===================================================================================================================================================================================");
			System.out.println("1.View Appointed Patient List\n2.Update Patient\n3.View All Patient List\n4.View All Admmited Patient\n0.Logout.");
			System.out.println("Enter your choice to proceed...!");
			no = scan.nextInt();
			switch(no)
			{
			//---------------------------------------------------Case 1-------------------------------------------------------------------------
			case 1: boolean b = ps.viewAppointedPatient(did);
					if(!b)
						System.out.println("Appointed Patient List is Empty..");
			break;
			
			//---------------------------------------------------Case 2-----------------------------------------------------
			case 2 : System.out.println("Enter patinent id");
					 int pid= scan.nextInt();
					PatientModel modelp = new PatientModel();
					modelp.setPid(pid);
					int value = ps.isPid(modelp);
					if(value>0)
					{
						 while(no!=0)
						 {
							 System.out.println("1.Add prescription\n2.See last Prescription \n3.Admmit Patient\n4.Discharge Patient\n0.Back");
							 System.out.println("Select Option :");
							 no = scan.nextInt();
							 
							 switch(no)
							 {
							 case 1: scan.nextLine();
							 		System.out.println("Enter Prescription here");
							 		String pre = scan.nextLine();
							 		modelp.setPrescription(pre);
							 		value = ps.setPrescription(modelp);
							 		
							 		String s = value>0?"Presciption updated sucessfully":"Some problem to update prescription";
							 		System.out.println(s);
							 break;
							 
							 case 2 : value = ps.viewPrescription(modelp);
							 		 if(value==0)
							 			 System.out.println("Prescription not found");
							 break;
							 		 
							 case 3 : value = ps.admmitPatient(modelp);
							 		  s= value>0?"Patient Admmit Sucessfully":"Some Problem to admmit Patient";
							 		  System.out.println(s);
							 		  
							 break;
							 
							 case 4 : value =ps.dischargePatinet(modelp);
							 		  s=value>0?"Discharge Patient Sucessfully":"Patient not Admmited";
							 		  System.out.println(s); 
							 break;
							 
							 case 0: System.out.println("Back.....");
							 break;
							 
							 default : System.out.println("Wrong Option..");
							 }
						 }
					}
					else if(value==0)
						System.out.println("Patient not Appointed");
					else
						System.out.println("Patient Id not found");
					no=1;
			break;
			
			//-------------------------------------------------Case 3-------------------------------------------------------------------
			case 3:  b = ps.viewAllPatient();
					if(!b)
					{
						System.out.println("Empty Patinet List");
					}	
			break;
			
			//------------------------------------------------Case 4--------------------------------------------------------------------
			case 4: b= ps.viewAdmmitPatients();
				if(!b)
				System.out.println("Not any patient admmited now");
			break;
			
			
			case 0: System.out.println("Logout......");
			break;
			
			default : System.out.println("Invalid input");
			}
		}
	}
}
