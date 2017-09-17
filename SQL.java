import java.sql.*;
import java.util.ArrayList;

public class SQL {

    public static void createTables() {

        String table1 =
           "CREATE TABLE `mentors` (" +
           "`id` INTEGER NOT NULL UNIQUE," +
           "`first_name`	TEXT," +
           "`last_name`	TEXT," +
           "`nick_name`	TEXT," +
           "`phone_number`	INTEGER," +
           "`email`	TEXT," +
           "`city`	TEXT," +
           "`favourite_number`	INTEGER," +
           "PRIMARY KEY(`id`))";

       String table2 =
          "CREATE TABLE `applicants` (" +
          "`id` INTEGER NOT NULL UNIQUE," +
          "`first_name`	TEXT," +
          "`last_name`	TEXT," +
          "`phone_number`	INTEGER," +
          "`email`	TEXT," +
          "`application_code`	INTEGER UNIQUE," +
          "PRIMARY KEY(`id`))";

        Connection c = null;
        Statement stmt = null;

        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           stmt.executeUpdate(table1);
           stmt.executeUpdate(table2);
           stmt.close();

           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void insertRecordsIntoTables() {
        Connection c = null;
        Statement stmt = null;
        ArrayList<String> applicants;
        ArrayList<String> mentors;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            applicants = CSVManager.loadLines("applicants.csv");
            mentors = CSVManager.loadLines("mentors.csv");
            for (String applicantInfo : applicants) {
                String sql = "INSERT INTO `applicants` (id,first_name,last_name,phone_number,email,application_code)" +
                            "VALUES (" + applicantInfo + ");";
                stmt.executeUpdate(sql);
            }
            for (String mentorInfo : mentors){
                String sql = "INSERT INTO `mentors` (id, first_name, last_name, nick_name, phone_number, email, city, favourite_number)" +
                             " VALUES (" + mentorInfo + ");";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.commit();
            c.close();
          } catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
          }
          System.out.println("Records created successfully");
        }

    public static void selectMentorsNames() {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           ResultSet rs = stmt.executeQuery( "SELECT first_name, last_name FROM mentors;" );

           while ( rs.next() ) {
              String firstName = rs.getString("first_name");
              String lastName = rs.getString("last_name");

              View.printSameLine("Name: " + firstName);
              View.printSameLine(" | " + lastName);
              System.out.println();
           }
           rs.close();
           stmt.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void selectMentorsNicknames() {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           ResultSet rs = stmt.executeQuery( "SELECT nick_name FROM mentors WHERE city = 'Miskolc';");

           while ( rs.next() ) {
              String nickName = rs.getString("nick_name");

              View.printSameLine("nick_name: " + nickName);
              System.out.println();
           }
           rs.close();
           stmt.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void selectFullnameAndPhone(String query) {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           ResultSet rs = stmt.executeQuery(query);
           while ( rs.next() ) {
              String fullName = rs.getString("full_name");
              String phoneNumber = rs.getString("phone_number");

              View.printSameLine("full_name: " + fullName);
              View.printSameLine(" | phone_number: " + phoneNumber);
              System.out.println();
           }
           rs.close();
           stmt.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void AddApplicantAndShowHisDetails() {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           c.setAutoCommit(true);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();

           try {
               String sql = "INSERT INTO `applicants` (first_name,last_name,phone_number,email,application_code)" +
                           "VALUES ('Markus', 'Schaffarzyk', '003620/725-2666', 'djnovus@groovecoverage.com', 54823);";
               stmt.executeUpdate(sql);
           } catch (Exception e) {
               View.print("User already in base");
           }

           ResultSet rs = stmt.executeQuery("SELECT * FROM applicants WHERE application_code = 54823;");
           while ( rs.next() ) {
              int id = rs.getInt("id");
              String firstName = rs.getString("first_name");
              String lastName = rs.getString("last_name");
              String phoneNumber = rs.getString("phone_number");
              String email = rs.getString("email");
              int applicationCode = rs.getInt("application_code");

              View.print("id: " + id);
              View.print("first name: " + firstName);
              View.print("last name: " + lastName);
              View.print("phone number: " + phoneNumber);
              View.print("application code: " + applicationCode);
              View.newLine();

            }
           rs.close();
           stmt.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void UpdateAndCheckJemima() {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           String sql = "UPDATE applicants SET phone_number = '003670/223-7459'" +
                       "WHERE first_name = 'Jemima' AND last_name = 'Foreman';";
           stmt.executeUpdate(sql);
           View.print("Record updated succesfully!");
           c.commit();

           ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, phone_number FROM applicants" +
                                            " WHERE first_name = 'Jemima' AND last_name = 'Foreman';");
           while ( rs.next() ) {
              String firstName = rs.getString("first_name");
              String lastName = rs.getString("last_name");
              String phoneNumber = rs.getString("phone_number");

              View.print("first name: " + firstName);
              View.print("last name: " + lastName);
              View.print("phone number: " + phoneNumber);
              View.newLine();

            }
           rs.close();
           stmt.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void DeleteApplicantsByEmail() {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           String sql = "DELETE FROM applicants WHERE email LIKE '%@mauriseu.net';";
           stmt.executeUpdate(sql);
           c.commit();

           stmt.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void main( String args[] ) {
        createTables();
        insertRecordsIntoTables();
    }
}