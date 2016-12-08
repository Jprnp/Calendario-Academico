package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Classificacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.DataComent;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.Anotacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.App.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AppHelper.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Estevao
 */
public class ExibirData {

    static void exibirData(Data data) {

        System.out.print("Data: " + CsvUtil.SDF.format(data.getDataInicial().getTime()));
        if (data.getClassificacao() == Classificacao.EVENTO) {
            System.out.println(" até " + CsvUtil.SDF.format(data.getDataFinal().getTime()));
        }
        System.out.println("-------------------------------------------------");
        System.out.println("\t\t" + data.getTitulo());
        System.out.println("-------------------------------------------------");
        System.out.println("Descricao: " + data.getDescricao());
        System.out.println("Classificacao: " + data.getClassificacao().getClassificacao());
        System.out.println("ID: " + data.getIdEvento());
        System.out.println("=================================================");

    }

    static void exibirCalendario() {
        int op;
        boolean volta = false;

        try {
            do {

                menuSelecioneRegional("Exibir Calendario\n");
                op = readInteger();
                switch (op) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        try {
                            getCalendario(op);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 0:
                        volta = true;
                        break;
                    default:
                        System.out.println("Opcao invalida\n");
                }

            } while (volta != true);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    static void exibirDataComent(DataComent data) {

        System.out.print("Data: " + CsvUtil.SDF.format(data.getDataInicial().getTime()));
        if (data.getClassificacao() == Classificacao.EVENTO) {
            System.out.println(" até " + CsvUtil.SDF.format(data.getDataFinal().getTime()));
        }
        System.out.println("-------------------------------------------------");
        System.out.println("\t\t" + data.getTitulo());
        System.out.println("-------------------------------------------------");
        System.out.println("Descricao: " + data.getDescricao());
        System.out.println("Classificacao: " + data.getClassificacao().getClassificacao());
        System.out.println("ID: " + data.getIdEvento());
        System.out.println("\nObservção: " + data.getComentario());
        System.out.println("=================================================");
    }

    static void exibirAnotacoes() {
        boolean volta = false;
        if (coment.isEmpty()) {
            System.out.println("não há Anotações feitas.");
        } else {
            for (DataComent data : coment) {
                exibirDataComent(data);
            }
            do {
                menuExibirAnot();
                switch (readInteger()) {
                    case 1:
                        removerDataComent();
                        break;
                    case 0:
                        volta = true;
                        break;
                    default:
                        System.out.println("Opçao Invalida");
                        break;
                }
            } while (!volta);
        }
        main(new String[0]);
    }

    private static void menuExibirAnot() {
        System.out.println("O que deseja fazer?");
        System.out.println("1 - remover uma anotacao");
        System.out.println("0 - voltar");
    }

    private static void removerDataComent() {
        boolean volta = false;
        System.out.println("Digite o id da Anotacao que deseja remover:");
        try {
            Anotacao.removeEvento(readInteger());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            do {
                System.out.println("Oque deseja fazer?");
                System.out.println(" 1 - Tentar novamente.");
                System.out.println(" 2 - Voltar");
                switch (readInteger()) {
                    case 1:
                        removerDataComent();
                        volta = true;
                        break;
                    case 2:
                        volta = true;
                        break;
                    default:
                        System.out.println("Opcao Invalida.");
                        break;
                }

            } while (volta);
            salvarCalendario();
            main(new String[0]);
        }
    }
}
