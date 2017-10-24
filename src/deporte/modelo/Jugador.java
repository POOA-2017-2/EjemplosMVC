package deporte.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import deporte.vista.panel.Cancha;

public class Jugador{

	private int x;
	private int y;
	private int dx;
	private int dy;
	//private BufferedImage imagen;
	//private int currentFrame;
	//private Image currentImage;
	
	/*
	
	public int getCurrentFrame() {
		return currentFrame;
	}


	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}


	public Image getCurrentImage() {
		return currentImage;
	}


	public void setCurrentImage(Image currentImage) {
		this.currentImage = currentImage;
	}

*/

	public Jugador(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.dx=5;
		this.dy=5;
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
	
	

	public int getDx() {
		return dx;
	}


	public void setDx(int dx) {
		this.dx = dx;
	}


	public int getDy() {
		return dy;
	}


	public void setDy(int dy) {
		this.dy = dy;
	}

/*
	public Image getImagen() {
		return imagen;
	}


	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
*/

	public Jugador mover(Rectangle r,boolean left, boolean right, boolean up, boolean down){
		double xCancha=r.getX();
		double yCancha=r.getY();
		double widthCancha=r.getWidth();
		double heightCancha=r.getHeight();
		//int row=0;
		
		if(left && getX()>xCancha){
			setX(getX()-5);
			//row=1;
			
		}
		if(right && getX()<(widthCancha)){
			setX(getX()+5);
			//row=2;
		}
		if(up && getY()>yCancha){
			setY(getY()-5);
			//row=3;
		}
		if(down && getY()<(heightCancha-20)){
			setY(getY()+5);
			//row=0;
		}
		/*
		int srcX = currentFrame * 32;
        int srcY = row * 32;
        currentImage=imagen.getSubimage(srcX, srcY, 32, 32);
        */
		return this;
	}
}
