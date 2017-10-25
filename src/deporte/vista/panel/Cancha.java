package deporte.vista.panel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import deporte.controlador.ControladorJugador;
import deporte.modelo.Paquete;
import deporte.vista.interfaz.CanchaInterfaz;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Cancha extends JPanel implements CanchaInterfaz, Runnable{


	//private BufferedImage jugador;
	private JLabel jugador;
	private int xJugador;
	private int yJugador;
	private Image currentFrame;
	private Rectangle cancha;
	private ControladorJugador control;
	private String type;
	
	private Socket cliente;
	private ObjectOutputStream output;

	int first=0;
	
	public Cancha(Socket cliente, String type) {

		this.cliente=cliente;
		this.type=type;
		setLayout(null);
			
		jugador= new JLabel();
		//jugador=new ImageIcon(Cancha.class.getResource("/img/logo.png")));
		jugador.setIcon(new ImageIcon(Cancha.class.getResource("/img/logo.png")));
		//jugador=new ImageIcon(Cancha.class.getResource("/img/test.png")).getImage();
		//jugador.setBackground(Color.red);
		//jugador.setOpaque(true);
			
		add(jugador);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		ImageIcon aux= new ImageIcon(Silvato.class.getResource("/img/cancha.png"));

		Image image= aux.getImage();
		int x=getWidth()/2 - image.getWidth(this)/2;
		int y=getHeight()/2-image.getHeight(this)/2;
		g.drawImage(image, x, y,this );
		
		cancha=new Rectangle(x, y, aux.getIconWidth(), aux.getIconHeight());
		
	
		if(jugador.getBounds().width==0){
			x=getWidth()/2 - 20;
			y=getHeight()/2-20;
			jugador.setBounds(x, y, 40, 40);
			if(type.equals("Cliente")){
				Thread t=new Thread(this);
				t.start();
			}

		}
		
	}

	public Rectangle getCancha(){
		return cancha;
	}
	@Override
	public int getJugadorX() {
		// TODO Auto-generated method stub
		return jugador.getX();
		//return xJugador;
	}

	@Override
	public int getJugadorY() {
		// TODO Auto-generated method stub
		return jugador.getY();
		//return yJugador;
	}

	@Override
	public void actualizar(int x, int y) throws IOException {
		
		output=new ObjectOutputStream(cliente.getOutputStream());
		Paquete paquete=new Paquete(x, y, "ACTUALIZAR");
		output.writeObject(paquete);
		jugador.setBounds(x, y, 40, 40);

	}

	@Override
	public void run() {
		control=new ControladorJugador(this);
		addKeyListener(control);

	}

	@Override
	public JLabel getJugador() {
		// TODO Auto-generated method stub
		return jugador;
	}

	@Override
	public void setCliente(Socket cliente) {
		this.cliente=cliente;
		
	}
}
