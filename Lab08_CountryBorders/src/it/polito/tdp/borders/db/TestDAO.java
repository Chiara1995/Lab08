package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni:");
		List<Country> countries = dao.loadAllCountries();
		for(Country c : countries){
			System.out.println(c);
		}
		
		System.out.println();
		
		System.out.println("Lista nazioni confinanti:");
		List<Border> borders=dao.getCountryPairs(1960);
		for(Border b : borders){
			System.out.println(b);
		}
		
		System.out.println();
		
		System.out.format("Lista nazioni dell'anno %d:\n", 1960);
		List<Country> c1960=dao.loadAllCountriesOfYear(1960);
		for(Country c : c1960){
			System.out.println(c);
		}
	}
	
}
