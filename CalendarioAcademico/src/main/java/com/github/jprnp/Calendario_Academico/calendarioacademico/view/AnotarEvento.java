/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jprnp.Calendario_Academico.calendarioacademico.view;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.util.Anotacao;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.App.*;
import static com.github.jprnp.Calendario_Academico.calendarioacademico.view.AppHelper.*;

/**
 *
 * @author Estevao
 */
public class AnotarEvento {
    

    static void anotarEvento(Data evento) {
        System.out.println("Deseja colocar alguma observação no evento?");
        System.out.println(" 1 - Sim");
        System.out.println(" 2 - Não");
        String comente = "";
        String c;
        boolean sair = false;
        do {
            switch (readInteger()) {
                case 1:
                    do {
                        System.out.println("Escreva sua observação sobre o evento");
                        c = leitor.nextLine();
                        comente = c;
                        c.replaceAll(" ", "");
                    } while (c.equals(""));
                    Anotacao.adicionaEvento(evento, comente);
                    coment = Anotacao.getArrayDataComent();

                    sair = true;
                    break;
                case 2:
                    Anotacao.adicionaEvento(evento, comente);
                    coment = Anotacao.getArrayDataComent();
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        } while (sair == false);
        salvarCalendario();
        main(new String[1]);
    }

}
