package com.uca.capas.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Auditorium;
import com.uca.capas.repository.AuditoriumRepository;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	AuditoriumRepository aRepo;
	
	@Override
	public List<Auditorium> getAuditoriumList() throws DataAccessException {
		return aRepo.findAll(Sort.by("name"));
	}

	@Override
	public Auditorium getAuditorium(Integer code) throws DataAccessException {
		Optional<Auditorium> auditorium = aRepo.findById(code);
		return auditorium.isPresent()? auditorium.get() : null;
	}

	@Override
	@Transactional
	public void save(Auditorium auditorium, String userName) throws DataAccessException {
		
		if(auditorium.getId() == null) {
			auditorium.setCreatedDate(Calendar.getInstance().getTime());
			auditorium.setCreatedBy(userName);
		}
		
		auditorium.setUpdatedDate(Calendar.getInstance().getTime());
		auditorium.setUpdatedBy(userName);

		
		aRepo.save(auditorium);	
	}

}
