package deporte.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

import deporte.modelo.FileManager;
import deporte.modelo.Paquete;
import deporte.vista.interfaz.DeporteInterfaz;
import deporte.vista.jdialog.AboutDialog;

public class ControladorBotones implements ActionListener{

	FileManager<Component> file;
	DeporteInterfaz vista;
	Socket cliente;
	ObjectOutputStream output;
	
	public ControladorBotones(DeporteInterfaz vista, Socket cliente) {
		// TODO Auto-generated constructor stub
		this.vista=vista;
		this.cliente=cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String type=e.getActionCommand();
		switch(type){
			case "save":
				JFileChooser seleccion= new JFileChooser();
				int code=seleccion.showSaveDialog(vista.getComponentShow());
				if(code==JFileChooser.APPROVE_OPTION){
					file=new FileManager<Component>(seleccion.getSelectedFile().getAbsolutePath());
					file.writeObject(vista.getWriteObject());
				}
				break;
			case "play":
				vista.showCancha();
				vista.setFocusablePlay(false);
				vista.setFocusablePause(false);
				vista.setFocusableCancha(true);
			try {
				output=new ObjectOutputStream(cliente.getOutputStream());
				Paquete paquete=new Paquete(0,0,"CANCHA");
				output.writeObject(paquete);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

				break;
			case "acerca":
				AboutDialog about= new AboutDialog();
				about.setVisible(true);
				break;
		}
		
	}

}
