package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.mum.config.RestHttpHeader;
import edu.mum.domain.Entry;
import edu.mum.domain.Schedule;
import edu.mum.rest.service.ScheduleRestService;

@Service
@Transactional
public class ScheduleRestServiceImpl implements ScheduleRestService {

	@Autowired
	RestHttpHeader restHttpHeader;

	@Override
	public Schedule findOne(Long id) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		Schedule sc = (Schedule)restTemplate.exchange("http://localhost:8080/api/schedules/view/" + id, HttpMethod.GET, restHttpHeader.getHttpEntity(), Schedule.class).getBody();
		return sc;
	}

	@Override
	public void update(Schedule sc) {
		// TODO Auto-generated method stub
		try {

			//RestTemplate restTemplate = remoteApi.getRestTemplate();
			//HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, remoteApi.getHttpHeaders());
			//restTemplate.postForObject("http://localhost:8080/MemberRestJSON/products/add/", 
			//		        httpEntity, Product.class);
	                                     // Product.class - Response type
			
				RestTemplate restTemplate = restHttpHeader.getRestTemplate();
				HttpEntity<Schedule> httpEntity = new HttpEntity<Schedule>(sc, restHttpHeader.getHttpHeaders());
				restTemplate.postForObject("http://localhost:8080/api/schedules/update/", httpEntity, Schedule.class);
				//restTemplate.exchange("http://localhost:8080/api/schedules/update/", HttpMethod.POST, httpEntity, Schedule.class).getBody();
	 		}
			catch (RestClientException exce) {
				System.out.println("ERROR: " + exce.getMessage());
			}
	}

}
