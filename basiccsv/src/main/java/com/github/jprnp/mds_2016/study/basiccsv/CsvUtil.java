package com.github.jprnp.mds_2016.study.basiccsv;

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

public class CsvUtil {
	/**
	 * Constantes para formatação do arquivo.
	 */
	static final String FILENAME = "csv1.csv";
	static final String COMMA = ",";
	static final String NEWLINE = "\n";
	static final String HEADER = "id,dtIni,dtFim,descr";
	/**
	 * SimpleDateFormat para formatação dos campos de data.
	 */
	static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Gera o arquivo .csv.
	 * @param eventos ArrayList com todos os eventos
	 */
	public static void generateCsv(ArrayList<EventDate> eventos) {
		try {
			FileWriter writer = new FileWriter(new File(FILENAME), false); 
			writer.append(HEADER);
			for (EventDate e : eventos) {
				writer.append(NEWLINE);
				writer.append(String.valueOf(e.getId()));
				writer.append(COMMA);
				writer.append(sdf.format(e.getDtIni().getTime()));
				writer.append(COMMA);
				writer.append(sdf.format(e.getDtFim().getTime()));
				writer.append(COMMA);
				writer.append(e.getDescr());
			}
			writer.flush();
			writer.close();
			//System.out.println("Dados armazenados com sucesso!");
		} catch (IOException ex) {
			//System.out.println(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		} 		
	}
	/**
	 * Carrega o .csv em um ArrayList
	 * @return ArrayList com os valores armazenados no .csv
	 */
	public static ArrayList<EventDate> loadCsv() {
		ArrayList<EventDate> eventos = new ArrayList<EventDate>();
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
					eventos.add(new EventDate(id, dtIni, dtFim, results[3]));
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
	 * Ordena o ArrayList por Data de Início (Em ordem crescente)
	 * @param eventos ArrayList com todos os eventos
	 * @return ArrayList dos eventos ordenados
	 */
	public static ArrayList<EventDate> sortCsv(ArrayList<EventDate> eventos) {
		Collections.sort(eventos, new Comparator<EventDate>() {
			public int compare(EventDate o1, EventDate o2) {
			      return o1.getDtIni().getTime().compareTo(o2.getDtIni().getTime());
			}
		});
		return eventos;
	}
}
