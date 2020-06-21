package upo.battleship.cecciTragno;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
/**
 * Controller del pattern MVC
 */
public class BattleshipController{
	
	private BattleshipView vista;
	private BattleshipModel modello;
	private boolean orientamento; // true = verticale, false = orizzontale
	VistaGrigliaComputer vistaComputer;
	@SuppressWarnings("unused")
	private PopUp popUp;

	public BattleshipController(BattleshipModel m, BattleshipView v) {
		this.modello = m;
		this.vista = v;
		modello.fasePosizionamento();
		modello.getComputer().posizionaRandom();
		popUp = new PopUp("LA FLOTTA IMPERIALE SI E' DISPOSTA");
	}
	

	public ActionListener assegnaGestoreNewGame() {
		// Classe Locale
		ActionListener gestoreNewGame = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modello.reset();
				vista.reset();
				modello.getComputer().posizionaRandom();
				popUp = new PopUp("LA FLOTTA IMPERIALE SI E' DISPOSTA");
			}
		};
		return gestoreNewGame;
	}
	
	public MouseListener assegnaGestoreVerticale() {
		
		MouseListener gestoreVerticale = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton bottone = new JButton();
				bottone = (JButton) e.getSource();
				if(bottone.getText() == "VERTICALE") {
					orientamento = true;
					modello.setVerticale(orientamento);
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
		
		MouseListener gestoreOrizzontale = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton bottone = new JButton();
				bottone = (JButton) e.getSource();
				if(bottone.getText() == "ORIZZONTALE") {
					orientamento = false;
					modello.setVerticale(orientamento);
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
