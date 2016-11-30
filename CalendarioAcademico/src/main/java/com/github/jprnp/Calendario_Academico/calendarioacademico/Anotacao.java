package com.github.jprnp.Calendario_Academico.calendarioacademico;

import java.util.ArrayList;

public class Anotacao {

    private static ArrayList<DataComent> arrayDataComent = new ArrayList();
    
    public static void adicionaEvento(Data data, String coment)
            throws RuntimeException {

        try {
        DataComent dataComent = new DataComent(data.getDataInicial(),
                data.getDataFinal(), data.getDescricao(), data.getRegional(),
                data.getIdEvento(), coment);
            
            arrayDataComent.add(dataComent);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Ocorreu um erro ao tentar criar" +
                                      " um objeto DataComent.");
        }

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
        return this.arrayDataComent;
    }
}
