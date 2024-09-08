package javasource.resourceobjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javasource.Constants;

/**
 * Rappresenta un esame universitario composto da due o più prove parziali
 */

public class EsameComposto implements Esame{
	
	/**
	 * Voto dell'esame
	 */
	private Integer voto;
	
	/**
	 * lode
	 */
	private Boolean lode;
	
	/**
	 * Studente che ha conseguito l'esame
	 */
	private Studente studente;
	
	/**
	 * Materia dell'esame
	 */
	private Materia materia;
	
	/**
	 * Lista dei voti parziali
	 */
	List<Integer> votiParziali;
	
	/**
	 * Lista dei pesi di ogni parziale
	 */
	List<Integer> percPesi;
	
	/**
	 * Costruttore 
	 * 
	 * @param s Studente
	 * @param m Materia
	 * @param voto Voto
	 * @param lode Lode
	 * @param voti lista dei voti
	 * @param pesi lista dei pesi
	 */
	public EsameComposto(Integer voto, Boolean lode, Studente s, Materia m, List<Integer> voti, List<Integer> pesi) {
		super();
		this.setStudente(s);
		this.setMateria(m);
		this.setVoto(voto);
		this.setLode(lode);
		this.setVotiParziali(voti);
		this.setPercPesi(pesi);
	}

	/**
	 * @return voto
	 */
	public Integer getVoto() {
		return voto;
	}



	/**
	 * @param voto Voto
	 */
	public void setVoto(Integer voto) {
		this.voto = voto;
	}



	/**
	 * @return lode
	 */
	public Boolean getLode() {
		return lode;
	}



	/**
	 * @param lode Lode
	 */
	public void setLode(Boolean lode) {
		this.lode = lode;
	}



	/**
	 * @return studente
	 */
	public Studente getStudente() {
		return studente;
	}



	/**
	 * @param studente Studente
	 */
	public void setStudente(Studente studente) {
		this.studente = studente;
	}



	/**
	 * @return materia
	 */
	public Materia getMateria() {
		return materia;
	}



	/**
	 * @param materia Materia
	 */
	public void setMateria(Materia materia) {
		this.materia = materia;
	}



	/**
	 * @return votiParziali
	 */
	public List<Integer> getVotiParziali() {
		return votiParziali;
	}



	/**
	 * @param votiParziali Voti parziali
	 */
	public void setVotiParziali(List<Integer> votiParziali) {
		this.votiParziali = votiParziali;
	}



	/**
	 * @return percPesi
	 */
	public List<Integer> getPercPesi() {
		return percPesi;
	}



	/**
	 * @param percPesi Pesi in formato percentuale
	 */
	public void setPercPesi(List<Integer> percPesi) {
		this.percPesi = percPesi;
	}



	/**
	 * Calcola e restituisce il voto ottenuto
	 */
	@Override
	public Integer calcolaVoto() {
		Integer media = 0;
		Integer somma = 0;
		
		
		//Verifico che i voti e i pesi siano accettabili
		 
		if ( this.getVotiParziali() == null || this.getVotiParziali().size() == 0 || this.getPercPesi() == null || this.getPercPesi().size() == 0 || (this.getPercPesi().size() != this.getVotiParziali().size())) {
			Logger.writeOnFile("Errore durante il calcolo del voto", LocalDateTime.now(), "EsameComposto", "calcolaVoto", null);
			return null;
		}
		
		Integer size = this.getVotiParziali().size();
		
		
		//Algoritmo notevole della media pesata
		 
		for (int i = 0; i < size; i++) {
			somma += this.getPercPesi().get(i);
			media += this.getVotiParziali().get(i) * this.getPercPesi().get(i);
		}
		
		media = media / somma;
		
		return media;
	}
	
	/*
	 * Override del metodo toString()
	 */
	@Override
	public String toString() {
		String str = "composto" + Constants.ESAMI_SEP + 
				this.getVoto() + Constants.ESAMI_SEP +
				this.getLode() + Constants.ESAMI_SEP +
				this.getStudente().getMatricola() + Constants.ESAMI_SEP +
				this.getMateria().getTitolo() + "\n";
		
		for (int i = 0; i < this.getVotiParziali().size(); i++) {
			str += this.getVotiParziali().get(i) + Constants.ESAMI_SEP;
		}
		
		for (int i = 0; i < this.getPercPesi().size(); i++) {
			str += (i != (this.getPercPesi().size() - 1)) ? this.getPercPesi().get(i) + Constants.ESAMI_SEP : this.getPercPesi().get(i);
		}
		
		return str;
	}
	
	/**
	 * Verifica se i due esami sono identifi estensionalmente
	 * @param e Esame
	 * @return Esito dell'operazione
	 */
	@Override
	public Boolean compareExams(Esame e) {
		if (e == null || !(e instanceof EsameComposto)) {
			return false;
		}
		
		EsameComposto c = (EsameComposto) e;
		
		if (
				this.voto == e.getVoto() &&
				this.lode == e.getLode() &&
				this.materia.compareMaterie(e.getMateria()) &&
				this.studente.compareStudenti(e.getStudente())
		) {
			//Controllo voti parziali
			if (this.votiParziali != null && c.getVotiParziali() != null && (this.votiParziali.size() != c.getVotiParziali().size() ) ) {
				return false;
			}
			
			for (int i = 0; i < this.votiParziali.size(); i++) {
				if (this.votiParziali.get(i) != c.getVotiParziali().get(i)) {
					return false;
				}
			}
			
			//Controllo sui pesi
			if (this.percPesi != null && c.getPercPesi() != null && (this.percPesi.size() != c.percPesi.size()) ) {
				return false;
			}
			
			for (int i = 0; i < this.percPesi.size(); i++) {
				if (this.percPesi.get(i) != c.percPesi.get(i)) {
					return false;
				}
			}
			
			return true;
			
		}
		
		return false;
	}
	
	/**
	 * Restituisce un nuovo oggetto di tipo EsameComposto inizializzato con i valori dell'esame attuale
	 * @return EsameComposto EsameComposto
	 */
	public EsameComposto copyEsameComposto() {
		
		List<Integer> v = new ArrayList<Integer>();
		v.addAll(this.votiParziali);
		List<Integer> p = new ArrayList<Integer>();
		p.addAll(this.percPesi);
		
		EsameComposto ec = new EsameComposto(voto, lode, studente, materia, v, p);
		
		return ec;
	}

}
