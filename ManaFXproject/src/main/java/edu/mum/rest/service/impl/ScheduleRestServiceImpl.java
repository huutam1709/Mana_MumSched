package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
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

}
