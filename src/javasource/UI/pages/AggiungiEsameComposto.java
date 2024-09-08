package javasource.UI.pages;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javasource.resourceobjects.EsameComposto;
import javasource.resourceobjects.Materia;
import javasource.resourceobjects.Studente;

/**
 * Schermata per l'inserimento di un esame composto
 */
public class AggiungiEsameComposto extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * box
	 */
	private JPanel box;

	/**
	 * Pannello del titolo
	 */
	private JPanel box__titlePanel;

	/**
	 * titolo
	 */
	private JLabel box__titlePanel__title;

	/**
	 * Box nel quale inserire i vari dati
	 */
	private JPanel box__data;

	/**
	 * Panel per le tabelle
	 */
	private JPanel box__data__tables;

	/**
	 * Tabella con gli studenti registrati
	 */
	private JTable box__data__tables__studenti;

	/**
	 * Tabella con le materie registrate
	 */
	private JTable box__data__tables__materie;

	/**
	 * Pannello area voto
	 */
	private JPanel box__data__mark;

	/**
	 * Pannello per l'inserimento del voto
	 */
	private JPanel box__data__mark__votoPanel;

	/**
	 * Label per l'inserimento del voto
	 */
	private JLabel box__data__mark__votoPanel__title;

	/**
	 * Campo per l'inserimento del voto
	 */
	private JTextField box__data__mark__votoPanel__field;

	/**
	 * Pannello per l'inseriemnto della lode
	 */
	private JPanel box__data__mark__pesoPanel;

	/**
	 * Titolo inserimento lode
	 */
	private JLabel box__data__mark__pesoPanel__label;

	/**
	 * Inserimento lode
	 */
	private JTextField box__data__mark__pesoPanel__peso;

	/**
	 * Pannello finale
	 */
	private JPanel box__optionsPanel;

	/**
	 * Pulsante aggiungi
	 */
	private JButton box__optionsPanel__add;

	/**
	 * Pulsante torna indietro
	 */
	private JButton box__optionsPanel__delete;

	/**
	 * Pannello per la lode
	 */
	private JPanel box__lodePanel;

	/**
	 * Lode
	 */
	private JCheckBox box__lodePanel__lode;

	/**
	 * Tabelle voti
	 */
	private JPanel box__data__markTables;

	/**
	 * Tabella
	 */
	private JTable box__data__markTables__table;

	/**
	 * Pulsanti finali
	 */
	private JPanel box__endButton;

	/**
	 * Pulsante per salvare l'esame
	 */
	private JButton box__endButton__save;

	/**
	 * Pulsante per tornare indietro
	 */
	private JButton box__endButton__back;

	/**
	 * Costruttore
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager //MAnager delle classi
	 * @param esame Esame dello studente
	 * @param doubleClick Se è stato effettuato il doppio click sulla riga della tabella
	 */
	public AggiungiEsameComposto(CardLayout cl, JPanel cont, ClassManager classManager, EsameComposto esame, Boolean doubleClick) {
		this.setLayout(new GridBagLayout());
		this.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;

		box = new JPanel();
		box.setBackground(getBackground());
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

		box__titlePanel = new JPanel();
		box__titlePanel.setBackground(getBackground());
		box__titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

		box__titlePanel__title = new JLabel("Esame Composto");
		box__titlePanel__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__titlePanel__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__titlePanel__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));

		box__data = new JPanel();
		box__data.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data.setLayout(new BoxLayout(box__data, BoxLayout.Y_AXIS));

		box__data__tables = new JPanel();
		box__data__tables.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__tables.setLayout(new GridLayout(1, 2, 80, 0));
		box__data__tables.setPreferredSize(new Dimension(400, 150));

		// Creazione dei dati per la tabella
		Object[][] studentiData = classManager.getMatricole();

		// Creazione delle colonne della tabella
		String[] studentiColumns = { "Matricola" };

		DefaultTableModel studentiModel = new DefaultTableModel(studentiData, studentiColumns) {
			private static final long serialVersionUID = 1L;

			// Override del metodo isCellEditable per rendere tutte le celle non
			// modificabili
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		box__data__tables__studenti = new JTable(studentiModel);
		box__data__tables__studenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		box__data__tables__studenti.setRowHeight(30);
		// Creazione del modello dei dati per la tabella
		JScrollPane studentiScrollPane = new JScrollPane(box__data__tables__studenti);

		// Creazione dei dati per la tabella
		Object[][] materieData = classManager.getTitoli();

		// Creazione delle colonne della tabella
		String[] materieColumns = { "Titolo" };

		DefaultTableModel materieModel = new DefaultTableModel(materieData, materieColumns) {
			private static final long serialVersionUID = 1L;

			// Override del metodo isCellEditable per rendere tutte le celle non
			// modificabili
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		box__data__tables__materie = new JTable(materieModel);
		box__data__tables__materie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		box__data__tables__materie.setRowHeight(30);
		// Creazione del modello dei dati per la tabella
		JScrollPane materieScrollPane = new JScrollPane(box__data__tables__materie);

		box__data__mark = new JPanel();
		box__data__mark.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark.setLayout(new GridLayout(1, 2, 80, 0));
		box__data__mark.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		box__data__mark__votoPanel = new JPanel();
		box__data__mark__votoPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__votoPanel.setLayout(new BoxLayout(box__data__mark__votoPanel, BoxLayout.Y_AXIS));

		box__data__mark__votoPanel__title = new JLabel("Voto");
		box__data__mark__votoPanel__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__votoPanel__title.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__data__mark__votoPanel__title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		box__data__mark__votoPanel__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H2_SIZE));

		box__data__mark__votoPanel__field = new JTextField();
		box__data__mark__votoPanel__field.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__data__mark__votoPanel__field.setForeground(Constants.COLOR_TEXT);
		box__data__mark__votoPanel__field.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));

		box__data__mark__pesoPanel = new JPanel();
		box__data__mark__pesoPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__pesoPanel.setLayout(new BoxLayout(box__data__mark__pesoPanel, BoxLayout.Y_AXIS));

		box__data__mark__pesoPanel__label = new JLabel("Peso");
		box__data__mark__pesoPanel__label.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__pesoPanel__label.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__data__mark__pesoPanel__label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		box__data__mark__pesoPanel__label.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H2_SIZE));

		box__data__mark__pesoPanel__peso = new JTextField();
		box__data__mark__pesoPanel__peso.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__data__mark__pesoPanel__peso.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__pesoPanel__peso.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));

		box__optionsPanel = new JPanel();
		box__optionsPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__optionsPanel.setLayout(new GridLayout(1, 2, 80, 0));
		box__optionsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		box__optionsPanel__add = new JButton("Aggiungi");
		box__optionsPanel__add.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__optionsPanel__add.setForeground(Constants.COLOR_TEXT);
		box__optionsPanel__add.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__optionsPanel__add.setPreferredSize(new Dimension(box__optionsPanel__add.getWidth(), 50));

		box__optionsPanel__delete = new JButton("Elimina");
		box__optionsPanel__delete.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__optionsPanel__delete.setForeground(Constants.COLOR_TEXT);
		box__optionsPanel__delete.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));

		box__lodePanel = new JPanel();
		box__lodePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__lodePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		box__lodePanel__lode = new JCheckBox("Lode");
		box__lodePanel__lode.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__lodePanel__lode.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__lodePanel__lode.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__lodePanel__lode.setVisible(false);

		box__data__markTables = new JPanel();
		box__data__markTables.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__markTables.setLayout(new GridLayout(1, 2, 80, 0));
		box__data__markTables.setPreferredSize(new Dimension(400, 150));
		box__data__markTables.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		// Creazione dei dati per la tabella
		Object[][] votiData = null;

		// Creazione delle colonne della tabella
		String[] votiColumns = { "Voti", "Pesi" };

		DefaultTableModel votiModel = new DefaultTableModel(votiData, votiColumns) {
			private static final long serialVersionUID = 1L;

			// Override del metodo isCellEditable per rendere tutte le celle non
			// modificabili
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		box__data__markTables__table = new JTable(votiModel);
		box__data__markTables__table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		box__data__markTables__table.setRowHeight(30);
		// Creazione del modello dei dati per la tabella
		JScrollPane votiScrollPane = new JScrollPane(box__data__markTables__table);

		box__endButton = new JPanel();
		box__endButton.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__endButton.setLayout(new GridLayout(1, 2, 80, 0));
		box__endButton.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		box__endButton__save = new JButton("Salva");
		box__endButton__save.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__endButton__save.setForeground(Constants.COLOR_TEXT);
		box__endButton__save.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__endButton__save.setPreferredSize(new Dimension(box__optionsPanel__add.getWidth(), 50));
		
		if (doubleClick) {
			box__endButton__save.setVisible(false);
		} else {
			box__endButton__save.setVisible(true);
		}

		box__endButton__back = new JButton("Indietro");
		box__endButton__back.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__endButton__back.setForeground(Constants.COLOR_TEXT);
		box__endButton__back.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__endButton__back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "AddExams");
			}
		});

		box__optionsPanel__add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * - Recuperare il voto - Recuperare il peso - Aggiungere rispettivamente alla
				 * tabella dei voti e dei pesi
				 */

				if (box__data__mark__votoPanel__field.getText() == null
						|| box__data__mark__votoPanel__field.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Indica un voto da 18 a 30", "Voto errato",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (box__data__mark__pesoPanel__peso.getText() == null
						|| box__data__mark__pesoPanel__peso.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Indicare il peso in percentuale (1 - 99)", "Voto errato",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Integer voto = Integer.parseInt(box__data__mark__votoPanel__field.getText());
				Integer peso = Integer.parseInt(box__data__mark__pesoPanel__peso.getText());

				if (voto == null || (voto < 18 || voto > 30)) {
					JOptionPane.showMessageDialog(null, "Indica un voto da 18 a 30", "Voto errato",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (peso == null || (peso < 1 || peso > 99)) {
					JOptionPane.showMessageDialog(null, "Indicare il peso in percentuale (1 - 99)", "Peso errato",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Caricamento dei dati nelle loro tabelle

				Object[] votoRow = new Object[] { voto };
				Object[] pesoRow = new Object[] { peso };

				// Gestire il controllo sul limite dei pesi
				Integer pesiCounter = 0;

				for (int i = 0; i < box__data__markTables__table.getRowCount(); i++) {
					pesiCounter += Integer.parseInt(box__data__markTables__table.getValueAt(i, 1).toString());
				}

				if ((pesiCounter + Integer.parseInt(pesoRow[0].toString())) > 100) {
					JOptionPane.showMessageDialog(null, "La percentuale del peso non e' valida", "Peso errato",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if ((pesiCounter + Integer.parseInt(pesoRow[0].toString())) == 100) {
					box__optionsPanel__add.setVisible(false);
				}

				votiModel.addRow(new Object[] { votoRow[0].toString(), pesoRow[0].toString() });

			}
		});

		box__optionsPanel__delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * - Selezione della riga - Eliminazione dalla tabella
				 */

				if (box__data__markTables__table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Seleziona una riga della tabella dei voti",
							"Operazione fallita", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Integer index = box__data__markTables__table.getSelectedRow();

				votiModel.removeRow(index);

				box__optionsPanel__add.setVisible(true);
			}
		});

		box__endButton__save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (box__optionsPanel__add.isVisible()) {
					JOptionPane.showMessageDialog(null, "Valori per voti - pesi non validi", "Operazione fallita",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (box__data__tables__studenti.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Seleziona una matricola", "Matricola mancante",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (box__data__tables__materie.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Seleziona un titolo", "Materia mancante",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String matricola = classManager.getStudenti().get((int) box__data__tables__studenti.getSelectedRow())
						.getMatricola();
				String titolo = classManager.getMaterie().get((int) box__data__tables__materie.getSelectedRow())
						.getTitolo();

				// Selezione dei voti&pesi
				List<Integer> voti = new ArrayList<Integer>(box__data__markTables__table.getRowCount());
				List<Integer> pesi = new ArrayList<Integer>(box__data__markTables__table.getRowCount());

				for (int i = 0; i < box__data__markTables__table.getRowCount(); i++) {
					voti.add(Integer.parseInt(box__data__markTables__table.getValueAt(i, 0).toString()));
					pesi.add(Integer.parseInt(box__data__markTables__table.getValueAt(i, 1).toString()));
				}
				
				if (esame == null) {

					EsameComposto ec = new EsameComposto(null, null, classManager.getStudenteFromMatricola(matricola),
							classManager.getMateriaFromTitolo(titolo), voti, pesi);
	
					Integer votoFinale = ec.calcolaVoto();
	
					ec.setVoto(votoFinale);
	
					if (votoFinale == 30) {
						int choice = JOptionPane.showConfirmDialog(null, "Aggiungere la lode?", "Voto Finale",
								JOptionPane.OK_CANCEL_OPTION);
	
						if (choice == JOptionPane.OK_OPTION) {
							ec.setLode(true);
						} else if (choice == JOptionPane.CANCEL_OPTION) {
							ec.setLode(false);
						} else {
							ec.setLode(false);
						}
					} else {
						ec.setLode(false);
					}
	
					Boolean esito = classManager.addEsame(ec);
	
					if (!esito) {
						JOptionPane.showMessageDialog(null, "L'aggiunta non e' andata a buon fine", "Operazione fallita",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else {
					esame.setPercPesi(pesi);
					esame.setVotiParziali(voti);
					esame.setStudente(classManager.getStudenteFromMatricola(matricola));
					esame.setMateria(classManager.getMateriaFromTitolo(titolo));
					
					Integer votoFinale = esame.calcolaVoto();
					
					esame.setVoto(votoFinale);
					
					if (votoFinale == 30) {
						int choice = JOptionPane.showConfirmDialog(null, "Aggiungere la lode?", "Voto Finale",
								JOptionPane.OK_CANCEL_OPTION);
	
						if (choice == JOptionPane.OK_OPTION) {
							esame.setLode(true);
						} else if (choice == JOptionPane.CANCEL_OPTION) {
							esame.setLode(false);
						} else {
							esame.setLode(false);
						}
					} else {
						esame.setLode(false);
					}
				}

				cont.remove(classManager.getPanelsM().getPortaleEsami());

				PortaleEsami nuovoPortaleEsami = new PortaleEsami(cl, cont, classManager);
				cont.add(nuovoPortaleEsami, "PortaleEsami");

				cl.show(cont, "PortaleEsami");
			}
		});

		if (esame != null) {

			Integer indexTable = null;

			// indice per lo studente
			for (int i = 0; i < classManager.getStudenti().size(); i++) {
				Studente s = classManager.getStudenti().get((Integer) i);

				if (s.getMatricola().equals(esame.getStudente().getMatricola())) {
					indexTable = i;
				}
			}

			if (indexTable == null
					|| (indexTable != null && (indexTable < 0 || indexTable > classManager.getStudenti().size()))) {
				// Error Alert --> portaleEsami
				JOptionPane.showMessageDialog(null, "Visualizzazione dei dati per la modifica non andata a buon fine",
						"Operazione fallita", JOptionPane.ERROR_MESSAGE);

				cl.show(cont, "PortaleEsami");
			} else {

				// Settaggio riga per la tabella con le matricole
				box__data__tables__studenti.getSelectionModel().setSelectionInterval(indexTable, indexTable);

				indexTable = null;

				// indice per la materia
				for (int i = 0; i < classManager.getMaterie().size(); i++) {
					Materia m = classManager.getMaterie().get((Integer) i);

					if (m.getTitolo().equals(esame.getMateria().getTitolo())) {
						indexTable = i;
					}
				}

				if (indexTable == null || indexTable < 0 || indexTable > classManager.getStudenti().size()) {
					// Error Alert --> portaleEsami
					JOptionPane.showMessageDialog(null,"Visualizzazione dei dati per la modifica non andata a buon fine", "Operazione fallita",
							JOptionPane.ERROR_MESSAGE);

					cl.show(cont, "PortaleEsami");
				} else {

					// Settaggio riga per la tabella con i titoli
					box__data__tables__materie.getSelectionModel().setSelectionInterval(indexTable, indexTable);

					for (int k = 0; k < esame.getPercPesi().size(); k++) {
						Integer esameVoto = esame.getVotiParziali().get((Integer) k);
						Integer esamePeso = esame.getPercPesi().get((Integer) k);

						Object[] newRow = { esameVoto, esamePeso };
						votiModel.addRow(newRow);
					}

					cont.add(this, "AddEsameComposto");
					cl.show(cont, "AddEsameComposto");
				}
			}
			
			Integer pesiCounter = 0;

			for (int i = 0; i < box__data__markTables__table.getRowCount(); i++) {
				pesiCounter += Integer.parseInt(box__data__markTables__table.getValueAt(i, 1).toString());
			}

			if (pesiCounter  == 100) {
				box__optionsPanel__add.setVisible(false);
			}
		}
			

		/******************************************************************************************************************/

		this.add(box, gbc);
		box.add(box__titlePanel);
		box__titlePanel.add(box__titlePanel__title);
		box.add(box__data);
		box__data.add(box__data__tables);
		box__data__tables.add(studentiScrollPane);
		box__data__tables.add(materieScrollPane);
		box__data.add(box__data__mark);
		box__data__mark.add(box__data__mark__votoPanel);
		box__data__mark__votoPanel.add(box__data__mark__votoPanel__title);
		box__data__mark__votoPanel.add(box__data__mark__votoPanel__field);
		box__data__mark.add(box__data__mark__pesoPanel);
		box__data__mark__pesoPanel.add(box__data__mark__pesoPanel__label);
		box__data__mark__pesoPanel.add(box__data__mark__pesoPanel__peso);
		box__data.add(box__lodePanel);
		box__lodePanel.add(box__lodePanel__lode);
		box.add(box__optionsPanel);
		box__optionsPanel.add(box__optionsPanel__add);
		box__optionsPanel.add(box__optionsPanel__delete);
		box.add(box__data__markTables);
		box__data__markTables.add(votiScrollPane);
		box.add(box__endButton);
		box__endButton.add(box__endButton__save);
		box__endButton.add(box__endButton__back);

		if (esame == null)
			cont.add(this, "AddEsameComposto");
	}
}
