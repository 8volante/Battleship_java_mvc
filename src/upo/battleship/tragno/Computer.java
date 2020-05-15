package upo.battleship.tragno;

import java.util.ArrayList;

/**
 * Giocatore computer
 * @author Riccardo Cecci
 *
 */
public class Computer {
	
	protected Griglia grigliaGiocatore;
	public Griglia grigliaComputer;
	
	private ArrayList<Cella> bersaglio; //contiene i bersagli
	private ArrayList<Cella> cercato; //contiene celle già  cercate
	private ArrayList<Cella> posIntorno;
	private Cella[][] cellePossibili;
	private boolean secondoColpo;
	private Cella colpo;
	private Cella posVecchia;
	private Cella newPos;
	private boolean bersaglioUsato;
	private int turno;
	private ArrayList<Nave> naviDaPosizionare;
	private ArrayList<Nave> navi;
	private int[] lunghezze = {5, 3, 2};
	private String[] nomi = {"incrociatore", "classeLambda" , "cacciaTie"};
	private boolean giocoPronto;
	public boolean fineTurno, finePartita;
	int x, y, z;
	
	
	public Computer(int turno, Griglia grigliaGiocatore, Griglia grigliaComputer) {

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
	
		cellePossibili = new Cella[10][10];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				cellePossibili[i][j] = new Cella(i, j);
			}
		}
		
		bersaglioUsato = false;
		secondoColpo = false;
		bersaglio = new ArrayList<Cella>();
		cercato = new ArrayList<Cella>();
	}
	
	/**
	 * Permette all'{@link IA} di colpire una posizione randomica
	 * @param campoGiocatore
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
			x = (int) (Math.random() * 10) % 10;
			y = (int) (Math.random() * 10) % 10;
			z = (int) (Math.random() * 10) % 2;
			System.out.println(z);
			boolean orientamento = vertical[z];
			if (posizionaNave(nave, x, y, orientamento)) {
				naviDaPosizionare.remove(nave);
			}
		}
		return true;
	}

	public boolean posizionaNave(Nave nave, int x, int y, boolean orientamento) {
		if(naviDaPosizionare.isEmpty()) {
			giocoPronto = true;
		}
		
		grigliaComputer.setVerticale(orientamento);
		if(naviDaPosizionare.contains(nave)) {
			if(grigliaComputer.posizionaNave(nave, x, y)) {
				navi.add(nave);
				return true;
			}
		}
		return false;
				
	}
	
	/**
	 * Permette all'{@link Computer} di colpire una {@link Cella} casuale
	 * @param cella
	 */
	public int colpisci() {
		if(bersaglio.isEmpty()) {
			bersaglioUsato = false;
			secondoColpo = false;
			colpo = random();
			while(cercato.contains(colpo)) {
				colpo = random();
			}
		} else {
			bersaglioUsato = true;
			while(cercato.contains(colpo)) {
				colpo = bersaglio.remove(0);
			}
		}
		
		cercato.add(colpo);
		int ris = grigliaGiocatore.colpisci(colpo.getX(), colpo.getY());
		
		if (ris == -1) {
			if(cercato.contains(colpo)) {
				//GIA' CERCATO
			}
		}
			
		if(ris == 0) {
			if(cercato.contains(colpo)) {
				//CELLA VUOTA
			}
			if(bersaglio.isEmpty()) {
				bersaglio = aggiorna();
			}
		}
		
		if (ris == 1 && !secondoColpo) {
			secondoColpo = true;
			posVecchia = colpo;

			posIntorno = intorno(colpo);

			
			while (!posIntorno.isEmpty()) {
				bersaglio.add(posIntorno.remove(0));
			}
		} else if (ris == 1 && secondoColpo) {

			bersaglio.clear();

			if (colpo.getX() > posVecchia.getX()) {
				newPos = new Cella(colpo.getX() + 1, colpo.getY());
				bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);
			}

			if (colpo.getY() > posVecchia.getY()) {
				newPos = new Cella(colpo.getX(), colpo.getY() + 1);
				bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);

			}

			if (colpo.getX() < posVecchia.getX()) {
				newPos = new Cella(colpo.getX() - 1, colpo.getY());
				bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);
			}

			if (colpo.getY() < posVecchia.getY()) {
				newPos = new Cella(colpo.getX(), colpo.getY() - 1);
				bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);
			}
		}

		if (ris == 2) {
			bersaglio.clear();
			bersaglio = aggiorna();
			secondoColpo = false;
		}
		
		if(ris == 3) {
			finePartita = true;
		}
		
		return ris;
	}

	/**
	 * Sceglie una {@link Cella} casuale da colpire
	 * @return {@link Cella}
	 * <pre>cella papabile per essere colpita, inserendola alla griglia di celle possibili</pre>
	 */
	private Cella random() {
		Cella pos;
		do {
			pos = new Cella((int) (Math.random() * 10) % 10,(int) (Math.random() * 10) % 10);
			System.out.println("Cella colpita (" + pos.getX() +","+ pos.getY() + " )");
		}
		while (pos.getX() <= 1 || pos.getX() >= 10 || pos.getY() <= 1 || pos.getY() >= 10);
		return cellePossibili[pos.getX()][pos.getY()];
	}
	
	public Cella getColpo() {
		return colpo;
	}
	
	/**
	 * Aggiorna le celle possibili
	 * @return ArrayList[Cella]
	 */
	private ArrayList<Cella> aggiorna() {
		ArrayList<Cella> posizioni = new ArrayList<Cella>();
		
		int[][] stato = grigliaGiocatore.getStato(true);
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				if (stato[i][j] == 2) {
					for (int h = 0; h < intorno(cellePossibili[i][j]).size(); h++)
						
						posizioni.add(intorno(cellePossibili[i][j]).get(h));
				}
			}
		}
		return posizioni;
	}
	
	/**
	 * Una volta colpita una nave, l'{@link Computer} colpisce nei dintorni del colpo sparato precedentemente
	 * @param colpo
	 * @return ArrayList delle caselle vicine
	 */
	private ArrayList<Cella> intorno(Cella colpo) {
		ArrayList<Cella> posizioni = new ArrayList<Cella>();
		Cella new_pos = new Cella(0, 0);
		if (colpo.getX() > 1) {
			new_pos = new Cella(colpo.getX() - 1, colpo.getY());
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}

		if (colpo.getX() < 10) {
			new_pos = new Cella(colpo.getX() + 1, colpo.getY());
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}

		if (colpo.getY() < 10) {
			new_pos = new Cella(colpo.getX(), colpo.getY() + 1);
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}

		if (colpo.getY() > 1) {
			new_pos = new Cella(colpo.getX(), colpo.getY() - 1);
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}
		
		return posizioni;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public ArrayList<Nave> getNaviDaPosizionare() {
		return naviDaPosizionare;
	}

	public ArrayList<Nave> getNavi() {
		return navi;
	}
}
