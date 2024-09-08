package javasource.resourceobjects;

/**
 * Interfaccia esame
 */
public interface Esame {

	/**
	 * Per calcolare il voto dell'esame
	 * @return Integer voto finale
	 */
	public Integer calcolaVoto();
	
	/**
	 * @return voto
	 */
	public Integer getVoto();

	/**
	 * @param voto Voto
	 */
	public void setVoto(Integer voto);
	
	/**
	 * @return materia
	 */
	public Materia getMateria();
	
	/**
	 * @param materia Materia
	 */
	public void setMateria(Materia materia);

	/**
	 * @return studente
	 */
	public Studente getStudente();

	/**
	 * @param studente Studente
	 */
	public void setStudente(Studente studente);

	/**
	 * @return lode
	 */
	public Boolean getLode();

	/**
	 * @param lode lode
	 */
	public void setLode(Boolean lode);
	
	/**
	 * Verifica se i due esami sono identici estensionalmente
	 * @param e Esame
	 * @return Esito dell'operazione
	 */
	public Boolean compareExams(Esame e);
}
