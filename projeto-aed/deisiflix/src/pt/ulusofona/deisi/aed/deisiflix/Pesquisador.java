package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;

public class Pesquisador {
    static Filme pesquisaBinaria(ArrayList<Filme> filmes, int IDFilme) {
        int left = 0, right = filmes.size() -1, middle = (left + right) / 2;

        while (left < right) {
            Filme filmeMiddle = filmes.get(middle);
            if (filmeMiddle.idFilme == IDFilme) {
                return filmes.get(middle);
            }

            if (filmeMiddle.idFilme < IDFilme) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
            middle = (left + right) /2;
        }

        if (filmes.get(left).idFilme == IDFilme) {
            return filmes.get(left);
        }

        return null;
    }

    static Filme pesquisaLinear(ArrayList<Filme> filmes, int IDFilme) {
        for (Filme filme: filmes) {
            if (filme != null && filme.idFilme == IDFilme) {
                return filme;
            }
        }

        return null;
    }
}
