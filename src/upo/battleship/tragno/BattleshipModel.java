package upo.battleship.tragno;

import java.util.Observable;
public class BattleshipModel{
	
	private final static int INIZIO = 0;
	private final static int FASE_POSIZIONAMENTO_NAVI = 1;
	private final static int FASE_DI_BATTAGLIA = 2;
	private final static int FINE_PARTITA= 3;
	private final static int TURNO_GIOCATORE = 0;
	private final static int TURNO_COMPUTER = 1;
	private int faseProgramma;
	private int turno;
	private Cella cella;
	private Griglia grigliaGiocatore, grigliaComputer;
	private Giocatore giocatore;
	private Computer computer;
	private boolean orientamento;
	
	
	public BattleshipModel() {
		super();
		faseProgramma = 0;
		turno = (int) (Math.random()*10) % 2;
		grigliaComputer = new Griglia();
		grigliaGiocatore = new Griglia();
		giocatore = new Giocatore(turno, grigliaGiocatore, grigliaComputer);
		computer = new Computer(turno, grigliaGiocatore, grigliaComputer);
	}
	
	public void fasePosizionamento() {
		setFase(FASE_POSIZIONAMENTO_NAVI);
		
	}
	
	public void fineBattaglia() {
		setFase(FINE_PARTITA);
	}
	
	public void faseBattaglia() {
		setFase(FASE_DI_BATTAGLIA);
	}
	
	public void setFase(int fase) {
		this.faseProgramma = fase;
	}
	
	public int getFase() {
		return faseProgramma;
	}
	
	
	public void reset() {
		faseProgramma = 1;
		turno = (int) (Math.random()*10) % 2;
		grigliaComputer = new Griglia();
		grigliaGiocatore = new Griglia();
		giocatore = new Giocatore(turno, grigliaGiocatore, grigliaComputer);
		computer = new Computer(turno, grigliaGiocatore, grigliaComputer);
	}
	
	public Griglia getGrigliaComputer() {
		return this.grigliaComputer;
		
	}

	public Griglia getGrigliaPlayer() {
		
		return this.grigliaGiocatore;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public void setVerticale(boolean orientamento) {
		this.orientamento = orientamento;
		setVerticaleGriglia();
	}
	
	public boolean getVerticale() {
		return this.orientamento;
	}
	
	public void setVerticaleGriglia() {
		boolean orientamento = getVerticale();
		grigliaGiocatore.setVerticale(orientamento);
	}

}
