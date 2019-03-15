public interface DAOCompra {
	void grabar (Compra compra);
	int enumerar();
	Compra ticket (int numFac);
	void cerrar();
}
