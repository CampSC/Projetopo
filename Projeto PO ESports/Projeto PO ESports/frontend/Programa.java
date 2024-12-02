package frontend;

import backend.Admin;
import backend.Jogadores;

public class Programa {
    public static void main(String[] args) {
        Funcoes funcoes = new Funcoes();
        Menus menus = new Menus();
        funcoes.criarAdmin();
        menus.MenuPrincipal(Admin.getAdminInstance());
    }
}
