package upo.battleship.tragno;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * Definisce gli elementi grafici che compongono la vista
 * @author Andrea Tragno
 *
 */

public class BattleshipView extends JFrame{
	
	private JMenuBar barraMenu;
	private JMenu menu;
	private JMenuItem avvioPartita, nuovaPartita, salvaPartita, caricaPartita;
	private JPanel areaDiGioco, areaBottoni, areaNavi;
	private Border bordo = BorderFactory.createLineBorder(Color.RED, 10);
	private VistaGrigliaGiocatore grigliaGiocatore;
	private VistaGrigliaComputer grigliaComputer;
	private JButton verticale, orizzontale;
	private JLabel computer, player;
	private BattleshipModel modello;
	private BattleshipController controllore;

	
	
	public BattleshipView(String titolo, BattleshipModel modello) {
		
		super.setLayout(new BorderLayout());
		this.modello = modello;
		this.controllore = new BattleshipController(modello, this);
	
		
		setTitle(titolo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 900);
		setLocationRelativeTo(null);
		setBackground(Color.LIGHT_GRAY);
		
		/*MENU*/
		barraMenu = new JMenuBar();
		menu = new JMenu("Menù");
		
		avvioPartita = new JMenuItem("Avvio Partita");
		//aggiungo un listener per il button "Avvio Partita"
		avvioPartita.addActionListener(controllore.assegnaGestoreStartGame());
		menu.add(avvioPartita);
		
		nuovaPartita = new JMenuItem("Nuova Partita");
		//aggiungo un listener per il button "Nuova Partita"
		nuovaPartita.addActionListener(controllore.assegnaGestoreNewGame());
		menu.add(nuovaPartita);
		
		salvaPartita = new JMenuItem("Salva Partita");
		menu.add(salvaPartita);
		
		caricaPartita = new JMenuItem("Carica Partita");
		menu.add(caricaPartita);
		
		barraMenu.add(menu);
		setJMenuBar(barraMenu);
		
		/*AREA DI GIOCO*/
		areaDiGioco = new JPanel();
		areaDiGioco.setLayout(new BoxLayout(areaDiGioco, BoxLayout.Y_AXIS));
		areaDiGioco.setBackground(Color.LIGHT_GRAY);
		areaDiGioco.setBorder(bordo);
		/*Griglie*/
		grigliaComputer = new VistaGrigliaComputer(this.modello, this.controllore);
		computer = new JLabel("IMPERO                ");
		areaDiGioco.add(computer);
		areaDiGioco.add(grigliaComputer);
		grigliaGiocatore = new VistaGrigliaGiocatore(this.modello, this.controllore);
		player = new JLabel("RIBELLIONE              ");
		areaDiGioco.add(player);
		areaDiGioco.add(grigliaGiocatore);
		add(areaDiGioco, BorderLayout.CENTER);
		
		
		/*AREA BOTTONI ORIENTAMENTO*/
		areaBottoni = new JPanel();
		areaBottoni.setLayout(new FlowLayout());
		areaBottoni.setBackground(Color.LIGHT_GRAY);
		areaBottoni.setBorder(bordo);
		/*Bottoni*/
		orizzontale = new JButton("ORIZZONTALE");
		//aggiungo un listener per il button "orizzontale"
		orizzontale.addMouseListener(controllore.assegnaGestoreOrizzontale());
		areaBottoni.add(orizzontale);
		verticale = new JButton("VERTICALE");
		//aggiungo un listener per il button "verticale"
		verticale.addMouseListener(controllore.assegnaGestoreVerticale());
		areaBottoni.add(verticale);
		add(areaBottoni, BorderLayout.PAGE_END);
		
		/*PANNELLO LATERALE PER CONTEGGIO NAVI???
		areaNavi = new JPanel();
		areaNavi.setLayout(new BoxLayout(areaNavi, BoxLayout.Y_AXIS));
		areaNavi.setBackground(Color.LIGHT_GRAY);
		//areaNavi.setBorder(bordo);
		JLabel navi = new JLabel("NAVI GIOCATORE");
		areaNavi.add(navi);
		add(areaNavi, BorderLayout.LINE_END);
	*/
	
		setVisible(true);
	}


	public VistaGrigliaGiocatore getGrigliaGiocatore() {
		return grigliaGiocatore;
	}


	public void setGrigliaGiocatore() {
		this.grigliaGiocatore = new VistaGrigliaGiocatore(this.modello, this.controllore);
	}


	public VistaGrigliaComputer getGrigliaComputer() {
		return grigliaComputer;
	}


	public void setGrigliaComputer(VistaGrigliaGiocatore grigliaComputer) {
		this.grigliaComputer =  new VistaGrigliaComputer(this.modello, this.controllore);
	}


	public JMenuItem getNuovaPartita() {
		return this.avvioPartita;
	}


	public void setNuovaPartita(JMenuItem nuovaPartita) {
		this.avvioPartita = nuovaPartita;
	}


	public JButton getVerticale() {
		return verticale;
	}


	public void setVerticale(JButton verticale) {
		this.verticale = verticale;
	}


	public JButton getOrizzontale() {
		return orizzontale;
	}


	public void setOrizzontale(JButton orizzontale) {
		this.orizzontale = orizzontale;
	}


	public void reset() {
		grigliaComputer.reset();
		grigliaGiocatore.reset();
	}
	
	
}

