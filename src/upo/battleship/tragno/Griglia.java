package upo.battleship.tragno;

import java.io.Serializable;
import java.util.ArrayList;



/**
 * Griglia contenente 10x10 oggetti {@link Cella} e 2 oggetti {@link Nave}.
 * Griglia implementa Serializable per il salvataggio/caricamento della partita.
 * 
 */

public class Griglia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6428890924584908996L;
	/**
	 * La dimensione della Griglia
	 */
	private static final int DIM_GRIGLIA = 11;
	//private Cella cella;
	private Cella [][] celle;
	private ArrayList<Cella> celleLibere;
	private ArrayList<Nave> navi;
	private ArrayList<Nave> navi_colpite;
	private ArrayList<Nave> navi_affondate;
	private boolean verticale;
	
	/**
	 * Crea una Griglia con 10x10 oggetti {@link Cella} e 2 oggetti {@link Nave} da posizionare.
	 * 
	 * @param grigliaGiocatore indica se siamo nella griglia del giocatore o no.
	 */
	
	public Griglia () {
		
		celle = new Cella[DIM_GRIGLIA][DIM_GRIGLIA];
		celleLibere = new ArrayList<>();
		
		for(int i = 0; i < DIM_GRIGLIA; i++) {
			for(int j = 0; j < DIM_GRIGLIA; j++ ) {
				celle[i][j] = new Cella(i, j);
				celleLibere.add(celle[i][j]);
			}
		}
		
		navi = new ArrayList<>();
		navi_colpite = new ArrayList<>();
		navi_affondate = new ArrayList<>();
	}
	
	
	/**
	 * Posiziona una nave nella griglia.
	 * @param nave
	 * @param x
	 * @param y
	 * @param verticale
	 * @return
	 */

	public boolean posizionaNave(Nave nave, int x, int y) {
		
		int lunghezza = nave.getLunghezza();
		
		if(!verificaCoordinate(x, y)) {
			return false;
		}
		
		if(!getVerticale()) {
			if(x + lunghezza > DIM_GRIGLIA) {
				return false;
			}
			for(int i = 0; i < lunghezza; i++) {
				if(!celleLibere.contains(celle[x+i][y]))
					return false;
				if(!verificaNaveVicina(x+i, y, i))
					return false;
				celle[x+i][y].assegnaNave(nave);
				celleLibere.remove(celle[x+i][y]);
			}
		}
		else {
			if(y + lunghezza > DIM_GRIGLIA) {
				return false;
			}
			for(int i = 0; i < lunghezza; i++) {
				if(!celleLibere.contains(celle[x][y+i]))
					return false;
				if(!verificaNaveVicina(x, y+i, i))
					return false;
				celle[x][y+i].assegnaNave(nave);
				celleLibere.remove(celle[x][y+i]);
			}
		}
		navi.add(nave);
		return true;
	}
	
	/**
	 * Colpisci una cella.
	 * @param x della {@link Cella} da colpire
	 * @param y della {@link Cella} da colpire
	 * @return -1 errore, 0 acqua, 1 colpita, 2 affondata, 3 fine partita
	 */
	
	public int colpisci (int x, int y) {
		
		int ret = 0;
		int stato_cella = celle[x][y].colpisci();
		
		if(stato_cella < 0)
			return -1;
		else if (stato_cella == 2) {
			if(!(navi_colpite.contains(celle[x][y].getNave()))) {
				navi.remove(celle[x][y].getNave());
				navi_colpite.add(celle[x][y].getNave());
			}
			ret = 1;
		}
		else if(stato_cella == 3) {
			if(!(navi_affondate.contains(celle[x][y].getNave()))) {
				navi_colpite.remove(celle[x][y].getNave());
				navi_affondate.add(celle[x][y].getNave());
			}
			ret = 2;
		}
		if(navi.isEmpty() && navi_colpite.isEmpty())
			ret = 3;
		
		return ret;
	}
	
	/**
	 * Ottiene lo stato del campo. Se cpu == TRUE non sono visualizzate le
	 * navi non colpite.
	 * 0 = niente. 1 = colpo vuoto. 2 = colpito. 3 = affondato. -1 = HIDDEN
	 * @param cpu
	 * @return
	 */
	
	public int[][] getStato(boolean cpu){
		
		int stato = 0;
		int [][] stato_griglia = new int [DIM_GRIGLIA][DIM_GRIGLIA];
		
		for(int i = 0; i < DIM_GRIGLIA; i++) {
			for(int j = 0; j < DIM_GRIGLIA; j++) {
				if(!cpu) {
					stato = celle[i][j].getStato();
					stato_griglia[i][j] = stato;
				}
				else {
					stato = celle[i][j].getStato();
					if (stato == 0 && !celle[i][j].getLibera())
						stato_griglia[i][j] = -1;
					else
						stato_griglia[i][j] = stato;
				}
			}
		}
		return stato_griglia;
	}

	/**
	 * Ritorna una griglia con il posizionamento delle navi
	 * @return stato, array bidimensionale di booleani
	 */
