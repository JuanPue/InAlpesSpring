package co.edu.poli.inalpes1.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.inalpes1.model.Erol;
import co.edu.poli.inalpes1.model.Rol;
import co.edu.poli.inalpes1.model.Usuario;
import co.edu.poli.inalpes1.security.dto.JwtDto;
import co.edu.poli.inalpes1.security.dto.LoginUsuario;
import co.edu.poli.inalpes1.security.dto.NuevoUsuario;
import co.edu.poli.inalpes1.security.jwt.JwtProvider;
import co.edu.poli.inalpes1.security.service.RolService;
import co.edu.poli.inalpes1.security.service.UsuarioService;

@RestController
@RequestMapping("/inalpes/api/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioService usuService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors())
		{
			return new ResponseEntity("Por favor diligencia todos los campos o diligencialos correctamente", HttpStatus.BAD_REQUEST);
		}
		else if(usuService.existsByCorreo(nuevoUsuario.getCorreo()))
		{
			return new ResponseEntity("El correo ya existe", HttpStatus.BAD_REQUEST);
		}
		else {
		Usuario usuario = 
				new Usuario(null,nuevoUsuario.getNombre(),nuevoUsuario.getIdentificacion(), nuevoUsuario.getApellido(), nuevoUsuario.getTelefono(), nuevoUsuario.getCorreo(),
						passwordEncoder.encode(nuevoUsuario.getClave()), nuevoUsuario.getEstado(), null, null,
						null, null, null, null,
						null);
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(Erol.ROLE_USER).get());
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(Erol.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		if(nuevoUsuario.getRoles().contains("comercial"))
			roles.add(rolService.getByRolNombre(Erol.ROLE_COMERCIAL).get());
		usuario.setRoles(roles);
		usuService.save(usuario);
		return new ResponseEntity("Creado", HttpStatus.CREATED);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity("Por favor diligencia todos los campos o diligencialos correctamente", HttpStatus.BAD_REQUEST);
		Authentication authentication =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(), loginUsuario.getClave()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.CREATED);
		
	}
}
