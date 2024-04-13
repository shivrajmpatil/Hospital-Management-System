package org.Hospital.Repository;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.sql.*;
import org.Hospital.Helper.PathHelper;

public class DBCconfig {

	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	protected ResultSet rr;
	
	public DBCconfig()
	{
		try {
				PathHelper phelp = new PathHelper();
				Class.forName(PathHelper.p.getProperty("driver"));
				
				conn= DriverManager.getConnection(PathHelper.p.getProperty("url"),PathHelper.p.getProperty("user"),PathHelper.p.getProperty("password"));
				
				String s = conn!=null?"Database connected":"Some problem to connect database";
				//System.out.println(s);
		
			}
		
	catch(Exception e) {
		System.out.println("Errror is "+e);
		}
	
	}
}
