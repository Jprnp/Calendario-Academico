package com.github.jprnp.Calendario_Academico.calendarioacademico;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Classe utilitária para operações relacionadas ao arquivo CSV.
 * @author Jprnp
 * @version 1.0.0
 */
public class CsvUtil {
	public static final String FILENAME = "calacaddata.csv";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Recebe a lista de eventos + do evento a ser excluído e 
	 * deleta tal evento da lista.
	 * @param lista Lista de Eventos no estado atual do programa
	 * @param idEvento ID do evento a ser excluído
	 * @return Lista após a exclusão do evento
	 * @throws RuntimeException Caso o evento não seja encontrado
	 */
	public static ArrayList<Data> deletarEvento(ArrayList<Data> lista, int idEvento)
            throws RuntimeException{
        
        int contador = 0;
        
        for (Data d : lista) {
            
            if(d.getIdEvento() == idEvento){
                lista.remove(contador);
                break;
            } else {
                contador++;
            }
        }
        
        if(contador == lista.size()){
            throw new RuntimeException("Evento não encontrado.");
        }
        
        return lista;
    }
	/**
	 * Recebe a lista de eventos onde o evento a ser atualizado se encontra
	 * e então retorna outra lista com tal evento substituido por outro passado
	 * por parâmetro.
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
	 * @return ArrayList com os eventos carregados
	 * @throws RuntimeException Caso ocorra erro na conversão  das datas
	 * para formato dd/MM/aaaa
	 */
	public static ArrayList<Data> loadCsv() throws RuntimeException{
		ArrayList<Data> eventos = new ArrayList<Data>();
		String line;
		try {
			Scanner csvFile = new Scanner(new File(FILENAME));
			csvFile.nextLine();
			while (csvFile.hasNextLine()) {
				line = csvFile.nextLine();
				String[] results = line.split(",");
				int id = Integer.parseInt(results[0]);				
				try {
					Calendar dtIni = Calendar.getInstance();
					dtIni.setTime(sdf.parse(results[1]));
					Calendar dtFim = Calendar.getInstance();
					dtFim.setTime(sdf.parse(results[2]));
					//eventos.add(new Data(id, dtIni, dtFim, results[3]));  <---- Adequar para a Classe nova
				} catch (ParseException pe) {
					throw new RuntimeException(pe.getMessage());
				}				
			}
			eventos = sortCsv(eventos);
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		
		return eventos;
	}
	/**
	 * Realiza ordenação dos eventos dentro de um ArrayList por Data Inicial 
	 * em ordem crescente.
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
        
        public static ArrayList<Data> addEvento(ArrayList<Data> eventos, DATA data){
            eventos.add(data);
        }
}
