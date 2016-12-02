package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import static com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil.deletarEvento;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Classificacao;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Regional;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.CsvUtil;

public class App {

	static Scanner leitor = new Scanner(System.in);

	static ArrayList<Data> catalao = new ArrayList<Data>();
	static ArrayList<Data> goiania = new ArrayList<Data>();
	static ArrayList<Data> goias = new ArrayList<Data>();
	static ArrayList<Data> jatai = new ArrayList<Data>();
	static String[] argss;
	static boolean loaded = true;

	/**
	 * Menu Principal do Programa
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		argss = args;
		int op;
		boolean sair = false;
		try {
			try {
				if (loaded) {
					carregarCalendarios();
					loaded = false;
				}
			} catch (FileNotFoundException e) {

			}
			do {
				mainMenu();
				op = readInteger();
				switch (op) {
				case 1:
					exibirCalendario();
					break;
				case 2:
					criarEvento();
					break;
				case 3:
					editarCalendario();
					break;
				case 4:
					salvarCalendario();
					break;
				case 0:
					sair = true;
					break;
				default:
					System.out.println("Opcao Invalida!\n");
				}
			} while (sair == false);

			System.exit(op);

		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		}
		System.out.println("Obrigado por usar nosso Software! :) ");

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
		default:
			throw new Exception("Codigo invalido! Tente Novamente:\n");
		}
	}

	private static void exibirData(Data data) {

		System.out.print("Data: " + CsvUtil.SDF.format(data.getDataInicial().getTime()));
		if (data.getClassificacao() == Classificacao.EVENTO) {
			System.out.println(" até " + CsvUtil.SDF.format(data.getDataFinal().getTime()));
		}
		System.out.println("-------------------------------------------------");
		System.out.println("Descricao: " + data.getDescricao());
		System.out.println("Classificacao: " + data.getClassificacao().getClassificacao());
		System.out.println("ID: " + data.getIdEvento());
		System.out.println("=================================================");

	}

	private static void criarEvento() {
		Regional reg = selecionarRegional("Criar Evento");
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

		System.out.println("Descricao ou nome do Evento:");
		String descr = leitor.nextLine();
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
				switch (readInteger()) {
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
					break;
				}
			} while (sair != true);
		} catch (NumberFormatException nfe) {
			System.out.println("Comando invalido\n");
		}

	}

	private static Regional selecionarRegional(String titulo) {
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
			main(argss);
			break;
		default:
			System.out.println("Opcao invalida!\n");
			return selecionarRegional(titulo);
		}
		return null;
	}

	private static Classificacao selecionarClassificacao(String frase) {
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

		// EXPRESSÃO REGULAR RESPONSÁVEL PELA VERIFICAÇÃO
		String regex = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|"
				+ "((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0" + "[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|"
				+ "(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|" + "12|16|20|24|28|32|36|40|44|48|52|56|60|64|"
				+ "68|72|76|80|84|88|92|96)$)";

		boolean done = false;
		do {
			dt = leitor.nextLine();
			if (dt.matches(regex)) {
				done = true;
			} else {
				System.out.println(
						"Data inserida nao segue padrao " + "DD/MM/AAAA ou e uma data invalida - Tente" + " Novamente");
			}
		} while (!done);

		return dt;
	}

	public static void carregarCalendarios() throws FileNotFoundException {
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
	}

	private static void salvarCalendario() {
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
			System.out.println("Salvo com Sucesso!");
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
		}
	}

	private static void editarCalendario() {
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

	private static int buscarDataId(int id, ArrayList<Data> calendario) {
		for (Data data : calendario) {
			if (data.getIdEvento() == id) {
				return calendario.indexOf(data);
			}
		}
		return -1;
	}

	private static ArrayList<Data> editarCalendario2(int index, ArrayList<Data> calendario) {
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

	private static void menuEditarCalendario() {
		System.out.println("\t Editar Calendario");
		System.out.println("1 - Deletar Evento");
		System.out.println("2 - Editar Data Inicial");
		System.out.println("3 - Editar Data Final");
		System.out.println("4 - Editar Descricao");
		System.out.println("5 - Editar Classificacao");
		System.out.println("0 - Cancelar");
	}

	private static ArrayList<Data> editarRegional(int index, ArrayList<Data> calendario) {
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

	private static ArrayList<Data> editarClassificacao(int index, ArrayList<Data> calendario, int cl) {
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

	private static ArrayList<Data> editarDescriacao(int index, ArrayList<Data> calendario, String descricao) {
		calendario.get(index).setDescricao(descricao);
		return calendario;
	}

	public static ArrayList<Data> editarDataInicial(int index, ArrayList<Data> calendario) {
		Calendar dt = Calendar.getInstance();
		try {
			dt.setTime(CsvUtil.SDF.parse(readDateFormat()));
			calendario.get(index).setDataInicial(dt);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return calendario;
	}

	public static ArrayList<Data> editarDataFinal(int index, ArrayList<Data> calendario) throws ParseException {
		Calendar dt = Calendar.getInstance();
		try {
			dt.setTime(CsvUtil.SDF.parse(readDateFormat()));
			calendario.get(index).setDataFinal(dt);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return calendario;
	}

	/**
	 * Função que força o usuário a digitar um inteiro válido.
	 * 
	 * @return inteiro digitado
	 */
	public static int readInteger() {
		try {
			return Integer.parseInt(leitor.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\nFormato Invalido!");
			return -1;
		}
	}

}
