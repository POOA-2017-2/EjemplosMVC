package deporte.vista.interfaz;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLabel;

public interface CanchaInterfaz {
	
	public int getJugadorX();
	public int getJugadorY();
	public Rectangle getCancha();
	public void actualizar(int x, int y) throws IOException;
	public JLabel getJugador();
	public void setCliente(Socket cliente);
}
