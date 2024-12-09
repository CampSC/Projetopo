package backend;
import java.io.Serializable;
import java.util.*;

public class ListaJogadores implements Serializable {
    private static final long serialVersionUID = 1L;

    private static ListaJogadores instanciaUnica = null;

    private ArrayList<Jogadores> listajogadores;

    private ListaJogadores() {
        listajogadores = new ArrayList<>();
    }

    public static ListaJogadores getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ListaJogadores();
        }
        return instanciaUnica;
    }

    public static void setInstancia(ListaJogadores novaInstancia) {
        if (novaInstancia != null) {
            instanciaUnica = novaInstancia;
        } else {
            throw new IllegalArgumentException("A nova instância não pode ser null.");
        }
    }

    public boolean adicionarJogadores(Jogadores j) {
        for (Jogadores jogador : listajogadores) {
            if (jogador.getNicknameJogador().equals(j.getNicknameJogador())) {
                System.err.println("Esse nickname já foi usado, por favor insira um novo.");
                return false;
            }
        }
        listajogadores.add(j);
        System.out.println("Jogador adicionado à lista com sucesso.");
        return true;
    }

    public void removerJogador(String idJogador) {
        Iterator<Jogadores> iterator = listajogadores.iterator();
        while (iterator.hasNext()) {
            Jogadores jogadores = iterator.next();
            if (jogadores.getIdJogador().equals(idJogador)) {
                iterator.remove();
                System.out.println("Jogador removido com sucesso.");
                return;
            }
        }
        System.err.println("Jogador com ID " + idJogador + " não encontrado.");
    }

    public Jogadores JogadorpeloId(String idJogador){
        for (Jogadores jogador : listajogadores) {
            if (jogador.getIdJogador().equals(idJogador)) {
                return jogador;
            }
        }
        System.err.println("Jogador não encontrado com ID: " + idJogador);
        return null;
    }


    public String LoginJogador(String nickname , String password){
            for(Jogadores jogador : listajogadores){
                if(nickname.equals(jogador.getNicknameJogador()) && password.equals(jogador.getPassword())){
                    System.out.println("Login bem sucedido");
                    return jogador.getIdJogador();
                }
        }  
        System.err.println("Credenciais inválidas");
        return null;
    }


    public void mostrarDadosJogador(String idJogador){
        Jogadores jogador = JogadorpeloId(idJogador);

        if (jogador != null) {
            System.out.println("ID do Jogador: " + jogador.getIdJogador());
            System.out.println("Nome do Jogador: " + jogador.getNomeJogador());
            System.out.println("Nickname do Jogador: " + jogador.getNicknameJogador());
            System.out.println("Tipo do Jogador: " + jogador.getTipoJogador());
        } else {
            System.err.println("Jogador não encontrado.");
        }
    }

    public void exibirEstatisticas(String idjogador) {

        Jogadores jogador = JogadorpeloId(idjogador);
        if (jogador instanceof FPS) {
            FPS fpsJogador = (FPS) jogador;
            System.out.println("Precisão: " + fpsJogador.getPrecisao());
            System.out.println("Headshots: " + fpsJogador.getHeadshots());
        } else if (jogador instanceof MOBA) {
            MOBA mobaJogador = (MOBA) jogador;
            System.out.println("Personagem Principal: " + mobaJogador.getPersonagemPrincipal());
            System.out.println("Kills: " + mobaJogador.getKills());
            System.out.println("Assists: " + mobaJogador.getAssists());
            System.out.println("Deaths: " + mobaJogador.getDeaths());
            System.out.println("KDA: " + mobaJogador.getKda());
        } else if (jogador instanceof EFOOTBALL) {
            EFOOTBALL efootballJogador = (EFOOTBALL) jogador;
            if(efootballJogador.getPosicao().equals("guarda-redes")){
                System.out.println("Posição: " + efootballJogador.getPosicao());
                System.out.println("Defesas: " + efootballJogador.getDefesas());
                return;
            }
            System.out.println("Posição: " + efootballJogador.getPosicao());
            System.out.println("Gols: " + efootballJogador.getGolos());
            System.out.println("Assistências: " + efootballJogador.getAssistencias());
        } else {
            System.out.println("Tipo de jogador não reconhecido.");
        }
    }

    public void EditarJogador(String nome, String nickname,String idJogador){
        Jogadores jogadores = JogadorpeloId(idJogador);
        if(!nome.isBlank()){
            jogadores.setNomeJogador(nome);
        }
        if(!nickname.isBlank()){
            jogadores.setNicknameJogador(nickname);
        }
    }

    public void verTorneiosJogador(String idJogador) {
        Jogadores jogadores = JogadorpeloId(idJogador);
        if (jogadores == null) {
            System.out.println("Jogador não encontrado.");
            return;
        }

        List<Torneios> torneios = jogadores.getTorneios(); //
        if (torneios == null || torneios.isEmpty()) {
            System.out.println("O jogador não está inscrito em nenhum torneio.");
        } else {
            System.out.println("=========================================");
            System.out.println("    TORNEIOS EM QUE O JOGADOR PARTICIPA  ");
            System.out.println("=========================================");
            for (Torneios torneio : torneios) {
                System.out.println("- " + torneio.getNomeTorneio());
            }
        }
    }

}