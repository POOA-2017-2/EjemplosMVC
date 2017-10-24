package deporte.vista.panel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import deporte.controlador.ControladorJugador;
import deporte.vista.interfaz.CanchaInterfaz;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Cancha extends JPanel implements CanchaInterfaz, Runnable{


	//private BufferedImage jugador;
	private JLabel jugador;
	private int xJugador;
	private int yJugador;
	private Image currentFrame;
	private Rectangle cancha;
	private ControladorJugador control;
	int first=0;
	
	public Cancha() {

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
			Thread t=new Thread(this);
			t.start();
		}
		
		/*
		Image j=null;
		if(first==0){
			xJugador=getWidth()/2 - 20;
			yJugador=getHeight()/2-20;
			first++;
			j=jugador.getSubimage(0, 0, 32, 32);
			Thread t=new Thread(this);
			control=new ControladorJugador(this);
			addKeyListener(control);
			//t.start();
		}
		else{
			j=currentFrame;
			System.out.println("entra");
		}
	
		g.drawImage(j, xJugador, yJugador,this);	
		*/

		
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
	public void actualizar(int x, int y) {
		// TODO Auto-generated method stub
		//jugador.
		jugador.setBounds(x, y, 40, 40);
		//xJugador=x;
		//yJugador=y;
		//this.currentFrame=imagen;
		//repaint();
	}

	@Override
	public void run() {
		control=new ControladorJugador(this);
		addKeyListener(control);

	}
}
