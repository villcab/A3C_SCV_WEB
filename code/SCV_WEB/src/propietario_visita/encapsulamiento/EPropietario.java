package propietario_visita.encapsulamiento;

public class EPropietario {

	private int ci;
	private String nombre;
	private String apellido;
	private byte [] foto;
	private char sexo;
	private boolean estado;
	
	public EPropietario() {
		this.ci = 0;
		this.nombre = "";
		this.apellido = "";
		this.foto = null;
		this.sexo = ' ';
		this.estado = false;
	}
	
	/*** GETTER AND SETTER ***/
	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
