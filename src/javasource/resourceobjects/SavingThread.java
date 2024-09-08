package javasource.resourceobjects;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javasource.UI.ClassManager;

/**
 * Salvataggio periodico del file
 */
public class SavingThread extends Thread {
	
	/**
	 * Manager delle classi
	 */
	private ClassManager classManager;
	
	/**
	 * Stabilisce se il thread deve essere in esecuzione o meno;
	 */
	private Boolean running = true;
	
	/**
	 * Costruttore
	 * @param classManager manager delle classi
	 */
	public SavingThread(ClassManager classManager) {
		this.classManager = classManager;
	}
	
	/**
	 * init del thread
	 */
	public void run() {
		while (running) {
			try {
				
				if (Thread.currentThread().isInterrupted()) {
                    // Termino l'esecuzione in modo pulito
                    running = false;
                    return;
                }
				
				if (classManager.getEsamiFile() != null ) {
					//Verifico che la lista attuale sia diversa rispetto a quella presente nel file
					if (classManager.areExamsListsDifferent()) {
						classManager.getEsamiFile().clear();
						classManager.getEsamiFile().addAll(classManager.getEsami());
						classManager.getEsamiFileManager().writeExamsOnFile(classManager.getEsami());
						
						//Creo una finestra di dialogo non modale con un messaggio
				        @SuppressWarnings("unused")
						JOptionPane optionPane = new JOptionPane("Salvataggio automatico");

				        //Creo una finestra di dialogo senza pulsanti
				        final JOptionPane message = new JOptionPane("Modifiche salvate", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

				        // Creo un JDialog dalla finestra di dialogo
				        final JDialog dialog = new javax.swing.JDialog();
				        dialog.setTitle("Popup");
				        dialog.setModal(false);
				        dialog.setContentPane(message);
				        dialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
				        dialog.pack();

				        //Imposto un timer per chiudere la finestra dopo 3 secondi 
				        Timer timer = new Timer(3000, e -> {
				        	// Chiudo la finestra di dialogo dopo il tempo specificato
				            dialog.dispose(); 
				        });
				        timer.setRepeats(false); // Imposto il timer per non ripetersi

				        // Avvio il timer e mostro la finestra di dialogo
				        timer.start();
				        dialog.setVisible(true);
					}
				}
				Thread.sleep(60000);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	/**
	 * Metodo per interrompere il thread
	 */
	public void turnOff() {
		running = false;
		interrupt();
	}

}
