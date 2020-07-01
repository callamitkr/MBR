package com.capgemini.mbr.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SimpleFilter extends ZuulFilter {

	 
	  private static final Logger logger = LoggerFactory.getLogger(SimpleFilter.class);
	  @Override
	  public String filterType() {
	    return "pre";
	  }

	  @Override
	  public int filterOrder() {
	    return 1;
	  }

	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }

	  @Override
	  public Object run() {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    String remoteHost = ctx.getRequest().getRemoteHost();
	    String requestURL = ctx.getRequest().getRequestURL().toString();
	    logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    logger.info("Remote Host  :{}",remoteHost);
	    logger.info("Requested URL  :{}",request.getRemoteHost());
	    return null;
	  }
}