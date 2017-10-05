package deporte.vista.panel;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import deporte.controlador.ControladorJugador;
import deporte.vista.interfaz.CanchaInterfaz;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Cancha extends JPanel implements CanchaInterfaz{


	private JLabel jugador;
	
	public Cancha() {

		setLayout(null);
			
		jugador= new JLabel();
		jugador.setIcon(new ImageIcon(Cancha.class.getResource("/img/logo.png")));
		//jugador.setBackground(Color.red);
		//jugador.setOpaque(true);
		jugador.setBounds(10, 10, 40, 40);
		
		add(jugador);
		
		ControladorJugador control=new ControladorJugador(this);
		addKeyListener(control);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image imagen= new ImageIcon(Silvato.class.getResource("/img/cancha.png")).getImage();
		int x=getWidth()/2 - imagen.getWidth(this)/2;
		int y=getHeight()/2-imagen.getHeight(this)/2;
		g.drawImage(imagen, x, y,this );
		
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
}
