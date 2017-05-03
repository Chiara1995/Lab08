package it.polito.tdp.borders.model;

public class Country {
	
	private String nomeCountry;
	private int codiceCountry;
	private String abbreviazioneCountry;
	
	public Country(String nomeCountry, int codiceCountry, String abbreviazioneCountry) {
		super();
		this.nomeCountry = nomeCountry;
		this.codiceCountry = codiceCountry;
		this.abbreviazioneCountry = abbreviazioneCountry;
	}

	public String getNomeCountry() {
		return nomeCountry;
	}

	public void setNomeCountry(String nomeCountry) {
		this.nomeCountry = nomeCountry;
	}

	public int getCodiceCountry() {
		return codiceCountry;
	}

	public void setCodiceCountry(int codiceCountry) {
		this.codiceCountry = codiceCountry;
	}

	public String getAbbreviazioneCountry() {
		return abbreviazioneCountry;
	}

	public void setAbbreviazioneCountry(String abbreviazioneCountry) {
		this.abbreviazioneCountry = abbreviazioneCountry;
	}

	@Override
	public String toString() {
		return nomeCountry+" "+codiceCountry+" "+abbreviazioneCountry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codiceCountry;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (codiceCountry != other.codiceCountry)
			return false;
		return true;
	}

}
