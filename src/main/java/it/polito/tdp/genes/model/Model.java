package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.genes.db.GenesDao;


public class Model {
	
	private GenesDao dao;
	private List<String> localizzazioni = new ArrayList<String>();
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<String> camminoMax;
	private double pesoMax;
	
	public Model() {
		dao = new GenesDao();
		localizzazioni = this.dao.getAllLocalizzazioni();
	}
	
	public void creaGrafo() {
		 grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		 Graphs.addAllVertices(this.grafo, localizzazioni);
		 List<Adiacenza> archi = new ArrayList<Adiacenza>(dao.getArchi());
		 for(Adiacenza a : archi) {
		  Graphs.addEdge(this.grafo, a.getLocation1(), a.getLocation2(), a.getPeso());}		 
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public String getCompConn(String localizzazione) {
		    String s ="";
			List<String> lista = new ArrayList<>();
			BreadthFirstIterator<String, DefaultWeightedEdge> bfv = 
			new BreadthFirstIterator<>(this.grafo, localizzazione);
			while (bfv.hasNext()) {
			String l = bfv.next();
			if (!l.equals(localizzazione)) {
			 lista.add(l);
			 l=bfv.getParent(l); 
			 }
			}
			for(String s1 : lista) {
			 for(Adiacenza a: dao.getArchi()) {
				if((a.location1.equals(localizzazione)&&a.location2.equals(s1))||(a.location2.equals(localizzazione)&&a.location1.equals(s1)))
				  s+=s1+" "+a.getPeso()+"\n";
			}}
             return s;
	}

	public List<String> getLocalizzazioni() {
		return localizzazioni;
	}

	public void setLocalizzazioni(List<String> localizzazioni) {
		this.localizzazioni = localizzazioni;
	}
	
	//ricorsione
	public String cercaCammino(String partenza) {
		String s="";
		this.camminoMax = null ;
		this.pesoMax = 0.0 ;
		
		List<String> parziale = new ArrayList<>() ;
		parziale.add(partenza) ;
		
		search(parziale);
		for(String s1 : camminoMax) {
			s+="\n"+s1;
		}
		return s;
	}
	
	private void search(List<String> parziale) {

			double peso = pesoCammino(parziale) ;
			if(peso>this.pesoMax) {
				this.pesoMax=peso ;
				this.camminoMax = new ArrayList<>(parziale);}
		
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1)) ;
		for(String v : vicini) {
			if(!parziale.contains(v)) {
				parziale.add(v) ;
				search(parziale) ;
				parziale.remove(parziale.size()-1) ;
			}
		}
	}

	private double pesoCammino(List<String> parziale) {
		double peso = 0.0 ;
		for(int i=1; i<parziale.size(); i++) {
			double p = this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i-1), parziale.get(i))) ;
			peso += p ;
		}
		return peso ;
	}
	
	
	
}