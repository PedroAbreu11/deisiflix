package pt.ulusofona.deisi.aed.deisiflix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestFilme {
    @Test
    public void toStringTeste1() {
        Filme teste = new Filme(10, "Teste", "01-01-2022", 20, 5);
        assertEquals("10 | Teste | 2022-01-01 | 20 | 5.0", teste.toString());
    }

    @Test
    public void toStringTeste2() {
        Filme teste = new Filme();
        teste.idFilme = 10;
        teste.titulo = "Teste";
        teste.data = "01-01-2022";
        assertEquals("10 | Teste | 2022-01-01 | 0 | 0.0", teste.toString());
    }
}
