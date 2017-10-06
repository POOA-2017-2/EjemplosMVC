package deporte.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import deporte.controlador.ControladorBotones;
import deporte.vista.interfaz.DeporteInterfaz;
import deporte.vista.jdialog.AboutDialog;
import deporte.vista.panel.Cancha;
import deporte.vista.panel.Silvato;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class Deporte extends JFrame implements DeporteInterfaz {

	private JPanel contentPane;
	private JPanel pnlJuego ;
	private Cancha pnlCancha;
	private JButton btnPlay ;
	private JButton btnPause;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deporte frame = new Deporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Deporte() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int alto=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int hv=550;
		int wv=750;
		int x= (ancho/2) - (wv/2);
		int y= (alto/2) - (hv/2);
		setBounds(x, y, wv, hv);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.setIcon(new ImageIcon(Deporte.class.getResource("/img/hucha.png")));
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		
		mnArchivo.add(mntmGuardar);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de ...");
		mnAyuda.add(mntmAcercaDe);
		
		JMenuItem mntmReglas = new JMenuItem("Reglas");
		mnAyuda.add(mntmReglas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBotones = new JPanel();
		contentPane.add(pnlBotones, BorderLayout.NORTH);
		
		btnPlay = new JButton("Play");

		pnlBotones.add(btnPlay);
		
		btnPause = new JButton("Pause");
		pnlBotones.add(btnPause);
		
		pnlJuego = new JPanel();
		contentPane.add(pnlJuego, BorderLayout.CENTER);
		pnlJuego.setLayout(new CardLayout(0, 0));
		
		Silvato pnlPresentacion=new Silvato();
		pnlJuego.add(pnlPresentacion,"Silbato");
		
		pnlCancha=new Cancha();
		pnlJuego.add(pnlCancha,"Cancha");
		
		
		ControladorBotones ctlBotones=new ControladorBotones(this);
	
		btnPlay.setActionCommand("play");
		btnPlay.addActionListener(ctlBotones);
		
		mntmGuardar.setActionCommand("save");
		mntmGuardar.addActionListener(ctlBotones);
		
		mntmAcercaDe.setActionCommand("acerca");
		mntmAcercaDe.addActionListener(ctlBotones);
	}

	@Override
	public Component getWriteObject() {
		return pnlCancha;
	}

	@Override
	public Component getComponentShow() {
		// TODO Auto-generated method stub
		return pnlCancha;
	}

	/*
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void setFocusablePlay(boolean focus) {
		btnPlay.setFocusable(focus);
		
	}

	@Override
	public void setFocusablePause(boolean focus) {
		btnPause.setFocusable(focus);	
	}

	@Override
	public void setFocusableCancha(boolean focus) {
		pnlCancha.setFocusable(focus);
	}

	@Override
	public void showCancha() {
		CardLayout c= (CardLayout)pnlJuego.getLayout();
		c.show(pnlJuego, "Cancha");
		
	}

}
