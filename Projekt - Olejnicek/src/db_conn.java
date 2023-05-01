
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Arrays;

    public class db_conn extends FilmManager{
        private static final String url = "jdbc:sqlite:C:/Users/vojoo_3uc6r7g/OneDrive/Plocha/vejška ukoly/programko/Projekt - Olejnicek/db/movie_db.db";
         static Connection connection = null;
        public static void connect() {

            try {
                connection = DriverManager.getConnection(url);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Připojeno do SQL databaze.");
        }
        public static Connection getConn(){
            return connection;
        }

        public static void disconnectConn(){
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Odpojeno z SQL databaze.");
        }
                public static void deleteMovie(String name) throws SQLException {
                    String sql =" DELETE FROM movies WHERE name = '"+name+"'";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();

                }
                  public static void saveMovie() throws SQLException {
                for(Film film : filmList){
                    if(film instanceof Movie){
                        Movie movie = (Movie) film;
                        String sql = "INSERT OR REPLACE INTO  movies (name, director, year, actors, ratings) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, movie.getName());
                        statement.setString(2, movie.getDirector());
                        statement.setInt(3, movie.getYear());
                        statement.setObject(4, Arrays.toString(movie.getActors().toArray()));
                        statement.setObject(5, Arrays.toString(movie.getRatings().toArray()));
                        statement.executeUpdate();}}

        }
        public static void saveCartoon() throws SQLException {
            for(Film film : filmList){
                if(film instanceof Cartoon){
                    Cartoon cartoon = (Cartoon) film;
                    String sql = "INSERT OR REPLACE INTO  cartoons (name, director, year, animators, ratings, recommended_age) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, cartoon.getName());
                    statement.setString(2, cartoon.getDirector());
                    statement.setInt(3, cartoon.getYear());
                    statement.setObject(4, Arrays.toString(cartoon.getAnimators().toArray()));
                    statement.setObject(5, Arrays.toString(cartoon.getRatings().toArray()));
                    statement.setInt(6, cartoon.getRecommendedAge());
                    statement.executeUpdate();}}

        }

        public static void loadMovies() throws SQLException {
            ArrayList<Movie> movies = new ArrayList<>();
            String sql = "SELECT * FROM movies";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String director = resultSet.getString("director");
                int year = resultSet.getInt("year");
               //ArrayList<String> actors = (ArrayList<String>) resultSet.getArray("actors").getArray();

                String actorsStr = resultSet.getString("actors");
                String[] actorsArray = resultSet.getString("actors").substring(1, actorsStr.length() - 1).split(", ");
                ArrayList<String> actors = new ArrayList<>();
                for (String actor : actorsArray) {
                    actors.add(actor);
                }

                String ratingStr = resultSet.getString("ratings");
                String[] ratingValues = resultSet.getString("ratings").substring(1, ratingStr.length() - 1).split(", ");
                ArrayList<Integer> ratings= new ArrayList<>();
                for (String ratingValue : ratingValues) {
                    ratings.add(Integer.parseInt(ratingValue));
                }

                Film movie = new Movie(name, director, year, actors, ratings);
                filmList.add(movie);
            }


        }

        public static void loadCartoons() throws SQLException {
            ArrayList<Cartoon> cartoons = new ArrayList<>();
            String sql = "SELECT * FROM cartoons";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String director = resultSet.getString("director");
                int year = resultSet.getInt("year");

                String animatorsStr = resultSet.getString("animators");
                String[] animatorsArray = resultSet.getString("animators").substring(1, animatorsStr.length() - 1).split(", ");
                ArrayList<String> animators = new ArrayList<>();
                for (String animator : animatorsArray) {
                    animators.add(animator);
                }


                String ratingStr = resultSet.getString("ratings");
                String[] ratingValues = resultSet.getString("ratings").substring(1, ratingStr.length() - 1).split(", ");
                ArrayList<Integer> ratings= new ArrayList<>();
                for (String ratingValue : ratingValues) {
                    ratings.add(Integer.parseInt(ratingValue));

                int recommendedAge = resultSet.getInt("recommended_age");

                Film cartoon = new Cartoon(name, director, year, animators, ratings, recommendedAge);
                filmList.add(cartoon);
             }


            }


        }

    }

