package propietario_visita.encapsulamiento;

public class EPropietario {

	private Integer ci;
	private String nombre;
	private String apellido;
	private byte [] foto;
	private Character sexo;
	private Boolean estado;
	
	public EPropietario() {
	}
	
	/*** GETTER AND SETTER ***/
	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "EPropietario [ci=" + ci + ", nombre=" + nombre + ", apellido="
				+ apellido + ", sexo=" + sexo + "]";
	}
	
}
