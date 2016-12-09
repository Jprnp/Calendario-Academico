/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.Anotacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvBusca;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.App.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AppHelper.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.ExibirData.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AnotarEvento.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.EditarEventos.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Estevao
 */
public class Buscas {
    
    private static Anotacao anot = new Anotacao();

    public static void escolhaBusca(ArrayList<Data> busca) {
        boolean volta = false;
        do {
            menuEscolha();
            switch (readInteger()) {
                case 1:
                    if (busca.size() > 1) {
                        System.out.println("Digite o id do evento que deseja anotar:");
                        anotarEvento(busca.get(buscarDataId(readInteger(), busca)));
                    } else {
                        anotarEvento(busca.get(0));
                    }
                    volta = true;
                    break;
                case 2:
                    if (busca.size() > 1) {
                        System.out.println("Digite o id do evento que deseja editar:");
                        int idx;
                        idx = buscarDataId(readInteger(), busca);
                        switch (busca.get(idx).getRegional().getRegionalNum()) {
                            case 1:
                                catalao = editarCalendario2(idx , catalao);
                                break;
                            case 2:
                                goiania = editarCalendario2(idx , goiania);
                                break;
                            case 3:
                                goias = editarCalendario2(idx , goias);
                                break;
                            case 4:
                                jatai = editarCalendario2(idx , jatai);
                                break;
                            case 5:
                                catalao = editarCalendario2(idx , catalao);
                                goiania = editarCalendario2(idx , goiania);
                                goias = editarCalendario2(idx , goias);
                                jatai = editarCalendario2(idx , jatai);
                                break;
                        }                   
                    } else {
                        switch (busca.get(0).getRegional().getRegionalNum()) {
                            case 1:
                                catalao = editarCalendario2(0 , catalao);
                                break;
                            case 2:
                                goiania = editarCalendario2(0 , goiania);
                                break;
                            case 3:
                                goias = editarCalendario2(0 , goias);
                                break;
                            case 4:
                                jatai = editarCalendario2(0 , jatai);
                                break;
                            case 5:
                                catalao = editarCalendario2(0 , catalao);
                                goiania = editarCalendario2(0 , goiania);
                                goias = editarCalendario2(0 , goias);
                                jatai = editarCalendario2(0 , jatai);
                                break;
                        }
                    }
                    volta = true;
                    break;
                case 0:
                    volta = true;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
            
        } while (volta == false);
        main(new String[0]);
    }

    /**
     * Classe que vai buscar o evento;
     */
    public static void buscarEvento() throws ParseException {
        boolean volta = false;
        do {
            buscaMenu();
            switch (readInteger()) {
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
                case 0:
                    volta = true;
                    break;
                default:
                    System.out.println("Opcao Invalida");
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
        String dt;
        dt = readDateFormat();

        busca = CsvBusca.buscaData(App.todas, dt);
        if (busca.isEmpty()) {
            System.out.println("Nenhuma evento encontrado.");
        } else {
            busca = CsvUtil.sortCsv(busca);
        }
        for (Data data : busca) {
            exibirData(data);
        }
        escolhaBusca(busca);
    }

    public static void bucarNome() throws ParseException {
    	Scanner read = new Scanner(System.in);
        ArrayList<Data> busca = null;
        System.out.println("Digite o Nome do Evento:");
        busca = CsvBusca.buscaTitulo(App.todas, read.nextLine());
        if (busca.isEmpty()) {
            System.out.println("Nenhuma evento encontrado.");
        } else {
            busca = CsvUtil.sortCsv(busca);
        }
        for (Data data : busca) {
            exibirData(data);
        }
        escolhaBusca(busca);
    }

    public static void buscaDescri() {
    	Scanner read = new Scanner(System.in);
        ArrayList<Data> busca = null;
        System.out.println("Digite a busca:");
        String busc = read.nextLine();
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
        for (Data data : busca) {
            exibirData(data);
        }
        escolhaBusca(busca);
    }

    public static void buscaId() {
        ArrayList<Data> busca = null;
        System.out.println("Digite o id do Evento:");
        busca.add(CsvBusca.buscaId(App.todas, readInteger()));
        if (busca.isEmpty()) {
            System.out.println("Nenhuma evento encontrado.");
        } else {
            busca = CsvUtil.sortCsv(busca);
        }
        for (Data data : busca) {
            exibirData(data);
        }
        escolhaBusca(busca);
    }

    static int buscarDataId(int id, ArrayList<Data> calendario) {
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
            case 5:
                if (!todas.isEmpty()) {
                    for (Data data : todas) {
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
