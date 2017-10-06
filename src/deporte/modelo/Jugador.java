package deporte.modelo;

import java.awt.Rectangle;

public class Jugador {

	private int x;
	private int y;
	
	
	public Jugador(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

	public Jugador mover(Rectangle r,boolean left, boolean right, boolean up, boolean down){
		double xCancha=r.getX();
		double yCancha=r.getY();
		double widthCancha=r.getWidth();
		double heightCancha=r.getHeight();
		
		if(left && getX()>xCancha){
			setX(getX()-5);
			System.out.println(getX());
			
		}
		if(right && getX()<(widthCancha)){
			setX(getX()+5);
		}
		if(up && getY()>yCancha){
			setY(getY()-5);
			System.out.println(getY());
		}
		if(down && getY()<(heightCancha-20)){
			setY(getY()+5);
		}
		
		return this;
	}
	
}
