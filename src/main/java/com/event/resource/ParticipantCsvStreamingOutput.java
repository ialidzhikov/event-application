package com.event.resource;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import com.event.entity.Participant;
import com.event.model.ParticipantCsvModel;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ParticipantCsvStreamingOutput implements StreamingOutput {

	private List<Participant> participants;

	public ParticipantCsvStreamingOutput(List<Participant> participants) {
		this.participants = participants;
	}

	@Override
	public void write(OutputStream output) throws IOException, WebApplicationException {
		List<ParticipantCsvModel> models = participants.stream().map(this::map).collect(toList());

		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(ParticipantCsvModel.class).withHeader();
		mapper.writer(schema).writeValue(output, models);
	}

	private ParticipantCsvModel map(Participant participant) {
		ParticipantCsvModel model = new ParticipantCsvModel();
		model.setFirstName(participant.getFirstName());
		model.setLastName(participant.getLastName());
		model.setEmail(participant.getEmail());

		return model;
	}
}
