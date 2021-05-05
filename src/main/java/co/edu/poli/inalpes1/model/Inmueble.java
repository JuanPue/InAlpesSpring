package co.edu.poli.inalpes1.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Inmueble")
public class Inmueble {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String estado;
	
	@Column 
	private String tipo;
	
	@Column
	private String direccion;
	
	@Column
	private Integer num_habitaciones;
	
	@Column
	private Integer num_banos;
	
	@Column
	private Integer area;
	
	@Column
	private BigDecimal precio;

	@Column
	private Integer estrato;
	
	@Column
	private String descripcion;
	
	@Column
	private String certificado;
	
	@Column
	private String ruta;
	
	@Column
	private String tipo_inmueble;
	
	@Column
	private String ciudad;
	
	@Column	
	private String barrio;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;
    
	public Inmueble() {
	
	}

	public Inmueble(Long id, String estado, String tipo, String direccion, Integer num_habitaciones, Integer num_banos,
			Integer area, BigDecimal precio, Integer estrato, String descripcion, String certificado, String ruta, String tipo_inmueble,
			String ciudad, String barrio) {
		super();
		this.id = id;
		this.estado = estado;
		this.tipo = tipo;
		this.direccion = direccion;
		this.num_habitaciones = num_habitaciones;
		this.num_banos = num_banos;
		this.area = area;
		this.precio = precio;
		this.estrato = estrato;
		this.descripcion = descripcion;
		this.certificado = certificado;
		this.ruta = ruta;
		this.tipo_inmueble = tipo_inmueble;
		this.ciudad = ciudad;
		this.barrio = barrio;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getNum_habitaciones() {
		return num_habitaciones;
	}

	public void setNum_habitaciones(Integer num_habitaciones) {
		this.num_habitaciones = num_habitaciones;
	}

	public Integer getNum_banos() {
		return num_banos;
	}

	public void setNum_banos(Integer num_banos) {
		this.num_banos = num_banos;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getEstrato() {
		return estrato;
	}

	public void setEstrato(Integer estrato) {
		this.estrato = estrato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getTipo_inmueble() {
		return tipo_inmueble;
	}

	public void setTipo_inmueble(String tipo_inmueble) {
		this.tipo_inmueble = tipo_inmueble;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

	
}
