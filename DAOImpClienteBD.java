import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DAOImpClienteBD implements DAOCliente {
	
	private Connection con;

	public DAOImpClienteBD(){
		try {
      con = DriverManager.getConnection("jdbc:sqlite:tienda.db");
    } catch (SQLException e) {
       System.out.println(e.getMessage());
    }
	}

	public void alta (Cliente cliente) {
		String sql = "INSERT INTO cliente(Name,Dni,Direccion) VALUES(?,?,?)";
    try{
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, cliente.getName());
      pstmt.setString(2, producto.getDni());
      pstmt.setString(3, producto.getDireccion());
      pstmt.executeUpdate();
      System.out.println("Insertado en la DB ");
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "***");
    }
	}
	public void baja (Cliente cliente) {
		String sql = "DELETE FROM cliente where Dni = ?";
		try{
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, cliente.getDni());
      pstmt.executeUpdate();
      System.out.println("Eliminado de la DB ");
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "***");
    }
	}
	public Cliente leer (String dni) {
		Cliente cliente = null;
		String sql = "SELECT Nombre, Dni, Direccion FROM cliente WHERE Dni LIKE '"+dni+"'";
		try {
			Statement stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery(sql);
    	cliente = new Cliente(rs.getString("Nombre"),rs.getString("Dni"),rs.getString("Direccion"));
    } catch (SQLException e) {
    	System.out.println("***" + e.getMessage() + "***");
    }
    return cliente;
	}

	public List<Cliente> leerTodos () {
		Cliente cliente;
		List<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "SELECT Nombre, Dni, Direccion FROM cliente";
      try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
        	cliente = new Cliente(rs.getString("Nombre"),rs.getString("Dni"),rs.getString("Direccion"));
        	clientes.add(cliente);
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    return albaran;
	}

	public void actualizar (Cliente cliente) {
		String sql = "UPDATE Cliente SET Nombre = ? , Direccion = ? WHERE Dni = ?";
    try{
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, producto.getNombre());
      pstmt.setString(2, producto.getDireccion());
      pstmt.setString(3, producto.getDni());
      pstmt.executeUpdate();
      System.out.println("Actualizada DB ");
    } catch (SQLException e) {
    	System.out.println("***" + e.getMessage() + "***");
    }
	}

	public boolean existe (Cliente cliente) {
		String condicion;
		List<Cliente> clientes = new ArrayList<Cliente>();
		Scanner sc = new Scanner(System.in);
		DAOImpClienteBD dc = new DAOImpClienteBD();
		clientes = dc.leerTodos();
		for (Cliente c: clientes) {
			if (c.equals(cliente)) {
				if (c.getNombre() =! cliente.getNombre() || c.getDireccion() =! cliente.getDireccion()){
					System.out.println("¿Ha introducido bien el dni? S/N");
					condicion = sc.nextLine();
					if (condicion == "s" || condicion == "S"){
						if (c.getNombre() =! cliente.getNombre()){
							System.out.println("Nombre diferente, ¿desea cambiarlo? S/N");
							condicion = sc.nextLine();
							if (condicion == "s" || condicion == "S"){
								c.setNombre(cliente.getNombre());
								dc.actualizar(c);
							}
						}
						if (c.getDireccion() =! cliente.getDireccion()){
							System.out.println("Direccion diferente, ¿desea cambiarlo? S/N");
							condicion = sc.nextLine();
							if (condicion == "s" || condicion == "S"){
								c.setDireccion(cliente.getDireccion());
								dc.actualizar(c);
							}
						}	
					} else {
						System.out.println("Escriba su dni correctamente");
						cliente.setDni(sc.nextLine());
						dc.existe(cliente);
						dc.cerrar();
					}
				}
				dc.cerrar();	
				return true;
			}
		}
		dc.cerrar();
		return false;
	}


	public void cerrar() {
    try {
      con.close();
    } catch (SQLException e) {
       System.out.println(e.getMessage());
    }  
  }
}
