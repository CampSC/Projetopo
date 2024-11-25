package frontend;

import backend.Equipa;
import backend.ListaEquipas;
import backend.ListaJogadores;
import backend.ListaTreinadores;

public class Menus {
    ListaJogadores listaJogadores = ListaJogadores.getInstancia();
    ListaTreinadores listaTreinadores = ListaTreinadores.getInstancia();
    ListaEquipas listaEquipas = ListaEquipas.getInstancia();
    Consola consola = new Consola();
    Funcoes funcoes = new Funcoes();
    Equipa equipa = new Equipa();

    public void MenuPrincipal() {
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
                    System.out.println("Você escolheu Administrador.");
                    break;
                case 4:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 4.");
            }
        }
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
                    //listaJogadores.verTorneiosJogador(idjogador);
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
                    //funcoes.InscreverEquipaTorneio(idTreinador);
                    break;
                case 4:
                    System.out.println("=========================================");
                    System.out.println("             DADOS DA EQUIPA             ");
                    System.out.println("=========================================");
                    //funcoes.MostrarDadosEquipa(idTreinador);
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
            System.out.println("4-  Ver Jogadores da Equipa");
            System.out.println("5- Voltar ao Menu Anterior");
            System.out.println("=========================================");

            int opcao = consola.lerInteiro("Escolha uma opção (1-4)");

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.println("          ADICIONAR JOGADORES            ");
                    System.out.println("=========================================");
                    funcoes.AdicionarJogadoresEquipa(idTreinador);
                    break;
                case 2:
                    System.out.println("=========================================");
                    System.out.println("           REMOVER JOGADOR               ");
                    System.out.println("=========================================");
                    //funcoes.RemoverJogador(idTreinador);
                    break;
                case 3:
                    System.out.println("=========================================");
                    System.out.println("         EDITAR DETALHES DA EQUIPA       ");
                    System.out.println("=========================================");
                    //funcoes.EditarEquipa(idTreinador);
                    break;
                case 4:
                    System.out.println("=========================================");
                    System.out.println("         VER JOGADORES DA EQUIPA       ");
                    System.out.println("=========================================");
                    listaEquipas.MostrarJogadoresNaEquipa(idTreinador);
                case 5:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número entre 1 e 4.");
            }
        }
    }



}
