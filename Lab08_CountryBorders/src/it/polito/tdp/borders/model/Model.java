package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private UndirectedGraph<Country, DefaultEdge> graph;
	BordersDAO bdao=new BordersDAO();

	public Model() {
		this.graph=new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
	}
	
	public void createGraph(int i) {
		Graphs.addAllVertices(graph, bdao.loadAllCountriesOfYear(i));
		
		for(Border b : bdao.getCountryPairs(i)){
			graph.addEdge(b.getCountry1(), b.getCountry2());
		}
	}

	public List<Country> getCountries() {
		List<Country> country=new ArrayList<Country>();
		for(Country c : graph.vertexSet()){
			country.add(c);
		}
		return country;
	}

	public Object getNumberOfConnectedComponents() {
		ConnectivityInspector<Country, DefaultEdge> inspector=new ConnectivityInspector<Country, DefaultEdge>(graph);
		return inspector.connectedSets().size();
	}

	public List<String> getCountryCounts() {
		List<String> ltemp=new ArrayList<String>();
		int cont=0;
		for(Country c : graph.vertexSet()){
			cont=0;
			for(DefaultEdge e : graph.edgesOf(c)){
				cont++;
			}
			String s=c+" "+cont;
			if(ltemp.indexOf(s)==-1)
				ltemp.add(s);
		}
		return ltemp;
	}
	
	

}
