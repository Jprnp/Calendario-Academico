package com.github.jprnp.Calendario_Academico.calendarioacademico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Classe utilitária para operações relacionadas ao arquivo CSV.
 *
 * @author Jprnp
 * @version 1.0.0
 */
public class CsvUtil {

    static String filename;
    static final String COMMA = ";";
    static final String NEWLINE = "\n";
    static final String HEADER = "idEvento,dataInicial,dataFinal,"
            + "descricao,classificacao,regional";
    static final String FILECATALAO = "catalao.csv";
    static final String FILEGOIANIA = "goiania.csv";
    static final String FILEGOIAS = "goias.csv";
    static final String FILEJATAI = "jatai.csv";
    public static final String FILENAME = "calacaddata.csv";
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Recebe a lista de eventos + do evento a ser excluído e deleta tal evento
     * da lista.
     *
     * @author Hyago Vieira de Souza
     * @param lista Lista de Eventos no estado atual do programa
     * @param idEvento ID do evento a ser excluído
     * @return Lista após a exclusão do evento
     * @throws RuntimeException Caso o evento não seja encontrado
     */
    public static ArrayList<Data> deletarEvento(ArrayList<Data> lista, int idEvento)
            throws RuntimeException {

        int contador = 0;

        for (Data d : lista) {

            if (d.getIdEvento() == idEvento) {
                lista.remove(contador);
                break;
            } else {
                contador++;
            }
        }

        if (contador == lista.size()) {
            throw new RuntimeException("Evento não encontrado.");
        }

        return lista;
    }

    /**
     * Recebe a lista de eventos onde o evento a ser atualizado se encontra e
     * então retorna outra lista com tal evento substituido por outro passado
     * por parâmetro.
     *
     * @param lista Lista onde o evento a ser atualizado se encontra
     * @param evento Versão nova do evento
     * @return Lista com o evento substituído
     */
    public static ArrayList<Data> atualizaEvento(ArrayList<Data> lista, Data evento) {
        ArrayList<Data> nLista = new ArrayList<Data>();

        for (Data d : lista) {
            if (evento.getIdEvento() == d.getIdEvento()) {
                nLista.add(evento);
            } else {
                nLista.add(d);
            }
        }

        return nLista;
    }

    /**
     * Carrega os dados armazenados no arquivo .csv em um ArrayList.
     *
     * @return ArrayList com os eventos carregados
     * @throws RuntimeException Caso ocorra erro na conversão das datas para
     * formato dd/MM/aaaa
     */
    public static ArrayList<Data> loadCsv(Regional reg) throws RuntimeException {
        ArrayList<Data> eventos = new ArrayList<Data>();
        String line;
        try {
            Scanner csvFile = null;
            switch (reg.getRegionalNum()) {
                case 1:
                    csvFile = new Scanner(new File(FILECATALAO));
                    break;
                case 2:
                    csvFile = new Scanner(new File(FILEGOIANIA));
                    break;
                case 3:
                    csvFile = new Scanner(new File(FILEGOIAS));
                    break;
                case 4:
                    csvFile = new Scanner(new File(FILEJATAI));
                    break;
            }
            csvFile.nextLine();
            while (csvFile.hasNextLine()) {
                line = csvFile.nextLine();
                String[] results = line.split(COMMA);
                int id = Integer.parseInt(results[0]);
                try {
                    Calendar dtIni = (Calendar.getInstance());
                    dtIni.setTime(SDF.parse(results[1]));
                    Calendar dtFim = Calendar.getInstance();
                    dtFim.setTime(SDF.parse(results[2]));
                    String descricao = results[3];
                    Regional regional = null;
                    switch (Integer.parseInt(results[5])) {
                        case 1:
                            regional = Regional.CATALAO;
                            break;
                        case 2:
                            regional = Regional.GOIANIA;
                            break;
                        case 3:
                            regional = Regional.GOIAS;
                            break;
                        case 4:
                            regional = Regional.JATAI;
                            break;
                        case 5:
                            regional = Regional.TODAS;
                            break;
                    }

                    Classificacao classificacao = null;
                    switch (Integer.parseInt(results[4])) {
                        case 5:
                            classificacao = Classificacao.EVENTO;
                            break;
                        case 1:
                            classificacao = Classificacao.FERIADO_NACIONAL;
                            break;
                        case 2:
                            classificacao = Classificacao.FERIADO_MUNICIPAL;
                            break;
                        case 3:
                            classificacao = Classificacao.PONTO_FACUTATIVO;
                            break;
                        case 4:
                            classificacao = Classificacao.RECESSO_ACADEMICO;
                            break;
                    }

                    eventos.add(new Data(dtIni, dtFim, descricao, regional,
                            classificacao, id));
                } catch (ParseException pe) {
                    throw new RuntimeException(pe.getMessage());
                }
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        eventos = sortCsv(eventos);
        return eventos;
    }

    /**
     * Realiza ordenação dos eventos dentro de um ArrayList por Data Inicial em
     * ordem crescente.
     *
     * @param eventos ArrayList a ser ordenado
     * @return ArrayList ordenado
     */
    public static ArrayList<Data> sortCsv(ArrayList<Data> eventos) {
        Collections.sort(eventos, new Comparator<Data>() {
            public int compare(Data o1, Data o2) {
                return o1.getDataInicial().getTime().compareTo(o2.getDataInicial().getTime());
            }
        });
        return eventos;
    }

    public static ArrayList<Data> addEvento(ArrayList<Data> eventos, Data data) {
        eventos.add(data);
        return eventos;
    }

    public static void generateCsv(ArrayList<Data> eventos) {
        String name = null;
        try {
            switch (eventos.get(0).getRegional().getRegionalNum()) {
                case 1:
                    name = FILECATALAO;
                    break;
                case 2:
                    name = FILEGOIANIA;
                    break;
                case 3:
                    name = FILEGOIAS;
                    break;
                case 4:
                    name = FILEJATAI;
                    break;            
            }
            FileWriter writer = new FileWriter(new File(name), false);
            
            writer.append(HEADER);
            for (Data e : eventos) {
                writer.append(NEWLINE);
                writer.append(String.valueOf(e.getIdEvento()));
                writer.append(COMMA);
                writer.append(SDF.format(e.getDataInicial().getTime()));
                writer.append(COMMA);
                writer.append(SDF.format(e.getDataFinal().getTime()));
                writer.append(COMMA);
                writer.append(e.getDescricao());
                writer.append(COMMA);
                writer.append(String.valueOf(e.getClassificacao()
                        .getClassificacaoNum()));
                writer.append(COMMA);
                writer.append(String.valueOf(e.getRegional().getRegionalNum()));
            }
            writer.flush();
            writer.close();
            //System.out.println("Dados armazenados com sucesso!");
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

}
