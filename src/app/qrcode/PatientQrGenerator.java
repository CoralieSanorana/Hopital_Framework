package app.qrcode;

import app.model.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class PatientQrGenerator {

    // URL de l'endpoint JSON exposé par PatientController.findAll()
    private static final String API_PATIENTS_URL = "http://localhost:8085/Hopital/api/patients";
    
    // URL encodée dans chaque QR Code
    private static final String QR_TARGET_BASE_URL = "http://localhost:77/patients/";

    // Répertoire racine CDN (à adapter selon votre environnement : ex. "/user/local/cdn" ou "D:/cdn")
    private static final String CDN_ROOT = "D:/ITU/S5/Mr_Naina/Sprint_S5/QRCode";

    private static final int QR_WIDTH = 300;
    private static final int QR_HEIGHT = 300;

    public static void main(String[] args) {
        try {
            System.out.println("[QR] Récupération des patients depuis : " + API_PATIENTS_URL);
            List<Patient> patients = recupererPatientsDepuisApi(API_PATIENTS_URL);
            System.out.println("[QR] " + patients.size() + " patient(s) trouvé(s).");

            int generes = 0;
            int ignores = 0;

            for (Patient p : patients) {
                int id = p.getId();
                Path qrPath = construireCheminQrCode(id);

                // Ne génère que si le fichier n'existe pas encore
                if (Files.exists(qrPath)) {
                    ignores++;
                    System.out.println("[QR] Déjà existant pour patient #" + id + " -> " + qrPath);
                    continue;
                }

                String contenuQr = QR_TARGET_BASE_URL + id;
                genererEtStockerQrCode(contenuQr, qrPath);
                generes++;
                System.out.println("[QR] Généré pour patient #" + id + " (" + contenuQr + ") -> " + qrPath);
            }

            System.out.println("[QR] Terminé. Générés : " + generes + " | Déjà présents : " + ignores);

        } catch (Exception e) {
            System.err.println("[QR][ERREUR] " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Récupère et parse le JSON retourné par PatientController.findAll()
    public static List<Patient> recupererPatientsDepuisApi(String endpointUrl) throws IOException {
        URL url = new URL(endpointUrl.trim());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(10000);

        int status = conn.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            StringBuilder errBuilder = new StringBuilder();
            if (conn.getErrorStream() != null) {
                try (BufferedReader errReader = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                    String errLine;
                    while ((errLine = errReader.readLine()) != null) {
                        errBuilder.append(errLine);
                    }
                }
            }
            throw new IOException("Échec appel API (" + status + ") sur " + endpointUrl + " | Réponse : " + errBuilder);
        }

        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        } finally {
            conn.disconnect();
        }

        // Gson avec format de date/timestamp compatible SQL si nécessaire
        Gson gson = new GsonBuilder()
                .setDateFormat("MMM dd, yyyy")
                .create();

        Type listType = new TypeToken<List<Patient>>() {}.getType();
        List<Patient> patients = gson.fromJson(jsonBuilder.toString(), listType);
        return patients != null ? patients : new ArrayList<>();
    }

    // Construit : <CDN_ROOT>/patients/<id_patient>/qrcode/<id_patient>-qrcode.jpg
    public static Path construireCheminQrCode(int patientId) {
        return Paths.get(
                CDN_ROOT,
                "patients",
                String.valueOf(patientId),
                "qrcode",
                patientId + "-qrcode.jpg"
        );
    }

    // Génère l'image JPG du QR Code et l'enregistre sur disque
    public static void genererEtStockerQrCode(String contenu, Path cibleJpg) throws WriterException, IOException {
        Path parentDir = cibleJpg.getParent();
        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }

        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(contenu, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);

        // Écrit directement en JPG
        MatrixToImageWriter.writeToPath(bitMatrix, "JPG", cibleJpg);
    }
}