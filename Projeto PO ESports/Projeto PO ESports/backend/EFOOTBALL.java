package backend;

public class EFOOTBALL extends Jogadores{
    private String posicao;
    private int golos;
    private int assistencias;
    private int defesas;

    public EFOOTBALL(String idJogador, String nomeJogador, String nicknameJogador,String tipoJogador,
                     String password,String posicao) {
        super(idJogador, nomeJogador, nicknameJogador, tipoJogador, password);
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


