package practical.loginservice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserManagement manager;

    @Override
    public void init() throws ServletException {
        manager = new UserManagement();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("login") != null)
        {
            String username = req.getParameter("usernamelogin");
            String password = req.getParameter("passwordlogin");
            if(manager.checkLoginCredentials(username, password))
            {
                HttpSession session = req.getSession(true);
                session.setAttribute("loggedUser", username);
                session.setMaxInactiveInterval(30);
                resp.sendRedirect("home.jsp");
            }
            else { resp.sendRedirect("errorlogin.jsp"); }
        }
        else if(req.getParameter("logout") != null)
        {
            HttpSession session = req.getSession(false);
            session.removeAttribute("loggedUser");
            session.getMaxInactiveInterval();
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("register") != null)
        {
            String username = req.getParameter("username");
            String password = req.getParameter("password");  
            String confirmpass = req.getParameter("confirmpassword");
            if(password.equals(confirmpass))
            {
                User newUser = manager.registerUser(username, password);
                if(newUser != null)
                {
                    resp.sendRedirect("login.jsp");
                }
                else { resp.sendRedirect("errorregister.jsp"); }
            }
            else { resp.sendRedirect("errorregister.jsp"); }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmpass = req.getParameter("confirmpassword");
        if(username.isEmpty() || password.isEmpty() || confirmpass.isEmpty())
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("<html><body><p>Arguments (username), (password) and (confirm password) must not be empty"
            + "</p></body></html>");
        }
        else if(password.equals(confirmpass))
        {
            boolean result = manager.unregisterUser(username, password, confirmpass);
            if(result)
            {
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                resp.getWriter().println("<html><body><p>Succsessfully deleted user with Username : " 
                + username + "</p></body></html>");
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("<html><body><p>User with that username and/or password does not exist."
                + "</p></body></html>");
            }
        }
        else
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("<html><body><p>Password and confirm password does not match.</p></body></html>");
        }
    }
    
}