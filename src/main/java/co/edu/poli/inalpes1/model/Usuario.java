package co.edu.poli.inalpes1.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import co.edu.poli.inalpes1.model.Rol;



@Entity
@Table(name="usuario")
public class Usuario {

	 @Id
	 @Column
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column
	 private String nombre;
	 
	 @Column(unique=true)
	 private String identificacion;
	 
	 @Column
	 private String apellido;
	 
	 @Column
	 private String telefono;
	 
	 @Column(unique=true)
	 private String correo;
	 
	 @Column
	 private String clave;
	 
	 @Column
	 private String estado;
	 

	 @Column
	 private String fecha_nacimiento;
	 
	 @Column
	 private String tipo_Usuario;

	 @Column
	 private String ciudad;

	 @Column
	 private String oficina;

	 @Column
	 private String preferencias;

	 @Column
	 private String recibir_notificaciones;

	 @Column
	 private String recibir_novedades;
	    
	 @OneToMany(mappedBy = "usuario")
	 private Set<Inmueble> inmueble;
	 
	 @NotNull
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
	    inverseJoinColumns = @JoinColumn(name = "rol_id"))
	    private Set<Rol> roles = new HashSet<>();
	 
	 public Usuario() {
		 
	 }

	public Usuario(Long id, String nombre, String identificacion, String apellido, String telefono, String correo,
			String clave, String estado, String fecha_nacimiento, String tipo_Usuario,
			String ciudad, String oficina, String preferencias, String recibir_notificaciones,
			String recibir_novedades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
		this.clave = clave;
		this.estado = estado;
		this.fecha_nacimiento = fecha_nacimiento;
		this.tipo_Usuario = tipo_Usuario;
		this.ciudad = ciudad;
		this.oficina = oficina;
		this.preferencias = preferencias;
		this.recibir_notificaciones = recibir_notificaciones;
		this.recibir_novedades = recibir_novedades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getTipo_Usuario() {
		return tipo_Usuario;
	}

	public void setTipo_Usuario(String tipo_Usuario) {
		this.tipo_Usuario = tipo_Usuario;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}

	public String getRecibir_notificaciones() {
		return recibir_notificaciones;
	}

	public void setRecibir_notificaciones(String recibir_notificaciones) {
		this.recibir_notificaciones = recibir_notificaciones;
	}

	public String getRecibir_novedades() {
		return recibir_novedades;
	}

	public void setRecibir_novedades(String recibir_novedades) {
		this.recibir_novedades = recibir_novedades;
	}

	public Set<Inmueble> getInmueble() {
		return inmueble;
	}

	public void setInmueble(Set<Inmueble> inmueble) {
		this.inmueble = inmueble;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	 
	
}
