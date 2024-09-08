package javasource;

import java.awt.Color;
import java.io.File;

/**
 * Questa classe racchiude tutte le costanti utilizzate nel programma
 * 
 * @author Barbi Michael
 */

/*
https://patorjk.com/software/taag/
Sito web per le cornisci ASCII
ANSI Shadow
Calvin S
*/

public abstract class Constants {
	
	/**
	 * 
	 * Costruttore
	 */
	public Constants() {
		
	}
	
	/*
	 ██████╗██╗      █████╗ ███████╗███████╗
	 ██╔════╝██║     ██╔══██╗██╔════╝██╔════╝
	 ██║     ██║     ███████║███████╗███████╗
	 ██║     ██║     ██╔══██║╚════██║╚════██║
	 ╚██████╗███████╗██║  ██║███████║███████║
	  ╚═════╝╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝
	 */
	                                         
	
	/**
	 * files project path
	 */
	public final static String PATH = "src" + File.separator + "staticfiles" + File.separator + "files" + File.separator;
	
	/**
	 * Percorso file Materie
	 */
	public final static String MATERIE = (new File(Constants.PATH + "materie.txt")).getAbsolutePath();
	
	/**
	 * Separatore delle materie
	 */
	public final static String MATERIE_SEP = ",";
	
	/**
	 * Percorso file users. Contiene tutti gli utenti registrati
	 */
	public final static String STUDENTI = (new File(Constants.PATH + "studenti.txt")).getAbsolutePath();
	
	/**
	 * Separatore informazioni del file users
	 */
	public final static String STUDENTI_SEP = ",";
	
	/**
	 * Percorso file di log. Contiene tutti gli errori del programma
	 */
	public final static String LOG = (new File(Constants.PATH + "log.txt")).getAbsolutePath();
	
	/**
	 * Separatore di errori
	 * 
	 * In questo caso due newLine con formato specifico del sistema (ex: \n, \r\n)
	 */
	public final static String LOG_SEP = System.getProperty("line.separator") /*+ System.getProperty("line.separator")*/;	
	
	
	/**
	 * Percorso del file contenente tutti gli esami effettuati da uno studente
	 */
	public final static String ESAMI = (new File(Constants.PATH + "esami.txt")).getAbsolutePath();
	
	/**
	 * Carattere separatore nel file esami.txt
	 */
	public final static String ESAMI_SEP = ",";
	
	/*
	██████╗  █████╗ ███╗   ██╗███████╗██╗     ███████╗
	██╔══██╗██╔══██╗████╗  ██║██╔════╝██║     ██╔════╝
	██████╔╝███████║██╔██╗ ██║█████╗  ██║     ███████╗
	██╔═══╝ ██╔══██║██║╚██╗██║██╔══╝  ██║     ╚════██║
	██║     ██║  ██║██║ ╚████║███████╗███████╗███████║
	╚═╝     ╚═╝  ╚═╝╚═╝  ╚═══╝╚══════╝╚══════╝╚══════╝
	*/	
	
	/**
	 * Nome dell'applicazione
	 */
	public final static String APPLICATION_NAME = "University Exams Manager";

	
	/*
	┌─┐┌─┐┬  ┌─┐┬─┐┌─┐
	│  │ ││  │ │├┬┘└─┐
	└─┘└─┘┴─┘└─┘┴└─└─┘
	*/
	
	/**
	 * Sfondo dell'applicazione dark
	 */
	public final static Color BACKGROUND_COLOR_DARK_RGB  = new Color(10,10,10);
	
	/**
	 * Sfondo dell'applicazione light
	 */
	public final static Color BACKGROUND_COLOR_LIGHT_RGB = new Color(255,255,255);
	
	/**
	 * Colore h1 light
	 */
	public final static Color COLOR_H1_LIGHT_RGB = new Color(255,255,255);
	
	/**
	 * Colore h1 dark
	 */
	public final static Color COLOR_H1_DARK_RGB = new Color(10,10,10);
	
	/**
	 * Colore h2 light
	 */
	public final static Color COLOR_H2_LIGHT_RGB = new Color(255,255,255);
	
	/**
	 * Colore h2 dark
	 */
	public final static Color COLOR_H2_DARK_RGB = new Color(10,10,10);
	
	/**
	 * Colore link
	 */
	public final static Color COLOR_LINK = new Color(19, 33, 235);
	
	/**
	 * Colore testo
	 */
	public final static Color COLOR_TEXT = BACKGROUND_COLOR_DARK_RGB;
	
	/**
	 * Colore testo in errore
	 */
	public final static Color COLOR_ERROR = Color.RED;
	
	/**
	 * Sfondo scuro della parte vuota di una JTable
	 */
	public final static Color BACKGROUND_COLOR_DARK_TABLE_EMPTY = new Color(10,10,10);
	
	/**
	 * Sfondo chiaro della parte vuota di una JTable
	 */
	public final static Color BACKGROUND_COLOR_LIGHT_TABLE_EMPTY = new Color(255, 255, 255);
	
	/**
	 * Colore di sfondo delle celle selezionate in una JTable
	 */
	public final static Color BACKGROUND_SELECTED_CELL_TABLE = new Color(2, 127, 199);
	
	/**
	 * Colore di foreground delle celle selezionate in una JTable
	 */
	public final static Color FOREGROUND_SELECTED_CELL_TABLE = new Color(10,10,10);
	
	/*
	┌─┐┌─┐┌┐┌┌┬┐┌─┐
	├┤ │ ││││ │ └─┐
	└  └─┘┘└┘ ┴ └─┘
	*/
	
	/**
	 * Font dell'applicazione
	 */
	public final static String FONT = "Arial";
	
	/*
	┌─┐┬┌─┐┌─┐┌─┐
	└─┐│┌─┘├┤ └─┐
	└─┘┴└─┘└─┘└─┘
	*/
	
	/**
	 * Dimensione in pixel dell'Icon title
	 */
	public final static int ICON_TITLE_SIZE = 200;
	
	/**
	 * Dimensione degli h1
	 */
	public final static int H1_SIZE = 60;
	
	/**
	 * Dimensione degli h2
	 */
	public final static int H2_SIZE = 40;
	
	/**
	 * Dimensione degli h3
	 */
	public final static int H3_SIZE = 25;
	
	/**
	 * Text
	 */
	public final static int TEXT = 15;

	/**
	 * Dimensione minima di una finestra
	 */
	public final static Integer[] MINIMUM_SIZE = {1350,800};
}
