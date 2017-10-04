package calculadora.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import calculadora.modelo.Operaciones;
import calculadora.vista.CalculadoraInterfaz;

public class ControladorBotones implements ActionListener {

	private Operaciones modelo;
	private CalculadoraInterfaz vista;
	
	public ControladorBotones(Operaciones modelo, CalculadoraInterfaz vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		String accion=e.getActionCommand();
		double numero1=vista.getNumero1();
	    double numero2=vista.getNumero2();
		modelo.setNumero1(numero1);
		modelo.setNumero2(numero2);
		double resultado=0;
		
		switch(accion){
			case "sumar":
				resultado=modelo.suma();
				break;
			case "restar":
				resultado=modelo.resta();
				break;
			case "multiplicar":
				resultado=modelo.multiplicar();
				break;
			case "dividir":
				resultado=modelo.dividir();
				break;
		}
		
		vista.setResultado(resultado);
		
	}

}
