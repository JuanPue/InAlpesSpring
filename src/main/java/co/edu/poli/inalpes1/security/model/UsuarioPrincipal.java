package co.edu.poli.inalpes1.security.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.edu.poli.inalpes1.model.Inmueble;
import co.edu.poli.inalpes1.model.Usuario;

public class UsuarioPrincipal implements UserDetails{
	

	 private String nombre;
	 
	 private String identificacion;
	 
	 private String apellido;
	 
	 private String telefono;
	 
	 private String correo;
	 
	 private String clave;
	 
	 private String estado;
	 
	 private String fecha_nacimiento;
	 
	 private String tipo_Usuario;

	 private String ciudad;

	 private String oficina;

	 private String preferencias;

	 private String recibir_notificaciones;

	 private String recibir_novedades;
	    
	 private Set<Inmueble> inmueble;
	 
	 private Collection<? extends GrantedAuthority> authorities;
	 
	 

	public UsuarioPrincipal(String nombre, String identificacion, String apellido, String telefono, String correo,
			String clave, String estado, String fecha_nacimiento, String tipo_Usuario, String ciudad, String oficina,
			String preferencias, String recibir_notificaciones, String recibir_novedades, Set<Inmueble> inmueble,
			Collection<? extends GrantedAuthority> authorities) {
		super();
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
		this.inmueble = inmueble;
		this.authorities = authorities;
	}
	
	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities = 
				usuario.getRoles().stream().map(rol ->new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getNombre(), usuario.getIdentificacion(), usuario.getApellido(), usuario.getTelefono(), usuario.getCorreo(), 
				usuario.getClave(), usuario.getEstado(), usuario.getFecha_nacimiento(), usuario.getTipo_Usuario(), 
				usuario.getCiudad(), usuario.getOficina(), 
				usuario.getPreferencias(), usuario.getRecibir_notificaciones(), usuario.getRecibir_novedades(),usuario.getInmueble() ,authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return clave;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return correo;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public String getApellido() {
		return apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEstado() {
		return estado;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public String getTipo_Usuario() {
		return tipo_Usuario;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getOficina() {
		return oficina;
	}

	public String getPreferencias() {
		return preferencias;
	}

	public String getRecibir_notificaciones() {
		return recibir_notificaciones;
	}

	public String getRecibir_novedades() {
		return recibir_novedades;
	}

	public Set<Inmueble> getInmueble() {
		return inmueble;
	}
	
	
	 
	 
	 
}
