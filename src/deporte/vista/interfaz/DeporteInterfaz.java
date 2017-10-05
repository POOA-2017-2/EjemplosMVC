package deporte.vista.interfaz;

import java.awt.Component;

import javax.swing.JPanel;

public interface DeporteInterfaz {
	public Component getWriteObject();
	public Component getComponentShow();
	//public JPanel getPanel();
	public void setFocusablePlay(boolean focus);
	public void setFocusablePause(boolean focus);
	public void setFocusableCancha(boolean focus);
	public void showCancha();
}
