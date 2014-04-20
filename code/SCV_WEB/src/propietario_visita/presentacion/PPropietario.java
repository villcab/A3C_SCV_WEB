package propietario_visita.presentacion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import propietario_visita.encapsulamiento.EPropietario;
import propietario_visita.negocio.NPropietario;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class PPropietario {
	
	private final Logger log = Logger.getLogger(PPropietario.class);
	
	private NPropietario propietarioBL;
	
	private List<EPropietario> propietarios;
	private EPropietario propietario;
	private boolean edit;
	
	@PostConstruct
	public void init() {
		try {
			propietarioBL = new NPropietario();
			propietarios = propietarioBL.obtenerTodosPropietarios();
			propietario = new EPropietario();

			log.info("Iniciando vista de gestionar de propietarios");
		} catch (Exception e) {
			log.error("Error al cargar vista de gestionar de propietarios: ", e);
		}
	}
	
	/*** METODOS GENERALES ***/
	
	public void registrarPropietario() {
		if (!edit) {	//REGISTRAR
			propietario.setEstado(true);
			boolean sw = propietarioBL.registrarPropietario(propietario);
			if (sw) {
				clear();
				propietarios = propietarioBL.obtenerTodosPropietarios();
				FacesUtil.showFacesMessage("Registro exitoso", FacesUtil.SEVERITY_INFO);
			} else {
				FacesUtil.showFacesMessage("Fallos al registrar", FacesUtil.SEVERITY_ERROR);
			}
		} else {		//MODIFICAR
			modificarPropietario();
		}
	}
	
	public void modificarPropietario() {
		boolean sw = propietarioBL.modificarPropietario(propietario);
		if (sw) {
			clear();
			propietarios = propietarioBL.obtenerTodosPropietarios();
			FacesUtil.showFacesMessage("Modificacion exitoso", FacesUtil.SEVERITY_INFO);
		} else {
			FacesUtil.showFacesMessage("Fallos al modificar", FacesUtil.SEVERITY_ERROR);
		}
	}
	
	public void anularPropietario() {
		String ci = FacesUtil.getParameter("ci").toString();
		propietario = propietarioBL.obtenerPropietarioPorKey(Integer.parseInt(ci));
		boolean sw = propietarioBL.anularPropietario(propietario);
		if (sw) {
			clear();
			propietarios = propietarioBL.obtenerTodosPropietarios();
			FacesUtil.showFacesMessage("Anulacion exitoso", FacesUtil.SEVERITY_INFO);
		} else {
			FacesUtil.showFacesMessage("Fallos al anular", FacesUtil.SEVERITY_ERROR);
		}
	}
	
	/*** METODOS ADICIONALES ***/
	
	public void cargarPropietario() {
		String ci = FacesUtil.getParameter("ci").toString();
		propietario = propietarioBL.obtenerPropietarioPorKey(Integer.parseInt(ci));
		edit = true;
	}
	
	public void clear() {
		propietario = new EPropietario();
		edit = false;
	}
	
	/*** GETTER AND SETTER ***/
	
	public List<EPropietario> getPropietarios() {
		return propietarios;
	}
	
	public void setPropietarios(List<EPropietario> propietarios) {
		this.propietarios = propietarios;
	}
	
	public EPropietario getPropietario() {
		return propietario;
	}
	
	public void setPropietario(EPropietario propietario) {
		this.propietario = propietario;
	}
	
	public boolean isEdit() {
		return edit;
	}
	
	public void setEdit(boolean edit) {
		this.edit = edit;
	}

}
