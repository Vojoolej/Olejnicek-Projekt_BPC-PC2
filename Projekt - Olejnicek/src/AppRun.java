
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class AppRun {


    private static Scanner scanner = new Scanner(System.in);
    public static int pouzeCelaCisla(Scanner scanner)
    {
        int cislo = 0;
        try
        {
            cislo = scanner.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Nastala vyjimka typu "+e.toString());
            System.out.println("zadejte prosim cele cislo ");
            scanner.nextLine();
            cislo = pouzeCelaCisla(scanner);
        }
        return cislo;
    }
    public static float pouzeCisla(Scanner scanner)
    {
        float cislo = 0;
        try
        {
            cislo = scanner.nextFloat();
        }
        catch(Exception e)
        {
            System.out.println("Nastala vyjimka typu "+e.toString());
            System.out.println("zadejte prosim cislo ");
            scanner.nextLine();
            cislo = pouzeCisla(scanner);
        }
        return cislo;
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        FilmManager FilmManager= new FilmManager();
        boolean quit = false;
        db_conn.connect();
        db_conn.loadMovies();
        db_conn.loadCartoons();
        db_conn.disconnectConn();

        while (!quit) {
            System.out.println("Vyberte jednu z následujících možností:");
            System.out.println("1 - Přidat nový film");
            System.out.println("2 - Upravit film");
            System.out.println("3 - Přidat hodnocení");
            System.out.println("4 - Smazat film");
            System.out.println("5 - Vypsat všechny filmy");
            System.out.println("6 - Vyhledání filmu");
            System.out.println("7 - Výpis herců/animátorů");
            System.out.println("8 - Výpis filmů s herci/animátory");
            System.out.println("9 - Uložení filmu do souboru");
            System.out.println("10 - Načtení filmu ze souboru");
            System.out.println("11 - Konec programu");
             int choice = pouzeCelaCisla(scanner);
            scanner.nextLine();
        




            switch (choice) {
                case 1:
                    FilmManager.addFilm();
                    break;
                case 2:
                    System.out.println("Zadej název Filmu:");
                    FilmManager.updateFilm(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Zadej název Filmu:");
                    FilmManager.addRating(scanner.nextLine());
                    break;
                case 4:
                    System.out.println("Zadej název Filmu:");
                    if(FilmManager.deleteFilmByName(scanner.nextLine())==true)System.out.println("Film byl úspěšně smazán");
                    else System.out.println("Film nebyl nalezen");
                    break;
                case 5:
                    FilmManager.printAllFilms();
                    break;
                case 6:
                    System.out.println("Zadej název Filmu:");
                    FilmManager.printFilm(scanner.nextLine());
                    break;
                case 7:
                    FilmManager.printActors();
                    break;
                case 8:
                    System.out.println("Zadej jmého herce/animátora:");
                    FilmManager.printMoviesWithActor(scanner.nextLine());
                    break;
                case 9:
                    System.out.println("Zadej název filmu pro uložení do souboru:");
                    FilmManager.saveFilmToFile(scanner.nextLine());
                    break;
                case 10:
                    System.out.println("Zadej název filmu pro načtení ze souboru:");
                    String filmName=scanner.nextLine();
                     FilmManager.loadFilmsFromFile(filmName+".txt");
                    break;
                case 11:
                    db_conn.connect();
                    db_conn.saveMovie();
                    db_conn.saveCartoon();
                    db_conn.disconnectConn();
                    quit = true;
                    break;
                case 12:
                    db_conn.connect();
                    db_conn.deleteMovie(scanner.nextLine());
                    db_conn.disconnectConn();
                    break;
                
                default:System.out.println("Zadávejte pouze čísla od 1 do 11!");
                break;
            }
        }

    }
}
