

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu implements java.io.Serializable {


    private static final long serialVersionUID = 1234567L;
    Survey survey;
    Test test;

    public static void DisplayInitialMainMenu() {
        System.out.println("1.Survey");
        System.out.println("2.Test");
    }


    public static void DisplayMainMenu() {
        System.out.println("1.Create a new survey");
        System.out.println("2.Display an existing survey");
        System.out.println("3.Load an existing survey");
        System.out.println("4.Save the current survey");
        System.out.println("5.Take the current survey");
        System.out.println("6.Modifying the current survey");
        System.out.println("7.Tabulate a survey");
        System.out.println("8.Return to the previous menu");

    }

    public static void DisplayMainMenu2() {
        System.out.println("1.Add a new T/F question");
        System.out.println("2.Add a new multiple-choice question");
        System.out.println("3.Add a new short answer question");
        System.out.println("4.Add a new essay question");
        System.out.println("5.Add a new date question");
        System.out.println("6.Add a new matching question");
        System.out.println("7.Return to previous menu");

    }

    public static void DisplayMainTestMenu() {
        System.out.println("1.Create a new Test");
        System.out.println("2.Display an existing Test without correct answers");
        System.out.println("3.Display an existing Test with correct answers");
        System.out.println("4.Load an existing Test");
        System.out.println("5.Save the current Test");
        System.out.println("6.Take the current Test");
        System.out.println("7.Modify the current Test");
        System.out.println("8.Tabulate a Test");
        System.out.println("9.Grade a Test");
        System.out.println("10.Return to the previous menu");

    }

    public void MainMenu() throws ClassNotFoundException, IOException {
        Scanner Input1 = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a Number: ");
        String UserChoice1 = Input1.nextLine();

        if (!Validation.isNumeric(UserChoice1)) {
            System.out.println("Please enter a number");
            Menu.DisplayMainMenu();
            MainMenu();
        } else if (!Validation.isValidInt(Integer.parseInt(UserChoice1))) {
            System.out.println("Please enter a valid number");
            Menu.DisplayMainMenu();
            MainMenu();
        } else if (Integer.parseInt(UserChoice1) == 1) {
            survey = new Survey();
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);

        } else if (Integer.parseInt(UserChoice1) == 2) {
            if (!(survey == null)) {
                ArrayList<Question> array = survey.getQuestions();
                int num = array.size();
                for (int i = 0; i < num; i++) {
                    System.out.println((i + 1) + ")");
                    System.out.print(array.get(i));
                    System.out.println("\n");
                }
            } else {
                System.out.println("You must have a survey loaded in order to display it.");
            }
            DisplayMainMenu();
            MainMenu();
        } else if (Integer.parseInt(UserChoice1) == 3) {
            FileUtils serializer = new FileUtils();
            survey = serializer.load();
            DisplayMainMenu();
            MainMenu();
        } else if (Integer.parseInt(UserChoice1) == 4) {
            if (!(survey == null)) {
                FileUtils serializer = new FileUtils();
                serializer.save(survey);
            } else {
                System.out.println("You must have a survey loaded in order to save it.");
            }
            DisplayMainMenu();
            MainMenu();
        } else if (Integer.parseInt(UserChoice1) == 5) {
            if (!(survey == null)) {
                survey.take(survey);
            } else {
                System.out.println("You must have a survey loaded in order to take it.");
            }
            DisplayMainMenu();
            MainMenu();

        } else if (Integer.parseInt(UserChoice1) == 6) {
            if (!(survey == null)) {
                survey.modify(survey);
            } else {
                System.out.println("You must have a survey loaded in order to modify it.");
            }
            DisplayMainMenu();
            MainMenu();


        } else if (Integer.parseInt(UserChoice1) == 7) {
            if (!(survey == null)) {
                survey.tabulate(survey);
            } else {
                System.out.println("You must have a survey loaded in order to tabulate it.");
            }
            DisplayMainMenu();
            MainMenu();

        } else if (Integer.parseInt(UserChoice1) == 8) {
            Menu.DisplayInitialMainMenu();
            UserChoice();
        }
    }

    public void MainTestMenu() throws IOException, ClassNotFoundException {
        Scanner Input1 = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a Number: ");
        String UserChoice1 = Input1.nextLine();

        if (!Validation.isNumeric(UserChoice1)) {
            System.out.println("Please enter a number");
            Menu.DisplayMainTestMenu();
            MainTestMenu();
        } else if (!Validation.isValidIntTest(Integer.parseInt(UserChoice1))) {
            System.out.println("Please enter a valid number");
            Menu.DisplayMainTestMenu();
            MainTestMenu();
        } else if (Integer.parseInt(UserChoice1) == 1) {
            test = new Test();
            Menu.DisplayMainMenu2();
            TestOptions(test);
        }  else if (Integer.parseInt(UserChoice1) == 2){
            if (!(test == null)) {
                ArrayList<Question> array = test.getQuestions();
                int num = array.size();
                for (int i = 0; i < num; i++) {
                    System.out.println((i + 1) + ")");
                    System.out.print(array.get(i));
                    System.out.println("\n");
                }
            } else {
                System.out.println("You must have a test loaded in order to display it.");
            }
            DisplayMainTestMenu();
            MainTestMenu();
        }  else if (Integer.parseInt(UserChoice1) == 3){
            if (!(test == null)) {
                ArrayList<Question> array = test.getQuestions();
                int num = array.size();
                for (int i = 0; i < num; i++) {
                    System.out.println((i + 1) + ")");
                    System.out.print(array.get(i) + "\n");
                    if (array.get(i) instanceof TF || array.get(i) instanceof ShortAnswer || array.get(i) instanceof Matching){

                        System.out.println("Correct Answer is: " + Arrays.toString(array.get(i).getCorrAnswer()));
                        System.out.println("\n");
                    } else if  (array.get(i) instanceof MC){
                        System.out.println("Correct Answer is: " + Arrays.toString(array.get(i).getCorrAnswers()));
                        System.out.println("\n");
                    }

                }
            } else {
                System.out.println("You must have a test loaded in order to display it.");
            }
            DisplayMainTestMenu();
            MainTestMenu();
        }
        else if (Integer.parseInt(UserChoice1) == 4) {
            FileUtils serializer = new FileUtils();
            test = serializer.loadTest();
            DisplayMainTestMenu();
            MainTestMenu();
        }

        else if (Integer.parseInt(UserChoice1) == 5){
            if (!(test == null)) {
                FileUtils serializer = new FileUtils();
                serializer.saveTest(test);
            } else {
                System.out.println("You must have a test loaded in order to save it.");
            }
            DisplayMainTestMenu();
            MainTestMenu();
        }
        else if (Integer.parseInt(UserChoice1) == 6) {
            if (!(test == null)) {
                test.take(test);
            } else {
                System.out.println("You must have a test loaded in order to take it.");
            }
            DisplayMainTestMenu();
            MainTestMenu();

        }
        else if (Integer.parseInt(UserChoice1) == 7) {
            if (!(test == null)) {
                test.modify(test);
            } else {
                System.out.println("You must have a test loaded in order to modify it.");
            }
            DisplayMainTestMenu();
            MainTestMenu();
        }
        else if (Integer.parseInt(UserChoice1) == 8) {
            if (!(test == null)) {
                test.tabulate(test);
            } else {
                System.out.println("You must have a test loaded in order to tabulate it.");
            }
            DisplayMainTestMenu();
            MainTestMenu();
        }
        else if (Integer.parseInt(UserChoice1) == 9) {
            System.out.println(Test.grade());
            DisplayMainTestMenu();
            MainTestMenu();

        }
        else if (Integer.parseInt(UserChoice1) == 10) {
            Menu.DisplayInitialMainMenu();
            UserChoice();
        }

    }


    public void UserChoice() throws IOException, ClassNotFoundException {
        Scanner Input1 = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a Number: ");
        String UserChoice1 = Input1.nextLine();
        if (!Validation.isNumeric(UserChoice1)) {
            System.out.println("Please enter a number");
            Menu.DisplayInitialMainMenu();
            UserChoice();
        } else if (!Validation.isValidInt(Integer.parseInt(UserChoice1))) {
            System.out.println("Please enter a valid number");
            Menu.DisplayInitialMainMenu();
            UserChoice();
        } else if (Integer.parseInt(UserChoice1) == 1) {
            Menu.DisplayMainMenu();
            MainMenu();

        } else if (Integer.parseInt(UserChoice1) == 2) {
            Menu.DisplayMainTestMenu();
            MainTestMenu();

        } else if (!(Integer.parseInt(UserChoice1) == 1) || !(Integer.parseInt(UserChoice1) == 2)){
            System.out.println("Please enter a valid number");
            UserChoice();
        }


    }


    public Survey SurveyOptions(Survey survey) throws IOException, ClassNotFoundException {
        Scanner Input2 = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a Number: ");
        String UserChoice2 = Input2.nextLine();
        if (!Validation.isNumeric(UserChoice2)) {
            System.out.println("Please enter a number");
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
        } else if (!Validation.isValidIntQues(Integer.parseInt(UserChoice2))) {
            System.out.println("Please enter a valid number");
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
        } else if (Integer.parseInt(UserChoice2) == 1) {
            survey.addTF();
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
            return survey;
        } else if (Integer.parseInt(UserChoice2) == 2) {
            survey.addMC();
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
        } else if (Integer.parseInt(UserChoice2) == 3) {
            survey.addShortAnswer();
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
        } else if (Integer.parseInt(UserChoice2) == 4) {
            survey.addEssay();
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
        } else if (Integer.parseInt(UserChoice2) == 5) {
            survey.addDate();
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
        } else if (Integer.parseInt(UserChoice2) == 6) {
            survey.addMatching();
            Menu.DisplayMainMenu2();
            SurveyOptions(survey);
        } else if (Integer.parseInt(UserChoice2) == 7) {
            Menu.DisplayMainMenu();
            MainMenu();

        }

        return survey;

    }

    public Test TestOptions(Test test) throws IOException, ClassNotFoundException {
        Scanner Input2 = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a Number: ");
        String UserChoice2 = Input2.nextLine();
        if (!Validation.isNumeric(UserChoice2)) {
            System.out.println("Please enter a number");
            Menu.DisplayMainMenu2();
            TestOptions(test);
        } else if (!Validation.isValidIntQues(Integer.parseInt(UserChoice2))) {
            System.out.println("Please enter a valid number");
            Menu.DisplayMainMenu2();
            TestOptions(test);
        } else if (Integer.parseInt(UserChoice2) == 1) {
            test.addTF();
            Menu.DisplayMainMenu2();
            TestOptions(test);
            return test;
        } else if (Integer.parseInt(UserChoice2) == 2) {
            test.addMC();
            Menu.DisplayMainMenu2();
            TestOptions(test);
        } else if (Integer.parseInt(UserChoice2) == 3) {
            test.addShortAnswer();
            Menu.DisplayMainMenu2();
            TestOptions(test);
        } else if (Integer.parseInt(UserChoice2) == 4) {
            test.addEssay();
            Menu.DisplayMainMenu2();
            TestOptions(test);
        } else if (Integer.parseInt(UserChoice2) == 5) {
            test.addDate();
            Menu.DisplayMainMenu2();
            TestOptions(test);
            ;
        } else if (Integer.parseInt(UserChoice2) == 6) {
            test.addMatching();
            Menu.DisplayMainMenu2();
            TestOptions(test);
        } else if (Integer.parseInt(UserChoice2) == 7) {
            Menu.DisplayMainTestMenu();
            MainTestMenu();

        }
        return test;

    }
}