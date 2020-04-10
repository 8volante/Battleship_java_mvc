package upo.battleship.tragno;

//import java.util.ArrayList;

/**
 * Nave utilizzata nel gioco
 * @author Riccardo Cecci
 *
 */

public class Nave {
	
	private int lunghezza;
	private String nome;
	private boolean colpita;
	private boolean affondata;
	private boolean verticale;
	private int vita;
	
	
	
	
	public Nave(int lunghezza, String nome) {
		this.colpita = false;
		this.affondata = false;
		this.verticale = false;
		this.lunghezza = lunghezza;
		this.vita = lunghezza;
		this.nome = nome;
		
	}
	
	/**
	 * Assegna lo stato ad una nave (colpita, affondata)
	 * @return int
	 * <pre>Stato della nave</pre>
	 */
	public int getStato() {
		int stato = -1;
		if(colpita && !affondata) {
			stato = 2;
		}
		else if(affondata) {
			stato = 3;
		}
		return stato;
	}
	/**
	 * Ritorna la lunghezza della nave
	 * @return int
	 * <pre>La lunghezza della nave</pre>
	 */
	public int getLunghezza() {
		return lunghezza;
	}

	/**
	 * 
	 * @return boolean
	 * <pre>TRUE se la nave è verticale, FALSE altrimenti</pre>
	 */
	public boolean getVerticale() {
		return verticale;
	}

	public void setVerticale(boolean orientamento) {
		this.verticale = orientamento;
	}

	
	/**
	 * Diminuisce di 1 la vita della nave
	 */ 
	public void colpisci() {
		vita--;
		colpita = true;
		if(vita == 0) {
			affondata = true;
		}
		else affondata = false;
	}

	/**
	 * Ritorna il nome della nave
	 * @return String
	 * <pre>Il nome della nave</pre>
	 */
	public String getNome() {
		return nome;
	}
	
	/*
	 * Rimuove la cella della nave
	 
	public void cancellaCella() {
		this.celle.clear();
	}*/
	
	
	/*
	 * Ritorna la prua della nave
	 * @return {@link Cella} dove è contenuta la prua
	 
	public Cella getPruaNave() {
		
		Cella prua = celle.get(0);
		Cella top = new Cella(prua.getX(), prua.getY(), true); //= {prua.getX(), prua.getY()};
		for(int i = 1; i < celle.size(); i++) {
			Cella c = celle.get(i);
			if((c.getX() < top.getX()) && (c.getY() < top.getY())) {
				top.setX(c.getX());
				top.setY(c.getY());
				
			}
			
		}
		return top;
	}*/
	
	/*public void updateSquareReferences(Griglia griglia) {
        ArrayList<Cella> newCella = new ArrayList<>();
        for (Cella s : newCella) {
            newCella.add(griglia.getCella(s.getX(), s.getY()));
        }
        this.celle = newCella;
    }*/
	
	
	/*
	 * Contiene i tipi di nave
	 * @author Riccardo Cecci
	 *
	 
	public enum Tipo {

		PORTAEREI(5, "portaerei"),
		
		SOTTOMARINO(3, "sottomarino"),
		
		CACCIATORPEDINIERE(2, "cacciatorpediniere");
		
		private int length;
		private String nome;
		
		Tipo(int length, String nome){
			this.length = length;
			this.nome = nome;
		}
		
		public String getNome() {
			return nome;
		}
	}*/
}
