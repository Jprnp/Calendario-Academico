package com.github.jprnp.Calendario_Academico.calendarioacademico.data;

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
    
    CATALAO(1, "Catalão"), GOIANIA(2, "Goiânia"), GOIAS(3, "Goiás"), JATAI(4, "Jataí"), 
    TODAS(5, "Todas");
   
    private final String nome;
    private final int numero;
    
    private Regional(int numero, String nome) {
    	this.numero = numero;
        this.nome = nome;
    }
    /**
     * Resgata o nome da Regional.
     * @return Nome da regional
     */
    public String getRegional() {
        return nome;
    }
    /**
     * Resgata o número correspondente à regional.
     * @return Número da regional
     */
    public int getRegionalNum() {
    	return numero;
    }
}