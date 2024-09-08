package javasource.UI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javasource.Constants;

/**
 * UI Manager dell'applicazione
 */
public class FrameManager extends JFrame{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Larghezza dello schermo dell'utente
	 */
	private static int screenDefaultWidth;
	
	/**
	 * Altezza dello schermo dell'utente
	 */
	private static int screenDefaultHeight;
	
	/**
	 * Costruttore
	 * 
	 * @param name Nome dell'applicazione 
	 */
	public FrameManager(String name) {
		super(name);
		
		//Inizia il file system se necessario
		initFile();
		
		//inizializzo il manager delle classi
		ClassManager classManager = new ClassManager();	
		
		//Ottengo la dimensione dello schermo dell'utente
		defaultScreenSize();
		//Qui setto invece la dimensione minima
		setMinimumSize(new Dimension(Constants.MINIMUM_SIZE[0], Constants.MINIMUM_SIZE[1]));
		        
		//Questo contenitore conterrà a sua volta la UI di ogni pagina dell'applicazione
        JPanel panelCont = new JPanel();
        //Stabilisce quale pagina mostrare
        CardLayout cl = new CardLayout();
        
        panelCont.setLayout(cl);
        
        //Manager dei vari pannelli usati dall'applicazione
        PanelsInitManager pim = new PanelsInitManager();
        boolean pimRes = pim.initPages(cl, panelCont, classManager);
        
        if (!pimRes) System.exit(1);
        
        //Il metodo applicationClosing() gestisce l'uscita dall'applicazione
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		classManager.applicationClosing();
        	}
		});
        
        classManager.setPanelsM(pim);
        
        cl.show(panelCont, "Home");
        this.add(panelCont);
        
        //Non fa niente alla chiusura della finestra
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Ridimensiona in automatico la finestra al primo dimensionamento catturato
        pack();
        //Setto la dimensione di default della finestra dell'applicazione
        setSize(screenDefaultWidth, screenDefaultHeight);
        //Imposta la posizione della finestra al centro dello schermo
        setLocationRelativeTo(null);
        
        // Rende visibile a schermo il frame principale
        setVisible(true);
	}
	
	/**
	 * Setta le dimensioni della finestra con quelle dello schermo dell'utente
	 */
	public void defaultScreenSize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//Ottengo la dimensione dello schermo in pixel
		Dimension screenSize = toolkit.getScreenSize();
		//Larghezza dello schermo
		screenDefaultWidth = screenSize.width;
		//Altenza dello schermo
		screenDefaultHeight = screenSize.height;
	}
		
	/**
	 * Verifica ed eventualmente crea il file di log, per gli studenti e per le materie
	 */
	public void initFile() {
	    try {
	        File directory = new File(Constants.PATH);
	        
	        File materie = new File(Constants.MATERIE);
            File log = new File(Constants.LOG);
            File studenti = new File(Constants.STUDENTI);
            
            BufferedWriter bw = null;

	        if (!directory.exists()) {
	            if (directory.mkdirs()) { // Utilizza mkdirs() invece di mkdir()
	                
	                if (!materie.exists() && !materie.createNewFile()) {
	                    System.out.println("Impossibile creare il file materie.");
	                    System.exit(1);
	                }
	                	                
	                bw = new BufferedWriter(new FileWriter(materie));
	                
	                bw.write(11 + "\n");
	                bw.write(
	                		"Algebra,9" + "\n" +
	                		"Analisi 1,9" + "\n" +
	                		"Analisi 2,9" + "\n" +
	                		"Sistemi operativi,9" + "\n"+
	                		"Virologia,10" + "\n" +
	                		"Programmazione ad oggetti,9" + "\n" +
	                		"Fisica,6" + "\n" +
	                		"Statistica,6" + "\n" +
	                		"Programmazione 1,9" + "\n" +
	                		"Programmazione 2,9" + "\n" +
	                		"Inglese,6"
	                );

	                if (!log.exists() && !log.createNewFile()) {
	                    System.out.println("Impossibile creare il file log.");
	                    System.exit(1);
	                }

	                if (!studenti.exists() && !studenti.createNewFile()) {
	                    System.out.println("Impossibile creare il file studenti.");
	                    System.exit(1);
	                }
	                
	                bw.close();
	                
	                bw = new BufferedWriter(new FileWriter(studenti));
	                
	                bw.write(10 + "\n");
	                bw.write(
	                		"100001,Michael,Barbi" + "\n" +
	                		"100002,Marco,Merisi" + "\n" +
	                		"100003,Giulia,Raponi" + "\n" +
	                		"100004,Giulio,Andreotti" + "\n" +
	                		"100005,Sofia,Malagutti" + "\n" +
	                		"100006,Mattia,Borali" + "\n" +
	                		"100007,Matteo,Marchini" + "\n" +
	                		"100008,Laura,Coschi" + "\n" +
	                		"100009,Marco,Iori" + "\n" +
	                		"100010,Filippo,Bergamini"
	                );
	                
	                bw.close();
	                
	                // Tutti e 3 i file sono stati creati con successo
	                System.out.println("I file sono stati creati con successo.");
	                
	            } else {
	                System.out.println("Impossibile creare la directory principale.");
	                System.exit(1);
	            }
	        } else {
	        	if (!(materie.exists() && log.exists() && studenti.exists())) {
	        		if (!materie.exists() && !materie.createNewFile()) {
	                    System.out.println("Impossibile creare il file materie.");
	                    System.exit(1);
	                }
	        		
	        		bw = new BufferedWriter(new FileWriter(materie));
	                
	                bw.write(11 + "\n");
	                bw.write(
	                		"Algebra,9" + "\n" +
	                		"Analisi 1,9" + "\n" +
	                		"Analisi 2,9" + "\n" +
	                		"Sistemi operativi,9" + "\n"+
	                		"Virologia,10" + "\n" +
	                		"Programmazione ad oggetti,9" + "\n" +
	                		"Fisica,6" + "\n" +
	                		"Statistica,6" + "\n" +
	                		"Programmazione 1,9" + "\n" +
	                		"Programmazione 2,9" + "\n" +
	                		"Inglese,6"
	                );
	                
	                bw.close();

	                if (!log.exists() && !log.createNewFile()) {
	                    System.out.println("Impossibile creare il file log.");
	                    System.exit(1);
	                }

	                if (!studenti.exists() && !studenti.createNewFile()) {
	                    System.out.println("Impossibile creare il file studenti.");
	                    System.exit(1);
	                }
	                
	                bw = new BufferedWriter(new FileWriter(studenti));
	                
	                bw.write(10 + "\n");
	                bw.write(
	                		"100001,Michael,Barbi" + "\n" +
	                		"100002,Marco,Merisi" + "\n" +
	                		"100003,Giulia,Raponi" + "\n" +
	                		"100004,Giulio,Andreotti" + "\n" +
	                		"100005,Sofia,Malagutti" + "\n" +
	                		"100006,Mattia,Borali" + "\n" +
	                		"100007,Matteo,Marchini" + "\n" +
	                		"100008,Laura,Coschi" + "\n" +
	                		"100009,Marco,Iori" + "\n" +
	                		"100010,Filippo,Bergamini"
	                );
	                
	                bw.close();
	        	}
	        }
	    } catch (Exception e) {
	        System.out.println("Errore durante la creazione dei file: " + e.getMessage());
	        System.exit(1);
	    }
	}
}
