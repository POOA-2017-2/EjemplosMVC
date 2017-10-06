package deporte.vista.panel;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import deporte.controlador.ControladorJugador;
import deporte.vista.interfaz.CanchaInterfaz;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Cancha extends JPanel implements CanchaInterfaz, Runnable{


	private JLabel jugador;
	private Rectangle cancha;
	
	public Cancha() {

		setLayout(null);
			
		jugador= new JLabel();
		jugador.setIcon(new ImageIcon(Cancha.class.getResource("/img/logo.png")));
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
			Thread t=new Thread(this);
			t.start();
		}
		
	}

	public Rectangle getCancha(){
		return cancha;
	}
	@Override
	public int getJugadorX() {
		// TODO Auto-generated method stub
		return jugador.getX();
	}

	@Override
	public int getJugadorY() {
		// TODO Auto-generated method stub
		return jugador.getY();
	}

	@Override
	public void actualizar(int x, int y) {
		// TODO Auto-generated method stub
		jugador.setBounds(x, y, 40, 40);
	}

	@Override
	public void run() {
		ControladorJugador control=new ControladorJugador(this);
		addKeyListener(control);

	}
}
