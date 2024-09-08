package javasource.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.jfree.data.category.DefaultCategoryDataset;

import javasource.resourceobjects.Esame;
import javasource.resourceobjects.EsameComposto;
import javasource.resourceobjects.EsameSemplice;
import javasource.resourceobjects.EsamiFileManager;
import javasource.resourceobjects.Logger;
import javasource.resourceobjects.Materia;
import javasource.resourceobjects.Studente;

/**
 * Gestisce tutte le entità in esecuzione nell'applicazione.
 * 
 * Offre una soluzione rapida per lavorare e manipolare l'insieme di tutte le classi in gioco.
 * Permette di riutilizzare oggetti già inizializzati in modo globale
 * 
 * Questa classe mira ad essere uno strumento che permetta di integrare, seppur in maniera elementare,
 * i concetti di Dependency injection e Inversion of Control offerti da alcuni framework java.
 */

public class ClassManager {

	/**
	 * Materie registrate nell'applicazione
	 */
	private List<Materia> materie;
	
	/**
	 * Studenti registrati nell'applicazione
	 */
	private List<Studente> studenti;
	
	/**
	 * Lista degli esami
	 */
	private List<Esame> esami;
	
	/**
	 * Lista degli esami usati durante l'udo dei filtri di ricerca
	 */
	private List<Esame> esamiFilter;
	
	/**
	 * Esami effetti sul file dell'utente
	 */
	private List<Esame> esamiFile;
	
	
	/**
	 * Manager degli pannelli
	 */
	private PanelsInitManager panelsM;
	
	/**
	 * Manager del file degli esami
	 */
	EsamiFileManager esamiFileManager = null;
	
	/**
	 * Costruttore
	 */
	public ClassManager() {
		esamiFileManager = new EsamiFileManager();
		materie = Materia.readMaterieFromFile();
		studenti = Studente.readStudentiFromFile();
		esamiFile = esamiFileManager.readEsamiFromFile(this);
		
		int size = (esamiFile == null) ? 0 : esamiFile.size();
		
		esami = new ArrayList<Esame>(size);
		esamiFilter = new ArrayList<Esame>(size);
		
		if (size > 0) {
			esami.addAll(esamiFile);
			esamiFilter.addAll(esami);
		}
	}


	/**
	 * @return materie
	 */
	public List<Materia> getMaterie() {
		return materie;
	}


	/**
	 * @param materie Lista di materie
	 */
	public void setMaterie(List<Materia> materie) {
		this.materie = materie;
	}

	/**
	 * @return esami
	 */
	public List<Esame> getEsami() {
		return esami;
	}


	/**
	 * @param esami Lista di esami
	 */
	public void setEsami(List<Esame> esami) {
		this.esami = esami;
	}


	/**
	 * @return esamiFilter
	 */
	public List<Esame> getEsamiFilter() {
		return esamiFilter;
	}


	/**
	 * @param esamiFilter Lista degli esami filtrati
	 */
	public void setEsamiFilter(List<Esame> esamiFilter) {
		this.esamiFilter = esamiFilter;
	}


	/**
	 * @return studenti
	 */
	public List<Studente> getStudenti() {
		return studenti;
	}


	/**
	 * @param studenti Lista di studenti
	 */
	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	/**
	 * @return esamiFile
	 */
	public List<Esame> getEsamiFile() {
		return esamiFile;
	}


	/**
	 * @param esamiFile Lista degli esami salvati su file
	 */
	public void setEsamiFile(List<Esame> esamiFile) {
		this.esamiFile = esamiFile;
	}


	/**
	 * @return esamiFileManager
	 */
	public EsamiFileManager getEsamiFileManager() {
		return esamiFileManager;
	}


	/**
	 * @param esamiFileManager Manager del file con gli esami
	 */
	public void setEsamiFileManager(EsamiFileManager esamiFileManager) {
		this.esamiFileManager = esamiFileManager;
	}
	
	/**
	 * @return panelsM
	 */
	public PanelsInitManager getPanelsM() {
		return panelsM;
	}


	/**
	 * @param panelsM Manager dei panels
	 */
	public void setPanelsM(PanelsInitManager panelsM) {
		this.panelsM = panelsM;
	}


	/**
	 * Ritorna lo studente dato la matricola
	 * 
	 * @param matricola Matricola dello studente
	 * @return studente Studente con tale matricola
	 */
	public Studente getStudenteFromMatricola(String matricola) {
		for (Studente stu : this.studenti) {
			if (stu.getMatricola().equals(matricola)) {
				return stu;
			}
		}
		
		return null;
	}
	
