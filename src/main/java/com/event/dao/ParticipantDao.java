package com.event.dao;

import com.event.entity.Participant;

public class ParticipantDao extends AbstractDao<Participant> {

	@Override
	public Class<Participant> getEntityType() {
		return Participant.class;
	}

}
