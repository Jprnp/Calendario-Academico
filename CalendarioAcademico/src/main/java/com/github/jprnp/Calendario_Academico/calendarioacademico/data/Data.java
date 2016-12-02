package com.github.jprnp.Calendario_Academico.calendarioacademico.data;
//importações
import java.util.Calendar;
/**
*
* @author Estevao
*/
public class Data {
  //Atributos
  private int idEvento;
  private Calendar dataInicial;
  private Calendar dataFinal;
  private String descricao;
  private Classificacao classificacao;
  private Regional regional;
  
  /*
  *
  */
  public Data(Calendar dataInicial, Calendar dataFinal, String descricao, 
          Regional regional, int idEvento) {
      
      this.dataInicial = dataInicial;
      this.dataFinal = dataFinal;
      this.descricao = descricao;
      classificacao = Classificacao.EVENTO;
      this.regional = regional;
      this.idEvento = idEvento;
      
  }
  
   public Data(Calendar dataInicial, Calendar dataFinal, String descricao, 
          Regional regional, Classificacao classificacao, int idEvento) {
      
      this.dataInicial = dataInicial;
      this.dataFinal = dataFinal;
      this.descricao = descricao;
      this.classificacao = classificacao;
      this.regional = regional;
      this.idEvento = idEvento;
      
  }
  
  public Data(Calendar data, String descricao, Regional regional, int idEvento,
          Classificacao classificacao) {

      this.dataInicial = data;
      this.dataFinal = null;
      this.descricao = descricao;
      this.classificacao = classificacao;
      this.regional = regional;
      this.idEvento = idEvento;
  }
  
  
  public int getIdEvento() {
      return idEvento;
  }
  
  public void setIdEvento(int idEvento) {
      this.idEvento = idEvento;
  }
  
  public Calendar getDataInicial() {
      return dataInicial;
  }
  
  public void setDataInicial(Calendar data) {
      this.dataInicial = data;
  }
  
  
  public Calendar getDataFinal() {
      return dataFinal;
  }
  
  public void setDataFinal(Calendar data) {
      this.dataFinal = data;
  }
  
  public String getDescricao() {
      return descricao;
  }
  
  public void setDescricao(String descricao) {
      this.descricao = descricao;
  }
  
  public Regional getRegional() {
      return regional;
  }
  
  public void setRegional(Regional regional) {
      this.regional = regional;
  }
  
  public Classificacao getClassificacao() {
      return classificacao;
  }
  
 public void setClassificacao(Classificacao classificacao) {
      this.classificacao = classificacao;
      
  }
}