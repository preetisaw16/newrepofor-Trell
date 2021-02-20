package resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reporter {
	
	public static Logger log =LogManager.getLogger(base.class.getName());
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	private ArrayList<String> tResult = new ArrayList<String>();
	private ArrayList<String> tMessage = new ArrayList<String>();
	private ArrayList<String> ssPath = new ArrayList<String>();
	
	
	public void logReport(String result, String message, String screenshotPath) 
	{
		
		tResult.add(result);
		tMessage.add(message);
		ssPath.add(screenshotPath);
		
		/*if (result.equalsIgnoreCase("Alert"))
		{
			String[] arr_message = message.split(":");
			if (arr_message[1].equalsIgnoreCase(" "))
			{
				tResult.add(result);
				tMessage.add(arr_message[0] + ": No alerts found");
				ssPath.add(screenshotPath);
			}
			else
			{
				tResult.add(result);
				tMessage.add(message);
				ssPath.add(screenshotPath);
			}
		}
		else 
		{
			tResult.add(result);
			tMessage.add(message);
			ssPath.add(screenshotPath);
		}*/
		

		log.info(message);
		
	}
	
	public void writeFile(String value){
	    String PATH = "D:\\Metrics\\";
	    String directoryName = PATH.concat(value);
	    String timeStamp = sdf.format(timestamp);
	    String fileName = value + timeStamp + ".txt";

	    File directory = new File(directoryName);
	    if (! directory.exists()){
	        directory.mkdirs();
	        // If you require it to make the entire directory path including parents,
	        // use directory.mkdirs(); here instead.
	    }

	    File file = new File(directoryName + "/" + fileName);
	    try{
	        FileWriter fw = new FileWriter(file.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);
	        for (int cnt =0; cnt < tResult.size() ; cnt++)
	        {
	        	bw.write("[" + tResult.get(cnt) + "]: " + tMessage.get(cnt)  );
	        	bw.newLine();
	        }
	        
	        bw.close();
	    }
	    catch (IOException e){
	        e.printStackTrace();
	        System.exit(-1);
	    }
	}

}
	
