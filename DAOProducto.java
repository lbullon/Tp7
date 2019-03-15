import java.util.List;

public interface DAOProducto{
	List<Producto> leerTodos();
	void grabar(Producto producto);
	void cerrar();
}