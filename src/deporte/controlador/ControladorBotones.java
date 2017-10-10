package deporte.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import deporte.modelo.FileManager;
import deporte.vista.interfaz.DeporteInterfaz;
import deporte.vista.jdialog.AboutDialog;

public class ControladorBotones implements ActionListener{

	FileManager<Component> file;
	DeporteInterfaz vista;
	public ControladorBotones(DeporteInterfaz vista) {
		// TODO Auto-generated constructor stub
		this.vista=vista;
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
				break;
			case "acerca":
				AboutDialog about= new AboutDialog();
				about.setVisible(true);
				break;
		}
		
	}

}
