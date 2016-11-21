/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jprnp.Calendario_Academico.calendarioacademico;

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
    
    FERIADO_NACIONAL(1, "Feriado Nacional"), FERIADO_MUNICIPAL(2, "Feriado Municipal"),
    PONTO_FACUTATIVO(3, "Ponto Facultativo"), RECESSO_ACADEMICO(4, "Recesso Acadêmico"),
    EVENTO(5, "Evento");
    
    private final String titulo;
    private final int numero;
    
    private Classificacao(int numero, String titulo) {
    	this.numero = numero;
        this.titulo = titulo;
    }
    /**
     * Resgata a descrição da classificação.
     * @return Descrição da Classificação
     */
    public String getClassificacao() {
        return titulo;
    }
    /**
     * Resgata o número correspondente à classificação.
     * @return Número da Classificação
     */
    public int getClassificacaoNum() {
    	return numero;
    }
    
}