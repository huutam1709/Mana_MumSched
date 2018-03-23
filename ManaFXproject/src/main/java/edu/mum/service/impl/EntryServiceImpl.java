package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Entry;
import edu.mum.rest.service.EntryRestService;
import edu.mum.service.EntryService;

@Service
@Transactional 
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRestService entryRestService;
	
	@Override
	public List<Entry> findAll() {
		// TODO Auto-generated method stub
		return entryRestService.findAll();
	}

}
