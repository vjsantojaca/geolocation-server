package com.merinosa.geolocation.vjsantojaca.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletResponse Httpresponse = (HttpServletResponse) response;
		Httpresponse.setHeader("Access-Control-Allow-Origin", "*");
		Httpresponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		Httpresponse.setHeader("Access-Control-Max-Age", "3600");
		Httpresponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {	}

}
