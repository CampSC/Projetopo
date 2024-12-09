package frontend;

import backend.Admin;
import backend.Sistema;

public class Programa {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.carregarEstado();
        Funcoes funcoes = new Funcoes();
        funcoes.criarAdmin();
        Menus menus = new Menus(sistema);
        menus.MenuPrincipal(Admin.getAdminInstance());
    }
}
