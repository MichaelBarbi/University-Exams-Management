package javasource.resourceobjects;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javasource.Constants;

/**
 * Logger dell'applicazione
 */
public class Logger implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore
	 */
	public Logger() {
		super();
	}

	/**
	 * Stampa su file di log
	 * 	
	 * @param message Messaggio
	 * @param date Data cattura dell'errore
	 * @param className Nome della classe
	 * @param methodName Nome del metodo
	 * @param filePath Percorso alternativo per la stampa dei log
	 */
	public static void writeOnFile(String message, LocalDateTime date, String className, String methodName , String filePath) {
		try {
			
			File file = null;;
			
			if (filePath == null) {
				file = new File(Constants.LOG);	
			} else {
				file = new File(filePath);
			}
			
			FileWriter fos = new FileWriter(file, true);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			fos.write(Constants.LOG_SEP + date.format(formatter) + "[" + className + " - " + methodName + "] Error: " + message);
			
			fos.close();
			
			
		} catch (Exception e) {
			System.err.println("\n" + e.getMessage());
		}
	}

}
