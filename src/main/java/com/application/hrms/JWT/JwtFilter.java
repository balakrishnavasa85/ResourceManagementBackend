package com.application.hrms.JWT;

import io.jsonwebtoken.Claims;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private jwtUtil jwtUtil;

	@Autowired
	private CustomerUserDetailsService service;

	Claims claims = null;
	private String username = null;
	private static final RequestMatcher EXCLUDED_PATHS = new AntPathRequestMatcher("/uploads/**");

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		  if (EXCLUDED_PATHS.matches(httpServletRequest)) {
			  filterChain.doFilter(httpServletRequest, httpServletResponse); // Skip JWT authentication
	            return;
	        }
		if (httpServletRequest.getServletPath()
				.matches("/user/login|/user/forgotPassword|/user/signup|/applicationaccess/checkaccess|/uploads")) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			String authorizationHeader = httpServletRequest.getHeader("Authorization");
			String token = null;

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
				token = authorizationHeader.substring(7);
				username = jwtUtil.extractUsername(token);
				claims = jwtUtil.extractAllClaims(token);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = service.loadUserByUsername(username);
				if (jwtUtil.validatetoken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	public boolean isAdmin() {
		System.out.println("admin".equalsIgnoreCase((String) claims.get("role")));
		return "admin".equalsIgnoreCase((String) claims.get("role"));
	}

	public boolean isUser() {
		return "user".equalsIgnoreCase((String) claims.get("role"));
	}

	public String getCurrentUsername() {
		return username;
	}

}
