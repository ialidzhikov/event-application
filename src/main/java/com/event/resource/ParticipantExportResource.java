package com.event.resource;

import java.text.MessageFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.event.entity.Participant;
import com.event.service.ParticipantService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@Path("export/participants")
public class ParticipantExportResource {

	private static final String HEADER_CONTENT_DISPOSITION_KEY = "Content-Disposition";
	private static final String HEADER_CONTENT_DISPOSITION_VALUE = "attachment; filename={0}.csv";

	private ParticipantService service;

	@Inject
	public ParticipantExportResource(ParticipantService service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getAll(@QueryParam("format") String format) {
		List<Participant> participants = service.getAll();
		ParticipantCsvStreamingOutput streaming = new ParticipantCsvStreamingOutput(participants);
		String value = MessageFormat.format(HEADER_CONTENT_DISPOSITION_VALUE, "participants");

		return Response.ok(streaming).header(HEADER_CONTENT_DISPOSITION_KEY, value).build();
	}
}
