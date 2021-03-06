package edu.mum.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Schedule;
import edu.mum.rest.service.ScheduleRestService;
import edu.mum.service.ScheduleService;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRestService scheduleRestService;
	
	@Override
	public Schedule findOne(long id) {
		// TODO Auto-generated method stub
		return scheduleRestService.findOne(id);
	}

	@Override
	public void update(Schedule sc) {
		// TODO Auto-generated method stub
		scheduleRestService.update(sc);
	}

}
