package app.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Patient {
    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String sexe;
    private String telephone;
    private String groupeSanguin;
    private String situationMedicale;
    private Timestamp dateAdmission;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getGroupeSanguin() { return groupeSanguin; }
    public void setGroupeSanguin(String groupeSanguin) { this.groupeSanguin = groupeSanguin; }
    public String getSituationMedicale() { return situationMedicale; }
    public void setSituationMedicale(String situationMedicale) { this.situationMedicale = situationMedicale; }
    public Timestamp getDateAdmission() { return dateAdmission; }
    public void setDateAdmission(Timestamp dateAdmission) { this.dateAdmission = dateAdmission; }
}