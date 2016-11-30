/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jprnp.Calendario_Academico.calendarioacademico;

import java.util.Calendar;

/**
 *
 * @author Estevao
 */
public class DataComent extends Data {
    
    private String comentario;
    
    public DataComent(Calendar dataInicial, Calendar dataFinal, 
            String descricao, Regional regional, int IdEvento,
            String comentario) {
        super(dataInicial, dataFinal, descricao, regional, IdEvento);
        
        this.comentario = comentario;
        
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}