package backend;

public class FPS extends Jogadores {
    private float precisao;
    private int headshots;

    public FPS(String nomeJogador, String nicknameJogador,String tipoJogador,
               String password) {
        super(nomeJogador, nicknameJogador, tipoJogador, password);
        this.precisao = 0;
        this.headshots = 0;
    }

    public FPS(){

    }

    public float getPrecisao() {
        return precisao;
    }

    public void setPrecisao(float precisao) {
        this.precisao = precisao;
    }

    public int getHeadshots() {
        return headshots;
    }

    public void setHeadshots(int headshots) {
        this.headshots = headshots;
    }
}
