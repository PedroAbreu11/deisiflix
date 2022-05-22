package pt.ulusofona.deisi.aed.deisiflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    static ArrayList<ArrayList<String>> linhasIgn = new ArrayList<>();
    static String[][] filmesFicheiro;

    static ArrayList<Filme> filmes;

    static String[][] atores;
    static String[][] votos;
    static String[][] genres;

    public static void main(String[] args) throws IOException {
        lerFicheiros();
    }

    static boolean temNulls(String[] a) {
        for (String s: a) {
            if (s == null) {
                return true;
            }
        }

        return false;
    }

    static ArrayList<Filme> getFilmes() {
        filmes = new ArrayList<>();
        int i = 0;

        for (String[] linha: filmesFicheiro) {
            if (!temNulls(linha)) {
                filmes.add(new Filme(
                        Integer.parseInt(linha[0]),
                        linha[1],
                        Float.parseFloat(linha[2]),
                        Integer.parseInt(linha[3]),
                        linha[4])
                );
            }
            i++;
        }

        // Ordena usando o QuickSort, para as pesquisas consequentes serem mais rápidas

        ArrayList<Filme> filmesTemp = (ArrayList<Filme>) filmes.clone();
        Ordenador.quickSort(filmesTemp);

        for (String[] s: votos) {
            if (s[0] != null) {

                // Usa a pesquisa binária para encontrar o filme com o id da linha atual dos votos
                // e adiciona os detalhes necessários
                Filme filme = Pesquisador.pesquisaBinaria(filmesTemp, Integer.parseInt(s[0]));
                if (filme != null) {
                    filme.mediaVotos = Float.parseFloat(s[1]);
                    filme.nVotos = Integer.parseInt(s[2]);
                }
            }
        }

        return filmes;
    }

    static ArrayList<String> getLinhasIgnoradas(String fileName) {
        for (ArrayList<String> list: linhasIgn) {
            if (Objects.equals(list.get(0), fileName)) {
                // Ignora o nome, que é o primeiro elemento do Array
                return new ArrayList<>(list.subList(1, list.size()));
            }
        }

        return null;
    }

    static void lerFicheiros() throws IOException {
        filmesFicheiro = lerFicheiroBuffered("deisi_movies.txt", 5);

        atores = lerFicheiroBuffered("deisi_people.txt", 5);

        votos = lerFicheiroBuffered("deisi_movie_votes.txt", 3);

        genres = lerFicheiroBuffered("deisi_genres.txt", 2);

        filmes = getFilmes();
    }

    static String[][] lerFicheiroBuffered(String fileName, int nParams) {
        try {
            ArrayList<String> linhasIgnoradas = new ArrayList<>();
            // Primeiro elemento do array vai ser o nome do ficheiro, para se poder encontrar mais tarde
            linhasIgnoradas.add(fileName);

            // Para ser removido no futuro

            int nLinhas = contarLinhas(fileName);
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            String[][] conteudos = new String[nLinhas][nParams];

            int l = 0;

            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] linhas;
                // Verifica o número de parametros
                if ((linhas = linha.split(",")).length == nParams) {
                    int i = 0;
                    for (String s: linhas) {
                        conteudos[l][i] = s.strip();
                        i++;
                    }
                    l++;
                } else {
                    linhasIgnoradas.add(linha);
                }
            }

            fileReader.close();
            reader.close();

            // Verificação das linhas ignoradas
            // Se já existir uma entrada com o mesmo nome, sobreescreve

            boolean existeIgnoradas = false;

            for (int i = 0; i < linhasIgn.size(); i++) {
                if (Objects.equals(linhasIgn.get(i).get(0), fileName)) {
                    existeIgnoradas = true;
                    linhasIgn.set(i, linhasIgnoradas);
                }
            }
            if (!existeIgnoradas) {
                linhasIgn.add(linhasIgnoradas);
            }

            return conteudos;
        } catch (IOException ignored) {
            return new String[0][0];
        }
    }

    static int contarLinhas(String fileName) throws IOException {
        // Usado para inicializar o array do lerFicheiroBuffered, por agora
        // Irá ser apagada quando se mudar para ArrayList

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            int nLinhas = (int) reader.lines().count();

            fileReader.close();
            reader.close();
            return nLinhas;
        } catch (IOException ignored) {
            return 0;
        }
    }

    /*static void medirTempos() throws IOException {
        long tempo1 = System.currentTimeMillis();
        lerFicheirosBuffered();

        long tempo2 = System.currentTimeMillis() - tempo1;

        long tempo3 = System.currentTimeMillis();
        lerFicheiros();
        long tempo4 = System.currentTimeMillis() - tempo3;

        System.out.println(tempo2 + "\n" + tempo4 + "\n");
    }*/
}
