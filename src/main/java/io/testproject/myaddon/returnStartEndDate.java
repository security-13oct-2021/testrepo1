package io.testproject.myaddon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;



@Action(name = "Return Start and End Date for DB query")
public class returnStartEndDate implements GenericAction {

    @Parameter(description = "Date Range", direction = ParameterDirection.INPUT)
    private String dateRange;

    @Parameter(description = "Start Date", direction = ParameterDirection.OUTPUT)
    private String startDate;
    
    @Parameter(description = "End Date", direction = ParameterDirection.OUTPUT)
    private String endDate;

   
    public ExecutionResult execute(AddonHelper helper) throws FailureException {
    	String[] dates = dateRange.split("-");
		String sDate = dates[0];
		String eDate = dates[1];
		Date date = new Date();
		SimpleDateFormat fromFormat = new SimpleDateFormat("MMMd,yyyy");
		SimpleDateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");
				
		try {
			Date date1 = fromFormat.parse(sDate);
			startDate = toFormat.format(date1);
			if (eDate.equalsIgnoreCase("TODAY")) {
				endDate = toFormat.format(date);
			}else {
				Date date2 = fromFormat.parse(eDate);				
				endDate = toFormat.format(date2);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    	
        return ExecutionResult.PASSED;
    }
    
}