package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public class Equipa implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 1;
    private ArrayList<Jogadores> jogadoresnaequipa;
    private String idEquipa;
    private String nomeEquipa;
    private String tipoEquipa;
    private int vitorias;
    private int derrotas;

    public Equipa(String nomeEquipa, String tipoEquipa) {
        this.idEquipa = "E" + contador++;
        this.nomeEquipa = nomeEquipa;
        this.tipoEquipa = tipoEquipa;
        this.vitorias = 0;
        this.derrotas = 0;
        this.jogadoresnaequipa = new ArrayList<Jogadores>();
    }

    public Equipa(){}


    public String getIdEquipa() {
        return idEquipa;
    }

    public String getNomeEquipa() {
        return nomeEquipa;
    }

    public void setNomeEquipa(String nomeEquipa) {
        this.nomeEquipa = nomeEquipa;
    }

    public String getTipoEquipa() {
        return tipoEquipa;
    }

    public void setTipoEquipa(String tipoEquipa) {
        this.tipoEquipa = tipoEquipa;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public ArrayList<Jogadores> getJogadoresnaequipa() {
        return jogadoresnaequipa;
    }

    public void setJogadoresnaequipa(ArrayList<Jogadores> jogadoresnaequipa) {
        this.jogadoresnaequipa = jogadoresnaequipa;
    }

    public void MostrarJogadoresNaEquipa(){
        if(jogadoresnaequipa != null){
            for(Jogadores j : jogadoresnaequipa){
                System.out.println("Id:" + j.getIdJogador() + " Nickname:" + j.getNicknameJogador());
                System.out.println("________________________________________________");

            }
        }else{
            System.out.println("A lista Esta vazia");
        }
    }
}
