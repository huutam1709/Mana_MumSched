package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.mum.config.RestHttpHeader;
import edu.mum.domain.Entry;
//import edu.mum.domain.Member;
//import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.EntryRestService;

@Service
@Transactional 
public class EntryRestServiceImpl implements EntryRestService {
	@Autowired
	RestHttpHeader restHttpHeader;
	@Override
	public List<Entry> findAll() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/api/entries/", HttpMethod.GET, restHttpHeader.getHttpEntity(), Entry[].class).getBody());
		//return null;
	}

}
