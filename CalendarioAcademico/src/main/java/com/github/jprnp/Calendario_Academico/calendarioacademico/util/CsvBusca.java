package com.github.jprnp.Calendario_Academico.calendarioacademico.util;

/**
 *
 * @author pedro
 */
import java.util.ArrayList;
import java.util.Calendar;

import com.github.jprnp.Calendario_Academico.calendarioacademico.data.Data;
import java.text.ParseException;

public class CsvBusca {
    
    public static Data buscaId (ArrayList<Data> eventos, 
                                int iddigitado){
        Data resultadobusca = null;
        int cont;
        for (Data dt : eventos){
            if (dt.getIdEvento() == iddigitado){
                resultadobusca = dt;
            }
        }
        return resultadobusca;
    }
    
    public static ArrayList<Data> buscaDescricao (ArrayList<Data> eventos, 
                                       String descricaodigitada){
        ArrayList<Data> resultadobusca = new ArrayList<Data>();
        int cont;
        for (Data dt : eventos){
            if (dt.getDescricao().matches("(?i).*"+descricaodigitada+".*")){
                resultadobusca.add(dt);
            }
        }
        return resultadobusca;
    }
    
    public static ArrayList<Data> buscaTitulo (ArrayList<Data> eventos, 
                                       String titulodigitado){
        ArrayList<Data> resultadobusca = new ArrayList<Data>();
        int cont;
        for (Data dt : eventos){
            if (dt.getTitulo().matches("(?i).*"+titulodigitado+".*")){
                resultadobusca.add(dt);
            }
        }
        return resultadobusca;
    }
    
    public static ArrayList<Data> buscaData (ArrayList<Data> eventos, 
                                             String data) throws ParseException{
        ArrayList<Data> resultadobusca = new ArrayList<Data>();
        Calendar datadigitada = Calendar.getInstance();
        datadigitada.setTime(CsvUtil.SDF.parse(data));
        for (Data dt : eventos){
            if ( (dt.getDataInicial().equals(datadigitada)) || 
                   (dt.getDataFinal().equals(datadigitada)) ||
                    ( (datadigitada.before(dt.getDataFinal())) && 
                       (datadigitada.after(dt.getDataInicial()))) ){
                resultadobusca.add(dt);
            }
        }
        return resultadobusca;
    }
    
}
