# DESCRIÇÃO SYNCWATCH

Este programa tem como objetivo demonstrar de forma didática a execução do algoritmo de Berkley para sincronização de relógios.

Para executar o projeto, basta rodar o arquivo script presente com o nome Sync.sh

O arquivo Sync.sh contém uma sequência de instruções que criam N processos para a execução do programa.

---
>**args[0]** = "-m" ou "-s" para indicar se o processo deve inicializar um Master ou um Slave  
#### Parâmetros para Master
>**args[1]** = tempo da máquina que será enviado para o servidor e processar a sincronização   
>**args[2]** = arquivo de log onde serão gravadas as saídas

  
#### Parâmetros para Slave
>**args[1]** = ip e porta do processo  
>**args[2]** = tempo da máquina que será enviado para o servidor e processar a sincronização  
>**args[3]** = arquivo de log onde serão gravadas as saídas
