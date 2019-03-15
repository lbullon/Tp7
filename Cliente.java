public class Cliente {
	private String nombre;
	private String dni;
	private String direccion;

	public Cliente() {

	}

	public Cliente(String nombre, String dni, String direccion) {
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}
	public String getDni(){
		return dni;
	}
	public String getDireccion(){
		return direccion;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@ Override
	public String toString() {
		return "Cliente: "+nombre+" "+dni+" "+direccion;
	}

	@Override
	public boolean equals(Object o) {
      if (o == null) return false;
      if (o == this) return true;
      if (!(o instanceof Cliente)){
        return false;
      }
      Cliente cliente = (Cliente) o;
      if(dni.equals(cliente.getDni())) {
        return true;
      }
      else {
        return false;
      }
    }

}