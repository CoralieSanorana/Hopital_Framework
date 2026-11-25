CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL UNIQUE -- ex: 'ADMIN', 'MEDECIN', 'INFIRMIER'
);

CREATE TABLE utilisateur (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- à hasher en production (ex: BCrypt/SHA-256)
    nom_complet VARCHAR(150) NOT NULL,
    role_id INT NOT NULL REFERENCES role(id) ON DELETE RESTRICT
);

CREATE TABLE patient (
    id SERIAL PRIMARY KEY,
    matricule VARCHAR(50) NOT NULL UNIQUE,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_naissance DATE,
    sexe VARCHAR(10), -- 'M', 'F', etc.
    telephone VARCHAR(30),
    groupe_sanguin VARCHAR(10),
    situation_medicale TEXT NOT NULL, -- état / diagnostic / observations médicales
    date_admission TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Données de test
INSERT INTO role (libelle) VALUES ('ADMIN'), ('MEDECIN'), ('RECEPTION');

INSERT INTO utilisateur (username, password, nom_complet, role_id)
SELECT 'admin', 'admin123', 'Dr Administrateur', id FROM role WHERE libelle = 'ADMIN';

INSERT INTO utilisateur (username, password, nom_complet, role_id)
SELECT 'medecin1', '1234', 'Dr Rakoto', id FROM role WHERE libelle = 'MEDECIN';

INSERT INTO patient (matricule, nom, prenom, date_naissance, sexe, telephone, groupe_sanguin, situation_medicale)
VALUES
('PAT-001', 'Randria', 'Hery', '1990-05-14', 'M', '0340000001', 'O+', 'Suivi hypertension artérielle, contrôle mensuel.'),
('PAT-002', 'Rasoamanana', 'Soa', '1998-11-22', 'F', '0340000002', 'A-', 'Observation post-opératoire, traitement antibiotique en cours.');