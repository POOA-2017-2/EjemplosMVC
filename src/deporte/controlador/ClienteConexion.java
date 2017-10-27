package deporte.controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import deporte.modelo.Paquete;
import deporte.vista.interfaz.CanchaInterfaz;
import deporte.vista.interfaz.DeporteInterfaz;

public class ClienteConexion implements Runnable{

	private Socket cliente;
	private ObjectInputStream input;
	private Thread t;
	private DeporteInterfaz vista;
	private CanchaInterfaz vistaCancha;
	
	public ClienteConexion(DeporteInterfaz vista,CanchaInterfaz vistaCancha,Socket cliente) {
		// TODO Auto-generated constructor stub
		this.cliente=cliente;
		this.vista=vista;
		this.vistaCancha=vistaCancha;
		t=new Thread(this);
		t.start();
	}

	public void run() {
		while(true){
			try {
				input=new ObjectInputStream(cliente.getInputStream());
				Paquete paquete=(Paquete) input.readObject();
				String tipo=paquete.getTipo();
				if(tipo.equals("ACTUALIZAR")){
					// actualizar posicion de jugador
					vistaCancha.getJugador().setBounds(paquete.getX(),paquete.getY(),40,40);
				}
				else{
					// pintar cancha
					vista.showCancha();
					vista.setFocusablePlay(false);
					vista.setFocusablePause(false);
					vista.setFocusableCancha(true);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
