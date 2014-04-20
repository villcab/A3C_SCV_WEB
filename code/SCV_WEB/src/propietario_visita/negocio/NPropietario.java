package propietario_visita.negocio;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import propietario_visita.datos.DPropietario;
import propietario_visita.encapsulamiento.EPropietario;

@Named
public class NPropietario {

	@Inject
	private DPropietario dao; 
	
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
