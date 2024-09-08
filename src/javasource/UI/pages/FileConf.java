package javasource.UI.pages;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import javasource.Constants;
import javasource.UI.ClassManager;
import javasource.resourceobjects.Esame;
import javasource.resourceobjects.Logger;
import javasource.resourceobjects.MyTable;
import javasource.resourceobjects.SavingThread;
import javasource.resourceobjects.TablePrinter;

/**
 * Pagina che gestisce le principali azioni con il file
 */
public class FileConf extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Box principale
	 */
	private JPanel box;
	
	/**
	 * Pannello per il titolo
	 */
	private JPanel box__titlePanel;
	
	/**
	 * Titlo della schermata
	 */
	private JLabel box__titlePanel__title;
	
	/**
	 * Box per per i pulsanti principali
	 */
	private JPanel box__buttons;
	
	/**
	 * Pulsante per salvare il nuovo file
	 */
	private JButton box__buttons__save;
	
	/**
	 * Carica un file
	 */
	private JButton box__buttons__load;
	
	/**
	 * Stampa con la stampante un file di esami
	 */
	private JButton box__buttons__print;
	
	/**
	 * Crea un nuovo file vuoto non salvato
	 */
	private JButton box__buttons__new;
	
	/**
	 * Pannello per l'auto salvattaggio
	 */
	private JPanel box__autoSavePanel;
	
	/**
	 * Per abilitare l'auto salvataggio
	 */
	private JCheckBox box__autoSavePanel__auto;
	
	/**
	 * Pannello pulsante salva ed esci
	 */
	private JPanel box__savePanel;
	
	/**
	 * Salva ed esci
	 */
	private JButton box__savePanel__save;
	
	/**
	 * Thread per il salvataggio automatico
	 */
	private SavingThread savingThread;
	
	/**
	 * Costruttore
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager manager delle classi
	 */
	public FileConf(CardLayout cl, JPanel cont, ClassManager classManager) {
		
		this.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbcRoot = new GridBagConstraints();
		gbcRoot.gridx = 0;
		gbcRoot.gridy = 0;
		gbcRoot.fill = GridBagConstraints.CENTER;
		
		box = new JPanel();
		box.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		
		box__titlePanel = new JPanel();
		box__titlePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		
		box__titlePanel__title = new JLabel("File degli esami");
		box__titlePanel__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__titlePanel__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__titlePanel__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		
		box__buttons = new JPanel();
		box__buttons.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__buttons.setLayout(new GridLayout(4, 1, 0, 20));
		box__buttons.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		
		box__buttons__save = new JButton("Salva con nome");
		box__buttons__save.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__save.setForeground(Constants.COLOR_TEXT);
		box__buttons__save.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__buttons__save.setPreferredSize(new Dimension(this.getWidth(), 50));
		box__buttons__save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Salvo il file
				boolean esito = classManager.getEsamiFileManager().saveFileWithName(classManager);
				
				if (esito) {
					JOptionPane.showMessageDialog(null, "Il file è stato salvato", "Salvataggio File", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Il file non è stato salvato", "Salvataggio File", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		box__buttons__load = new JButton("Carica file");
		box__buttons__load.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__load.setForeground(Constants.COLOR_TEXT);
		box__buttons__load.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__buttons__load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Caricamento di un file e dei relativi esami
				if (classManager.getEsami() != null && classManager.getEsami().size() != 0 && classManager.getEsamiFile() == null) {
					int scelta = JOptionPane.showConfirmDialog(null, "Vuoi salvare la lista su un nuovo file prima di procedere?", "Modifiche non salvate", JOptionPane.YES_NO_CANCEL_OPTION);
			        
					if (scelta == JOptionPane.YES_OPTION) {
						Boolean esito = classManager.getEsamiFileManager().saveFileWithName(classManager);
						if (!esito) {
							JOptionPane.showMessageDialog(null, "Creazione del nuovo file fallita", "Nuovo File", JOptionPane.ERROR_MESSAGE);
						}
					} 
					
				} else if (classManager.getEsami() != null && classManager.getEsami().size() != 0 && classManager.getEsamiFile() != null && classManager.areExamsListsDifferent()) {
					int scelta = JOptionPane.showConfirmDialog(null, "Sono state rilevate modifiche non salvate sul file attuale, si vuole salvarle ", "Modifiche non salvate", JOptionPane.YES_NO_CANCEL_OPTION);
					if (scelta == JOptionPane.YES_OPTION) {
						classManager.getEsamiFileManager().saveFile(classManager);
					} 
				
					
				}
					
				//Import di un file
				Boolean esito = classManager.getEsamiFileManager().importEsamiFile(classManager);
				
				if (esito) {
					JOptionPane.showMessageDialog(null, "Il file è stato caricato correttamente", "Caricamento file", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "File non caricato", "Nuovo File", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		box__buttons__print = new JButton("Stampa");
		box__buttons__print.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__print.setForeground(Constants.COLOR_TEXT);
		box__buttons__print.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__buttons__print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Recuperare la JTable
				Object[][] data = classManager.getArrayDataFromEsami("Filter");
				
				if (data == null) {
					System.exit(1);
				}
				
				if (data.length == 0) {
					JOptionPane.showMessageDialog(null, "File vuoto", "Stampa degli esami", JOptionPane.ERROR_MESSAGE);
					return;
				}
				

		        // Creazione delle colonne della tabella
		        String[] columns = {"Matricola", "Studente", "Materia", "Voto", "Lode", "CFU", "Composto"};
		        
		        MyTable myTable = new MyTable(columns, data);
		        JTable tb = myTable.getTable();
		        
		        /*
		        try {
		        	Boolean esito = tb.print();
		        } catch (Exception e) {
		        	Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "FileConf", "Constructor", null);
		        	return;
		        }*/
		        
		        /*
		        int rowCount = tb.getRowCount();
		        int columnCount = tb.getColumnCount();

		        List<Object[]> rows = new ArrayList<>();

		        for (int i = 0; i < rowCount; i++) {
		            Object[] row = new Object[columnCount];
		            for (int j = 0; j < columnCount; j++) {
		                row[j] = tb.getValueAt(i, j);
		            }
		            rows.add(row);
		        }*/

		        
		        TablePrinter tp = new TablePrinter(tb);
		        
		        tp.printTable();
		        
			}
		});
		
		box__buttons__new = new JButton("Nuovo file");
		box__buttons__new.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__new.setForeground(Constants.COLOR_TEXT);
		box__buttons__new.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__buttons__new.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * Creazione di un nuovo file.
				 * 
				 * 1)
				 * 1.1)
				 * esami != null && esamiFile = null
				 * Chiedo all'utente se vuole salvarle su un nuovo file
				 * 1.2)
				 * esami != null && esamiFile != null
				 * Chiedo all'utente se vuole salvare le modifiche sul file attuale.
				 * 
				 * 2)
				 * Setto esamiFile=null e cancello il contenuto di esami
				 */
				
				if (classManager.getEsami() != null && classManager.getEsami().size() != 0 && classManager.getEsamiFile() == null) {
					System.out.println("11111111");
					int scelta = JOptionPane.showConfirmDialog(null, "Vuoi salvare la lista su un nuovo file prima di procedere?", "Modifiche non salvate", JOptionPane.YES_NO_CANCEL_OPTION);
			        
					if (scelta == JOptionPane.YES_OPTION) {
						Boolean esito = classManager.getEsamiFileManager().saveFileWithName(classManager);
						System.out.println("222222");
						if (!esito) {
							JOptionPane.showMessageDialog(null, "Creazione del nuovo file fallita", "Nuovo File", JOptionPane.ERROR_MESSAGE);
						}
					} 
					
				} else if (classManager.getEsami() != null && classManager.getEsami().size() != 0 && classManager.getEsamiFile() != null && classManager.areExamsListsDifferent()) {
					int scelta = JOptionPane.showConfirmDialog(null, "Sono state rilevate modifiche non salvate sul file attuale, si vuole salvarle ", "Modifiche non salvate", JOptionPane.YES_NO_CANCEL_OPTION);
					if (scelta == JOptionPane.YES_OPTION) {
						classManager.getEsamiFileManager().saveFile(classManager);

					} 
				
					
				}
					
				JOptionPane.showMessageDialog(null, "E' stato creato un nuovo file correttamente", "Nuovo File", JOptionPane.INFORMATION_MESSAGE);
				classManager.setEsami(new ArrayList<Esame>());
				classManager.setEsamiFile(null);
				classManager.setEsamiFilter(null);
	
			}
		});
		
		box__autoSavePanel = new JPanel();
		box__autoSavePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__autoSavePanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		this.savingThread = null;
		
		box__autoSavePanel__auto = new JCheckBox("Salvataggio automatico");
		box__autoSavePanel__auto.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__autoSavePanel__auto.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__autoSavePanel__auto.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__autoSavePanel__auto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (box__autoSavePanel__auto.isSelected()) {
					savingThread = new SavingThread(classManager);
					savingThread.start();
				} else {
					try {
						savingThread.turnOff();
					} catch (Exception e) {
						Logger.writeOnFile(e.getMessage(), LocalDateTime.now(), "FileConf", "autosaveCheckbox", null);
						System.exit(1);
					}
					savingThread = null;
				}
			}
		});
		
		box__savePanel = new JPanel();
		box__savePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__savePanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		box__savePanel__save = new JButton("Salva ed esci");
		box__savePanel__save.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__savePanel__save.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__savePanel__save.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__savePanel__save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "Home");
			}
		});
		
		
		/*****************************************************************************************/
		
		this.add(box, gbcRoot);
		  box.add(box__titlePanel);
		    box__titlePanel.add(box__titlePanel__title);
		  box.add(box__buttons);
		    box__buttons.add(box__buttons__save);
		    box__buttons.add(box__buttons__load);
		    box__buttons.add(box__buttons__print);
		    box__buttons.add(box__buttons__new);
		  box.add(box__autoSavePanel);
		    box__autoSavePanel.add(box__autoSavePanel__auto);
		  box.add(box__savePanel);
		    box__savePanel.add(box__savePanel__save);
		
		cont.add(this, "File");
	}

}
