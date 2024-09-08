package javasource.resourceobjects;

import javasource.Constants;

/**
 * Rappresenta un esame universitario nella sua forma più comune
 */

public class EsameSemplice implements Esame{
	
	/**
	 * Voto dell'esame
	 */
	private Integer voto;
	
	/**
	 * Materia dell'esame
	 */
	private Materia materia;
	
	/**
	 * Studente che ha conseguito l'esame
	 */
	private Studente studente;
	
	/**
	 * Lode
	 */
	private Boolean lode;
	
	/**
	 * Costruttore
	 * 
	 * @param voto Voto
	 * @param s Studente
	 * @param m Materia
	 * @param lode Lode
	 */
	public EsameSemplice(Integer voto, Boolean lode, Studente s, Materia m) {
		super();
		this.setVoto(voto);
		this.setStudente(s);
		this.setMateria(m);
		this.setLode(lode);
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
		if (voto >= 18 && voto <= 30) 
			this.voto = voto;
		else 
			this.voto = null;	}

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
	 * @return lode
	 */
	public Boolean getLode() {
		return lode;
	}

	/**
	 * @param lode Lode
	 */
	public void setLode(Boolean lode) {
		if (this.voto != null && this.voto == 30) {
			this.lode = lode;
		} else {
			this.lode = false;
		}
	}

	/**
	 * Calcola il voto
	 */
	@Override
	public Integer calcolaVoto() {
		return this.getVoto();
	}
	
	/**
	 * Serializza la classe in formato stringa
	 */
	@Override
	public String toString() {
		return "semplice" + Constants.ESAMI_SEP + this.getVoto() + Constants.ESAMI_SEP + this.getLode() + Constants.ESAMI_SEP + this.getStudente().getMatricola() + Constants.ESAMI_SEP + this.getMateria().getTitolo();
	}
	
	/**
	 * Verifica se i due esami sono identifi estensionalmente
	 * @param e Esame
	 * @return Esito dell'operazione
	 */
	@Override
	public Boolean compareExams(Esame e) {
		if (e == null || !(e instanceof EsameSemplice)) {
			return false;
		}
		
		if (
				this.voto == e.getVoto() &&
				this.lode == e.getLode() &&
				this.materia.compareMaterie(e.getMateria()) &&
				this.studente.compareStudenti(e.getStudente())
		) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Restituisce un nuovo oggetto di tipo EsameSemplice inizializzato con i valori dell'esame attuale
	 * @return EsameSemplice Esame semplice
	 */
	public EsameSemplice copyEsameSemplice() {
		
		EsameSemplice es = new EsameSemplice(voto, lode, studente, materia);
		
		return es;
	}

}
