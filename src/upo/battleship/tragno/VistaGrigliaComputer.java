package upo.battleship.tragno;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;



public class VistaGrigliaComputer extends JPanel{

	JLabel[][] celleComputer;
	private BattleshipController controllore;
	private BattleshipModel modello;
	private VistaGrigliaGiocatore vistaPlayer;
	ControllerGrigliaComputer controllerComputer;
	
	
	public VistaGrigliaComputer(BattleshipModel modello, BattleshipController controllore, VistaGrigliaGiocatore player) {
		
		this.controllore = controllore;
		this.modello = modello;
		this.vistaPlayer = player;
		
		controllerComputer = new ControllerGrigliaComputer(this, modello, controllore, player);
		
		this.setSize(100, 100);
		this.setLayout(new GridLayout(11,11));
		celleComputer = new JLabel[11][11];
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				
				if(j == 0 && i == 0 ) {
					celleComputer[i][j] = new JLabel();
					celleComputer[i][j].setPreferredSize(new Dimension(10, 10));
					celleComputer[i][j].setOpaque(true);
					//Setto il colore del label a bianco
					celleComputer[i][j].setBackground(Color.LIGHT_GRAY);
					//Aggiungo un bordo (creato prima del ciclo)
					celleComputer[i][j].setBorder(border);
				}
				
				//Condizione per saltare la prima cella e identificare le altre
				else if(i == 0 && j > 0 ) { 
					celleComputer[i][j] = new JLabel("" + j);
					celleComputer[i][j].setPreferredSize(new Dimension(10, 10));
					celleComputer[i][j].setOpaque(true);
					celleComputer[i][j].setHorizontalAlignment(JLabel.CENTER);
					celleComputer[i][j].setVerticalAlignment(JLabel.CENTER);
					//Setto il colore del label a bianco
					celleComputer[i][j].setBackground(Color.LIGHT_GRAY);
					//Aggiungo un bordo (creato prima del ciclo)
					celleComputer[i][j].setBorder(border);
				}
				//Condizione per saltare la prima cella e identificare le altre
				else if(j == 0 && i > 0 ) {
					celleComputer[i][j] = new JLabel("" + i);
					celleComputer[i][j].setPreferredSize(new Dimension(10, 10));
					celleComputer[i][j].setOpaque(true);
					celleComputer[i][j].setHorizontalAlignment(JLabel.CENTER);
					celleComputer[i][j].setVerticalAlignment(JLabel.CENTER);
					//Setto il colore del label a bianco
					celleComputer[i][j].setBackground(Color.LIGHT_GRAY);
					//Aggiungo un bordo (creato prima del ciclo)
					celleComputer[i][j].setBorder(border);
				}
				
				else {
					celleComputer[i][j] = new JLabel();
					celleComputer[i][j].addMouseListener(this.controllerComputer.assegnaGestoreCelle(i, j));
					celleComputer[i][j].setPreferredSize(new Dimension(10, 10));
					celleComputer[i][j].setOpaque(true);
					celleComputer[i][j].setHorizontalAlignment(JLabel.CENTER);
					celleComputer[i][j].setVerticalAlignment(JLabel.CENTER);
					//Setto il colore del label a bianco
					celleComputer[i][j].setBackground(Color.CYAN);
					//Aggiungo un bordo (creato prima del ciclo)
					celleComputer[i][j].setBorder(border);
				}
				
				this.add(celleComputer[i][j]);	
			}
		}
		this.setVisible(true);
	}

	
	public void setCella(int x, int y) {
		String colpito = "X";
		this.celleComputer[x][y].setText(colpito);
		
	}
	
	public JLabel getCella(int x, int y) {
		return this.celleComputer[x][y];
		
	}
	
	public void colpisciCellaPiena(int x, int y) {
		String colpito = "X";
		this.celleComputer[x][y].setText(colpito);
		this.celleComputer[x][y].setBackground(Color.YELLOW);
	}
	
	public void colpisciCellaVuota(int x, int y) {
		String colpito = "X";
		this.celleComputer[x][y].setText(colpito);
		this.celleComputer[x][y].setBackground(Color.BLUE);
	}
	
	public void colpisciNaveColpita(int x, int y) {
		String colpito = "X";
		this.celleComputer[x][y].setText(colpito);
		this.celleComputer[x][y].setBackground(Color.RED);
	}
	
	public void finePartita() {
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				if((i != 0 && j != 0) && (i != 0 && j > 0) && (j != 0 && i > 0) ) {
					this.celleComputer[i][j].setBackground(Color.BLACK);
					this.celleComputer[i][j].setText("");
				}
			}
		}
	}
	
	public void posizionaNave(Nave nave, int x, int y) {
		System.out.println("Vista");
		int lunghezza = nave.getLunghezza();
		boolean verticale = nave.getVerticale();
		
		if(verticale) {
			for( int j = 0; j < lunghezza; j++) {
				System.out.println("COLORO NAVE VERTICALE");
				celleComputer[x+j][y].setBackground(Color.GRAY);
			}
		}
		else if(!verticale) {
			for(int i = 0; i < lunghezza; i++) {
				System.out.println("COLORO NAVE ORIZZONTALE");
				celleComputer[x][y+i].setBackground(Color.GRAY);
			}
		}
	}


	public void reset() {
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				if((i != 0 && j != 0) && (i != 0 && j > 0) && (j != 0 && i > 0) ) {
					this.celleComputer[i][j].setBackground(Color.CYAN);
					this.celleComputer[i][j].setText("");
				}
			}
		}
	}
}
