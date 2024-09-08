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
 * Bivio tra l'aggiungere un esame semplice o un esame composto
 */
public class AggiungiEsameHome extends JPanel{
	
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
	 * Pannello che contiene i due pulsanti per la scelta dell'esame
	 */
	private JPanel box__options;
	
	/**
	 * Pulsante esame semplice
	 */
	private JButton box__options__semplice;
	
	/**
	 * Pulsante esame composto
	 */
	private JButton box__options__composto;
	
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
	public AggiungiEsameHome(CardLayout cl, JPanel cont, ClassManager classManager) {
		
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
		
		box__titlePanel__title = new JLabel("Aggiungi Esame");
		box__titlePanel__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__titlePanel__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__titlePanel__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		
		box__options = new JPanel();
		box__options.setBackground(getBackground());
		GridLayout gl = new GridLayout(2,1);
		gl.setVgap(20);
		box__options.setLayout(gl);
		
		box__options__semplice = new JButton("Esame semplice");
		box__options__semplice.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__options__semplice.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__options__semplice.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		box__options__semplice.setPreferredSize(new Dimension(150,100));
		box__options__semplice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cont.remove(classManager.getPanelsM().getAggiungiEsameSemplice());
				
				AggiungiEsameSemplice aes = new AggiungiEsameSemplice(cl, cont, classManager, null);
				cont.add(aes, "AddEsameSemplice");
				
				cl.show(cont, "AddEsameSemplice");
			}
		});
		
		box__options__composto = new JButton("Esame composto");
		box__options__composto.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__options__composto.setForeground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__options__composto.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		box__options__composto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cont.remove(classManager.getPanelsM().getAggiungiEsameComposto());
				
				AggiungiEsameComposto aec = new AggiungiEsameComposto(cl, cont, classManager,null, false);
				cont.add(aec, "AddEsameComposto");
				
				cl.show(cont, "AddEsameComposto");
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
				cl.show(cont, "PortaleEsami");
			}
		});
		
		/*********************************************************************************************/
		
		this.add(box, gbc);
		  box.add(box__titlePanel);
		    box__titlePanel.add(box__titlePanel__title);
		  box.add(box__options);
		  	box__options.add(box__options__semplice);
		    box__options.add(box__options__composto);
		  box.add(box__backPanel);
		    box__backPanel.add(box__backPanel__back);
		
		cont.add(this, "AddExams");
	}

}
