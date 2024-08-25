package com.CN.FitFusion.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtAuthenticationHelper jwtAuthenticationHelper;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {

		String requetHeader = request.getHeader("Authorization");

		String userName = null;
		String token = null;

		if (requetHeader != null && requetHeader.startsWith("Bearer")) {
			token = requetHeader.substring(7); // get token only exclude Bearer

			userName = jwtAuthenticationHelper.getUserNameByToken(token); // get username

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetail = userDetailsService.loadUserByUsername(userName); // get userDetail

				if (!jwtAuthenticationHelper.isTokenExpired(token)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					// store in securitycontext the userAndPassword token
				}
			}
		}

		filterChain.doFilter(request, response); // Continue with filterChain after this function

	}

}
