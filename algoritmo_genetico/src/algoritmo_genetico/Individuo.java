
package algoritmo_genetico;


import java.util.HashMap;
import java.util.Random;


public class Individuo {
    private int[] genotipo;
    private int aptidao;
    private int[][] regraGrafo;
    private HashMap<Integer, Integer> verificarDuplicados;
    
    
    public Individuo(int[][] n, int aptidaoMax) { 
        regraGrafo = n;
        genotipo = new int[n.length];
        geraGenotipoAleatorio();
        calcAptidao(aptidaoMax, n);
    }
    
    public Individuo(int[] pai, int[] mae, int aptidaoMax, int corteCrossover, int[][] n) {
        regraGrafo = n;
        genotipo = new int[pai.length];
        crossOver(pai, mae, corteCrossover);
        calcAptidao2(aptidaoMax, n);
    }
    
    public int valorCaminho(){
        int valor = 0;
        int i;
        int[][] grafo = getregraGrafo();

        for(i=0;i<genotipo.length-1;i++){
            if(grafo[genotipo[i]][genotipo[i+1]]!=-1){
                valor += grafo[genotipo[i]][genotipo[i+1]];
            }
            else{
                valor += MA(grafo) * 2;
            }
        }
         return valor;

    }
    
    
    private int MA(int[][] grafo){
        int ma=0;
        for(int i=0;i<grafo.length - 1;i++){
            for(int j=0;j<grafo.length - 1;j++){
                if(grafo[i][j]>ma){
                    ma=grafo[i][j];
                }
            }
        }
        return ma;
    }
    
    private void geraGenotipoAleatorio() {
        Random r = new Random();
        for (int i = 0; i < getGenotipo().length; i++) { 
            genotipo[i] = r.nextInt(getGenotipo().length);
        }

    }
    
    private void calcAptidao(int aptidaoMax, int[][] grafo) {
        verificarDuplicados = new HashMap<>();
        int erros = 0;
        int anterior = -1;
        
        for (int i = 0; i < getGenotipo().length; i++) {
            if(anterior != -1 && grafo[anterior][genotipo[i]] == -1){
                erros+= MA(grafo)*2;
            }
            verificarDuplicados.put(genotipo[i], i);
            anterior = genotipo[i];
        }
        erros += valorCaminho();
        aptidao = aptidaoMax - erros;
        
        if(verificarDuplicados.size() < getGenotipo().length){
            aptidao = aptidao /2;
        }
    }
    //////////////////////////////////////////////////////////////////////////
    
    public void exibeIndividuo() {
        System.out.print("Caminho percorrido: ");
        int[] caminho = getGenotipo();
        for (int i = 0; i < getGenotipo().length; i++) {
            System.out.print(caminho[i] + " ");
        }
        System.out.println("");
        System.out.println("Aptidão = " + getAptidao());
        System.out.println("Valor Caminho = " + valorCaminho());
    }
    
    private void crossOver(int[] pai, int[] mae, int corteCrossover) {
        System.arraycopy(pai, 0, genotipo, 0, corteCrossover); //copia a parte inicial do pai
        System.arraycopy(mae, corteCrossover, genotipo, corteCrossover, genotipo.length - corteCrossover); //copia a parte final da mãe

    }
    
    public void muta() {
        Random r = new Random();

        genotipo[r.nextInt(genotipo.length)] = r.nextInt(genotipo.length);
    }
    
    private void calcAptidao2(int aptidaoMax, int[][] grafo) {  
        verificarDuplicados = new HashMap<>();
        int erros = 0;
        int anterior = -1;
        
        for (int i = 0; i < getGenotipo().length; i++) {
            if(anterior != -1 && grafo[anterior][genotipo[i]] == -1){
                erros+= MA(grafo)*2;
            }
            verificarDuplicados.put(genotipo[i], i);
            anterior = genotipo[i];
        }
        
        erros += valorCaminho();
        
        aptidao = aptidaoMax - erros;
        if(verificarDuplicados.size() < getGenotipo().length){
            aptidao = aptidao /2;
        }
    
    }
    
    //////////////////////////////////////////////////////////////////////////
    public int[] getGenotipo() {
        return genotipo;
    }
    public int getAptidao() {
        return aptidao;
    }
    public int[][] getregraGrafo() {
        return regraGrafo;
    }
    
}
