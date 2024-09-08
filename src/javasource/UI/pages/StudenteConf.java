package javasource.UI.pages;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import javasource.Constants;
import javasource.UI.ClassManager;
import javasource.resourceobjects.Studente;

/**
 * Schermata di configurazione degli studenti
 */
public class StudenteConf extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Valore dell'indice da modificare
	 */
	private Integer indexToModify = null;
	
	/**
	 * Pannello sinistro
	 */
	private JPanel left;
	
	/**
	 * Main box per il pannello sinistro
	 */
	private JPanel left__box;
	
	/**
	 * Contenitore per l'inserimento delle proprietà 
	 */
	private JPanel left__box__propsPanel;
	
	/**
	 * Contenitore schema per la matricola
	 */
	private JPanel left__box__propsPanel__matricolaPanel;
	
	/**
	 * Label per la matricola
	 */
	private JLabel left__box__propsPanel__matricolaPanel__label;
	
	/**
	 * Field per l'inserimento della matricola
	 */
	private JTextField left__box__propsPanel__matricolaPanel__field;
	
	/**
	 * Contenitore per il nome
	 */
	private JPanel left__box__propsPanel__nomePanel;
	
	/**
	 * Nome label
	 */
	private JLabel left__box__propsPanel__nomePanel__label;
	
	/**
	 * Field per l'inserimento del nome
	 */
	private JTextField left__box__propsPanel__nomePanel__field;
	
	/**
	 * Pannello per il cognome
	 */
	private JPanel left__box__propsPanel__cognomePanel;
	
	/**
	 * Congome label
	 */
	private JLabel left__box__propsPanel__cognomePanel__label;
	
	/**
	 * Field per l'inserimento del cognome
	 */
	private JTextField left__box__propsPanel__cognomePanel__field;
	
	/**
	 * Contenitore dei pulsanti
	 */
	private JPanel left__box__actionsPanel;
	
	/**
	 * Pulsante aggiungi
	 */
	private JButton left__box__actionsPanel__add;
	
	/**
	 * Pulsante elimina
	 */
	private JButton left__box__actionsPanel__delete;
	
	/**
	 * Pulsante modifica
	 */
	private JButton left__box__actionsPanel__modifica;
	
	/**
	 * Contenitore per il pulsante per tornare indietro
	 */
	private JPanel left__box__backPanel;
	
	/**
	 * Pulsante torna indietro
	 */
	private JButton left__box__backPanel__back;	
	
	/**
	 * Pannello destro
	 */
	private JPanel right;
	
	/**
	 * Tabella con gli studenti
	 */
	private JTable right__table;
	
	/**
	 * Costruttore 
	 * 
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager manager delle classi
	 */
	public StudenteConf(CardLayout cl, JPanel cont, ClassManager classManager) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;
		
		this.setLayout(new GridLayout(1,2));
		
		left = new JPanel();
		left.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left.setLayout(new GridBagLayout());
		
		left__box = new JPanel();
		left__box.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box.setLayout(new BoxLayout(left__box, BoxLayout.Y_AXIS));
		
		left__box__propsPanel = new JPanel();
		left__box__propsPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel.setLayout(new BoxLayout(left__box__propsPanel, BoxLayout.Y_AXIS));
		
		left__box__propsPanel__matricolaPanel = new JPanel();
		left__box__propsPanel__matricolaPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__matricolaPanel.setLayout(new BoxLayout(left__box__propsPanel__matricolaPanel, BoxLayout.Y_AXIS));
		left__box__propsPanel__matricolaPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		left__box__propsPanel__matricolaPanel__label = new JLabel("Matricola");
		left__box__propsPanel__matricolaPanel__label.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__matricolaPanel__label.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		left__box__propsPanel__matricolaPanel__label.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		left__box__propsPanel__matricolaPanel__label.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		left__box__propsPanel__matricolaPanel__field = new JTextField();
		left__box__propsPanel__matricolaPanel__field.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__propsPanel__matricolaPanel__field.setForeground(Constants.COLOR_H1_DARK_RGB);
		left__box__propsPanel__matricolaPanel__field.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__propsPanel__matricolaPanel__field.setPreferredSize(new Dimension(100, 30));
		
		left__box__propsPanel__nomePanel = new JPanel();
		left__box__propsPanel__nomePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__nomePanel.setLayout(new BoxLayout(left__box__propsPanel__nomePanel, BoxLayout.Y_AXIS));
		left__box__propsPanel__nomePanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		left__box__propsPanel__nomePanel__label = new JLabel("Nome");
		left__box__propsPanel__nomePanel__label.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__nomePanel__label.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		left__box__propsPanel__nomePanel__label.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		left__box__propsPanel__nomePanel__label.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		left__box__propsPanel__nomePanel__field = new JTextField();
		left__box__propsPanel__nomePanel__field.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__propsPanel__nomePanel__field.setForeground(Constants.COLOR_H1_DARK_RGB);
		left__box__propsPanel__nomePanel__field.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__propsPanel__nomePanel__field.setPreferredSize(new Dimension(100, 30));
		
		left__box__propsPanel__cognomePanel = new JPanel();
		left__box__propsPanel__cognomePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__cognomePanel.setLayout(new BoxLayout(left__box__propsPanel__cognomePanel, BoxLayout.Y_AXIS));
		left__box__propsPanel__cognomePanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		left__box__propsPanel__cognomePanel__label = new JLabel("Cognome");
		left__box__propsPanel__cognomePanel__label.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__cognomePanel__label.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		left__box__propsPanel__cognomePanel__label.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		left__box__propsPanel__cognomePanel__label.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		left__box__propsPanel__cognomePanel__field = new JTextField();
		left__box__propsPanel__cognomePanel__field.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__propsPanel__cognomePanel__field.setForeground(Constants.COLOR_H1_DARK_RGB);
		left__box__propsPanel__cognomePanel__field.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__propsPanel__cognomePanel__field.setPreferredSize(new Dimension(100, 30));
		
		left__box__actionsPanel = new JPanel();
		left__box__actionsPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__actionsPanel.setLayout(new GridLayout(1, 3, 20, 0));
		left__box__actionsPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		
		left__box__actionsPanel__add = new JButton("Aggiungi");
		left__box__actionsPanel__add.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__actionsPanel__add.setForeground(Constants.COLOR_TEXT);
		left__box__actionsPanel__add.setPreferredSize(new Dimension(left__box__actionsPanel__add.getWidth(), 50));
		left__box__actionsPanel__add.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__actionsPanel__add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String matricola = left__box__propsPanel__matricolaPanel__field.getText();
				String nome = left__box__propsPanel__nomePanel__field.getText();
				String cognome = left__box__propsPanel__cognomePanel__field.getText();
				
				if (matricola == null || matricola.length() < 6 || (classManager.matricolaIsAlreadySet(matricola) && indexToModify == null) ) {
					JOptionPane.showMessageDialog(null, "Inserire la matricola", "Form invalido",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (nome == null || nome.length() == 0) {
					JOptionPane.showMessageDialog(null, "Inserire il nome", "Form invalido",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (cognome == null || cognome.length() == 0) {
					JOptionPane.showMessageDialog(null, "Inserire il cognome", "Form invalido",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				left__box__propsPanel__matricolaPanel__field.setEditable(true);
				
				if (indexToModify != null ) {
					
					classManager.getStudenti().get((int) indexToModify).setMatricola(matricola);
					classManager.getStudenti().get((int) indexToModify).setNome(ClassManager.getDataWellFormatted(nome));
					classManager.getStudenti().get((int) indexToModify).setCognome(ClassManager.getDataWellFormatted(cognome));
					Studente.writeStudentiOnFile(classManager.getStudenti());
					
					indexToModify = null;
					
					cont.remove(classManager.getPanelsM().getStudenteConf());
					
					StudenteConf sc = new StudenteConf(cl, cont, classManager);
					cont.add(sc, "StudenteConf");
					
					cl.show(cont, "StudenteConf");
					
				} else {
				
					Studente newStudente = new Studente(matricola, ClassManager.getDataWellFormatted(nome), ClassManager.getDataWellFormatted(cognome));
					
					classManager.getStudenti().add(newStudente);
					Boolean esito = Studente.writeStudentiOnFile(classManager.getStudenti());
					
					if (!esito) {
						JOptionPane.showMessageDialog(null, "L'aggiunta non è stata effettuata", "Operazione fallita",JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						cont.remove(classManager.getPanelsM().getStudenteConf());
						
						StudenteConf sc = new StudenteConf(cl, cont, classManager);
						cont.add(sc, "StudenteConf");
						
						cl.show(cont, "StudenteConf");
					}
				}	
			}
		});
		
		left__box__actionsPanel__delete = new JButton("Elimina");
		left__box__actionsPanel__delete.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__actionsPanel__delete.setForeground(Constants.COLOR_TEXT);
		left__box__actionsPanel__delete.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		
		
		left__box__actionsPanel__modifica = new JButton("Modifica");
		left__box__actionsPanel__modifica.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__actionsPanel__modifica.setForeground(Constants.COLOR_TEXT);
		left__box__actionsPanel__modifica.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		
		left__box__backPanel = new JPanel();
		left__box__backPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__backPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		left__box__backPanel__back = new JButton("Indietro");
		left__box__backPanel__back.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__backPanel__back.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__backPanel__back.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__backPanel__back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "Configurazioni");
			}
		});
		
		right = new JPanel();
		right.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		right.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		GridBagConstraints gbcTable = new GridBagConstraints();
		gbcTable.gridx = 0;
		gbcTable.gridy = 0;
		gbcTable.weighty = 1.0;
		gbcTable.weightx = 1.0;
		gbcTable.fill = GridBagConstraints.BOTH;
		right.setLayout(new GridBagLayout());
		
		// Creazione dei dati per la tabella
        Object[][] data = ClassManager.studentiListToMatrix(Studente.orderListByMatricola(Studente.readStudentiFromFile()));

        // Creazione delle colonne della tabella
        String[] columns = {"Matricola", "Nome", "Cognome"};
        
        DefaultTableModel model = new DefaultTableModel(data, columns) {
        	private static final long serialVersionUID = 1L;

			// Override del metodo isCellEditable per rendere tutte le celle non modificabili
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
		right__table = new JTable(model);
		right__table.setRowHeight(45);
		right__table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		right__table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Creazione del modello dei dati per la tabella
		JScrollPane scrollPane = new JScrollPane(right__table);
		
		left__box__actionsPanel__delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer index = right__table.getSelectedRow();
				
				if (index < 0 || index >= classManager.getStudenti().size()) {
					JOptionPane.showMessageDialog(null, "Selezionare uno studente dalla tabella", "Eliminazione annullata",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//Prima di rimuoverlo devo verificare che non vi siano esami legati allo studente
				if (classManager.hasMatricolaBeenUsed(classManager.getStudenti().get((int)index).getMatricola())) {
					JOptionPane.showMessageDialog(null, "Matricola già utilizzata in un esame", "Eliminazione annullata",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				classManager.getStudenti().remove((int) index);
				
				Studente.writeStudentiOnFile(classManager.getStudenti());
				
				cont.remove(classManager.getPanelsM().getStudenteConf());
				
				StudenteConf sc = new StudenteConf(cl, cont, classManager);
				cont.add(sc, "StudenteConf");
				
				cl.show(cont, "StudenteConf");
			}
		});
		
		left__box__actionsPanel__modifica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer index = right__table.getSelectedRow();
				
				if (index < 0 || index >= classManager.getStudenti().size()) {
					JOptionPane.showMessageDialog(null, "Selezionare uno studente dalla tabella", "Eliminazione annullata",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				indexToModify = index;
				
				Studente selected = classManager.getStudenti().get((int)index);
				
				left__box__propsPanel__matricolaPanel__field.setText(selected.getMatricola());
				left__box__propsPanel__matricolaPanel__field.setEditable(false);
				left__box__propsPanel__nomePanel__field.setText(selected.getNome());
				left__box__propsPanel__cognomePanel__field.setText(selected.getCognome());
				
			}
		});
		
		/**************************************************************************************/
		
		this.add(left);	
		  left.add(left__box, gbc);
		    left__box.add(left__box__propsPanel);
		      left__box__propsPanel.add(left__box__propsPanel__matricolaPanel);
		        left__box__propsPanel__matricolaPanel.add(left__box__propsPanel__matricolaPanel__label);
		        left__box__propsPanel__matricolaPanel.add(left__box__propsPanel__matricolaPanel__field);
		      left__box__propsPanel.add(left__box__propsPanel__nomePanel);
		        left__box__propsPanel__nomePanel.add(left__box__propsPanel__nomePanel__label);
		        left__box__propsPanel__nomePanel.add(left__box__propsPanel__nomePanel__field);
		      left__box__propsPanel.add(left__box__propsPanel__cognomePanel);
		        left__box__propsPanel__cognomePanel.add(left__box__propsPanel__cognomePanel__label);
		        left__box__propsPanel__cognomePanel.add(left__box__propsPanel__cognomePanel__field);
		    left__box.add(left__box__actionsPanel);
		      left__box__actionsPanel.add(left__box__actionsPanel__add);
		      left__box__actionsPanel.add(left__box__actionsPanel__delete);
		      left__box__actionsPanel.add(left__box__actionsPanel__modifica);
		    left__box.add(left__box__backPanel);
		      left__box__backPanel.add(left__box__backPanel__back);
		
		this.add(right);
		  right.add(scrollPane, gbcTable);
		
		
		cont.add(this, "StudenteConf");
	}

}
