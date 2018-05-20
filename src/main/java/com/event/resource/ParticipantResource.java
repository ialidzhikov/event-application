package com.event.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.event.entity.Participant;
import com.event.service.ParticipantService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@Path("participants")
public class ParticipantResource {

	private ParticipantService service;

	@Inject
	public ParticipantResource(final ParticipantService service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Participant> getAll() {
		return service.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Participant participant) {
		service.create(participant);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);

		return Response.ok().build();
	}
}
