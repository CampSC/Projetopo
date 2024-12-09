package backend;

import java.io.Serializable;

public class EFOOTBALL extends Jogadores implements Serializable {
    private static final long serialVersionUID = 1L;
    private String posicao;
    private int golos;
    private int assistencias;
    private int defesas;

    public EFOOTBALL(String nomeJogador, String nicknameJogador,String tipoJogador,
                     String password,String posicao) {
        super(nomeJogador, nicknameJogador, tipoJogador, password);
        this.posicao = posicao;
        this.golos = 0;
        this.assistencias = 0;
        this.defesas = 0;
    }

    public EFOOTBALL(){

    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getGolos() {
        return golos;
    }

    public void setGolos(int golos) {
        this.golos = golos;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public void setAssistencias(int assistencias) {
        this.assistencias = assistencias;
    }

    public int getDefesas() {
        return defesas;
    }

    public void setDefesas(int defesas) {
        this.defesas = defesas;
    }
}


