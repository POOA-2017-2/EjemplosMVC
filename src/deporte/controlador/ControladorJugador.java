package deporte.controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import deporte.modelo.Jugador;
import deporte.vista.interfaz.CanchaInterfaz;

public class ControladorJugador extends KeyAdapter {

	private Jugador jug;
	private CanchaInterfaz vista;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	public ControladorJugador(CanchaInterfaz vista) {
		this.vista=vista;
		jug=new Jugador(vista.getJugadorX(), vista.getJugadorY());
	}

	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		//System.out.println(e.getKeyText(code));
		switch(code){
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		}
		jug=jug.mover(vista.getCancha(),left, right, up, down);
		try {
			vista.actualizar(jug.getX(),jug.getY());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void keyReleased(KeyEvent e){
		int code=e.getKeyCode();
		switch(code){
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		}
		jug=jug.mover(vista.getCancha(),left, right, up, down);
		try {
			vista.actualizar(jug.getX(),jug.getY());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
