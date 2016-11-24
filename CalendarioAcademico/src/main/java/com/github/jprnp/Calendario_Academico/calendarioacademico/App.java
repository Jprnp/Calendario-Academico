package com.github.jprnp.Calendario_Academico.calendarioacademico;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class App {

    static Scanner leitor = new Scanner(System.in);

    static ArrayList<Data> catalao = new ArrayList<Data>();
    static ArrayList<Data> goiania = new ArrayList<Data>();
    static ArrayList<Data> goias = new ArrayList<Data>();
    static ArrayList<Data> jatai = new ArrayList<Data>();

    /**
     * Menu Principal do Programa
     *
     * @param args
     */
    public static void main(String[] args) {
        int op;
        boolean sair = false;
        try {
            carregarCalendarios();
            do {
                mainMenu();
                op = leitor.nextInt();
                switch (op) {
                    case 1:
                        exibirCalendario();
                        break;
                    case 2:
                        criarEvento();
                    case 3:
                        editarCalendario();
                    case 4:
                        salvarCalendario();
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opcao Invalida!");
                }
            } while (sair == false);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            main(args);
        }
        System.out.println("Obrigado por usar nosso Software! :) ");
        System.exit(0);
    }

    public static void mainMenu() {
        System.out.println("\t MENU");
        System.out.println(" 1 = Exibir Calendario");
        System.out.println(" 2 = Criar Evento");
        System.out.println(" 3 = Editar Calendario");
        System.out.println(" 4 = Salvar");
        System.out.println(" 0 = Sair");
    }

    private static void exibirCalendario() {
        int op;
        boolean volta = false;

        do {
            try {

                menuSelecioneRegional("Exibir Calendario");
                op = leitor.nextInt();
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
                        System.out.println("Opcao invalida");
                }
            } catch (NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (volta != true);

    }

    private static void menuSelecioneRegional(String titulo) {
        System.out.println("\t" + titulo);
        System.out.println("Selecione a Regional desejada:");
        System.out.println(" 1 - Catalão");
        System.out.println(" 2 - Goiânia");
        System.out.println(" 3 - Goiás");
        System.out.println(" 4 - Jataí");
        System.out.println(" 0 - Voltar");
    }

    static public void getCalendario(int op) throws Exception {
        switch (op) {
            case 1:
                if (!catalao.isEmpty()) {
                    for (Data data : catalao) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio");
                }
                System.out.println("");
                break;
            case 2:
                if (!goiania.isEmpty()) {
                    for (Data data : goiania) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio");
                }
                System.out.println("");
                break;
            case 3:
                if (!goias.isEmpty()) {
                    for (Data data : goias) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio");
                }
                System.out.println("");
                break;
            case 4:
                if (!jatai.isEmpty()) {
                    for (Data data : jatai) {
                        exibirData(data);
                    }
                } else {
                    System.out.println("Calendario Vazio");
                }
                System.out.println("");
                break;
            default:
                throw new Exception("Codigo invalido! Tente Novamente:");
        }
    }

    private static void exibirData(Data data) {

        System.out.print("Data: " + CsvUtil.SDF.format(
                data.getDataInicial().getTime()));
        if (data.getClassificacao() == Classificacao.EVENTO) {
            System.out.println(" até " + CsvUtil.SDF.format(
                    data.getDataFinal().getTime()));
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Descricao: " + data.getDescricao());
        System.out.println("Classificacao: "
                + data.getClassificacao().getClassificacao());
        System.out.println("ID: " + data.getIdEvento());
        System.out.println("=================================================");

    }

    private static void criarEvento() {
        Regional reg = selecionarRegional();
        Classificacao classif = selecionarClassificacao();
        System.out.println("Data do Fim (DD/MM/AAAA):");
        Calendar dtInic = null;
        Calendar dtFim = null;
        String dt = readDateFormat();
        try {
            dtInic.setTime(CsvUtil.SDF.parse(dt));

            if (classif == Classificacao.EVENTO) {

                dt = readDateFormat();
                dtFim.setTime(CsvUtil.SDF.parse(dt));

            } else {
                dtFim = dtInic;
            }
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }

        System.out.println("Descricao ou nome do Evento:");
        String descr = leitor.nextLine();
        int id;
        String idStr = null;
        switch (reg.getRegionalNum()) {
            case 1:
                idStr = (reg.getRegionalNum() + "" + catalao.size());
                break;
            case 2:
                idStr = (reg.getRegionalNum() + "" + goiania.size());
                break;
            case 3:
                idStr = (reg.getRegionalNum() + "" + goias.size());
                break;
            case 4:
                idStr = (reg.getRegionalNum() + "" + jatai.size());
                break;
        }
        id = Integer.parseInt(idStr);
        Data data = new Data(dtInic, dtFim, descr, reg, classif, id);
        System.out.println("\nData criada com sucesso!");
        System.out.println("-------------------------------------------------");
        exibirData(data);
        try {
            boolean sair = false;
            do {
                System.out.println("O que deseja fazer?");
                System.out.println(" 1 - Iserir o evento criado");
                System.out.println(" 2 - Descarta o evento criado");
                switch (leitor.nextInt()) {
                    case 1:
                        switch (reg.getRegionalNum()) {
                            case 1:
                                CsvUtil.addEvento(catalao, data);
                                break;
                            case 2:
                                CsvUtil.addEvento(goiania, data);
                                break;
                            case 3:
                                CsvUtil.addEvento(goias, data);
                                break;
                            case 4:
                                CsvUtil.addEvento(jatai, data);
                                break;
                            case 5:
                                CsvUtil.addEvento(catalao, data);
                                CsvUtil.addEvento(goiania, data);
                                CsvUtil.addEvento(goias, data);
                                CsvUtil.addEvento(jatai, data);
                        }
                        sair = true;
                        break;
                    case 2:
                        sair = true;
                }
            } while (sair == true);
        } catch (NumberFormatException nfe) {
            System.out.println("Comando invalido");
        }

    }

    private static Regional selecionarRegional() {
        int op;
        menuSelecioneRegional("Criar Evento");
        op = leitor.nextInt();
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
            default:
                System.out.println("Opcao invalida!");
                return selecionarRegional();
        }
    }

    private static Classificacao selecionarClassificacao() {
        int op;
        menuSelecioneClassificacao("Criar Evento");
        try {
            op = leitor.nextInt();
        } catch (NumberFormatException nfe) {
            System.out.println("Codigo invalido");
            return selecionarClassificacao();
        }
        switch (op) {
            case 1:
                return Classificacao.EVENTO;
            case 2:
                return Classificacao.FERIADO_NACIONAL;
            case 3:
                return Classificacao.FERIADO_MUNICIPAL;
            case 4:
                return Classificacao.PONTO_FACUTATIVO;
            case 5:
                return Classificacao.RECESSO_ACADEMICO;
            default:
                System.out.println("Opcao invalida!");
                return selecionarClassificacao();
        }
    }

    private static void menuSelecioneClassificacao(String titulo) {
        System.out.println("\t" + titulo);
        System.out.println("Selecione a classificacao do Evento:");
        System.out.println(" 1 - Evento");
        System.out.println(" 2 - Feriado Nacional");
        System.out.println(" 3 - Feriado Municipal");
        System.out.println(" 4 - Ponto Facutativo");
        System.out.println(" 5 - Recesso Acadêmico");
    }

    /**
     * Função que força o usuário a digitar uma data válida no formato
     * DD/MM/AAAA
     *
     * @return String com a data digitada
     */
    public static String readDateFormat() {
        String dt = "";

        //EXPRESSÃO REGULAR RESPONSÁVEL PELA VERIFICAÇÃO
        String regex = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|"
                + "((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0"
                + "[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|"
                + "(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|"
                + "12|16|20|24|28|32|36|40|44|48|52|56|60|64|"
                + "68|72|76|80|84|88|92|96)$)";

        boolean done = false;
        do {
            dt = leitor.nextLine();
            if (dt.matches(regex)) {
                done = true;
            } else {
                System.out.println("Data inserida nao segue padrao "
                        + "DD/MM/AAAA ou e uma data invalida - Tente"
                        + " Novamente");
            }
        } while (!done);

        return dt;
    }

    public static void carregarCalendarios() {
        catalao = CsvUtil.loadCsv(Regional.CATALAO);
        goiania = CsvUtil.loadCsv(Regional.GOIANIA);
        goias = CsvUtil.loadCsv(Regional.GOIAS);
        jatai = CsvUtil.loadCsv(Regional.JATAI);
    }

    private static void salvarCalendario() {
        CsvUtil.generateCsv(catalao);
        CsvUtil.generateCsv(goiania);
        CsvUtil.generateCsv(goias);
        CsvUtil.generateCsv(jatai);
    }

    private static void editarCalendario() {
        int id, codReg, indexCalen;
        String idStr;
        System.out.println("\t Editar Calendario");
        System.out.println("entre com o Id do evento:");
        id = leitor.nextInt();
        idStr = "" + id;
        codReg = Integer.parseInt(idStr.substring(0, 1));
        switch (codReg) {
            case 5:
            case 1:
                int index = buscarDataId(id, catalao);
                if (index != -1) {
                    editarCalendario2(index);
                } else {
                    System.out.println("Não encontramos Evento com esse ID");
                }
                break;
            case 2:
            case 3:
            case 4:
        }

    }

    private static int buscarDataId(int id, ArrayList<Data> calendario) {
        for (Data data : calendario) {
            if (data.getIdEvento() == id) {
                return calendario.indexOf(data);
            }
        }
        return -1;
    }

    private static void editarCalendario2(int index) {
        int op;
        boolean cancelar = false;
        do {
            exibirData(catalao.get(index));
            menuEditarCalendario();
            try {
                op = leitor.nextInt();

                switch (op) {
                    case 1:
                        deletarEvento(catalao.get(index), catalao);
                        break;
                    case 2:
                        editarDataInicial(catalao.get(index), catalao);
                        break;
                    case 3:
                        editarDataFinal(catalao.get(index), catalao);
                        break;
                    case 4:
                        editarDescriacao(catalao.get(index), catalao);
                        break;
                    case 5:
                        editarClassificacao(catalao.get(index), catalao);
                        break;
                    case 6:
                        editarRegional(catalao.get(index), catalao);
                        break;
                    case 0:
                        cancelar = true;
                        break;
                    default:
                        System.out.println("Opcao invalida");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Opcao invalida");
                editarCalendario2(index);
            }
        } while (cancelar != true);

    }

    private static void menuEditarCalendario() {
        System.out.println("\t Editar Calendario");
        System.out.println("1 - Deletar Evento");
        System.out.println("2 - Editar Data Inicial");
        System.out.println("3 - Editar Data Final");
        System.out.println("4 - Editar Descricao");
        System.out.println("5 - Editar Classificacao");
        System.out.println("6 - Editar Regional");
        System.out.println("0 - Cancelar");
    }

}
