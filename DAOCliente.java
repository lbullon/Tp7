public interface DAOCliente {
	void alta (Cliente cliente);
	void baja (Cliente cliente);
	Cliente leer (String dni);
	List<Cliente> leerTodos ();
	void actualizar (Cliente cliente)
	boolean existe (Cliente cliente);
	void cerrar();
}