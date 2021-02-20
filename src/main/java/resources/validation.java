package resources;

import resources.base;

public class validation extends base {
	
	public boolean validateLog (String fieldName, String inputGiven, String printedValue, String validateFlag)
	{
		
		
		if(!inputGiven.equalsIgnoreCase(""))
		{
			re.logReport("Info", "Input given for " + fieldName + ":" + inputGiven, "");	
			re.logReport("Info", "Accepted value for " + fieldName + ":" + printedValue, "");
			
			if (validateFlag.equalsIgnoreCase("true"))
			{
				if (inputGiven.equalsIgnoreCase(printedValue) )
				{
					re.logReport("Pass",fieldName + ": Value matched", "");
					return true ;
				}
				else
				{
					re.logReport("Fail",fieldName + ": Value didn't matched", "");
					return false ;
				}
			}
			else
			{
				if (inputGiven.equalsIgnoreCase(printedValue) )
				{
					re.logReport("Fail","<Negative Testing>" + fieldName + ": Value matched", "");
					return false ;
				}
				else
				{
					re.logReport("Pass","<Negative Testing>" + fieldName + ": Value didn't matched", "");
					return true ;
				}
			}
		}
		else
		{
			re.logReport("info",  "No input given for " + fieldName, "");
			return true;
		}
		
		
	}
	
	
	public boolean validateLogAlerts(String fieldDes, String alertMsg,String alertFlag )
	{
		if(!alertMsg.equalsIgnoreCase(""))
		{
			re.logReport("Alert", "Alert message for " + fieldDes + ":" + alertMsg , "");
			if(alertMsg.contains("OTP sent successfully"))
			{
				re.logReport("Pass", "OTP Triggered", "");
				return true;
			}
			else if(alertFlag.equalsIgnoreCase("true"))
			{
				re.logReport("Fail", fieldDes + ": Shoudn't have displayed Alert", "");
				return false;
				
			}
			else
			{
				re.logReport("Pass", fieldDes + ": Alert displayed", "");
				return true;
			}
			
		}
		else
		{
			if(alertFlag.equalsIgnoreCase("false"))
			{
				re.logReport("Fail",  fieldDes + ": Shoud have displayed Alert", "");
				return false;
			}
			else
			{
				re.logReport("Pass", fieldDes + ": No Alerts found", "");
				return true;
			}
		}
		
	}
	
	

}
