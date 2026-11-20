# Proyecto - Java con Pruebas para Autograding
Este es un cambio

Plantilla básica para proyecto de Java con Autograding

## Diagrama de clases - Árbol Binario de Búsqueda
[Editor en línea](https://mermaid.live/)

```mermaid
---
title: Árbol Binario de Búsqueda
---
classDiagram
    class Comparable {
        <<interface>>
        +esMenor(Object) boolean
        +esIgual(Object) boolean
        +esMayor(Object) boolean
    }

    class Nodo {
        -valor: Object
        -izquierdo: Nodo
        -derecho: Nodo
        +Nodo()
        +Nodo(Object)
        +getValor() Object
        +setValor(Object) void
        +getIzquierdo() Nodo
        +setIzquierdo(Nodo) void
        +getDerecho() Nodo
        +setDerecho(Nodo) void
    }

    class ArbolBinarioBusqueda {
        -raiz: Nodo
        +ArbolBinarioBusqueda()
        +ArbolBinarioBusqueda(Nodo)
        +getRaiz() Nodo
        +setRaiz(Nodo) void
        +insertar(Object) void
        +insertar2(Object) void
        +buscar(Object) Nodo
        +buscar2(Object) Nodo
        +eliminar(Object) boolean
        +preorden() void
        +inorden() void
        +postorden() void
        +preordenIterativo() void
        +inordenIterativo() void
        +postordenIterativo() void
        +recorridoPorNiveles() void
        -insertarRec(Nodo, Comparable) Nodo
        -localizar(Nodo, Comparable) Nodo
        -reemplazarPorMayorIzquierdo(Nodo) void
        -visitar(Nodo) void
    }

    class Entero {
        -dato: int
        +Entero(int)
        +getDato() int
        +setDato(int) void
        +toString() String
        +esIgual(Object) boolean
        +esMayor(Object) boolean
        +esMenor(Object) boolean
    }

    class Estudiante {
        -codigo: int
        -nombre: String
        +Estudiante(int, String)
        +getCodigo() int
        +getNombre() String
        +setCodigo(int) void
        +setNombre(String) void
        +toString() String
        +esIgual(Object) boolean
        +esMayor(Object) boolean
        +esMenor(Object) boolean
    }

    class ArbolBinario {
        <<abstract>>
        #raiz: Nodo
        +ArbolBinario()
        +ArbolBinario(Nodo)
        +getRaiz() Nodo
        +setRaiz(Nodo) void
        +preorden() void
        +inorden() void
        +postorden() void
        #preorden(Nodo) void
        #inorden(Nodo) void
        #postorden(Nodo) void
        #visitar(Nodo) void
    }

    Nodo --> Nodo : referencia
    ArbolBinarioBusqueda --> Nodo : utiliza
    ArbolBinarioBusqueda --> Comparable : utiliza
    Comparable <|.. Entero : implementa
    Comparable <|.. Estudiante : implementa
    ArbolBinario <|-- ArbolBinarioBusqueda : extiende
```

[Referencia-Mermaid](https://mermaid.js.org/syntax/classDiagram.html)

## Diagrama de clases UML con draw.io
El repositorio está configurado para crear Diagramas de clases UML con ```draw.io```. Para usarlo simplemente agrega un archivo con extensión ```.drawio.png```, das doble clic sobre el mismo y se activará el editor ```draw.io``` incrustado en ```VSCode``` para edición. Asegúrate de agregar las formas UML en el menú de formas del lado izquierdo (opción ```+Más formas```).

## Uso del proyecto con make

### Default - Compilar+Probar+Ejecutar
```
make
```
### Compilar
```
make compile
```
### Probar todo
```
make test
```
### Ejecutar App
```
make run
```
### Limpiar binarios
```
make clean
```
## Comandos Git-Cambios y envío a Autograding

### Por cada cambio importante que haga, actualice su historia usando los comandos:
```
git add .
git commit -m "Descripción del cambio"
```
### Envíe sus actualizaciones a GitHub para Autograding con el comando:
```
git push origin main
```
## Comandos individuales
### Compilar

```
find ./ -type f -name "*.java" > compfiles.txt
javac -d build -cp lib/junit-platform-console-standalone-1.5.2.jar @compfiles.txt
```
Ejecutar ambos comandos en 1 sólo paso:

```
find ./ -type f -name "*.java" > compfiles.txt ; javac -d build -cp lib/junit-platform-console-standalone-1.5.2.jar @compfiles.txt
```


### Ejecutar Todas la pruebas locales de 1 Test Case

```
java -jar lib/junit-platform-console-standalone-1.5.2.jar -class-path build --select-class miTest.AppTest
```
### Ejecutar 1 prueba local de 1 Test Case

```
java -jar lib/junit-platform-console-standalone-1.5.2.jar -class-path build --select-method miTest.AppTest#appHasAGreeting
```
### Ejecutar App
```
java -cp build miPrincipal.Principal
```
Los comandos anteriores están considerados para un ambiente Linux. [Referencia.](https://www.baeldung.com/junit-run-from-command-line)
