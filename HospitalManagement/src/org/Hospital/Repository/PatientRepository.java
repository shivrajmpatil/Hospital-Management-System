package org.Hospital.Repository;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

import org.Hopital.Model.PatientModel;

public class PatientRepository extends DBCconfig{
	
	List<PatientModel> list = new ArrayList<PatientModel>();

	//-------------------------------------------addPatient--------------------------------------------------------
		public boolean addPatient(PatientModel model)
		{
			int Pid= getPid();
			Pid++;
			try {
				  stmt = conn.prepareStatement("insert into Patient values(?,?,?,?,?,?,?,?)");
				  stmt.setInt(1, Pid);
				  stmt.setString(2, model.getName());
				  stmt.setString(3, model.getBloodgroup());
				  stmt.setInt(4, model.getAge());
				  stmt.setString(5, model.getGender());
				  stmt.setString(6, model.getContact());
				  stmt.setString(7, model.getDisease());
				  stmt.setString(8, model.getAddress());
				  
				  int value = stmt.executeUpdate(); 
				  return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("addPatient ex ="+ex);
				return false;
			}
		}
		
		
		//-----------------------------------------------getPid-------------------------------------------------------
		private int getPid()
		{
			try {
				stmt = conn.prepareStatement("select max(Pid) from Patient");
				rs = stmt.executeQuery();
				if(rs.next())
				{
					String S= rs.getString(1);	
					return Integer.parseInt(S);	
				}
				
				else
					return 0;
			}
			catch(Exception ex)
			{
				System.out.println("getPid ex ="+ex);
				return 0;
			}
		}
		
		
		//--------------------------------------------isPatientRemove----------------------------------------------------------
		public boolean isPatientRemove(PatientModel model)
		{
			try {
				stmt = conn.prepareStatement("delete from Patient where Pid=?");
				stmt.setInt(1, model.getPid());
				
				int value = stmt.executeUpdate(); 
				  return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("isPatientRemove"+ex);
				return false;
			}
		}
		
		
		//--------------------------------------------viewPatientList-----------------------------------------------------------
		public List<PatientModel> viewPatientList()
		{
			list.clear();
			try {
				stmt = conn.prepareStatement("select *from Patient");
				rs = stmt.executeQuery();
				
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					model.setPid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setBloodgroup(rs.getString(3));
					model.setAge(rs.getInt(4));
					model.setGender(rs.getString(5));
					model.setContact(rs.getString(6));
					model.setDisease(rs.getString(7));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("viewPatientList ex="+ex);
				return null;
			}
		}
		
		
		//-------------------------------------------updateAppointment---------------------------------------------------------------
		public boolean updateAppointment(PatientModel model)
		{
			boolean b = checkDate(model);
			if(!b)
			{
				System.out.println("You Insert Previous Date");
				return false;
			}
			try {
				stmt = conn.prepareStatement("update patientstatus set AppointmentDate=?, Disease=?, Did=? where Pid=?");
				stmt.setDate(1, model.getAppointmentDate());
				stmt.setString(2, model.getDisease());
				stmt.setInt(3, model.getDid());
				stmt.setInt(4, model.getPid());
				
				int value = stmt.executeUpdate();
				
				return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("updateAppointment ex="+ex);
				return false;
			}
		}
		
		
		//----------------------------------------------addAppointment----------------------------------------------------------------
		public boolean addAppointment(PatientModel model)
		{
			boolean b = checkDate(model);
			if(!b)
				return false;
			try {
				stmt = conn.prepareStatement("insert into patientstatus(Pid,AppointmentDate,Disease,Did) values(?,?,?,?)");
				stmt.setInt(1, model.getPid());
				stmt.setDate(2, model.getAppointmentDate());
				stmt.setString(3, model.getDisease());
				stmt.setInt(4, model.getDid());
				
				int value = stmt.executeUpdate();
				return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("addAppointment ex ="+ex);
				return false;
			}
		}
		
		
		//-----------------------------------------------viewAppointedPatients---------------------------------------------------------
		public List<PatientModel> viewAppointedPatients(int did)
		{
			list.clear();
			Date ld = Date.valueOf(LocalDate.now());
			System.out.println(ld);
			try {
				stmt = conn.prepareStatement("select p.Pid, p.Name, ps.AppointmentDate, ps.category from patient p inner join patientstatus ps on p.pid=ps.pid where ps.did=? and ps.AppointmentDate >= ? order by ps.AppointmentDate");
				stmt.setDate(2, ld);
				stmt.setInt(1, did);
				rs = stmt.executeQuery();
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					model.setPid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setAppointmentDate(rs.getDate(3));
					model.setCategory(rs.getString(4));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("viewAppointedPatients ex ="+ex);
				return null;
			}
		}
		
		
		//-----------------------------------------getPatient------------------------------------------------------------------------------
		public List<PatientModel> getPatient(PatientModel model)
		{
			list.clear();
			try {
				stmt = conn.prepareStatement("select p.name, p.bloodgroup, p.age, ps.category from patient p inner join patientstatus ps where ps.pid=? and p.pid=?");
				stmt.setInt(1,model.getPid());
				stmt.setInt(2, model.getPid());
				rs = stmt.executeQuery();
				if(rs.next())
				{
					PatientModel modelp = new PatientModel();
					modelp.setName(rs.getString(1));
					modelp.setBloodgroup(rs.getString(2));
					modelp.setAge(rs.getInt(3));
					modelp.setCategory(rs.getString(4));
					
					list.add(modelp);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("getPatient ex="+ex);
				return null;
			}
		}
		
		
		//-------------------------------------------------------isPid-----------------------------------------------------------------
		public boolean isPid(PatientModel model)
		{
			try {
				stmt = conn.prepareStatement("select *from patient where pid=?");
				stmt.setInt(1, model.getPid());
				rs = stmt.executeQuery();
				return rs.next();
			}
			catch(Exception ex)
			{
				System.out.println("isPid ex ="+ex);
				return false;
			}
		}
		
		
		//---------------------------------------------------updatePrescription-------------------------------------------------------
		public boolean updatePrescription(PatientModel model)
		{
			try {
				stmt = conn.prepareStatement("update patientstatus set prescription=? where pid=?");
				stmt.setString(1, model.getPrescription());
				stmt.setInt(2, model.getPid());
				int value = stmt.executeUpdate();
				return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("updatePrescription ex ="+ex);
				return false;
				
			}
		}
		
		
		//-----------------------------------------------------getPrescription-----------------------------------------------------------
		public List<PatientModel> getPrescription(PatientModel model)
		{
			list.clear();
			try {
				stmt = conn.prepareStatement("select prescription from patientstatus where pid=?");
				stmt.setInt(1, model.getPid());
				rs = stmt.executeQuery();
				if(rs.next())
				{
					PatientModel modelp = new PatientModel();
					modelp.setPrescription(rs.getString(1));
					list.add(modelp);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("getPrescription ex ="+ex);
				return null;
			}
		}
		
		
		//---------------------------------------------------viewAllAppointedPatients--------------------------------------------------------------
		public List<PatientModel> viewAllAppointedPatients()
		{
			Date ld = Date.valueOf(LocalDate.now());
			System.out.println(ld);
			list.clear();
			try {
				stmt = conn.prepareStatement("select p.Pid, p.Name, ps.AppointmentDate, ps.Did, ps.category from patient p inner join patientstatus ps where p.pid=ps.pid and ps.AppointmentDate>=? order by ps.AppointmentDate");
				stmt.setDate(1, ld);
				rs = stmt.executeQuery();
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					model.setPid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setAppointmentDate(rs.getDate(3));
					model.setDid(rs.getInt(4));
					model.setCategory(rs.getString(5));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("viewAllAppointedPatients ex ="+ex);
				return null;
			}
		}
		

		//--------------------------------------------------admmitPatient-----------------------------------------------------------------
		public boolean admmitPatient(PatientModel model)
		{
			try {
				LocalDate d = LocalDate.now();
				Date sqld = Date.valueOf(d);
				
				stmt = conn.prepareStatement("update patientstatus set AdmmitDate=?, category=? where pid=?");
				stmt.setDate(1, sqld);
				stmt.setString(2, "IPD");
				stmt.setInt(3, model.getPid());
				
				int value = stmt.executeUpdate();
				return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("admmitPatient ex="+ex);
				return false;
			}
		}
		
		
		//-------------------------------------------------dischargePatient---------------------------------------------------------------
		public boolean dischagePatient(PatientModel model)
		{
			try {
				
				LocalDate d= LocalDate.now();
				Date sqld = Date.valueOf(d);
				
				stmt = conn.prepareStatement("update patientstatus set DischargeDate=?, category=? where pid=? and category=?");
				stmt.setDate(1, sqld);
				stmt.setString(2, "OPD");
				stmt.setInt(3, model.getPid());
				stmt.setString(4, "IPD");
				
				int value = stmt.executeUpdate();
				return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("dischargePatient ex ="+ex);
				return false;
			}
		}
		
		
		//-------------------------------------------------viewAdmmitedPatients-----------------------------------------------------------------
		public List<PatientModel> viewAdmmitedPatients()
		{
			list.clear();
			try {
				stmt = conn.prepareStatement("select p.Pid, p.Name, ps.AdmmitDate, ps.Did from patient p inner join patientstatus ps where p.pid=ps.pid and ps.category=? order by ps.AdmmitDate");
				stmt.setString(1, "IPD");
				rs = stmt.executeQuery();
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					model.setPid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setAdmmitDate(rs.getDate(3));
					model.setDid(rs.getInt(4));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("viewAllAppointedPatients ex ="+ex);
				return null;
			}
		}
		
		
		//-------------------------------------------------checkDate---------------------------------------------------------------------
		private boolean checkDate(PatientModel model)
		{
			Date ld = Date.valueOf(LocalDate.now());
			Date ad = model.getAppointmentDate();
			
			int check = ad.compareTo(ld);
			return check<0?false:true;
			
		}
		
		
		//=================================================Search Patients===============================================================
		public List<PatientModel> getPatientById(int id)
		{
			try {
				stmt = conn.prepareStatement("select name, contact, address from patient where pid=?");
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					
					model.setName(rs.getString(1));
					model.setContact(rs.getString(2));
					model.setAddress(rs.getString(3));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("getPatientById ex ="+ex);
				return null;
			}
		}
		
		public List<PatientModel> getPatientByName(String name)
		{
			try {
				stmt = conn.prepareStatement("select pid, contact, address from patient where name=?");
				stmt.setString(1, name);
				rs = stmt.executeQuery();
				
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					
					model.setPid(rs.getInt(1));
					model.setContact(rs.getString(2));
					model.setAddress(rs.getString(3));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("getPatientByName ex ="+ex);
				return null;
			}
		}
		
		public List<PatientModel> getPatientByDisease(String disease)
		{
			try {
				stmt = conn.prepareStatement("select pid, name, contact, address from patient where disease=?");
				stmt.setString(1, disease);
				rs = stmt.executeQuery();
				
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					
					model.setPid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setContact(rs.getString(3));
					model.setAddress(rs.getString(4));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("getPatientByDisease ex ="+ex);
				return null;
			}
		}
	
		public List<PatientModel> getPatientByCategory(String category)
		{
			try {
				stmt = conn.prepareStatement("select p.pid, p.name, p.contact, p.address from patient p inner join patientstatus ps on p.pid=ps.pid where ps.category=?");
				stmt.setString(1, category);
				rs = stmt.executeQuery();
				
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					
					model.setPid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setContact(rs.getString(3));
					model.setAddress(rs.getString(4));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("getPatientByCategory ex ="+ex);
				return null;
			}
		}
		
		public List<PatientModel> getPatientByDate(String date)
		{
			Date d = Date.valueOf(date);
			try {
				stmt = conn.prepareStatement("select p.pid, p.name, p.contact, p.address from patient p inner join patientstatus ps on p.pid=ps.pid where ps.AppointmentDate=?");
				stmt.setDate(1, d);
				rs = stmt.executeQuery();
			
				while(rs.next())
				{
					PatientModel model = new PatientModel();
					model.setPid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setContact(rs.getString(3));
					model.setAddress(rs.getString(4));
					
					list.add(model);
				}
				System.out.println(list.size());
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("getPatientByDate ex ="+ex);
				return null;
			}
		}
}
