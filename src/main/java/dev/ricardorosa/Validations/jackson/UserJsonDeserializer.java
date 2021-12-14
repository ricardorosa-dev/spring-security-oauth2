package dev.ricardorosa.Validations.jackson;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import dev.ricardorosa.Validations.entity.Email;
import dev.ricardorosa.Validations.entity.User;

public class UserJsonDeserializer extends JsonDeserializer<User>{

	@Override
	public User deserialize(JsonParser p, DeserializationContext ctxt) 
			throws IOException, JacksonException {

		ObjectCodec codec = p.getCodec();
		JsonNode root = codec.readTree(p);
		
		User user = new User();
		if (root.get("firstName") != null) {
			user.setName(root.get("name").asText());
		}
		if (root.get("active") != null) {
			user.setActive(root.get("active").asBoolean());
		}

		if (root.get("dateOfBirth") != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			user.setDateOfBirth(LocalDate.parse(root.get("dateOfBirth").asText(), formatter));
		}
		
		List<Email> emails = new ArrayList<>();
		user.setEmails(emails);
		
		return user;
	}

}
