package it.polito.tdp.genes.model;

import java.util.Objects;

public class Adiacenza {
	String location1;
	String location2;
	int peso;
	public String getLocation1() {
		return location1;
	}
	public void setLocation1(String location1) {
		this.location1 = location1;
	}
	public String getLocation2() {
		return location2;
	}
	public void setLocation2(String location2) {
		this.location2 = location2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(location1, location2, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		return Objects.equals(location1, other.location1) && Objects.equals(location2, other.location2)
				&& peso == other.peso;
	}
	public Adiacenza(String location1, String location2, int peso) {
		super();
		this.location1 = location1;
		this.location2 = location2;
		this.peso = peso;
	}
	
	

}
