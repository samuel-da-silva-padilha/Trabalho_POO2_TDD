#!/usr/bin/env bash
# ---------------------------------------------------------------------------
#  Executa todos os testes do projeto com o JUnit Platform Console Launcher.
#  Requer apenas um JDK (17 ou superior). Nao precisa do Maven.
#
#  Uso:  bash executar-testes.sh               (todas as classes de teste)
#        bash executar-testes.sh CompraTest    (apenas uma classe)
# ---------------------------------------------------------------------------
set -euo pipefail
cd "$(dirname "$0")"

JUNIT_JAR="lib/junit-platform-console-standalone-1.10.1.jar"
JUNIT_URL="https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/junit-platform-console-standalone-1.10.1.jar"

if [ -n "${JAVA_HOME:-}" ]; then
    JAVAC="$JAVA_HOME/bin/javac"
    JAVA="$JAVA_HOME/bin/java"
else
    JAVAC="javac"
    JAVA="java"
fi

if ! "$JAVAC" -version >/dev/null 2>&1; then
    echo "ERRO: JDK nao encontrado. Instale um JDK 17 ou superior e configure o JAVA_HOME."
    exit 1
fi

# No Git Bash/Cygwin (Windows) o separador do classpath e ';'
case "$(uname -s)" in
    MINGW*|MSYS*|CYGWIN*) SEP=";" ;;
    *) SEP=":" ;;
esac

if [ ! -f "$JUNIT_JAR" ]; then
    echo "Baixando o JUnit Platform Console Launcher 1.10.1..."
    mkdir -p lib
    if command -v curl >/dev/null 2>&1; then
        curl -sSfL -o "$JUNIT_JAR" "$JUNIT_URL"
    else
        wget -q -O "$JUNIT_JAR" "$JUNIT_URL"
    fi
fi

rm -rf out

echo "Compilando o codigo de producao e os testes..."
"$JAVAC" -encoding UTF-8 -d out/main src/main/java/br/ufpr/poo2/*.java
"$JAVAC" -encoding UTF-8 -cp "out/main${SEP}${JUNIT_JAR}" -d out/test src/test/java/br/ufpr/poo2/*.java

if [ $# -gt 0 ]; then
    SELECAO="--select-class br.ufpr.poo2.$1"
else
    SELECAO="--select-package br.ufpr.poo2"
fi

"$JAVA" -Dstdout.encoding=UTF-8 -jar "$JUNIT_JAR" execute --details=tree --disable-banner \
    --class-path "out/main${SEP}out/test" $SELECAO
