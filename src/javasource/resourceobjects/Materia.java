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
 * La classe materia rappresenta una materia universitaria
 * 
 * @author barbi michael
 */

public class Materia {

	/**
	 * Titolo della materia ex: Matematica, Lingua inglese
	 */
	private String titolo = null;
	
	/**
	 * CFU
	 */
	private Integer cfu = null;
	
	/**
	 * Costruttore
	 */
	public Materia() {
		super();
	}

	/**
	 * Costruttore
	 * 
	 * @param titolo Titolo
	 * @param cfu CFU
	 */
	public Materia(String titolo, Integer cfu) {
		super();
		this.setTitolo(titolo);
		this.setCfu(cfu);
	}

	/**
	 * @return titolo della materia
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param titolo Titolo della materia
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * @return CFU
	 */
	public Integer getCfu() {
		return cfu;
	}

	/**
	 * @param cfu CFU
	 */
	public void setCfu(Integer cfu) {
		if (cfu > 0)
			this.cfu = cfu;
		else
			this.cfu = null;
	}

	/**
	 * Override del metodo toString
	 */
	@Override
	public String toString() {
		return this.getTitolo() + Constants.MATERIE_SEP + this.getCfu();
	}
	
	/**
	 * Ritorna le materie registrate nell'applicazione salvate su file
	 * 
	 * @return Lista delle materie
	 */
	public static List<Materia> readMaterieFromFile() {
		List<Materia> materie = new ArrayList<Materia>();
		
		try {
			
			//Apertura del file
			File materieFile = new File(Constants.MATERIE);
			
			if (materieFile.length() == 0) {
				return materie;
			}
			
			//inizializzazione del lettore su file
			BufferedReader br = new BufferedReader(new FileReader(materieFile));
			
			//numero di materie nel file
			Integer size = 0;
			
			size = Integer.parseInt(br.readLine());
			
			if (size == null || size < 0) {
				Logger.writeOnFile("size invalido", LocalDateTime.now(), "Materia", "readMaterieFromFile", null);
				br.close();
				return null;
			}
			
			for (int i = 0; i < size; i++) {
				
				//Titolo,CFU
				String[] line = br.readLine().split(Constants.MATERIE_SEP);
				
				if (line[0] == null || line[0].length() == 0) {
					Logger.writeOnFile("titolo invalido", LocalDateTime.now(), "Materia", "readMaterieFromFile", null);
					br.close();
					return null;
				}
				
				if (line[1] == null || line[1].length() == 0) {
					Logger.writeOnFile("cfu invalido", LocalDateTime.now(), "Materia", "readMaterieFromFile", null);
					br.close();
					return null;
				}
				
				Materia m = new Materia(line[0], Integer.parseInt(line[1]));
				
				materie.add(m);
			}
			
			br.close();
			
			
		} catch (Exception e) {
			Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "Materia", "readMaterieFromFile", null);
		}
		
		return orderListByTitolo(materie);
	}
	
	/**
	 * Scrivi le materie su file
	 * @param materie Lista di materie
	 * @return Esito della scrittura
	 */
	public static boolean writeMaterieOnFile(List<Materia> materie) {
		
		try {
			
			if (materie == null) {
				Logger.writeOnFile("materie non e' valido", LocalDateTime.now(), "Materia", "writeMaterieOnFile", null);
				return false;
			}
			
			File materieFile = new File(Constants.MATERIE);
			
			//File writer
			FileWriter fw = new FileWriter(materieFile);
			
			
			fw.write(materie.size() + "\n");
			
			for (Materia m : materie) {
				fw.write(m.toString() + "\n");
			}
			
			fw.close();
			
			return true;
			
		} catch (Exception e) {
			Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "Materia", "writeMaterieOnFile", null);
			return false;
		}
	}
	
	/**
	 * Ordina la lista di materie per titolo
	 * @param list Lista delle materie
	 * @return Lista di materie riordinata
	 */
	public static List<Materia> orderListByTitolo(List<Materia> list) {
		
		if (list == null) {
			Logger.writeOnFile("list non e' valido", LocalDateTime.now(), "Materia", "orderListByTitolo", null);
			return null;
		}
		
		if (list.size() == 0) {
			return list;
		}
		
		Collections.sort(list, new Comparator<Materia>() {
			@Override
			public int compare(Materia m1, Materia m2) {
				return m1.getTitolo().compareTo(m2.getTitolo());
			}
		});
		
		return list;
	}
	
	/**
	 * Verifica se due materie sono identiche estensionalmente
	 * @param m materia
	 * @return Esito dell'operazione
	 */
	public Boolean compareMaterie(Materia m) {
		if (m == null) {return false;}
		
		if (this.titolo.equals(m.getTitolo()) && this.cfu == m.getCfu()) {
			return true;
		}
		return false;
	}
	
}
