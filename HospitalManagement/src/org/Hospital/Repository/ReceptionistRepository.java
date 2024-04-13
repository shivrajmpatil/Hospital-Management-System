package org.Hospital.Repository;
import java.util.*;
import org.Hopital.Model.ReceptionistModel;

public class ReceptionistRepository extends DBCconfig{
	
	List<ReceptionistModel> list = new ArrayList<ReceptionistModel>();

	//-------------------------------------------addReceptionist--------------------------------------------------------
		public boolean addReceptionist(ReceptionistModel model)
		{
			int Rid= getRid();
			Rid++;
			try {
				  stmt = conn.prepareStatement("insert into Receptionist values(?,?,?,?,?,?)");
				  stmt.setInt(1, Rid);
				  stmt.setString(2, model.getName());
				  stmt.setString(3, model.getGender());
				  stmt.setString(4, model.getContact());
				  stmt.setString(5, model.getUsername());
				  stmt.setString(6, model.getPassword());
				  
				  int value = stmt.executeUpdate(); 
				  return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("addReceptionist ex ="+ex);
				return false;
			}
		}
		
		
		//-----------------------------------------------getRid-------------------------------------------------------
		private int getRid()
		{
			try {
				stmt = conn.prepareStatement("select max(Rid) from Receptionist");
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
				//System.out.println("getRid ex ="+ex);
				return 0;
			}
		}
		
		
		//------------------------------------------------isReceptionist----------------------------------------------
		public boolean isReceptionist(ReceptionistModel model)
		{
			try {
				stmt = conn.prepareStatement("select *from Receptionist where UserName=?");
				stmt.setString(1, model.getUsername());
				rs = stmt.executeQuery();
				return rs.next();
			}
			catch(Exception ex)
			{
				System.out.println("isReceptionist ex="+ex);
				return false;
			}
		}
		
		//----------------------------------------------isReceptionistRemove--------------------------------------------
		public boolean isReceptionistRemove(ReceptionistModel model)
		{
			try {
				stmt = conn.prepareStatement("delete from Receptionist where Rid=? and UserName=?");
				stmt.setInt(1, model.getRid());
				stmt.setString(2, model.getUsername());
				
				int value = stmt.executeUpdate(); 
				  return value>0?true:false;
			}
			catch(Exception ex)
			{
				System.out.println("isReceptionistRemove"+ex);
				return false;
			}
		}
		
		
		//----------------------------------------------checkUserPass---------------------------------------
		public boolean checkUserPass(ReceptionistModel model)
		{
			try {
				stmt = conn.prepareStatement("select *from Receptionist where UserName=? and Password=?");
				stmt.setString(1, model.getUsername());
				stmt.setString(2, model.getPassword());
				
				rs = stmt.executeQuery();
				return rs.next();
			}
			
			catch(Exception ex)
			{
				System.out.println("checkUserPass ex ="+ex.getMessage());
				return false;
			}
		}
		
		//--------------------------------------------viewReceptionistList-----------------------------------------------
		public List<ReceptionistModel> viewReceptionistList()
		{
			list.clear();
			try {
				stmt = conn.prepareStatement("select *from Receptionist");
				rs = stmt.executeQuery();
				
				while(rs.next())
				{
					ReceptionistModel model = new ReceptionistModel();
					model.setRid(rs.getInt(1));
					model.setName(rs.getString(2));
					model.setGender(rs.getString(3));
					model.setContact(rs.getString(4));
					model.setUsername(rs.getString(5));
					
					list.add(model);
				}
				return list.size()>0?list:null;
			}
			catch(Exception ex)
			{
				System.out.println("viewReceptionistList ex="+ex);
				return null;
			}
		}
}
