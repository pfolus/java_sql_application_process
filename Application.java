import java.util.Scanner;

public class Application {

    public enum Choices {
        HEADER("Select option: "),
        OPTION1("1. Delete applicants with @mauriseu.net email address"),
        OPTION2("2. Show mentors fullnames"),
        OPTION3("3. Show Miskolc mentors nicknames"),
        OPTION4("4. Show fullname and phone number of an applicant, which name is 'Carol'"),
        OPTION5("5. Show fullname and phone number of an applicant, which email is like @adipiscingenimmi.edu"),
        OPTION6("6. Add Markus Schaffarzyk to DB"),
        OPTION7("7. Edit phone number of applicant Jenima Foreman "),
        OPTION0("0. Exit ");

        private final String message;

        Choices(String message) {
            this.message = message;
        }

        public String message() {
            return this.message;
        }

        public String info() {
            return "Option " + this.ordinal() + " chosen";
        }
    }

    public static void showMenu(){
        View.newLine();
        for (Choices choice: Choices.values()){
            View.print(choice.message());
        }
        View.newLine();
    }

    public static void main(String[] args) {
        String choice;
        String query1;
        String query2;

        Scanner scanner = new Scanner(System.in);
        do {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    View.print(Choices.OPTION1.info());
                    SQL.DeleteApplicantsByEmail();
                    break;
                case "2":
                    View.print(Choices.OPTION2.info());
                    SQL.selectMentorsNames();
                    break;
                case "3":
                    View.print(Choices.OPTION3.info());
                    SQL.selectMentorsNicknames();
                    break;
                case "4":
                    View.print(Choices.OPTION4.info());
                    query1 = "SELECT (first_name || ' ' || last_name) AS full_name, phone_number FROM applicants WHERE first_name = 'Carol';";
                    SQL.selectFullnameAndPhone(query1);
                    break;
                case "5":
                    View.print(Choices.OPTION5.info());
                    query2 = "SELECT (first_name || ' ' || last_name) AS full_name, phone_number FROM applicants WHERE email LIKE '%@adipiscingenimmi.edu';";
                    SQL.selectFullnameAndPhone(query2);
                    break;
                case "6":
                    View.print(Choices.OPTION6.info());
                    SQL.AddApplicantAndShowHisDetails();
                    break;
                case "7":
                    View.print(Choices.OPTION7.info());
                    SQL.UpdateAndCheckJemima();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    View.print("Wrong option chosen, try again!");
            }
        } while (!choice.equals("0"));
    }
}
