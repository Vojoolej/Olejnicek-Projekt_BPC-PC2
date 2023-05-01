import java.io.*;
import java.util.*;

public class FilmManager {
    static ArrayList<Film> filmList;

    public FilmManager() {
        filmList = new ArrayList<>();
    }
    public  int pouzeCelaCisla(Scanner input)
    {
        int cislo = 0;
        try
        {
            cislo = input.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Nastala vyjimka typu "+e.toString());
            System.out.println("zadejte prosim cele cislo ");
            input.nextLine();
            cislo = pouzeCelaCisla(input);
        }
        return cislo;
    }
    public static float pouzeCisla(Scanner input)
    {
        float cislo = 0;
        try
        {
            cislo = input.nextFloat();
        }
        catch(Exception e)
        {
            System.out.println("Nastala vyjimka typu "+e.toString());
            System.out.println("zadejte prosim cislo ");
            input.nextLine();
            cislo = pouzeCisla(input);
        }
        return cislo;
    }
    public void addFilm() {
        Scanner input = new Scanner(System.in);

            System.out.println("Vyberte typ filmu:");
            System.out.println("1. Hraný film");
            System.out.println("2. Animovaný film");
            int choice = pouzeCelaCisla(input);

        System.out.println("Zadejte název filmu:");
        String title = input.next();

        System.out.println("Zadejte jméno režiséra:");
        String director = input.next();

        System.out.println("Zadejte rok vydání:");
        int year =  pouzeCelaCisla(input);


        if (choice == 1) { // Hraný film
            ArrayList<String> actors = new ArrayList<>();

            System.out.println("Zadejte seznam herců oddělených čárkou (bez mezer):");
            String actorsString = input.next();
            String[] actorsArray = actorsString.split(",");
            for (String actor : actorsArray) {
                actors.add(actor);
            }

            System.out.println("Zadejte hodnocení diváků (1-5):");
            ArrayList<Integer> ratings = new ArrayList<>();
            int newRatings = pouzeCelaCisla(input);
            ratings.add(newRatings);


            Film movie = new Movie(title, director, year, actors, ratings);
            filmList.add(movie);
        } else if (choice == 2) { // Animovaný film
            ArrayList<String> animators = new ArrayList<>();

            System.out.println("Zadejte seznam animátorů oddělených čárkou (bez mezer):");
            String animatorsString = input.next();
            String[] animatorsArray = animatorsString.split(",");
            for (String animator : animatorsArray) {
                animators.add(animator);
            }

            System.out.println("Zadejte hodnocení diváků (1-10):");
            ArrayList<Integer> ratings = new ArrayList<>();
            int newRatings = pouzeCelaCisla(input);
            ratings.add(newRatings);

            System.out.println("Zadejte doporučený věk diváka:");
            int age = pouzeCelaCisla(input);

            Film cartoon = new Cartoon(title, director, year, animators, ratings, age);
            filmList.add(cartoon);
        }
    }
    public Film findFilmByName(String name) {
        for (Film film : filmList) {
            if (film.getName().equals(name)) {
                return film;
            }
        }
        return null;
    }


