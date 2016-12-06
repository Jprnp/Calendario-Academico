package com.github.jprnp.Calendario_Academico.calendarioacademico.util;

/**
 *
 * @author pedro
 */
import java.util.ArrayList;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;

public class CsvBusca {
    
    public static Data buscaId (ArrayList<Data> eventos, 
                                int iddigitado){
        Data resultadobusca = null;
        int cont;
        for(cont = 0; cont < eventos.size(); cont++){
            if(eventos.get(cont).getIdEvento() == iddigitado)
                 resultadobusca = eventos.get(cont);
        }
        return resultadobusca;
    }
    
    public static ArrayList<Data> buscaDescricao (ArrayList<Data> eventos, 
                                       String descricaodigitada){
        ArrayList<Data> resultadobusca = new ArrayList<Data>();
        int cont;
        for(cont = 0; cont < eventos.size(); cont++){
            if(eventos.get(cont).getDescricao()
                    .matches("(?i).*"+descricaodigitada+".*"))
                resultadobusca.add(eventos.get(cont));       
        }
        return resultadobusca;
    }
    
    public static ArrayList<Data> buscaTitulo (ArrayList<Data> eventos, 
                                       String titulodigitado){
        ArrayList<Data> resultadobusca = new ArrayList<Data>();
        int cont;
        for(cont = 0; cont < eventos.size(); cont++){
            if(eventos.get(cont).getTitulo()
                    .matches("(?i).*"+titulodigitado+".*"))
                resultadobusca.add(eventos.get(cont));       
        }
        return resultadobusca;
    }
}
