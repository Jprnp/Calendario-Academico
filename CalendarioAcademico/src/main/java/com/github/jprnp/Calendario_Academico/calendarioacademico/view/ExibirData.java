package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Classificacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
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
                        getCalendario(op);
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
    
}
