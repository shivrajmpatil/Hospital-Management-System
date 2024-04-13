package org.Hospital.Repository;

import java.util.*;
import org.Hopital.Model.DoctorModel;
import org.Hopital.Model.ReceptionistModel;

public class DoctorRepository extends DBCconfig{
	
	List<DoctorModel> listd = new ArrayList<DoctorModel>();
	
	List<DoctorModel> lists = new ArrayList<DoctorModel>();
	//-------------------------------------------addDoctor--------------------------------------------------------
	public boolean addDoctor(DoctorModel model)
	{
		int Did= getDid();
		int Sid= getSid(model);
		//System.out.println("Sid ="+Sid);
		if(Sid==0)
			return false;
		model.setSid(Sid);
		Did++;
		try {
			  stmt = conn.prepareStatement("insert into Doctor values(?,?,?,?,?,?,?)");
			  stmt.setInt(1, Did);
			  stmt.setString(2, model.getName());
			  stmt.setInt(3,model.getSid());
			  stmt.setString(4, model.getGender());
			  stmt.setString(5, model.getContact());
			  stmt.setString(6, model.getUsername());
			  stmt.setString(7, model.getPassword());
			  
			  int value = stmt.executeUpdate(); 
			  return value>0?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("addDoctor ex ="+ex);
			return false;
		}
	}
	
	
	//-----------------------------------------------getDid-------------------------------------------------------
	private int getDid()
	{
		try {
			stmt = conn.prepareStatement("select max(Did) from Doctor");
			rs = stmt.executeQuery();
			if(rs.next())
			{
				String S= rs.getString(1);	//max value get in String format ( If column is empty then occur exception
				return Integer.parseInt(S);	// return max by converting into int
			}
			
			else
				return 0;
		}
		catch(Exception ex)
		{
			//System.out.println("getDid ex ="+ex);
			return 0;
		}
	}
	
	
	//------------------------------------------------getSid---------------------------------------------------
	private int getSid(DoctorModel model)
	{
		try {
			stmt = conn.prepareStatement("select sid from specilization where Sname=?");
			stmt.setString(1, model.getSpecilization());
			rs = stmt.executeQuery();
			if(rs.next())
			return rs.getInt(1);
			else
			{ System.out.println("Specilization not found pls check specilization");
				return 0;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Specilization not found "+ex);
			return 0;
		}
	}
	
	
	//------------------------------------------------isDoctor----------------------------------------------
	public boolean isDoctor(DoctorModel model)
	{
		try {
			stmt = conn.prepareStatement("select *from Doctor where UserName=?");
			stmt.setString(1, model.getUsername());
			rs = stmt.executeQuery();
			return rs.next();
		}
		catch(Exception ex)
		{
			System.out.println("isDoctor ex="+ex);
			return false;
		}
	}
	
	
	//----------------------------------------------isDoctorRemove--------------------------------------------
	public boolean isDoctorRemove(DoctorModel model)
	{
		try {
			stmt = conn.prepareStatement("delete from Doctor where Did=? and UserName=?");
			stmt.setInt(1, model.getDid());
			stmt.setString(2, model.getUsername());
			
			int value = stmt.executeUpdate(); 
			  return value>0?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("isDoctorRemove"+ex);
			return false;
		}
	}
	
	
	//--------------------------------------------viewDoctorList-----------------------------------------------
	public List<DoctorModel> viewDoctorList()
	{
		listd.clear();
		try {
			stmt = conn.prepareStatement("select * from Doctor");
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				DoctorModel model = new DoctorModel();
				model.setDid(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setSid(rs.getInt(3));
				model.setGender(rs.getString(4));
				model.setContact(rs.getString(5));
				model.setUsername(rs.getString(6));
				//getSpecilization(model);
				
				try {
					stmt = conn.prepareStatement("select Sname from Specilization where sid=?");
					
					stmt.setInt(1, model.getSid());
					rr= stmt.executeQuery();
					if(rr.next())
					{
						model.setSpecilization(rr.getString(1));
					}
				}
				catch(Exception ex)
				{
					System.out.println("Exception in getSpecilization ="+ex);
				}
				
				listd.add(model);
			}
			return listd.size()>0?listd:null;
		}
		catch(Exception ex)
		{
			System.out.println("viewDoctorList ex="+ex);
			return null;
		}
	}
	
	
	//---------------------------------------------getSpecilization------------------------------------
//	private void getSpecilization(DoctorModel model)
//	{
//		try {
//			stmt = conn.prepareStatement("select Sname from Specilization where sid=?");
//			stmt.setInt(1, model.getSid());
//			rs= stmt.executeQuery();
//			if(rs.next())
//			{
//				model.setSpecilization(rs.getString(1));
//			}
//		}
//		catch(Exception ex)
//		{
//			System.out.println("Exception in getSpecilization ="+ex);
//		}
//	}
	
	//----------------------------------------------checkUserPass---------------------------------------
			public int checkUserPass(DoctorModel model)
			{
				try {
					stmt = conn.prepareStatement("select did from Doctor where UserName=? and Password=?");
					stmt.setString(1, model.getUsername());
					stmt.setString(2, model.getPassword());
					
					rs = stmt.executeQuery();
					if(rs.next())
					{
						return rs.getInt(1);
					}
					else
						return 0;
				}
				
				catch(Exception ex)
				{
					System.out.println("Doctor checkUserPass ex ="+ex);
					return 0;
					
				}
			}
		
			
	//---------------------------------------------addSpecilization---------------------------------------
			public boolean addSpecilization(DoctorModel model)
			{
				int Sid = getMaxSid();
					Sid++;
				try {
					stmt = conn.prepareStatement("insert into Specilization values(?,?)");
					stmt.setInt(1, Sid);
					stmt.setString(2, model.getSpecilization());
					int value = stmt.executeUpdate();
					return value>0?true:false;
				}
				catch(Exception ex)
				{
					System.out.println("addSpecilization ex ="+ex);
					return false;
				}
			}
			
	
	//---------------------------------------------getMaxSid----------------------------------------------------
			private int getMaxSid()
			{
				try {
					stmt = conn.prepareStatement("select max(Sid) from Specilization");
					rs = stmt.executeQuery();
					if(rs.next())
					{
						return rs.getInt(1);
					}
					else
						return 0;
				}
				catch(Exception ex)
				{
					System.out.println("getMaxSid ex = "+ex);
					return 0;
				}
			}
			
	
	//--------------------------------------------isSpecilization-------------------------------------------------
			public boolean isSpecilization(DoctorModel model)
			{
				try {
					stmt = conn.prepareStatement("select *from Specilization where Sname=?");
					stmt.setString(1, model.getSpecilization());
					rs = stmt.executeQuery();
					return rs.next();
				}
				catch(Exception ex)
				{
					return false;
				}
			}
		
			
	//-------------------------------------------specilizationList--------------------------------------------------
			public List<DoctorModel> specilizationList()
			{
				lists.clear();
				try {
					stmt = conn.prepareStatement("select *from Specilization order by sid");
					rs = stmt.executeQuery();
					while(rs.next())
					{
						DoctorModel model = new DoctorModel();
						model.setSid(rs.getInt(1));
						model.setSpecilization(rs.getString(2));
						lists.add(model);
					}
					return lists.size()>0?lists:null;
				}
				catch(Exception ex)
				{
					System.out.println("specilizationList ex ="+ex);
					return null;
				}
				
			}
}
