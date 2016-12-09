package com.github.jprnp.Calendario_Academico.calendarioacademico.util;

import java.util.ArrayList;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import com.github.jprnp.Calendario_Academico.calendarioacademico.data.DataComent;

public class Anotacao {

    private static ArrayList<DataComent> arrayDataComent = new ArrayList();
    
    public static ArrayList<DataComent> adicionaEvento(Data data, String coment)
            throws RuntimeException {

        try {
        DataComent dataComent = new DataComent(data.getDataInicial(),
                data.getDataFinal(), data.getTitulo(), data.getDescricao(), 
                data.getRegional(), data.getClassificacao(), data.getIdEvento(), coment);
            arrayDataComent.add(dataComent);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Ocorreu um erro ao tentar criar" +
                                      " um objeto DataComent.");
        }
        return getArrayDataComent();
    }

    public static void removeEvento(int idEvento)
            throws RuntimeException {
        
        int contador = 0;
        
        for (DataComent dc : arrayDataComent) {
            if (dc.getIdEvento() == idEvento) {
                arrayDataComent.remove(dc);
            } else {
                contador = contador + 1;
            }
        }
        
        if (contador == arrayDataComent.size()) {
            throw new RuntimeException("Evento não encontrado.");
        }

    }
    
    public static ArrayList<DataComent> getArrayDataComent() {
        return arrayDataComent;
    }
}
