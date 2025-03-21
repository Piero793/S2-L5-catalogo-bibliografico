package it.epicode.classi;

import lombok.Data;

@Data
public class Libri extends Biblioteca {
    private String autore;
    private String genere;

    public Libri(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libri() {
    }
}

