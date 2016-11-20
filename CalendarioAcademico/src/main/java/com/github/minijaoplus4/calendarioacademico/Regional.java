/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.minijaoplus4.calendarioacademico;

/**
 * Enum das Constantes do nome das Regionaias
 * Catalão, Goiânia, Goiás, Jataí e Todas.
 * 
 * @author Estevão Cristino
 * 
 * @version 1.0
 * 
 * @since Release 1 da aplicação
 */
public enum Regional {
    
    CATALAO("Catalão"), GOIANIA("Goiânia"), GOIAS("Goiás"), JATAI("Jataí"), 
    TODAS("Todas");
   
    private final String nome;
    
    private Regional(String nome) {
        this.nome = nome;
    }
    /**
     * resgata o nome da Regional
     * @return 
     */
    public String getRegional() {
        return nome;
    }
}
