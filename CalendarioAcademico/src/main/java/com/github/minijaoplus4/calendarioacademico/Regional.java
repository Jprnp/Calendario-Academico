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
