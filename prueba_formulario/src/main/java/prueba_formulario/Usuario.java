package prueba_formulario;

public class Usuario {
	private String nombreUsuario;
	private String apellidosUsuario;
	private int edadUsuario;
	
	public Usuario() {
		super();
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidosUsuario() {
		return apellidosUsuario;
	}
	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}
	public int getEdadUsuario() {
		return edadUsuario;
	}
	public void setEdadUsuario(int edadUsuario) throws Exception {
		if (edadUsuario<=0) {
			throw new Exception();
		}
		this.edadUsuario = edadUsuario;
	}
	
	
	
}
