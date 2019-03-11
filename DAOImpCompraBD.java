import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

public class DAOImpCompraBD implements DAOCompra {

  private Connection con;

  public DAOImpCompraBD() {
    try {
      con = DriverManager.getConnection("jdbc:sqlite:tienda.db");
    } catch (SQLException e) {
       System.out.println(e.getMessage());
    }
  }

  public void grabar (Compra compra) {
    String sql = "INSERT INTO compra (Factura,Sku,Dni,Fecha,Unidades) VALUES(?,?,?,?,?)";
    try{
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, compra.getNumFac());
      pstmt.setInt(2, compra.getProducto().getSku());
      pstmt.setString(3, compra.getCliente().getDni());
      pstmt.setDate(4, compra.getFecha());
      pstmt.setDouble(5, compra.getCantidad());
      pstmt.executeUpdate();
      System.out.println("Insertado en la DB ");
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "***");
    }
  }

  public Compra ticket (String dni,int numFac) { //Revisar Select
    Compra compra = null;
    Cliente cliente;
    Producto producto;
    Map<Producto,double> comprado;
    String sqlCliente = "SELECT Dni, Nombre, Direccion FROM cliente WHERE Dni = '"+dni+"'"; // para conseguir datos de la tabla Cliente
    String sqlProductos = "SELECT A.Sku, Nombre, Precio, Cantidad, Unidades FROM compra C, albaran A WHERE numFac ="+numFac; // Conseguir los datos de los productos y unidades compradas para generar el Map<Producto,Double>
    String sqlFecha = "SELECT Fecha FROM compra WHERE Factura ="+numFac;
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sqlCliente);
      cliente = new Cliente(rs.getString("Nombre"),rs.getString("Dni"),rs.getString("Direccion"));
      ResultSet rs = stmt.executeQuery(sqlProductos);
      while (rs.next()) {
        producto = new Producto(rs.getInt("Sku"),rs.getString("Name"),rs.getDouble("Precio"), rs.getDouble("Cantidad"));
        comprado.put(producto,rs.getDouble("Unidades"));
      }
      ResultSet rs = stmt.executeQuery(sqlFecha);
      compra = new Compra(cliente,comprado,numFac,rs.getDate("Fecha"));
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "***");
    }
    return compra;
  }

  public List<Compra> allTicketCliente (String dni) { //Revisar Select
    Compra compra = null;
    Cliente cliente;
    Producto producto;
    Map<Producto,double> comprado;
    List<Compra> allticket = new ArrayList<Compra>();
    String sqlCliente = "SELECT Dni, Nombre, Direccion FROM cliente WHERE Dni = '"+dni+"'"; // para conseguir datos de la tabla Cliente
    String sqlProductos = "SELECT A.Sku, Nombre, Precio, Cantidad, Unidades FROM compra C, albaran A WHERE dni ="+dni; // Conseguir los datos de los productos y unidades compradas para generar el Map<Producto,Double>
    String sqlFecha = "SELECT Factura, Fecha FROM compra WHERE Dni ="+dni;
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sqlCliente);
      cliente = new Cliente(rs.getString("Nombre"),rs.getString("Dni"),rs.getString("Direccion"));
      ResultSet rs = stmt.executeQuery(sqlProductos);
      while (rs.next()) {
        producto = new Producto(rs.getInt("Sku"),rs.getString("Name"),rs.getDouble("Precio"), rs.getDouble("Cantidad"));
        comprado.put(producto,rs.getDouble("Unidades"));
      }
      ResultSet rs = stmt.executeQuery(sqlFecha);
      compra = new Compra(cliente,comprado,rs.getInt("Factura"),rs.getDate("Fecha"));
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "***");
    }
    return allTicket;
  }

  public List<Compra> allTicket () { //Terminar

  }

  public void cerrar() {
    try {
      con.close();
    } catch (SQLException e) {
       System.out.println(e.getMessage());
    }  
  }
}