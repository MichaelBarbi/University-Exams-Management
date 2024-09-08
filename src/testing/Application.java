package testing;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javasource.Constants;
import javasource.UI.FrameManager;

/**
 * Classe per l'esecuzione dell'applicazione
 */

public class Application extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * Funzione main 
     * @param args Parametri esterni
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	
            // Inizializzazione dell'applicazione
            @SuppressWarnings("unused")
			FrameManager app = new FrameManager(Constants.APPLICATION_NAME);
        });
    }

}
