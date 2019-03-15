public interface DAOCliente {
	void grabar (Cliente cliente);
	Cliente leer (String dni);
	void cerrar();
}