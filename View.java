public class View {
    public static final String TABLE1 =
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

    public static final String TABLE2 =
        "CREATE TABLE `applicants` (" +
        "`id` INTEGER NOT NULL UNIQUE," +
        "`first_name`	TEXT," +
        "`last_name`	TEXT," +
        "`phone_number`	INTEGER," +
        "`email`	TEXT," +
        "`application_code`	INTEGER UNIQUE," +
        "PRIMARY KEY(`id`))";

    public static void print(String text){
        System.out.println(text);
    }

    public static void printSameLine(String text){
        System.out.print(text);
    }

    public static void newLine(){
        System.out.println();
    }
}
