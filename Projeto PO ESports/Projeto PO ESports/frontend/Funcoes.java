package frontend;
import backend.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Funcoes {
    ListaJogadores listaJogadores = ListaJogadores.getInstancia();
    ListaTreinadores listaTreinadores = ListaTreinadores.getInstancia();
    ListaEquipas listaEquipas = ListaEquipas.getInstancia();
    ListaTorneios listaTorneios = ListaTorneios.getInstancia();
    ListaPartidas listaPartidas = ListaPartidas.getInstancia();

    Equipa equipa = new Equipa();
    Treinadores treinadores = new Treinadores();
    MOBA moba = new MOBA();
    FPS fps = new FPS();
    EFOOTBALL efootball = new EFOOTBALL();
    Consola consola = new Consola();
    Admin admin = new Admin();
    Partida partida = new Partida();
    Sistema sistema = new Sistema();

    public String LoginJogador() {
        String nickname = consola.lerString("Insira o seu nickname");
        String password = consola.lerString("Insira a sua palavra passe");

        String idjogador = sistema.getListaJogadores().LoginJogador(nickname, password);

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

    public boolean LoginAdminMenu() {
        String email = consola.lerString("Insira o seu email ");
        String password = consola.lerString("Insira a sua palavra passe ");

        if (Admin.getAdminInstance().LoginAdmin(email, password)) {
            return true;
        } else {
            System.out.println("Falha no login. Verifique as credenciais.");
            return false;
        }
    }



    public boolean CriarJogador() {
        String nome = consola.lerString("Insira o seu nome");
        String nickname = consola.lerString("Insira o seu nickname");
        String password = consola.lerString("Insira uma password");
        String tipoJogador = null;

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
                novoJogador = new FPS(nome, nickname, tipoJogador, password);
                break;
            case "MOBA":
                novoJogador = new MOBA(nome, nickname, tipoJogador, password);
                break;
            case "EFOOTBALL":
                novoJogador = new EFOOTBALL(nome, nickname, tipoJogador, password, posicao);
                break;
        }


        if(!listaJogadores.adicionarJogadores(novoJogador)){
            System.out.println("Erro ao criar usuario!");
            return false;
        }

        sistema.gravarEstado();

        return true;
    }

    public boolean CriarTreinador(){
        String nome = consola.lerString("Insira o seu nome");
        String email = consola.lerString("Insira o seu email");
        String password = consola.lerString("Insira a sua password");

        Treinadores treinadores = new Treinadores(nome, email, password);

        if(!listaTreinadores.adicionarTreinador(treinadores)){
            System.out.println("Erro ao criar Treinador");
            return false;
        }
        return true;
    }

    public Admin criarAdmin(){
        String idAdmin = admin.generateUniqueId();
        Admin novoAdmin = new Admin(idAdmin, "admin", "admin@gmail.com", "admin");
        Admin.admin = novoAdmin;
        return admin;
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


        Equipa equipa = new Equipa(nome, tipoEquipa);
        if(!listaEquipas.adicionarEquipa(equipa)){
            System.out.println("Erro ao criar equipa");
            return false;
        }
        Treinadores treinador = listaTreinadores.treinadorPeloId(idTreinador);
        if (treinador != null) {
            treinador.setEquipaGerida(equipa);
            System.out.println("A equipa " + nome + " foi criada e atribuída ao treinador " + treinador.getNomeTreinador());
        }
        return true;
    }

    public boolean CriarTorneio() {
        String nomeTorneio = consola.lerString("Insira o nome do torneio");

        System.out.println("Escolha o tipo de jogo para o torneio:");
        System.out.println("1 - FPS");
        System.out.println("2 - MOBA");
        System.out.println("3 - EFOOTBALL");
        int opcao = consola.lerInteiro("Opção");

        String tipoJogo = null;

        switch (opcao){
            case 1:
                tipoJogo = "FPS";
                break;
            case 2:
                tipoJogo = "MOBA";
                break;
            case 3:
                tipoJogo = "EFOOTBALL";
                break;
            default:
                System.out.println("Opção inválida! Por favor, insira um número entre 1 e 3.");
                return false;
        }

        Torneios novoTorneio = new Torneios(nomeTorneio, tipoJogo, null);


        System.out.println("Agora, vamos adicionar equipes ao torneio.");
        while (true) {
            // Listar as equipas disponíveis para o tipo de jogo do torneio
            System.out.println("Equipes disponíveis para o tipo: " + tipoJogo);
            List<Equipa> equipasDisponiveis = listaEquipas.EquipaPorTipo(tipoJogo);

            if (equipasDisponiveis.isEmpty()) {
                System.out.println("Nenhuma equipe disponível para este tipo de jogo.");
                break;
            }

            for (int i = 0; i < equipasDisponiveis.size(); i++) {
                Equipa equipe = equipasDisponiveis.get(i);
                System.out.println((i + 1) + " - Nome: " + equipe.getNomeEquipa() + " | ID: " + equipe.getIdEquipa());
            }

            // Solicitar ao usuário que escolha uma equipe
            int escolha = consola.lerInteiro("Escolha uma equipe para adicionar ao torneio (0 para sair):");
            if (escolha == 0) {
                break;
            }

            if (escolha < 1 || escolha > equipasDisponiveis.size()) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }

            Equipa equipaEscolhida = equipasDisponiveis.get(escolha - 1);

            // Adicionar equipe ao torneio
            if (!novoTorneio.adicionarEquipa(equipaEscolhida)) {
                System.out.println("Erro ao adicionar a equipa ao torneio. Verifique se ela já foi adicionada.");
            } else {
                System.out.println("Equipa adicionada com sucesso!");
            }
        }

        // Verificar se pelo menos uma equipe foi adicionada
        if (novoTorneio.getEquipasParticipantes().isEmpty()) {
            System.out.println("Nenhuma equipe foi adicionada. O torneio não será criado.");
            return false;
        }

        // Adicionar o torneio à lista de torneios
        if (!listaTorneios.adicionarTorneio(novoTorneio)) {
            System.out.println("Erro ao criar o torneio!");
            return false;
        }

        System.out.println("Torneio criado com sucesso!");
        return true;
    }

    public boolean CriarPartida() {
        System.out.println("Insira o id das equipas para a partida:");
        String idEquipaA = consola.lerString("Insira o id da equipa A");
        String idEquipaB = consola.lerString("Insira o id da equipa B");

        if (!listaEquipas.ExisteEquipa(idEquipaA)) {
            System.out.println("O ID da equipa A não existe.");
            return false;
        }
        if (!listaEquipas.ExisteEquipa(idEquipaB)) {
            System.out.println("O ID da equipa B não existe.");
            return false;
        }

        Equipa equipaA = listaEquipas.EquipapeloId(idEquipaA);
        Equipa equipaB = listaEquipas.EquipapeloId(idEquipaB);

        if (equipaA.equals(equipaB)) {
            System.out.println("As equipas não podem ser iguais.");
            return false;
        }

        Torneios torneioComum = null;
        for (Torneios torneio : ListaTorneios.getInstancia().getListaTorneios()) {
            if (torneio.getEquipasParticipantes().contains(equipaA) &&
                    torneio.getEquipasParticipantes().contains(equipaB)) {
                torneioComum = torneio;
                break;
            }
        }

        if (torneioComum == null) {
            System.out.println("As equipas não estão registradas no mesmo torneio.");
            return false;
        }

        LocalDate data = LocalDate.now();
        Partida partida = new Partida(equipaA, equipaB, data);

        torneioComum.agendarPartida(partida);
        if (listaPartidas.adicionarPartida(partida)) {
            System.out.println("Partida adicionada com sucesso ao torneio: " + torneioComum.getNomeTorneio());
            return true;
        }

        System.out.println("Erro ao criar a partida.");
        return false;
    }


    public boolean EditarJogador(String idJogador){
        System.out.println("Se não quiser alterar pressione Enter!!!");
        String novoNome = consola.lerString("Insira o novo nome");
        String novoNickName =  consola.lerString("Insira o novo nickname");
        listaJogadores.EditarJogador(novoNome,novoNickName,idJogador);
        return true;
    }

    public boolean EditarEquipa(String idTreinador){
        System.out.println("Se não quiser alterar pressione Enter!!!");
        String novoNome = consola.lerString("Insira o novo nome");
        Treinadores treinadores = listaTreinadores.treinadorPeloId(idTreinador);
        Equipa equipa = listaEquipas.EquipapeloId(treinadores.getEquipaGerida().getIdEquipa());
        listaEquipas.EditarEquipa(novoNome, equipa);
        return true;
    }

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

    public void RemoverJogadoresEquipa(String idTreinador){
        Treinadores treinadores = listaTreinadores.treinadorPeloId(idTreinador);
        Equipa equipa = listaEquipas.EquipapeloId(treinadores.getEquipaGerida().getIdEquipa());
        String idJogador = consola.lerString("Insira o id do jogador que quer remover");
        Jogadores jogadores = listaJogadores.JogadorpeloId(idJogador);
        if(equipa.getJogadoresnaequipa().remove(jogadores)){
            System.out.println("Jogador removido com sucesso");
        }else{
            System.out.println("Esse jogador não se encontra nesta equipa");
        }
    }

    public void RemoverJogador(){
        String idJogador = consola.lerString("Insira o id do jogador que quer remover");
        listaJogadores.removerJogador(idJogador);
    }

    public void RemoverTreinador(){
        String idTreinador = consola.lerString("Insira o id do treinador que quer remover");
        listaTreinadores.removerTreinador(idTreinador);
    }

    public boolean RegistarEquipaTorneio(String idTreinador) {
        // Procurar o treinador pelo ID
        Treinadores treinadores = listaTreinadores.treinadorPeloId(idTreinador);
        if (treinadores == null) {
            System.out.println("Treinador não encontrado.");
            return false;
        }

        // Verificar se o treinador tem uma equipa associada
        Equipa equipaGerida = treinadores.getEquipaGerida();
        if (equipaGerida == null) {
            System.out.println("Nenhuma equipa está associada ao treinador.");
            return false;
        }

        // Obter o ID do torneio
        String idTorneio = consola.lerString("Insira o ID do torneio que quer registar a equipa:");

        // Verificar se o torneio existe
        if (!listaTorneios.ExisteTorneio(idTorneio)) {
            System.out.println("Esse ID de torneio não existe.");
            return false;
        }

        // Obter o torneio
        Torneios torneios = listaTorneios.TorneioPeloId(idTorneio);
        if (torneios == null) {
            System.out.println("Erro ao recuperar o torneio.");
            return false;
        }

        // Verificar se a equipa já está registada no torneio
        if (listaTorneios.existeEquipaNoTorneio(torneios, equipaGerida)) {
            System.out.println("A equipa já está registada neste torneio.");
            return false;
        }

        // Verificar se o tipo da equipa corresponde ao tipo de jogo do torneio
        if (equipaGerida.getTipoEquipa().equals(torneios.getJogo())) {
            torneios.adicionarEquipa(equipaGerida);
            System.out.println("Equipa adicionada ao torneio com sucesso!");
            return true;
        } else {
            System.out.println("Erro: O tipo de equipa não corresponde ao tipo de jogo do torneio.");
            return false;
        }
    }

    public boolean ImprimirEquipaNoTorneio() {
        String idTorneio = consola.lerString("Insira o ID do torneio ");

        List<Equipa> listaEquipaTorneio = listaTorneios.VerEquipasNoTorneio(idTorneio);

        if (listaEquipaTorneio == null) {
            return false;
        }

        for (Equipa equipa : listaEquipaTorneio) {
            System.out.println("Nome: " + equipa.getNomeEquipa() + " Id: " + equipa.getIdEquipa());
        }

        return true;
    }

    public void recolherEstatisticas(String tipoPartida, ArrayList<Jogadores>  jogador){
        if(tipoPartida.equals("EFOOTBALL")){

            Map<String, Object> novasEstatisticas = new HashMap<>();
            novasEstatisticas.put("golos", consola.lerInteiro("Digite o número de gols:"));
            novasEstatisticas.put("assistencias", consola.lerInteiro("Digite o número de assistências:"));
            novasEstatisticas.put("defesas", consola.lerInteiro("Digite o número de defesas:"));

            for(Jogadores jogadores : jogador){
                Funcoes.atualizarEstatisticasPorTipo(jogadores, novasEstatisticas);
            }
        }

        if(tipoPartida.equals("FPS")){
            Map<String, Object> novasEstatisticas = new HashMap<>();
            novasEstatisticas.put("Precisão", consola.lerFloat("Digite a percentagem de headshots:"));
            novasEstatisticas.put("Headshots", consola.lerInteiro("Digite o número de Headshots:"));

            for(Jogadores jogadores : jogador){
                Funcoes.atualizarEstatisticasPorTipo(jogadores, novasEstatisticas);
            }
        }

        if(tipoPartida.equals("MOBA")){
            Map<String, Object> novasEstatisticas = new HashMap<>();
            novasEstatisticas.put("Kills", consola.lerInteiro("Digite o numero de kills:"));
            novasEstatisticas.put("Assists",consola.lerInteiro("Digite o número de assists:"));
            novasEstatisticas.put("Deaths", consola.lerInteiro("Digite o número de deaths:"));

            for(Jogadores jogadores : jogador){
                Funcoes.atualizarEstatisticasPorTipo(jogadores, novasEstatisticas);
            }
        }
    }

    public static void atualizarEstatisticasPorTipo(Jogadores jogador, Map<String, Object> novasEstatisticas) {
        String tipo = jogador.getTipoJogador();

        switch (tipo) {
            case "FPS":
                if (novasEstatisticas.containsKey("precisao") && novasEstatisticas.containsKey("headshots")) {
                    float novaPrecisao = (float) novasEstatisticas.get("precisao");
                    int novosHeadshots = (int) novasEstatisticas.get("headshots");
                    atualizarEstatisticasFPS((FPS) jogador, novaPrecisao, novosHeadshots);
                } else {
                    System.out.println("Dados de estatísticas para FPS incompletos.");
                }
                break;
            case "MOBA":
                if (novasEstatisticas.containsKey("kills") && novasEstatisticas.containsKey("assists") && novasEstatisticas.containsKey("minions")) {
                    int novosKills = (int) novasEstatisticas.get("kills");
                    int novosAssists = (int) novasEstatisticas.get("assists");
                    int novosMinions = (int) novasEstatisticas.get("minions");
                    atualizarEstatisticasMOBA((MOBA) jogador, novosKills, novosAssists, novosMinions);
                } else {
                    System.out.println("Dados de estatísticas para MOBA incompletos.");
                }
                break;
            case "EFOOTBALL":
                if (novasEstatisticas.containsKey("golos") && novasEstatisticas.containsKey("assistencias") && novasEstatisticas.containsKey("defesas")) {
                    int novosGolos = (int) novasEstatisticas.get("golos");
                    int novasAssistencias = (int) novasEstatisticas.get("assistencias");
                    int novasDefesas = (int) novasEstatisticas.get("defesas");
                    atualizarEstatisticasEFOOTBALL((EFOOTBALL) jogador, novosGolos, novasAssistencias, novasDefesas);
                } else {
                    System.out.println("Dados de estatísticas para EFOOTBALL incompletos.");
                }
                break;
            default:
                System.out.println("Tipo de jogador inválido: " + tipo);
                break;
        }
    }


    // Método para atualizar as estatísticas de jogadores do tipo EFOOTBALL
    private static void atualizarEstatisticasEFOOTBALL(EFOOTBALL jogador, int golos, int assistencias, int defesas) {
        System.out.println("Atualizando estatísticas do jogador EFOOTBALL: " + jogador.getNomeJogador());
        jogador.setGolos(jogador.getGolos() + golos);  // Atualizar gols
        jogador.setAssistencias(jogador.getAssistencias() + assistencias);  // Atualizar assistências
        jogador.setDefesas(jogador.getDefesas() + defesas);  // Atualizar defesas
    }

    // Método para atualizar as estatísticas de jogadores do tipo FPS
    private static void atualizarEstatisticasFPS(FPS jogador, float precisao, int headshots) {
        System.out.println("Atualizando estatísticas do jogador FPS: " + jogador.getNomeJogador());
        jogador.setPrecisao(jogador.getPrecisao() * precisao);  // Atualizar precisão
        jogador.setHeadshots(jogador.getHeadshots() + headshots);  // Atualizar headshots
    }

    // Método para atualizar as estatísticas de jogadores do tipo MOBA
    private static void atualizarEstatisticasMOBA(MOBA jogador, int kills, int assists, int novosMinions) {
        System.out.println("Atualizando estatísticas do jogador MOBA: " + jogador.getNomeJogador());
        jogador.setKills(jogador.getKills() + kills);  // Atualizar kills
        jogador.setAssists(jogador.getAssists() + assists);  // Atualizar assists// Atualizar minions farmed
    }
}

