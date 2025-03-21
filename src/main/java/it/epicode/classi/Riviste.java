package it.epicode.classi;

import it.epicode.interfacce.Periodicita;

public class Riviste extends Biblioteca {
    private Periodicita periodicita;

    public Riviste(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Riviste() {
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + ", Periodicita: " + periodicita;
    }

}
