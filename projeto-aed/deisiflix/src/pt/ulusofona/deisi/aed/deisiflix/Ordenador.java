package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;

public class Ordenador {
    static void quickSort(ArrayList<Filme> filmes) {
        quickSort(filmes, 0, filmes.size());
    }

    static void quickSort(ArrayList<Filme> filmes, int left, int right) {
        if (left < right) {
            int pivot = partition(filmes,left,right-1);

            quickSort(filmes,left,pivot);
            quickSort(filmes,pivot+1,right);
        }
    }

    static int partition(ArrayList<Filme> filmes, int left, int right) {
        Filme pivot = filmes.get(right);
        int leftIdx = left;
        int rightIdx = right - 1;
        while (leftIdx <= rightIdx) {

            if (filmes.get(leftIdx).idFilme > pivot.idFilme && filmes.get(rightIdx).idFilme < pivot.idFilme) {
                Filme temp = filmes.get(leftIdx);
                filmes.set(leftIdx, filmes.get(rightIdx));
                filmes.set(rightIdx, temp);
            }

            if (filmes.get(leftIdx).idFilme <= pivot.idFilme) {
                leftIdx++;
            }

            if (filmes.get(rightIdx).idFilme >= pivot.idFilme) {
                rightIdx--;
            }
        }

        filmes.set(right, filmes.get(leftIdx));
        filmes.set(leftIdx, pivot);
        return leftIdx;
    }

    // Nota para o futuro: Usar o merge sort para ordenar por pessoa
}
