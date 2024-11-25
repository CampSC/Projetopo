
package backend;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public  class Treinadores {
    private static final Set<String> generatedIds = new HashSet();
    private String idTreinador;
    private String nomeTreinador;
    private String emailTreinador;
    private String passwordTreinador;
    private Equipa equipaGerida;

    public Treinadores(String idTreinador, String nomeTreinador, String emailTreinador,String passwordTreinador) {
        this.idTreinador = idTreinador;
        this.nomeTreinador = nomeTreinador;
        this.emailTreinador = emailTreinador;
        this.passwordTreinador = passwordTreinador;

    }

    public Treinadores(){}


    public String generateUniqueId() {
        Random random = new Random();

        String newId;
        do {
            int number = random.nextInt(1000000);
            newId = "t" + number;
        } while (generatedIds.contains(newId));

        generatedIds.add(newId);
        return newId;
    }

    public String getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(String idTreinador) {
        this.idTreinador = idTreinador;
    }

    public String getNomeTreinador() {
        return nomeTreinador;
    }

    public void setNomeTreinador(String nomeTreinador) {
        this.nomeTreinador = nomeTreinador;
    }

    public String getEmailTreinador() {
        return emailTreinador;
    }

    public void setEmailTreinador(String emailTreinador) {
        this.emailTreinador = emailTreinador;
    }

    public String getPasswordTreinador() {
        return passwordTreinador;
    }

    public void setPasswordTreinador(String passwordTreinador) {
        this.passwordTreinador = passwordTreinador;
    }

    public Equipa getEquipaGerida() {
        return equipaGerida;
    }

    public void setEquipaGerida(Equipa equipaGerida) {
        this.equipaGerida = equipaGerida;
    }
}
