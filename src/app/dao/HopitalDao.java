package app.dao;

import app.db.DBConnection;
import app.model.Patient;
import app.model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HopitalDao {

    public Utilisateur authentifier(String username, String password) throws SQLException {
        String sql = "SELECT u.id, u.username, u.nom_complet, u.role_id, r.libelle AS role_libelle " +
                     "FROM utilisateur u JOIN role r ON u.role_id = r.id " +
                     "WHERE u.username = ? AND u.password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setNomComplet(rs.getString("nom_complet"));
                    u.setRoleId(rs.getInt("role_id"));
                    u.setRoleLibelle(rs.getString("role_libelle"));
                    return u;
                }
            }
        }
        return null;
    }

    public List<Patient> findAllPatients() throws SQLException {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patient ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapPatient(rs));
            }
        }
        return list;
    }

    public Patient findPatientById(int id) throws SQLException {
        String sql = "SELECT * FROM patient WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPatient(rs);
                }
            }
        }
        return null;
    }

    public void insertPatient(Patient p) throws SQLException {
        String sql = "INSERT INTO patient (matricule, nom, prenom, date_naissance, sexe, telephone, groupe_sanguin, situation_medicale) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getMatricule());
            ps.setString(2, p.getNom());
            ps.setString(3, p.getPrenom());
            ps.setDate(4, p.getDateNaissance());
            ps.setString(5, p.getSexe());
            ps.setString(6, p.getTelephone());
            ps.setString(7, p.getGroupeSanguin());
            ps.setString(8, p.getSituationMedicale());
            ps.executeUpdate();
        }
    }

    public void updatePatient(Patient p) throws SQLException {
        String sql = "UPDATE patient SET matricule = ?, nom = ?, prenom = ?, date_naissance = ?, " +
                     "sexe = ?, telephone = ?, groupe_sanguin = ?, situation_medicale = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getMatricule());
            ps.setString(2, p.getNom());
            ps.setString(3, p.getPrenom());
            ps.setDate(4, p.getDateNaissance());
            ps.setString(5, p.getSexe());
            ps.setString(6, p.getTelephone());
            ps.setString(7, p.getGroupeSanguin());
            ps.setString(8, p.getSituationMedicale());
            ps.setInt(9, p.getId());
            ps.executeUpdate();
        }
    }

    public void deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM patient WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Patient mapPatient(ResultSet rs) throws SQLException {
        Patient p = new Patient();
        p.setId(rs.getInt("id"));
        p.setMatricule(rs.getString("matricule"));
        p.setNom(rs.getString("nom"));
        p.setPrenom(rs.getString("prenom"));
        p.setDateNaissance(rs.getDate("date_naissance"));
        p.setSexe(rs.getString("sexe"));
        p.setTelephone(rs.getString("telephone"));
        p.setGroupeSanguin(rs.getString("groupe_sanguin"));
        p.setSituationMedicale(rs.getString("situation_medicale"));
        p.setDateAdmission(rs.getTimestamp("date_admission"));
        return p;
    }
}