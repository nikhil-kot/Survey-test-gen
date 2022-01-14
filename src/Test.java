import java.io.*;
import java.nio.file.Paths;
import java.util.*;


public class Test implements java.io.Serializable {


    String FileName;
    private ArrayList<Question> questions = new ArrayList<Question>();
    private String name;
    private static final long serialVersionUID = 123456789L;
    private static int grade;
    public String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    Test() {
        questions = new ArrayList<Question>();
        name = "Test";

    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public static void display(String str) {
        System.out.println(str);
    }


    public static void setMatchingPrompt(Question question) {
        Scanner RPrompt = new Scanner(System.in);
        display("Enter the prompt for your matching question:");
        String Prompt = RPrompt.nextLine();
        question.setPrompt(Prompt);
    }

    public void setMatchingCorrAnswer(Question question) {
        int len = ((Matching) question).getColumn1().length;
        List<String> a = new ArrayList<String>();
        display("Enter correct choice:");
        for (int p = 0; p < len; p++) {
            Scanner Answer1 = new Scanner(System.in);
            String ans1 = Answer1.nextLine();
            a.add(ans1);
        }
        String[] myArray = new String[a.size()];
        a.toArray(myArray);
        ((Matching) question).setCorrAnswer(myArray);
    }

    public void addMatching() {
        Question question = new Matching();
        setMatchingPrompt(question);
        question.setOptions();
        setMatchingCorrAnswer(question);
        questions.add(question);

    }


    public void setDatePrompt(Question question) {
        Scanner DPrompt = new Scanner(System.in);
        display("Enter the prompt for your date question:");
        String Prompt = DPrompt.nextLine();
        question.setPrompt(Prompt);
    }
    public void setDateCorrAnswer(Question question) {
        Scanner Ans = new Scanner(System.in);
        display("Enter correct choice in format mm/dd/yyyy:");
        String NewAns = Ans.nextLine();
        while (!(Validation.isValidDate(NewAns))){
            Ans = new Scanner(System.in);
            display("Enter valid date in the format mm/dd/yyyy:");
            NewAns = Ans.nextLine();
        }
        question.setCorrAnswer(NewAns);
    }

    public void addDate() {
        Question question = new Date();
        setDatePrompt(question);
        setDateCorrAnswer(question);
        questions.add(question);

    }

    public void setShortAnswerPrompt(Question question) {
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your short answer question:");
        String Prompt = PromptEssay.nextLine();
        question.setPrompt(Prompt);
    }

    public void setShortCorrAnswer(Question question) {
        Scanner Ans = new Scanner(System.in);
        display("Enter correct choice:");
        String NewAns = Ans.nextLine();
        question.setCorrAnswer(NewAns);
    }

    protected void addShortAnswer() {
        Question question = new ShortAnswer();
        setShortAnswerPrompt(question);
        setShortCorrAnswer(question);
        questions.add(question);
    }


    public void setTFPrompt(Question question) {
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your T/F question:");
        String Prompt = PromptEssay.nextLine();
        question.setPrompt(Prompt);
    }

    public void setTFCorrAnswer(Question question) {
        Scanner Ans = new Scanner(System.in);
        display("Enter correct choice:");
        String NewAns = Ans.nextLine();
        while (!(Validation.isValidTF(NewAns))){
            Ans = new Scanner(System.in);
            display("Enter true or false:");
            NewAns = Ans.nextLine();
        }
        question.setCorrAnswer(NewAns);

    }

    protected void addTF() {
        Question question = new TF();
        setTFPrompt(question);
        setTFCorrAnswer(question);
        questions.add(question);
    }


    public void setMCPrompt(Question question) {
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your multiple-choice question:");
        String Prompt = PromptEssay.nextLine();
        question.setPrompt(Prompt);
        question.setOptions();
        ((MC) question).setNumAnswers(question);

    }

    public void setMCCorrAnswer(Question question) {
        List<String> answers = new ArrayList();
        for (int i = 1; i <= ((MC) question).getNumAnswers(); i++) {
            Scanner Ans = new Scanner(System.in);
            display("Enter correct choice " + i);
            String NewAns = Ans.nextLine();
            while (!(Validation.isValidMC((MC) question, NewAns))){
                Ans = new Scanner(System.in);
                display("Enter a valid letter:");
                NewAns = Ans.nextLine();
            }
            answers.add(NewAns);
        }
        String[] myArray = new String[answers.size()];
        answers.toArray(myArray);
        ((MC) question).setCorrAnswers(myArray);
    }

    public void addMC() {
        Question question = new MC();
        setMCPrompt(question);
        setMCCorrAnswer(question);
        questions.add(question);
    }


    public void setEssayPrompt(Question question) {
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your essay question:");
        String EssayPrompt = PromptEssay.nextLine();
        question.setPrompt(EssayPrompt);
    }

    protected void addEssay() {
        Question question = new Essay();
        setEssayPrompt(question);
        questions.add(question);

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void take(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        int numQuestions = questions.size();
        for (int x = 0; x < numQuestions; x++) {
            if (questions.get(x) instanceof MC && ((MC) questions.get(x)).getNumAnswers() > 1) {
                List<String> answers = new ArrayList<String>();
                System.out.print(questions.get(x) + "\n");
                for (int z = 1; z <= ((MC) questions.get(x)).getNumAnswers(); z++) {
                    Scanner Answer = new Scanner(System.in);
                    String ans = Answer.nextLine();
                    if (ans.length() == 0) {
                        System.out.print("Please enter an answer for this question\n");
                        Answer = new Scanner(System.in);
                        ans = Answer.nextLine();
                    }
                    while (!Validation.isValidMC((MC) questions.get(x), ans)) {
                        System.out.print("Please enter a valid letter for this question\n");
                        Answer = new Scanner(System.in);
                        ans = Answer.nextLine();
                    }
                    answers.add(ans);
                }
                String[] myArray = new String[answers.size()];
                answers.toArray(myArray);
                ((MC) questions.get(x)).setAnswers(myArray);
                display("you answered: " + Arrays.toString(questions.get(x).getAnswers()));
            }

            if (questions.get(x) instanceof MC && ((MC) questions.get(x)).getNumAnswers() == 1) {
                System.out.print(questions.get(x) + "\n");
                Scanner Answer = new Scanner(System.in);
                String ans = Answer.nextLine();
                if (ans.length() == 0) {
                    System.out.print("Please enter an answer for this question\n");
                    Answer = new Scanner(System.in);
                    ans = Answer.nextLine();
                }
                while (!Validation.isValidMC((MC) questions.get(x), ans)) {
                    System.out.print("Please enter a valid letter for this question\n");
                    Answer = new Scanner(System.in);
                    ans = Answer.nextLine();
                }
                questions.get(x).setAnswer(ans);
                display("you answered: " + questions.get(x).getAnswer());

            }

            if (!(questions.get(x) instanceof Matching) && !(questions.get(x) instanceof MC)) {
                System.out.print(questions.get(x) + "\n");
                Scanner Answer = new Scanner(System.in);
                String ans = Answer.nextLine();
                if (ans.length() == 0) {
                    System.out.print("Please enter an answer for this question\n");
                    Answer = new Scanner(System.in);
                    ans = Answer.nextLine();
                }
                if (questions.get(x) instanceof TF) {
                    while (!Validation.isValidTF(ans)) {
                        System.out.print("Please enter true or false for this question\n");
                        Answer = new Scanner(System.in);
                        ans = Answer.nextLine();
                    }
                }
                if (questions.get(x) instanceof Date) {
                    while (!Validation.isValidDate(ans)) {
                        System.out.print("Please enter a valid date in the format MM/DD/YYYY\n");
                        Answer = new Scanner(System.in);
                        ans = Answer.nextLine();
                    }
                }
                questions.get(x).setAnswer(ans);
                display("you answered: " + questions.get(x).getAnswer());
            }
            if (questions.get(x) instanceof Matching) {
                System.out.print(questions.get(x) + "\n");
                int len = ((Matching) questions.get(x)).getColumn1().length;
                List<String> a = new ArrayList<String>();
                for (int p = 0; p < len; p++) {
                    Scanner Answer1 = new Scanner(System.in);
                    String ans1 = Answer1.nextLine();
                    a.add(ans1);
                }
                String[] myArray = new String[a.size()];
                a.toArray(myArray);
                ((Matching) questions.get(x)).setAnswers(myArray);
                display("you answered: " + Arrays.toString(questions.get(x).getAnswers()));
            }

        }
        Scanner Input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String UserName = Input.nextLine();
        new File("TestResponses" + "/" + test.getName() + "_" + UserName);
        String filename = "TestResponses" + "/" + test.getName() + "_" + UserName;

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(test);
            oos.close();
            fos.close();
            System.out.println("Saved in " + "TestResponses as " + test.getName() + "_" + UserName);
        } catch (IOException i) {
            i.printStackTrace();
        }
        for (int x = 0; x < numQuestions; x++) {
            questions.get(x).clearAnswer();
        }
    }

    public static String getLastElementOfPath(String path) {
        return Paths.get(path).getFileName().toString();
    }

    public static String getLastElementOfPath(File path) {
        return getLastElementOfPath(path.toString());
    }

    public static String grade() {
        Test test = null;
        String[] pathnames;
        File folder = new File("Tests");
        pathnames = folder.list();
        boolean EssayBool = false;
        int numEssay = 0;
        int x = 1;
        System.out.println("Please select a test to grade: ");
        for (String pathname : pathnames) {
            System.out.println(x + ") " + pathname);
            x++;
        }
        Scanner Input = new Scanner(System.in);
        String SurveyNum = Input.nextLine();
        while (!Validation.isNumeric(SurveyNum) || Integer.parseInt(SurveyNum) == 0 || Integer.parseInt(SurveyNum) > pathnames.length) {
            Input = new Scanner(System.in);
            System.out.println("Please enter a valid file number: ");
            SurveyNum = Input.nextLine();
        }

        String SurveyName = pathnames[Integer.parseInt(SurveyNum) - 1];
        display(SurveyName);
        grade = 0;
        File dir = new File("TestResponses");
        File[] files = dir.listFiles((d, name) -> name.startsWith(SurveyName));
        List<String> gradeNames = new ArrayList<>();
        System.out.println("Please enter the number of the test you would like to grade: ");
        x = 1;
        for (File file : files) {
            System.out.println(x + ") " + getLastElementOfPath(file));
            gradeNames.add(file.getName());
            x++;
        }
        Scanner Name = new Scanner(System.in);
        String NameToGrade = Name.nextLine();

        //File dir1 = new File("TestResponses");
        //File[] files1 = dir1.listFiles((d, name) -> name.endsWith(NameToGrade) && name.startsWith(SurveyName));
        File FTG = new File(gradeNames.get(Integer.parseInt(NameToGrade) - 1));
        System.out.println(FTG.getName());
        //for (File file : files1) {
        //     System.out.println("you will grade: " + getLastElementOfPath(file));
        ////     FTG = file;
        // }
        try {
            FileInputStream fileIn = new FileInputStream("TestResponses/" + FTG.getName());
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

        assert test != null;
        ArrayList<Question> questions = test.getQuestions();
        int numQuestions = questions.size();
        for (int p = 0; p < numQuestions; p++) {
            if (questions.get(p) instanceof TF) {
                String correct = questions.get(p).getCorrAnswer()[0];
                String user = questions.get(p).getAnswer();
                if (user.equals(correct)) {
                    grade++;
                }
            } else if (questions.get(p) instanceof ShortAnswer) {
                String correct = questions.get(p).getCorrAnswer()[0];
                String user = questions.get(p).getAnswer();
                if (user.equals(correct)) {
                    grade++;
                }
            } else if (questions.get(p) instanceof Matching) {
                String[] correct = questions.get(p).getCorrAnswer();
                String[] user = questions.get(p).getAnswers();
                ArrayList<String> listOne = new ArrayList<>(Arrays.asList(correct));
                ArrayList<String> listTwo = new ArrayList<>(Arrays.asList(user));

                Collections.sort(listOne);
                Collections.sort(listTwo);

                if (listOne.equals(listTwo)) {
                    grade++;
                }
            } else if (questions.get(p) instanceof MC) {
                if (((MC) questions.get(p)).getNumAnswers() == 1) {
                    String correct = ((MC) questions.get(p)).getCorrAnswers()[0];
                    String user = questions.get(p).getAnswer();
                    if (user.equals(correct)) {
                        grade++;
                    }
                } else if (((MC) questions.get(p)).getNumAnswers() > 1) {
                    String[] correct = questions.get(p).getCorrAnswers();
                    String[] user = questions.get(p).getAnswers();
                    ArrayList<String> listOne = new ArrayList<>(Arrays.asList(correct));
                    ArrayList<String> listTwo = new ArrayList<>(Arrays.asList(user));
                    Collections.sort(listOne);
                    Collections.sort(listTwo);
                    if (listOne.equals(listTwo)) {
                        grade++;
                    }
                }
            } else if (questions.get(p) instanceof Date) {
                String correct = questions.get(p).getCorrAnswer()[0];
                String user = questions.get(p).getAnswer();
                if (user.equals(correct)) {
                    grade++;
                }
            } else if ((questions.get(p) instanceof Essay)) {
                EssayBool = true;
                numEssay++;

            }
        }
        float finalGrade = ((float) grade) / numQuestions;
        String str = String.format("%2.02f", (finalGrade * 100));
        if (EssayBool == false) {
            String Final = "You received " + str + " on the test";
            return Final;
        }
        else if (EssayBool == true) {
            float gr = ((float) (numQuestions-numEssay)/numQuestions)*100;
            String str1 = String.format("%2.02f", gr);
            String Final = "You received " + str + " on the test. The test was worth 100 points, but only " + str1 + " of those points could be graded because there were " + numEssay +" essay question/s";
            return Final;
        }
        return null;

    }



    public void modify(Test test) {
        Scanner Question = new Scanner(System.in);
        display("What question do you wish to modify?" );
        String QuestionMod = Question.nextLine();
        ArrayList<Question> array = test.getQuestions();
        ArrayList<String> ar = new ArrayList<String>();
        int num = array.size();
        for (int i = 0; i < num; i++){
            String prompt = "";
            prompt = array.get(i).getPrompt();
            ar.add(prompt);

        }

        if (ar.contains(QuestionMod)) {
            display("Existing Prompt: \n" + QuestionMod);
            int loc = ar.indexOf(QuestionMod);
            Question question = array.get(loc);
            if (!(question instanceof MC)) {
                Scanner Y = new Scanner(System.in);
                display("Do you wish to modify prompt? ");
                String choice = Y.nextLine();
                if (choice.equals("yes")) {
                    Scanner Prompt = new Scanner(System.in);
                    display("Enter a new prompt: ");
                    String PP = Prompt.nextLine();
                    question.setPrompt(PP);
                    new File("Tests" + "/" + test.getName());
                    String filename1 = "Tests" + "/" + test.getName();
                    try {
                        FileOutputStream fos = new FileOutputStream(filename1);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(test);
                        oos.close();
                        fos.close();
                        System.out.println("Saved the change");
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
                if (question instanceof Date) {
                    Scanner Z = new Scanner(System.in);
                    display("Do you wish to modify the correct choice? ");
                    String Corr = Z.nextLine();
                    if (Corr.equals("yes")) {
                        Scanner NewCorr = new Scanner(System.in);
                        display("Enter a new correct choice: ");
                        String NC = NewCorr.nextLine();
                        question.setCorrAnswer(NC);
                        System.out.println("Saved new correct answer as: " + Arrays.toString(question.getCorrAnswer()));
                    }
                } else if ((question instanceof ShortAnswer) && !(question instanceof Date)){
                    Scanner Z = new Scanner(System.in);
                    display("Do you wish to modify the correct choice? ");
                    String Corr = Z.nextLine();
                    if (Corr.equals("yes")) {
                        Scanner NewCorr = new Scanner(System.in);
                        display("Enter a new correct choice: ");
                        String NC = NewCorr.nextLine();
                        question.setCorrAnswer(NC);
                        System.out.println("Saved new correct answer as: " + Arrays.toString(question.getCorrAnswer()));
                    }
                } else if (question instanceof TF) {
                    Scanner Z = new Scanner(System.in);
                    display("Do you wish to modify the correct choice? ");
                    String Corr = Z.nextLine();
                    if (Corr.equals("yes")) {
                        Scanner NewCorr = new Scanner(System.in);
                        display("Enter a new correct choice: ");
                        String NC = NewCorr.nextLine();
                        question.setCorrAnswer(NC);
                        System.out.println("Saved new correct answer as: " + Arrays.toString(question.getCorrAnswer()));
                    }
                }
                else if (question instanceof Matching) {
                    Scanner Z = new Scanner(System.in);
                    display("Do you wish to modify the correct choice? ");
                    String Corr = Z.nextLine();
                    if (Corr.equals("yes")) {
                        setMatchingCorrAnswer(question);
                        System.out.println("Saved new correct answer as: " + Arrays.toString(question.getCorrAnswer()));
                    }
                }

            }
            else if (question instanceof MC){
                Scanner Y = new Scanner(System.in);
                display("Do you wish to modify prompt? ");
                String choice = Y.nextLine();
                if (choice.equals("yes")) {
                    Scanner Prompt = new Scanner(System.in);
                    display("Enter a new prompt: ");
                    String PP = Prompt.nextLine();
                    question.setPrompt(PP);

                    Scanner YC = new Scanner(System.in);
                    display("Do you wish to modify the choices? ");
                    String choiceC = YC.nextLine();
                    if (choiceC.equals("yes")) {

                        String[] arrayChoices = question.getOptions();
                        List<String> list = Arrays.asList(arrayChoices);
                        String Options = "";
                        int num1 = arrayChoices.length;
                        int p = 0;
                        Scanner ctc = new Scanner(System.in);
                        display("Which choice do you want to modify?");
                        while (p < num1){
                            String TBA = "";
                            Options = Options + (p+1) + ") " + arrayChoices[p] + "    ";
                            p++;
                        }
                        System.out.println(Options);
                        String ctc1 = ctc.nextLine();
                        if (list.contains(ctc1)){
                            int locC = list.indexOf(ctc1);
                            Scanner NC = new Scanner(System.in);
                            display("What do you want to change the choice to?");
                            String NC1 = NC.nextLine();
                            arrayChoices[locC] = NC1;
                            ((MC) question).resetOptions(arrayChoices);
                        }
                        Scanner YC1 = new Scanner(System.in);
                        display("Do you wish to modify the correct choice? ");
                        String choiceC1 = YC1.nextLine();
                        if (choiceC1.equals("yes")) {
                            if (((MC) question).getNumAnswers() == 1) {
                                System.out.println("The current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                                Scanner Cor = new Scanner(System.in);
                                display("Enter a new correct choice: ");
                                String CC = Cor.nextLine();
                                List<String> TBA = new ArrayList<String>();
                                TBA.add(CC);
                                ((MC) question).setCorrAnswers(TBA.toArray(new String[0]));
                                System.out.println("The new current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                            } else {
                                System.out.println("The current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                                List<String> answers = new ArrayList<String>();
                                for (int i = 1; i <= ((MC) question).getNumAnswers(); i++) {
                                    Scanner Ans = new Scanner(System.in);
                                    display("Enter new correct choice " + i);
                                    String NewAns = Ans.nextLine();
                                    answers.add(NewAns);
                                }
                                String[] myArray = new String[answers.size()];
                                answers.toArray(myArray);
                                ((MC) question).setCorrAnswers(myArray);
                                System.out.println("The new current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                            }
                        }
                    }

                    new File("Tests" + "/" + test.getName());
                    String filename1 = "Tests" + "/" + test.getName();
                    try {
                        FileOutputStream fos = new FileOutputStream(filename1);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(test);
                        oos.close();
                        fos.close();
                        System.out.println("Saved the change");
                    } catch (IOException i) {
                        i.printStackTrace();
                    }

                }
                else if (choice.equals("no")){
                    Scanner YC = new Scanner(System.in);
                    display("Do you wish to modify the choices? ");
                    String choiceC = YC.nextLine();
                    if (choiceC.equals("yes")) {

                        String[] arrayChoices = question.getOptions();
                        List<String> list = Arrays.asList(arrayChoices);
                        String Options = "";
                        int num1 = arrayChoices.length;
                        int p = 0;
                        Scanner ctc = new Scanner(System.in);
                        display("Which choice do you want to modify?");
                        while (p < num1){
                            String TBA = "";
                            Options = Options + (p+1) + ") " + arrayChoices[p] + "    ";
                            p++;
                        }
                        System.out.println(Options);
                        String ctc1 = ctc.nextLine();


                        if (list.contains(ctc1)){
                            int locC = list.indexOf(ctc1);
                            Scanner NC = new Scanner(System.in);
                            display("What do you want to change the choice to?");
                            String NC1 = NC.nextLine();
                            arrayChoices[locC] = NC1;
                            ((MC) question).resetOptions(arrayChoices);
                        }
                        Scanner YC1 = new Scanner(System.in);
                        display("Do you wish to modify the correct choice? ");
                        String choiceC1 = YC1.nextLine();
                        if (choiceC1.equals("yes")) {
                            if (((MC) question).getNumAnswers() == 1) {
                                System.out.println("The current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                                Scanner Cor = new Scanner(System.in);
                                display("Enter a new correct choice: ");
                                String CC = Cor.nextLine();
                                List<String> TBA = new ArrayList<String>();
                                TBA.add(CC);
                                ((MC) question).setCorrAnswers(TBA.toArray(new String[0]));
                                System.out.println("The new current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                            } else {
                                System.out.println("The current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                                List<String> answers = new ArrayList<String>();
                                for (int i = 1; i <= ((MC) question).getNumAnswers(); i++) {
                                    Scanner Ans = new Scanner(System.in);
                                    display("Enter new correct choice " + i);
                                    String NewAns = Ans.nextLine();
                                    answers.add(NewAns);
                                }
                                String[] myArray = new String[answers.size()];
                                answers.toArray(myArray);
                                ((MC) question).setCorrAnswers(myArray);
                                System.out.println("The new current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                            }
                        }


                    }
                    else if (choiceC.equals("no")){
                        Scanner YC1 = new Scanner(System.in);
                        display("Do you wish to modify the correct choice? ");
                        String choiceC1 = YC1.nextLine();
                        if (choiceC1.equals("yes")) {
                            if (((MC) question).getNumAnswers() == 1) {
                                System.out.println("The current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                                Scanner Cor = new Scanner(System.in);
                                display("Enter a new correct choice: ");
                                String CC = Cor.nextLine();
                                List<String> TBA = new ArrayList<String>();
                                TBA.add(CC);
                                ((MC) question).setCorrAnswers(TBA.toArray(new String[0]));
                                System.out.println("The new current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                            } else {
                                System.out.println("The current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                                List<String> answers = new ArrayList<String>();
                                for (int i = 1; i <= ((MC) question).getNumAnswers(); i++) {
                                    Scanner Ans = new Scanner(System.in);
                                    display("Enter new correct choice " + i);
                                    String NewAns = Ans.nextLine();
                                    answers.add(NewAns);
                                }
                                String[] myArray = new String[answers.size()];
                                answers.toArray(myArray);
                                ((MC) question).setCorrAnswers(myArray);
                                System.out.println("The new current correct choice is: " + Arrays.toString(question.getCorrAnswers()));
                            }
                        }
                        new File("Tests" + "/" + test.getName());
                        String filename1 = "Tests" + "/" + test.getName();
                        try {
                            FileOutputStream fos = new FileOutputStream(filename1);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(test);
                            oos.close();
                            fos.close();
                        } catch (IOException i) {
                            i.printStackTrace();
                        }
                    }
                    new File("Tests" + "/" + test.getName());
                    String filename1 = "Tests" + "/" + test.getName();
                    try {
                        FileOutputStream fos = new FileOutputStream(filename1);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(test);
                        oos.close();
                        fos.close();
                        System.out.println("Saved the change");
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }

            }
        }

    }
    public void tabulate(Test test) {
        ArrayList<Question> questions = test.getQuestions();
        String SurveyName = test.getName();
        String finalSurveyName = SurveyName;
        File dir = new File("TestResponses");
        File[] files = dir.listFiles((d, name) -> name.startsWith(finalSurveyName));
        for (Question question : questions) {
            if (question instanceof TF) {
                List<String> tabNames = new ArrayList<>();
                List<String> TFlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "TestResponses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = test.getQuestions();

                    if (questions1.get(x) instanceof TF) {
                        String tf = questions1.get(x).getAnswer();
                        TFlist.add(tf);
                    }

                }
                int occurrencesT = Collections.frequency(TFlist, "true");
                int occurrencesF = Collections.frequency(TFlist, "false");
                System.out.println(questions.get(x).getPrompt() + " true / false");
                System.out.println("True: " + occurrencesT);
                System.out.println("False: " + occurrencesF);
                System.out.println("\n");
            } if (question instanceof ShortAnswer && !(question instanceof Date)) {
                List<String> tabNames = new ArrayList<>();
                List<String> SAlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "TestResponses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = test.getQuestions();

                    if (questions1.get(x) instanceof ShortAnswer) {
                        String SA = questions1.get(x).getAnswer();
                        SAlist.add(SA);
                    }

                }
                Map<String, Integer> hm = new HashMap<String, Integer>();

                for (String i : SAlist) {
                    Integer j = hm.get(i);
                    hm.put(i, (j == null) ? 1 : j + 1);
                }

                System.out.println(questions.get(x));
                for (Map.Entry<String, Integer> val : hm.entrySet()) {
                    System.out.println(val.getKey() + " " +
                            val.getValue());
                }
                System.out.println("\n");
            }  if (question instanceof Essay && !(question instanceof ShortAnswer)) {
                List<String> tabNames = new ArrayList<>();
                List<String> SAlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "TestResponses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = test.getQuestions();

                    if (questions1.get(x) instanceof Essay) {
                        String SA = questions1.get(x).getAnswer();
                        SAlist.add(SA);
                    }

                }
                System.out.println(questions.get(x));
                for (String s : SAlist) {
                    System.out.println(s + "\n");
                }
            }
            if (question instanceof Matching) {
                List<String> tabNames = new ArrayList<>();
                List<List<String>> Mlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "TestResponses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = test.getQuestions();

                    if (questions1.get(x) instanceof Matching) {
                        List<String> ansM = new ArrayList<>();
                        ansM = Arrays.asList(questions1.get(x).getAnswers());
                        Collections.sort(ansM);
                        Mlist.add(ansM);
                    }

                }
                Map<List<String>, Integer> hm = new HashMap<>();

                for (List<String> i : Mlist) {
                    Integer j = hm.get(i);
                    hm.put(i, (j == null) ? 1 : j + 1);
                }

                System.out.println(questions.get(x));
                for (Map.Entry<List<String>, Integer> val : hm.entrySet()) {
                    System.out.println((val.getValue()));
                    for (int n = 0; n < val.getKey().size(); n++ ){
                        System.out.println(val.getKey().get(n));
                    }
                    display("\n");
                }
            }  if (question instanceof Date) {
                List<String> tabNames = new ArrayList<>();
                List<String> SAlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "TestResponses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = test.getQuestions();

                    if (questions1.get(x) instanceof Date) {
                        String SA = questions1.get(x).getAnswer();
                        SAlist.add(SA);
                    }

                }
                Map<String, Integer> hm = new HashMap<String, Integer>();

                for (String i : SAlist) {
                    Integer j = hm.get(i);
                    hm.put(i, (j == null) ? 1 : j + 1);
                }

                System.out.println(questions.get(x).getPrompt());
                for (Map.Entry<String, Integer> val : hm.entrySet()) {
                    System.out.println(val.getKey() + "\n" +
                            val.getValue() + "\n");
                }

            } if (question instanceof MC) {
                List<String> tabNames = new ArrayList<>();
                List<String> MClist = new ArrayList<>();
                int x = questions.indexOf(question);
                int numAns = question.getOptions().length;
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "TestResponses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = test.getQuestions();

                    if (questions1.get(x) instanceof MC && ((MC) questions1.get(x)).getNumAnswers() == 1) {
                        String SA = questions1.get(x).getAnswer();
                        MClist.add(SA);
                    } else if (questions1.get(x) instanceof MC && ((MC) questions1.get(x)).getNumAnswers() > 1){
                        String[] SA = questions1.get(x).getAnswers();
                        MClist.addAll(Arrays.asList(SA));
                    }
                }
                System.out.println(questions.get(x).getPrompt());
                for (int k = 0; k<numAns; k++){
                    System.out.println(letters[k] + ": " +  Collections.frequency(MClist, letters[k]));
                }
                System.out.println("\n");
            }

        }
    }
}

