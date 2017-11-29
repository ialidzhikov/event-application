package com.event.service;

import java.util.List;

import com.event.dao.ParticipantDao;
import com.event.entity.Participant;
import com.google.inject.Inject;

public class ParticipantService {

	private ParticipantDao dao;
	
	@Inject
	public ParticipantService(ParticipantDao dao) {
		this.dao = dao;
	}
	
	public List<Participant> getAll() {
		return dao.findAll();
	}
	
	public void create(Participant participant) {
		dao.persist(participant);
	}

	public void delete(Long id) {
		if (!dao.contains(id)) {
			return;
		}
		
		dao.remove(id);
	}
}
