package javasource.UI;

import java.awt.CardLayout;

import javax.swing.JPanel;

import javasource.UI.pages.AggiungiEsameComposto;
import javasource.UI.pages.AggiungiEsameHome;
import javasource.UI.pages.AggiungiEsameSemplice;
import javasource.UI.pages.Configurazioni;
import javasource.UI.pages.DocenteHome;
import javasource.UI.pages.ExamsFilter;
import javasource.UI.pages.FileConf;
import javasource.UI.pages.MateriaConf;
import javasource.UI.pages.PortaleEsami;
import javasource.UI.pages.Statistiche;
import javasource.UI.pages.StudenteConf;

/**
 * Gestore del ciclo di vita dei JPanels
 */

public class PanelsInitManager {
	
	/**
	 * Schermata home del docente
	 */
	private DocenteHome docenteHome = null;
	
	/**
	 * Portale gestione degli esami
	 */
	private PortaleEsami portaleEsami = null;
	
	/**
	 * Schermata per la selezione della tipologia di esame da inserire
	 */
	private AggiungiEsameHome aggiungiEsameHome = null;
	
	/**
	 * Schermata aggiunta esame semplice
	 */
	private AggiungiEsameSemplice aggiungiEsameSemplice = null;
	
	/**
	 * Schemrata aggiunta esame composto
	 */
	private AggiungiEsameComposto aggiungiEsameComposto = null;
	
	/**
	 * Schermata selezione filtro
	 */
	private ExamsFilter filter = null;
	
	/**
	 * Schermata delle statistiche
	 */
	private Statistiche stat = null;
	
	/**
	 * Configurazione del file degli esami
	 */
	private FileConf fileConf = null;
	
	/**
	 * Scelta di una delle configurazioni da configurare
	 */
	private Configurazioni conf = null;
	
	/**
	 * Per configurare gli studenti presenti nell'applicazione
	 */
	private StudenteConf studenteConf = null;
	
	/**
	 * Per configurare le materie presenti nell'applicazione
	 */
	private MateriaConf materieConf = null;
  
    
    /**
     * Costruttore
     */
    public PanelsInitManager() {}
    
    /**
     * Inizializza i panels utilizzati dall'applicazione 
     * 
     * @param cl Panels manager
	 * @param cont Contenitore dei pannelli
	 * @param classManager manager delle classi
     * @return Esito dell'inizializzazione
     */
    public boolean initPages(CardLayout cl, JPanel cont, ClassManager classManager) {
    	docenteHome = new DocenteHome(cl, cont, classManager);
    	portaleEsami = new PortaleEsami(cl, cont, classManager);
    	aggiungiEsameHome = new AggiungiEsameHome(cl, cont, classManager);
    	aggiungiEsameSemplice = new AggiungiEsameSemplice(cl, cont, classManager, null);
    	aggiungiEsameComposto = new AggiungiEsameComposto(cl, cont, classManager, null, false);
    	filter = new ExamsFilter(cl, cont, classManager);
    	stat = new Statistiche(cl, cont, classManager);
    	fileConf = new FileConf(cl, cont, classManager);
    	conf = new Configurazioni(cl, cont, classManager);
    	studenteConf = new StudenteConf(cl, cont, classManager);
    	materieConf = new MateriaConf(cl, cont, classManager);
        
        if (
    		docenteHome == null ||
    		portaleEsami == null || 
    		aggiungiEsameHome == null ||
    		aggiungiEsameSemplice == null || 
    		aggiungiEsameComposto == null ||
    		filter == null ||
    		stat == null ||
    		fileConf == null || 
    		conf == null || 
    		studenteConf == null ||
    		materieConf == null
        ) {
        	return false;
        }
        
        return true;
    }

	/**
	 * @return docenteHome
	 */
	public DocenteHome getDocenteHome() {
		return docenteHome;
	}

	/**
	 * @param docenteHome DocenteHome
	 */
	public void setDocenteHome(DocenteHome docenteHome) {
		this.docenteHome = docenteHome;
	}

	/**
	 * @return portaleEsami
	 */
	public PortaleEsami getPortaleEsami() {
		return portaleEsami;
	}

	/**
	 * @param portaleEsami PortaleEsami
	 */
	public void setPortaleEsami(PortaleEsami portaleEsami) {
		this.portaleEsami = portaleEsami;
	}

	/**
	 * @return aggiungiEsameHome
	 */
	public AggiungiEsameHome getAggiungiEsameHome() {
		return aggiungiEsameHome;
	}

	/**
	 * @param aggiungiEsameHome aggiungiEsameHome
	 */
	public void setAggiungiEsameHome(AggiungiEsameHome aggiungiEsameHome) {
		this.aggiungiEsameHome = aggiungiEsameHome;
	}

	/**
	 * @return aggiungiEsameSemplice
	 */
	public AggiungiEsameSemplice getAggiungiEsameSemplice() {
		return aggiungiEsameSemplice;
	}

	/**
	 * @param aggiungiEsameSemplice aggiungiEsameSemplice
	 */
	public void setAggiungiEsameSemplice(AggiungiEsameSemplice aggiungiEsameSemplice) {
		this.aggiungiEsameSemplice = aggiungiEsameSemplice;
	}

	/**
	 * @return aggiungiEsameComposto
	 */
	public AggiungiEsameComposto getAggiungiEsameComposto() {
		return aggiungiEsameComposto;
	}

	/**
	 * @param aggiungiEsameComposto aggiungiEsameComposto
	 */
	public void setAggiungiEsameComposto(AggiungiEsameComposto aggiungiEsameComposto) {
		this.aggiungiEsameComposto = aggiungiEsameComposto;
	}

	/**
	 * @return filter
	 */
	public ExamsFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter filter
	 */
	public void setFilter(ExamsFilter filter) {
		this.filter = filter;
	}

	/**
	 * @return stat
	 */
	public Statistiche getStat() {
		return stat;
	}

	/**
	 * @param stat stat
	 */
	public void setStat(Statistiche stat) {
		this.stat = stat;
	}

	/**
	 * @return fileConf
	 */
	public FileConf getFileConf() {
		return fileConf;
	}

	/**
	 * @param fileConf fileConf
	 */
	public void setFileConf(FileConf fileConf) {
		this.fileConf = fileConf;
	}

	/**
	 * @return conf
	 */
	public Configurazioni getConf() {
		return conf;
	}

	/**
	 * @param conf conf
	 */
	public void setConf(Configurazioni conf) {
		this.conf = conf;
	}

	/**
	 * @return studenteConf
	 */
	public StudenteConf getStudenteConf() {
		return studenteConf;
	}

	/**
	 * @param studenteConf studenteConf
	 */
	public void setStudenteConf(StudenteConf studenteConf) {
		this.studenteConf = studenteConf;
	}

	/**
	 * @return materieConf
	 */
	public MateriaConf getMaterieConf() {
		return materieConf;
	}

	/**
	 * @param materieConf materieConf
	 */
	public void setMaterieConf(MateriaConf materieConf) {
		this.materieConf = materieConf;
	}
    
    

}
