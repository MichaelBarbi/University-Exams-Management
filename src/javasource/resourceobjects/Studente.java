package javasource.resourceobjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javasource.Constants;

/**
 * La classe Studente rappresenta il singolo studente universitario.
 * 
 * @author Barbi Michael
 */

public class Studente {
	
	/**
	 * Matricola dello studente
	 */
	private String matricola = null;
	
	/**
	 * Nome dello studente
	 */
	private String nome = null;
	
	/**
	 * Cognome dello studente
	 */
	private String cognome = null;
	
	/**
	 * Costruttore
	 */
	public Studente() {
		super();
	}
	
	/**
	 * Costruttore
	 * @param matricola Matricola
	 * @param nome Nome
	 * @param cognome Cognome
	 */
	public Studente(String matricola, String nome, String cognome) {
		super();
		this.setMatricola(matricola);
		this.setNome(nome);
		this.setCognome(cognome);
	}

	/**
	 * @return matricola
	 */
	public String getMatricola() {
		return matricola;
	}

	/**
	 * @param matricola Matricola
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome Nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome Cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	@Override
	public String toString() {
		return this.getMatricola() + Constants.STUDENTI_SEP + this.getNome() + Constants.STUDENTI_SEP + this.getCognome();
	}
	
	/**
	 * Ritorna la lista di studenti registrati
	 * 
	 * @return lista di studenti
	 */
	public static List<Studente> readStudentiFromFile() {
		
		List<Studente> studenti = new ArrayList<Studente>();		
		
		try {
			
				File file = new File(Constants.STUDENTI);
				
				if (file.length() == 0) {
					return studenti;
				}
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				//Numero di studenti
				Integer size = Integer.parseInt(br.readLine());
				
				if (size == null || size < 0) {
					br.close();
					Logger.writeOnFile("Size non e' valido", LocalDateTime.now(), "Studente", "readStudentiFromFile", null);
					return null;
				}
				
				for (int i = 0; i < size; i++) {
					
					String[] line = br.readLine().split(Constants.STUDENTI_SEP);
					
					if (line[0] == null || line[0].length() <= 5) {
						br.close();
						Logger.writeOnFile("La matricola non e' valida", LocalDateTime.now(), "Studente", "readStudentiFromFile", null);
						return null;
					}
					
					if (line[1] == null || line[1].length() <= 0) {
						br.close();
						Logger.writeOnFile("Il nome non e' valido", LocalDateTime.now(), "Studente", "readStudentiFromFile", null);
						return null;
					}
					
					if (line[2] == null || line[2].length() <= 0) {
						br.close();
						Logger.writeOnFile("Il cognome non e' valido", LocalDateTime.now(), "Studente", "readStudentiFromFile", null);
						return null;
					}
					
					Studente s = new Studente(line[0], line[1], line[2]);		
					
					studenti.add(s);
				}
				
				br.close();
			
		} catch (Exception e) {
			Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "Studente", "readStudentiFromFile", null);
			return null;
		}
		
		return orderListByMatricola(studenti);
	}
	
	/**
	 * Stampa gli studenti su file
	 * @param studenti Lista di studenti
	 * @return esito della scrittura
	 */
	public static boolean writeStudentiOnFile(List<Studente> studenti) {
		
		try {
			
			if (studenti == null) {
				throw new Exception("studenti non e' valido");
			}
			
			File file = new File(Constants.STUDENTI);
			
			FileWriter fw = new FileWriter(file);
			
			fw.write(studenti.size() + "\n");
			
			for (Studente s : studenti) {
				if (s == null) {
					fw.close();
					throw new Exception("studente non e' valido");
				}
				
				fw.write(s.toString() + "\n");
			}
			
			fw.close();
						
			return true;
			
		} catch (Exception e) {
			Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "Studente", "writeStudentiOnFile", null);
			return false;
		}
	}
	
	/**
	 * Restituisce la lista ordinata numericamente per matricola
	 * @param list Lista di studenti
	 * @return Lista di studenti riordinata
	 */
	public static List<Studente> orderListByMatricola(List<Studente> list) {
		
		if (list == null) {
			Logger.writeOnFile("list non e' valido", LocalDateTime.now(), "Studente", "orderListByMatricola", null);
			return null;
		}
		
		if (list.size() == 0) {
			return list;
		}
		
		Collections.sort(list, new Comparator<Studente>() {
			@Override
			public int compare(Studente s1, Studente s2) {
				return s1.getMatricola().compareTo(s2.getMatricola());
			}
		});
		
		return list;
	}
	
	/**
	 * Verifica se due studenti sono identici estensionalmente
	 * @param s Studente
	 * @return Esito dell'operazione
	 */
	public Boolean compareStudenti(Studente s) {
		if (s == null) {return false;}
		
		if (this.getMatricola().equals(s.getMatricola()) && this.nome.equals(s.getNome()) && this.cognome.equals(s.getCognome())) {
			return true;
		}
		return false;
	}

}
