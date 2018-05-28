package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String tarefa = req.getParameter("t");
        try {
            if(tarefa == null){
                RequestDispatcher dispatcher = req.getRequestDispatcher("falha.html");
                dispatcher.forward(req, resp);
            } 

            tarefa = "Control." + tarefa;
            Class<?> tipo = Class.forName(tarefa);
            ITarefa instancia;
            instancia = (ITarefa) tipo.newInstance();
            String pagina = instancia.executar(req, resp);
            
            resp.sendRedirect(pagina);
            //RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
            //dispatcher.forward(req, resp);
            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | ServletException ex) {
            PrintWriter writer = resp.getWriter();
            
            //Escreve o erro para o usuario
            writer.println("<html>");
            writer.println("<body>");
            writer.println(ex.toString() + " <br/>");
            writer.println("</body>");
            writer.println("</html>");
        }
        
    }
}