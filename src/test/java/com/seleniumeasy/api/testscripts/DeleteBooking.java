package com.seleniumeasy.api.testscripts;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumeasy.framework.api.BookerApiClientUtil;
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;

public class DeleteBooking extends ApiBaseBooking{
	
	@Test(priority=6)
	public void deleteBookingTest() throws ClientProtocolException, IOException {
    	
    	logger.info("*****INSIDE DELETE*****");
    	Global.sScriptName = "deleteBookingTest";
		Global.useCaseName = "API_TC_07_DeleteBookingPositiveFlow";
		Global.useCaseDescription = "Verify if the booking is deleted";
		Global.curHighLight = true;
		Global.curHeading = "Verify the deleted booking";
		Global.ER = "Successful booking ID deletion";
		Global.EAR = "Booking id successfully deleted";
		Global.UEAR = "Failed to verify the booking";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
    			
    	HttpResponse response = BookerApiClientUtil.fetchDeleteResponse(clientUrl+createdBookingId, true, headerMap);
    	
    	logger.debug("Deleted Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_CREATED);
    	
    	String responseMessage = BookerApiClientUtil.getResponseMessage(response.getEntity());
  
    	String entity = gson.fromJson(responseMessage, String.class);
    	logger.debug("Deleted Response==>"+entity.toString());
    	
    	BookerApiClientUtil.writeResponseMessage(responseMessage, "4DeleteResponseP.json");
    	
    	Assert.assertEquals(entity, "Created");
    	Utility.reportingResults("Pass", "Deletion of Booking ID",
				"Successfully deleted booking ID is "+createdBookingId,
				"User should be able to delete a booking ID");
    	logger.info("*****END OF DELETE*****");		
	}
	
	@Test(priority=7)
	public void deleteBookingTestNegative() throws ClientProtocolException, IOException {
    	
    	logger.info("*****INSIDE DELETE*****");
    	Global.sScriptName = "deleteBookingTestNegative";
		Global.useCaseName = "API_TC_08_DeleteBookingNegativeFlow";
		Global.useCaseDescription = "Verify if the booking is not deleted";
		Global.curHighLight = true;
		Global.curHeading = "Verify the booking is not deleted";
		Global.ER = "Failed to delete Booking";
		Global.EAR = "Booking id not deleted";
		Global.UEAR = "Failed to verify the status";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";		
    	HttpResponse response = BookerApiClientUtil.fetchDeleteResponse(clientUrl+createdBookingIdforNegative, false, headerMap);
    	
    	logger.debug("Deleted Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
    	
    	logger.info("*****END OF DELETE*****");	
    	Utility.reportingResults("Pass", "Booking ID not deleted",
				"Received failure response code is "+response.getStatusLine().getStatusCode(),
				"User should not be able to delete a booking without proper authorization token");
	}

}
