package deletarevento;

import java.util.ArrayList;

public class DeleterEvento2 {
    
    public static ArrayList deletarEvento2(ArrayList lista, int idEvento)
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
}
