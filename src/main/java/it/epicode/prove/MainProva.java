package it.epicode.prove;

import it.epicode.classi.Archivio;
import it.epicode.classi.Libri;
import it.epicode.classi.Riviste;
import it.epicode.interfacce.Periodicita;

import java.util.List;

public class MainProva {
    public static void main(String[] args) {
        // Creazione liste di libri e riviste
        List<Libri> listaLibri = List.of(
                new Libri("12345", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy"),
                new Libri("67890", "1984", 1949, 328, "George Orwell", "Distopia"),
                new Libri("54321", "Orgoglio e Pregiudizio", 1813, 432, "Jane Austen", "Romantico")
        );

        List<Riviste> listaRiviste = List.of(
                new Riviste("11111", "National Geographic", 2023, 150, Periodicita.MENSILE),
                new Riviste("22222", "Time Magazine", 2023, 120, Periodicita.SETTIMANALE),
                new Riviste("33333", "Science Journal", 2023, 200, Periodicita.SEMESTRALE)
        );

        // Creazione dell'Archivio
        Archivio archivio = new Archivio();

        // Aggiunta dei libri al catalogo
        System.out.println("\nAggiunta dei libri al catalogo...");
        listaLibri.forEach(archivio::aggiungiElemento);

        // Aggiunta delle riviste al catalogo
        System.out.println("\nAggiunta delle riviste al catalogo...");
        listaRiviste.forEach(archivio::aggiungiElemento);

        // Stampa delle statistiche del catalogo
        System.out.println("\nStatistiche del catalogo:");
        archivio.statisticheCatalogo();

        // Ricerca di un libro per ISBN
        System.out.println("\nRicerca di un libro per ISBN (ISBN: 12345):");
        listaLibri.forEach(libro -> {
            if (libro.getIsbn().equals("12345")) {
                System.out.println("Libro trovato: " + libro);
            }
        });

        // Rimozione di un elemento dal catalogo
        System.out.println("\nRimozione di un elemento dal catalogo (ISBN: 67890):");
        archivio.rimuoviPerISBN("67890");

        // Visualizzazione del catalogo aggiornato
        System.out.println("\nCatalogo aggiornato:");
        archivio.statisticheCatalogo();

        // Ricerca di libri per autore
        System.out.println("\nRicerca di libri per autore (Autore: 'J.R.R. Tolkien'):");
        listaLibri.forEach(libro -> {
            if (libro.getAutore().equalsIgnoreCase("J.R.R. Tolkien")) {
                System.out.println("Libro trovato: " + libro);
            }
        });

        // Ricerca per anno di pubblicazione
        System.out.println("\nRicerca di elementi per anno di pubblicazione (Anno: 2023):");
        listaRiviste.forEach(rivista -> {
            if (rivista.getAnnoPubblicazione() == 2023) {
                System.out.println("Rivista trovata: " + rivista);
            }
        });

        // Aggiornamento di un libro
        System.out.println("\nAggiornamento di un libro (ISBN: 12345):");
        Libri nuovoLibro = new Libri("12345", "Il Ritorno del Re", 1955, 1100, "J.R.R. Tolkien", "Fantasy");
        archivio.aggiornaElemento("12345", nuovoLibro);

        // Catalogo finale dopo l'aggiornamento
        System.out.println("\nCatalogo finale dopo l'aggiornamento:");
        archivio.statisticheCatalogo();
    }
}


