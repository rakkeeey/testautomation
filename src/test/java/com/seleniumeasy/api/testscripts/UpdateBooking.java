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
import com.seleniumeasy.framework.reporting.ReportUtility;

/**
 * This is a test case to Test positive & negative test cases of Update Booking
 * 
 * @author Rakesh
 *
 */
public class UpdateBooking extends ApiBaseBooking {

	/**
	 * This method test for Positive Booking Updation Flow
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test(priority = 4)
	public void updateBookingTest() throws IOException {
		// File requestFile = new
		// File(System.getProperty("user.dir")+"\\Requests\\UpdateRequestP.json");

		logger.info("*****INSIDE UPDATE*****");

		ReportUtility.setReportingData("updateBookingTest", "API_TC_05_UpdateBookingPositiveFlow",
				"Verify if the booking is updated", "Verify the booking", "Successful booking update",
				"Booking id successfully updated", "Failed to verify the booking");

		HttpResponse response = BookerApiClientUtil.fetchPutResponse(clientUrl + createdBookingId,
				new StringEntity(gson.toJson(bookingInputUpdate)), true, headerMap);

		logger.debug("Update Response Status==>" + response.getStatusLine().getStatusCode());
		try {
			Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
		} catch (AssertionError e) {
			ReportUtility.reportingResults("Fail", "Status code verification ", "Status code not received as expected",
					"Status code should be received as expected");
			throw e;
		}
		ReportUtility.reportingResults("Pass", "Status code verification ",
				"Status code received as expected :" + response.getStatusLine().getStatusCode(),
				"Status code should be received as expected");

		String responseMessage = BookerApiClientUtil.getResponseMessage(response.getEntity());

		BookingVo entity = gson.fromJson(responseMessage, BookingVo.class);
		logger.debug("Update Response==>" + entity.toString());

		BookerApiClientUtil.writeResponseMessage(responseMessage, "3UpdateResponseP.json");

		try {
			Assert.assertEquals(entity.getAdditionalneeds(), bookingInputUpdate.getAdditionalneeds());
		} catch (AssertionError e) {
			ReportUtility.reportingResults("Fail", "Updating Booking ID", "Failed to update a booking",
					"User should be able to update a booking");
			throw e;
		}
		ReportUtility.reportingResults("Pass", "Updating Booking ID",
				"Successfully updated booking ID is " + createdBookingId + " and Updated data is "
						+ entity.getAdditionalneeds() + " After Update booking detail is: " + entity.toString(),
				"User should be able to update a booking");
		logger.info("*****END OF UPDATE*****");
	}

	/**
	 * This method test for Negative Booking Updation Flow
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test(priority = 5)
	public void updateBookingTestNegative() throws IOException {
		// File requestFile = new
		// File(System.getProperty("user.dir")+"\\Requests\\UpdateRequestP.json");

		logger.info("*****INSIDE UPDATE*****");

		ReportUtility.setReportingData("updateBookingTestNegative", "API_TC_06_UpdateBookingNegativeFlow",
				"Verify if the booking is not updated", "Verify the booking is not updated", "Failed updating Booking",
				"Booking id not updated", "Failed to verify the status");

		HttpResponse response = BookerApiClientUtil.fetchPutResponse(clientUrl + createdBookingIdforNegative,
				new StringEntity(gson.toJson(bookingInputUpdate)), false, headerMap);

		logger.debug("Update Response Status==>" + response.getStatusLine().getStatusCode());

		try {
			Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
		} catch (AssertionError e) {
			ReportUtility.reportingResults("Fail", "Booking updated",
					"Received response code is " + response.getStatusLine().getStatusCode(),
					"User should not be able to update Booking details without authorization key");
			throw e;
		}

		ReportUtility.reportingResults("Pass", "Booking not updated",
				"Received failure response code is " + response.getStatusLine().getStatusCode(),
				"User should not be able to update Booking details without authorization key");

		logger.info("*****END OF UPDATE*****");

	}

}
