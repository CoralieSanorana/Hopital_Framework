package app.controller;

import app.dao.HopitalDao;
import app.model.Utilisateur;
import main.com.annotation.*;
import main.com.util.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final HopitalDao dao = new HopitalDao();

    @UrlMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("auth/login");
        mv.setAttribute("titre", "Connexion - Hôpital");
        return mv;
    }

    @UrlMapping(value = "/login/submit", method = "POST")
    public ModelAndView doLogin(String username, String password, HttpSession session) throws Exception {
        Utilisateur user = dao.authentifier(username, password);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            mv.setUrl("auth/login");
            mv.setAttribute("erreur", "Identifiants invalides.");
            return mv;
        }

        session.setAttribute("user", user);
        session.setAttribute("role", user.getRoleLibelle()); // correspond à <param-name>authorisation</param-name> -> role

        if ("ADMIN".equalsIgnoreCase(user.getRoleLibelle())) {
            mv.setUrl("patient/liste");
            mv.setAttribute("patients", dao.findAllPatients());
        } else {
            mv.setUrl("auth/acces_limite");
            mv.setAttribute("user", user);
        }
        return mv;
    }

    @UrlMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        ModelAndView mv = new ModelAndView();
        mv.setUrl("auth/login");
        mv.setAttribute("message", "Déconnexion réussie.");
        return mv;
    }
}