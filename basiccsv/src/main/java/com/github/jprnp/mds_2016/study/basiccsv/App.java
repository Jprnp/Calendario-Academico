package com.github.jprnp.mds_2016.study.basiccsv;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class App {
	static Scanner read = new Scanner(System.in);
    public static void main( String[] args ) {
        int op = 0, id;
        String dtIniStr, dtFimStr, descr;
        ArrayList<EventDate> eventos = new ArrayList<EventDate>();
        
        //AQUI O PROGRAMA TENTA CARREGAR O ARQUIVO ANTIGO... SE DER EXCEÇÃO,
        //QUER DIZER QUE O ARQUIVO AINDA NÃO EXISTE
        try {
        	eventos = CsvUtil.loadCsv();
        } catch (RuntimeException ex) {
        	System.out.println("Base de dados nao encontrada. Sera "
        			+ "criada uma nova.");
        }
        
    	do {
    		System.out.print("\n= = = = = = = = = = = = = =");
    	    System.out.print("\n|M E N U   D E   A C O E S|");    
    	    System.out.print("\n= = = = = = = = = = = = = =");
    	    System.out.print("\n= 1 - INSERIR NOVO EVENTO =");
    	    System.out.print("\n= 2 - EXIBIR EVENTOS      =");
    	    System.out.print("\n= 3 - SALVAR E SAIR       =");
    	    System.out.print("\n= = = = = = = = = = = = = =\n");
    	    
    	    for (EventDate e : eventos) {
    	    	System.out.println(e);
    	    }
    	      
    	    op = readInteger();
    	      
    	    switch (op) {
    	    case 1: // INSERIR NOVO EVENTO
    	    	
    	    	Calendar dtIni = Calendar.getInstance(), dtFim = Calendar.getInstance();
    	    	System.out.println("Codigo ID:");
    	    	id = readInteger();
    	    	System.out.println("Data de Inicio (DD/MM/AAAA):");
    	    	
    	    	//FORCA O USUARIO A DIGITAR UMA DATA VÁLIDA NO FORMATO DD/MM/AAAA
    	    	dtIniStr = readDateFormat();
    	    	
    	    	//TENTA CONVERTER A STRING DE DATA LIDA EM FORMATO DATE E 
    	    	//EM SEGUIDA "SETA" A DATA DO CALENDAR COM O RESULTADO
    	    	try {
    	    		dtIni.setTime(CsvUtil.sdf.parse(dtIniStr));
    	    	} catch (ParseException pe) {
    	    		System.out.println(pe.getMessage());
    	    	}
    	    	
    	    	//REPETE O QUE FOI FEITO ANTERIORMENTE
    	    	System.out.println("Data do Fim (DD/MM/AAAA):");
    	    	dtFimStr = readDateFormat();
    	    	try {
    	    		dtFim.setTime(CsvUtil.sdf.parse(dtFimStr));
    	    	} catch (ParseException pe) {
    	    		System.out.println(pe.getMessage());
    	    	}
    	    	
    	    	System.out.println("Descricao do Evento:");
    	    	descr = read.nextLine();
    	    	
    	    	//ADICIONA NOVO EVENTO AO ARRAYLIST
    	    	eventos.add(new EventDate(id, dtIni, dtFim, descr));
    	    	
    	    	break;
    	    case 2:
    	    	//N TA FEITO MAS É SIMPLES DJAEUHDUAHAEHD
    	    	break;
    	    case 3:
    	    	CsvUtil.generateCsv(eventos); // GERA O ARQUIVO .CSV
    	    	break;
    	    default:
    	      System.out.println("\nOperacao Invalida!");
    	    }
    	    
    	    //ORGANIZA O ARRAYLIST AO FINAL DE CADA OPERAÇÃO
    	    eventos = CsvUtil.sortCsv(eventos); 
    	    
    	} while (op != 3);
    	System.exit(0);
    }
    
    /**
     * Função que força o usuário a digitar um inteiro válido.
     * @return inteiro digitado
     */
    public static int readInteger() {
        try {
          return Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("\nFormato Invalido!");
          return 0;
        }
    }
    
    /**
     * Função que força o usuário a digitar uma data válida no formato
     * DD/MM/AAAA
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
    		dt = read.nextLine();
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
}