	/**
	 * Ritorna la materia dato il titolo
	 * @param titolo Titolo della materia
	 * @return materia Materia con tale titolo
	 */
	public Materia getMateriaFromTitolo(String titolo) {
		for (Materia m : this.materie) {
			if (m.getTitolo().trim().equals(titolo.trim())) {
				return m;
			}
		}
		
		return null;
	}
	
	/**
	 * Data una lista di esami, per ogni esame ne estrapola i dati per formare una matrice di esami
	 * @param src Da quale lista prelevare gli esami
	 * @return Matrice di dati ottenuti dalla lista degli esami
	 */
	public Object[][] getArrayDataFromEsami(String src) {
		
		List<Esame> e = new ArrayList<Esame>();
		if (src.equals("Esami")) {
			e = this.esami;			
		} else if (src.equals("Filter")) {
			if (this.esamiFilter == null) {
				this.esamiFilter = new ArrayList<>();
				this.esamiFilter.addAll(this.esami);
			}
			e = this.esamiFilter;
		} else {
			return null;
		}
		
		if (e == null) {
			Logger.writeOnFile("esami non e' valido", LocalDateTime.now(), "ClassManager", "getArrayDataFromEsami", null);
			return new Object[0][];
		}
		
		Object[][] data = new Object[e.size()][];
		
		for (int i = 0; i < e.size(); i++)  {	
			
			Object[] singleData = null;
			
			if (e.get(i) instanceof EsameSemplice) {
				
				EsameSemplice es = (EsameSemplice) e.get(i);
				
				singleData = new Object[] {
						es.getStudente().getMatricola(),
						es.getStudente().getNome() + " " + es.getStudente().getCognome(),
						es.getMateria().getTitolo(),
						es.getVoto(),
						es.getLode(),
						es.getMateria().getCfu(),
						"false"
				};
				
			} else if (e.get(i) instanceof EsameComposto) {
				
				EsameComposto ec = (EsameComposto) e.get(i);
				
				singleData = new Object[] {
						ec.getStudente().getMatricola(),
						ec.getStudente().getNome() + " " + ec.getStudente().getCognome(),
						ec.getMateria().getTitolo(),
						ec.getVoto(),
						ec.getLode(),
						ec.getMateria().getCfu(),
						"true"
				};
				
			} else {
				return null;
			}
			
			data[i] = singleData;
		}
		
		return data;
	}
	
	/**
	 * Rimuove un esame dalla lista degli esami
	 * @param index indice dell'esame nella lista
	 * @return esito dell'eleminazione
	 */
	public Boolean deleteExam(Integer index) {
		if (index == null || index < 0 || index > this.esami.size() - 1) {
			Logger.writeOnFile("index non valido", LocalDateTime.now(), "ClassManager", "deleteExam", null);
			return false;
		}
		
		Esame e = this.esami.get((int) index);
		this.esami.remove(e);
		this.esamiFilter.remove(e);
		
		return true;
	}
	