/*	public boolean[][] getStatoPosizionamento() {
		boolean [][] stato;
		stato = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				stato[i][j] = celle[i][j].getVerticale();
			}
		}
		return stato;
	}
	*/
	/**
	 * Valuta tutte le celle intorno alla cella selezionata e ripete per ogni cella
	 * @param x
	 * @param y
	 * @param k
	 * @return
	 */
	public boolean verificaNaveVicina(int x, int y, int k) {
		
		int i = x;
		int j = y;
		
		Cella cella = getCella(i, j);
		Cella cella1 = getCella(i-1, j);
		Cella cella2 = getCella(i-1, j-1);
		Cella cella3 = getCella(i, j-1);
		Cella cella4 = getCella(i+1, j-1);
		Cella cella5 = getCella(i+1, j);
		Cella cella6 = getCella(i+1, j+1);
		Cella cella7 = getCella(i, j+1);
		Cella cella8 = getCella(i-1, j+1);
		
		if(!getVerticale()) {
			
			if(k == 0) {
				
				if(verificaCoordinate(i, j) && cella.getLibera() &&
				   verificaCoordinate(i-1, j) && cella1.getLibera() &&
				   verificaCoordinate(i-1, j-1) && cella2.getLibera() &&
				   verificaCoordinate(i, j-1) && cella3.getLibera() &&
				   verificaCoordinate(i+1, j-1) && cella4.getLibera() &&
				   verificaCoordinate(i+1, j) && cella5.getLibera() &&
				   verificaCoordinate(i+1, j+1) && cella6.getLibera() &&
				   verificaCoordinate(i, j+1) && cella7.getLibera() &&
				   verificaCoordinate(i-1, j+1) && cella8.getLibera()) {
					return true;
				}
			}
			else{
				if(verificaCoordinate(i, j) && cella.getLibera() &&
				   verificaCoordinate(i-1, j-1) && cella2.getLibera() &&
				   verificaCoordinate(i, j-1) && cella3.getLibera() &&
				   verificaCoordinate(i+1, j-1) && cella4.getLibera() &&
				   verificaCoordinate(i+1, j) && cella5.getLibera() &&
				   verificaCoordinate(i+1, j+1) && cella6.getLibera() &&
				   verificaCoordinate(i, j+1) && cella7.getLibera() &&
				   verificaCoordinate(i-1, j+1) && cella8.getLibera()) {
					return true;
				}
			}
		}
		else {
			if(k == 0) {
				
				if(verificaCoordinate(i, j) && cella.getLibera() &&
				   verificaCoordinate(i-1, j) && cella1.getLibera() &&
				   verificaCoordinate(i-1, j-1) && cella2.getLibera() &&
				   verificaCoordinate(i, j-1) && cella3.getLibera() &&
				   verificaCoordinate(i+1, j-1) && cella4.getLibera() &&
				   verificaCoordinate(i+1, j) && cella5.getLibera() &&
				   verificaCoordinate(i+1, j+1) && cella6.getLibera() &&
				   verificaCoordinate(i, j+1) && cella7.getLibera() &&
				   verificaCoordinate(i-1, j+1) && cella8.getLibera()) {
					return true;
				}
			}
			else{
				if(verificaCoordinate(i, j) && cella.getLibera() &&
				   verificaCoordinate(i-1, j) && cella1.getLibera() &&
				   verificaCoordinate(i-1, j-1) && cella2.getLibera() &&
				   verificaCoordinate(i+1, j-1) && cella4.getLibera() &&
				   verificaCoordinate(i+1, j) && cella5.getLibera() &&
				   verificaCoordinate(i+1, j+1) && cella6.getLibera() &&
				   verificaCoordinate(i, j+1) && cella7.getLibera() &&
				   verificaCoordinate(i-1, j+1) && cella8.getLibera()) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	
	public boolean verificaCoordinate(int x, int y) {
		
		return(x > 0 && x < DIM_GRIGLIA && y > 0 && y < DIM_GRIGLIA);
	}
	
	public Cella getCella(int x, int y) {
		return celle[x][y];
	}
	
	/**
	 * 
	 * @return navi non ancora colpita
	 */
	public ArrayList<Nave> getNavi() {
		return navi;
	}
	
	/**
	 * 
	 * @return navi colpita
	 */
	
	public ArrayList<Nave> getNaviColpite() {
		return navi_colpite;
	}
	
	/**
	 * 
	 * @return navi affondate
	 */
	
	public ArrayList<Nave> getNaviAffondate() {
		return navi_affondate;
	}


	public void setVerticale(boolean orientamento) {
		this.verticale = orientamento;
		
	}
	
	public boolean getVerticale() {
		return verticale;
	}

	
/*
	public boolean isPosizioneNaveBloccata() {
		return posizioneNaveBloccata;
	}
	
	public void setPosizioneNaveBloccata(boolean posizioneNaveBloccata) {
		this.posizioneNaveBloccata = posizioneNaveBloccata;
		
	}


	public Cella getCella(int x, int y) {
		return celle[x][y];
	}
	
	
	private Nave trovaNavePerTipo(Nave.Tipo tipo) {
		for(Nave n: navi) {
			if(n.getTipo() == tipo)
				return n;
		}
		return null;
	}

	public void setNavi_colpite(ArrayList<Nave> navi_colpite) {
		this.navi_colpite = navi_colpite;
	}

	public void setNavi_affondate(ArrayList<Nave> navi_affondate) {
		this.navi_affondate = navi_affondate;
	}


	public void setNavi(ArrayList<Nave> navi) {
		this.navi = navi;
	}
*/
	
}
