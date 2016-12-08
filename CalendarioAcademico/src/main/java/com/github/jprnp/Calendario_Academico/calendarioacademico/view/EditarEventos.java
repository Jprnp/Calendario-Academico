/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.*;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Classificacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Regional;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.App.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AppHelper.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.ExibirData.exibirData;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Estevao
 */
class EditarEventos {
    
    static void editarCalendario() {
        int id, codReg, index;
        boolean voltar = false;
        do {
            codReg = selecionarRegional("Editar Calendario").getRegionalNum();
            System.out.println("Digite o Id do Evento:\n");
            id = readInteger();
            switch (codReg) {
                case 5:
                    index = buscarDataId(id, catalao);
                    if (index != -1) {
                        catalao = editarCalendario2(index, catalao);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                    if (index != -1) {
                        goiania = editarCalendario2(index, goiania);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                    if (index != -1) {
                        goias = editarCalendario2(index, goias);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                    if (index != -1) {
                        jatai = editarCalendario2(index, jatai);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                case 1:
                    index = buscarDataId(id, catalao);
                    if (index != -1) {
                        catalao = editarCalendario2(index, catalao);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                    break;
                case 2:
                    index = buscarDataId(id, goiania);
                    if (index != -1) {
                        goiania = editarCalendario2(index, goiania);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                    break;
                case 3:
                    index = buscarDataId(id, goias);
                    if (index != -1) {
                        goias = editarCalendario2(index, goias);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                    break;
                case 4:
                    index = buscarDataId(id, jatai);
                    if (index != -1) {
                        jatai = editarCalendario2(index, jatai);
                    } else {
                        System.out.println("Não encontramos Evento com esse ID");
                    }
                    break;
                case 0:
                    voltar = true;
                    break;
            }
        } while (voltar != true);

    }
    
    static ArrayList<Data> editarCalendario2(int index, ArrayList<Data> calendario) {
        int op;
        boolean cancelar = false;
        do {
            try {
                try {
                    exibirData(calendario.get(index));
                } catch (RuntimeException re) {
                    editarCalendario();
                }
                menuEditarCalendario();

                op = readInteger();

                switch (op) {
                    case 1:
                        try {
                            calendario = CsvUtil.deletarEvento(calendario, index);
                            System.out.println("Evento deletado com Sucesso!");
                        } catch (RuntimeException re) {
                            System.out.println(re.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Entre com a Data Inicial");
                        calendario = editarDataInicial(index, calendario);
                        break;
                    case 3:
                        System.out.println("Entre com a Data Final");
                        calendario = editarDataFinal(index, calendario);
                        break;
                    case 4:
                        System.out.println("Entre com uma nova descricao ou nome:");
                        String descricao = leitor.nextLine();
                        calendario = editarDescriacao(index, calendario, descricao);
                        break;
                    case 5:
                        calendario = editarClassificacao(index, calendario,
                                selecionarClassificacao("Editar Evento").getClassificacaoNum());
                        break;
                    case 0:
                        cancelar = true;
                        break;
                    default:
                        System.out.println("Opcao invalida\n");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Opcao invalida\n");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } while (cancelar != true);
        return calendario;
    }
    
    static ArrayList<Data> editarRegional(int index, ArrayList<Data> calendario) {
        Data data;
        data = calendario.get(index);
        switch (selecionarRegional("Editar Evento").getRegionalNum()) {
            case 1:
                calendario = deletarEvento(calendario, data.getIdEvento());
                data.setRegional(Regional.CATALAO);
                CsvUtil.addEvento(catalao, data);
                break;
            case 2:
                calendario = deletarEvento(calendario, data.getIdEvento());
                data.setRegional(Regional.GOIANIA);
                CsvUtil.addEvento(goiania, data);
                break;
            case 3:
                calendario = deletarEvento(calendario, data.getIdEvento());
                data.setRegional(Regional.GOIAS);
                CsvUtil.addEvento(goias, data);
                break;
            case 4:
                calendario = deletarEvento(calendario, data.getIdEvento());
                data.setRegional(Regional.JATAI);
                CsvUtil.addEvento(jatai, data);
                break;
            case 5:
                calendario = deletarEvento(calendario, data.getIdEvento());
                data.setRegional(Regional.CATALAO);
                CsvUtil.addEvento(catalao, data);
                CsvUtil.addEvento(goiania, data);
                CsvUtil.addEvento(goias, data);
                CsvUtil.addEvento(jatai, data);
                break;
        }

        return calendario;
    }
    
    static ArrayList<Data> editarClassificacao(int index, ArrayList<Data> calendario, int cl) {
        switch (cl) {
            case 1:
                calendario.get(index).setClassificacao(Classificacao.EVENTO);
                return calendario;
            case 2:
                calendario.get(index).setClassificacao(Classificacao.FERIADO_NACIONAL);
                return calendario;
            case 3:
                calendario.get(index).setClassificacao(Classificacao.FERIADO_MUNICIPAL);
                return calendario;
            case 4:
                calendario.get(index).setClassificacao(Classificacao.PONTO_FACUTATIVO);
                return calendario;
            case 5:
                calendario.get(index).setClassificacao(Classificacao.RECESSO_ACADEMICO);
                return calendario;
        }
        return null;
    }

    static ArrayList<Data> editarDescriacao(int index, ArrayList<Data> calendario, String descricao) {
        calendario.get(index).setDescricao(descricao);
        return calendario;
    }

    static ArrayList<Data> editarDataInicial(int index, ArrayList<Data> calendario) {
        Calendar dt = Calendar.getInstance();
        try {
            dt.setTime(CsvUtil.SDF.parse(readDateFormat()));
            calendario.get(index).setDataInicial(dt);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return calendario;
    }

    static ArrayList<Data> editarDataFinal(int index, ArrayList<Data> calendario) throws ParseException {
        Calendar dt = Calendar.getInstance();
        try {
            dt.setTime(CsvUtil.SDF.parse(readDateFormat()));
            calendario.get(index).setDataFinal(dt);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return calendario;
    }

    private static int buscarDataId(int id, ArrayList<Data> catalao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
