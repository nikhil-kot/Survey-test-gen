import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class FileUtils implements java.io.Serializable{
    String filePath = "Surveys";
    private static final long serialVersionUID = 123456789L;

    protected void save(Survey survey){
        Scanner Input = new Scanner(System.in);
        System.out.println("Enter the name of the survey: ");
        String SurveyName = Input.nextLine();
        survey.setName(SurveyName);
        new File(filePath + "/" + SurveyName);
        String filename = filePath + "/" + SurveyName;

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(survey);
            oos.close();
            fos.close();
            System.out.println("Saved in " + filePath);
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    protected void saveTest(Test test){
        Scanner Input = new Scanner(System.in);
        System.out.println("Enter the name of the test: ");
        String TestName = Input.nextLine();
        test.setName(TestName);
        new File("Tests" + "/" + TestName);
        String filename = "Tests" + "/" + TestName;

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(test);
            oos.close();
            fos.close();
            System.out.println("Saved in " + "Tests");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    protected Survey load(){
        Survey survey = null;
        String[] pathnames;
        File folder = new File(filePath);
        pathnames = folder.list();
        int x = 1;
        System.out.println("Please select a file to load: ");
        for (String pathname : pathnames) {
            System.out.println(x + ") " + pathname);
            x++;
        }
        Scanner Input = new Scanner(System.in);
        String SurveyNum = Input.nextLine();
        while (!Validation.isNumeric(SurveyNum) || Integer.parseInt(SurveyNum) == 0 || Integer.parseInt(SurveyNum) > pathnames.length){
            Input = new Scanner(System.in);
            System.out.println("Please enter a valid file number: ");
            SurveyNum = Input.nextLine();
        }

        String SurveyName = pathnames[Integer.parseInt(SurveyNum)-1];
        String filename = filePath + "/" + SurveyName;
        System.out.println("Loaded " + SurveyName);

        if (Validation.fileExists(SurveyName)){
            try {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                survey = (Survey) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("File not found");
                c.printStackTrace();
            }
        }
        else{
            return null;
        }
        return survey;



    }
    protected Test loadTest(){
        Test test = null;
        String[] pathnames;
        File folder = new File("Tests");
        pathnames = folder.list();
        int x = 1;
        System.out.println("Please select a file to load: ");
        for (String pathname : pathnames) {
            System.out.println(x + ") " + pathname);
            x++;
        }
        Scanner Input = new Scanner(System.in);
        String SurveyNum = Input.nextLine();
        while (!Validation.isNumeric(SurveyNum) || Integer.parseInt(SurveyNum) == 0 || Integer.parseInt(SurveyNum) > pathnames.length){
            Input = new Scanner(System.in);
            System.out.println("Please enter a valid file number: ");
            SurveyNum = Input.nextLine();
        }

        String SurveyName = pathnames[Integer.parseInt(SurveyNum)-1];
        String filename = "Tests" + "/" + SurveyName;
        System.out.println("Loaded " + SurveyName);

        if (Validation.fileExistsT(SurveyName)){
            try {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                test = (Test) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("File not found");
                c.printStackTrace();
            }
        }
        else{
            return null;
        }
        return test;



    }



}
