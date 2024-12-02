package backend;

public class MOBA extends Jogadores {
    private String personagemPrincipal;
    private int kills;
    private int assists;
    private int deaths;
    private float kda;

    public MOBA(String nomeJogador, String nicknameJogador,String tipoJogador,
                String password) {
        super(nomeJogador, nicknameJogador, tipoJogador, password);
        this.personagemPrincipal = null;
        this.kills = 0;
        this.assists = 0;
        this.deaths = 0;
        this.kda = 0;
    }

    public MOBA(){

    }


    public String getPersonagemPrincipal() {
        return personagemPrincipal;
    }

    public void setPersonagemPrincipal(String personagemPrincipal) {
        this.personagemPrincipal = personagemPrincipal;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public float getKda() {
        return kda;
    }

    public void setKda(float kda) {
        this.kda = kda;
    }
}
