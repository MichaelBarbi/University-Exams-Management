package javasource.resourceobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JTable;

/**
 * Rappresenta lo strumento che permette di stampare la tabella degli esami
 */

public class TablePrinter implements Printable {
	
	/**
	 * Tabella degli esami
	 */
	private JTable esamiTable;
	
	/**
	 * Costruttore
	 * @param esamiTable JTable
	 */
	public TablePrinter(JTable esamiTable) {
		this.esamiTable = esamiTable;
	}
	
	/**
	 * Stabilisce come disegnare la lista degli esami sulla pagina per la stampa
	 */
	@Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        this.esamiTable.print(g2d);
        return Printable.PAGE_EXISTS;
    }

	/**
	 * Prepara e avvia la stampa degli esami
	 */
 	public void printTable() {
	    //Stampa i dati della tabella nella console
	    for (int row = 0; row < esamiTable.getRowCount(); row++) {
	        for (int col = 0; col < esamiTable.getColumnCount(); col++) {
	            Object value = esamiTable.getValueAt(row, col);
	            System.out.print(value + "\t");
	        }
	        System.out.println();
	    }

	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(this);

	    boolean doPrint = job.printDialog();
	    if (doPrint) {
	        try {
	            job.print();
	        } catch (PrinterException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}
