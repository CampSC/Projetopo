package frontend;

import backend.*;

public class Menus {

    private Sistema sistema;
    ListaJogadores listaJogadores = ListaJogadores.getInstancia();
    ListaTreinadores listaTreinadores = ListaTreinadores.getInstancia();
    ListaEquipas listaEquipas = ListaEquipas.getInstancia();
    ListaTorneios listaTorneios = ListaTorneios.getInstancia();
    ListaPartidas listaPartidas = ListaPartidas.getInstancia();
    private TabelaClassificacao tabelaClassificacao;
    Consola consola = new Consola();
    Funcoes funcoes = new Funcoes();
    Equipa equipa = new Equipa();

    public Menus(Sistema sistema){
        this.sistema = sistema;
    }

    public void MenuPrincipal(Admin admin) {
        while (true) {
            System.out.println("=========================================");
            System.out.println("            MENU PRINCIPAL              ");
            System.out.println("=========================================");
            System.out.println("1 - Jogador");
            System.out.println("2 - Treinador");
            System.out.println("3 - Administrador");
            System.out.println("4 - Sair");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-4)");

            switch (opcao) {
                case 1:
                    MenuJogadorInicial();
                    break;
                case 2:
                    MenuTreinadorInicial();
                    break;
                case 3:
                    MenuAdministrador(admin);
                    break;
                case 4:
                    gravarDados();
                    System.out.println("A sair do programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 4.");
            }
        }
    }

    public void gravarDados(){
        sistema.gravarEstado();
    }


    public void MenuJogadorInicial() {
        while (true) {
            System.out.println("=========================================");
            System.out.println("           MENU JOGADOR INICIAL         ");
            System.out.println("=========================================");
            System.out.println("1 - Registo");
            System.out.println("2 - Log in");
            System.out.println("3 - Voltar ao Menu Principal");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-3)");

            switch (opcao) {
                case 1:
                    System.out.println("Você escolheu Registo.");
                    funcoes.CriarJogador();
                    break;
                case 2:
                    String idjogador = funcoes.LoginJogador();

                    if (idjogador != null) {
                        MenuPosLogin(idjogador);
                    }
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 3.");
            }
        }
    }

    public void MenuAdministrador(Admin admin) {
        while (true) {
            System.out.println("=========================================");
            System.out.println("           MENU ADMINISTRADOR           ");
            System.out.println("=========================================");
            System.out.println("1 - Log In");
            System.out.println("2 - Voltar ao Menu Principal");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-2)");

            switch (opcao) {
                case 1:
                    if(funcoes.LoginAdminMenu()){
                        MenuPosLoginAdmin();
                    }
                    break;
                case 2:
                    System.out.println("Voltando ao Menu Principal...");
                    return; // Sai do loop e retorna ao menu principal
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 2.");
            }
        }
    }


    public void MenuTreinadorInicial() {
        while (true) {
            System.out.println("=========================================");
            System.out.println("           MENU TREINADOR INICIAL        ");
            System.out.println("=========================================");
            System.out.println("1 - Registo");
            System.out.println("2 - Log in");
            System.out.println("3 - Voltar ao Menu Principal");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-3)");

            switch (opcao) {
                case 1:
                    System.out.println("Você escolheu Registo.");
                    funcoes.CriarTreinador();
                    break;
                case 2:
                    String idTreinador = funcoes.LoginTreinador();

                    if (idTreinador != null) {
                        MenuPosLoginTreinador(idTreinador);
                    }
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 3.");
            }
        }
    }


    public void MenuPosLogin(String idjogador) {
        while (true) {
            System.out.println("=========================================");
            System.out.println("            MENU POS LOGIN               ");
            System.out.println("=========================================");
            System.out.println("1 - Ver Dados do Jogador");
            System.out.println("2 - Editar Dados do Jogador");
            System.out.println("3 - Ver Torneios em que Participa");
            System.out.println("4 - Ver Estatísticas");
            System.out.println("5 - Sair para o Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-5)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("            DADOS DO JOGADOR             ");
                    System.out.println("=========================================");
                    listaJogadores.mostrarDadosJogador(idjogador);
                    consola.pausar();
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("           EDITAR DADOS JOGADOR          ");
                    System.out.println("=========================================");
                    funcoes.EditarJogador(idjogador);
                    break;
                case 3:
                    System.out.println("=========================================");
                    System.out.println("       TORNEIOS EM QUE PARTICIPA         ");
                    System.out.println("=========================================");
                    listaJogadores.verTorneiosJogador(idjogador);
                    break;
                case 4:
                    System.out.println("=========================================");
                    System.out.println("             ESTATÍSTICAS                ");
                    System.out.println("=========================================");
                    listaJogadores.exibirEstatisticas(idjogador);
                    consola.pausar();

                    break;
                case 5:
                    System.out.println("Saindo para o Menu Anterior...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 5.");
            }
        }
    }

    public void MenuPosLoginAdmin() {
        while (true) {
            System.out.println("=========================================");
            System.out.println("           MENU ADMINISTRADOR            ");
            System.out.println("=========================================");
            System.out.println("1 - Gerir Jogadores e Treinadores");
            System.out.println("2 - Criar e Gerir Torneios");
            System.out.println("3 - Agendar Partidas");
            System.out.println("4 - Registar Resultados e Atualizar Classificações");
            System.out.println("5 - Acompanhar Estatísticas e Resultados do Torneio");
            System.out.println("6 - Sair para o Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-6)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("    GERIR JOGADORES E TREINADORES       ");
                    System.out.println("=========================================");
                    MenuGerirJogadoresTreinadores();
                    consola.pausar();
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("      CRIAR E GERIR TORNEIOS            ");
                    System.out.println("=========================================");
                    MenuGerirTorneios();
                    break;
                case 3:
                    System.out.println("=========================================");
                    System.out.println("           AGENDAR PARTIDAS             ");
                    System.out.println("=========================================");
                    funcoes.CriarPartida();
                    listaPartidas.mostrarPartidas();
                    consola.pausar();
                    break;
                case 4:
                    System.out.println("=========================================");
                    System.out.println("REGISTAR RESULTADOS E ATUALIZAR CLASSIFICAÇÕES");
                    System.out.println("=========================================");
                    // Função para registar resultados e atualizar classificações
                    //funcoes.RegistarResultadosAtualizarClassificacoes();
                    consola.pausar();
                    break;
                case 5:
                    System.out.println("=========================================");
                    System.out.println("ACOMPANHAR ESTATÍSTICAS E RESULTADOS DO TORNEIO");
                    System.out.println("=========================================");
                    // Função para acompanhar estatísticas e resultados
                    //funcoes.AcompanharEstatisticasResultados();
                    consola.pausar();
                    break;
                case 6:
                    System.out.println("Saindo para o Menu Anterior...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 6.");
            }
        }
    }

    /*public void RegistarResultadosAtualizarClassificacoes() {
        // Mostrar partidas agendadas
        System.out.println("=========================================");
        System.out.println("            RESULTADOS DAS PARTIDAS      ");
        System.out.println("=========================================");
        listaPartidas.mostrarPartidas();
        System.out.println("Escolha a partida para registrar o resultado:");

        // Ler a ID da partida
        String idPartida = consola.lerString("Digite o ID da partida:");

        // Obter a partida específica
        Partida partida = listaPartidas.buscarPartidaPeloId(idPartida);

        if (partida == null) {
            System.out.println("Partida não encontrada.");
            return;
        }

        // Pedir os resultados da partida
        String equipaVencedora = String.valueOf(consola.lerInteiro("Equipa vencedora:"));

        // Registrar os resultados

        // Atualizar as classificações


        // Mostrar os resultados
        System.out.println("=========================================");
        System.out.println("Resultado registrado com sucesso!");
        // Atualizando a classificação
        System.out.println("Atualizando a classificação...");
        listaTorneios.atualizarClassificacao(partida.getTorneioId());
        listaTorneios.mostrarClassificacao(partida.getTorneioId());  // Mostrar a classificação após atualização
    }*/


    public void MenuGerirTorneios() {
        while (true) {
            System.out.println("=========================================");
            System.out.println("           MENU GERIR TORNEIOS           ");
            System.out.println("=========================================");
            System.out.println("1 - Criar Torneio");
            System.out.println("2 - Ver Equipas no Torneio");
            System.out.println("3 - Editar Torneio");
            System.out.println("4 - Voltar para o Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-4)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("            CRIAR TORNEIO               ");
                    System.out.println("=========================================");
                    funcoes.CriarTorneio();
                    consola.pausar();
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("         VER EQUIPAS NO TORNEIO         ");
                    System.out.println("=========================================");
                    funcoes.ImprimirEquipaNoTorneio();
                    consola.pausar();
                    break;
                case 3:
                    System.out.println("=========================================");
                    System.out.println("           EDITAR TORNEIO               ");
                    System.out.println("=========================================");
                    //funcoes.EditarTorneio(); // Função para editar o torneio
                    consola.pausar();
                    break;
                case 4:
                    System.out.println("Saindo para o Menu Anterior...");
                    return; // Retorna ao menu anterior
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 4.");
            }
        }
    }


    public void MenuGerirJogadoresTreinadores() {
        while (true) {
            System.out.println("1 - Jogador");
            System.out.println("2 - Treinador");
            System.out.println("3 - Voltar para o Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-3)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("            JOGADOR                     ");
                    System.out.println("=========================================");
                    MenuAdicionarOuRemover("jogador");
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("            TREINADOR                   ");
                    System.out.println("=========================================");
                    MenuAdicionarOuRemover("treinador");
                    break;
                case 3:
                    System.out.println("Voltando para o Menu Anterior...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 3.");
            }
        }
    }


    public void MenuAdicionarOuRemover(String tipo) {
        while (true) {
            System.out.println("=========================================");
            System.out.println("      " + tipo.toUpperCase() + " - ADICIONAR OU REMOVER      ");
            System.out.println("=========================================");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Remover");
            System.out.println("3 - Voltar para o Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-3)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("          ADICIONAR " + tipo.toUpperCase() + "          ");
                    System.out.println("=========================================");
                    if (tipo.equalsIgnoreCase("jogador")) {

                    } else if (tipo.equalsIgnoreCase("treinador")) {
                        funcoes.CriarTreinador();
                    }
                    consola.pausar();
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("          REMOVER " + tipo.toUpperCase() + "          ");
                    System.out.println("=========================================");
                    if (tipo.equalsIgnoreCase("jogador")) {
                        funcoes.RemoverJogador();
                    } else if (tipo.equalsIgnoreCase("treinador")) {
                        funcoes.RemoverTreinador();
                    }
                    consola.pausar();
                    break;
                case 3:
                    System.out.println("Voltando para o Menu Anterior...");
                    return;  // Sai do loop e volta para o menu anterior
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 3.");
            }
        }
    }

    public void MenuPosLoginTreinador(String idTreinador) {
        while (true) {
            System.out.println("=========================================");
            System.out.println("            MENU POS LOGIN               ");
            System.out.println("=========================================");
            System.out.println("1 - Criar Equipa");
            System.out.println("2 - Gerir Equipa");
            System.out.println("3 - Inscrever Equipa em Torneios");
            System.out.println("4 - Dados da Equipa");
            System.out.println("5 - Sair para o Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-5)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("              CRIAR EQUIPA               ");
                    System.out.println("=========================================");
                    funcoes.CriarEquipa(idTreinador);
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("             GERIR EQUIPA                ");
                    System.out.println("=========================================");
                    MenuGerirEquipa(idTreinador);
                    break;
                case 3:
                    System.out.println("=========================================");
                    System.out.println("      INSCREVER EQUIPA EM TORNEIOS       ");
                    System.out.println("=========================================");
                    funcoes.RegistarEquipaTorneio(idTreinador);
                    break;
                case 4:
                    System.out.println("=========================================");
                    System.out.println("             DADOS DA EQUIPA             ");
                    System.out.println("=========================================");
                    listaEquipas.MostrarJogadoresNaEquipa(idTreinador);
                    consola.pausar();
                    break;
                case 5:
                    System.out.println("Saindo para o Menu Anterior...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 5.");
            }
        }
    }

    public void MenuGerirEquipa(String idTreinador) {
        while (true) {
            System.out.println("=========================================");
            System.out.println("             MENU GERIR EQUIPA           ");
            System.out.println("=========================================");
            System.out.println("1 - Adicionar Jogadores");
            System.out.println("2 - Remover Jogador");
            System.out.println("3 - Editar Detalhes da Equipa");
            System.out.println("4- Voltar ao Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-4)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("          ADICIONAR JOGADORES            ");
                    System.out.println("=========================================");
                    Treinadores treinadores = listaTreinadores.treinadorPeloId(idTreinador);
                    System.out.println("Equipa: " + treinadores.getEquipaGerida().getNomeEquipa());
                    funcoes.AdicionarJogadoresEquipa(idTreinador);
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("           REMOVER JOGADOR               ");
                    System.out.println("=========================================");
                    funcoes.RemoverJogadoresEquipa(idTreinador);
                    break;
                case 3:
                    System.out.println("=========================================");
                    System.out.println("         EDITAR DETALHES DA EQUIPA       ");
                    System.out.println("=========================================");
                    funcoes.EditarEquipa(idTreinador);
                    break;
                case 4:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 4.");
            }
        }
    }
}
