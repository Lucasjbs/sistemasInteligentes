 PROJETO FEITO NO SEGUNDO SEMESTRE DE 2020 (8° PERÍODO)

O Miniprojeto de Redes Neurais foi feito na linguagem de programação
Java com o uso da biblioteca externa jfreechart para realizar a plotagem dos
gráficos. Para executar o programa, é necessário instalar a JDK, JRE e o
NetBeans da Oracle. Caso o programa não esteja localizando as importações
da “org.jfree.chart”, é necessário clicar com o botão direito na pasta
‘bibliotecas’, que fica na barra lateral esquerda onde estão os projetos do
NetBeans, e feito isso adicione as pastas JAR da biblioteca do jfreechart para o
projeto.

A classe ‘leitor’ extrai os dados do arquivo ‘tic-tac-toe.data’, convertendo os
dados ‘positive’ e ‘negative’ para 1 e 0 respectivamente. Além disso, os dados
‘b’, ’x’, ’o’, foram convertidos para 0, 1 e 2, e por fim, esses dados são usados
pela classe ‘modelo’ para calcular o custo de cada interação.

Os resultados obtidos em gráfico não foram os esperados para a Rede
Neural Rasa. Apesar do custo diminuir a cada interação eles ainda estão
elevados em comparação com o que era esperado, inclusive, o custo foi maior
do que o custo na Regressão Logística. Além disso, não houve alteração na
acurácia em nenhuma das configurações.
Esse não foi o resultado esperado pela equipe, porém, acreditamos que o
problema pode ter sido causado algum erro na programação, pois a acurácia
atinge o valor final muito rápido para ser real.
Além disso, achamos curioso o fato de que os neurônios possuem a mesma
entrada e função alterando apenas o peso.