	/**
	 * Aggiunge un nuovo esame alla lista degli esami
	 * @param esame Nuovo esame
	 * @return Esito dell'operazione
	 */
	public Boolean addEsame(Esame esame) {
		if (esame == null) {
			Logger.writeOnFile("esame non e' valido", LocalDateTime.now(), "ClassManager", "addEsame", null);
			return false;
		}
		
		if (esame instanceof EsameSemplice || esame instanceof EsameComposto) {
			this.esami.add(esame);
			this.esamiFilter.add(esame);
		} else {
			Logger.writeOnFile("l'oggetto esame non e' un esame", LocalDateTime.now(), "ClassManager", "addEsame", null);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Restituisce una matrice di matricole
	 * 
	 * @return Matrice di stringhe
	 */
	public String[][] getMatricole() {
		if (this.studenti == null ) {
			Logger.writeOnFile("Non ci sono studenti", LocalDateTime.now(), "ClassManager", "getMatricole", null);
			return null;
		}
		
		String[][] matr = new String[this.studenti.size()][1];
		
		for (int i = 0; i < matr.length; i++) {
			matr[i][0] = this.studenti.get(i).getMatricola();
		}
		
		return matr;
	}
	
	/**
	 * Date tutte le materie configurate, ritorna i loro titoli
	 * @return Matrice di stringhe
	 */
	public String[][] getTitoli() {
		if (this.materie == null ) {
			Logger.writeOnFile("Non ci sono materie", LocalDateTime.now(), "ClassManager", "getTitoli", null);
			return null;
		}
		
		String[][] tit = new String[this.materie.size()][1];
		
		for (int i = 0; i < tit.length; i++) {
			tit[i][0] = this.materie.get(i).getTitolo();
		}
		
		return tit;
	}
	
	/**
	 * Ritorna un oggetto "Esame" like costruito partendo dal record selezionato nella tabella
	 * 
	 * @param index Indice dell'esame da selezionare
	 * @return Esame preso dalla tabella
	 */
	public Object createEsameFromTable(Integer index) {
		
		if (index == null || index < 0 ) {
			Logger.writeOnFile("Index non e' valido", LocalDateTime.now(), "ClassManager", "createEsameFromTable", null);
			return null;
		}
		
		if (this.esami == null || this.esami.size() == 0) {
			Logger.writeOnFile("Esami non e' valido", LocalDateTime.now(), "ClassManager", "createEsameFromTable", null);
			return null;
		}
		
		//Ottenere l'oggetto "esame" ottenendo l'esame dalla lista con indice _index
		
		Object esame = this.esami.get((Integer) index);
		
		if (esame == null) {
			Logger.writeOnFile("L'oggeto esame non e' valido", LocalDateTime.now(), "ClassManager", "createEsameFromTable", null);
			return null;
		}
		
		return esame;
	
	}
	
	/**
	 * Dato una lista di studenti, ne restituire una matrice
	 * @param studenti Lista di studenti
	 * @return Matrice di studenti
	 */
	public static Object[][] studentiListToMatrix(List<Studente> studenti) {
		
		if (studenti == null) {
			Logger.writeOnFile("studenti non e' valido", LocalDateTime.now(), "ClassManager", "studentiListToMatrix", null);
			return null;
		}
		
		Object[][] data = new Object[studenti.size()][Studente.class.getDeclaredFields().length];
		int cont = 0;
		
		for (Studente s : studenti) {
			data[cont][0] = s.getMatricola();
			data[cont][1] = s.getNome();
			data[cont][2] = s.getCognome();
			cont++;
		}
		
		return data;
	}
	
	/**
	 * Dato una lista di materie, ne restituisce una matrice
	 * @param materie Lista di materie
	 * @return Matrice di materie
	 */
	public static Object[][] materieListToMatrix(List<Materia> materie) {
		if (materie == null ) {
			Logger.writeOnFile("materie non e' valido", LocalDateTime.now(), "ClassManager", "materieListToMatrix", null);
			return null;
		}
		
		Object[][] data = new Object[materie.size()][Studente.class.getDeclaredFields().length];
		int cont = 0;
		
		for (Materia m : materie) {
			data[cont][0] = m.getTitolo();
			data[cont][1] = m.getCfu();
			cont++;
		}
		
		return data;
	}
	
	/**
	 * Verifica se nel database è già presente uno studente con la stessa matricola 
	 * @param matricola Matricola
	 * @return Esito dell'operazione
	 */
	public Boolean matricolaIsAlreadySet(String matricola) {
		for (Studente s : this.studenti) {
			if (s.getMatricola().equals(matricola)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Verifica se nel database è già presente una materia con lo stesso titolo 
	 * @param titolo Titolo
	 * @return Esito dell'operazione
	 */
	public Boolean titoloIsAlreadySet(String titolo) {
		for (Materia m : this.materie) {
			if (m.getTitolo().equals(titolo)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Ritorna la striga stessa con la prima lettera maiuscola e le successive tutte minuscole
	 * @param str Stringa
	 * @return Stringa ricomposta
	 */
	public static String getDataWellFormatted(String str) {
		if (str == null || str.length() == 0) {
			Logger.writeOnFile("str non e' valido", LocalDateTime.now(), "ClassManager", "getDataWellFormatted", null);
			return null;
		}
		
		String first = str.charAt(0) + "";
		first = first.toUpperCase();
		
		return first + str.substring(1, str.length()).toLowerCase();
	}
	
	/**
	 * Segnala se la matricola passata è già stata usata per un esame
	 * @param matricola Matricola
	 * @return Esito dell'operazione
	 */
	public Boolean hasMatricolaBeenUsed(String matricola) {
		
		for (Esame e : this.esami) {
			if (e.getStudente().getMatricola().equals(matricola)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Segnala se il titolo passato è già stato usato per un esame
	 * @param titolo Titolo
	 * @return Esito dell'operazione
	 */
	public Boolean hasTitoloBeenUsed(String titolo) {
		for (Esame e : this.esami) {
			if (e.getMateria().getTitolo().equals(titolo)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica se il contenuto degli esami attuali e di quelli su file sono diversi o meno
	 * @return Esito dell'operazione
	 */
	public Boolean areExamsListsDifferent() {
		if (this.esami != null && this.esamiFile != null) {
			
			if (this.esami.size() == this.esamiFile.size())  {
				
				//Devo verificare che tutti gli elementi siano uguali
				for (int i = 0; i < this.esami.size(); i++) {
					if (!this.esami.get(i).compareExams(this.esamiFile.get(i))) {
						return true;
					}
				}
				
				return false;
				
			} else {
				return true;
			}
		} 
		
		return null;
	}
	
	/**
	 * Verifica se alla chiusura dell'applicazione ci sono degli esami non salvati
	 */
	public void applicationClosing() {
		
		int choice = JOptionPane.showConfirmDialog(null, "Confermare per chiudere l'applicazione", "Chiusura applicazione", JOptionPane.OK_CANCEL_OPTION);
		
		if (choice == JOptionPane.OK_OPTION) {
			
			int saveChoice = 0;
			
			/**
			 * 1) Esami vuoto --> niente
			 * 2) Esami si ma file vuoto --> saveFileWithName
			 * 3) Esami si e file si --> salva sul file
			 */
			if (this.esami != null && this.esami.size() != 0 && (this.esamiFile == null || this.esamiFile.size() == 0)) {
				
				saveChoice = JOptionPane.showConfirmDialog(null, "Salvare le modifiche prima di uscire dall'applicazione", "Modiche non salvate", JOptionPane.OK_CANCEL_OPTION);
				
				if (saveChoice == JOptionPane.OK_OPTION) {
					Boolean esito = this.getEsamiFileManager().saveFileWithName(this);
				
					if (esito) {
						JOptionPane.showMessageDialog(null, "Il file è stato salvato", "Salvataggio File", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Il file non è stato salvato", "Salvataggio File", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			} else if (this.esami != null && this.esami.size() != 0 && this.esamiFile != null && this.areExamsListsDifferent()) {
				saveChoice = JOptionPane.showConfirmDialog(null, "Salvare le modifiche prima di uscire dall'applicazione", "Modiche non salvate", JOptionPane.OK_CANCEL_OPTION);
				
				if (saveChoice == JOptionPane.OK_OPTION) {
					this.getEsamiFileManager().saveFile(this);			
				}
			}
			
			System.exit(0);
		}
		
	}
	
	/**
	 * Ottiene la lista dei voti di una materia da passare all'istogramma 
	 * 
	 * @param index Indice della materia
	 * @return DefaultCategoryDataset Dataset di dati per il grafico
	 */
	public DefaultCategoryDataset getStatisticheVotiFromMateriaIndex(Integer index) {
		
		if (index == null ) {
			return null;
		}
		
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		
		//Ottendo la materia dall'indice
		Materia materia = this.materie.get(index);
		
		HashMap<Integer, Integer> voti_quantita = new HashMap<Integer, Integer>();
		
		for (Esame e : this.esami) {
			if (e.getMateria().getTitolo().equals(materia.getTitolo())) {
				if (voti_quantita.containsKey(e.getVoto())) {
					int vecchiaQua = voti_quantita.get(e.getVoto());
					voti_quantita.put(e.getVoto(), vecchiaQua + 1);
				} else {
					voti_quantita.put(e.getVoto(), 1);
				}
			}
		}
		
		//Inizializzazione del dataset
		
		for (Map.Entry<Integer, Integer> entry : voti_quantita.entrySet())  {
			Integer voto = entry.getKey();
			Integer quantita = entry.getValue();
			data.addValue(quantita, voto.toString(), "");
		}		
		
		return data;
		
	}
	
	/**
	 * Ottiene la lista dei voti di uno studente da passare all'istogramma 
	 * 
	 * @param index Indice della materia
	 * @return DefaultCategoryDataset Dataset di dati per il grafico
	 */
	public DefaultCategoryDataset getStatisticheVotiFromStudenteIndex(Integer index) {
		if (index == null ) {
			return null;
		}
		
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		
		//Ottendo la materia dall'indice
		Studente studente = this.studenti.get(index);
		
		HashMap<Integer, Integer> voti_quantita = new HashMap<Integer, Integer>();
		
		for (Esame e : this.esami) {
			if (e.getStudente().getMatricola().equals(studente.getMatricola())) {
				if (voti_quantita.containsKey(e.getVoto())) {
					int vecchiaQua = voti_quantita.get(e.getVoto());
					voti_quantita.put(e.getVoto(), vecchiaQua + 1);
				} else {
					voti_quantita.put(e.getVoto(), 1);
				}
			}
		}
		
		//Inizializzazione del dataset
		
		for (Map.Entry<Integer, Integer> entry : voti_quantita.entrySet())  {
			Integer voto = entry.getKey();
			Integer quantita = entry.getValue();
			data.addValue(quantita, voto.toString(), "");
		}		
		
		return data;
	}
}
