package upo.battleship.tragno;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerGrigliaComputer {
	
	VistaGrigliaComputer vistaComputer;
	VistaGrigliaGiocatore vistaPlayer;
	BattleshipModel model;
	BattleshipController controllore;
	
	public ControllerGrigliaComputer(VistaGrigliaComputer vista, BattleshipModel model, BattleshipController controllore, VistaGrigliaGiocatore vista2) {
		this.model = model;
		this.vistaComputer = vista;
		this.controllore = controllore;
		this.vistaPlayer = vista2;
	}
	
	public MouseListener assegnaGestoreCelle(int x, int y) {
		MouseListener gestoreCelle;
		
		gestoreCelle = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(model.getFase() == 2 && model.getTurno() == 0) {
					
					System.out.println("CELLA COMPUTER");
					int valuta = model.getGrigliaComputer().colpisci(x, y);
					if(valuta == 0) {
						System.out.println("COLPISCO");
						vistaComputer.colpisciCellaVuota(x, y);
						System.out.println("HO COLPITO");
					}
					else if(valuta == 1) {
						vistaComputer.colpisciCellaPiena(x, y);
					}
					else if(valuta == 2) {
						vistaComputer.colpisciNaveColpita(x, y);
					}
					else if(valuta == 3) {
						System.out.println("END");
						vistaComputer.finePartita();
						model.setFase(3);
					}
					model.setTurno(1);
				}
				
				if(model.getFase() == 2 && model.getTurno() == 1) {
					
					int stato = model.getComputer().colpisci();
					if(stato == 0) {
						System.out.println("COLPISCO");
						vistaPlayer.colpisciCellaVuota(model.getComputer().getColpo().getX(), model.getComputer().getColpo().getY());
						System.out.println("ACQUA");
					}
					else if(stato == 1) {
						vistaPlayer.colpisciCellaPiena(model.getComputer().getColpo().getX(), model.getComputer().getColpo().getY());
						System.out.println("COLPITO");
					}
					else if(stato == 2) {
						vistaPlayer.colpisciNaveColpita(model.getComputer().getColpo().getX(), model.getComputer().getColpo().getY());
						System.out.println("COLPITO E AFFONDATO");
					}
					else if(stato == 3) {
						System.out.println("END");
						vistaPlayer.finePartita();
						model.setFase(3);
					}
					model.setTurno(0);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		return gestoreCelle;
	}
	
	
}
