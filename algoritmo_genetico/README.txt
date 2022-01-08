  PROJETO FEITO NO SEGUNDO SEMESTRE DE 2020 (8° PERÍODO)
  AUTORES: LUCAS JUNQUEIRA BASTOS, LUCAS PIRES BARBOZA, YURI SUHEISHI SILVA

O algoritmo foi desenvolvido visando obter o menor caminho a ser percorrido no grafo,
atribuindo pesos às arestas e organizando-o em uma matriz, sendo os valores de -1
representando caminhos inexistentes/impossíveis entre dados nós do grafo e os outros valores
representando o peso da aresta que os liga. Foi desenvolvido também um sistema de punição
de aptidão para valores repetidos e inexistentes de forma que reforce a ideia de percorrer as
arestas entre os nós sem caminhos circulares (de um nó para ele mesmo) e elimine a
probabilidade de erros de cálculo com os valores de caminhos impossíveis entre os nós.

Os dados de entrada dos grafos precisam seguir alguns padrões:
1. Eles precisão ser quadráticos (n por n),
2. É necessário colocar um ‘G’ para identificar a mudança de vértice,
3. A letra ‘X’ é usada para identificar os vértices que NÃO estão conectados no vértice
atual, os outros valores são os pesos. Qualquer valor diferente de X são caminhos
possíveis. 

Para fins de teste, o número máximo de gerações adotado pelo grupo foi de 50 gerações,
realizando-se assim um total de 10 execuções do código com valores diferentes dos parâmetros
de corte de crossover, tamanho da população, taxa de mutação, elitismo e método adotado.
