package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";

		try {
			List<Country> country=new ArrayList<Country>();
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country ctemp=new Country(rs.getString("StateNme"),rs.getInt("ccode"), rs.getString("StateAbb"));
				if(!country.contains(ctemp))
					country.add(ctemp);
			}

			conn.close();
			return country;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		
		String sql = "SELECT  C1.CCode AS codice1, C1.StateAbb AS abbreviazione1, C1.StateNme AS nome1, C2.CCode AS codice2, C2.StateAbb AS abbreviazione2, C2.StateNme AS nome2 "+
					 "FROM contiguity, country AS C1, country AS C2 "+
					 "WHERE state1no=C1.CCode AND state2no=C2.CCode AND year<=? AND contiguity.conttype='1' ";
		try {
			List<Border> border=new ArrayList<Border>();
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country ctemp1=new Country(rs.getString("nome1"),rs.getInt("codice1"), rs.getString("abbreviazione1"));
				Country ctemp2=new Country(rs.getString("nome2"), rs.getInt("codice2"), rs.getString("abbreviazione2"));
				Border btemp=new Border(ctemp1, ctemp2);
				if(!border.contains(btemp))
					border.add(btemp);
			}

			conn.close();
			return border;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
		
	}
	
	public List<Country> loadAllCountriesOfYear(int anno) {

		String sql = "SELECT DISTINCT country.StateNme, country.CCode, country.StateAbb "+
					 "FROM country, contiguity "+
				     "WHERE country.CCode=contiguity.state1no AND contiguity.year<=? AND contiguity.conttype='1'";

		try {
			List<Country> country=new ArrayList<Country>();
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country ctemp=new Country(rs.getString("StateNme"),rs.getInt("CCode"), rs.getString("StateAbb"));
				if(!country.contains(ctemp))
					country.add(ctemp);
			}

			conn.close();
			return country;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}
	
}
