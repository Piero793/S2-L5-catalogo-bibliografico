package it.epicode.main;

import it.epicode.classi.*;
import it.epicode.helper.Periodicita;
import it.epicode.colori.Colori;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        logger.info(Colori.VERDE.colora("Benvenuto nel catalogo della biblioteca!"));

        while (continua) {
            try {
                System.out.println(Colori.BLU.colora("\nCosa vuoi fare? Seleziona un'opzione:"));
                System.out.println(Colori.BLU.colora("---------------------------------------"));
                System.out.println(Colori.GIALLO.colora("1. Aggiungi elemento"));
                System.out.println(Colori.GIALLO.colora("2. Cerca elemento per ISBN"));
                System.out.println(Colori.GIALLO.colora("3. Rimuovi elemento per ISBN"));
                System.out.println(Colori.GIALLO.colora("4. Cerca elementi per anno di pubblicazione"));
                System.out.println(Colori.GIALLO.colora("5. Cerca libri per autore"));
                System.out.println(Colori.GIALLO.colora("6. Aggiorna elemento"));
                System.out.println(Colori.GIALLO.colora("7. Mostra statistiche catalogo"));
                System.out.println(Colori.GIALLO.colora("8. Esci"));

                int scelta = Integer.parseInt(scanner.nextLine());

                switch (scelta) {
                    case 1:
                        System.out.println(Colori.BLU.colora("Stai aggiungendo un elemento. È un libro o una rivista? premi L per libro o R per rivista"));
                        String tipo = scanner.nextLine().toUpperCase();

                        System.out.print("Inserisci ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Inserisci titolo: ");
                        String titolo = scanner.nextLine();

                        System.out.print("Inserisci anno di pubblicazione: ");
                        int annoPubblicazione = Integer.parseInt(scanner.nextLine());

                        System.out.print("Inserisci numero di pagine: ");
                        int numeroPagine = Integer.parseInt(scanner.nextLine());

                        if ("L".equals(tipo)) {
                            System.out.print("Inserisci autore: ");
                            String autore = scanner.nextLine();

                            System.out.print("Inserisci genere: ");
                            String genere = scanner.nextLine();

                            Libri libro = new Libri(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
                            archivio.aggiungiElemento(libro);
                        } else if ("R".equals(tipo)) {
                            System.out.print("Inserisci periodicità (MENSILE/SETTIMANALE/SEMESTRALE): ");
                            String periodicitaInput = scanner.nextLine().toUpperCase();

                            Periodicita periodicita;
                            try {
                                periodicita = Periodicita.valueOf(periodicitaInput);
                            } catch (IllegalArgumentException e) {
                                logger.error(Colori.ROSSO.colora("Errore: Periodicità non valida. Usa MENSILE, SETTIMANALE o SEMESTRALE."));
                                break;
                            }

                            Riviste rivista = new Riviste(isbn, titolo, annoPubblicazione, numeroPagine, periodicita);
                            archivio.aggiungiElemento(rivista);
                        } else {
                            logger.error(Colori.ROSSO.colora("Tipo non valido."));
                        }
                        break;

                    case 2:
                        System.out.print("Inserisci ISBN per cercare l'elemento: ");
                        String isbnRicerca = scanner.nextLine();
                        archivio.cercaPerISBN(isbnRicerca);
                        break;

                    case 3:
                        System.out.print("Inserisci ISBN per rimuovere l'elemento: ");
                        String isbnRimuovi = scanner.nextLine();
                        archivio.rimuoviPerISBN(isbnRimuovi);
                        break;

                    case 4:
                        System.out.print("Inserisci anno di pubblicazione per cercare gli elementi: ");
                        int annoRicerca = Integer.parseInt(scanner.nextLine());
                        archivio.cercaPerAnnoPubblicazione(annoRicerca);
                        break;

                    case 5:
                        System.out.print("Inserisci autore per cercare i libri: ");
                        String autoreRicerca = scanner.nextLine();
                        archivio.cercaPerAutore(autoreRicerca);
                        break;

                    case 6:
                        System.out.print("Inserisci ISBN dell'elemento da aggiornare: ");
                        String isbnAggiorna = scanner.nextLine();

                        System.out.print("Inserisci nuovo titolo: ");
                        String nuovoTitolo = scanner.nextLine();

                        System.out.print("Inserisci nuovo anno di pubblicazione: ");
                        int nuovoAnnoPubblicazione = Integer.parseInt(scanner.nextLine());

                        System.out.print("Inserisci nuovo numero di pagine: ");
                        int nuovoNumeroPagine = Integer.parseInt(scanner.nextLine());

                        Biblioteca nuovoElemento = new Biblioteca(isbnAggiorna, nuovoTitolo, nuovoAnnoPubblicazione, nuovoNumeroPagine);
                        archivio.aggiornaElemento(isbnAggiorna, nuovoElemento);
                        break;

                    case 7:
                        archivio.statisticheCatalogo();
                        break;

                    case 8:
                        logger.info(Colori.VERDE.colora("Uscita dal programma. Arrivederci!"));
                        continua = false;
                        break;

                    default:
                        logger.error(Colori.ROSSO.colora("Scelta non valida, riprova."));
                }
            } catch (Exception e) {
                logger.error(Colori.ROSSO.colora("Errore: {}"), e.getMessage(), e);
            }
        }

        scanner.close();
    }
}

