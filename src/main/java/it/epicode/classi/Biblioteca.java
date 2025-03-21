package it.epicode.classi;

import lombok.Data;

@Data
public class Biblioteca {
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    public Biblioteca(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }
}
