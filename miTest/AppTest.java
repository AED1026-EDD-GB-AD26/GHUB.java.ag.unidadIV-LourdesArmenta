package miTest;

import arbolAVL.ArbolAVL;
import arbolAVL.Entero;
import arbolAVL.Nodo;
import arbolBinario.ArbolBinarioBusqueda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

class AppTest {

    @Test
    void eliminacionesAleatoriasNoCorrompenElArbol() throws Exception {
        for (int tamanio = 1; tamanio <= 100; tamanio++) {
            ArbolAVL arbol = new ArbolAVL();
            List<Integer> valores = new ArrayList<>();

            for (int i = 1; i <= tamanio; i++) {
                valores.add(i);
            }

            Collections.shuffle(valores, new Random(tamanio));
            for (Integer valor : valores) {
                arbol.insertar(new Entero(valor));
            }

            Collections.shuffle(valores, new Random(tamanio));
            for (Integer valor : valores) {
                arbol.eliminar(new Entero(valor));
                assertVerificaAVL(arbol.getRaiz());
            }

            assertNull(arbol.getRaiz());
        }
    }

    @Test
    void operacionesBasicasDelArbolAVL() throws Exception {
        ArbolAVL arbol = new ArbolAVL();
        for (int i = 1; i <= 100; i++) {
            arbol.insertar(new Entero(i));
        }
        assertVerificaAVL(arbol.getRaiz());
        assertNotNull(arbol.buscar(new Entero(5)));
        assertNotNull(arbol.buscar2(new Entero(15)));
        assertNull(arbol.buscar(new Entero(101)));

        ArbolAVL pequeno = new ArbolAVL();
        pequeno.insertar(new Entero(2));
        pequeno.insertar(new Entero(1));
        pequeno.insertar(new Entero(3));
        assertVerificaAVL(pequeno.getRaiz());
        assertEquals("Entero [dato=2],Entero [dato=1],Entero [dato=3],", pequeno.preorden2());

        ArbolAVL vacio = new ArbolAVL();
        assertDoesNotThrow(vacio::preorden);
        assertDoesNotThrow(vacio::inorden);
        assertDoesNotThrow(vacio::postorden);
        assertDoesNotThrow(vacio::preordenIterativo);
        assertDoesNotThrow(vacio::inordenIterativo);
        assertDoesNotThrow(vacio::postordenIterativo);
        assertDoesNotThrow(vacio::recorridoPorNivel);
        assertEquals("", vacio.preorden2());

        assertThrows(Exception.class, () -> arbol.insertar("texto"));
        assertThrows(Exception.class, () -> arbol.eliminar("texto"));
        assertNull(arbol.buscar("texto"));
        assertNull(arbol.buscar2("texto"));
    }

    @Test
    void operacionesBasicasDelArbolBinario() throws Exception {
        ArbolBinarioBusqueda arbol = crearArbolBinario();
        assertEquals(8, datoBinario(arbol.buscar(entero(8))));
        assertEquals(4, datoBinario(arbol.buscar2(entero(4))));
        assertEquals(14, datoBinario(arbol.buscar(entero(14))));
        assertNull(arbol.buscar(entero(99)));
        assertNull(arbol.buscar2(entero(99)));

        ArbolBinarioBusqueda iterativo = new ArbolBinarioBusqueda();
        iterativo.insertar(entero(8));
        assertThrows(Exception.class, () -> iterativo.insertar(entero(8)));
        ArbolBinarioBusqueda recursivo = new ArbolBinarioBusqueda();
        recursivo.insertar2(entero(8));
        assertThrows(Exception.class, () -> recursivo.insertar2(entero(8)));

        ArbolBinarioBusqueda vacio = new ArbolBinarioBusqueda();
        assertNull(vacio.buscar(entero(1)));
        assertNull(vacio.buscar2(entero(1)));
        assertFalse(vacio.eliminar(entero(1)));
        assertEquals("", salidaDe(vacio::preorden));
        assertEquals("", salidaDe(vacio::inorden));
        assertEquals("", salidaDe(vacio::postorden));
        assertEquals("", salidaDe(vacio::preordenIterativo));
        assertEquals("", salidaDe(vacio::inordenIterativo));
        assertEquals("", salidaDe(vacio::postordenIterativo));
        assertEquals("", salidaDe(vacio::recorridoPorNiveles));
    }

