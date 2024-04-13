package org.Hopital.Admin;

import java.sql.Date;
import java.util.Scanner;
import org.Hopital.Model.PatientModel;
import org.Hopital.Model.ReceptionistModel;
import org.Hospital.Service.DoctorService;
import org.Hospital.Service.PatientService;
import org.Hospital.Service.ReceptionistService;

public class ReceptionistLogin {
	
	PatientService ps = new PatientService();
	ReceptionistService rs = new ReceptionistService();
	DoctorService ds = new DoctorService();
	
	public int checkReceptinist(ReceptionistModel model)
	{
		return rs.checkLogin(model)?1:0;
	}

	public int receptionistLogin()
	{	
		Scanner scan = new Scanner(System.in);
		int no=1;
		while(no!=0)
		{
			System.out.println("===================================================================================================================================================================================");
			System.out.println("1. Add new Patient\n2.Remove Patient\n3.View all Patients List\n4.View Doctors List\n5.Appoint Patient\n6.List Appointed Patient\n7.List IPD Patient\n8.Search Patient\n9.Update Patient \n0.Logout.");
			System.out.println("Enter your choice to proceed...!");
			no=scan.nextInt();
			
			switch(no)
			{
			//----------------------------------------------Case1---------------------------------------------------------
			case 1: scan.nextLine();
					System.out.println("Enter Patient Name");
					String name = scan.nextLine();
					System.out.println("Enter BloodGroup");
					String blood = scan.nextLine();
					System.out.println("Enter Age");
					int age=scan.nextInt();
					scan.nextLine();
					System.out.println("Enter Gender");
					String gender = scan.nextLine();
					System.out.println("Enter contact");
					String contact = scan.nextLine();
					System.out.println("Enter Disease");
					String disease = scan.nextLine();
					System.out.println("Enter Address");
					String Address = scan.nextLine();
					
					PatientModel Pmodel = new PatientModel(name,blood,age,gender,contact,disease,Address);
					
					int value = ps.isPatientAdd(Pmodel);
					
					String s = value==1?"Patient Added Successfully..":"Something went wrong";
					System.out.println(s);
			break;
			
			//------------------------------------------Case2--------------------------------------------------------
			case 2: scan.nextLine();
					System.out.println("Enter Patient id");
					int Pid = scan.nextInt();
				
					PatientModel Pmodelr = new PatientModel();
				    Pmodelr.setPid(Pid);
				    
				    value = ps.isRemovePatient(Pmodelr);
				    s = value==1?"Patient Remove Successfully..":value==0?"Id not match":"Something went wrong";
					System.out.println(s);
			break;
			
			//------------------------------------------Case3---------------------------------------------------------
			case 3: boolean b = ps.viewAllPatient();
					if(!b)
					{
						System.out.println("Empty Patinet List");
					}
		    break;
		    
		    //------------------------------------------Case4--------------------------------------------------------
			case 4: b = ds.viewAllDoctor();
			if(!b)
				System.out.println("Empty Doctor list");
			break;
			
		    //------------------------------------------Case5---------------------------------------------------------
			case 5: System.out.println("Enter Patient Id");
					Pid = scan.nextInt();
					scan.nextLine();
					System.out.println("Enter Appointment Date yyyy-mm-dd");
					String date = scan.nextLine();
					System.out.println("Enter Disease");
					disease = scan.nextLine();
					System.out.println("Enter Doctor Id");
					int Did = scan.nextInt();
					Date d1= Date.valueOf(date);
					PatientModel modelu = new PatientModel();
					modelu.setPid(Pid);
					modelu.setAppointmentDate(d1);
					modelu.setDid(Did);
					modelu.setDisease(disease);
					value = ps.setAppointment(modelu);
					s= value!=-1?"AppointMent Done...":"Some problem is there";
					System.out.println(s);
			break;
			
			//------------------------------------------Case6---------------------------------------------------------
			case 6:  b = ps.viewAllAppointedPatient();
			if(!b)
				System.out.println("Appointed Patient List is Empty");
			break;
			
			//---------------------------------------------Case7------------------------------------------------------------
			case 7 : b= ps.viewAdmmitPatients();
			if(!b)
				System.out.println("Not any patient admmited now");
			break;
			
			//-------------------------------------------Case8-----------------------------------------------------------------
			case 8 : 
				while(no!=0)
				{
					System.out.println("Patient Search Options :\n1.Id\n2.Name\n3.Diases\n4.Category\n5.Appointment Date\n0.Back.");
					no = scan.nextInt();
					
					switch(no)
					{
					case 1 : System.out.println("Enter Patient Id");
							 Pid = scan.nextInt();
							 ps.searchPatientById(Pid);
							 
					break;
					
					case 2 : scan.nextLine();
							 System.out.println("Enter Patient Name");
							 name = scan.nextLine();
							 ps.searchPatientByName(name);		 
					break;
					
					case 3 : scan.nextLine();
					 		 System.out.println("Enter Diases");
							 disease = scan.nextLine();
							 ps.searchPatientByDisease(disease);
					break;
					
					case 4 : System.out.println("Select Category\n1.OPD\n2.IPD");
							 value = scan.nextInt();
							 String category=null;
							 switch(value)
							 {
							 case 1: category="OPD";
							 break;
							 case 2: category="IPD";
							 default : System.out.println("Wrong choice");
							 }
							 if(category!=null)
								 ps.searchPatientByCategory(category);			 		
					break;
					
					case 5 : scan.nextLine();
					  	     System.out.println("Enter Date yyyy-mm-dd");
							 date = scan.nextLine();
							 ps.searchPatientByDate(date);		 
					break;
					
					case 0 : System.out.println("Back....");
					break;
					
					default : System.out.println("Invalid Option");
					}
				}
				no=1;
				
			break;
			case 0: System.out.println("Logout....");
			break;
					
			default: System.out.println("Invalid choice");
			}
			
		}
		return 0;
	}
}
