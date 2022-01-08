package algoritmo_genetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class AlgoritmoGenetico {
    private final int tamPopulacao;
    private ArrayList<Individuo> populacao;
    private int aptidaoTotal, aptidaoMaxIndivido;
    
    public AlgoritmoGenetico(int[][] grafo, int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
        populacao = new ArrayList<>();
        aptidaoMaxIndivido = MA(grafo)* MA(grafo) * 20;
        geraPopulacaoInicial(grafo);
        
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
    
    private void geraPopulacaoInicial(int[][] regrasGrafo) {

        for (int i = 0; i < tamPopulacao; i++) {

            populacao.add(new Individuo(regrasGrafo, aptidaoMaxIndivido));
            ordenaPopulacao(populacao);
            calculaAptidaoTotal();
        }
    }
    
    private void ordenaPopulacao(ArrayList<Individuo> populacao) {
        Collections.sort(populacao, new Comparator<Individuo>() {
            @Override
            public int compare(Individuo i1, Individuo i2) {
                return i2.getAptidao() - i1.getAptidao();
            }
        });
    }
    
    private void calculaAptidaoTotal() {
        aptidaoTotal = 0;

        for (Individuo i : populacao) {
            aptidaoTotal += i.getAptidao();
        }
    }
    
    //////////////////////////////////////////////////////////////////////////
    
    public void executa(int nGeracoes, int corteCrossover, int taxaMutacao, String metodo, int elitismo) {
        int i = 0;
        while (i < nGeracoes && populacao.get(0).getAptidao() != aptidaoMaxIndivido) {
            System.out.println("Geração " + i + "\nTamanho populacao: " + populacao.size() + "\nAptidao media:" + (aptidaoTotal / populacao.size()));
            System.out.println("Melhor indivíduo: ");
            populacao.get(0).exibeIndividuo();
            System.out.println("--------------------------------\n");
            geraNovaPopulacao(corteCrossover, taxaMutacao, metodo, elitismo);
            i++;
        }
        System.out.println("Solucao vencedora: ");
        populacao.get(0).exibeIndividuo();
    }
    
    private void geraNovaPopulacao(int corteCrossover, int taxaMutacao, String metodo, int elitismo) {
        ArrayList<Individuo> novaPopulacao = new ArrayList<>();
        Random r = new Random();

        if (elitismo > 0) {
            int i = 0;
            while (i < elitismo) {
                novaPopulacao.add(populacao.get(i));
                i++;
            }
        }

        
        while (novaPopulacao.size() < populacao.size()) {
            Individuo i1, i2;


            if (metodo.equals("roleta")) {
                i1 = selecionaRoleta();
                i2 = selecionaRoleta();
            } else {
                i1 = selecionaTorneio();
                i2 = selecionaTorneio();
            }

 
            novaPopulacao.add(new Individuo(i1.getGenotipo(), i2.getGenotipo(), aptidaoMaxIndivido, corteCrossover, i1.getregraGrafo()));
            if (r.nextInt(100) < taxaMutacao) {
                novaPopulacao.get(novaPopulacao.size() - 1).muta();
            }
            novaPopulacao.add(new Individuo(i2.getGenotipo(), i1.getGenotipo(), aptidaoMaxIndivido, corteCrossover, i1.getregraGrafo()));
            if (r.nextInt(100) < taxaMutacao) {
                novaPopulacao.get(novaPopulacao.size() - 1).muta();
            }
        }
        ordenaPopulacao(novaPopulacao);

     
        while (novaPopulacao.size() > populacao.size()) {
            novaPopulacao.remove(novaPopulacao.size() - 1);
        }

        populacao = novaPopulacao;
        calculaAptidaoTotal();
    }
    
    private Individuo selecionaRoleta() {
        Random r = new Random();
        int valor = r.nextInt(aptidaoTotal); 
        int i;

       
        for (i = 0; i < populacao.size(); i++) {
            valor -= populacao.get(i).getAptidao();
            if (valor <= 0) {
                break;
            }
        }

        return populacao.get(i);
    }
    
    private Individuo selecionaTorneio() {
        Random r = new Random();

 
        int i = r.nextInt(populacao.size());
        int j = r.nextInt(populacao.size());

    
        if (populacao.get(i).getAptidao() >= populacao.get(j).getAptidao()) {
            return populacao.get(i);
        } else {
            return populacao.get(j);
        }
    }
    
}
