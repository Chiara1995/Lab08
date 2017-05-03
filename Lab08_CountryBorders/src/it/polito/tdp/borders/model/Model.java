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
	
	//Creazione di un grafo non orientato, non pesato e semplice
	private UndirectedGraph<Country, DefaultEdge> graph;
	BordersDAO bdao=new BordersDAO();

	public Model() {
		this.graph=new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
	}
	
	/*
	 * Metodo di creazione di un grafo non orientato, non pesato e semplice 
	 */
	public void createGraph(int i) {
		//Creazione dei vertici del grafo
		Graphs.addAllVertices(graph, bdao.loadAllCountriesOfYear(i));
		
		//Creazione degli archi del grafo 
		for(Border b : bdao.getCountryPairs(i)){
			graph.addEdge(b.getCountry1(), b.getCountry2());
		}
	}

	/*
	 * Metodo che restituisce gli stati presenti nel grafo 
	 */
	public List<Country> getCountries() {
		List<Country> country=new ArrayList<Country>();
		for(Country c : graph.vertexSet()){
			country.add(c);
		}
		return country;
	}

	/*
	 * Metodo che restituisce il numero di componenti connesse del grafo
	 */
	public Object getNumberOfConnectedComponents() {
		ConnectivityInspector<Country, DefaultEdge> inspector=new ConnectivityInspector<Country, DefaultEdge>(graph);
		return inspector.connectedSets().size();
	}

	/*
	 * Metodo che restituisce gli stati e per ognuno il numero di stati confinanti
	 */
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
