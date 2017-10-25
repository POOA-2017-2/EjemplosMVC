package deporte.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import deporte.controlador.ClienteConexion;
import deporte.controlador.ControladorBotones;
import deporte.vista.interfaz.DeporteInterfaz;
import deporte.vista.panel.Cancha;
import deporte.vista.panel.Silvato;

public class DeporteServidor extends JFrame implements DeporteInterfaz,Runnable {


	private JPanel contentPane;
	private JPanel pnlJuego ;
	private Cancha pnlCancha;
	private ServerSocket servidor;
	private Socket cliente;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeporteServidor frame = new DeporteServidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeporteServidor() throws IOException{

		servidor=new ServerSocket(8000);
		init(); // metodo que construye la interfaz
		Thread t=new Thread(this);
		t.start();
		
	}

	public void init(){
		
		setTitle("SERVIDOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int alto=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int hv=550;
		int wv=750;
		int x= (ancho/2) - (wv/2);
		int y= (alto/2) - (hv/2);
		setBounds(x, y, wv, hv);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pnlJuego = new JPanel();
		contentPane.add(pnlJuego, BorderLayout.CENTER);
		pnlJuego.setLayout(new CardLayout(0, 0));
		
		Silvato pnlPresentacion=new Silvato();
		pnlJuego.add(pnlPresentacion,"Silbato");
		
		pnlCancha=new Cancha(cliente,"Servidor");
		pnlJuego.add(pnlCancha,"Cancha");
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
		//btnPlay.setFocusable(focus);
		
	}

	@Override
	public void setFocusablePause(boolean focus) {
		//btnPause.setFocusable(focus);	
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
		while(true){
			try {
				cliente=servidor.accept();
				pnlCancha.setCliente(cliente);
				ClienteConexion cc=new ClienteConexion(this,cliente);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
