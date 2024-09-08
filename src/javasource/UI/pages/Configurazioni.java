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
 * Scelta di una configurazione
 */
public class Configurazioni extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * box
	 */
	private JPanel box;
	
	/**
	 * Pannello per il titolo
	 */
	private JPanel box__titlePanel;
	
	/**
	 * Titolo
	 */
	private JLabel box__titlePanel__title;
	
	/**
	 * Pannello che contiene un pulsante per ogni configurazione
	 */
	private JPanel box__options;
	
	/**
	 * Pulsante esame semplice
	 */
	private JButton box__options__studenti;
	
	/**
	 * Pulsante esame composto
	 */
	private JButton box__options__materie;
	
	/**
	 * Pannello per il pulsante torna inditro
	 */
	private JPanel box__backPanel;
	
	/**
	 * Pulsante torna indietrp
	 */
	private JButton box__backPanel__back;
	
	/**
	 * Costruttore
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager //MAnager delle classi
	 */
	public Configurazioni(CardLayout cl, JPanel cont, ClassManager classManager) {
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;
		
		box = new JPanel();
		box.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		
		box__titlePanel = new JPanel();
		box__titlePanel.setBackground(getBackground());
		box__titlePanel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
		
		box__titlePanel__title = new JLabel("Configurazioni");
		box__titlePanel__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__titlePanel__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__titlePanel__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		
		box__options = new JPanel();
		box__options.setBackground(getBackground());
		GridLayout gl = new GridLayout(2,1);
		gl.setVgap(20);
		box__options.setLayout(gl);
		
		box__options__studenti = new JButton("Studenti");
		box__options__studenti.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__options__studenti.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__options__studenti.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		box__options__studenti.setPreferredSize(new Dimension(150,100));
		box__options__studenti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cont.remove(classManager.getPanelsM().getStudenteConf());
				
				StudenteConf sc = new StudenteConf(cl, cont, classManager);
				cont.add(sc, "StudenteConf");
				
				cl.show(cont, "StudenteConf");
			}
		});
		
		box__options__materie = new JButton("Materie");
		box__options__materie.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__options__materie.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__options__materie.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		box__options__materie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cont.remove(classManager.getPanelsM().getMaterieConf());
				
				MateriaConf mc = new MateriaConf(cl, cont, classManager);
				cont.add(mc, "MaterieConf");
				
				cl.show(cont, "MaterieConf");
			}
		});
		
		box__backPanel = new JPanel();
		box__backPanel.setBackground(getBackground());
		box__backPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		
		box__backPanel__back = new JButton("Indietro");
		box__backPanel__back.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__backPanel__back.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__backPanel__back.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__backPanel__back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "Home");
			}
		});
		
		/*********************************************************************************************/
		
		this.add(box, gbc);
		  box.add(box__titlePanel);
		    box__titlePanel.add(box__titlePanel__title);
		  box.add(box__options);
		  	box__options.add(box__options__studenti);
		    box__options.add(box__options__materie);
		  box.add(box__backPanel);
		    box__backPanel.add(box__backPanel__back);
		
		cont.add(this, "Configurazioni");
	}

}
