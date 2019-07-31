package com.akujas.vcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestResponseUtil  {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseUtil.class);

	private final static String defaultCT = "2000";

	private final static String defaultST = "10000";

	private static RequestResponseUtil requestResponseUtil = null;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private RequestResponseUtil() {

	}

	/* Static 'instance' method */
	public static RequestResponseUtil getInstance() {

		if (requestResponseUtil == null) {

			requestResponseUtil = new RequestResponseUtil();
		}

		return requestResponseUtil;
	}

	/**
	 * @param url
	 *            : hitting url
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url) {
		return HTTPGetRequest(url, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), 0, null);
	}

	/**
	 * @param url
	 *            : hitting url
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url, Map<String, Object> headers) {
		return HTTPGetRequest(url, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), 0, headers);
	}

	/**
	 * @param url
	 *            : hitting url
	 * @param retryCount
	 *            : integer value for number of retry
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url, int retryCount) {
		return HTTPGetRequest(url, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), retryCount, null);
	}

	/**
	 * @param url
	 *            : hitting url
	 * @param retryCount
	 *            : integer value for number of retry
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url, int retryCount, Map<String, Object> headers) {
		return HTTPGetRequest(url, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), retryCount, headers);
	}

	/**
	 * @param url
	 *            : hitting url
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url, int connectionTimeout, int soketTimeout) {
		return HTTPGetRequest(url, connectionTimeout, soketTimeout, 0, null);
	}

	/**
	 * @param url
	 *            : hitting url
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @param retryCount
	 *            : integer value for number of retry
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url, int connectionTimeout, int soketTimeout, int retryCount) {
		return HTTPGetRequest(url, connectionTimeout, soketTimeout, retryCount, null);
	}

	/**
	 * @param url
	 *            : hitting url
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url, int connectionTimeout, int soketTimeout, Map<String, Object> headers, boolean requireHeaders, boolean printLog) {
		return HTTPGetRequest(url, connectionTimeout, soketTimeout, 0, headers,requireHeaders,printLog);
	}

	/**
	 * @param url
	 *            : hitting url
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @param retryCount
	 *            : integer value for number of retry
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPGetRequest(String url, int connectionTimeout, int socketTimeout, int retryCount,
			Map<String, Object> headers) {

		return HTTPGetRequestInternal(url,
		        connectionTimeout,
		        socketTimeout,
		        retryCount,
		        headers, false,false);
	}
	
	public Object HTTPGetRequest(String url, int connectionTimeout, int socketTimeout, int retryCount,
			Map<String, Object> headers, boolean provideHeader, boolean printLog) {

		return HTTPGetRequestInternal(url,
		        connectionTimeout,
		        socketTimeout,
		        retryCount,
		        headers, provideHeader,printLog);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object HTTPGetRequestInternal(String url,
	        int connectionTimeout,
	        int socketTimeout,
	        int retryCount,
	        Map<String, Object> headers, boolean provideHeader, boolean printLog) {

		Date startTime = new Date();

		if (StringUtils.isBlank(url)) {
			LOGGER.debug("Requested url is blank (GET)");
			return "";
		}

		LOGGER.debug("REQUESTED URL -- " + url + " WITH CT = " + connectionTimeout + " AND ST = " + socketTimeout);

		Object responseBody = "";

		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout).build();

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

		CloseableHttpResponse httpResponse = null;

		BufferedReader reader = null;
		boolean isException = false;
		try {
			HttpGet httpGet = new HttpGet(url);

			if (headers != null) {
				for (String header : headers.keySet()) {
					httpGet.setHeader(header, headers.get(header) + "");
				}
			}

			httpResponse = httpClient.execute(httpGet);
			
			reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			
			String inputLine;

			while ((inputLine = reader.readLine()) != null) {
				responseBody += inputLine;
			}

			//log.debug("REQUESTED URL RESPONSE -- " + responseBody);

		} catch (Exception e) {
			isException	=	true;
			responseBody = handleRetry(responseBody, url, connectionTimeout, socketTimeout, retryCount, e, headers);
		} finally {
			closeConnections(httpClient, httpResponse, reader);
		}

		Date endTime = new Date();

		if(printLog || isException){
			LOGGER.info("REQUESTED_URL = " + url + " ## TIMEOUT = " + connectionTimeout + "-" + socketTimeout
					+ " ## REQUESTED_URL_RESPONSE = " + responseBody + " ## Headers = " + headers + " ## Time_Taken = "
					+ (endTime.getTime() - startTime.getTime()) + "milliseconds");
		}else{
			LOGGER.info("REQUESTED_URL = " + url + " ## TIMEOUT = " + connectionTimeout + "-" + socketTimeout
					+ " ## REQUESTED_URL_RESPONSE = SUCCESS  ## Headers = " + headers + " ## Time_Taken = "
					+ (endTime.getTime() - startTime.getTime()) + "milliseconds");
		}
		
		int sc	=	httpResponse.getStatusLine().getStatusCode();
		if (sc == 200 && provideHeader) {
			ObjectMapper mapper = new ObjectMapper();
			Map response;
			try {
				response = mapper.readValue((String)responseBody, Map.class);
				Header[] headersResponse = httpResponse.getAllHeaders();

				Map<String, Object> headersMap = new HashMap<>();
				Arrays.asList(headersResponse)
				        .forEach(t -> {

					        headersMap.put(t.getName(),t.getValue());
				        });
				response.put("headers", headersMap);
				return response;
			} catch (IOException e) {
				LOGGER.error("Exception",e);
			}
		}
		return responseBody;
	}
	private Object handleRetry(Object responseBody, String url, int connectionTimeout, int socketTimeout,
			int retryCount, Exception e, Map<String, Object> headers) {

		if (retryCount > 0) {
			retryCount--;
			return HTTPGetRequest(url, connectionTimeout, socketTimeout, retryCount, headers);
		}

		if (e != null) {
			return handleException(e);
		}

		return responseBody;
	}

	private String handleException(Exception e) {

		String msg = e.getMessage();

		if (e instanceof ConnectTimeoutException || e instanceof HttpHostConnectException) {
			msg = msg + "#" + "REQUESTED_URL_RESPONSE@CONNECTION_TIMEOUT";
		} else if (e instanceof SocketTimeoutException) {
			msg = msg + "#" + "REQUESTED_URL_RESPONSE@SOCKET_TIMEOUT";
		} else {
			msg = msg + "#" + "REQUESTED_URL_RESPONSE@URL_EXCEPTION";
		}

		return msg;
	}

	private void closeConnections(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse,
			BufferedReader reader) {

		if (httpClient == null || httpResponse == null) {
			return;
		}

		try {
			reader.close();
			httpClient.close();
			httpResponse.close();
			LOGGER.debug("CLOSE_CONNECTION | SUCCESS");
		} catch (Exception e) {
			LOGGER.debug("CLOSE_CONNECTION | EXCEPTION");
		}

	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequest(String url) {
		return HTTPPostRequest(url, null, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), null);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param body
	 *            : as json string
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequest(String url, String body) {
		return HTTPPostRequest(url, body, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), null);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequest(String url, Map<String, Object> headers) {
		return HTTPPostRequest(url, null, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), headers);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param body
	 *            : as json string
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequest(String url, String body, Map<String, Object> headers) {
		return HTTPPostRequest(url, body, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), headers);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param body
	 *            : as json string
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequest(String url, String body, int connectionTimeout, int socketTimeout) {
		return HTTPPostRequest(url, body, connectionTimeout, socketTimeout, null);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequest(String url, int connectionTimeout, int socketTimeout) {
		return HTTPPostRequest(url, null, connectionTimeout, socketTimeout, null);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequest(String url, int connectionTimeout, int socketTimeout, Map<String, Object> headers) {
		return HTTPPostRequest(url, null, connectionTimeout, socketTimeout, headers);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param body
	 *            : as json string
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	@SuppressWarnings("resource")
	public Object HTTPPostRequest(String url, String body, int connectionTimeout, int socketTimeout,
			Map<String, Object> headers) {

		Object responseBody = "";
		Date startTime = new Date();
		CloseableHttpResponse httpResponse = null;
		BufferedReader reader = null;
		HttpEntity entity = null;
		int statuscode = -1;
		CloseableHttpClient httpClient=null;

		try {
			
		if (StringUtils.isBlank(url)) {
			LOGGER.debug("Requested url is blank (POST)");
			return "";
		}

		LOGGER.debug("REQUESTED URL -- " + url + " WITH CT= " + connectionTimeout + " AND ST = " + socketTimeout);


		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout).build();

		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

		
			HttpPost httpPost = new HttpPost(url);

			if (headers != null) {
				for (String header : headers.keySet()) {
					httpPost.setHeader(header, headers.get(header) + "");
				}
			}

			if (StringUtils.isNotBlank(body)) {
				entity = new ByteArrayEntity(body.getBytes("UTF-8"));
				httpPost.setEntity(entity);
			}

			httpResponse = httpClient.execute(httpPost);
			
			statuscode=httpResponse!=null?httpResponse.getStatusLine().getStatusCode():-1;
			
			if(statuscode == org.apache.http.HttpStatus.SC_METHOD_FAILURE) {
				LOGGER.info("Response is blank in first attempt - Response Code is {}, body {}", httpResponse.getStatusLine().getStatusCode(),body);
				httpResponse = httpClient.execute(httpPost);
				statuscode=httpResponse!=null?httpResponse.getStatusLine().getStatusCode():-1;
				
				if(statuscode == org.apache.http.HttpStatus.SC_METHOD_FAILURE) {
					LOGGER.info("Response is blank in second attempt - Response Code is {}, body {}", httpResponse.getStatusLine().getStatusCode(),body);
					httpResponse = httpClient.execute(httpPost);
					statuscode=httpResponse!=null?httpResponse.getStatusLine().getStatusCode():-1;
					Thread.sleep(700);
					if(statuscode == org.apache.http.HttpStatus.SC_METHOD_FAILURE) {
						LOGGER.info("Response is blank in third attempt - Response Code is {}, body {}", httpResponse.getStatusLine().getStatusCode(),body);
						httpResponse = httpClient.execute(httpPost);
						statuscode=httpResponse!=null?httpResponse.getStatusLine().getStatusCode():-1;
					}
					
				}
			}
			
			reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

			String inputLine;

			while ((inputLine = reader.readLine()) != null) {
				responseBody += inputLine;
			}

		} catch (Exception e) {
			
			LOGGER.error("EXCEPTION_handleException REQUESTED_URL = " + url + " ## TIMEOUT = " + connectionTimeout + "-" + socketTimeout
					+ " ## REQUESTED_URL_RESPONSE = " + responseBody + " ## Headers = " + headers + " ## Body_Sent = "
					+ body + " ## HTTP_RESPONSE_CODE = "+ statuscode,e);
			
			responseBody = handleException(e);
			
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			LOGGER.debug("httpclient connection shutdown ");
			closeConnections(httpClient, httpResponse, reader);
		}

		Date endTime = new Date();

		LOGGER.info("REQUESTED_URL = " + url + " ## TIMEOUT = " + connectionTimeout + "-" + socketTimeout
				+ " ## REQUESTED_URL_RESPONSE = " + responseBody + " ## Headers = " + headers + " ## Body_Sent = "
				+ body + " ## HTTP_RESPONSE_CODE = "+ statuscode +" ## Time_Taken = " + (endTime.getTime() - startTime.getTime()) + "milliseconds");

		return responseBody;
	}
	

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param formDataMap
	 *            : form data which will be send in body
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequestWithFormData(String url, Map<String, Object> formDataMap) {
		return HTTPPostRequestWithFormData(url, formDataMap, Integer.parseInt(defaultCT), Integer.parseInt(defaultST),
				null);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param formDataMap
	 *            : form data which will be send in body
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequestWithFormData(String url, Map<String, Object> formDataMap,
			Map<String, Object> headers) {
		return HTTPPostRequestWithFormData(url, formDataMap, Integer.parseInt(defaultCT), Integer.parseInt(defaultST),
				headers);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param formDataMap
	 *            : form data which will be send in body
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequestWithFormData(String url, Map<String, Object> formDataMap, int connectionTimeout,
			int socketTimeout) {
		return HTTPPostRequestWithFormData(url, formDataMap, connectionTimeout, socketTimeout, null);
	}

	/**
	 * 
	 * @param url
	 *            : hitting url
	 * @param formDataMap
	 *            : form data which will be send in body
	 * @param connectionTimeout
	 *            : time in milliseconds(integer value) for connection timeout
	 * @param socketTimeout
	 *            : time in milliseconds(integer value) for socket timeout
	 * @param headers
	 *            : headers to be set in request
	 * @return : will return response as fetched
	 */
	public Object HTTPPostRequestWithFormData(String url, Map<String, Object> formDataMap, int connectionTimeout,
			int socketTimeout, Map<String, Object> headers) {

		Date startTime = new Date();

		if (StringUtils.isBlank(url)) {
			LOGGER.debug("Requested url is blank (POST)");
			return "";
		}

		LOGGER.debug("REQUESTED URL -- " + url + " WITH CT= " + connectionTimeout + " AND ST = " + socketTimeout);

		Object responseBody = "";

		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout).build();

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

		CloseableHttpResponse httpResponse = null;

		BufferedReader reader = null;

		try {
			HttpPost httpPost = new HttpPost(url);

			if (headers != null) {
				for (String header : headers.keySet()) {
					httpPost.setHeader(header, headers.get(header) + "");
				}
			}

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			if (formDataMap != null) {
				for (String key : formDataMap.keySet()) {
					if (StringUtils.isNotBlank(formDataMap.get(key) + "")) {
						nameValuePairs.add(new BasicNameValuePair(key, formDataMap.get(key) + ""));
					}
				}
			}

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

			httpResponse = httpClient.execute(httpPost);

			reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

			String inputLine;

			while ((inputLine = reader.readLine()) != null) {
				responseBody += inputLine;
			}

			//log.debug("Requested url response -- " + responseBody);

		} catch (Exception e) {
			responseBody = handleException(e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			LOGGER.debug("httpclient connection shutdown ");
			closeConnections(httpClient, httpResponse, reader);
		}

		Date endTime = new Date();

		LOGGER.info("REQUESTED_URL = " + url + " ## TIMEOUT = " + connectionTimeout + "-" + socketTimeout
				+ " ## REQUESTED_URL_RESPONSE = " + responseBody + " ## Headers = " + headers + " ## Body_Sent = "
				+ formDataMap + " ## Time_Taken = " + (endTime.getTime() - startTime.getTime()) + "milliseconds");

		return responseBody;
	}

	public CloseableHttpResponse httpPostRequest(String url, String body, Map<String, Object> headers) {
		return httpPostRequest(url, body, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), headers);
	}

	public CloseableHttpResponse httpPostRequest(String url, String body, int connectionTimeout, int socketTimeout,
			Map<String, Object> headers) {

		CloseableHttpResponse execute = null;

		if (StringUtils.isBlank(url)) {
			LOGGER.debug("Requested url is blank (POST)");
			return null;
		}

		LOGGER.debug("REQUESTED URL -- " + url + " WITH CT= " + connectionTimeout + " AND ST = " + socketTimeout);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout).build();

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

		HttpEntity entity = null;

		try {
			HttpPost httpPost = new HttpPost(url);

			if (headers != null) {
				for (String header : headers.keySet()) {
					httpPost.setHeader(header, headers.get(header) + "");
				}
			}

			if (StringUtils.isNotBlank(body)) {
				entity = new ByteArrayEntity(body.getBytes("UTF-8"));
				httpPost.setEntity(entity);
			}

			execute = httpClient.execute(httpPost);
			return execute;

		} catch (Exception e) {
			LOGGER.debug("Exception is: " + e);
		}
		return execute;
	}
	
	public CloseableHttpResponse httpGetRequest(String url, Map<String, Object> headers) {
		return httpGetRequest(url, Integer.parseInt(defaultCT), Integer.parseInt(defaultST), headers);
	}

	public CloseableHttpResponse httpGetRequest(String url, int connectionTimeout, int socketTimeout,
			Map<String, Object> headers) {

		CloseableHttpResponse execute = null;

		if (StringUtils.isBlank(url)) {
			LOGGER.debug("Requested url is blank (GET)");
			return null;
		}

		LOGGER.debug("REQUESTED URL -- " + url + " WITH CT= " + connectionTimeout + " AND ST = " + socketTimeout);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout).build();

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

		try {
			HttpGet httpGet = new HttpGet(url);

			if (headers != null) {
				for (String header : headers.keySet()) {
					httpGet.setHeader(header, (String) headers.get(header));
				}
			}

			execute = httpClient.execute(httpGet);
			
			return execute;

		} catch (Exception e) {
			LOGGER.debug("Exception is: " + e);
		}
		return execute;
	}

	public String HTTPGetCurlRequest(String url, Map<String, Object> headers) {
		return HTTPGetCurlRequest(url, null, headers);
	}

	/*
	 * connectionTimeOut should be in seconds.
	 */
	public String HTTPGetCurlRequest(String url, Integer connectionTimeOut, Map<String, Object> headers) {

		Date startTime = new Date();

		if (StringUtils.isBlank(url)) {
			return "";
		}

		String responseBody = "";

		String command = "curl";

		if (headers != null) {
			for (Entry<String, Object> entry : headers.entrySet()) {
				command += " -H '" + entry.getKey() + ": " + entry.getValue() + "'";
			}
		}

		command += " '" + url + "'";

		if (connectionTimeOut != null) {
			command += " --connect-timeout " + connectionTimeOut;
		}

		Process p = null;

		try {

			p = new ProcessBuilder("/bin/sh", "-c", command).start();

			p.waitFor();

			String line;

			StringBuffer sb = new StringBuffer();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}

			responseBody = sb.toString();

		} catch (Exception e) {

			LOGGER.info("USING_CURL = " + command + " ## ERROR " + e.getMessage());

		} finally {

			p.destroy();

		}

		Date endTime = new Date();

		LOGGER.info("USING_CURL = " + command + " ## REQUESTED_URL_RESPONSE = " + responseBody + " ## TIME_TAKEN "
				+ (endTime.getTime() - startTime.getTime()) + " milliseconds");

		return responseBody;
	}

	public String getFullURL(HttpServletRequest request, Map<String, String[]> parameterMap) {

		try {

			if (parameterMap == null) {
				StringBuffer requestURL = request.getRequestURL();
				String queryString = request.getQueryString();

				if (StringUtils.isBlank(queryString)) {
					return requestURL.toString();
				} else {
					return requestURL.append('?').append(queryString).toString();
				}
			}
			if (parameterMap != null) {

				StringBuffer requestURL = request.getRequestURL();
				String queryParam = "";

				for (String key : parameterMap.keySet()) {
					String[] strings = parameterMap.get(key);
					String value = "";
					if (strings != null) {
						value = strings[0];
					}
					queryParam = key + "=" + value + "&" + queryParam;
				}

				return requestURL.append('?').append(queryParam).toString();
			}
		} catch (Exception e) {

		}

		return "";

	}

	/**
	 * use for get request
	 * 
	 * @param url
	 * @return
	 */
	public String sendGet(String url) {

		if (StringUtils.isBlank(url)) {
			LOGGER.debug("Requested url is blank (GET)");
			return "";
		}
		StringBuffer response = new StringBuffer();

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

			String inputLine;

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();

			httpClient.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response.toString();
	}

	

	public Map<String, Object> getAllHeaders(HttpServletRequest request) {

		Map<String, Object> headerBuilder = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headerBuilder.put(headerName, request.getHeader(headerName));
		}

		if (headerBuilder.containsKey("content-length")) {
			headerBuilder.remove("content-length");
		}

		return headerBuilder;
	}

}
