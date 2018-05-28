package Control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements ITarefa {

    @Override
    public String executar(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        
        return "login.html";
    }
    
}