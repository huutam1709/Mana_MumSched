package edu.mum.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.mum.config.RestHttpHeader;
import edu.mum.domain.Schedule;
import edu.mum.domain.UserCredentials;
import edu.mum.domain.UserLogin;
import edu.mum.rest.service.UserRestService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {

	@Autowired
	RestHttpHeader restHttpHeader;
	@Override
	public boolean login(UserLogin user) {
		// TODO Auto-generated method stub
		try {
			
			 String encodedQuery = URLEncoder.encode("username=admin@mum.edu&password=1", "UTF-8");
		        // This is the data that is going to be send to itcuties.com via POST request
		        // 'e' parameter contains data to echo
		        String postData = "e=" + encodedQuery;
		         
		        // Connect to google.com
		        URL url = new URL("http://localhost:8080/login/");
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setDoOutput(true);
		        connection.setRequestMethod("POST");
		        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		        connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
		         
		        // Write data
		        OutputStream os = connection.getOutputStream();
		        os.write(postData.getBytes());
		         
		        // Read response
		        StringBuilder responseSB = new StringBuilder();
		        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		          
		        String line;
		        while ( (line = br.readLine()) != null)
		            responseSB.append(line);
		                 
		        // Close streams
		        br.close();
		        os.close();
			
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//			map.add("email", "first.last@example.com");
//			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//			ResponseEntity<String> response = restHttpHeader.getRestTemplate().postForEntity( "http://localhost:8080/login/", request , String.class );
			
			
//			String urlParameters  = "username=admin@edu.mum&password=12";
//			byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
//			int postDataLength = postData.length;
//			String request = "http://localhost:8080/login/";
//			URL url = new URL( request );
//			HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
//			conn.setDoOutput(true);
//			conn.setInstanceFollowRedirects(false);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
//			conn.setRequestProperty("charset", "utf-8");
//			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
//			conn.setUseCaches(false);
//			try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
//			   wr.write( postData );
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			RestTemplate restTemplate = restHttpHeader.getRestTemplate();
			
			System.out.println("test");
			restTemplate.exchange("http://localhost:8080/login/", HttpMethod.POST, new HttpEntity<UserLogin>(createHeaders("admin@mum.edu", "1")), UserLogin.class);

			

			
			String plainCreds = "admin@mum.edu:1";
			byte[] plainCredsBytes = plainCreds.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);

			//HttpHeaders headers = new HttpHeaders();
			//headers.add("Authorization", "Basic " + base64Creds);
			
//			HttpEntity<String> request = new HttpEntity<String>(headers);
//			ResponseEntity<UserLogin> response = restTemplate.exchange("http://localhost:8080/login/", HttpMethod.POST, request, UserLogin.class);
//			response.getBody();
			
			//RestTemplate restTemplate = restHttpHeader.getRestTemplate();
			//HttpEntity<UserLogin> httpEntity = new HttpEntity<UserLogin>(user, restHttpHeader.getHttpHeaders());
			//restTemplate.postForObject("http://localhost:8080/login/", httpEntity, UserLogin.class);
			
		} catch(RestClientException exce) {
			System.out.println("ERROR: " + exce.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	private HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}

}
