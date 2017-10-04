package calculadora.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculadora.controlador.ControladorBotones;
import calculadora.modelo.Operaciones;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculadora extends JFrame implements CalculadoraInterfaz {

	private JPanel contentPane;
	private JTextField txtNumero1;
	private JTextField txtNumero2;
	private JTextField txtResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculadora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlBotones = new JPanel();
		contentPane.add(pnlBotones, BorderLayout.SOUTH);
		
		JButton btnSuma = new JButton("Suma");


		pnlBotones.add(btnSuma);
		
		JButton btnResta = new JButton("Resta");
		pnlBotones.add(btnResta);
		
		JButton btnMultiplicar = new JButton("Multiplicar");
		pnlBotones.add(btnMultiplicar);
		
		JButton btnDividir = new JButton("Dividir");
		pnlBotones.add(btnDividir);
		
		JPanel pnlDatos = new JPanel();
		contentPane.add(pnlDatos, BorderLayout.CENTER);
		pnlDatos.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel lblNumero = new JLabel("Numero 1 :");
		pnlDatos.add(lblNumero);
		
		txtNumero1 = new JTextField();
		pnlDatos.add(txtNumero1);
		txtNumero1.setColumns(10);
		
		JLabel lblNumero_1 = new JLabel("Numero 2:");
		pnlDatos.add(lblNumero_1);
		
		txtNumero2 = new JTextField();
		pnlDatos.add(txtNumero2);
		txtNumero2.setColumns(10);
		
		JLabel lblResultado = new JLabel("Resultado :");
		pnlDatos.add(lblResultado);
		
		txtResultado = new JTextField();
		txtResultado.setEnabled(false);
		pnlDatos.add(txtResultado);
		txtResultado.setColumns(10);
		
		Operaciones modelo=new Operaciones();
		ControladorBotones controlador= new ControladorBotones(modelo, this);
	
		btnSuma.setActionCommand("sumar");
		btnSuma.addActionListener(controlador);
		
		btnResta.setActionCommand("restar");
		btnResta.addActionListener(controlador);
		
		btnMultiplicar.setActionCommand("multiplicar");
		btnMultiplicar.addActionListener(controlador);
		
		btnDividir.setActionCommand("dividir");
		btnDividir.addActionListener(controlador);
	
	}

	@Override
	public double getNumero1() {
		return Double.parseDouble(txtNumero1.getText());
	}

	@Override
	public double getNumero2() {
		return Double.parseDouble(txtNumero2.getText());
	}

	@Override
	public void setResultado(double resultado) {
		txtResultado.setText(String.valueOf(resultado));
	}

}
