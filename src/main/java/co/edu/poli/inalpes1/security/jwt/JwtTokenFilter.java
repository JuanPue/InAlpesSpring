package co.edu.poli.inalpes1.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.poli.inalpes1.security.service.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter{

	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImp;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = getToken(request);
			if(token != null && jwtProvider.validateToken(token)) {
				String correo = jwtProvider.getCorreoFromToken(token);
				UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(correo);
				UsernamePasswordAuthenticationToken auth = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			logger.error("fail método dofilter");
		}
		filterChain.doFilter(request, response);
		
	}
	
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")) {
        	logger.info("IF POSITIVO");
            return header.replace("Bearer ", "");
        }
        logger.info("IF NEGATIVO");
        return null;
    }

}
