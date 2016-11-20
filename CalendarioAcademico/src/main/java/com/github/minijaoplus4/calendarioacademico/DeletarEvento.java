
package deletarevento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DeletarEvento {
    
    public static void deletarEvento(String deletarIsso,File arquivo){
        try{
            
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            
            String linha = br.readLine();
            ArrayList<String> guardar = new ArrayList();
            
            while(linha != null){
                
                if(linha.equals(deletarIsso) == false){
                    guardar.add(linha);
                }
                
                linha = br.readLine();
            }
            
            br.close();
            fr.close();
            FileWriter fw2 = new FileWriter(arquivo, true);
            fw2.close();
            
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int contador = 0; contador < guardar.size(); contador++){
                bw.write( guardar.get(contador) );
                bw.newLine();
            }
            bw.close();
            fw.close();
            
        }catch(IOException ex){
            System.out.println("Um Erro ocorreu.");
        }
    }
    
    public static void main(String[] args) {
        File arquivo = new File("arquivo.txt");
        deletarEvento("oi", arquivo);
    }
}
