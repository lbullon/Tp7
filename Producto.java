public class Producto {

    private Integer sku;
    private String name;
    private Double precio;
    private Double cantidad;

    public Producto() {
         sku = 0;
         name = "";
         precio = 0.0;
         cantidad = 0.0;      
    }
    public Producto( int sku, String name, double precio, double cantidad) {
        this.sku = sku;
        this.name = name;
        this.precio = precio;
        this.cantidad = cantidad; 
    }

    public void setSku(int sku) {
      this.sku = sku;
    }
    public int getSku() {
      return sku;
    }

    public void setName(String name) {
      this.name = name;
    }
    public String getName() {
      return name;
    }

    public void setPrecio(double precio) {
      this.precio = precio;
    }
    public double getPrecio() {
      return precio;
    }

    public void setCantidad(double cantidad) {
      this.cantidad = cantidad;
    }
    public double getCantidad() {
      return cantidad;
    }

    @ Override
    public String toString() {
      return name+":"+sku+":"+precio+":"+cantidad;
    }

    @ Override
    public boolean equals(Object o) {
      if (o == null) return false;
      if (o == this) return true;
      if (!(o instanceof Producto)){
        return false;
      }
      Producto producto = (Producto) o;
      if(sku.equals(producto.getSku()) && name.equals(producto.getName()) && precio.equals(producto.getPrecio()) && cantidad.equals(producto.getCantidad())) {
        return true;
      }
      else {
        return false;
      }
    }

}
