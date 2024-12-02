package backend;
import java.util.*;

public abstract class Jogadores {
    private static int contador = 1;
    private String idJogador;
    private String nomeJogador;
    private String nicknameJogador;
    private String tipoJogador;
    private String password;
    public Jogadores(){

    }

    public Jogadores(String nomeJogador, String nicknameJogador, String tipoJogador, String password) {
        this.idJogador = "J" + contador++;
        this.nomeJogador = nomeJogador;
        this.nicknameJogador = nicknameJogador;
        this.tipoJogador = tipoJogador;
        this.password = password;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public String getNicknameJogador() {
        return nicknameJogador;
    }

    public void setNicknameJogador(String nicknameJogador) {
        this.nicknameJogador = nicknameJogador;
    }

    public String getTipoJogador() {
        return tipoJogador;
    }

    public void setTipoJogador(String tipoJogador) {
        this.tipoJogador = tipoJogador;
    }

    public String getpassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

}