package ReadWriteCSVFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSV {

	public static void putData(String path, String name, String id, String email) throws IOException
	{
		FileWriter write = new FileWriter(path,true);
		String separator = ",";
		String nextline = "\n";		
	
		write.append(name);
		write.append(separator);
		write.append(id);
		write.append(separator);
		write.append(email);
		write.append(nextline);		
	
		System.out.println("Data Entered");
		
		write.flush();
		write.close();		
		
	}
	
	public static void main(String[] args) throws IOException {
	      // csv file to read
	      String csvFile = "src"+File.separator+"test"+File.separator+"resources"+File.separator+"TestData"+File.separator+"Test.csv";
	       WriteCSV.putData(csvFile, "Sahil", "46", "sahil@qainfotech");
	   }
}
