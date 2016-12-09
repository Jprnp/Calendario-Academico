package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Classificacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Regional;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.App.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AppHelper.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.ExibirData.exibirData;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Estevao
 */
public class CriarEvento {

    public static void criarEvento() {
    	Scanner read = new Scanner(System.in);
        boolean ok = false;
        // Regional reg = selecionarRegional("Criar Evento");
        ArrayList<Regional> regios = selecionarRegionais("Criar Evento");
        Classificacao classif = selecionarClassificacao("Criar Evento");
        Calendar dtInic = Calendar.getInstance();
        Calendar dtFim = Calendar.getInstance();
        System.out.println("Data do Evento (DD/MM/AAAA):");
        String dt = readDateFormat();
        try {
            dtInic.setTime(CsvUtil.SDF.parse(dt));
            dt = "";
            if (classif == Classificacao.EVENTO) {

                System.out.println("Data do Fim do evento(DD/MM/AAAA):");
                dt = readDateFormat();
                dtFim.setTime(CsvUtil.SDF.parse(dt));
                dt = "";

            } else {
                dtFim = dtInic;
            }
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }

        System.out.println("Título do Evento:");
        String titulo = read.nextLine();

        System.out.println("Descricao do Evento:");
        String descr = read.nextLine();
        int ans = 0;
        for (Regional reg : regios) {
            int id = 1, cod = reg.getRegionalNum();
            switch (cod) {
                case 1:
                    id = CsvUtil.generateNewId(catalao);
                    break;
                case 2:
                    id = CsvUtil.generateNewId(goiania);
                    break;
                case 3:
                    id = CsvUtil.generateNewId(goias);
                    break;
                case 4:
                    id = CsvUtil.generateNewId(jatai);
                    break;
                default:
                    id = -1;
            }
            Data data = new Data(dtInic, dtFim, titulo, descr, reg, classif, id);
            if (!ok) {
                System.out.println("\nData criada com sucesso!");
                System.out.println("-------------------------------------------------");
                exibirData(data);
            }
            try {
                boolean sair = false;
                do {
                    if (!ok) {
                        System.out.println("O que deseja fazer?");
                        System.out.println(" 1 - Iserir o evento criado");
                        System.out.println(" 2 - Descarta o evento criado");
                        ans = readInteger();
                        ok = true;
                    }
                    switch (ans) {
                        case 1:
                            switch (reg.getRegionalNum()) {

                                case 1:
                                    CsvUtil.addEvento(catalao, data);
                                    todas.add(data);
                                    break;
                                case 2:
                                    CsvUtil.addEvento(goiania, data);
                                    todas.add(data);
                                    break;
                                case 3:
                                    CsvUtil.addEvento(goias, data);
                                    todas.add(data);
                                    break;
                                case 4:
                                    CsvUtil.addEvento(jatai, data);
                                    todas.add(data);
                                    break;
                                case 5:
                                    CsvUtil.addEvento(catalao, data);
                                    CsvUtil.addEvento(goiania, data);
                                    CsvUtil.addEvento(goias, data);
                                    CsvUtil.addEvento(jatai, data);
                                    todas.add(data);
                            }
                            sair = true;
                            break;
                        case 2:
                            sair = true;
                            break;

                    }
                } while (sair != true);
            } catch (NumberFormatException nfe) {
                System.out.println("Comando invalido\n");
            }
        }
        salvarCalendario();
    }

}
