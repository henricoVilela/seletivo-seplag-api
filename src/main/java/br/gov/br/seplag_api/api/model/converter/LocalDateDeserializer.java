package br.gov.br.seplag_api.api.model.converter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.gov.br.seplag_api.commos.Constantes;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		var data = p.getText();
		
		if (Objects.isNull(data) || data.isEmpty())
			return null;

		return LocalDate.parse(data, Constantes.DATA_PADRAO);
	}
}
