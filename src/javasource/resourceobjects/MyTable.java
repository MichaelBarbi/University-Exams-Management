package javasource.resourceobjects;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import javasource.Constants;

/**
 * Esami main table
 */

public class MyTable extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Colonne della tabella
	 */
	private String[] columns;
	
	/**
	 * Righe della tabella
	 */
	private Object[][] data;
	
	/**
	 * Tabella
	 */
	private JTable table = null;
	
	/**
	 * Costruttore
	 * 
	 * @param columns Colonne
	 * @param data Data
	 */
	
	public MyTable(String[] columns, Object[][] data) {
		super();
		this.setBackground(Constants.BACKGROUND_COLOR_LIGHT_RGB);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		this.columns = columns;
		this.data = data;
		
		GridBagConstraints gbcTable = new GridBagConstraints();
		gbcTable.gridx = 0;
		gbcTable.gridy = 0;
		gbcTable.weighty = 1.0;
		gbcTable.weightx = 1.0;
		gbcTable.fill = GridBagConstraints.BOTH;
		this.setLayout(new GridBagLayout());
		
		DefaultTableModel model = new DefaultTableModel(this.data, this.columns) {
        	private static final long serialVersionUID = 1L;

			// Override del metodo isCellEditable per rendere tutte le celle non modificabili
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        
        table = new JTable(model);
		table.setRowHeight(45);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Creazione del modello dei dati per la tabella
		JScrollPane scrollPane = new JScrollPane(table);
		
		this.add(scrollPane, gbcTable);
	}

	/**
	 * @return table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table jTable
	 */
	public void setTable(JTable table) {
		this.table = table;
	}	
	

}
