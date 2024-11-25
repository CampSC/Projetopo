package frontend;
import backend.*;

public class Funcoes {
    ListaJogadores listaJogadores = ListaJogadores.getInstancia();
    ListaTreinadores listaTreinadores = ListaTreinadores.getInstancia();
    ListaEquipas listaEquipas = ListaEquipas.getInstancia();

    Equipa equipa = new Equipa();
    Treinadores treinadores = new Treinadores();
    MOBA moba = new MOBA();
    FPS fps = new FPS();
    EFOOTBALL efootball = new EFOOTBALL();
    Consola consola = new Consola();

    public String LoginJogador() {
        String nickname = consola.lerString("Insira o seu nickname");
        String password = consola.lerString("Insira a sua palavra passe");

        String idjogador = listaJogadores.LoginJogador(nickname, password);

        if (idjogador == null) {
            return null;
        }
        return idjogador;
    }

    public String LoginTreinador() {
        String email = consola.lerString("Insira o seu email");
        String password = consola.lerString("Insira a sua palavra passe");

        String idtreinador = listaTreinadores.LoginTreinador(email, password);

        if (idtreinador == null) {
            return null;
        }
        return idtreinador;
    }

    public boolean CriarJogador() {
        String nome = consola.lerString("Insira o seu nome");
        String nickname = consola.lerString("Insira o seu nickname");
        String password = consola.lerString("Insira uma password");
        String tipoJogador = null;

        // Escolher tipo de jogador
        System.out.println("Escolha um tipo de jogador");
        System.out.println("1-FPS");
        System.out.println("2-MOBA");
        System.out.println("3-EFOOTBALL");
        int opcao = consola.lerInteiro("Opção");

        switch (opcao) {
            case 1:
                tipoJogador = "FPS";
                break;
            case 2:
                tipoJogador = "MOBA";
                break;
            case 3:
                tipoJogador = "EFOOTBALL";
                break;
            default:
                System.out.println("Opção inválida! Por favor, insira um número entre 1 e 3.");
                return false;
        }

        String posicao = null;

        if (tipoJogador.equals("EFOOTBALL")) {
            System.out.println("Escolha uma posição");
            System.out.println("1-guarda-redes");
            System.out.println("2-defesa");
            System.out.println("3-medio");
            System.out.println("4-avancado");
            int opcaoposicao = consola.lerInteiro("Opção");
            switch (opcaoposicao) {
                case 1:
                    posicao = "guarda-redes";
                    break;
                case 2:
                    posicao = "defesa";
                    break;
                case 3:
                    posicao = "medio";
                    break;
                case 4:
                    posicao = "avancado";
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 4");
                    return false;
            }
        }
        Jogadores novoJogador = null;

        switch (tipoJogador) {
            case "FPS":
                String idJogadorF = fps.generateUniqueId();
                novoJogador = new FPS(idJogadorF,nome, nickname, tipoJogador, password);
                break;
            case "MOBA":
                String idJogadorM = moba.generateUniqueId();
                novoJogador = new MOBA(idJogadorM,nome, nickname, tipoJogador, password);
                break;
            case "EFOOTBALL":
                String idJogadorE = efootball.generateUniqueId();
                novoJogador = new EFOOTBALL(idJogadorE,nome, nickname, tipoJogador, password, posicao);
                break;
        }


        if(!listaJogadores.adicionarJogadores(novoJogador)){
            System.out.println("Erro ao criar usuario!");
            return false;
        }
        return true;
    }

    public boolean CriarTreinador(){
        String nome = consola.lerString("Insira o seu nome");
        String email = consola.lerString("Insira o seu email");
        String password = consola.lerString("Insira a sua password");
        String idTreinador = treinadores.generateUniqueId();

        Treinadores treinadores = new Treinadores(idTreinador, nome, email, password);

        if(!listaTreinadores.adicionarTreinador(treinadores)){
            System.out.println("Erro ao criar Treinador");
            return false;
        }
        return true;
    }

    public boolean CriarEquipa(String idTreinador){
        String nome = consola.lerString("Insira o nome da equipa");
        String tipoEquipa = null;

        System.out.println("Escolha o tipo da equipa");
        System.out.println("1-FPS");
        System.out.println("2-MOBA");
        System.out.println("3-EFOOTBALL");
        int opcao = consola.lerInteiro("Opção");

        switch (opcao) {
            case 1:
                tipoEquipa = "FPS";
                break;
            case 2:
                tipoEquipa = "MOBA";
                break;
            case 3:
                tipoEquipa = "EFOOTBALL";
                break;
            default:
                System.out.println("Opção inválida! Por favor, insira um número entre 1 e 3.");
                return false;
        }

        String idEquipa = equipa.generateUniqueId();

        Equipa equipa = new Equipa(idEquipa, nome, tipoEquipa);
        if(!listaEquipas.adicionarEquipa(equipa)){
            System.out.println("Erro ao criar equipa");
            return false;
        }
        Treinadores treinador = listaTreinadores.treinadorPeloId(idTreinador);
        if (treinador != null) {
            treinador.setEquipaGerida(equipa); // Atribui o nome da equipa ao treinador
            System.out.println("A equipa " + nome + " foi criada e atribuída ao treinador " + treinador.getNomeTreinador());
        }
        return true;
    }

    public boolean EditarJogador(String idJogador){
        System.out.println("Se não quiser alterar pressione Enter!!!");
        String novoNome = consola.lerString("Insira o novo nome");
        String novoNickName =  consola.lerString("Insira o novo nickname");
        listaJogadores.EditarJogador(novoNome,novoNickName,idJogador);
        return true;
    }

    /*public boolean EditarTreinador(String idTreinador){
        System.out.println("Se não quiser alterar pressione Enter!!!");
        String novoNome = consola.lerString("Insira o novo nome");
        String novoEmail =  consola.lerString("Insira o novo email");
        listaJogadores.EditarJogador(novoNome,novoEmail,idTreinador);
        return true;
    }*/

    public boolean AdicionarJogadoresEquipa(String idTreinador) {
        Treinadores treinador = listaTreinadores.treinadorPeloId(idTreinador);

        Equipa equipa = treinador.getEquipaGerida();

        String idJogador = consola.lerString("Insira o id do jogador que quer adicionar");
        Jogadores j = listaJogadores.JogadorpeloId(idJogador);
        if (j == null) {
            System.out.println("Jogador não encontrado com ID: " + idJogador);
            return false;
        }
        if(equipa.getJogadoresnaequipa().contains(j)){
            System.out.println("Esse jogador ja esta adicionado a essa equipa");
            return false;
        }

        if (!equipa.getTipoEquipa().equals(j.getTipoJogador())) {
            System.out.println("O tipo de jogador não corresponde ao tipo de equipa.");
            return false;
        }

        System.out.println("Jogador antes de adicionar: " + equipa.getJogadoresnaequipa().size());
        equipa.getJogadoresnaequipa().add(j);
        System.out.println("Jogador depois de adicionar: " + equipa.getJogadoresnaequipa().size());

        return true;
    }

}
