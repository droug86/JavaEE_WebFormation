package fr.eni_ecole.jee.filters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import fr.eni_ecole.jee.dal.ServeurDAO;

/**
 * Servlet Filter implementation class IpFilter
 */
public class IpFilter implements Filter 
{

    /**
     * Default constructor. 
     */
    public IpFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(ServeurDAO.ipPresente(request.getRemoteAddr()))
		{
			try {
				ServeurDAO.ajouterIP(request.getRemoteAddr(), new Date());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
