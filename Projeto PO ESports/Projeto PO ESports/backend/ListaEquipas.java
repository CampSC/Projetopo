package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaEquipas implements Serializable {
    private static final long serialVersionUID = 1L;

    ListaTreinadores listaTreinadores = ListaTreinadores.getInstancia();
    private static ListaEquipas instanciaUnica = null;

    private ArrayList<Equipa> listaequipas;

    private ListaEquipas() {
        listaequipas = new ArrayList<>();
    }

    public static ListaEquipas getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ListaEquipas();
        }
        return instanciaUnica;
    }

    public static void setInstancia(ListaEquipas novaInstancia) {
        if (novaInstancia != null) {
            instanciaUnica = novaInstancia;
        } else {
            throw new IllegalArgumentException("A nova instância não pode ser null.");
        }
    }

    public boolean adicionarEquipa(Equipa e){
        for(Equipa equipas : listaequipas){
            if(e.getNomeEquipa().equals(equipas.getNomeEquipa())) {
                System.out.println("Esse nome de equipa já foi usado");
                return false;
            }
        }
        System.out.println("Equipa adicionada com sucesso");
        listaequipas.add(e);
        return true;
    }

    public boolean ExisteEquipa(String idEquipa){
        for(Equipa equipa : listaequipas){
            if(equipa.getIdEquipa().equals(idEquipa)){
                return true;
            }
        }

        return false;
    }

    public Equipa EquipapeloId(String idEquipa){
        for (Equipa equipa : listaequipas) {
            if (equipa.getIdEquipa().equals(idEquipa)) {
                return equipa;
            }
        }
        System.err.println("Jogador não encontrado com ID: " + idEquipa);
        return null;
    }

    public void MostrarJogadoresNaEquipa(String idTreinador){
        Treinadores treinadores = listaTreinadores.treinadorPeloId(idTreinador);
        Equipa equipa = treinadores.getEquipaGerida();
        System.out.println("Nome: " + equipa.getNomeEquipa());
        System.out.println("Id: " + equipa.getIdEquipa());
        equipa.MostrarJogadoresNaEquipa();
    }

    public void EditarEquipa(String nome, Equipa equipa){

        if(!nome.isBlank()){
            equipa.setNomeEquipa(nome);
        }
    }
}
