package org.Hopital.Admin;
import java.util.*;

import org.Hopital.Model.*;
import org.Hospital.Service.DoctorService;
import org.Hospital.Service.ReceptionistService;
public class AdminLogin {
	
	DoctorService ds = new DoctorService();
	ReceptionistService rs = new ReceptionistService();

	public int adminLogin()
	{	
		Scanner scan = new Scanner(System.in);
		int no=1;
		while(no!=0)
		{
			System.out.println("===================================================================================================================================================================================");
			System.out.println("1. Add new Doctor\n2.Remove Doctor\n3.Add Specilization\n4.View Specilization\n5.Add Receptionist\n6.Remove Receptionist\n7.View Doctor List\n8.View Receptionist List\n0.Logout.");
			System.out.println("Enter your choice to proceed...!");
			no=scan.nextInt();
			
			switch(no)
			{
			//----------------------------------------------Case1---------------------------------------------------------
			case 1: scan.nextLine();
					System.out.println("Enter Doctor Name");
					String name = scan.nextLine();
					System.out.println("Enter Specilization");
					String spe = scan.nextLine();
					System.out.println("Enter Gender");
					String gender = scan.nextLine();
					System.out.println("Enter contact");
					String contact = scan.nextLine();
					System.out.println("Enter Username");
					String user = scan.nextLine();
					System.out.println("Enter Password");
					String pass = scan.nextLine();
					
					DoctorModel Dmodel = new DoctorModel(name,spe,gender,contact,user,pass);
					
					int value = ds.isDoctorAdd(Dmodel);
					
					String s = value==1?"Doctor Added Successfully..":value==0?"UserName already present":"Some Problem is there";
					System.out.println(s);
			break;
			
			//---------------------------------------------Case2--------------------------------------------------------
			case 2: scan.nextLine();
					System.out.println("Enter Doctor id and username");
					int Did = scan.nextInt();
					scan.nextLine();
					String username = scan.nextLine();
					
					DoctorModel Dmodelr = new DoctorModel();
				    Dmodelr.setDid(Did);
				    Dmodelr.setUsername(username);
				    
				    value = ds.isRemoveDoctor(Dmodelr);
				    s = value==1?"Doctor Remove Successfully..":value==0?"Id Username not match":"Wrong UserName";
					System.out.println(s);
			break;
			
			
			//---------------------------------------------Case3---------------------------------------------------------
			case 3: scan.nextLine();
					System.out.println("Enter Specilization Name");
					spe = scan.nextLine();
					DoctorModel Dmodels = new DoctorModel();
					Dmodels.setSpecilization(spe);
					value = ds.isSpecilizationAdd(Dmodels);
					s = value>0?"Specilization Added Successfully..":value==0?"Specilization Alredy present":"Some problem is there";
					System.out.println(s);
			break;
			
			
			//-------------------------------------------Case4-----------------------------------------------------------
			case 4: boolean b = ds.viewSpecilization();
					if(!b)
						System.out.println("Empty Specilization List");
					
			break;	
			
			
			//------------------------------------------Case5---------------------------------------------------------
			case 5: scan.nextLine();
					System.out.println("Enter Receptionist Name");
					name = scan.nextLine();
					System.out.println("Enter Gender");
					gender = scan.nextLine();
					System.out.println("Enter contact");
					contact = scan.nextLine();
					System.out.println("Enter Username");
					user = scan.nextLine();
					System.out.println("Enter Password");
					pass = scan.nextLine();
			
			ReceptionistModel Rmodel = new ReceptionistModel(name,gender,contact,user,pass);
			
			value = rs.isReceptionistAdd(Rmodel);
			
			s = value==1?"Receptionist Added Successfully..":value==0?"UserName already present":"Some Problem is there";
			System.out.println(s);
			
			break;
			
			//-------------------------------------Case6--------------------------------------------------------------------
			case 6: scan.nextLine();
			System.out.println("Enter Receptionist id and username");
			int Rid = scan.nextInt();
			scan.nextLine();
			username = scan.nextLine();
			
			ReceptionistModel Rmodelr = new ReceptionistModel();
		    Rmodelr.setRid(Rid);
		    Rmodelr.setUsername(username);
		    
		    value = rs.isRemoveReceptionist(Rmodelr);
		    s = value==1?"Receptionist Remove Successfully..":value==0?"Id Username not match":"Wrong UserName";
			System.out.println(s);
			
			break;
			
			//----------------------------------------Case7----------------------------------------------------------------
			case 7: b = ds.viewAllDoctor();
					if(!b)
						System.out.println("Empty Doctor list");
			break;
			
			//----------------------------------------Case8--------------------------------------------------------------
			case 8:	b = rs.viewAllReceptionist();
					if(!b)
						System.out.println("Empty Receptionist list");	
			break;
			
			
			case 0: System.out.println("Logout....");
			break;
			default : 
				
				System.out.println("Invalid Input");
			}
			
		}
		return 0;
	}
}
