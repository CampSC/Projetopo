package backend;

import java.io.Serializable;
import java.util.*;

public abstract class Jogadores implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 1;
    private String idJogador;
    private String nomeJogador;
    private String nicknameJogador;
    private String tipoJogador;
    private String password;
    private List<Torneios> torneios;  // Lista de torneios em que o jogador participa

    public Jogadores() {
        this.torneios = new ArrayList<>();
    }

    public Jogadores(String nomeJogador, String nicknameJogador, String tipoJogador, String password) {
        this.idJogador = "J" + contador++;
        this.nomeJogador = nomeJogador;
        this.nicknameJogador = nicknameJogador;
        this.tipoJogador = tipoJogador;
        this.password = password;
        this.torneios = new ArrayList<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método para adicionar um torneio à lista de torneios em que o jogador participa
    public void adicionarTorneio(Torneios torneio) {
        if (!torneios.contains(torneio)) {
            torneios.add(torneio);
        }
    }

    // Método para obter a lista de torneios em que o jogador está inscrito
    public List<Torneios> getTorneios() {
        return torneios;
    }

}
