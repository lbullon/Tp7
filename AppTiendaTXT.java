import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class AppTiendaTXT {
	public static void main(String[] args) {
		DAOCompraTxt daoC = new DAOCompraTxt();
		DAOImpProductoTxt daoP = new DAOImpProductoTxt();
		DAOImpClienteTxt daoCl = new DAOImpClienteTxt();
		List<Producto> albaran = null;
		Map<Producto,Double> comprado= new HashMap<Producto,Double>();
		Cliente cliente = null;
		
		Compra compra = new Compra();
		int numFac = 0;
		Scanner sc = new Scanner(System.in);
		boolean clienteComprando = true;
		boolean tiendaAbierta = true;
									
									/*TEXTO DE BIENVENIDA*/
		System.out.println("\n********Bienvenido a la Fruteria Pepe********\nHoy tenemos a la venta los siguientes artículos:\n");
    albaran = daoP.leerTodos();
    for (Producto p : albaran){
    	System.out.println(p.getName());
    }
    daoP.cerrar();

    			/*VENTA*/
    while (tiendaAbierta){
	    System.out.println("\n****Datos de cliente*****"); 
			cliente = new Cliente();
            System.out.print("Dni: ");
            String input = sc.nextLine();
            cliente.setDni(input);
            cliente.setDireccion(daoC.direccion(input));
            cliente.setDireccion(daoC.nombre(input));
            compra.setCliente(cliente);

			
			daoCl.grabar(cliente);//Graba si no está almacenado en la base de datos
			daoCl.cerrar();	
			while(clienteComprando) {
				Producto producto = new Producto();
				System.out.print("Producto: ");
				String nombreProducto = sc.nextLine();
				producto.setName(nombreProducto);
				System.out.print("Cantidad: ");
				double cantidad = Double.parseDouble(sc.nextLine());
				producto.setCantidad(cantidad);
				producto.setSku(Integer.parseInt(daoC.sku(nombreProducto)));
				producto.setPrecio(daoC.precio(producto.getName()));
				
				 Double precio = cantidad * producto.getPrecio();
				comprado.put(producto, precio)
				
				
				;
		    	System.out.println("Algo mas?S/N");
		   
		    	
				compra.setComprado(comprado);
		    	
		    	System.out.println(comprado.size());
	    	if(sc.nextLine().equalsIgnoreCase("n")){// Para añadir productos a la compra
	    		clienteComprando = false;
	    	}
	    }
	    clienteComprando = true;
	
	    numFac = daoC.enumerar();
	    compra.setNumFac(numFac);
	    
	    daoC.grabar(compra);
	    daoC.cerrar();//Obligado a cerrar porque necesito hacer insert de posibles nuevos clientes y el select crea conflicto
	    System.out.println("Tienda Abierta?S/N");
	    if(sc.nextLine().equalsIgnoreCase("n")){ //para atender nuevo cliente
	   		tiendaAbierta = false;
	   	}
	  }
	  //dao = new DAOImpCompraBD();

    System.out.println(daoC.ticket(numFac)); //Imprime ticket en este caso el ultimo registrado
    //System.out.println(daoC.ticket(daoC.enumerar()-1));
    //dao.cerrar();
    //dao3.cerrar();
	}
}