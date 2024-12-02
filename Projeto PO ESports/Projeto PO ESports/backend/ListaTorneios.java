package backend;

import java.util.ArrayList;
import java.util.List;

public class ListaTorneios {

    private static ListaTorneios instanciaUnica = null;

    private ArrayList<Torneios> listaTorneios;

    Equipa equipa = new Equipa();

    private ListaTorneios() {
        listaTorneios = new ArrayList<>();
    }

    public static ListaTorneios getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ListaTorneios();
        }
        return instanciaUnica;
    }

    public ArrayList<Torneios> getListaTorneios() {
        return listaTorneios;
    }


    public boolean adicionarTorneio(Torneios t) {
        for (Torneios torneio : listaTorneios) {
            if (t.getNomeTorneio().equalsIgnoreCase(torneio.getNomeTorneio())) {
                System.out.println("Já existe um torneio com este nome.");
                return false;
            }
        }
        listaTorneios.add(t);
        return true;
    }

    // Buscar torneio pelo ID
    public Torneios TorneioPeloId(String idTorneio) {
        for (Torneios torneio : listaTorneios) {
            if (torneio.getIdTorneio().equals(idTorneio)) {
                return torneio;
            }
        }
        System.err.println("Torneio não encontrado com ID: " + idTorneio);
        return null;
    }

    public boolean ExisteTorneio(String idTorneio){
        for(Torneios torneios : listaTorneios){
            if(torneios.getIdTorneio().equals(idTorneio)){
                return true;
            }
        }
        return false;
    }

    public boolean editarTorneio(String idTorneio, String novoNome) {
        Torneios torneio = TorneioPeloId(idTorneio);
        if (torneio != null) {
            if (!novoNome.isBlank()) {
                torneio.setNomeTorneio(novoNome);
                System.out.println("Torneio atualizado com sucesso.");
                return true;
            }
            System.err.println("O novo nome não pode estar em branco.");
            return false;
        }
        return false;
    }

    public boolean existeEquipaNoTorneio(Torneios torneio, Equipa equipa) {
        for (Equipa equipaRegistrada : torneio.getEquipasParticipantes()) {
            if (equipaRegistrada.equals(equipa)) {
                return true;
            }
        }
        return false;
    }

    public List VerEquipasNoTorneio(String idTorneio){
        for(Torneios torneios : listaTorneios){
            if(idTorneio.equals(torneios.getIdTorneio())){
                if(!torneios.getEquipasParticipantes().isEmpty()) {
                    return torneios.getEquipasParticipantes();
                }else{
                    System.out.println("Equipa encontra-se vazia");
                    return null;
                }
            }
        }
        System.out.println("Id do Torneio não encontrada");
        return null;
    }

    // Mostrar todos os torneios
    public void mostrarTorneios() {
        if (listaTorneios.isEmpty()) {
            System.out.println("Nenhum torneio registrado.");
        } else {
            System.out.println("Lista de Torneios:");
            for (Torneios torneio : listaTorneios) {
                System.out.println(torneio);
            }
        }
    }


    public boolean removerTorneio(String idTorneio) {
        Torneios torneio = TorneioPeloId(idTorneio);
        if (torneio != null) {
            listaTorneios.remove(torneio);
            System.out.println("Torneio removido com sucesso.");
            return true;
        }
        System.err.println("Erro ao remover: Torneio não encontrado.");
        return false;
    }
}
