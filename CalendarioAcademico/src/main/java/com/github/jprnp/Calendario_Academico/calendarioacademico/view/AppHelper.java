/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Classificacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Regional;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.App.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.ExibirData.exibirData;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Estevao
 */
class AppHelper {
    /**
     * Função que força o usuário a digitar uma data válida no formato
     * DD/MM/AAAA
     *
     * @return String com a data digitada
     */
    static String readDateFormat() {
    	Scanner read = new Scanner(System.in);
        String dt = "";

        // EXPRESSÃO REGULAR RESPONSÁVEL PELA VERIFICAÇÃO
        String regex = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|"
                + "((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0" + "[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|"
                + "(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|" + "12|16|20|24|28|32|36|40|44|48|52|56|60|64|"
                + "68|72|76|80|84|88|92|96)$)";

        boolean done = false;
        do {
            dt = read.nextLine();
            if (dt.matches(regex)) {
                done = true;
            } else {
                System.out.println(
                        "Data inserida nao segue padrao " + "DD/MM/AAAA ou e uma data invalida - Tente" + " Novamente");
            }
        } while (!done);

        return dt;
    }

    static void carregarCalendarios() throws FileNotFoundException {
    	Scanner read = new Scanner(System.in);
        try {
            catalao = CsvUtil.loadCsv(Regional.CATALAO);
        } catch (RuntimeException re1) {
        }
        try {
            goiania = CsvUtil.loadCsv(Regional.GOIANIA);
        } catch (RuntimeException re2) {
        }
        try {
            goias = CsvUtil.loadCsv(Regional.GOIAS);
        } catch (RuntimeException re3) {
        }
        try {
            jatai = CsvUtil.loadCsv(Regional.JATAI);
        } catch (RuntimeException re4) {
        }
        try {
            for (Data data : catalao) {
                if(!todas.contains(data))
                    todas.add(data);
            }
            for (Data data : goias) {
                if(!todas.contains(data))
                todas.add(data);
            }
            for (Data data : goiania) {
                if(!todas.contains(data))
                todas.add(data);
            }
            for (Data data : jatai) {
                if(!todas.contains(data))
                todas.add(data);
            }
            todas = CsvUtil.sortCsv(todas);
        } catch (RuntimeException re5) {
        }
        try {
            coment = CsvUtil.loadCsvComent();
        } catch (RuntimeException re4) {
        }
    }

    static void salvarCalendario() {
        try {
            if (!catalao.isEmpty()) {
                CsvUtil.generateCsv(catalao);
            } else {
                CsvUtil.generateEmptyCsv(catalao, Regional.CATALAO.getRegionalNum());
            }
            if (!goiania.isEmpty()) {
                CsvUtil.generateCsv(goiania);
            } else {
                CsvUtil.generateEmptyCsv(goiania, Regional.GOIANIA.getRegionalNum());
            }
            if (!goias.isEmpty()) {
                CsvUtil.generateCsv(goias);
            } else {
                CsvUtil.generateEmptyCsv(goias, Regional.GOIAS.getRegionalNum());
            }
            if (!jatai.isEmpty()) {
                CsvUtil.generateCsv(jatai);
            } else {
                CsvUtil.generateEmptyCsv(jatai, Regional.JATAI.getRegionalNum());
            }
            if (!coment.isEmpty()) {
                CsvUtil.generateCsvComent(coment);
            } else {
                CsvUtil.generateEmptyCsv(coment);
            }
            System.out.println("Salvo com Sucesso!");
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
    }

    static void escolhaBusca() {
        boolean volta = false;
        do {
            menuEscolha();
            switch (readInteger()) {
                case 1:

            }

        } while (volta == false);

    }

    static Classificacao selecionarClassificacao(String frase) {
        int op;
        menuSelecioneClassificacao(frase);
        try {
            op = readInteger();
        } catch (NumberFormatException nfe) {
            System.out.println("Codigo invalido");
            return selecionarClassificacao(frase);
        }
        switch (op) {
            case 5:
                return Classificacao.EVENTO;
            case 1:
                return Classificacao.FERIADO_NACIONAL;
            case 2:
                return Classificacao.FERIADO_MUNICIPAL;
            case 3:
                return Classificacao.PONTO_FACUTATIVO;
            case 4:
                return Classificacao.RECESSO_ACADEMICO;
            default:
                System.out.println("Opcao invalida!\n");
                return selecionarClassificacao(frase);
        }
    }

    static Regional selecionarRegional(String titulo) {
        int op;

        menuSelecioneRegional(titulo);
        op = readInteger();
        switch (op) {
            case 1:
                return Regional.CATALAO;
            case 2:
                return Regional.GOIANIA;
            case 3:
                return Regional.GOIAS;
            case 4:
                return Regional.JATAI;
            case 5:
                return Regional.TODAS;
            case 0:
                main(new String[0]);
                break;
            default:
                System.out.println("Opcao invalida!\n");
                return selecionarRegional(titulo);
        }
        return null;
    }

    static void getCalendario(int op) throws Exception {
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

    static int readInteger() {
    	Scanner read = new Scanner(System.in);
        try {
            return read.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas numeros.");
            return readInteger();
        }
    }

    static ArrayList<Regional> selecionarRegionais(String titulo) {
    	Scanner read = new Scanner(System.in);
        menuSelecioneRegionais(titulo);
        String selected = read.nextLine();
        selected = selected.replaceAll(" ", "");
        String[] split = selected.split(",");
        ArrayList<Regional> regions = new ArrayList<Regional>();

        for (String op : split) {
            switch (Integer.parseInt(op)) {
                case 1:
                    regions.add(Regional.CATALAO);
                    break;
                case 2:
                    regions.add(Regional.GOIANIA);
                    break;
                case 3:
                    regions.add(Regional.GOIAS);
                    break;
                case 4:
                    regions.add(Regional.JATAI);
                    break;
                case 5:
                    if (split.length == 1) {
                        regions.add(Regional.TODAS);
                    }
                    break;
                case 0:
                    if (split.length == 1) {
                        main(new String[0]);
                    } else {
                        System.out.println("Regionais invalidas na seleção." + " Tente novamente");
                        return selecionarRegionais(titulo);
                    }
                    break;
                default:
                    System.out.println("Regionais invalidas na seleção." + " Tente novamente");
                    return selecionarRegionais(titulo);
            }
        }
        return regions;
    }
    
}
