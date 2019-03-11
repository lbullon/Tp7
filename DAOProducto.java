import java.util.List;

public interface DAOProducto{
	void actualizar(Producto producto);
	List<Producto> leerTodos();
	Producto leer(String name);
	void insertar(Producto producto);
	void insertarListado(List<Producto> albaran);
	void cerrar();
}