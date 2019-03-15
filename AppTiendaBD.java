import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class AppTiendaBD {
	public static void main(String[] args) {
		DAOCompra dao = null;
		DAOProducto dao2 = new DAOImpProductoBD();
		DAOCliente dao3 = null;
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
    albaran = dao2.leerTodos();
    for (Producto p : albaran){
    	System.out.println(p);
    }
    dao2.cerrar();

    			/*VENTA*/
    while (tiendaAbierta){
	    System.out.println("\n****Datos de cliente*****"); 
			cliente = new Cliente();
			System.out.print("Dni: ");
			cliente.setDni(sc.nextLine());
			dao3 = new DAOImpClienteBD();
			dao3.grabar(cliente);//Graba si no está almacenado en la base de datos
			dao3.cerrar();	
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
	    dao = new DAOImpCompraBD();	// Por la necesidad de cerrarlo por si necesito hacer insert de clientes nuevos
	    numFac = dao.enumerar();
	    compra = new Compra(cliente,comprado,numFac);
	    dao.grabar(compra);
	    dao.cerrar();//Obligado a cerrar porque necesito hacer insert de posibles nuevos clientes y el select crea conflicto
	    System.out.println("Tienda Abierta?S/N");
	    if(sc.nextLine().equalsIgnoreCase("n")){ //para atender nuevo cliente
	   		tiendaAbierta = false;
	   	}
	  }
	  dao = new DAOImpCompraBD();

    System.out.println(dao.ticket(numFac)); //Imprime ticket en este caso el ultimo registrado
    System.out.println(dao.ticket(numFac-1));
    dao.cerrar();
    dao3.cerrar();
	}
}