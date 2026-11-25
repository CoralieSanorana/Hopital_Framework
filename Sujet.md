# EXTRA-HOPITAL

## Sprint 1: Gestion des patients

CRUD sur les patients.
Table `patient`{id, nom, prenom, date_naissance, lieu_naissance, adresse, tel}

## Sprint 2: Generer QRCode pour chaque patient

- Dans `PatientController`, creer fonction `getAll()`
- Creer un programme autonomme. Recuperer tous les patients, et pour chaque patient : generer QRCode, et saugarder les QRCode.
- QRCode, se refere aux `id`,`url:http://localhost:77/patients/id`. Representer en `jpg`.
- Fiche patient, affiche son QRCode.

## Sprint 2 Prim: appelle PHP

- Taper `url`, rediriger vers `PHP`, et `PHP` appelle `api` de `Framework`.
