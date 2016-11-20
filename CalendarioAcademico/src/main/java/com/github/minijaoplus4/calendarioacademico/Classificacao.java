/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.minijaoplus4.calendarioacademico;

/**
 *
 * @author Estevao
 */
public enum Classificacao {
    
    FERIADO_NACIONAL("Feriado Nacional"), FERIADO_MUNICIPAL("Feriado Municipal"),
    PONTO_FACUTATIVO("Ponto Facultativo"), RECESSO_ACADEMICO("Recesso Acadêmico"),
    EVENTO("Evento");
    
    private final String titulo;
    
    private Classificacao(String titulo) {
        this.titulo = titulo;
    }
    /**
     * resgata o nome da Regional
     * @return 
     */
    public String getClassificacao() {
        return titulo;
    }
    
}
