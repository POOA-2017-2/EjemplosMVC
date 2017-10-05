package deporte.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager<T> {
	private String path;

	public FileManager(String path){
		this.path=path;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean writeObject(T obj){
		boolean result=true;
		try {
			FileOutputStream file = new FileOutputStream(path);
			ObjectOutputStream oos= new ObjectOutputStream(file);
			oos.writeObject(obj);
			file.close();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	public T readObject(){
		T leida=null;
		try {
			FileInputStream file= new FileInputStream(path);
			ObjectInputStream ois=new ObjectInputStream(file);
			leida=(T) ois.readObject();
			file.close();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch(Exception e){
			//e.printStackTrace();
		}
		return leida;
	}
	
	
}
