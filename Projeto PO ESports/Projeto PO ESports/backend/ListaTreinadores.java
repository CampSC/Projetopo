package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
public class ListaTreinadores implements Serializable {

    private static final long serialVersionUID = 1L;
    private static ListaTreinadores instanciaUnica = null;
    private ArrayList<Treinadores> listaTreinadores = new ArrayList<>();

    private ListaTreinadores() {
    }

    public static ListaTreinadores getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ListaTreinadores();
        }
        return instanciaUnica;
    }

    public static void setInstancia(ListaTreinadores novaInstancia) {
        if (novaInstancia != null) {
            instanciaUnica = novaInstancia;
        } else {
            throw new IllegalArgumentException("A nova instância não pode ser null.");
        }
    }

    public boolean adicionarTreinador(Treinadores t) {
        for (Treinadores treinador : listaTreinadores) {
            if (treinador.getEmailTreinador().equals(t.getEmailTreinador())) {
                System.err.println("Email do treinador já existe. Por favor, use outro email.");
                return false;
            }
        }
        listaTreinadores.add(t);
        System.out.println("Treinador adicionado à lista com sucesso.");
        return true;
    }

    public void removerTreinador(String idTreinador) {
        Iterator<Treinadores> iterator = listaTreinadores.iterator();
        while (iterator.hasNext()) {
            Treinadores treinador = iterator.next();
            if (treinador.getIdTreinador().equals(idTreinador)) {
                iterator.remove();
                System.out.println("Treinador removido com sucesso.");
                return;
            }
        }
        System.err.println("Treinador com ID " + idTreinador + " não encontrado.");
    }

    public Treinadores treinadorPeloId(String idTreinador) {
        for (Treinadores treinador : listaTreinadores) {
            if (treinador.getIdTreinador().equals(idTreinador)) {
                return treinador;
            }
        }
        System.err.println("Treinador não encontrado com ID: " + idTreinador);
        return null;
    }

    public String LoginTreinador(String email, String password){
        for(Treinadores treinadores: listaTreinadores){
            if(email.equals(treinadores.getEmailTreinador()) && password.equals(treinadores.getPasswordTreinador())){
                System.out.println("Login bem sucedido");
                return treinadores.getIdTreinador();
            }
        }
        System.err.println("Credenciais inválidas");
        return null;
    }

    public void mostrarDadosTreinador(String idTreinador) {
        Treinadores treinador = treinadorPeloId(idTreinador);
        if (treinador != null) {
            System.out.println("ID do Treinador: " + treinador.getIdTreinador());
            System.out.println("Nome do Treinador: " + treinador.getNomeTreinador());
            System.out.println("Email do Treinador: " + treinador.getEmailTreinador());
            System.out.println("Equipa Gerida: " + treinador.getEquipaGerida());
        } else {
            System.err.println("Treinador não encontrado.");
        }
    }

    /*public void editarTreinador(String idTreinador, String novoNome, String novoEmail) {
        Treinadores treinador = treinadorPeloId(idTreinador);
        if (treinador != null) {
            if (!novoNome.isBlank()) {
                treinador.setNomeTreinador(novoNome);
            }
            if (!novoEmail.isBlank()) {
                treinador.setEmailTreinador(novoEmail);
            }
            System.out.println("Dados do treinador atualizados com sucesso.");
        } else {
            System.err.println("Treinador não encontrado para edição.");
        }
    }*/
}
