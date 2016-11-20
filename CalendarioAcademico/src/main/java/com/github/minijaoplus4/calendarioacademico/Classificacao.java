/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.minijaoplus4.calendarioacademico;

/**
 * Enum das Constantes de tipo de Eventos
 * Feriado Nacional, Feriado Municipal, Ponto Facutativo, Recesso Acadêmico,
 * Evento.
 * 
 * @author Estevão Cristino
 * 
 * @version 1.0
 * 
 * @since Release 1 da aplicação
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
