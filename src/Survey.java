
import java.io.*;
import java.util.*;
import java.util.List;


public class Survey implements java.io.Serializable{

    String FileName;
    private ArrayList<Question> questions = new ArrayList<Question>();
    private String name;
    private static final long serialVersionUID = 12345678L;
    public String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    Survey(){

        questions = new ArrayList<Question>();
        name = "Survey";

    }
    public static void display(String str) {
        System.out.println(str);
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }


    public static void setMatchingPrompt(Question question){
        Scanner RPrompt = new Scanner(System.in);
        display("Enter the prompt for your matching question:" );
        String Prompt = RPrompt.nextLine();
        question.setPrompt(Prompt);
    }

    public void addMatching(){
        Question question = new Matching();
        setMatchingPrompt(question);
        question.setOptions();
        questions.add(question);

    }


    public void setDatePrompt(Question question){
        Scanner DPrompt = new Scanner(System.in);
        display("Enter the prompt for your date question:" );
        String Prompt = DPrompt.nextLine();
        question.setPrompt(Prompt);
    }

    public void addDate(){
        Question question = new Date();
        setDatePrompt(question);
        questions.add(question);

    }


    public void setShortAnswerPrompt(Question question){
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your short answer question:" );
        String Prompt = PromptEssay.nextLine();
        question.setPrompt(Prompt);
    }

    protected void addShortAnswer(){
        Question question = new ShortAnswer();
        setShortAnswerPrompt(question);
        questions.add(question);
    }


    public void setTFPrompt(Question question){
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your T/F question:" );
        String Prompt = PromptEssay.nextLine();
        question.setPrompt(Prompt);
    }

    protected void addTF(){
        Question question = new TF();
        setTFPrompt(question);
        questions.add(question);
    }


    public void setMCPrompt(Question question){
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your multiple-choice question:" );
        String Prompt = PromptEssay.nextLine();
        question.setPrompt(Prompt);
    }

    public void addMC(){
        Question question = new MC();
        setMCPrompt(question);
        question.setOptions();
        ((MC) question).setNumAnswers(question);
        questions.add(question);

    }


    public void setEssayPrompt(Question question) {
        Scanner PromptEssay = new Scanner(System.in);
        display("Enter the prompt for your essay question:" );
        String EssayPrompt = PromptEssay.nextLine();
        question.setPrompt(EssayPrompt);
    }

    protected void addEssay(){
        Question question = new Essay();
        setEssayPrompt(question);
        questions.add(question);

    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }


