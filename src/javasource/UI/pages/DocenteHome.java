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
import javax.swing.JPanel;

import javasource.Constants;
import javasource.UI.ClassManager;

/**
 * Rappresenta la schermata Home dell'applicazione
 */
public class DocenteHome extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Colonna sinistra
	 */
	private JPanel left;
	
	/**
	 * Contenitore dei widget a sinistra
	 */
	private JPanel left__box;
	
	/**
	 * Pannello per il titolo
	 */
	private JPanel left__box__titlePanel;
	
	/**
	 * Titolo dell'app
	 */
	private JLabel left__box__titlePanel__title;
	
	/**
	 * Panel che contiene le possibili opzioni
	 */
	private JPanel left__box__optionsPanel;
	
	/**
	 * Pulsante per la gestione degli esami
	 */
	private JButton left__box__optionsPanel__exams;
	
	/**
	 * Pulsante per la visualizzazione di dati statistici
	 */
	private JButton left__box__optionsPanel__statistics;
	
	/**
	 * Pulsante per modificare alcune configurazioni
	 */
	private JButton left__box__optionsPanel__confs;
	
	/**
	 * Pulsante per gestire il file degli esami
	 */
	private JButton left__box__optionsPanel__file;
	
	/**
	 * Panel dell'uscita
	 */
	private JPanel left__box__exitPanel;
	
	/**
	 * Pulsante per uscire
	 */
	private JButton left__box__exitPanel__exit;
	
	/**
	 * Costruttore
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager //MAnager delle classi
	 */
	public DocenteHome(CardLayout cl, JPanel cont, ClassManager classManager) {
		
		this.setLayout(new GridLayout(1,2));
		
		left = new JPanel();
		left.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left.setLayout(new GridBagLayout());
		GridBagConstraints leftGBC = new GridBagConstraints();
		leftGBC.gridx = 0;
		leftGBC.gridy = 0;
		leftGBC.fill = GridBagConstraints.CENTER;
		
		left__box = new JPanel();
		left__box.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		left__box.setLayout(new BoxLayout(left__box, BoxLayout.Y_AXIS));
		
		left__box__titlePanel = new JPanel();
		left__box__titlePanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__titlePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		left__box__titlePanel__title = new JLabel("UEM");
		left__box__titlePanel__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		left__box__titlePanel__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.ICON_TITLE_SIZE));
		
		left__box__optionsPanel = new JPanel();
		left__box__optionsPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__optionsPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		GridLayout optionsLayout = new GridLayout(2,2);
		optionsLayout.setHgap(30);
		optionsLayout.setVgap(30);
		left__box__optionsPanel.setLayout(optionsLayout);
		
		left__box__optionsPanel__exams = new JButton("Esami");
		left__box__optionsPanel__exams.setPreferredSize(new Dimension(140,80));
		left__box__optionsPanel__exams.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__optionsPanel__exams.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__optionsPanel__exams.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__optionsPanel__exams.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cont.remove(classManager.getPanelsM().getPortaleEsami());
				
				PortaleEsami pe = new PortaleEsami(cl, cont, classManager);
				cont.add(pe,  "PortaleEsami");
				
				cl.show(cont, "PortaleEsami");
			}
		});
		
		left__box__optionsPanel__statistics = new JButton("Statistiche");
		left__box__optionsPanel__statistics.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__optionsPanel__statistics.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__optionsPanel__statistics.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__optionsPanel__statistics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont.remove(classManager.getPanelsM().getStat());
				
				Statistiche s = new Statistiche(cl, cont, classManager);
				cont.add(s, "Statistiche");
				
				cl.show(cont, "Statistiche");
			}
		});
		
		left__box__optionsPanel__confs = new JButton("Configurazioni");
		left__box__optionsPanel__confs.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__optionsPanel__confs.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__optionsPanel__confs.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__optionsPanel__confs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "Configurazioni");
			}
		});
		
		left__box__optionsPanel__file = new JButton("File");
		left__box__optionsPanel__file.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__optionsPanel__file.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__optionsPanel__file.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__optionsPanel__file.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "File");
			}
		});
		
		left__box__exitPanel = new JPanel();
		left__box__exitPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__exitPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		
		left__box__exitPanel__exit = new JButton("Esci");
		left__box__exitPanel__exit.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__exitPanel__exit.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__exitPanel__exit.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__exitPanel__exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				classManager.applicationClosing();
			}
		});
		
		
		
		/****************************************************************************************************/
		
		this.add(left);
		  left.add(left__box, leftGBC);
		    left__box.add(left__box__titlePanel);
		      left__box__titlePanel.add( left__box__titlePanel__title );
		    left__box.add(left__box__optionsPanel);
			    left__box__optionsPanel.add(left__box__optionsPanel__exams);
			    left__box__optionsPanel.add(left__box__optionsPanel__statistics);
			    left__box__optionsPanel.add(left__box__optionsPanel__confs);
			    left__box__optionsPanel.add(left__box__optionsPanel__file);
			left__box.add(left__box__exitPanel);
			  left__box__exitPanel.add(left__box__exitPanel__exit);
		
		cont.add(this, "Home");
	}

}
