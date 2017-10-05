package deporte.modelo;

import java.awt.event.KeyEvent;

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

	public Jugador mover(int code){
	
		if(code==KeyEvent.VK_LEFT){
			setX(getX()-5);
			
		}
		else if(code==KeyEvent.VK_RIGHT){
			setX(getX()+5);
		}
		else if(code==KeyEvent.VK_UP){
			setY(getY()-5);
		}
		else if(code==KeyEvent.VK_DOWN){
			setY(getY()+5);
		}
		
		return this;
	}
	
}
