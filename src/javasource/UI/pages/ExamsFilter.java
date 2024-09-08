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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javasource.Constants;
import javasource.UI.ClassManager;
import javasource.resourceobjects.Esame;
import javasource.resourceobjects.EsameComposto;
import javasource.resourceobjects.EsameSemplice;

/**
 * Rappresenta la schermata di selezione del filtro di visualizzazione / ricerca degli esami in tabella.
 */
public class ExamsFilter extends JPanel{

	private static final long serialVersionUID = 1L;

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
	 * Panel per il filtro ricerca
	 */
	private JPanel box__searchPanel;
	
	/**
	 * Titolo per il filtro di ricerca
	 */
	private JLabel box__searchPanel__title;
	
	/**
	 * Barra di ricerca
	 */
	private JTextField box__searchPanel__field;
	
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
	 * Costruttore 
	 * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager Manager delle classi
	 */
	public ExamsFilter(CardLayout cl, JPanel cont, ClassManager classManager) {
		
		List<Esame> newEsamiFilter = new ArrayList<Esame>(classManager.getEsami().size());
		newEsamiFilter.addAll(classManager.getEsami());
		classManager.setEsamiFilter(newEsamiFilter);
		
		this.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbcRoot = new GridBagConstraints();
		gbcRoot.gridx = 0;
		gbcRoot.gridy = 0;
		gbcRoot.fill = GridBagConstraints.CENTER;
		
		box = new JPanel();
		box.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		
		box__filterSelection = new JPanel();
		box__filterSelection.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__filterSelection.setLayout(new BoxLayout(box__filterSelection, BoxLayout.Y_AXIS));
		
		box__filterSelection__title = new JLabel("Scegli il filtro");
		box__filterSelection__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__filterSelection__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__filterSelection__title.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		box__filterSelection__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		
		String[] options = {"Nessun filtro", "Materia", "Studente"};
		
		box__filterSelection__combo = new JComboBox<String>(options);
		box__filterSelection__combo.setPreferredSize(new Dimension(50,50));
		box__filterSelection__combo.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__filterSelection__combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedoption = (String) box__filterSelection__combo.getSelectedItem();
				if (selectedoption.equals("Materia") || selectedoption.equals("Studente")) {
					box__searchPanel.setVisible(true);
				} else {
					box__searchPanel.setVisible(false);
				}
			}
		});
		
		box__searchPanel = new JPanel();
		box__searchPanel.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__searchPanel.setLayout(new BoxLayout(box__searchPanel, BoxLayout.Y_AXIS));
		box__searchPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));		
		box__searchPanel.setVisible(false);
		
		box__searchPanel__title = new JLabel("Barra di ricerca");
		box__searchPanel__title.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__searchPanel__title.setForeground(Constants.COLOR_H1_LIGHT_RGB);
		box__searchPanel__title.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		box__searchPanel__title.setFont(new Font(Constants.FONT, Font.BOLD, Constants.H1_SIZE));
		
		box__searchPanel__field = new JTextField();
		box__searchPanel__field.setPreferredSize(new Dimension(50,50));
		box__searchPanel__field.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		
		box__buttons = new JPanel();
		box__buttons.setBackground(Constants.BACKGROUND_COLOR_DARK_RGB);
		box__buttons.setLayout(new GridLayout(1, 2, 20, 0));
		box__buttons.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		
		box__buttons__save = new JButton("Conferma");
		box__buttons__save.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__save.setForeground(Constants.COLOR_TEXT);
		box__buttons__save.setPreferredSize(new Dimension(80, 50));
		box__buttons__save.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__buttons__save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * - Tipo di filtro applicato
				 * - Barra di ricerca
				 * 
				 * Studenti => matricola e nome/cognome
				 * Materia  => Titolo 
				 * 
				 */
				
				Integer index = box__filterSelection__combo.getSelectedIndex();
				
				if (index < 0 || index > options.length) {
					JOptionPane.showMessageDialog(null, "Selezionare un filtro di ricerca valido","Operazione fallita", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String ricerca = "";
				List<Esame> newList = null;
				
				String selectedoption = (String) box__filterSelection__combo.getSelectedItem(); 
				
				switch(selectedoption) {
					case "Nessun filtro":
						//Riporto la lista identica a come si trova, non necessito di un ordine in particolare
						classManager.setEsamiFilter(classManager.getEsami());
						break;
					case "Materia":
						//Materia
						
						ricerca = box__searchPanel__field.getText();
						
						/*
						 * Ricerca e visualizzazione degli esami aventi le materie che rispettano il filtro di ricerca
						 */
						
						if (ricerca == null || ricerca.length() <= 0 || ricerca.equals("") ) {
							JOptionPane.showMessageDialog(null, "Inserire del testo nella barra di ricerca","Operazione fallita", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						newList = searchByMateria(classManager.getEsami(), ricerca);
						classManager.setEsamiFilter(newList);
						
						break;
					case "Studente":
						//Studente
						
						ricerca = box__searchPanel__field.getText();
						
						/*
						 * Ricerca e visualizzazione degli esami con matricola che rispetta filtro di ricerca
						 */
						
						if (ricerca == null || ricerca.length() <= 0 || ricerca.equals("") ) {
							JOptionPane.showMessageDialog(null, "Inserire del testo nella barra di ricerca","Operazione fallita", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						newList = searchByMatricola(classManager.getEsami(), ricerca);
						classManager.setEsamiFilter(newList);
						
						break;
					default:
						JOptionPane.showMessageDialog(null, "Selezionare un filtro di ricerca valido","Operazione fallita", JOptionPane.ERROR_MESSAGE);
						return;						
				}
				
				cont.remove(classManager.getPanelsM().getPortaleEsami());
				
				PortaleEsami pe = new PortaleEsami(cl, cont, classManager);
				cont.add(pe, "PortaleEsami");
				
				cl.show(cont, "PortaleEsami");
			}
		});
		
		box__buttons__back = new JButton("Indietro");
		box__buttons__back.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		box__buttons__back.setForeground(Constants.COLOR_TEXT);
		box__buttons__back.setFont(new Font(Constants.FONT, Font.BOLD, Constants.TEXT));
		box__buttons__back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont, "PortaleEsami");
			}
		});
		
		/************************************************************************************/
		
		this.add(box, gbcRoot);
		  box.add(box__filterSelection);
		    box__filterSelection.add(box__filterSelection__title);
		    box__filterSelection.add(box__filterSelection__combo);
		  box.add(box__searchPanel);
		    box__searchPanel.add(box__searchPanel__title);
		    box__searchPanel.add(box__searchPanel__field);
		  box.add(box__buttons);
		    box__buttons.add(box__buttons__save);
		    box__buttons.add(box__buttons__back);
		
		cont.add(this, "Filter");
	}
	
	/**
	 * Metodo per la ricerca delle materie negli esami
	 * @param esami
	 * @param ricerca
	 * @return
	 */
    private static List<Esame> searchByMateria(List<Esame> esami, String ricerca) {
        List<Esame> tmp = new ArrayList<>();

        for (Esame esame : esami) {
        	
            if (esame instanceof EsameSemplice) {
                EsameSemplice esameSemplice = (EsameSemplice) esame;
                if (esameSemplice.getMateria().getTitolo().toLowerCase().startsWith(ricerca.toLowerCase())) {
                	tmp.add(esameSemplice);
                }
            } else if (esame instanceof EsameComposto) {
                EsameComposto esameComposto = (EsameComposto) esame;
                if (esameComposto.getMateria().getTitolo().toLowerCase().startsWith(ricerca.toLowerCase())) {
                	tmp.add(esameComposto);
                }
            }
        }
        
        if (tmp != null && tmp.size() != 0) {
        	
        	//Ordinamento della lista tmp
        	tmp = ordinaLista(tmp,"Materia");
        }
        
        

        return tmp;
    }
    
    /**
     * Ordina una lista di esami in modo alfanumerico
     * @param list Lista di esami
     * @param type Tipo di esame
     * @return Lista di esami riordinata
     */
    public static List<Esame> ordinaLista(List<Esame> list, String type) {
    	
    	Collections.sort(list, new Comparator<Object>() {
    		@Override
    		public int compare(Object o1, Object o2) {
    			Esame e1;
    			Esame e2;
    			
				if (o1 instanceof EsameSemplice && o2 instanceof EsameSemplice) {
					e1 = (EsameSemplice) o1;
				    e2 = (EsameSemplice) o2;
				    
				    if (type.equals("Materia")) {
				    	return ((EsameSemplice) e1).getMateria().getTitolo().toLowerCase().compareTo(((EsameSemplice) e2).getMateria().getTitolo().toLowerCase());
				    } else {
				    	return ((EsameSemplice) e1).getStudente().getMatricola().toLowerCase().compareTo(((EsameSemplice) e2).getStudente().getMatricola().toLowerCase());
				    }
					
				} else if (o1 instanceof EsameSemplice && o2 instanceof EsameComposto) {
					e1 = (EsameSemplice) o1;
				    e2 = (EsameComposto) o2;
				    
					if (type.equals("Materia")) {
						return ((EsameSemplice) e1).getMateria().getTitolo().toLowerCase().compareTo(((EsameComposto) e2).getMateria().getTitolo().toLowerCase());
					} else {
						return ((EsameSemplice) e1).getStudente().getMatricola().toLowerCase().compareTo(((EsameComposto) e2).getStudente().getMatricola().toLowerCase());
					}
					
				} else if (o1 instanceof EsameComposto && o2 instanceof EsameSemplice) {	
					e1 = (EsameComposto) o1;
				    e2 = (EsameSemplice) o2;
				    
			    	if (type.equals("Materia")) {
			    		return ((EsameComposto) e1).getMateria().getTitolo().toLowerCase().compareTo(((EsameSemplice) e2).getMateria().getTitolo().toLowerCase());
				    } else {
				    	return ((EsameComposto) e1).getStudente().getMatricola().toLowerCase().compareTo(((EsameSemplice) e2).getStudente().getMatricola().toLowerCase());
				    }
					
				} else {					
					e1 = (EsameComposto) o1;
				    e2 = (EsameComposto) o2;
				    
				    if (type.equals("Materia")) {
				    	return ((EsameComposto) e1).getMateria().getTitolo().toLowerCase().compareTo(((EsameComposto) e2).getMateria().getTitolo().toLowerCase());
				    } else {
				    	return ((EsameComposto) e1).getStudente().getMatricola().toLowerCase().compareTo(((EsameComposto) e2).getStudente().getMatricola().toLowerCase());
				    }
				}
    		}
		});
    	
    	return list;
    }
    
    /**
     * Ricerca gli studenti con la matricola che rispetta il filtro di ricerca
     * 
     * @param esami Lista di esami
     * @param ricerca Stringa da ricercare
     * @return Lista di esami ottenuti dalla ricerca
     */
    public static List<Esame> searchByMatricola (List<Esame> esami, String ricerca) {
    	List<Esame> tmp = new ArrayList<>();

        for (Esame esame : esami) {
        	
            if (esame instanceof EsameSemplice) {
                EsameSemplice esameSemplice = (EsameSemplice) esame;
                if (esameSemplice.getStudente().getMatricola().toLowerCase().startsWith(ricerca.toLowerCase())) {
                	tmp.add(esameSemplice);
                }
            } else if (esame instanceof EsameComposto) {
                EsameComposto esameComposto = (EsameComposto) esame;
                if (esameComposto.getStudente().getMatricola().toLowerCase().startsWith(ricerca.toLowerCase())) {
                	tmp.add(esameComposto);
                }
            }
        }
        
        if (tmp != null && tmp.size() != 0) {
        	//Ordinamento della lista tmp
        	tmp = ordinaLista(tmp,"Studente");
        }      
        

        return tmp;
    }
}
