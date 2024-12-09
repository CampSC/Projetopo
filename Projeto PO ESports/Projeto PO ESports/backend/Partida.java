package backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 1;
    private String idPartida;
    private Equipa equipaA;
    private Equipa equipaB;
    private LocalDate dataPartida;

    // Construtor
    public Partida(Equipa equipaA, Equipa equipaB, LocalDate dataPartida) {
        this.idPartida = "P" + contador++;
        this.equipaA = equipaA;
        this.equipaB = equipaB;
        this.dataPartida = dataPartida;
    }

    public Partida(){}

    public String getIdPartida() {
        return idPartida;
    }

    public Equipa getEquipaA() {
        return equipaA;
    }

    public Equipa getEquipaB() {
        return equipaB;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }


    @Override
    public String toString() {
        return "Partida{" +
                "idPartida=" + idPartida +
                ", equipaA=" + equipaA.getNomeEquipa() +
                ", equipaB=" + equipaB.getNomeEquipa() +
                ", dataPartida=" + dataPartida +
                '}';
    }
}