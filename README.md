# Trabalho 02 – Testes Automatizados e TDD

Programação Orientada a Objetos II – UFPR Setor Palotina
Professor: José Lucas Errera Gomes
Aluno: Samuel da Silva Padilha (GRR20240770)

**Repositório no GitHub:** https://github.com/samuel-da-silva-padilha/Trabalho_POO2_TDD

Para baixar o projeto pelo Git:

```
git clone https://github.com/samuel-da-silva-padilha/Trabalho_POO2_TDD.git
cd Trabalho_POO2_TDD
```

## Conteúdo

| Exercício | Código de produção | Testes |
|---|---|---|
| 3 – Testes automatizados em Java | `Calculadora.java` | `CalculadoraTest.java` (13 testes) |
| 4 – TDD (Red–Green–Refactor) | `Compra.java` | `CompraTest.java` (7 testes) |
| 5 – Mocks e stubs | `Pedido.java`, `ServicoFrete.java` | `PedidoTest.java` (5 testes, com stub e mock) |

Total: **25 testes de unidade**, todos aprovados, com 100% de cobertura de linhas e de
ramificações (medida com o JaCoCo).

## Requisitos

- **JDK 25 LTS** para executar com o Maven (o `pom.xml` compila com `release 25`).
  Os scripts `executar-testes` funcionam com qualquer JDK 17 ou superior.
- O Maven **não precisa estar instalado**: o projeto inclui o Maven Wrapper (`mvnw` / `mvnw.cmd`),
  que baixa o Maven 3.9.11 automaticamente na primeira execução.
- JUnit 5 (JUnit Jupiter 5.10.1), baixado automaticamente.
- Conexão com a internet na primeira execução (para baixar o Maven e o JUnit).

Se o comando `java -version` mostrar uma versão antiga, configure a variável `JAVA_HOME`
apontando para a pasta do JDK 25 antes de executar.

## Como executar os testes

### Opção 1 – Script pronto (mais simples)

Na pasta do projeto:

| Sistema | Comando |
|---|---|
| Windows (Prompt de Comando ou PowerShell) | `executar-testes.bat` |
| Linux / macOS / Git Bash | `bash executar-testes.sh` |

O script compila o código, baixa o JUnit Platform Console Launcher para a pasta `lib/`
(se ainda não estiver lá) e mostra o resultado em forma de árvore, no mesmo formato das
figuras do relatório. Para executar só uma classe de teste:

```
executar-testes.bat CompraTest
bash executar-testes.sh CompraTest
```

### Opção 2 – Maven Wrapper (sem instalar o Maven)

| Sistema | Comando |
|---|---|
| Windows | `mvnw.cmd test` |
| Linux / macOS | `sh mvnw test` |

Resultado esperado:

```
[INFO] Tests run: 25, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Para executar só uma classe: `mvnw.cmd test -Dtest=CompraTest`.

### Opção 3 – Maven instalado

```
mvn test
```

### Opção 4 – IDE

Abra a pasta como projeto Maven (IntelliJ IDEA: *File > Open*; Eclipse: *Import > Existing Maven
Projects*; VS Code: extensão *Extension Pack for Java*). Depois clique com o botão direito em
`src/test/java` e escolha *Run All Tests*.

### Opção 5 – Comandos manuais com o JUnit Console Launcher

Baixe `junit-platform-console-standalone-1.10.1.jar` em
https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/
e salve na pasta do projeto.

Windows:

```
javac -encoding UTF-8 -d out/main src/main/java/br/ufpr/poo2/*.java
javac -encoding UTF-8 -cp "out/main;junit-platform-console-standalone-1.10.1.jar" -d out/test src/test/java/br/ufpr/poo2/*.java
java -jar junit-platform-console-standalone-1.10.1.jar execute --details=tree --class-path "out/main;out/test" --select-package br.ufpr.poo2
```

Linux / macOS (troque `;` por `:` no classpath):

```
javac -encoding UTF-8 -d out/main src/main/java/br/ufpr/poo2/*.java
javac -encoding UTF-8 -cp "out/main:junit-platform-console-standalone-1.10.1.jar" -d out/test src/test/java/br/ufpr/poo2/*.java
java -jar junit-platform-console-standalone-1.10.1.jar execute --details=tree --class-path "out/main:out/test" --select-package br.ufpr.poo2
```

## Cobertura de testes

O comando `mvnw.cmd test` (ou `mvn test`) também gera o relatório do JaCoCo em
`target/site/jacoco/index.html`. Abra esse arquivo no navegador para ver a cobertura
por classe e as linhas exercitadas pelos testes.

## Histórico do ciclo TDD (Exercício 4)

O ciclo Red–Green–Refactor está registrado nos commits do Git. Para ver o histórico:

```
git log --oneline
```

| Etapa | Commit | Situação dos testes de `Compra` |
|---|---|---|
| TDD Red | `a241cf7` | 4 testes escritos antes da implementação – os 4 falham |
| TDD Green | `c045865` | implementação mínima – os 4 passam |
| TDD Refactor | `102df4d` | constantes e métodos auxiliares, mesmo comportamento – os 4 passam |
| TDD Red (ciclo 2) | `3ff75ad` | testes de R$ 99,99, R$ 0,00 e valor nulo – o do valor nulo falha |
| TDD Green (ciclo 2) | `3c87bcd` | validação do valor nulo – os 7 passam |

O Exercício 3 também tem as duas execuções registradas: `e7c21a8` (testes da `Calculadora`
com 2 falhas, pois a divisão por zero ainda não era tratada) e `b6ce7af` (correção, todos passam).

Para executar os testes de uma etapa específica (exemplo com a etapa Red), traga o código
daquele commit, execute e depois volte ao código atual:

```
git restore --source=a241cf7 src
executar-testes.bat CompraTest
git restore src
```

No Linux/macOS, use `bash executar-testes.sh CompraTest` no segundo comando.

## Estrutura

```
Trabalho_POO2_TDD/
├── README.md
├── pom.xml                       (Maven + JUnit 5 + JaCoCo)
├── mvnw, mvnw.cmd, .mvn/         (Maven Wrapper)
├── executar-testes.bat           (script para Windows)
├── executar-testes.sh            (script para Linux/macOS)
└── src/
    ├── main/java/br/ufpr/poo2/
    │   ├── Calculadora.java      (Exercício 3)
    │   ├── Compra.java           (Exercício 4 - TDD)
    │   ├── Pedido.java           (Exercício 5)
    │   └── ServicoFrete.java     (Exercício 5)
    └── test/java/br/ufpr/poo2/
        ├── CalculadoraTest.java  (13 testes)
        ├── CompraTest.java       (7 testes)
        └── PedidoTest.java       (5 testes: stub e mock)
```
