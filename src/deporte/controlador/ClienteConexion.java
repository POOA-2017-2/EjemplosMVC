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
	private boolean activo;
	
	public ClienteConexion(DeporteInterfaz vista,CanchaInterfaz vistaCancha,Socket cliente) {
		// TODO Auto-generated constructor stub
		this.cliente=cliente;
		this.vista=vista;
		this.vistaCancha=vistaCancha;
		activo=true;
		t=new Thread(this);
		t.start();
	}

	public void run() {
		while(activo){
			try {
				input=new ObjectInputStream(cliente.getInputStream());
				System.out.println("CLIENTE-CONEXION:ESPERANDO MENSAJE");
				Paquete paquete=(Paquete) input.readObject();
				String tipo=paquete.getTipo();
				if(tipo.equals("ACTUALIZAR")){
					// actualizar posicion de jugador
					System.out.println("CLIENTE-CONEXION:LLEGO MENSAJE DE ACTUALIZAR");
					vistaCancha.getJugador().setBounds(paquete.getX(),paquete.getY(),40,40);
				}
				else{
					// pintar cancha
					System.out.println("CLIENTE-CONEXION:LLEGO MENSAJE PLAY");
					vista.showCancha();
					vista.setFocusablePlay(false);
					vista.setFocusablePause(false);
					vista.setFocusableCancha(true);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				activo=false;
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				activo=false;
				e.printStackTrace();
			}
		}
		
	}

}
