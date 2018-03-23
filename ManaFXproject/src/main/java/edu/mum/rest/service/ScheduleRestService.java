package edu.mum.rest.service;

import edu.mum.domain.Schedule;

public interface ScheduleRestService {
	public Schedule findOne(Long id);
	public void update(Schedule sc);
}
