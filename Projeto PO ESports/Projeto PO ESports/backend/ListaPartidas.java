package backend;

import java.util.ArrayList;

public class ListaPartidas {

    private static ListaPartidas instanciaUnica = null;

    private ArrayList<Partida> listaPartidas;

    private ListaPartidas() {
        listaPartidas = new ArrayList<>();
    }

    public static ListaPartidas getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ListaPartidas();
        }
        return instanciaUnica;
    }

    public boolean adicionarPartida(Partida partida) {
        if (listaPartidas.contains(partida)) {
            System.out.println("Essa partida já está registrada.");
            return false;
        }
        listaPartidas.add(partida);
        return true;
    }


    public Partida buscarPartidaPeloId(String idPartida) {
        for (Partida partida : listaPartidas) {
            if (partida.getIdPartida().equals(idPartida)) {
                return partida;
            }
        }
        System.err.println("Partida não encontrada com ID: " + idPartida);
        return null;
    }

    public boolean removerPartida(String idPartida) {
        Partida partida = buscarPartidaPeloId(idPartida);
        if (partida != null) {
            listaPartidas.remove(partida);
            System.out.println("Partida removida com sucesso!");
            return true;
        }
        System.err.println("Erro ao remover: Partida não encontrada.");
        return false;
    }

    public void mostrarPartidas() {
        if (listaPartidas.isEmpty()) {
            System.out.println("Nenhuma partida registrada.");
        } else {
            System.out.println("Lista de Partidas:");
            for (Partida partida : listaPartidas) {
                System.out.println(partida);
            }
        }
    }
}
