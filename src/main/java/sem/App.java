package sem;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class App {

    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }
        // Get list of cities in 'Arg'
        List<City> citiesInArg = a.getCitiesInCountry("Arg");
        // Display results
        a.displayCities(citiesInArg);
        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Retrieve a list of cities in a specific country.
     * @param countryCode The country code to search for cities.
     * @return A list of cities in the specified country.
     */
    public List<City> getCitiesInCountry(String countryCode) {
        List<City> cities = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, CountryCode, Population " +
                            "FROM city " +
                            "WHERE CountryCode = '" + countryCode + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Iterate through the result set and create City objects
            while (rset.next()) {
                City city = new City();
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.Population = rset.getInt("Population");
                cities.add(city);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities in country: " + countryCode);
        }
        return cities;
    }

    /**
     * Display a list of cities.
     * @param cities The list of cities to display.
     */
    public void displayCities(List<City> cities) {
        if (!cities.isEmpty()) {
            for (City city : cities) {
                System.out.println("City Name: " + city.Name);
                System.out.println("Country Code: " + city.CountryCode);
                System.out.println("Population: " + city.Population);
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No cities found.");
        }
    }
}