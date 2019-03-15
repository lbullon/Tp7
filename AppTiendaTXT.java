import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class AppTiendaTXT {
	public static void main(String[] args) {
		DAOCompraTxt daoC = new DAOCompraTxt();
		DAOImpProductoTxt daoP = new DAOImpProductoTxt();
		DAOImpClienteTxt daoCl = null;
		List<Producto> albaran = null;
		Map<Producto,Double> comprado= new HashMap<Producto,Double>();
		Cliente cliente = null;
		Producto producto = null;
		Compra compra = null;
		int numFac = 0;
		Scanner sc = new Scanner(System.in);
		boolean clienteComprando = true;
		boolean tiendaAbierta = true;
									
									/*TEXTO DE BIENVENIDA*/
		System.out.println("\n********Bienvenido a la Fruteria Pepe********\nHoy tenemos a la venta los siguientes artículos:\n");
    albaran = daoP.leerTodos();
    for (Producto p : albaran){
    	System.out.println(p);
    }
    daoP.cerrar();

    			/*VENTA*/
    while (tiendaAbierta){
	    System.out.println("\n****Datos de cliente*****"); 
			cliente = new Cliente();
            System.out.print("Dni: ");
            String input = sc.nextLine();
            cliente.setDni(input);
            System.out.println(cliente.getNombre());
			
			daoCl.grabar(cliente);//Graba si no está almacenado en la base de datos
			daoCl.cerrar();	
			while(clienteComprando) {
				System.out.print("Producto: ");
				String nombreProducto = sc.nextLine();
				System.out.print("Cantidad: ");
				double cantidad = Double.parseDouble(sc.nextLine()); 
				for (Producto p : albaran){
	    		if (p.getName().equalsIgnoreCase(nombreProducto)){
	    			if (!comprado.containsKey(p)){ // Para controlar la venta de un producto varias veces en una misma compra
	    				comprado.put(p,cantidad);
	    			} else {
	    				comprado.put(p,comprado.get(p)+cantidad);
	    			}
	    		}
	    	}
	    	System.out.println("Algo mas?S/N");
	    	if(sc.nextLine().equalsIgnoreCase("n")){// Para añadir productos a la compra
	    		clienteComprando = false;
	    	}
	    }
	    clienteComprando = true;
	
	    numFac = daoC.enumerar();
	    compra = new Compra(cliente,comprado,numFac);
	    daoC.grabar(compra);
	    daoC.cerrar();//Obligado a cerrar porque necesito hacer insert de posibles nuevos clientes y el select crea conflicto
	    System.out.println("Tienda Abierta?S/N");
	    if(sc.nextLine().equalsIgnoreCase("n")){ //para atender nuevo cliente
	   		tiendaAbierta = false;
	   	}
	  }
	  //dao = new DAOImpCompraBD();

    //System.out.println(dao.ticket(numFac)); //Imprime ticket en este caso el ultimo registrado
    //System.out.println(dao.ticket(numFac-1));
    //dao.cerrar();
    //dao3.cerrar();
	}
}