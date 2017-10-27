package deporte.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import deporte.controlador.ControladorBotones;
import deporte.vista.interfaz.DeporteInterfaz;
import deporte.vista.panel.Cancha;
import deporte.vista.panel.Silvato;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Component;

public class Deporte extends JFrame implements DeporteInterfaz, Runnable {

	private JPanel contentPane;
	private JPanel pnlJuego ;
	private Cancha pnlCancha;
	private JButton btnPlay ;
	private JButton btnPause;
	private Socket cliente;
	private DataInputStream input;
	
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

	public Deporte() throws IOException {
		cliente=new Socket("127.0.0.1",8000);
		init();
		Thread t=new Thread(this);
		t.start();
		
	}

	public void init(){
		setTitle("Cliente");
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
		
		pnlCancha=new Cancha(cliente,"Cliente");
		pnlJuego.add(pnlCancha,"Cancha");
		
		
		ControladorBotones ctlBotones=new ControladorBotones(this,cliente);
	
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

	@Override
	public void run() {
	// while(true){
		try {
			input=new DataInputStream(cliente.getInputStream());
			String mensaje=input.readUTF();
			if(mensaje.equals("DESCONECTAR")){
				JOptionPane.showMessageDialog(this,"Lo siento el servidor se ha detenido", "DESCONECTADO", JOptionPane.INFORMATION_MESSAGE);
				cliente.close();
				System.exit(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//}
		
	}

}
