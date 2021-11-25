
package de.fraunhofer.aisec.codyze;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public class LanguageDeseralizer extends StdDeserializer<EnumSet<Language>> {

	public LanguageDeseralizer() {
		super((JavaType) null);
	}

	protected LanguageDeseralizer(Class<?> vc) {
		super(vc);
	}

	protected LanguageDeseralizer(JavaType valueType) {
		super(valueType);
	}

	protected LanguageDeseralizer(StdDeserializer<?> src) {
		super(src);
	}

	@Override
	public EnumSet<Language> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {

		EnumSet<Language> result = EnumSet.noneOf(Language.class);
		JsonToken current = jp.getCurrentToken();
		if (current.equals(JsonToken.START_ARRAY)) {
			current = jp.nextToken();
			while (!current.equals(JsonToken.END_ARRAY)) {
				if (current.equals(JsonToken.VALUE_STRING)) {
					String s = jp.getValueAsString();
					String upper = s.toUpperCase();
					try {
						result.add(Language.valueOf(upper));
					}
					catch (IllegalArgumentException e) {
						System.out.println("Could not parse configuration file correctly because "
								+ s + " is not a supported programming language.");
						System.out.println("Continue with parsing rest of configuration file.");
					}
				}
				current = jp.nextToken();
			}
		}

		return result;
	}
}
