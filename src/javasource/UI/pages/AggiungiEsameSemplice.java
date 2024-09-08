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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import javasource.Constants;
import javasource.UI.ClassManager;
import javasource.resourceobjects.EsameSemplice;
import javasource.resourceobjects.Materia;
import javasource.resourceobjects.Studente;

/**
 * Finestra per l'aggiunta di un esame semplice
 */
public class AggiungiEsameSemplice extends JPanel {

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
	private JPanel box__data__mark__lodePanel;

	/**
	 * Titolo inserimento lode
	 */
	private JLabel box__data__mark__lodePanel__label;

	/**
	 * Inserimento lode
	 */
	private JCheckBox box__data__mark__lodePanel__lode;

	/**
	 * Pannello finale
	 */
	private JPanel box__endPanel;

	/**
	 * Pulsante aggiungi
	 */
	private JButton box__endPanel__add;

	/**
	 * Pulsante torna indietro
	 */
	private JButton box__endPanel__back;

	/**
	 * Costruttore
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager //MAnager delle classi
	 * @param esame Esame dello studente
	 */
	public AggiungiEsameSemplice(CardLayout cl, JPanel cont, ClassManager classManager, EsameSemplice esame) {

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

		box__titlePanel__title = new JLabel("Esame Semplice");
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

			// Override del metodo isCellEditable per rendere tutte le celle non modificabili
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
		box__data__mark__votoPanel__field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				box__data__mark__lodePanel__lode.setSelected(false);
				if (box__data__mark__votoPanel__field.getText().equals("30")) {
					box__data__mark__lodePanel__lode.setVisible(true);
				} else {
					box__data__mark__lodePanel__lode.setVisible(false);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				box__data__mark__lodePanel__lode.setSelected(false);
				if (box__data__mark__votoPanel__field.getText().equals("30")) {
					box__data__mark__lodePanel__lode.setVisible(true);
				} else {
					box__data__mark__lodePanel__lode.setVisible(false);
				}

			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				box__data__mark__lodePanel__lode.setSelected(false);
			}
		});

		box__data__mark__lodePanel = new JPanel();
		box__data__mark__lodePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__lodePanel.setLayout(new BoxLayout(box__data__mark__lodePanel, BoxLayout.Y_AXIS));

		box__data__mark__lodePanel__label = new JLabel("Lode");
		box__data__mark__lodePanel__label.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__lodePanel__label.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__data__mark__lodePanel__label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		box__data__mark__lodePanel__label.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H2_SIZE));

		box__data__mark__lodePanel__lode = new JCheckBox("Lode");
		box__data__mark__lodePanel__lode.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__data__mark__lodePanel__lode.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__data__mark__lodePanel__lode.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__data__mark__lodePanel__lode.setVisible(false);

		box__endPanel = new JPanel();
		box__endPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__endPanel.setLayout(new GridLayout(1, 2, 80, 0));
		box__endPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		box__endPanel__add = new JButton("Salva");
		box__endPanel__add.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__endPanel__add.setForeground(Constants.COLOR_TEXT);
		box__endPanel__add.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__endPanel__add.setPreferredSize(new Dimension(box__endPanel__add.getWidth(), 50));
		box__endPanel__add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

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

				String votoStr = box__data__mark__votoPanel__field.getText();
				if (votoStr == null || votoStr.length() == 0) {
					JOptionPane.showMessageDialog(null, "Indica un voto da 18 a 30", "Voto errato",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Integer voto = Integer.parseInt(votoStr);

				if (voto == null || (voto < 18 || voto > 30)) {
					JOptionPane.showMessageDialog(null, "Indica un voto da 18 a 30", "Voto errato",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Boolean lode = box__data__mark__lodePanel__lode.isSelected();

				if (lode && voto != 30) {
					JOptionPane.showMessageDialog(null, "Non puoi assegnare la lode ad un voto diverso da 30",
							"Voto errato", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (esame == null) {

					// I dati sono corretti

					EsameSemplice es = new EsameSemplice(voto, lode, classManager.getStudenteFromMatricola(matricola),
							classManager.getMateriaFromTitolo(titolo));

					Boolean esito = classManager.addEsame(es);

					if (esito) {

						cont.remove(classManager.getPanelsM().getPortaleEsami());

						PortaleEsami nuovoPortaleEsami = new PortaleEsami(cl, cont, classManager);
						cont.add(nuovoPortaleEsami, "PortaleEsami");

						cl.show(cont, "PortaleEsami");
					} else {
						JOptionPane.showMessageDialog(null, "L'aggiunta non e' andata a buon fine",
								"Operazione fallita", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else {
					
					//int index = classManager.getEsamiFilter().indexOf(esame);
					
					// Modifica dell'esame
					esame.setVoto(voto);
					esame.setLode(lode);
					esame.setStudente(classManager.getStudenteFromMatricola(matricola));
					esame.setMateria(classManager.getMateriaFromTitolo(titolo));

					cont.remove(classManager.getPanelsM().getPortaleEsami());

					PortaleEsami nuovoPortaleEsami = new PortaleEsami(cl, cont, classManager);
					cont.add(nuovoPortaleEsami, "PortaleEsami");

					cl.show(cont, "PortaleEsami");
				}

			}
		});

		box__endPanel__back = new JButton("Indietro");
		box__endPanel__back.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__endPanel__back.setForeground(Constants.COLOR_TEXT);
		box__endPanel__back.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__endPanel__back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "AddExams");
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
					JOptionPane.showMessageDialog(null,
							"Visualizzazione dei dati per la modifica non andata a buon fine", "Operazione fallita",
							JOptionPane.ERROR_MESSAGE);

					cl.show(cont, "PortaleEsami");
				} else {

					// Settaggio riga per la tabella con i titoli
					box__data__tables__materie.getSelectionModel().setSelectionInterval(indexTable, indexTable);

					box__data__mark__votoPanel__field.setText(esame.getVoto().toString());
					box__data__mark__lodePanel__lode.setSelected(esame.getLode());
					cont.add(this, "AddEsameSemplice");
					cl.show(cont, "AddEsameSemplice");
				}
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
		box__data__mark.add(box__data__mark__lodePanel);
		box__data__mark__lodePanel.add(box__data__mark__lodePanel__label);
		box__data__mark__lodePanel.add(box__data__mark__lodePanel__lode);
		box.add(box__endPanel);
		box__endPanel.add(box__endPanel__add);
		box__endPanel.add(box__endPanel__back);

		if (esame == null)
			cont.add(this, "AddEsameSemplice");
	}
}
