package backend;

import java.io.*;
import java.util.*;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;

    private ListaJogadores listaJogadores = ListaJogadores.getInstancia();
    private ListaTreinadores listaTreinadores = ListaTreinadores.getInstancia();
    private ListaEquipas listaEquipas = ListaEquipas.getInstancia();
    private ListaTorneios listaTorneios = ListaTorneios.getInstancia();
    private ListaPartidas listaPartidas = ListaPartidas.getInstancia();
    private final String caminhoArquivo;

    public Sistema() {
        String userHome = System.getProperty("user.home");
        caminhoArquivo = userHome + File.separator + "Documents" + File.separator + "estado.dat";
    }

    public ListaJogadores getListaJogadores() {
        return listaJogadores;
    }

    public ListaTreinadores getListaTreinadores() {
        return listaTreinadores;
    }

    public ListaEquipas getListaEquipas() {
        return listaEquipas;
    }

    public ListaTorneios getListaTorneios() {
        return listaTorneios;
    }

    public ListaPartidas getListaPartidas() {
        return listaPartidas;
    }

    private void atualizarEstado(Sistema novoSistema) {
        ListaJogadores.setInstancia(novoSistema.listaJogadores);
        ListaTreinadores.setInstancia(novoSistema.listaTreinadores);
        ListaEquipas.setInstancia(novoSistema.listaEquipas);
        ListaTorneios.setInstancia(novoSistema.listaTorneios);
        ListaPartidas.setInstancia(novoSistema.listaPartidas);
    }

    public void carregarEstado() {
        String userHome = System.getProperty("user.home");
        String caminhoArquivo = userHome + File.separator + "Documents" + File.separator + "estado.dat";

        System.out.println("Tentando carregar estado de: " + caminhoArquivo);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            Sistema sistemaCarregado = (Sistema) ois.readObject();

            // Atualize as listas nos singletons
            ListaJogadores.setInstancia(sistemaCarregado.getListaJogadores());
            ListaTreinadores.setInstancia(sistemaCarregado.getListaTreinadores());
            ListaEquipas.setInstancia(sistemaCarregado.getListaEquipas());
            ListaTorneios.setInstancia(sistemaCarregado.getListaTorneios());
            ListaPartidas.setInstancia(sistemaCarregado.getListaPartidas());

            System.out.println("Estado carregado com sucesso!");

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Nenhum estado carregado.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o estado: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void gravarEstado() {
        String userHome = System.getProperty("user.home");
        String caminhoArquivo = userHome + File.separator + "Documents" + File.separator + "estado.dat";

        System.out.println("Gravando estado em: " + caminhoArquivo);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(this);
            System.out.println("Estado gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gravar o estado: " + e.getMessage());
        }
    }
}