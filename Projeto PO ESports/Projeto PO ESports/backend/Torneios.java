package backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Torneios implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 1;
    private String idTorneio;
    private String nomeTorneio;
    private String jogo;
    private List<Equipa> equipasParticipantes;
    private List<Partida> listaDePartidas;
    private TabelaClassificacao tabelaClassificacao;
    private int resultado;

    public Torneios(String nomeTorneio, String jogo, List<Equipa> listaEquipas) {
        this.idTorneio = "L" + contador++;
        this.nomeTorneio = nomeTorneio;
        this.jogo = jogo;
        this.equipasParticipantes = new ArrayList<>();
        this.listaDePartidas = new ArrayList<>();
        this.tabelaClassificacao = new TabelaClassificacao();

        // Adicionar as equipes fornecidas na listaEquipas
        for (Equipa equipa : listaEquipas) {
            if (equipa.getTipoEquipa().equalsIgnoreCase(jogo)) {
                this.equipasParticipantes.add(equipa);
                this.tabelaClassificacao.adicionarEquipa(equipa);
            } else {
                System.out.println("Equipa " + equipa.getNomeEquipa() + " não é compatível com o jogo " + jogo);
            }
        }
    }

    public String getIdTorneio() {
        return idTorneio;
    }

    public String getNomeTorneio() {
        return nomeTorneio;
    }

    public void setNomeTorneio(String nomeTorneio) {
        this.nomeTorneio = nomeTorneio;
    }

    public String getJogo() {
        return jogo;
    }

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    public List<Equipa> getEquipasParticipantes() {
        return equipasParticipantes;
    }

    public List<Partida> getListaDePartidas() {
        return listaDePartidas;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public boolean adicionarEquipa(Equipa equipa) {
        if (!equipasParticipantes.contains(equipa)) {
            if (equipa.getTipoEquipa().equalsIgnoreCase(jogo)) {
                equipasParticipantes.add(equipa);
                tabelaClassificacao.adicionarEquipa(equipa);
                return true;
            } else {
                System.out.println("O tipo da equipa não é compatível com o jogo do torneio.");
                return false;
            }
        } else {
            System.out.println("A equipa já está inscrita no torneio.");
            return false;
        }
    }

    public void removerEquipa(Equipa equipa) {
        if (equipasParticipantes.contains(equipa)) {
            equipasParticipantes.remove(equipa);
            tabelaClassificacao.removerEquipa(equipa);
        } else {
            System.out.println("A equipa não está inscrita no torneio.");
        }
    }

    public void agendarPartida(Partida partida) {
        listaDePartidas.add(partida);
    }

    public void registrarResultado(Partida partida, String resultado) {
        if (listaDePartidas.contains(partida)) {
            partida.setResultado(resultado);
            tabelaClassificacao.atualizarClassificacao(partida);
        } else {
            System.out.println("Partida não encontrada na lista de partidas do torneio.");
        }
    }

    public void mostrarEstatisticas() {
        System.out.println("Torneio: " + nomeTorneio);
        System.out.println("Jogo: " + jogo);
        System.out.println("Equipes Participantes:");
        for (Equipa equipa : equipasParticipantes) {
            System.out.println("- " + equipa.getNomeEquipa());
        }
        System.out.println("\nPartidas:");
        for (Partida partida : listaDePartidas) {
            System.out.println(partida);
        }
        System.out.println("\nTabela de Classificação:");
        tabelaClassificacao.obterClassificacao();
    }

    public void gerarPartidasAutomaticamente(LocalDate dataInicial) {
        if (equipasParticipantes.size() < 2) {
            System.out.println("Não há equipes suficientes para gerar partidas.");
            return;
        }

        Set<String> partidasGeradas = new HashSet<>(); // Para evitar duplicatas
        Random random = new Random();
        int diasIntervalo = 1;

        for (int i = 0; i < equipasParticipantes.size(); i++) {
            for (int j = i + 1; j < equipasParticipantes.size(); j++) {
                Equipa equipaA = equipasParticipantes.get(i);
                Equipa equipaB = equipasParticipantes.get(j);

                // Criar um identificador único para evitar duplicatas
                String identificadorPartida = equipaA.getIdEquipa() + "-" + equipaB.getIdEquipa();
                if (partidasGeradas.contains(identificadorPartida)) {
                    continue;
                }

                LocalDate dataPartida = dataInicial.plusDays(diasIntervalo);
                Partida novaPartida = new Partida(equipaA, equipaB, dataPartida);
                listaDePartidas.add(novaPartida);
                partidasGeradas.add(identificadorPartida);
                diasIntervalo++;

                System.out.println("Partida criada: " + novaPartida);
            }
        }
    }

    @Override
    public String toString() {
        return "Torneio{" +
                "idTorneio=" + idTorneio +
                ", nomeTorneio='" + nomeTorneio + '\'' +
                ", jogo='" + jogo + '\'' +
                ", equipasParticipantes=" + equipasParticipantes +
                ", listaDePartidas=" + listaDePartidas +
                ", tabelaClassificacao=" + tabelaClassificacao +
                '}';
    }
}