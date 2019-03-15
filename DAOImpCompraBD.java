import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.text.ParseException;

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

  public void grabar (Compra compra) { // Almacena los datos de una compra en la BD
    String sql = "INSERT INTO compra (Factura,Sku,Dni,Fecha,Unidades) VALUES(?,?,?,?,?)";
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    String fechaComoCadena = sdf.format(compra.getFecha());
    try{
      PreparedStatement pstmt = con.prepareStatement(sql);
      for (Producto key : compra.getComprado().keySet()) {
        pstmt.setInt(1, compra.getNumFac());
        pstmt.setInt(2, key.getSku());
        pstmt.setString(3, compra.getCliente().getDni());
        pstmt.setString(4, fechaComoCadena);
        pstmt.setDouble(5, compra.getComprado().get(key));
        pstmt.executeUpdate();
      }
      System.out.println("Insertada en la DB ");
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "***");
    }
  }

  public int enumerar() { // Se encarga de dar un número de factura nuevo incrementa uno a el numFac mayor de la columna Factura 
    String sql="SELECT Factura FROM compra WHERE Factura = (SELECT MAX(Factura) FROM compra)"; //Consulta el numero de factura mayor
    int numFac = 0;
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      numFac = rs.getInt("Factura");  
      numFac++;
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "1***");
    } 
    return numFac;
  }

  public Compra ticket (int numFac) { //Devuelve una compra de una factura pedida
    Compra compra = null;
    Cliente cliente = null;
    Producto producto= null;
    Map<Producto,Double> comprado= new HashMap<Producto,Double>();
    String sql = "SELECT A.Sku,P.Dni,Fecha,Unidades,Name,Precio,Cantidad,Nombre,Direccion FROM compra C, albaran A, cliente P WHERE Factura ="+numFac+" AND A.Sku = C.Sku AND C.Dni = P.Dni";
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      cliente=new Cliente(rs.getString("Dni"),rs.getString("Nombre"),rs.getString("Direccion"));
      SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
      Date fecha = sdf.parse(rs.getString("Fecha"));
      while (rs.next()) {
        producto = new Producto(rs.getInt("Sku"),rs.getString("Name"),rs.getDouble("Precio"),rs.getDouble("Cantidad"));
        comprado.put(producto,rs.getDouble("Unidades"));
      }
      compra = new Compra(cliente,comprado,numFac,fecha);
      
    } catch (SQLException e) {
      System.out.println("***" + e.getMessage() + "1***");
    } catch (ParseException e) {
        e.printStackTrace();
      }
    return compra;
  }

  public void cerrar() { //Cierra la conexion
    try {
      con.close();
    } catch (SQLException e) {
       System.out.println(e.getMessage());
    }  
  }
}