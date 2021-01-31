package com.seleniumeasy.api.testscripts;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumeasy.framework.api.BookerApiClientUtil;
import com.seleniumeasy.framework.reporting.ReportUtility;

/**
 * This is a test case to Test positive & negative
 * test cases of Delete Booking
 * 
 * @author Rakesh
 *
 */
public class DeleteBooking extends ApiBaseBooking{
	
	/**
	 * This method test for Positive Booking Deletion Flow
	 * 
	 * @throws IOException
	 */
	@Test(priority=6)
	public void deleteBookingTest()throws IOException{
    	
    	logger.info("*****INSIDE DELETE*****");
    	
    	ReportUtility.setReportingData("deleteBookingTest", "API_TC_07_DeleteBookingPositiveFlow", 
				"Verify if the booking is deleted",
				"Verify the deleted booking", "Successful booking ID deletion", 
				"Booking id successfully deleted", "Failed to verify the booking");
    			
    	HttpResponse response = BookerApiClientUtil.fetchDeleteResponse(clientUrl+createdBookingId, true, headerMap);
    	
    	logger.debug("Deleted Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_CREATED);
    	
    	String responseMessage = BookerApiClientUtil.getResponseMessage(response.getEntity());
  
    	String entity = gson.fromJson(responseMessage, String.class);
    	logger.debug("Deleted Response==>"+entity.toString());
    	
    	BookerApiClientUtil.writeResponseMessage(responseMessage, "4DeleteResponseP.json");
    	
    	Assert.assertEquals(entity, "Created");
    	ReportUtility.reportingResults("Pass", "Deletion of Booking ID",
				"Successfully deleted booking ID is "+createdBookingId,
				"User should be able to delete a booking ID");
    	logger.info("*****END OF DELETE*****");		
	}
	
	/**
	 * This method test for Negative Booking Deletion Flow
	 * 
	 * @throws IOException
	 */
	@Test(priority=7)
	public void deleteBookingTestNegative() throws IOException {
    	
    	logger.info("*****INSIDE DELETE*****");
    	
    	ReportUtility.setReportingData("deleteBookingTestNegative", "API_TC_08_DeleteBookingNegativeFlow", 
				"Verify if the booking is not deleted",
				"Verify the booking is not deleted", "Failed to delete Booking", 
				"Booking id not deleted", "Failed to verify the status");
    		
    	HttpResponse response = BookerApiClientUtil.fetchDeleteResponse(clientUrl+createdBookingIdforNegative, false, headerMap);
    	
    	logger.debug("Deleted Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
    	
    	logger.info("*****END OF DELETE*****");	
    	ReportUtility.reportingResults("Pass", "Booking ID not deleted",
				"Received failure response code is "+response.getStatusLine().getStatusCode(),
				"User should not be able to delete a booking without proper authorization token");
	}

}
