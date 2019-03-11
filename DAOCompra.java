public interface DAOCompra {
	void grabar (Compra compra);
	Compra ticket (String dni,int numFac);
	List<Compra> allTicketCliente (Cliente cliente);
	List<Compra> allTicket ();
	void cerrar();
}
