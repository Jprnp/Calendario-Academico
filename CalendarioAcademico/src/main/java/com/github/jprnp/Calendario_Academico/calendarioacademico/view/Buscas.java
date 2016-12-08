/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvBusca;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.App.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AppHelper.readDateFormat;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.ExibirData.*;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Estevao
 */
public class Buscas {
    
     public static void escolhaBusca() {
        boolean volta = false;
        do {
            menuEscolha();
            switch (leitor.nextInt()) {
                case 1:

            }

        } while (volta == false);

    }
    
    /**
     * Classe que vai buscar o evento;
     */
    public static void buscarEvento() throws ParseException {
        boolean volta = false;
        do {
            buscaMenu();
            switch (leitor.nextInt()) {
                case 1:
                    buscarData();
                    volta = true;
                    break;
                case 2:
                    bucarNome();
                    volta = true;
                    break;
                case 3:
                    buscaDescri();
                    volta = true;
                    break;
                case 4:
                    buscaId();
                    volta = true;
                    break;

            }
        } while (volta == false);

    }

    public static void buscaMenu() {
        System.out.println("\t BUSCA");
        System.out.println(" 1 = por Data");
        System.out.println(" 2 = por Nome");
        System.out.println(" 3 = por Descrição");
        System.out.println(" 4 = por id");
        System.out.println(" 0 = voltar");
    }

    public static void buscarData() throws ParseException {
        ArrayList<Data> busca = null;
        System.out.println("Digite a data:");
        String dt = readDateFormat();
        busca = CsvBusca.buscaData(App.todas, dt);
        if (busca.isEmpty()) {
            System.out.println("Nenhuma evento encontrado.");
        } else {
            busca = CsvUtil.sortCsv(busca);
        }
    }

    public static void bucarNome() throws ParseException {
        ArrayList<Data> busca = null;
        System.out.println("Digite o Nome do Evento:");
        busca = CsvBusca.buscaTitulo(App.todas, leitor.nextLine());
        if (busca.isEmpty()) {
            System.out.println("Nenhuma evento encontrado.");
        } else {
            busca = CsvUtil.sortCsv(busca);
        }
    }

    public static void buscaDescri() {
        ArrayList<Data> busca = null;
        System.out.println("Digite a busca:");
        String busc = leitor.nextLine();
        busca = CsvBusca.buscaDescricao(App.todas, busc);
        for (Data data : CsvBusca.buscaTitulo(App.todas, busc)) {
            if (!busca.contains(data)) {
                busca.add(data);
            }
        }
        if (busca.isEmpty()) {
            System.out.println("Nenhuma evento encontrado.");
        } else {
            busca = CsvUtil.sortCsv(busca);
        }
    }

    public static void buscaId() {
        ArrayList<Data> busca = null;
        System.out.println("Digite o id do Evento:");
        busca.add(CsvBusca.buscaId(App.todas, leitor.nextInt()));
        if (busca.isEmpty()) {
            System.out.println("Nenhuma evento encontrado.");
        } else {
            busca = CsvUtil.sortCsv(busca);
        }

    }
    
    
    public static int buscarDataId(int id, ArrayList<Data> calendario) {
        for (Data data : calendario) {
            if (data.getIdEvento() == id) {
                return calendario.indexOf(data);
            }
        }
        return -1;
    }

    static public void getCalendario(int op) throws Exception {
        switch (op) {
            case 1:
                if (!catalao.isEmpty()) {
                    for (Data data : catalao) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio\n");
                }
                System.out.println("");
                break;
            case 2:
                if (!goiania.isEmpty()) {
                    for (Data data : goiania) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio\n");
                }
                System.out.println("");
                break;
            case 3:
                if (!goias.isEmpty()) {
                    for (Data data : goias) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio\n");
                }
                System.out.println("");
                break;
            case 4:
                if (!jatai.isEmpty()) {
                    for (Data data : jatai) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio\n");
                }
                System.out.println("");
                break;
            default:
                throw new Exception("Codigo invalido! Tente Novamente:\n");
        }
    }
    
    public static void menuEscolha() {
        System.out.println("\t O que deseja Fazer");
        System.out.println(" 1 = Anotar Evento");
        System.out.println(" 2 = Editar");
        System.out.println(" 0 = voltar");
    }
}
