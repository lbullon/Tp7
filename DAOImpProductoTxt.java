import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOImpProductoTxt implements DAOProducto {
	 ArrayList <Producto> albaran = new ArrayList <Producto>();
	 File file = new File("productos.txt");
	 String line ;
	
	public  DAOImpProductoTxt(){
		
	}
	 public void grabar(Producto producto) {
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			writer.write(producto.getSku()+":"+producto.getName()+":"+producto.getPrecio()+":"+ producto.getCantidad());
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		 
		
		
		
	}
	 public List<Producto> leerTodos(){
		
		 
		
		 try{
			FileReader reader = new FileReader(file);
	        BufferedReader read = new BufferedReader(reader);
	        while((line = read.readLine())!=null) {
	        	 Producto producto = new Producto();
	        	String [] lista = line.split(":");
	        	producto.setName(lista[1]);	        	
	        	producto.setCantidad(Double.parseDouble(lista[3]));
	        	producto.setPrecio(Double.parseDouble(lista[2]));
	        	producto.setSku(Integer.parseInt(lista[0]));
	        	
	        		        	
	        	albaran.add(producto);
	        	
	        }
			 //read.cerrar();

		 }catch(Exception e) 
		 {
			 
		 }
		 
		return albaran;
		 
	 }
	 
	 
	public void cerrar() {
		
	
		
		 
	 }

}
