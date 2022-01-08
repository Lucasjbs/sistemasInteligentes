package algoritmo_genetico;
/**
 * @author LUCAS JUNQUEIRA BASTOS
 * @author LUCAS PIRES BARBOZA
 * @author YURI SUHEISHI SILVA
 */
public class Main {
    public static void main(String[] args) {
        String str_grafo = null;
        str_grafo = "G X 6 5 X X 2 G 6 X 3 X X X G 5 3 X 3 3 X "
        + "G X X 3 X 4 6 G X X 3 4 X X G 2 X X 6 X X";
        
        int[][] grafo;
        System.out.println(str_grafo);
        
        String[] aux1 = str_grafo.split(" ");
        String[] aux2 = str_grafo.split("G");
        aux2 = aux2[1].split(" ");
        grafo = new int[aux2.length -1][aux2.length -1];
        int j = -1;
        int k = 0;
        for (int i = 0; i < aux1.length; i++) {
            
            if(aux1[i].equals("G")) {
                System.out.println("");
                k=0;
                j++;
            }
            else{
                if(aux1[i].equals("X")){
                    grafo[j][k] = -1;
                }
                else{
                    grafo[j][k] = Integer.parseInt(aux1[i]);
                }
                System.out.print(grafo[j][k] + " ");
                k++;
            }
        }
        System.out.println("");


        AlgoritmoGenetico ag = new AlgoritmoGenetico(grafo, 1000); //instancia
        ag.executa(100, 4, 15, "roleta", 10); //executa
        
    }
}