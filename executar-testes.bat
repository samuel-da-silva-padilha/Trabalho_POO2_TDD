@echo off
rem ---------------------------------------------------------------------------
rem  Executa todos os testes do projeto com o JUnit Platform Console Launcher.
rem  Requer apenas um JDK (17 ou superior). Nao precisa do Maven.
rem
rem  Uso:  executar-testes.bat               (todas as classes de teste)
rem        executar-testes.bat CompraTest    (apenas uma classe)
rem ---------------------------------------------------------------------------
setlocal
chcp 65001 >nul
cd /d "%~dp0"

set "JUNIT_JAR=lib\junit-platform-console-standalone-1.10.1.jar"
set "JUNIT_URL=https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/junit-platform-console-standalone-1.10.1.jar"

if defined JAVA_HOME (
    set "JAVAC=%JAVA_HOME%\bin\javac.exe"
    set "JAVA=%JAVA_HOME%\bin\java.exe"
) else (
    set "JAVAC=javac"
    set "JAVA=java"
)

"%JAVAC%" -version >nul 2>&1
if errorlevel 1 (
    echo ERRO: JDK nao encontrado. Instale um JDK 17 ou superior e configure o JAVA_HOME.
    exit /b 1
)

if not exist "%JUNIT_JAR%" (
    echo Baixando o JUnit Platform Console Launcher 1.10.1...
    if not exist lib mkdir lib
    curl -sSfL -o "%JUNIT_JAR%" "%JUNIT_URL%"
    if errorlevel 1 (
        echo ERRO: nao foi possivel baixar o arquivo. Baixe manualmente em:
        echo %JUNIT_URL%
        echo e salve na pasta lib.
        exit /b 1
    )
)

if exist out rmdir /s /q out

echo Compilando o codigo de producao e os testes...
"%JAVAC%" -encoding UTF-8 -d out\main src\main\java\br\ufpr\poo2\*.java
if errorlevel 1 exit /b 1
"%JAVAC%" -encoding UTF-8 -cp "out\main;%JUNIT_JAR%" -d out\test src\test\java\br\ufpr\poo2\*.java
if errorlevel 1 exit /b 1

if "%~1"=="" (
    set "SELECAO=--select-package br.ufpr.poo2"
) else (
    set "SELECAO=--select-class br.ufpr.poo2.%~1"
)

"%JAVA%" -Dstdout.encoding=UTF-8 -jar "%JUNIT_JAR%" execute --details=tree --disable-banner --disable-ansi-colors --class-path "out\main;out\test" %SELECAO%
exit /b %errorlevel%
