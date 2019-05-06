# DESCRIÇÃO SYNCWATCH

**Nome:** Leonardo Rick de Souza Marcineiro
**TIA:** 41607252

**Nome:** Ricardo Daniel
**TIA:**
---

Projeto desenvolvido na disciplina de Computação Distribuída da Universidade Presbiteriana Mackenzie.

Este programa tem como objetivo demonstrar de forma didática a execução do algoritmo de Berkley para sincronização de relógios.

Para executar o projeto, basta rodar o arquivo script presente com o nome Sync.sh

O arquivo Sync.sh contém uma sequência de instruções que criam N processos para a execução do programa.

---
<br>**args[0]** = "-m" ou "-s" para indicar se o processo deve inicializar um Master ou um Slave
#### Parâmetros para Master
<br>**args[1]** = ip e porta do processo
<br>**args[2]** = tempo da máquina que será enviado para o servidor e processar a sincronização
  
#### Parâmetros para Slave
<br>**args[1]** = ip e porta do processo
<br>**args[2]** = tempo da máquina que será enviado para o servidor e processar a sincronização

