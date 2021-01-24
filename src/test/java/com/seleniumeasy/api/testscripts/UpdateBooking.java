package com.seleniumeasy.api.testscripts;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumeasy.api.testobjects.BookingVo;
import com.seleniumeasy.framework.api.BookerApiClientUtil;
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;

public class UpdateBooking extends ApiBaseBooking{
	
	@Test(priority=4)
	public void updateBookingTest() throws IOException, ParseException{
		//File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\UpdateRequestP.json");
    	
    	logger.info("*****INSIDE UPDATE*****");
    	Global.sScriptName = "updateBookingTest";
		Global.useCaseName = "API_TC_05_UpdateBookingPositiveFlow";
		Global.useCaseDescription = "Verify if the booking is updated";
		Global.curHighLight = true;
		Global.curHeading = "Verify the booking";
		Global.ER = "Successful booking update";
		Global.EAR = "Booking id successfully updated";
		Global.UEAR = "Failed to verify the booking";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
    	
    	HttpResponse response =  BookerApiClientUtil.fetchPutResponse(clientUrl+createdBookingId, 
    			new StringEntity(gson.toJson(bookingInputUpdate)), true, headerMap);
    	
    	logger.debug("Update Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    	
    	String responseMessage = BookerApiClientUtil.getResponseMessage(response.getEntity());
 
    	BookingVo entity = gson.fromJson(responseMessage, BookingVo.class);
    	logger.debug("Update Response==>"+entity.toString());
    	
    	BookerApiClientUtil.writeResponseMessage(responseMessage, "3UpdateResponseP.json");
    	
    	Assert.assertEquals(entity.getAdditionalneeds(), bookingInputUpdate.getAdditionalneeds());
    	logger.info("*****END OF UPDATE*****");
    	Utility.reportingResults("Pass", "Updating Booking ID",
				"Successfully updated booking ID is "+createdBookingId
				+" and Updated data is "+entity.getAdditionalneeds()
				+" After Update booking detail is: "+entity.toString(),
				"User should be able to update a booking");
		
	}
	
	@Test(priority=5)
	public void updateBookingTestNegative() throws IOException, ParseException{
		//File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\UpdateRequestP.json");
    	
    	logger.info("*****INSIDE UPDATE*****");
    	Global.sScriptName = "updateBookingTestNegative";
		Global.useCaseName = "API_TC_06_UpdateBookingNegativeFlow";
		Global.useCaseDescription = "Verify if the booking is not updated";
		Global.curHighLight = true;
		Global.curHeading = "Verify the booking is not updated";
		Global.ER = "Failed updating Booking";
		Global.EAR = "Booking id not updated";
		Global.UEAR = "Failed to verify the status";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
    	
    	HttpResponse response =  BookerApiClientUtil.fetchPutResponse(clientUrl+createdBookingIdforNegative, 
    			new StringEntity(gson.toJson(bookingInputUpdate)), false, headerMap);
    	
    	logger.debug("Update Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
    	Utility.reportingResults("Pass", "Booking not updated",
				"Received failure response code is "+response.getStatusLine().getStatusCode(),
				"User should not be able to update Booking details without authorization key");
    	logger.info("*****END OF UPDATE*****");
		
	}

}
