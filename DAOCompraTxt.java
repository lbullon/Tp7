import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class DAOCompraTxt implements DAOCompra{    
    public  DAOCompraTxt(){
   
    }
	 File file = new File("compras.txt");
	 SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MMM-yyyy");
    public void grabar(Compra c){
        String datos = "";
       
       
        
        datos += c.getNumFac() + ":" + formatoDeFecha.format(c.getFecha()) + ":";
        datos += c.getCliente().getDni()  + "/";
       
        
        
        
        for(Producto producto : c.getComprado().keySet()){
            datos += producto.getSku() + ":" + producto.getCantidad()+ ":";

        }
        
        
        datos += "\n";
       
        
        try{

           
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            if(file.exists() != false){
                
                
                bw.write(datos);
                bw.close();
                
            }
            else{
                file.createNewFile();
                
                bw.write(datos);
                bw.close();
               
    
            }
            
            }catch (Exception e)
            {
                e.printStackTrace();
            }  

    }
    public void leer (String url){
        
    }
	@Override
	public int enumerar() {
	
	        int numFac = 0;
	        try { 
	        	FileReader reader = new FileReader(file);
	        	BufferedReader read = new BufferedReader(reader);
	        	String linea;
	        	int max = 0;
	        	while((linea = read.readLine())!=null) {
	        		String [] list = linea.split(":");
	        		numFac =Integer.parseInt(list[0]);
	        		
	        		if (numFac > max) {
	        			max = numFac;
	        		}
	        		
	        	}
	        	
	        }catch (Exception e) {
	        	
	        }
	        return numFac+1;
	}
	
	public Compra ticket(int numFac) {
		FileReader reader;
		String linea;
		Compra compra = new Compra();
		Cliente cliente = new Cliente();
		Producto producto = new Producto();
		Map<Producto,Double> comprado = new HashMap<Producto,Double>();
		
		
		try {
		    reader = new FileReader(file);
        	BufferedReader read = new BufferedReader(reader);
        	
        	
        	try {
				while((linea = read.readLine())!=null) {
					String [] lista1 = linea.split("/");
					
					String [] lista = lista1[0].split(":");
					String [] listaF = lista1[1].split(":");
					
				
					int n = Integer.parseInt(lista[0]);
					
					if(n == numFac) {
						compra.setNumFac(Integer.parseInt(lista[0]));
						Date date = null;
						try {
							date = formatoDeFecha.parse(lista[1]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						compra.setFecha(date);
						cliente.setDni(lista[2]);
						//cliente.setDireccion(direccion(cliente.getDni()));
						System.out.println(direccion(cliente.getDni()));
						compra.setCliente(cliente);
					}
				}
        	}
	
					    /*int pasadas = listaF.length;
						System.out.println(pasadas);
						int i = 0;
						
						while(i < pasadas-1) {
							listaF = listaF[i].split(":");
							System.out.println(listaF[0]);
						i++;
						
						producto.setSku(Integer.parseInt(listaF[0]));
						producto.setName(listaF[1]);
						producto.setCantidad(Integer.parseInt(lista[2]));
						producto.setPrecio(precio(producto.getName()));
						comprado.put(producto, (producto.getCantidad()*producto.getPrecio()));
						
						
										
						
						} }	
					
				}*/
			
					catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		// TODO Auto-generated method stub
		return compra;
	}
	@Override
	public void cerrar() {
		// TODO Auto-generated method stub
		
	}
	public Double precio (String name) {
			double price = 0 ;
		 try { 
			 	File fileP = new File("productos.txt");
	        	FileReader reader = new FileReader(fileP);
	        	BufferedReader read = new BufferedReader(reader);
	        	String linea;
	        	
	        	while((linea = read.readLine())!=null) {
	        		String [] list = linea.split(":");
	        		;
	        		
	        		if (list[1].equals(name)) {
	        			 price = Double.parseDouble(list[2]);
	        		}
	        		
	        	}
	        	read.close();	
	        }catch (Exception e) {
	        	
	        }
		 
		
		
		return price;
	}
	public String nombre (String dni) {
		String name = null;
		
		try { 
		 	File fileP = new File("clientes.txt");
        	FileReader reader = new FileReader(fileP);
        	BufferedReader read = new BufferedReader(reader);
        	String linea;
        	
        	while((linea = read.readLine())!=null) {
        		String [] list = linea.split(":");
        		;
        		
        		if (list[0].equals(dni)) {
        			name = list[1];
        			
        			 
        		}
        		
        	}
        	read.close();	
        }catch (Exception e) {
        	
        }
	 
	
	
	return name;
}
	public String direccion (String dni) {
		
		String direccion = null;
		try { 
		 	File fileP = new File("clientes.txt");
        	FileReader reader = new FileReader(fileP);
        	BufferedReader read = new BufferedReader(reader);
        	String linea;
        	
        	while((linea = read.readLine())!=null) {
        		String [] list = linea.split(":");
        		;
        		
        		if (list[0].equals(dni)) {
        			
        			direccion = list[2];
        			 
        		}
        		
        	}
        	read.close();	
        }catch (Exception e) {
        	
        }
	 
	
	
	return direccion;
}
}