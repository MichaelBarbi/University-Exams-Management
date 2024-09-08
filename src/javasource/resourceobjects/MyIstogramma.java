package javasource.resourceobjects;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Rappresenta l'istogramma utilizzato per le statistiche
 */
public class MyIstogramma  extends JPanel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Dataset
	 */
	private DefaultCategoryDataset dataset;
	
	/**
	 * chart
	 */
	private JFreeChart chart;
	
	/**
	 * ChartPanel
	 */
	private ChartPanel chartPanel;
	
	/**
	 * Costruttore 
	 * @param dataset Dataset
	 */
	public MyIstogramma(DefaultCategoryDataset dataset) {
		
		if (dataset == null) { 
			//Preparo un dataset di default
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			data.addValue(10, "Category 1", "Type 1");
			data.addValue(15, "Category 1", "Type 2");
			data.addValue(20, "Category 2", "Type 1");
			data.addValue(25, "Category 2", "Type 2");
			this.dataset = data;
		} else {
			this.dataset = dataset;
		}
		
		//Creo l'istogramma
		this.chart = ChartFactory.createBarChart(
                "Istogramma dei voti",
                "Voti",
                "Quantità",
                this.dataset
        );
		
		//Definisco il panel builtin per includere il grafico
		this.chartPanel = new ChartPanel(this.chart);
		this.chartPanel.setPreferredSize(new Dimension(600, 400));
		
		this.add(chartPanel);
	}
	
	/**
	 * Aggiorna lo schema con il nuovo dataset
	 * @param  newDataset Dataset
	 */
	public void updateDataset(DefaultCategoryDataset newDataset) {
		// Rimuovi il dataset corrente
	    this.chart.getCategoryPlot().setDataset(newDataset);
	}

}
