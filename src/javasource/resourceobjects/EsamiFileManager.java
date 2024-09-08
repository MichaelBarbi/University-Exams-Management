package javasource.resourceobjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javasource.Constants;
import javasource.UI.ClassManager;

/**
 * Questa classe permette di manipolare e gestire il file contenente gli esami
 */

public class EsamiFileManager {
	
	/**
	 * File creato/aperto dall'utente
	 */
	private File esamiFile;
	
	/**
	 * Costruttore
	 */
	public EsamiFileManager() {
		this.esamiFile = null;
	}

	/**
	 * @return esamiFile
	 */
	public File getEsamiFile() {
		return esamiFile;
	}
	
	/**
	 * Ritorna il nome del file aperto
	 * @return String Nome
	 */
	public String getEsamiFileName() {
		return esamiFile.getAbsolutePath();
	}

	/**
	 * Setta il nuovo file aperto
	 * @param newPath Nuovo percorso
	 */
	public void setEsamiFile(String newPath) {
		this.esamiFile = new File(newPath);
	}
	
	/**
	 * Salva le modifiche sul file attuale
	 * 
	 * @param classManager manager delle classi
	 */
	public void saveFile(ClassManager classManager) {
		
		//Se il file non è presente richiamare salvaConNome		
		if (classManager.getEsamiFile() == null) {
			
			if (classManager.getEsami() != null && classManager.getEsami().size() != 0) {
				//Richiamare la funzione per il salvataggio con nome
				boolean esito = classManager.getEsamiFileManager().saveFileWithName(classManager);
				
				if (esito) {
					JOptionPane.showMessageDialog(null, "Il file è stato salvato", "Salvataggio File", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Il file non è stato salvato", "Salvataggio File", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Il file è vuoto", "Salvataggio File", JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			//Verificare che la lista attuale sia diversa rispetto a quella presente nel file
			if (classManager.areExamsListsDifferent()) {
				classManager.getEsamiFile().clear();
				classManager.getEsamiFile().addAll(classManager.getEsami());
				classManager.getEsamiFileManager().writeExamsOnFile(classManager.getEsami());
				JOptionPane.showMessageDialog(null, "Salvataggio avvenuto", "Operazione completata", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Salva un nuovo file con nome
	 * @param manager Manager delle classi
	 * @return Esito della funzione
	 */
	public Boolean saveFileWithName(ClassManager manager) {
		if (manager.getEsami() == null) {
			Logger.writeOnFile("gli esami non sono definiti", LocalDateTime.now(), "EsamiFileManager", "saveFileWithName", null);
			return false;
		}
		
		if (manager.getEsami().size() == 0) {
			return false;
		}
		
		/**
		 * Finestra standard con il file system nel quale selezionare la directory per salvare il nuovo file
		 */
		JFileChooser fileChooser = new JFileChooser();
		
		int returnValue = fileChooser.showSaveDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			//Ottengo il file selezionto dall'utente
			File outputFile = fileChooser.getSelectedFile();
			
			if (outputFile.length() != 0) {
				//Il file selezionato contiene già dei dati, chiedere se si vuole sovvrascrivere
				
				int scelta = JOptionPane.showConfirmDialog(null, "Il file contiene del testo, sovvrascivere", "File non vuoto", JOptionPane.YES_NO_CANCEL_OPTION);
		        
				if (scelta != JOptionPane.YES_OPTION) {
					return false;
				}
				
			}
			
			this.esamiFile = outputFile;
			
			try {
				
				if (writeExamsOnFile(manager.getEsami())) {
					manager.setEsamiFile(new ArrayList<Esame>());
					manager.getEsamiFile().addAll(manager.getEsami());
					return true;
				}
				
                return false;
				
			} catch (Exception e) {
				Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "EsamiFileManager", "saveFileWithName", null);
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Ritorna l'esito del caricamento di un file esterno nell'applicazione
	 * @param manager manager delle classi
	 * @return Esito dell'operazione
	 */
	public Boolean importEsamiFile(ClassManager manager) {		
		/**
		 * Finestra standard con il file system nel quale selezionare il file che si intende utilizzare
		 */
		JFileChooser fileChooser = new JFileChooser();
		
		int returnValue = fileChooser.showSaveDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			try {
				
				File selectedFile = fileChooser.getSelectedFile();
				this.esamiFile = selectedFile;
				
				List<Esame> esamiFromFile = readEsamiFromFile(manager);
				
				if (esamiFromFile == null) {
					return false;
				}
				
				manager.setEsami(new ArrayList<Esame>());
				manager.setEsamiFile(new ArrayList<Esame>());
				manager.setEsamiFilter(new ArrayList<Esame>());
				
				for (Esame e : esamiFromFile) {
					if (e instanceof EsameSemplice) {
						manager.getEsami().add(((EsameSemplice)e).copyEsameSemplice());
						manager.getEsamiFile().add(((EsameSemplice)e).copyEsameSemplice());
					} else {
						manager.getEsami().add(((EsameComposto)e).copyEsameComposto());
						manager.getEsamiFile().add(((EsameComposto)e).copyEsameComposto());
					}
					
					
				}
				manager.getEsamiFilter().addAll(manager.getEsami());
				
				return true;
				
			} catch (Exception e) {
				Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "EsamiFileManager", "importEsamiFile", null);
				return false;
			}
			
		} else {
			return false;
		}
	}
	

	/**
	 * Legge gli esami dal file selezionato
	 * @param classManager manager delle classi
	 * @return Lista di esami
	 */
	public List<Esame> readEsamiFromFile(ClassManager classManager) {
		if (this.esamiFile == null || this.esamiFile.length() == 0 ) {
			return null;
		}		
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(this.esamiFile));
			
			Integer size = Integer.parseInt(br.readLine());
			
			if (size == null || size == 0) {
				br.close();
				Logger.writeOnFile("size non e' valido", LocalDateTime.now(), "EsamiFileManager", "readEsamiFromFile", null);
				return null;
			}
			
			List<Esame> list = new ArrayList<Esame>(size);
			
			for (int i = 0; i < size; i++) {
				String[] line = br.readLine().split(Constants.ESAMI_SEP);
				
				if (line == null || line.length == 0) {
					br.close();
					Logger.writeOnFile("line non e' valido", LocalDateTime.now(), "EsamiFileManager", "readEsamiFromFile", null);
					return null;
				}
				
				Esame esame = null;
				
				//Controllo la tipologia di esame
				switch(line[0]) {
				case "semplice":
					esame = new EsameSemplice(
							Integer.parseInt(line[1]),
							Boolean.parseBoolean(line[2]), 
							classManager.getStudenteFromMatricola(line[3]), 
							classManager.getMateriaFromTitolo(line[4])
					);
					break;
				case "composto":
					//Leggere da file un singolo esame composto
					//Devo leggere anche la seconda riga
					String[] secondLine = br.readLine().split(Constants.ESAMI_SEP);
					List<Integer> voti = new ArrayList<>(secondLine.length);
					List<Integer> pesi = new ArrayList<>(secondLine.length);
					
					int j = 0;
					
					for (j = 0; j < (secondLine.length / 2); j++) {
						voti.add(Integer.parseInt(secondLine[j]));
					}
					
					for (j = (secondLine.length / 2); j < secondLine.length; j++) {
						pesi.add(Integer.parseInt(secondLine[j]));
					}
					
					esame = new EsameComposto(
							Integer.parseInt(line[1]),
							Boolean.parseBoolean(line[2]), 
							classManager.getStudenteFromMatricola(line[3]), 
							classManager.getMateriaFromTitolo(line[4]), 
							voti, 
							pesi);
					
					break;
				default:
					br.close();
					Logger.writeOnFile("Tipologia non valida", LocalDateTime.now(), "EsamiFileManager", "readEsamiFromFile", null);
					return null;
				}
				
				list.add(esame);
			}
			
			br.close();
			
			return list;
			
		} catch (Exception e) {
			Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "EsamiFileManager", "readEsamiFromFile", null);
			return null;
		}
	}
	
	/**
	 * Scrive gli esami su file
	 * @param esami Lista di esami
	 * @return Esito dell'operazione
	 */
	public Boolean writeExamsOnFile(List<Esame> esami) {
		if (esami == null || this.esamiFile == null) {
			Logger.writeOnFile("esami o file non valido", LocalDateTime.now(), "EsamiFileManager", "writeExamsOnFile", null);
			return false;
		}
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.esamiFile));
			
			//Inizio scrittura
			bw.write(esami.size() + "\n");
			
			for (Esame e : esami) {
				bw.write(e.toString() + "\n");
			}
			
			bw.close();
			
			return true;
			
		} catch (Exception e) {
			Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "EsamiFileManager", "writeExamsOnFile", null);
			return false;
		}
	}
	
}
