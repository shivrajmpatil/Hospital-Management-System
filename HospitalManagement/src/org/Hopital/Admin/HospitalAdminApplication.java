package org.Hopital.Admin;
import java.util.*;
import org.Hopital.Model.DoctorModel;
import org.Hopital.Model.ReceptionistModel;
import org.Hospital.Helper.PathHelper;

public class HospitalAdminApplication {
	
	public static void main(String x[])
	{
		Scanner scan = new Scanner(System.in);
		int no;
		boolean yes = true;
		while(yes)
		{
			System.out.println("LOGIN-->\n\t=>1.ADMIN.\n\t=>2.DOCTOR.\n\t=>3.RECEPTIONIST.");
			System.out.println("Enter your choice to proceed...!");
			no=scan.nextInt();
		
			switch(no)
			{	//----------------------------------------------Case 1---------------------------------------------------------------------------
				case 1: System.out.println("Admin :");
						scan.nextLine();	
							PathHelper phelp = new PathHelper();
							System.out.println("Enter UserName");
							String name = scan.nextLine();
							System.out.println("Enter Password");
							String pass = scan.nextLine();
							String u = PathHelper.p.getProperty("adminuser");
							String s = PathHelper.p.getProperty("adminpass");
							if(name.equals(u) && pass.equals(s))	
							{
								AdminLogin a = new AdminLogin();
								System.out.println("Admin Login Sucessfully....");
								no=a.adminLogin();
							}
							else
								System.out.println("Invalid Details..");
							
				break;
				
				
				//----------------------------------------------------Case 2----------------------------------------------------------------------
				case 2: System.out.println("Doctor :");
						scan.nextLine();
						System.out.println("Enter UserName");
						name = scan.nextLine();
						System.out.println("Enter Password");
						pass = scan.nextLine();
						
						DoctorLogin d = new DoctorLogin();
						DoctorModel dmodel = new DoctorModel();
						dmodel.setUsername(name);
						dmodel.setPassword(pass);
						int id = d.checkDoctor(dmodel);
						
						if(id>0)
						{
							System.out.println("Doctor Login Sucessfully...");
							d.doctorLogin(id);
						}
						else
							System.out.println("Invalid Username and Password");
				
				break;
				
				
				//---------------------------------------------------------Case 3--------------------------------------------------------------
				case 3: System.out.println("Receptionist :");
						scan.nextLine();
						System.out.println("Enter UserName");
						name = scan.nextLine();
						System.out.println("Enter Password");
						pass = scan.nextLine();
						
						ReceptionistLogin r = new ReceptionistLogin();
						ReceptionistModel rmodel = new ReceptionistModel();
						rmodel.setUsername(name);
						rmodel.setPassword(pass);
						int value = r.checkReceptinist(rmodel);
						
						if(value==1)
						{	
							System.out.println("Receptionist Login Sucessfully...");
							r.receptionistLogin();
						}
						else
							System.out.println("Invalid Username and Password");
				break;
				
				default : System.out.println("Invalid choice");
			}

		}
	}
}

	