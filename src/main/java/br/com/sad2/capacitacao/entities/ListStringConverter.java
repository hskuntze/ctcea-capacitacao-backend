package br.com.sad2.capacitacao.entities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ListStringConverter implements AttributeConverter<List<String>, String>{

	private static final String DELIMITER = ";";
	
	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		if(attribute == null || attribute.isEmpty()) {
			return "";
		}
		return attribute.stream().collect(Collectors.joining(DELIMITER));
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return Arrays.asList(dbData.split(DELIMITER));
	}
}