    public void tabulate(Survey survey) {
        ArrayList<Question> questions = survey.getQuestions();
        for (Question question : questions) {
            if (question instanceof TF) {
                String SurveyName = survey.getName();
                File dir = new File("Responses");
                String finalSurveyName = SurveyName;
                File[] files = dir.listFiles((d, name) -> name.startsWith(finalSurveyName));
                List<String> tabNames = new ArrayList<>();
                List<String> TFlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "Responses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = survey.getQuestions();

                    if (questions1.get(x) instanceof TF) {
                        String tf = questions1.get(x).getAnswer();
                        TFlist.add(tf);
                    }

                }
                int occurrencesT = Collections.frequency(TFlist, "true");
                int occurrencesF = Collections.frequency(TFlist, "false");
                System.out.println(questions.get(x).getPrompt() + "true / false");
                System.out.println("True: " + occurrencesT);
                System.out.println("False: " + occurrencesF);
                System.out.println("\n");
            } if (question instanceof ShortAnswer && !(question instanceof Date)) {
                String SurveyName = survey.getName();
                File dir = new File("Responses");
                String finalSurveyName = SurveyName;
                File[] files = dir.listFiles((d, name) -> name.startsWith(finalSurveyName));
                List<String> tabNames = new ArrayList<>();
                List<String> SAlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "Responses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = survey.getQuestions();

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
                String SurveyName = survey.getName();
                File dir = new File("Responses");
                String finalSurveyName = SurveyName;
                File[] files = dir.listFiles((d, name) -> name.startsWith(finalSurveyName));
                List<String> tabNames = new ArrayList<>();
                List<String> SAlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "Responses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = survey.getQuestions();

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
                String SurveyName = survey.getName();
                File dir = new File("Responses");
                String finalSurveyName = SurveyName;
                File[] files = dir.listFiles((d, name) -> name.startsWith(finalSurveyName));
                List<String> tabNames = new ArrayList<>();
                List<List<String>> Mlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "Responses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = survey.getQuestions();

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
                String SurveyName = survey.getName();
                File dir = new File("Responses");
                String finalSurveyName = SurveyName;
                File[] files = dir.listFiles((d, name) -> name.startsWith(finalSurveyName));
                List<String> tabNames = new ArrayList<>();
                List<String> SAlist = new ArrayList<>();
                int x = questions.indexOf(question);
                for (File file : files) {
                    tabNames.add(file.getName());
                }

                int p = 0;
                for (File file : files) {
                    SurveyName = file.getName();
                    String filename = "Responses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = survey.getQuestions();

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
                String SurveyName = survey.getName();
                File dir = new File("Responses");
                String finalSurveyName = SurveyName;
                File[] files = dir.listFiles((d, name) -> name.startsWith(finalSurveyName));
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
                    String filename = "Responses" + "/" + SurveyName;
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
                    ArrayList<Question> questions1 = survey.getQuestions();

                    if ((questions1.get(x) instanceof MC) && ((MC) questions1.get(x)).getNumAnswers() == 1) {
                        String SA = questions1.get(x).getAnswer();
                        MClist.add(SA);
                    }
                    else if ((questions1.get(x) instanceof MC) && ((MC) questions1.get(x)).getNumAnswers() > 1){
                        String[] SA1 = questions1.get(x).getAnswers();
                        for (String item : SA1 ) {
                            MClist.add(item);
                        }
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


    public void take(Survey survey){
        ArrayList<Question> questions = survey.getQuestions();
        int numQuestions = questions.size();
        for (int x = 0; x < numQuestions; x++) {
            if (questions.get(x) instanceof MC && ((MC) questions.get(x)).getNumAnswers() > 1){
                List<String> answers = new ArrayList<String>();
                System.out.print(questions.get(x) + "\n");
                for (int z = 1; z <= ((MC) questions.get(x)).getNumAnswers(); z++){
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

            if (!(questions.get(x) instanceof Matching) && !(questions.get(x) instanceof MC)){
                System.out.print(questions.get(x) + "\n");
                Scanner Answer = new Scanner(System.in);
                String ans = Answer.nextLine();
                if(ans.length() == 0){
                    System.out.print("Please enter an answer for this question\n");
                    Answer = new Scanner(System.in);
                    ans = Answer.nextLine();
                }
                if (questions.get(x) instanceof TF) {
                    while (!Validation.isValidTF(ans)){
                        System.out.print("Please enter true or false for this question\n");
                        Answer = new Scanner(System.in);
                        ans = Answer.nextLine();
                    }
                }
                if (questions.get(x) instanceof Date){
                while (!Validation.isValidDate(ans)) {
                    System.out.print("Please enter a valid date in the format MM/DD/YYYY\n");
                    Answer = new Scanner(System.in);
                    ans = Answer.nextLine();
                    }
                }
                questions.get(x).setAnswer(ans);
                display("you answered: " + questions.get(x).getAnswer());
            }
            if (questions.get(x) instanceof Matching){
                System.out.print(questions.get(x) + "\n");
                int len =((Matching) questions.get(x)).getColumn1().length;
                List<String> a = new ArrayList<String>();
                for (int p = 0; p < len; p++){
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
        new File("Responses" + "/" + survey.getName() + "_" + UserName);
        String filename = "Responses" + "/" + survey.getName() + "_" + UserName;

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(survey);
            oos.close();
            fos.close();
            System.out.println("Saved in " + "Responses as " + survey.getName() + "_" + UserName);
        } catch (IOException i) {
            i.printStackTrace();
        }
        for (int x = 0; x < numQuestions; x++){
            questions.get(x).clearAnswer();
        }
    }

    public void modify(Survey survey) {
        Scanner Question = new Scanner(System.in);
        display("What question do you wish to modify?" );
        String QuestionMod = Question.nextLine();
        ArrayList<Question> array = survey.getQuestions();
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
                Scanner Prompt = new Scanner(System.in);
                display("Enter a new prompt: ");
                String PP = Prompt.nextLine();
                question.setPrompt(PP);
                new File("Surveys" + "/" + survey.getName());
                String filename1 = "Surveys" + "/" + survey.getName();
                try {
                    FileOutputStream fos = new FileOutputStream(filename1);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(survey);
                    oos.close();
                    fos.close();
                    System.out.println("Saved the change");
                } catch (IOException i) {
                    i.printStackTrace();
                }


            } else if (question instanceof MC){
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


                    }
                    new File("Surveys" + "/" + survey.getName());
                    String filename1 = "Surveys" + "/" + survey.getName();
                    try {
                        FileOutputStream fos = new FileOutputStream(filename1);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(survey);
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


                    }
                    else if (choiceC.equals("no")){
                        new File("Surveys" + "/" + survey.getName());
                        String filename1 = "Surveys" + "/" + survey.getName();
                        try {
                            FileOutputStream fos = new FileOutputStream(filename1);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(survey);
                            oos.close();
                            fos.close();
                            System.out.println("Saved the change");
                        } catch (IOException i) {
                            i.printStackTrace();
                        }
                    }
                    new File("Surveys" + "/" + survey.getName());
                    String filename1 = "Surveys" + "/" + survey.getName();
                    try {
                        FileOutputStream fos = new FileOutputStream(filename1);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(survey);
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


    }


