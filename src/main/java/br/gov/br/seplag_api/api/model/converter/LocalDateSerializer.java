package br.gov.br.seplag_api.api.model.converter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.br.seplag_api.commos.Constantes;

public class LocalDateSerializer extends JsonSerializer<LocalDate>  {

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		if (Objects.isNull(value)) 
			gen.writeNull();
		else 
			gen.writeString(value.format(Constantes.DATA_PADRAO));
	}

}
