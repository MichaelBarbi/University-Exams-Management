package javasource.UI.pages;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import javasource.Constants;
import javasource.UI.ClassManager;
import javasource.resourceobjects.Esame;
import javasource.resourceobjects.EsameComposto;
import javasource.resourceobjects.EsameSemplice;
import javasource.resourceobjects.MyTable;

/**
 * Pagina per la gestione degli esami
 */
public class PortaleEsami extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Colonna sinistra
	 */
	private JPanel left;
	
	/**
	 * Contenitore widget colonna sinistra
	 */
	private JPanel left__box;
	
	/**
	 * Pulsante aggiungi
	 */
	private JButton left__box__add;
	
	/**
	 * Pulsante modifica
	 */
	private JButton left__box__modify;
	
	/**
	 * pulsante delete
	 */
	private JButton left__box__delete;
	
	/**
	 * pulsante applica filtro
	 */
	private JButton left__box__filter;
	
	/**
	 * Salva la lista di esami
	 */
	private JButton left__box__save;
	
	/**
	 * Pulsante per tornare indietro
	 */
	private JButton left__box__back;
	
	/**
	 * Tabella con gli esami
	 */
	private MyTable right__table;
	
	/**
	 * Costruttore
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager manager delle classi
	 */
	public PortaleEsami(CardLayout cl, JPanel cont, ClassManager classManager) {
		this.requestFocus();
		
		this.setLayout(new GridLayout(1,2));
		
		left = new JPanel();
		left.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		left.setLayout(new GridBagLayout());
		GridBagConstraints gbcLeft = new GridBagConstraints();
		gbcLeft.gridx = 0;
		gbcLeft.gridy = 0;
		gbcLeft.fill = GridBagConstraints.CENTER;
		
		left__box = new JPanel();
		left__box.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		GridLayout bl = new GridLayout(0, 1);
		bl.setVgap(20);
		left__box.setLayout(bl);
		
		left__box__add = new JButton("Aggiungi");
		left__box__add.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__add.setForeground(Constants.COLOR_TEXT);
		left__box__add.setPreferredSize(new Dimension(300,80));
		left__box__add.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		left__box__add.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "AddExams");
			}
		});
		
		left__box__modify = new JButton("Modifica");
		left__box__modify.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__modify.setForeground(Constants.COLOR_TEXT);
		left__box__modify.setPreferredSize(new Dimension(300,80));
		left__box__modify.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		left__box__modify.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__modify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer index = right__table.getTable().getSelectedRow();
				
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "Selezionare una riga della tabella", "Operazione fallita", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Object esame = classManager.createEsameFromTable(index);
				
				if (esame == null) {
					JOptionPane.showMessageDialog(null, "Visualizzazione dei dati per la modifica non andata a buon fine", "Operazione fallita", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (esame instanceof EsameSemplice) {
					cont.remove(classManager.getPanelsM().getAggiungiEsameSemplice());
					
					@SuppressWarnings("unused")
					AggiungiEsameSemplice aes = new AggiungiEsameSemplice(cl, cont, classManager, (EsameSemplice) esame);
				} else if (esame instanceof EsameComposto) {
					cont.remove(classManager.getPanelsM().getAggiungiEsameComposto());
					
					@SuppressWarnings("unused")
					AggiungiEsameComposto aes = new AggiungiEsameComposto(cl, cont, classManager, (EsameComposto) esame, false);
				} else {
					JOptionPane.showMessageDialog(null, "Visualizzazione dei dati per la modifica non andata a buon fine", "Operazione fallita", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
		
		left__box__delete = new JButton("Elimina");
		left__box__delete.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__delete.setForeground(Constants.COLOR_TEXT);
		left__box__delete.setPreferredSize(new Dimension(300,80));
		left__box__delete.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		left__box__delete.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Controlla se è stato seelzionato un esame
				Integer index = right__table.getTable().getSelectedRow();
				
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "Selezionare una riga della tabella", "Operazione fallita", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Boolean esito = classManager.deleteExam(index);
				
				if (esito) {
					JOptionPane.showMessageDialog(null, "L'esame è stato eliminato", "Operazione completata", JOptionPane.INFORMATION_MESSAGE);
					
					//Rimuovo la classe allocata
					cont.remove(classManager.getPanelsM().getPortaleEsami());
					
					//Creo un nuovo oggetto
					PortaleEsami pe = new PortaleEsami(cl, cont, classManager);
					cont.add(pe, "PortaleEsami");
					
					cl.show(cont, "PortaleEsami");
					
				} else {
					JOptionPane.showMessageDialog(null, "Rimozione non avvenuta", "Operazione fallita", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		left__box__filter = new JButton("Filtro");
		left__box__filter.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__filter.setForeground(Constants.COLOR_TEXT);
		left__box__filter.setPreferredSize(new Dimension(300,80));
		left__box__filter.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		left__box__filter.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__filter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "Filter");
			}
		});
		
		left__box__save = new JButton("Salva");
		left__box__save.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__save.setForeground(Constants.COLOR_TEXT);
		left__box__save.setPreferredSize(new Dimension(300,80));
		left__box__save.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		left__box__save.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H3_SIZE));
		left__box__save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
    			classManager.getEsamiFileManager().saveFile(classManager);
			}
		});
		
		left__box__back = new JButton("Indietro");
		left__box__back.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		left__box__back.setForeground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		left__box__back.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		left__box__back.setPreferredSize(new Dimension(20,50));
		left__box__back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "Home");
			}
		});
		
		//Devo ottenere la lista degli esami
		Object[][] data = classManager.getArrayDataFromEsami("Filter");
		
		if (data == null) {
			System.exit(1);
		}
		

        // Creazione delle colonne della tabella
        String[] columns = {"Matricola", "Studente", "Materia", "Voto", "Lode", "CFU", "Composto"};
        
        right__table = new MyTable(columns, data);
        right__table.getTable().addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    
                    Esame esame = classManager.getEsamiFilter().get(row);
                    
                    if (esame instanceof EsameComposto) {
                    	cont.remove(classManager.getPanelsM().getAggiungiEsameComposto());
    					
    					@SuppressWarnings("unused")
						AggiungiEsameComposto aes = new AggiungiEsameComposto(cl, cont, classManager, (EsameComposto) esame, true);
                    }
                }
            }
		});
        
		/*********************************************************************************************/
		
		this.add(left);
		  left.add(left__box, gbcLeft);
		    left__box.add(left__box__add);
		    left__box.add(left__box__modify);
		    left__box.add(left__box__delete);
		    left__box.add(left__box__filter);
		    left__box.add(left__box__save);
		    left__box.add(left__box__back);
		
		
		
		this.add(right__table);
		
		cont.add(this, "PortaleEsami");
		
	}

}
