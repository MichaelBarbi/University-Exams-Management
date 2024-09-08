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
import javasource.resourceobjects.Materia;

/**
 * Schermata di configurazione delle materie
 */
public class MateriaConf extends JPanel{
	
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
	 * Contenitore schema per il titolo
	 */
	private JPanel left__box__propsPanel__titoloPanel;
	
	/**
	 * Label per il titolo
	 */
	private JLabel left__box__propsPanel__titoloPanel__label;
	
	/**
	 * Field per l'inserimento del titolo
	 */
	private JTextField left__box__propsPanel__titoloPanel__field;
	
	/**
	 * Contenitore per i CFU
	 */
	private JPanel left__box__propsPanel__cfuPanel;
	
	/**
	 * CFU label
	 */
	private JLabel left__box__propsPanel__cfuPanel__label;
	
	/**
	 * Field per l'inserimento dei CFU
	 */
	private JTextField left__box__propsPanel__cfuPanel__field;
	
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
	 * tabella destra
	 */
	private JTable right__table;
	
	/**
	 * Costruttore 
	 * 
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager manager delle classi
	 */
	public MateriaConf(CardLayout cl, JPanel cont, ClassManager classManager) {
		
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
		
		left__box__propsPanel__titoloPanel = new JPanel();
		left__box__propsPanel__titoloPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__titoloPanel.setLayout(new BoxLayout(left__box__propsPanel__titoloPanel, BoxLayout.Y_AXIS));
		left__box__propsPanel__titoloPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		left__box__propsPanel__titoloPanel__label = new JLabel("Titolo");
		left__box__propsPanel__titoloPanel__label.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__titoloPanel__label.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		left__box__propsPanel__titoloPanel__label.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		left__box__propsPanel__titoloPanel__label.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		left__box__propsPanel__titoloPanel__field = new JTextField();
		left__box__propsPanel__titoloPanel__field.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__propsPanel__titoloPanel__field.setForeground(Constants.COLOR_H1_DARK_RGB);
		left__box__propsPanel__titoloPanel__field.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__propsPanel__titoloPanel__field.setPreferredSize(new Dimension(100, 30));
		
		left__box__propsPanel__cfuPanel = new JPanel();
		left__box__propsPanel__cfuPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__cfuPanel.setLayout(new BoxLayout(left__box__propsPanel__cfuPanel, BoxLayout.Y_AXIS));
		left__box__propsPanel__cfuPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		left__box__propsPanel__cfuPanel__label = new JLabel("CFU");
		left__box__propsPanel__cfuPanel__label.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__propsPanel__cfuPanel__label.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		left__box__propsPanel__cfuPanel__label.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		left__box__propsPanel__cfuPanel__label.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		left__box__propsPanel__cfuPanel__field = new JTextField();
		left__box__propsPanel__cfuPanel__field.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__propsPanel__cfuPanel__field.setForeground(Constants.COLOR_H1_DARK_RGB);
		left__box__propsPanel__cfuPanel__field.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__propsPanel__cfuPanel__field.setPreferredSize(new Dimension(100, 30));
		
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
				String titolo = left__box__propsPanel__titoloPanel__field.getText();
				String cfu = left__box__propsPanel__cfuPanel__field.getText();
				
				Materia m = null;
				if (indexToModify != null) {
					m = classManager.getMaterie().get(indexToModify);
				}
				
				if (
						titolo == null || 
						titolo.length() == 0 || 
						(classManager.titoloIsAlreadySet(ClassManager.getDataWellFormatted(titolo)) && indexToModify == null) || 
						(classManager.titoloIsAlreadySet(ClassManager.getDataWellFormatted(titolo)) && indexToModify != null && m != null && !(m.getTitolo().equals(ClassManager.getDataWellFormatted(titolo)))))
				{
					JOptionPane.showMessageDialog(null, "Inserire il titolo", "Form invalido",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (cfu == null || cfu.length() == 0 || Integer.parseInt(cfu) < 1 || Integer.parseInt(cfu) > 30) {
					JOptionPane.showMessageDialog(null, "Inserire i cfu", "Form invalido",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				left__box__propsPanel__titoloPanel__field.setEditable(true);
				
				if (indexToModify != null ) {
					//Modifica
					
					Materia.orderListByTitolo(classManager.getMaterie()).get((int)indexToModify).setTitolo(ClassManager.getDataWellFormatted(titolo));
					Materia.orderListByTitolo(classManager.getMaterie()).get((int)indexToModify).setCfu(Integer.parseInt(cfu));
					Materia.writeMaterieOnFile(classManager.getMaterie());
					
					indexToModify = null;
					
					cont.remove(classManager.getPanelsM().getMaterieConf());
					
					MateriaConf mc = new MateriaConf(cl, cont, classManager);
					cont.add(mc, "MaterieConf");
					
					cl.show(cont, "MaterieConf");
					
				} else {
				
					Materia newMateria = new Materia(ClassManager.getDataWellFormatted(titolo), Integer.parseInt(cfu));
					
					classManager.getMaterie().add(newMateria);
					Boolean esito = Materia.writeMaterieOnFile(classManager.getMaterie());
					
					if (!esito) {
						JOptionPane.showMessageDialog(null, "L'aggiunta non è stata effettuata", "Operazione fallita",JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						cont.remove(classManager.getPanelsM().getMaterieConf());
						
						MateriaConf mc = new MateriaConf(cl, cont, classManager);
						cont.add(mc, "MaterieConf");
						
						cl.show(cont, "MaterieConf");
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
        Object[][] data = ClassManager.materieListToMatrix(Materia.orderListByTitolo(Materia.readMaterieFromFile()));

        // Creazione delle colonne della tabella
        String[] columns = {"Titolo", "CFU"};
        
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
				
				if (index < 0 || index >= classManager.getMaterie().size()) {
					JOptionPane.showMessageDialog(null, "Selezionare una materia dalla tabella", "Eliminazione annullata",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//Prima di rimuoverlo devo verificare che non vi siano esami legati alla materia
				if (classManager.hasTitoloBeenUsed(Materia.orderListByTitolo(classManager.getMaterie()).get((int)index).getTitolo())) {
					JOptionPane.showMessageDialog(null, "Titolo già utilizzato in un esame", "Eliminazione annullata",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				classManager.getMaterie().remove((int) index);
				
				Materia.writeMaterieOnFile(classManager.getMaterie());
				
				cont.remove(classManager.getPanelsM().getMaterieConf());
				
				MateriaConf mc = new MateriaConf(cl, cont, classManager);
				cont.add(mc, "MaterieConf");
				
				cl.show(cont, "MaterieConf");
			}
		});
		
		left__box__actionsPanel__modifica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer index = right__table.getSelectedRow();
				
				if (index < 0 || index >= classManager.getMaterie().size()) {
					JOptionPane.showMessageDialog(null, "Selezionare una materia dalla tabella", "Modifica annullata",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				indexToModify = index;
				
				Materia selected = Materia.orderListByTitolo(classManager.getMaterie()).get((int)index);
				
				left__box__propsPanel__titoloPanel__field.setText(selected.getTitolo());
				if (classManager.hasTitoloBeenUsed(selected.getTitolo())) {
					left__box__propsPanel__titoloPanel__field.setEditable(false);
				}
				left__box__propsPanel__cfuPanel__field.setText(selected.getCfu().toString());
				
			}
		});
		
		/**************************************************************************************/
		
		this.add(left);	
		  left.add(left__box, gbc);
		    left__box.add(left__box__propsPanel);
		      left__box__propsPanel.add(left__box__propsPanel__titoloPanel);
		        left__box__propsPanel__titoloPanel.add(left__box__propsPanel__titoloPanel__label);
		        left__box__propsPanel__titoloPanel.add(left__box__propsPanel__titoloPanel__field);
		      left__box__propsPanel.add(left__box__propsPanel__cfuPanel);
		        left__box__propsPanel__cfuPanel.add(left__box__propsPanel__cfuPanel__label);
		        left__box__propsPanel__cfuPanel.add(left__box__propsPanel__cfuPanel__field);
		    left__box.add(left__box__actionsPanel);
		      left__box__actionsPanel.add(left__box__actionsPanel__add);
		      left__box__actionsPanel.add(left__box__actionsPanel__delete);
		      left__box__actionsPanel.add(left__box__actionsPanel__modifica);
		    left__box.add(left__box__backPanel);
		      left__box__backPanel.add(left__box__backPanel__back);
		
		this.add(right);
		  right.add(scrollPane, gbcTable);
		
		
		cont.add(this, "MaterieConf");
	}

}
