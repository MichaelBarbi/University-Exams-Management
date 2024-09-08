package javasource.UI.pages;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import javasource.Constants;
import javasource.UI.ClassManager;
import javasource.resourceobjects.Logger;
import javasource.resourceobjects.MyIstogramma;

/**
 * Rappresenta la finestra nella quale verranno mostrate le statistiche
 */

public class Statistiche extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Pannello sinistro
	 */
	private JPanel left;
	
	/**
	 * Pannello destro
	 */
	private JPanel right;
	
	/**
	 * Container principale
	 */
	private JPanel box;
	
	/**
	 * Box per la selezione del filtro
	 */
	private JPanel box__filterSelection;
	
	/**
	 * Titolo per la selezione del filtro
	 */
	private JLabel box__filterSelection__title;
	
	/**
	 * Le varie opzioni di filtri disponibili
	 */
	private JComboBox<String> box__filterSelection__combo;
	
	/**
	 * Panel per la tabella
	 */
	private JPanel box__table;
	
	/**
	 * Titolo della tabella
	 */
	private JLabel box__table__title;
	
	/**
	 * Tabella
	 */
	private JTable box__table__table;
	
	/**
	 * Container per i pulsanti
	 */
	private JPanel box__buttons;
	
	/**
	 * Pulsante per confermare il filtro di ricerca
	 */
	private JButton box__buttons__save;
	
	/**
	 * Pulsante per tornare indietro
	 */
	private JButton box__buttons__back;
	
	/**
	 * Istogramma
	 */
	private MyIstogramma isto;
	
	
	/**
	 * Costruttore 
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager manager delle classi
	 */
	public Statistiche(CardLayout cl, JPanel cont, ClassManager classManager) {
		
		this.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		this.setLayout(new GridLayout(1,2));
		
		left = new JPanel();
		left.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left.setLayout(new GridBagLayout());
		GridBagConstraints gbcLeft = new GridBagConstraints();
		gbcLeft.gridx = 0;
		gbcLeft.gridy = 0;
		gbcLeft.fill = GridBagConstraints.CENTER;
		
		box = new JPanel();
		box.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		
		box__filterSelection = new JPanel();
		box__filterSelection.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__filterSelection.setLayout(new BoxLayout(box__filterSelection, BoxLayout.Y_AXIS));
		
		box__filterSelection__title = new JLabel("Categoria");
		box__filterSelection__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__filterSelection__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__filterSelection__title.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		box__filterSelection__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		
		String[] options = {"Nessuna categoria", "Materia", "Studente"};
		
		box__filterSelection__combo = new JComboBox<String>(options);
		box__filterSelection__combo.setPreferredSize(new Dimension(50,50));
		box__filterSelection__combo.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		
		box__table = new JPanel();
		box__table.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__table.setLayout(new BoxLayout(box__table, BoxLayout.Y_AXIS));
		box__table.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));		
		
		box__table__title = new JLabel("Scegliere qui");
		box__table__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__table__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__table__title.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		box__table__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));		
		
		box__table__table = new JTable();
		box__table__table.setRowHeight(50);
		
		box__buttons = new JPanel();
		box__buttons.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__buttons.setLayout(new GridLayout(1, 2, 20, 0));
		box__buttons.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		
		box__buttons__save = new JButton("Conferma");
		box__buttons__save.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__save.setForeground(Constants.COLOR_TEXT);
		box__buttons__save.setPreferredSize(new Dimension(80, 50));
		box__buttons__save.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		
		box__buttons__back = new JButton("Indietro");
		box__buttons__back.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__back.setForeground(Constants.COLOR_TEXT);
		box__buttons__back.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__buttons__back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "Home");
			}
		});
		
		right = new JPanel();
		right.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		right.setLayout(new GridBagLayout());
		GridBagConstraints gbcRight = new GridBagConstraints();
		gbcRight.gridx = 0;
		gbcRight.gridy = 0;
		gbcRight.fill = GridBagConstraints.CENTER;
		
		JScrollPane materieScrollPane = new JScrollPane(box__table__table);
		
		String selectedItemGlobal = (String) box__filterSelection__combo.getSelectedItem();
		
		if (selectedItemGlobal.equals("Nessuna categoria")) {
			right.setVisible(false);
		} else {
			right.setVisible(true);
		}
		
		box__filterSelection__combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedItem = (String) box__filterSelection__combo.getSelectedItem();
					right.setVisible(false);
					
					switch(selectedItem) {
						case "Nessuna categoria":
							box__table__table.setVisible(false);
							break;
						case "Materia":
							
						    // Visualizzazione delle materie
						    String[] materieColumns = {"Titoli"};
						    Object[][] materieData = classManager.getTitoli();

						    DefaultTableModel materieModel = new DefaultTableModel(materieData, materieColumns) {
						        private static final long serialVersionUID = 1L;

								// Override del metodo isCellEditable per rendere tutte le celle non modificabili
						        @Override
						        public boolean isCellEditable(int row, int column) {
						            return false;
						        }
						    };		

						    // Aggiorna il modello dei dati della tabella esistente
						    box__table__table.setModel(materieModel);
						    box__table__table.setVisible(true);
						    
							break;
						case "Studente":
							
							// Visualizzazione degli studenti
						    String[] studentiColumns = {"Matricole"};
						    Object[][] studentiData = classManager.getMatricole();

						    DefaultTableModel studentiModel = new DefaultTableModel(studentiData, studentiColumns) {
						        private static final long serialVersionUID = 1L;

								// Override del metodo isCellEditable per rendere tutte le celle non modificabili
						        @Override
						        public boolean isCellEditable(int row, int column) {
						            return false;
						        }
						    };		

						    // Aggiorna il modello dei dati della tabella esistente
						    box__table__table.setModel(studentiModel);
						    box__table__table.setVisible(true);
							
							break;
						default:
							Logger.writeOnFile("Errore visualizzazione della tabella", LocalDateTime.now(), "Statistiche", "Visualizzazione delle tabelle", null);
							System.exit(1);
					}
					
				}
			}
		});
		
		this.isto = new MyIstogramma(null);
		
		box__buttons__save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer index = box__table__table.getSelectedRow();
				
				if (index == null || index < 0 || index >= box__table__table.getRowCount()) {
					JOptionPane.showMessageDialog(null, "Visualizzazione delle statistiche fallita, selezionare un valore",
							"Statistiche", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String type = (String) box__filterSelection__combo.getSelectedItem();
				
				DefaultCategoryDataset dataset = null;
				
				switch(type) {
					case "Materia":
						
						/*
						 * Devo prelevare tutti gli esami legati a tale materia e selezionarne i voti
						 */
						
						dataset = classManager.getStatisticheVotiFromMateriaIndex(index);
						
						isto.updateDataset(dataset);
						right.setVisible(true);
						
						break;
					case "Studente":
						
						/*
						 * Devo prelevare tutti gli esami legati a tale studente e selezionarne i voti
						 */
						
						dataset = classManager.getStatisticheVotiFromStudenteIndex(index);
						
						isto.updateDataset(dataset);
						right.setVisible(true);
						break;
					default:
						return;
				}
			}
		});
		
		
		/************************************************************************************/
		
		this.add(left);
		left.add(box, gbcLeft);
		  box.add(box__filterSelection);
		    box__filterSelection.add(box__filterSelection__title);
		    box__filterSelection.add(box__filterSelection__combo);
		  box.add(box__table);
		    box__table.add(box__table__title);
		    box__table.add(materieScrollPane);
		  box.add(box__buttons);
		    box__buttons.add(box__buttons__save);
		    box__buttons.add(box__buttons__back);
		
		this.add(right);
		right.add(isto, gbcRight);
		
		
		
		/*******************************************************************************/
		
		cont.add(this, "Statistiche");
	}

}