    public void updateFilm(String name) {
        Film film = findFilmByName(name);
        if (film == null) {
            System.out.println("Film nenalezen.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Zadejte nové jméno (nechte prázdné pokud nechcete provést změnu): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            film.setName(newName);
        }
        System.out.print("Zadejte nového režiséra (nechte prázdné pokud nechcete provést změnu): ");
        String newDirector = scanner.nextLine();
        if (!newDirector.isEmpty()) {
            film.setDirector(newDirector);
        }
        System.out.print("Zadejte nový rok vydání (nechte prázdné pokud nechcete provést změnu): ");
        String newYear = scanner.nextLine();
        if (!newYear.isEmpty()) {
            film.setYear(Integer.parseInt(newYear));
        }
        if (film instanceof Movie) {
            Movie movie = (Movie) film;
            System.out.print("Zadejte nové herce(oddělené ',' bez mezer, nechte prázdné pokud nechcete provést změnu): ");
            String newActors = scanner.nextLine();
            if (!newActors.isEmpty()) {
                String[] actorArray = newActors.split(",");
                List<String> actorsList = new ArrayList<>(Arrays.asList(actorArray));
                movie.setActors((ArrayList<String>) actorsList);
            }

        } else if (film instanceof Cartoon) {
            Cartoon cartoon = (Cartoon) film;
            System.out.print("Zadejte nové animátory (oddělené ',' bez mezer, nechte prázdné pokud nechcete provést změnu): ");
            String newAnimators = scanner.nextLine();
            if (!newAnimators.isEmpty()) {
                String[] animatorsArray = newAnimators.split(",");
                List<String> animatorsList = new ArrayList<>(Arrays.asList(animatorsArray));
                cartoon.setAnimators((ArrayList<String>) animatorsList);
            }

            System.out.print("Zadejte doporučený věk (nechte prázdné pokud nechcete provést změnu): ");
            String newRecommendedAge = scanner.nextLine();
            if (!newRecommendedAge.isEmpty()) {
                cartoon.setRecommendedAge(Integer.parseInt(newRecommendedAge));
            }
        }
        System.out.println("Film byl úspěšně upraven.");
    }

    public boolean deleteFilmByName(String name) {
        Film filmToDelete = findFilmByName(name);
        if (filmToDelete != null) {
            filmList.remove(filmToDelete);
            return true;
        } else {
            return false;
        }
    }

    public void addRating(String name) {
        Film film = findFilmByName(name);
        if (film == null) {
            System.out.println("Film nebyl nalezen.");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        if (film instanceof Movie) {
            Movie movie = (Movie) film;
            System.out.print("Zadej hodnocení (1-5): ");

            int newRating = pouzeCelaCisla(scanner);

            if (newRating < 1 || newRating > 5) {
                System.out.println("Hodnota neodpovídá rozpětí, zadejte správnou hodnotu.");
                return;

            }
            movie.getRatings().add(newRating);
            System.out.println("Nové hodnocení bylo úspěšně přidáno.");

        } else if (film instanceof Cartoon) {
            Cartoon cartoon = (Cartoon) film;
            System.out.print("Zadejte hodnocení(1-10): ");
            int newRating = pouzeCelaCisla(scanner);
            if (newRating < 1 || newRating > 10) {
                System.out.println("Hodnota neodpovídá rozpětí, zadejte správnou hodnotu.");
                return;
            }
            cartoon.getRatings().add(newRating);
            System.out.println("Nové hodnocení bylo úspěšně přidáno.");
        }
    }



    public void printAllFilms() {


        for (Film film : filmList) {
            System.out.println("Název: " + film.getName());
            System.out.println("Režisér: " + film.getDirector());
            System.out.println("Rok vydání: " + film.getYear());

            if (film instanceof Cartoon) {
                Cartoon cartoon = (Cartoon) film;
                System.out.println("Animátoři: " + String.join(", ", cartoon.getAnimators()));
                System.out.println("Doporučený věk: " + cartoon.getRecommendedAge());
            } else {
                Movie movie = (Movie) film;
                System.out.println("Herci: " + String.join(", ", movie.getActors()));
            }
            System.out.println();
        }
    }
    public void printFilm(String name) {

        Film film = findFilmByName(name);
        if (film == null) {
            System.out.println("Film nebyl nalezen.");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        if (film instanceof Movie){
            Movie movie = (Movie) film;

            System.out.println("Film: " + movie.getName());
            System.out.println("Režisér: " + movie.getDirector());
            System.out.println("Rok vydání: " + movie.getYear());
            System.out.println("Herci: " + String.join(", ", movie.getActors()));

            ArrayList<Integer> ratings = ((Movie) film).getRatings();
            if (!ratings.isEmpty()) {
                Collections.sort(ratings, Collections.reverseOrder());
                System.out.println("Hodnocení diváků: " + ratings);
            }
            else {
                System.out.println("Hodnocení diváků: žádné");}
        }

        else{
            Cartoon cartoon = (Cartoon) film;
            System.out.println("Film: " + cartoon.getName());
            System.out.println("Režisér: " + cartoon.getDirector());
            System.out.println("Rok vydání: " + cartoon.getYear());
            System.out.println("Animatoři: " + String.join(", ", cartoon.getAnimators()));
            System.out.println("Doporučený věk: " + cartoon.getRecommendedAge());

            ArrayList<Integer> ratings = ((Cartoon) film).getRatings();
            if (!ratings.isEmpty()) {
                Collections.sort(ratings, Collections.reverseOrder());
                System.out.println("Hodnocení diváků: " + ratings);
            } else {
                System.out.println("Hodnocení diváků: žádné");
            }
        }
        System.out.println();

    }

    public void printActors() {
        HashMap<String, ArrayList<String>> contributorToMoviesMap = new HashMap<>();


        for (Film film : filmList) {
            if (film instanceof Movie) {
                Movie movie = (Movie) film;
                for (String actor : movie.getActors()) {
                    if (contributorToMoviesMap.containsKey(actor)) {
                        contributorToMoviesMap.get(actor).add(movie.getName());
                    } else {
                        ArrayList<String> movies = new ArrayList<>();
                        movies.add(movie.getName());
                        contributorToMoviesMap.put(actor, movies);
                    }
                }
            } else if (film instanceof Cartoon) {
                Cartoon cartoon = (Cartoon) film;
                for (String animator : cartoon.getAnimators()) {
                    if (contributorToMoviesMap.containsKey(animator)) {
                        contributorToMoviesMap.get(animator).add(cartoon.getName());
                    } else {
                        ArrayList<String> movies = new ArrayList<>();
                        movies.add(cartoon.getName());
                        contributorToMoviesMap.put(animator, movies);
                    }
                }

            }
        }
            boolean isTrue=false;

        for (Map.Entry<String, ArrayList<String>> entry : contributorToMoviesMap.entrySet()) {
            String contributor = entry.getKey();
            ArrayList<String> movies = entry.getValue();
            if (movies.size() > 1) {
                System.out.print(contributor + " se účastnil v: ");
                for (String movie : movies) {
                    System.out.print(movie + ", ");
                    isTrue=true;
                }
                System.out.println();
            }

        }
        if(isTrue==false) {System.out.println("Žádný herec se nenachází ve více filmech");
            System.out.println();                      }
        else System.out.println();
    }
    public void printMoviesWithActor(String name) {
        boolean found = false;
        for (Film film : filmList) {
            if (film instanceof Movie) {
                Movie movie = (Movie) film;
                if (movie.getActors().contains(name)) {
                    System.out.println(movie.getName());
                    found=true;
                }
            } else if (film instanceof Cartoon) {
                Cartoon cartoon = (Cartoon) film;
                if (cartoon.getAnimators().contains(name)) {
                    System.out.println(cartoon.getName());
                    found=true;
                }
            }
        }
        if(!found)System.out.println("Herec/Animátor " + name + " nebyl nalezen v žádných filmech.");
        System.out.println();
    }

    public void saveFilmToFile(String name) {
        Film film = findFilmByName(name);
        if (film == null) {
            System.out.println("Film nebyl nalezen.");
            return;
        }

        try {

            FileWriter writer;
            writer = new FileWriter(name + ".txt");

            // zapíšeme informace o filmu
            writer.write("Název: " + film.getName() + "\n");
            writer.write("Režisér: " + film.getDirector() + "\n");
            writer.write("Rok vydání: " + film.getYear() + "\n");

            if (film instanceof Movie) {
                Movie movie = (Movie) film;
                writer.write("Herci: " + movie.getActors() + "\n");
                writer.write("Hodnocení diváků: " + movie.getRatings() + "\n");
            } else if (film instanceof Cartoon) {
                Cartoon cartoon = (Cartoon) film;
                writer.write("Animátoři: " + cartoon.getAnimators() + "\n");
                writer.write("Hodnocení diváků: " + cartoon.getRatings() + "\n");
                writer.write("Doporučený věk: " + cartoon.getRecommendedAge() + "\n");
            }

            // zavřeme soubor
            writer.close();

            System.out.println("Film byl uložen do: " + name + ".txt");
        } catch (IOException e) {
            System.out.println("Film nebylo možné uložit.");
            e.printStackTrace();
        }
    }

    public void  loadFilmsFromFile(String filename) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            String name = "";
            String director = "";
            int recommendedAge=0;
            int year = 0;
            ArrayList<String> actors = new ArrayList<>();
            ArrayList<Integer> ratings = new ArrayList<>();
            ArrayList<String > animators = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("Název:")) {
                    name = line.substring(7).trim();
                } else if (line.startsWith("Režisér:")) {
                    director = line.substring(9).trim();
                } else if (line.startsWith("Rok vydání:")) {
                    year = Integer.parseInt(line.substring(12).trim());
                } else if (line.startsWith("Herci:")) {
                    String actorsStr = line.substring(7).trim();
                    String[] actorsArray = actorsStr.substring(1, actorsStr.length() - 1).split(", ");
                    actors = new ArrayList<>();
                    for (String actor : actorsArray) {
                        actors.add(actor);
                    }
                }
                else if (line.startsWith("Animátoři:")) {
                    String animatorsStr = line.substring(11).trim();
                    String[] animatorsArray = animatorsStr.substring(1, animatorsStr.length() - 1).split(", ");
                    animators = new ArrayList<>();
                    for (String animator : animatorsArray) {
                        animators.add(animator);
                    }
                }else if (line.startsWith("Hodnocení diváků:")) {
                    String ratingStr = line.substring(18).trim();
                    String[] ratingValues = ratingStr.substring(1, ratingStr.length() - 1).split(", ");
                    ratings = new ArrayList<>();
                    for (String ratingValue : ratingValues) {
                        ratings.add(Integer.parseInt(ratingValue));
                    }
                }
                else if (line.startsWith("Doporučený věk:")) {
                    recommendedAge = Integer.parseInt(line.substring(15).trim());
                }

                if (!line.equals("") && !scanner.hasNextLine()) {
                    if(recommendedAge==0){
                    Film movie = new Movie(name, director, year, actors, ratings);
                    filmList.add(movie);}

                    else {Film cartoon = new Cartoon(name, director, year, animators, ratings,recommendedAge);
                        filmList.add(cartoon);}
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Soubor nebyl nalezen: " + filename);
        }


    }

}
