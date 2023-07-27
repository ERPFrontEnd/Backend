package com.erp.blang.Authorization_Authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.erp.blang.repository.UserRepository;


@Component
public class RequestFilter extends OncePerRequestFilter  {
	
	@Autowired private JWTUtility utility;
	
	@Autowired private UserService userService;
	
	private String token;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String header = request.getHeader("Authorization");
		
		String jwtToken=null;
		
		String email=null;
		
		if(header != null && header.startsWith("Bearer ")) {
			jwtToken=header.substring(7);
			this.token=jwtToken;
			/*try {*/
				
				email=utility.getUsernameFromToken(jwtToken);
				/*} catch (IllegalArgumentException e) {
				System.out.println("Unable to get jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT token is expired");
			}
		}
		
		else {
			System.out.println("jwt token does not start with bearer");*/
		}
		
	
		
		if(email != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userService.loadUserByUsername(email);
			
			if(utility.validateToken(jwtToken, userDetails)) {
				
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =	
					new UsernamePasswordAuthenticationToken(userDetails,null , userDetails.getAuthorities());
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}
		}
		
		filterChain.doFilter(request, response);
		}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
