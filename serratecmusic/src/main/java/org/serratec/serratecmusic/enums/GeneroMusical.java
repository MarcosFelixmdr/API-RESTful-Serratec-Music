package org.serratec.serratecmusic.enums;

import org.serratec.serratecmusic.exceptions.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GeneroMusical {
	ROCK, POP, SAMBA, FUNK, SERTANEJO;

	@JsonCreator
	public static GeneroMusical Verifica(String value) throws EnumValidationException {

		for (GeneroMusical generoMusical : values()) {
			if (value.equals(generoMusical.name())) {
				return generoMusical;
			}
		}
		throw new EnumValidationException("Genero musical válidos: ROCK,POP,SAMBA,FUNK,SERTANEJO");
	}
}
