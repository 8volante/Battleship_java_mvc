package upo.battleship.tragno;

import java.util.ArrayList;



/**
 * Giocatore umano
 * @author Riccardo Cecci
 *
 */
public class Giocatore {
	
	protected Griglia grigliaGiocatore;
	public Griglia grigliaComputer;
	
	private ArrayList<Nave> naviDaPosizionare;
	private ArrayList<Nave> navi;
	private int[] lunghezze = {5, 3, 2};
	private String[] nomi = {"incrociatore", "classeLambda" , "cacciaTie"};
	private int turno;
	private boolean giocoPronto;
	public boolean fineTurno, finePartita;
	
	

	public Giocatore(int turno, Griglia grigliaGiocatore, Griglia grigliaComputer) {
		this.grigliaGiocatore = grigliaGiocatore;
		this.grigliaComputer = grigliaComputer;
		this.turno = turno;
		
		naviDaPosizionare = new ArrayList<Nave>();
		navi = new ArrayList<Nave>();
		Nave nave;
		for(int i = 0; i < 3; i++) {
			nave = new Nave(lunghezze[i], nomi[i]);
			naviDaPosizionare.add(nave);
		}
		
		fineTurno = false;
		finePartita = false;
		giocoPronto = false;
	}
	
	
	/**
	 * Permette al {@link Giocatore} di posizionare una {@link Nave}, dati in input la nave da 
	 * posizionare e la cella dove posizionare la sua prua
	 * @param nave
	 * @param cella
	 * @return boolean
	 * 		TRUE se la nave è stata posizionata, FALSE altrimenti
	 */
	public boolean posizionaNave(Nave nave, int x, int y) {
		boolean ret = false;
		if(naviDaPosizionare.contains(nave)) {
			nave.setVerticale(grigliaGiocatore.getVerticale());
			ret = grigliaGiocatore.posizionaNave(nave, x, y);
			naviDaPosizionare.remove(nave);
			navi.add(nave);
			if(naviDaPosizionare.isEmpty()) {
				giocoPronto = true;
			}
		}
		return ret;
				
	}
	
	/**
	 * Il {@link Giocatore} colpisce una cella nel campo dell'IA
	 * @param cella
	 * @return <b>int</b>
	 * 	<pre>-1 errore</pre>
	 *  <pre>0 vuoto</pre>
	 *  <pre>1 colpito</pre>
	 *  <pre>2 affondato</pre>
	 *  <pre>3 fine_partita</pre> 
	 */
	public int colpisci(int x, int y) {
		return grigliaComputer.colpisci(x, y);
	}

	public Griglia getCampoGiocatore() {
		return grigliaGiocatore;
	}

	public Griglia getCampoIA() {
		return grigliaComputer;
	}

	public ArrayList<Nave> getNaviDaPosizionare() {
		return naviDaPosizionare;
	}

	public ArrayList<Nave> getNavi() {
		return navi;
	}
	
	public String[] getNomeNavi() {
		return nomi;
	}

	public int getTurno() {
		return turno;
	}

	public boolean isGiocoPronto() {
		return giocoPronto;
	}
	
	/**
	 * Posiziona una nave randomicamente
	 * @return boolean
	 */
	public boolean posizionaRandom() {
		while (!naviDaPosizionare.isEmpty()) {
			// int i = 0;
			Nave nave = naviDaPosizionare.get(0);
			boolean[] vertical = new boolean[2];
			vertical[0] = true;
			vertical[1] = false;
			// giocatore_1.posizionaNave(nave, posizione, verticale);
			int x = (int) (Math.random() * 10) % 10;
			int y = (int) (Math.random() * 10) % 10;
			int z = (int) (Math.random() * 10) % 2;
			
			if (posizionaNave(nave, x, y)) {
				naviDaPosizionare.remove(nave);
			} else {
				naviDaPosizionare.add(nave);
			}
		}
		return true;
	}
	
	
}
