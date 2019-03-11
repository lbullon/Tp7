import java.util.Map;
import java.util.HashMap;
import java.util.Date;

public class Compra {
  private Cliente cliente;

  private Map<Producto,Double> comprado;
  private int cant;
  private int numFac;
  private Date fecha;

  public Compra() {

    cliente = new Cliente();
    comprado = new HashMap<Producto,Double>();
    numFac = 0;
    fecha = new java.util.Date();
  }

  public Compra(Cliente cliente,Map<Producto,Double> comprado,int numFac) {
    this.cliente = cliente;
    this.comprado = comprado;
    this.numFac = numFac;
    fecha = new java.util.Date();

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Cliente getCliente() {
    return cliente;

  }

  public Compra(Cliente cliente,Map<Producto,Double> comprado,int numFac, Date fecha) {
    this.cliente = cliente;
    this.comprado = comprado;
    this.numFac = numFac;
    this.fecha = fecha;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public Map<Producto,Double> getComprado() {
    return comprado;
  }

  public int getNumFac(){
    return numFac;
  }

  public Date getFecha(){
    return fecha;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public void setComprado(Map<Producto,Double> comprado) { 
    this.comprado = comprado;
  }

  public void setNumFac(int numFac) {
    this.numFac = numFac;
  }
  
  public Date setFecha(Date fecha) {
    return fecha;
  }

  @ Override
  public String toString() {
    String cadena;
    double total = 0;
    double cantidad;
    cadena = "\t\t****Factura: "+numFac+"****\n\n";
    cadena += cliente.getNombre()+" - "+ cliente.getDni()+"\n"+cliente.getDireccion()+"\n\n";
    for (Producto key :comprado.keySet()){
      cantidad = comprado.get(key);
      total += key.getPrecio()*cantidad;
      cadena += key.getSku()+"  "+String.format("%-10s",key.getName())+" ----- "+cantidad+"\t"+String.format("%.2f",key.getPrecio()*cantidad)+"\n";
    }
    cadena += "  \t\t\tTotal: "+ String.format("%.2f",total)+" Euros\n\n";
    cadena += fecha;
    return cadena;
  }
}

