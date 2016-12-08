package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AppHelper.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

class App {

    //<<<<<<< HEAD
    static Scanner leitor = new Scanner(System.in);

    static ArrayList<Data> catalao = new ArrayList<Data>();
    static ArrayList<Data> goiania = new ArrayList<Data>();
    static ArrayList<Data> goias = new ArrayList<Data>();
    static ArrayList<Data> jatai = new ArrayList<Data>();
    static ArrayList<Data> todas = new ArrayList<Data>();
    static String[] argss;
    static boolean loaded = true;

    /**
     * Menu Principal do Programa
     *
     * @param args
     */
    public static void main(String[] args) {
        argss = args;
        int op;
        boolean sair = false;
        try {
            try {
                if (loaded) {
                    carregarCalendarios();
                    loaded = false;
                }
            } catch (FileNotFoundException e) {

            }
            do {
                mainMenu();
                op = AppHelper.readInteger();
                switch (op) {
                    case 1:
                        ExibirData.exibirCalendario();
                        break;
                    case 2:
                        CriarEvento.criarEvento();
                        break;
                    case 3:
                        EditarEventos.editarCalendario();
                        break;
                    case 4: {
                        try {
                            Buscas.buscarEvento();
                        } catch (ParseException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opcao Invalida!\n");
                }
            } while (sair == false);

            System.exit(op);

        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        }
        System.out.println("Obrigado por usar nosso Software! :) ");

    }

    private static void mainMenu() {
        System.out.println("\t MENU");
        System.out.println(" 1 = Exibir Calendario");
        System.out.println(" 2 = Criar Evento");
        System.out.println(" 3 = Editar Calendario");
        System.out.println(" 4 = Buscar Evento");
        System.out.println(" 0 = Sair");
    }

    public static void menuSelecioneRegional(String titulo) {
        System.out.println("\t" + titulo);
        System.out.println("Selecione a Regional desejada:");
        System.out.println(" 1 - Catalão");
        System.out.println(" 2 - Goiânia");
        System.out.println(" 3 - Goiás");
        System.out.println(" 4 - Jataí");
        System.out.println(" 5 - Todas");
        System.out.println(" 0 - Voltar");
    }

    public static void menuSelecioneClassificacao(String titulo) {
        System.out.println("\t" + titulo);
        System.out.println("Selecione a classificacao do Evento:");
        System.out.println(" 1 - Feriado Nacional");
        System.out.println(" 2 - Feriado Municipal");
        System.out.println(" 3 - Ponto Facutativo");
        System.out.println(" 4 - Recesso Academico");
        System.out.println(" 5 - Evento");
    }

    public static void menuEditarCalendario() {
        System.out.println("\t Editar Calendario");
        System.out.println("1 - Deletar Evento");
        System.out.println("2 - Editar Data Inicial");
        System.out.println("3 - Editar Data Final");
        System.out.println("4 - Editar Descricao");
        System.out.println("5 - Editar Classificacao");
        System.out.println("0 - Cancelar");
    }

    public static void menuEscolha() {
        System.out.println("\t O que deseja Fazer");
        System.out.println(" 1 = Anotar Evento");
        System.out.println(" 2 = Editar");
        System.out.println(" 0 = voltar");
    }

    public static void menuSelecioneRegionais(String titulo) {
        System.out.println("\t" + titulo);
        System.out.println("Selecione as Regionais desejadas" + " (separadas por vírgula / MAX 4):");
        System.out.println(" 1 - Catalão");
        System.out.println(" 2 - Goiânia");
        System.out.println(" 3 - Goiás");
        System.out.println(" 4 - Jataí");
        System.out.println(" 0 - Voltar");
    }
}
