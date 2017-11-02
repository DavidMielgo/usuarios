package usuario;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import usuario.model.DataManager;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataManager dataManager;

    public UsuarioServlet() {
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("*** initializing controller servlet.");
		super.init(config);

		dataManager = new DataManager();
		dataManager.setDbURL(config.getInitParameter("dbURL"));
		dataManager.setDbUserName(config.getInitParameter("dbUserName"));
		dataManager.setDbPassword(config.getInitParameter("dbPassword"));

		ServletContext context = config.getServletContext();
		context.setAttribute("base", config.getInitParameter("base"));
		context.setAttribute("dataManager", dataManager);

		try { // load the database JDBC driver
			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hey");
		String base = "/JSP/";
		String url = base + "index.jsp";
		String action = request.getParameter("action");
		// recuperar datamanager del contexto action=ejer1
		//DataManager datamanager = (DataManager) request.getServletContext().getAttribute("dataManager");
		System.out.println(action);
		if (action != null) {
			switch (action) {
			case "ejer1":
				direcciones(request);
				url = base + "ejer1.jsp";
				break;
			case "ejer2":
				direcciones(request);
				request.getSession().setAttribute("error", "");

				url = base + "ejer2.jsp";
				break;

			case "toLog":
				direcciones(request);
				String user = request.getParameter("user");
				String pass = request.getParameter("pass");
				
				boolean isLogged = dataManager.getUser(user, pass);
				
				if(isLogged == true) {
					request.getSession().setAttribute("error", "");

					request.getSession().setAttribute("user", user);
					
					url = base + "logged.jsp";
				}
				else {
					url = base + "ejer2.jsp";
					request.getSession().setAttribute("error", "usuario o contraseña erroneo");
				}
				break;
			}
		}
		
		System.out.println(url);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void direcciones(HttpServletRequest request) {
		String[] dirs = {"http://www.google.com", "http://www.yahoo.es", "http://www.elrincon.com", "http://www.github.com", "http://www.twitter.com"};
		request.getSession().setAttribute("dirs", dirs);
	}
	


}
