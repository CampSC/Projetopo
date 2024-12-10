package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaClassificacao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<Equipa, EstatisticasEquipa> classificacao;

    // Construtor
    public TabelaClassificacao() {
        this.classificacao = new HashMap<>();
    }

    // Classe interna para armazenar as estatísticas de cada equipa
    private static class EstatisticasEquipa {
        private int vitorias;
        private int empates;
        private int derrotas;
        private int pontos;

        public EstatisticasEquipa() {
            this.vitorias = 0;
            this.empates = 0;
            this.derrotas = 0;
            this.pontos = 0;
        }

        public void registrarVitoria() {
            this.vitorias++;
            this.pontos += 3; // Vitória vale 3 pontos
        }

        public void registrarEmpate() {
            this.empates++;
            this.pontos += 1; // Empate vale 1 ponto
        }

        public void registrarDerrota() {
            this.derrotas++;
        }

        public int getPontos() {
            return pontos;
        }

        @Override
        public String toString() {
            return "Vitorias: " + vitorias + ", Empates: " + empates + ", Derrotas: " + derrotas + ", Pontos: " + pontos;
        }
    }

    // Métodos principais
    public void adicionarEquipa(Equipa equipa) {
        if (!classificacao.containsKey(equipa)) {
            classificacao.put(equipa, new EstatisticasEquipa());
        }
    }

    public void removerEquipa(Equipa equipa) {
        classificacao.remove(equipa);
    }

    public void atualizarClassificacao(Partida partida) {
        Equipa equipaA = partida.getEquipaA();
        Equipa equipaB = partida.getEquipaB();
        String resultado = partida.getResultado();

        adicionarEquipa(equipaA);
        adicionarEquipa(equipaB);

        EstatisticasEquipa estatisticasA = classificacao.get(equipaA);
        EstatisticasEquipa estatisticasB = classificacao.get(equipaB);

        // Processa o resultado no formato "X-Y"
        try {
            String[] placar = resultado.split("-");
            int pontosA = Integer.parseInt(placar[0].trim());
            int pontosB = Integer.parseInt(placar[1].trim());

            if (pontosA > pontosB) {
                estatisticasA.registrarVitoria();
                estatisticasB.registrarDerrota();
            } else if (pontosA < pontosB) {
                estatisticasB.registrarVitoria();
                estatisticasA.registrarDerrota();
            } else {
                estatisticasA.registrarEmpate();
                estatisticasB.registrarEmpate();
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar resultado da partida: " + resultado);
        }
    }

    public List<Map.Entry<Equipa, EstatisticasEquipa>> obterClassificacao() {
        List<Map.Entry<Equipa, EstatisticasEquipa>> listaClassificacao = new ArrayList<>(classificacao.entrySet());
        listaClassificacao.sort((e1, e2) -> Integer.compare(e2.getValue().getPontos(), e1.getValue().getPontos()));
        return listaClassificacao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tabela de Classificação:\n");
        for (Map.Entry<Equipa, EstatisticasEquipa> entry : obterClassificacao()) {
            sb.append(entry.getKey().getNomeEquipa())
                    .append(" -> ")
                    .append(entry.getValue().toString())
                    .append("\n");
        }
        return sb.toString();
    }
}