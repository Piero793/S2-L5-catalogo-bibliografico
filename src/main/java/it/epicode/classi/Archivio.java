package it.epicode.classi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Archivio {
    private List<Biblioteca> catalogo;

    public Archivio() {
        this.catalogo = new ArrayList<>();
    }

    // Metodo per aggiungere un elemento
    public void aggiungiElemento(Biblioteca elemento) {
        try {
            if (catalogo.stream().anyMatch(e -> e.getIsbn().equalsIgnoreCase(elemento.getIsbn()))) {
                throw new IllegalArgumentException("Elemento con ISBN " + elemento.getIsbn() + " già presente!");
            }
            catalogo.add(elemento);
            System.out.println("Elemento aggiunto correttamente: " + elemento);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore durante l'aggiunta: " + e.getMessage());
        }
    }

    // Metodo per cercare un elemento per ISBN
    public Biblioteca cercaPerISBN(String isbn) {
        try {
            Biblioteca risultato = catalogo.stream()
                    .filter(e -> e.getIsbn().equalsIgnoreCase(isbn))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Elemento con ISBN " + isbn + " non trovato!"));
            System.out.println("Elemento trovato: " + risultato);
            return risultato;
        } catch (Exception e) {
            System.out.println("Errore durante la ricerca: " + e.getMessage());
            return null;
        }
    }

    // Metodo per rimuovere un elemento dato un codice ISBN
    public void rimuoviPerISBN(String isbn) {
        try {
            boolean rimosso = catalogo.removeIf(e -> e.getIsbn().equalsIgnoreCase(isbn));
            if (!rimosso) {
                throw new Exception("Elemento con ISBN " + isbn + " non trovato, impossibile rimuovere!");
            }
            System.out.println("Elemento con ISBN " + isbn + " rimosso correttamente.");
        } catch (Exception e) {
            System.out.println("Errore durante la rimozione: " + e.getMessage());
        }
    }

    // Metodo per cercare elementi per anno di pubblicazione
    public List<Biblioteca> cercaPerAnnoPubblicazione(int annoPubblicazione) {
        List<Biblioteca> risultati = catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == annoPubblicazione)
                .collect(Collectors.toList());
        System.out.println("Elementi trovati per anno di pubblicazione (" + annoPubblicazione + "): " + risultati);
        return risultati;
    }

    // Metodo per cercare libri per autore
    public List<Libri> cercaPerAutore(String autore) {
        List<Libri> risultati = catalogo.stream()
                .filter(e -> e instanceof Libri)
                .map(e -> (Libri) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
        System.out.println("Libri trovati per autore (" + autore + "): " + risultati);
        return risultati;
    }

    // Metodo per aggiornare un elemento dato l'ISBN
    public void aggiornaElemento(String isbn, Biblioteca nuovoElemento) {
        try {
            Biblioteca elementoDaAggiornare = cercaPerISBN(isbn);
            if (elementoDaAggiornare != null) {
                catalogo.remove(elementoDaAggiornare);
                catalogo.add(nuovoElemento);
                System.out.println("Elemento con ISBN " + isbn + " aggiornato correttamente.");
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiornamento: " + e.getMessage());
        }
    }

    // Metodo per mostrare statistiche del catalogo

    public void statisticheCatalogo() {
        try {
            long numeroLibri = catalogo.stream().filter(e -> e instanceof Libri).count();
            long numeroRiviste = catalogo.stream().filter(e -> e instanceof Riviste).count();

            // Calcolo dell'elemento con il massimo numero di pagine
            Biblioteca elementoMaxPagine = catalogo.stream()
                    .max(Comparator.comparingInt(Biblioteca::getNumeroPagine))
                    .orElse(null);

            // Calcolo della media delle pagine
            double mediaPagine = catalogo.stream()
                    .mapToInt(Biblioteca::getNumeroPagine)
                    .average()
                    .orElse(0);

            // arrotondo la media pagine come numero intero
            double mediaPagineArrotondata = Math.floor(mediaPagine);

            // Stampa delle statistiche
            System.out.println("Statistiche del catalogo:");
            System.out.println("- Numero totale di libri: " + numeroLibri);
            System.out.println("- Numero totale di riviste: " + numeroRiviste);
            if (elementoMaxPagine != null) {
                System.out.println("- Elemento con maggior numero di pagine: " + elementoMaxPagine);
            } else {
                System.out.println("- Nessun elemento trovato nel catalogo.");
            }
            System.out.println("- Media delle pagine di tutti gli elementi: " + mediaPagine);
        } catch (Exception e) {
            System.out.println("Errore durante il calcolo delle statistiche: " + e.getMessage());
        }
    }
}
