package pt.ulusofona.deisi.aed.deisiflix;

public class Filme {
    int idFilme;
    int id;
    String titulo;
    Pessoa[] actores;
    Pessoa[] realizadores;
    String tipoPessoa;
    Genero[] generos;
    String data;
    int orcamento;
    float mediaVotos;
    int nVotos;
    float duracao;

    Filme() {
        idFilme = 0;
        id = 0;
        titulo = "";
        data = "";
        orcamento = 0;
        mediaVotos = 0.0f;
        nVotos = 0;
        duracao = 0.0f;
    }

    Filme(int idFilme) {
        this.idFilme = idFilme;
    }

    Filme (int idFilme, String titulo, float duracao, int orcamento, String data){
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.duracao = duracao;
        this.orcamento = orcamento;
        this.data = data;
    }

    Filme (int idFilme, float mediaVotos, int nVotos){
        this.idFilme = idFilme;
        this.mediaVotos = mediaVotos;
        this.nVotos = nVotos;
    }

    Filme (String tipoPessoa, int id, String nomePessoa, char genero, int idFilme){

    }

    Filme (String nomeGenero, int idFilme){

    }

    Filme (int idFilme, String titulo, String data, int nVotos, float mediaVotos) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.data = data;
        this.nVotos = nVotos;
        this.mediaVotos = mediaVotos;
    }

    public String toString() {
        // Reordenar data para AAAA-MM-DD
        String[] d = data.split("-");
        return idFilme + " | " + titulo + " | " + d[2] + "-" + d[1] + "-" + d[0] + " | " + nVotos + " | " + mediaVotos;
    }
}