    @Test
    void recorridosDelArbolBinarioMuestranElOrdenCorrecto() throws Exception {
        ArbolBinarioBusqueda arbol = crearArbolBinario();
        assertEquals(salidaEsperada(8, 4, 2, 6, 12, 10, 14), salidaDe(arbol::preorden));
        assertEquals(salidaEsperada(2, 4, 6, 8, 10, 12, 14), salidaDe(arbol::inorden));
        assertEquals(salidaEsperada(2, 6, 4, 10, 14, 12, 8), salidaDe(arbol::postorden));
        assertEquals(salidaEsperada(8, 4, 2, 6, 12, 10, 14), salidaDe(arbol::preordenIterativo));
        assertEquals(salidaEsperada(2, 4, 6, 8, 10, 12, 14), salidaDe(arbol::inordenIterativo));
        assertEquals(salidaEsperada(2, 6, 4, 10, 14, 12, 8), salidaDe(arbol::postordenIterativo));
        assertEquals(salidaEsperada(8, 4, 12, 2, 6, 10, 14), salidaDe(arbol::recorridoPorNiveles));
    }

    @Test
    void eliminacionesDelArbolBinario() throws Exception {
        ArbolBinarioBusqueda arbol = crearArbolBinario();
        assertTrue(arbol.eliminar(entero(2)));
        assertNull(arbol.buscar(entero(2)));
        assertEquals(salidaEsperada(4, 6, 8, 10, 12, 14), salidaDe(arbol::inorden));

        arbol = new ArbolBinarioBusqueda();
        arbol.insertar(entero(8));
        arbol.insertar(entero(4));
        arbol.insertar(entero(12));
        arbol.insertar(entero(10));
        assertTrue(arbol.eliminar(entero(12)));
        assertNull(arbol.buscar(entero(12)));
        assertEquals(10, datoBinario(arbol.getRaiz().getDerecho()));
        assertEquals(salidaEsperada(4, 8, 10), salidaDe(arbol::inorden));

        arbol = crearArbolBinario();
        assertTrue(arbol.eliminar(entero(8)));
        assertNull(arbol.buscar(entero(8)));
        assertEquals(salidaEsperada(2, 4, 6, 10, 12, 14), salidaDe(arbol::inorden));
        assertEquals(6, datoBinario(arbol.getRaiz()));

        arbol = new ArbolBinarioBusqueda();
        arbol.insertar(entero(8));
        arbol.insertar(entero(4));
        assertTrue(arbol.eliminar(entero(8)));
        assertEquals(4, datoBinario(arbol.getRaiz()));
        assertNull(arbol.getRaiz().getIzquierdo());
        assertNull(arbol.getRaiz().getDerecho());

        arbol = crearArbolBinario();
        assertFalse(arbol.eliminar(entero(99)));
        assertEquals(salidaEsperada(2, 4, 6, 8, 10, 12, 14), salidaDe(arbol::inorden));
    }

    private static ArbolBinarioBusqueda crearArbolBinario() throws Exception {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        for (int valor : new int[]{8, 4, 12, 2, 6, 10, 14}) {
            arbol.insertar(entero(valor));
        }
        return arbol;
    }

    private static arbolBinario.Entero entero(int valor) {
        return new arbolBinario.Entero(valor);
    }

    private static int datoBinario(arbolBinario.Nodo nodo) {
        assertNotNull(nodo);
        return ((arbolBinario.Entero) nodo.getValor()).getDato();
    }

    private static String salidaEsperada(int... valores) {
        StringBuilder salida = new StringBuilder();
        for (int valor : valores) {
            salida.append("Entero [dato=").append(valor).append("] ");
        }
        return salida.toString();
    }

    private static String salidaDe(Runnable recorrido) {
        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream captura = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(captura));
            recorrido.run();
            return captura.toString();
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static void assertVerificaAVL(Nodo nodo) {
        assertVerificaAVL(nodo, new HashSet<>());
    }

    private static int assertVerificaAVL(Nodo nodo, Set<Nodo> visitados) {
        if (nodo == null) {
            return 0;
        }

        assertTrue(
            visitados.add(nodo),
            "El árbol contiene una referencia circular"
        );
        int alturaIzquierda = assertVerificaAVL(nodo.getIzquierdo(), visitados);
        int alturaDerecha = assertVerificaAVL(nodo.getDerecho(), visitados);
        int factorCalculado = alturaDerecha - alturaIzquierda;
        assertTrue(Math.abs(factorCalculado) <= 1, "Nodo desbalanceado: " + nodo.getValor());
        assertEquals(factorCalculado, nodo.getFe(), "Factor de equilibrio incorrecto en: " + nodo.getValor());
        visitados.remove(nodo);
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
}