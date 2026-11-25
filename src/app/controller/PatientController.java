package app.controller;

import app.dao.HopitalDao;
import app.model.Patient;
import main.com.annotation.*;
import main.com.util.ModelAndView;
import java.util.List;
import java.sql.Date;

@Controller
public class PatientController {

    private final HopitalDao dao = new HopitalDao();

    // 1. Liste des patients
    @UrlMapping("/patients")
    @Autorisation("ADMIN")
    public ModelAndView liste() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("patient/liste");
        mv.setAttribute("patients", dao.findAllPatients());
        return mv;
    }

    // 2. Formulaire de saisie (création ou édition)
    @UrlMapping("/patients/nouveau")
    @Autorisation("ADMIN")
    public ModelAndView formulaireNouveau() {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("patient/form");
        mv.setAttribute("patient", new Patient());
        mv.setAttribute("mode", "create");
        return mv;
    }

    @UrlMapping("/patients/editer")
    @Autorisation("ADMIN")
    public ModelAndView formulaireEditer(int id) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("patient/form");
        mv.setAttribute("patient", dao.findPatientById(id));
        mv.setAttribute("mode", "edit");
        return mv;
    }

    // 3. Enregistrement (Create / Update)
    @UrlMapping(value = "/patients/enregistrer", method = "POST")
    @Autorisation("ADMIN")
    public ModelAndView enregistrer(
            Patient p
    ) throws Exception {
        if (p.getId() > 0) {
            dao.updatePatient(p);
        } else {
            dao.insertPatient(p);
        }

        ModelAndView mv = new ModelAndView();
        mv.setUrl("patient/liste");
        mv.setAttribute("patients", dao.findAllPatients());
        mv.setAttribute("message", "Dossier patient enregistré.");
        return mv;
    }

    // 4. Fiche détaillée d'un patient (situation médicale)
    @UrlMapping("/patients/fiche")
    @Autorisation("ADMIN")
    public ModelAndView fiche(int id) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("patient/fiche");
        mv.setAttribute("patient", dao.findPatientById(id));
        return mv;
    }

    // 5. Suppression
    @UrlMapping(value = "/patients/supprimer", method = "POST")
    @Autorisation("ADMIN")
    public ModelAndView supprimer(int id) throws Exception {
        dao.deletePatient(id);
        ModelAndView mv = new ModelAndView();
        mv.setUrl("patient/liste");
        mv.setAttribute("patients", dao.findAllPatients());
        mv.setAttribute("message", "Patient supprimé.");
        return mv;
    }
}