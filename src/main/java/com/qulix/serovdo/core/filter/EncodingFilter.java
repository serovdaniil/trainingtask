package com.qulix.serovdo.core.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "Encoding", urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {
    private String code;

    @Override
    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (codeRequest == null || !codeRequest.equalsIgnoreCase(code)) {
            request.setCharacterEncoding(code);
        }
        String codeResponse = response.getCharacterEncoding();
        if (codeResponse == null || !codeResponse.equalsIgnoreCase(code)) {
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }
}
