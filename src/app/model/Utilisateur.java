package app.model;

public class Utilisateur {
    private int id;
    private String username;
    private String password;
    private String nomComplet;
    private int roleId;
    private String roleLibelle;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNomComplet() { return nomComplet; }
    public void setNomComplet(String nomComplet) { this.nomComplet = nomComplet; }
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
    public String getRoleLibelle() { return roleLibelle; }
    public void setRoleLibelle(String roleLibelle) { this.roleLibelle = roleLibelle; }
}