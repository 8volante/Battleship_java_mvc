package upo.battleship.tragno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BattleshipController{
	
	private BattleshipView vista;
	private BattleshipModel modello;
	private boolean orientamento; // true = verticale, false = orizzontale
	VistaGrigliaComputer vistaComputer;
	int i = 0;

	public BattleshipController(BattleshipModel m, BattleshipView v) {
		this.modello = m;
		this.vista = v;
		//this.vistaComputer = vistaComputer;
	}

	public ActionListener assegnaGestoreStartGame() {
		ActionListener gestoreStartGame;
		ArrayList<Nave> navi;
		gestoreStartGame = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				modello.fasePosizionamento();
				System.out.println("Inizio fase: " + modello.getFase());
				
			/*	GESTIONE POSIZIONAMENTO NAVI COMPUTER
				if(modello.getFase() == 1) {
					ArrayList<Nave> navi =  modello.getComputer().getNaviDaPosizionare();
					Nave nave = navi.get(i);
					
					try {
	
						boolean valuta = modello.getComputer().posizionaRandom();
						
						if(valuta == true) {
							
							vistaComputer.posizionaNave(nave, modello.getComputer().getX(), modello.getComputer().getY());
						}//fine if
						
						else if(valuta == false) {
							navi.add(nave);
							System.out.println("Posizione non valida");
						}//fine else
				
					}catch (Exception exc) {
						System.out.println("ERRORE POSIZIONAMENTO NAVE " + exc.getMessage());
					}
					finally {
						
						System.out.println("FINE POSIZIONAMENTO NAVI COMPUTER");
					}
				}*/
				
			}
		};
		return gestoreStartGame;
	}
	
	public ActionListener assegnaGestoreNewGame() {
		ActionListener gestoreNewGame;
		
		gestoreNewGame = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modello.reset();
				vista.reset();
			}
			
		};
		
		return gestoreNewGame;
	}
	
	public MouseListener assegnaGestoreVerticale() {
		MouseListener gestoreVerticale;
		
		gestoreVerticale = new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				JButton bottone = new JButton();
				bottone = (JButton) e.getSource();
				if(bottone.getText() == "VERTICALE") {
					orientamento = true;
					modello.setVerticale(orientamento);
					System.out.println("VERTICALE = " + modello.getVerticale());
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return gestoreVerticale;
	}
	
	
	public MouseListener assegnaGestoreOrizzontale() {
		MouseListener gestoreOrizzontale;
		
		gestoreOrizzontale = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton bottone = new JButton();
				bottone = (JButton) e.getSource();
				if(bottone.getText() == "ORIZZONTALE") {
					orientamento = false;
					modello.setVerticale(orientamento);
					System.out.println("VERTICALE = " + modello.getVerticale());
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return gestoreOrizzontale;
	}
	
	public boolean getOrientamento() {
		return orientamento;
	}	
}
