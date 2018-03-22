package edu.mum.config;

import java.nio.charset.Charset;
import java.util.Collections;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class RestHttpHeader {
	protected RestTemplate restTemplate;
	
	
	public RestHttpHeader() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public HttpHeaders getHttpHeaders() {
		
		//CsrfToken csrfToken = csrfTokenRepository.generateToken(null);
        //HttpHeaders headers = basicAuthHeaders();

        //headers.add(csrfToken.getHeaderName(), csrfToken.getToken());
        //headers.add("Cookie", "XSRF-TOKEN=" + csrfToken.getToken());

		String username = "admin@mum.edu";
		String password = "1";
		
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String( encodedAuth );
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set("Authorization", authHeader);
		return requestHeaders;
	}

	public HttpEntity<?> getHttpEntity() {
		return new HttpEntity(getHttpHeaders());
	}

}

