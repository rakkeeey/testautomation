package com.seleniumeasy.framework.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import com.seleniumeasy.framework.config.Property;

/**
 * This Util can be used to implement all basic operations 
 * needed for API Client
 * 
 * @author Rakesh
 *
 */
public final class BookerApiClientUtil {
	
	protected static final Log logger = new Log4JLogger(BookerApiClientUtil.class.getName());

	/**
	 * Reads Response message from HttpEntity
	 * 
	 * @param httpEntity
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static String getResponseMessage(HttpEntity httpEntity) throws UnsupportedOperationException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}
	
	/**
	 * Writes Request message to File
	 * 
	 * @param responseMessage
	 * @param fileName
	 */
	public static void writeRequestMessage(String responseMessage, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(System.getProperty("user.dir") + "\\Requests\\" + fileName));
		} catch (FileNotFoundException e) {
			logger.error("File Not Found Exception ==>" + e.getMessage());
		} finally {
			pw.write(responseMessage);
			pw.close();
			pw.flush();
		}
	}
	
	/**
	 * Writes Response message to File
	 * 
	 * @param responseMessage
	 * @param fileName
	 */
	public static void writeResponseMessage(String responseMessage, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(System.getProperty("user.dir") + "\\Responses\\" + fileName));
		} catch (FileNotFoundException e) {
			logger.error("File Not Found Exception ==>" + e.getMessage());
		} finally {
			pw.write(responseMessage);
			pw.close();
			pw.flush();
		}
	}
	
	/**
	 * Fetches Encoded Token for Authorization
	 * 
	 * @return
	 * @throws IOException
	 */
	private static String fetchEncodedToken() throws IOException{
		String user = Property.getProperty("api.token.username");
		String pswrd = Property.getProperty("api.token.password");
		return Base64.getEncoder().encodeToString((user+":"+pswrd).getBytes("UTF-8"));
	}
	
	public static HttpResponse fetchPostResponse(String url, StringEntity entity, boolean authRequired, Map<String, String> headerMap)
			throws ClientProtocolException, IOException {
		logger.debug("INSIDE fetchPostResponse>>"+url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		// post.setEntity(new InputStreamEntity(new
		// FileInputStream(requestFile)));
		if (Objects.nonNull(entity)) {
			post.setEntity(entity);
		}
		if (authRequired) {
			post.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + fetchEncodedToken());
		}
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			post.addHeader(entry.getKey(),entry.getValue());
		}

		return client.execute(post);
	}

	public static HttpResponse fetchGetResponse(String url, boolean authRequired, Map<String, String> headerMap)
			throws ClientProtocolException, IOException {
		logger.debug("INSIDE fetchGetResponse>>"+url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		if (authRequired) {
			get.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + fetchEncodedToken());
		}
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			get.addHeader(entry.getKey(),entry.getValue());
		}
		  
		return client.execute(get);
	}

	public static HttpResponse fetchPutResponse(String url, StringEntity entity, boolean authRequired, Map<String, String> headerMap)
			throws ClientProtocolException, IOException {		
		
		logger.debug("INSIDE fetchPutResponse>>"+url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(url);
		if (Objects.nonNull(entity)) {
			put.setEntity(entity);
		}
		if (authRequired) {
			put.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + fetchEncodedToken());
		}
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			put.addHeader(entry.getKey(),entry.getValue());
		}

		return client.execute(put);
	}
	
	public static HttpResponse fetchDeleteResponse(String url, boolean authRequired, Map<String, String> headerMap)
			throws ClientProtocolException, IOException {
		
		logger.debug("INSIDE fetchDeleteResponse>>"+url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete delete = new HttpDelete(url);
    	if(authRequired){
    		delete.setHeader(HttpHeaders.AUTHORIZATION,"Basic "+fetchEncodedToken());
    	}
    	for(Map.Entry<String,String> entry : headerMap.entrySet()){
    		delete.addHeader(entry.getKey(),entry.getValue());
		}

		return client.execute(delete);
	}
	
}
