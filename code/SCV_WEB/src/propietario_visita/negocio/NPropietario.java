package propietario_visita.negocio;

import java.util.List;

import propietario_visita.datos.DPropietario;
import propietario_visita.encapsulamiento.EPropietario;

public class NPropietario {

	private DPropietario dao; 
	
	public NPropietario() {
		dao = new DPropietario();
	}
	
	public boolean registrarPropietario(EPropietario data) {
		return dao.registrar(data);
	}
	
	public boolean modificarPropietario(EPropietario data) {
		return dao.modificar(data);
	}
	
	public boolean anularPropietario(EPropietario data) {
		return dao.anular(data);
	}
	
	public EPropietario obtenerPropietarioPorKey(int ci) {
		return dao.obtenerPorKey(ci);
	}
	
	public List<EPropietario> obtenerTodosPropietarios() {
		return dao.obtenerTodos();
	}
	
}
