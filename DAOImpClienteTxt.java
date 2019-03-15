import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DAOImpClienteTxt {
	 public  DAOImpClienteTxt(){
		   
	    }
	 public void grabar (Cliente cliente) {
		 File file = new File("clientes.txt");
		 FileReader fileReader;
		 Scanner scanner = new Scanner(System.in);
		 String input;

		try {
			fileReader = new FileReader(file);
			FileWriter fileWriter;
			
			
			 BufferedReader reader = new BufferedReader(fileReader); 
			 Boolean comprueba = false;
			 String linea = null;
		     try {
				while((linea = reader.readLine())!=null) {
					 String  [] l = linea.split(":");
					 if (l[0].equals(cliente.getDni())){
						 System.out.println("Bienvenido "+l[1]);
						 comprueba = true;
					 }
				
						 
								 
					 }
				 if(comprueba == false) {
					 String datos = "";
					 datos = cliente.getDni()+ ":";
					 System.out.println("Nombre");
					 input = scanner.nextLine();
					 datos = datos +  input +":";
					 System.out.println("Direccion");
					 input = scanner.nextLine();
					 datos = datos + input + "\n";
					 try {
						 	File fileTxt = new File("clientes.txt"); 
						 	FileWriter writer = new FileWriter(fileTxt, true);
							writer.write(datos);
							writer.close();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		            
			 

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public Cliente leer (String dni) {
		Cliente cliente = new Cliente();
		 try {
			 File file = new File("clientes.txt");
			 FileReader fileReader = new FileReader(file);						
			 BufferedReader reader = new BufferedReader(fileReader); 
			 String line ;
			 while((line = reader.readLine()) !=null) {
				 String [] lista  = line.split(":");
				 if(lista[0].equals(dni)) {
					 cliente.setNombre(lista[0]);
					 cliente.setNombre(lista[1]);
					 cliente.setDireccion(lista[2]);
				 }
				 
			 }
			 
			
	
    } catch (IOException e) {
    	System.out.println("***" + e.getMessage() + "***");
    }
    return cliente;
	}
	void cerrar(){}
}
