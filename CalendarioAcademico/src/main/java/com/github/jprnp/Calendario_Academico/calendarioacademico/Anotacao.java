package anotacao;

import java.util.ArrayList;

public class Anotacao {

    public static ArrayList<String> adicionaEvento(Data data, String coment) {

        nomeDoArrayDataComent.setIdEvento(data.getIdEvento());
        nomeDoArrayDataComent.setDataInicial(data.getDataInicial());
        nomeDoArrayDataComent.setDataFinal(data.getDataFinal());
        nomeDoArrayDataComent.setDescricao(data.getDescricao());
        nomeDoArrayDataComent.setClassificacao(data.getClassificacao());
        nomeDoArrayDataComent.setRegional(data.getRegional());
        
        return nomeDoArrayDataComent;
    }

    public static ArrayList<String> removeEvento(int idEvento)
            throws RuntimeException{
        
        int contador = 0;
        
        for (DataComent dc : nomeDoArrayDataComent) {
            if (dc.getIdEvento() == idEvento) {
                nomeDoArrayDataComent.remove(dc);
            } else {
                contador = contador + 1;
            }
        }
        
        if (contador == nomeDoArrayDataComent.size()) {
            throw new RuntimeException("Evento não encontrado.");
        }
        
        return nomeDoArrayDataComent;
    }
}
