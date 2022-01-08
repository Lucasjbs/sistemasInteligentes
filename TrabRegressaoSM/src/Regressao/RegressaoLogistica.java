package Regressao;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/**
 *
 * @author Lucas Junqueira e Yuri Silva
 */
public class RegressaoLogistica {

    public static void main(String[] args) throws FileNotFoundException {
        //teste de sanidade, para checar o funcionamento
        double[][] X_treino = {{4.5, 5.5, 3.0, 2.1, 2.2, 0.1},
        {2.5, 3.5, 1.0, 0.1, 1.2, 0.2},
        {-4.5, -5.5, -3.0, -2.1, -2.2, 1.0},
        {-2.5, -3.5, -1.0, -0.1, -1.2, 1.2},
        {0, 0, 0, 0, 0, 0},
        {-10.2, -4.5, -3.0, -12.7, -9.0, 0.3},
        {1, 2, 3, 4, 5, 0.33},
        {5, 4, 3, 2, 1, 0.12},
        {4.8, 5.8, 3.3, 2.4, 2.5, 1.0}};

        double[] Y_treino = {1, 1, 0, 0, 1, 1, 0, 0, 0};

        Modelo m = new Modelo(X_treino, Y_treino, X_treino, Y_treino);
        m.constroi_modelo(0.005, 1000, true);
        
        //começo da nossa parte
        //tentativa com leitor
        Leitor x = new Leitor();
                
        //array de arraylist, banco de dados
        ArrayList<Leitor>[] bdd = new ArrayList[2]; 
        bdd[0] = x.lerBaseDeDados("treino.txt");
        bdd[1] = x.lerBaseDeDados("teste.txt");
                
        //teste dado
        System.out.println("tipo " + bdd[0].get(4).classe);
        System.out.println("dados: ");
        for(int i=0; i<bdd[0].get(4).dados.size() ;i++){
        System.out.println(bdd[0].get(4).dados.get(i));
        }        
        
        double [][] treinoX = new double[1000][9];
        double [] treinoY = new double[1000];
        
        for(int i=0;i<bdd[0].size();i++){
            treinoY[i]=bdd[0].get(i).classe;
            for(int j=0;j<bdd[0].get(i).dados.size();j++){
                treinoX[i][j]=bdd[0].get(i).dados.get(j);
            }
        }
        
        double [][] testeX = new double[500][9];
        double [] testeY = new double[500];
        
        for(int k=0;k<bdd[1].size();k++){
            testeY[k]=bdd[1].get(k).classe;
            for(int l=0;l<bdd[1].get(k).dados.size();l++){
                testeX[k][l]=bdd[1].get(k).dados.get(l);
            }
        }
        
        
        Modelo T = new Modelo(treinoX, treinoY, testeX, testeY);
        T.constroi_modelo(0.001, 1000, true);
        
        /////////////////////////////////////////
        XYSeries series = new XYSeries("Teste");
        for (int i = 0; i < T.getX_interacao().size(); i++)
            series.add(T.getY_custo().get(i), T.getX_interacao().get(i));
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Grafico Interação X Custo", 
                "Custo", 
                "Interacao", 
                dataset, 
                PlotOrientation.HORIZONTAL, 
                true, 
                true, 
                true);
        
        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setDomainZoomable(true);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(chartpanel, BorderLayout.NORTH);

        JFrame frame = new JFrame();
        frame.add(jPanel4);
        frame.pack();
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }
}