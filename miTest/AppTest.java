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
    void insercionMantieneArbolAVLBalanceado() throws Exception {
        ArbolAVL arbol = new ArbolAVL();

        for (int i = 1; i <= 100; i++) {
            arbol.insertar(new Entero(i));
        }

        assertVerificaAVL(arbol.getRaiz());
    }

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
    void insertarMantieneLaPropiedadAVL() throws Exception {
        ArbolAVL arbol = new ArbolAVL();

        arbol.insertar(new Entero(10));
        arbol.insertar(new Entero(5));
        arbol.insertar(new Entero(15));

        assertNotNull(arbol.buscar(new Entero(5)));
        assertNotNull(arbol.buscar2(new Entero(15)));
        assertVerificaAVL(arbol.getRaiz());
    }

    @Test
    void preorden2DevuelveTodosLosNodosEnPreorden() throws Exception {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(new Entero(2));
        arbol.insertar(new Entero(1));
        arbol.insertar(new Entero(3));

        assertEquals(
            "Entero [dato=2],Entero [dato=1],Entero [dato=3],",
            arbol.preorden2()
        );
    }

    @Test
    void recorridosSobreArbolVacioNoLanzanExcepcion() {
        ArbolAVL arbol = new ArbolAVL();

        assertDoesNotThrow(arbol::preorden);
        assertDoesNotThrow(arbol::inorden);
        assertDoesNotThrow(arbol::postorden);
        assertDoesNotThrow(arbol::preordenIterativo);
        assertDoesNotThrow(arbol::inordenIterativo);
        assertDoesNotThrow(arbol::postordenIterativo);
        assertDoesNotThrow(arbol::recorridoPorNivel);
        assertEquals("", arbol.preorden2());
    }

    @Test
    void rechazaTiposQueNoImplementanComparable() {
        ArbolAVL arbol = new ArbolAVL();

        assertThrows(Exception.class, () -> arbol.insertar("texto"));
        assertThrows(Exception.class, () -> arbol.eliminar("texto"));
        assertNull(arbol.buscar("texto"));
        assertNull(arbol.buscar2("texto"));
    }

    @Test
    void insertaValoresYLosEncuentraConAmbasBusquedas() throws Exception {
        ArbolBinarioBusqueda arbol = crearArbolBinario();

        assertEquals(8, datoBinario(arbol.buscar(entero(8))));
        assertEquals(4, datoBinario(arbol.buscar2(entero(4))));
        assertEquals(14, datoBinario(arbol.buscar(entero(14))));
        assertNull(arbol.buscar(entero(99)));
        assertNull(arbol.buscar2(entero(99)));
    }

    @Test
    void rechazaValoresDuplicadosEnAmbasInserciones() throws Exception {
        ArbolBinarioBusqueda iterativo = new ArbolBinarioBusqueda();
        iterativo.insertar(entero(8));
        assertThrows(Exception.class, () -> iterativo.insertar(entero(8)));

        ArbolBinarioBusqueda recursivo = new ArbolBinarioBusqueda();
        recursivo.insertar2(entero(8));
        assertThrows(Exception.class, () -> recursivo.insertar2(entero(8)));
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
    void eliminarHojaDesconectaSoloEseNodo() throws Exception {
        ArbolBinarioBusqueda arbol = crearArbolBinario();

        assertTrue(arbol.eliminar(entero(2)));

        assertNull(arbol.buscar(entero(2)));
        assertEquals(salidaEsperada(4, 6, 8, 10, 12, 14), salidaDe(arbol::inorden));
    }

    @Test
    void eliminarNodoConUnHijoConectaSuHijoConElPadre() throws Exception {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(entero(8));
        arbol.insertar(entero(4));
        arbol.insertar(entero(12));
        arbol.insertar(entero(10));

        assertTrue(arbol.eliminar(entero(12)));

        assertNull(arbol.buscar(entero(12)));
        assertEquals(10, datoBinario(arbol.getRaiz().getDerecho()));
        assertEquals(salidaEsperada(4, 8, 10), salidaDe(arbol::inorden));
    }

    @Test
    void eliminarRaizConDosHijosConservaElOrdenDelArbol() throws Exception {
        ArbolBinarioBusqueda arbol = crearArbolBinario();

        assertTrue(arbol.eliminar(entero(8)));

        assertNull(arbol.buscar(entero(8)));
        assertEquals(salidaEsperada(2, 4, 6, 10, 12, 14), salidaDe(arbol::inorden));
        assertEquals(6, datoBinario(arbol.getRaiz()));
    }

    @Test
    void eliminarRaizConUnSoloHijoPromueveEseHijo() throws Exception {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(entero(8));
        arbol.insertar(entero(4));

        assertTrue(arbol.eliminar(entero(8)));

        assertEquals(4, datoBinario(arbol.getRaiz()));
        assertNull(arbol.getRaiz().getIzquierdo());
        assertNull(arbol.getRaiz().getDerecho());
    }

    @Test
    void eliminarValorInexistenteDevuelveFalseSinCambiarElArbol() throws Exception {
        ArbolBinarioBusqueda arbol = crearArbolBinario();

        assertFalse(arbol.eliminar(entero(99)));
        assertEquals(salidaEsperada(2, 4, 6, 8, 10, 12, 14), salidaDe(arbol::inorden));
    }

    @Test
    void operacionesYRecorridosDelArbolBinarioVacioFuncionan() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        assertNull(arbol.buscar(entero(1)));
        assertNull(arbol.buscar2(entero(1)));
        assertFalse(arbol.eliminar(entero(1)));
        assertEquals("", salidaDe(arbol::preorden));
        assertEquals("", salidaDe(arbol::inorden));
        assertEquals("", salidaDe(arbol::postorden));
        assertEquals("", salidaDe(arbol::preordenIterativo));
        assertEquals("", salidaDe(arbol::inordenIterativo));
        assertEquals("", salidaDe(arbol::postordenIterativo));
        assertEquals("", salidaDe(arbol::recorridoPorNiveles));
    }

    private static ArbolBinarioBusqueda crearArbolBinario() throws Exception {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(entero(8));
        arbol.insertar(entero(4));
        arbol.insertar(entero(12));
        arbol.insertar(entero(2));
        arbol.insertar(entero(6));
        arbol.insertar(entero(10));
        arbol.insertar(entero(14));
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

        assertTrue(
            Math.abs(factorCalculado) <= 1,
            "Nodo desbalanceado: " + nodo.getValor()
        );
        assertEquals(
            factorCalculado,
            nodo.getFe(),
            "Factor de equilibrio incorrecto en: " + nodo.getValor()
        );

        visitados.remove(nodo);
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
}