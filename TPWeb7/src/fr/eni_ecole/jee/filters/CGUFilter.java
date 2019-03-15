package fr.eni_ecole.jee.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CGUFilter
 */
public class CGUFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CGUFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		boolean ok=false;
		HttpServletRequest rqt;
		rqt=(HttpServletRequest)request;
		Cookie[] gatos=rqt.getCookies();
		if (gatos!=null)
		{
			for(Cookie c : gatos)
			{
				if(c.getName().equals("CGU")&&c.getValue().equals("ok"))
				{
					ok=true;
				}
			}
		}
		if(rqt.getParameter("cgu")!=null && rqt.getParameter("cgu").equals("ok"))
		{
			Cookie gato;
			gato=new Cookie("CGU","ok");
			((HttpServletResponse)response).addCookie(gato);
			ok=true;
		}
		if (ok)
		{
			chain.doFilter(request, response);
		}
		else
		{
			request.setAttribute("url",rqt.getContextPath()+rqt.getServletPath());
			RequestDispatcher rd;
			rd=rqt.getServletContext().getRequestDispatcher("/CGU.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException 
	{
		// TODO Auto-generated method stub
	}

}
