package backend;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Admin {

    private static final Set<String> generatedIds = new HashSet<>();

    public static Admin admin;
    private String idAdmin;
    private String nomeAdmin;
    private String emailAdmin;
    private String passAdmin;
    //private Torneios idTorneio;

    public Admin(String idAdmin,String nomeAdmin, String emailAdmin , String passAdmin) {
        this.idAdmin = idAdmin;
        this.nomeAdmin = nomeAdmin;
        this.emailAdmin = emailAdmin;
        this.passAdmin = passAdmin;
    }

    public Admin(){}

    public String generateUniqueId() {
        Random random = new Random();
        String newId;

        do {
            int number = random.nextInt(1000000);
            newId = "a" + number;
        } while (generatedIds.contains(newId));

        generatedIds.add(newId);
        return newId;
    }

    public static Admin getAdminInstance() {
        return admin;
    }
    public String getIdAdmin() {
        return idAdmin;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public String getEmailAdmin(){
        return emailAdmin;
    }

    public String getPassAdmin() {
        return passAdmin;
    }


    /*public Torneios getIdTorneio() {
        return idTorneio;
    }

    public void setIdTorneio(Torneios idTorneio) {
        this.idTorneio = idTorneio;
    }*/

    public boolean LoginAdmin(String emailAdmin , String passAdmin){
        if(emailAdmin.equals(getEmailAdmin()) && passAdmin.equals(getPassAdmin())){
            System.out.println("Login bem-sucedido");
            return true;
        }
        System.out.println("Credenciais invalidas");
        return false;
    }
}