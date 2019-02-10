package com.mrik.microservice;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;





@Component
public class ZuulLogginFilter extends ZuulFilter {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		HttpServletRequest request = 
		RequestContext.getCurrentContext().getRequest();
		logger.info("request-> {} request uri -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
