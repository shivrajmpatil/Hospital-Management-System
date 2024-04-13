package org.Hospital.Helper;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class PathHelper {

	public static String completePath="";
	public static Properties p = new Properties();
	
	public PathHelper()
	{
		try {
			Path currentDirectoryPath = FileSystems.getDefault().getPath("");
			String currentDirectory = currentDirectoryPath.toAbsolutePath().toString();
			
			completePath=currentDirectory+"\\src\\Resources\\DataBase.properties";
			
			FileInputStream finf = new FileInputStream(completePath);
				p.load(finf);
		}
		
		catch(Exception ex)
		{
			System.out.println("Error is ="+ex);
		}
		
	}
}
