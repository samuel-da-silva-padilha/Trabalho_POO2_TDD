# Trabalho 02 – Testes Automatizados e TDD

Programação Orientada a Objetos II – UFPR Setor Palotina
Aluno: Samuel da Silva Padilha (GRR20240770)

Repositório: https://github.com/samuel-da-silva-padilha/Trabalho_POO2_TDD

## Requisitos

- Java 25 LTS (JDK)
- Maven 3.9.11 ou superior (ou uma IDE com suporte a Maven)
- JUnit 5 (JUnit Jupiter 5.10.1), baixado automaticamente pelo Maven

## Estrutura

```
Trabalho_POO2_TDD/
├── pom.xml
├── README.md
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

## Como executar os testes

### Opção 1 – Maven (terminal)

Na pasta do projeto (onde está o `pom.xml`):

```
mvn test
```

Resultado esperado: `Tests run: 25, Failures: 0, Errors: 0, Skipped: 0` e `BUILD SUCCESS`.

O mesmo comando gera o relatório de cobertura do JaCoCo em `target/site/jacoco/index.html`
(abra no navegador).

Para rodar só uma classe de teste:

```
mvn test -Dtest=CompraTest
```

### Opção 2 – IDE

Abra a pasta como projeto Maven (IntelliJ IDEA: File > Open; Eclipse: Import > Existing Maven Projects;
VS Code: extensão "Extension Pack for Java"). Depois clique com o botão direito em `src/test/java`
e escolha "Run All Tests".

### Opção 3 – JUnit Console Launcher (sem Maven)

Baixe `junit-platform-console-standalone-1.10.1.jar` em
https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/
e salve na pasta do projeto.

Linux / macOS:

```
javac -encoding UTF-8 -d out/main src/main/java/br/ufpr/poo2/*.java
javac -encoding UTF-8 -cp out/main:junit-platform-console-standalone-1.10.1.jar -d out/test src/test/java/br/ufpr/poo2/*.java
java -jar junit-platform-console-standalone-1.10.1.jar execute --class-path out/main:out/test --select-package br.ufpr.poo2
```

Windows (troque `:` por `;` no classpath):

```
javac -encoding UTF-8 -d out/main src/main/java/br/ufpr/poo2/*.java
javac -encoding UTF-8 -cp out/main;junit-platform-console-standalone-1.10.1.jar -d out/test src/test/java/br/ufpr/poo2/*.java
java -jar junit-platform-console-standalone-1.10.1.jar execute --class-path out/main;out/test --select-package br.ufpr.poo2
```

## Histórico do ciclo TDD (Exercício 4)

O ciclo Red–Green–Refactor está registrado nos commits do Git:

```
git log --oneline
```

- `TDD Red` – testes escritos antes da implementação (os 4 falham)
- `TDD Green` – implementação mínima (os 4 passam)
- `TDD Refactor` – melhoria da legibilidade sem mudar o comportamento (todos passam)
- `TDD Red (ciclo 2)` – novos testes de valores-limite e de valor nulo (o do valor nulo falha)
- `TDD Green (ciclo 2)` – validação do valor nulo (os 7 testes passam)

Para ver o código de uma etapa específica, use por exemplo `git checkout a241cf7`
(etapa Red) e depois `git checkout main` para voltar.
