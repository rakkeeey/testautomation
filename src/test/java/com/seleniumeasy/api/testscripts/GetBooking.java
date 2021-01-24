package com.seleniumeasy.api.testscripts;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumeasy.api.testobjects.BookingVo;
import com.seleniumeasy.framework.api.BookerApiClientUtil;
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;

public class GetBooking extends ApiBaseBooking{
	
	@Test(priority=2)
	public void getBookingTest() throws ParseException, ClientProtocolException, IOException {
	  	//File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\GetRequestP.json");
		
		Global.sScriptName = "getBookingTest";
		Global.useCaseName = "API_TC_03_GetBookingPositiveFlow";
		Global.useCaseDescription = "Verify if the booking is fetched";
		Global.curHighLight = true;
		Global.curHeading = "Booking fetched Successfully";
		Global.ER = "Successful booking Details fetched";
		Global.EAR = "Booking id successfully fetched";
		Global.UEAR = "Failed to fetch the booking";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
    	
    	logger.info("*****INSIDE GET*****");
    	
    	HttpResponse response = BookerApiClientUtil.fetchGetResponse(clientUrl+createdBookingId, false, headerMap);

    	logger.debug("Get Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    	
    	String responseMessage = BookerApiClientUtil.getResponseMessage(response.getEntity());
    	
    	BookingVo entity = gson.fromJson(responseMessage, BookingVo.class);
    	logger.debug("Get Response==>"+entity.toString());
    	BookerApiClientUtil.writeResponseMessage(responseMessage, "2GetResponseP.json");
    	
    	Assert.assertEquals(entity.getAdditionalneeds(), bookingInput.getAdditionalneeds());
    	logger.info("*****END OF GET*****");
    	Utility.reportingResults("Pass", "Fetching Booking Details",
				"Successfully fetched booking is for User "+entity.getFirstname()+" "+entity.getLastname()
				+" Booking Details : "+entity.toString(),
				"User should be able to get Booking details");
		
	}
	
	@Test(priority=3)
	public void getBookingTestNegative() throws ParseException, ClientProtocolException, IOException {
	  	//File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\GetRequestP.json");
		Global.sScriptName = "getBookingTestNegative";
		Global.useCaseName = "API_TC_04_GetBookingNegativeFlow";
		Global.useCaseDescription = "Verify if the booking is not fetched";
		Global.curHighLight = true;
		Global.curHeading = "Verify the booking is not fetched";
		Global.ER = "Retrieving Booking details failed";
		Global.EAR = "Booking details not retrieved";
		Global.UEAR = "Failed to verify the status";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
    	logger.info("*****INSIDE GET*****");
    	
    	HttpResponse response = BookerApiClientUtil.fetchGetResponse(clientUrl+createdBookingIdforNegative, false, headerMap);

    	logger.debug("Get Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    	Utility.reportingResults("Pass", "Booking not fetched",
				"Received failure response code is "+response.getStatusLine().getStatusCode(),
				"User should not be able to retrieve Booking details for invalid Id");
    	logger.info("*****END OF GET*****");
		
	}

}
