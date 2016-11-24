# Jogo do lixo
## Executando o jogo
A versão mais atualizada: [jogo/desktop/build/libs/desktop-1.0.jar](https://github.com/BarelyAliveMau5/JogoDoLixo/blob/master/jogo/desktop/build/libs/desktop-1.0.jar)

## Compilando e rodando o jogo
### No windows:
1. Abra uma janela de console usando o caminho onde o arquivo gradlew.bat está localizado (na pasta: Shift+Click direito -> Abrir janela de comando - se tiver preguiça como eu);
2. Defina a variável JAVA_HOME apontando para a pasta onde o jdk está instalado, caso não exista (de forma genérica mas sem garantia que funcione, tente: `set JAVA_HOME=C:\Arquivos de Programas\Java && set PATH=%PATH%;%JAVA_HOME%\jdk1.8.0_60`);
3. Execute o script batch `gradlew.bat` e verifique se não houve erros;
  * A versão do gradle usada no projeto será baixada automaticamente, se der algum erro de `Connection Reset`, esqueça, use outra máquina, é chutar o cachorro morto;
  * Note que a primeira etapa deve eliminar um erro chato, porisso vale verificar se essa variável do ambiente existe;
  * Se ocorrer algum erro em relação à incapacidade de alocação de memória, abra o arquivo `gradle.properties` e diminua a o parâmetro `-Xmx1500m` para `-Xmx1000m`, ou menor que isso (se não me engano é por causa da JVM estar rodando em 32bits);
  * Se ainda ocorrer algum erro em relação à alocação, tente abrir o script batch `gradlew.bat` e mude o texto `"set DEFAULT_JVM_OPTS="` para `"set DEFAULT_JVM_OPTS=-Xmx1000m"`;
  * Se o erro persistir veja no stackoverflow, provavelmente alguém ja resolveu isso. Google tradutor é seu amigo.
4. Se tudo deu certo, para rodar o jogo execute o comando `gradlew.bat run` e pra compilar o jar execute `gradlew.bat desktop:dist`;
5. Se quiser ver as operações que podem ser feitas execute o comando `gradlew.bat tasks`.

### No linux:
1. Execute o script gradlew para rodar o jogo `gradlew run` e para compilar o jar `gradlew desktop:dist`.
  * Se foi eu quem disse, é porque deu certo. Se você realmente usa um pc com linux, provavelmente vai saber resolver os problemas que aparecerem.
