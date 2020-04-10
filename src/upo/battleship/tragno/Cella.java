package upo.battleship.tragno;

import java.io.Serializable;


//import java.util.ArrayList;

//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;


/**
 * 	
 * Una Cella della {@link Griglia} che può contenere una {@link Nave} 
 * e può essere colpita dal player o dalla CPU. * 
 *
 */

public class Cella implements Serializable{
	
	private int x, y;
	private boolean libera;
	private int stato; // 0 = niente, 1 = cercato, 2 = colpito, 3 = affondato
	private Nave nave;
	
	public Cella(int x, int y) {
		this.x = x;
		this.y = y;
		this.libera = true;
		this.nave = null;
		this.stato = 0;
	}
	
	/**
	 * 
	 * @param nave
	 * 
	 * @return boolean
	 * <pre>TRUE se la nave è stata assegnata. FALSE altrimenti</pre>
	 */
	
	public boolean assegnaNave(Nave nave) {
		boolean ret = true;
		this.nave = nave;
		libera = false;
		return ret;
	}
	
	/**
	 * Colpisci la cella, se c'è la nave colpisce anche la nave, se no 
	 * segna la cella come "guardata" 
	 * 
	 * @return int
	 * <pre> -1 errore
	 *  0 acqua
	 *  1 colpita
	 *  2 affondata
	 *  3 fine partita</pre>
	 */
	
	public int colpisci() {
		int ret = 0;
		if (stato == 1 || stato == 2 || stato == 3) {
			return -1;
			//ECCEZIONE
		}
		stato = 1;
		ret = stato;
		if (!libera) {
			nave.colpisci();
			stato = nave.getStato();
			ret = stato;
		}
		return ret;
	}
	
	/**
	 * 
	 * @return int
	 * <pre>Stato della nave contenuta nella cella</pre>
	 */
	
	public int getStato() {
		if (nave != null && nave.getStato() == 3)
			stato = nave.getStato();
		
		return stato;
	}
	/**
	 * 
	 * @return boolean
	 * <pre>TRUE se la cella è libera, FALSE altrimenti</pre>
	 */
	public boolean getLibera() {
		return libera;
	}
	
	/**
	 * 
	 * @return {@link Nave}
	 */
	public Nave getNave() {
		return nave;
	}
	
	/*
	public void setVerticale(boolean verticale) {
		this.verticale = verticale;
	}
	*/
	/**
	 * 
	 * @return boolean
	 * <pre>TRUE se il parametro <i>verticale</i> è settato a true</pre>
	 *//*
	public boolean getVerticale() {
		return verticale;
	}
	*/
	/**
	 * 
	 * @return int
	 * <pre>La X della cella</pre>
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return int
	 * <pre>La Y della cella</pre>
	 */
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
/*
	public boolean isColpita() {
		return colpita;
	}

	public void setColpita(boolean b) {
		if(nave != null)
			nave.colpoSubito();
		colpita = b;
	}
	
	public boolean colpita() {
		colpita = true;
		if(nave != null) {
			nave.colpoSubito();
			return true;
		}
		return false;
	}
	
	public void aggiorna(boolean colpo, Nave naveAffondata) {
        this.colpita = true;
        if (this.stato == Stato.SCONOSCIUTO) {
            this.stato = (colpo) ? Stato.PIENA : Stato.VUOTA;
        } else if (this.nave != null) {
            nave.colpoSubito();
        }
        if (this.nave == null) {
            this.nave = naveAffondata;
        }
        cambioTurno();
    }

	public enum Stato{
		PIENA, VUOTA, SCONOSCIUTO
	}
	
	public boolean isNave() {
		return (nave != null);
	}

	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.nave = nave;
		this.stato = Stato.PIENA;
	}
	
	public void addChangeListener(ChangeListener listener) {
        changeListeners.add(listener);
    }
	
	public void cambioTurno() {
		ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : changeListeners) {
        	listener.stateChanged(event);
        }
	}
*/
}
