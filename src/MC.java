import java.util.Scanner;

public class MC extends Question implements java.io.Serializable{
    protected String[] choices;
    String[] answers;
    String[] corrAnswers;
    String answer;
    int numAnswers;
    private static final long serialVersionUID = 735154946839L;
    public String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    protected void setOptions() {
        String numChoices;
        int choice;
        Scanner UserNum = new Scanner(System.in);
        System.out.println("Enter the number of choices for your multiple-choice question: ");
        numChoices = UserNum.nextLine();
        while (!Validation.isNumeric(numChoices)){
            UserNum = new Scanner(System.in);
            System.out.println("Enter a valid number");
            numChoices = UserNum.nextLine();
        }
        choices = new String[Integer.parseInt(numChoices)];
        for (int i = 0; i < Integer.parseInt(numChoices); i++) {
            choice = i + 1;
            Scanner ScanChoiceNum = new Scanner(System.in);
            System.out.println("Enter choice " + letters[i]);
            choices[i] = ScanChoiceNum.nextLine();
        }

    }
    public String[] getOptions() {
        return choices;
    }


    public void resetOptions(String[] options){
        choices = options;
    }



    protected void setAnswers(String[] answers) {
        this.answers = answers;
    }
    protected void setCorrAnswers(String[] answers) {
        this.corrAnswers = answers;
    }
   public String[] getCorrAnswers() {
        return corrAnswers;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }

    protected void setNumAnswers(Question question) {
        Scanner UserNum1 = new Scanner(System.in);
        System.out.println("Enter the number of answers for your multiple choice question: ");
        String numAns = UserNum1.nextLine();
        while (!Validation.isNumeric(numAns) || Integer.parseInt(numAns) > question.getOptions().length){
            UserNum1 = new Scanner(System.in);
            System.out.println("Enter a valid number");
            numAns = UserNum1.nextLine();
        }
        this.numAnswers = Integer.parseInt(numAns);

    }
    protected int getNumAnswers(){
        return numAnswers;
    }

    @Override
    public String toString(){
        String Options = "";
        String[] choices;
        choices = getOptions();
        int num = choices.length;
        int p = 0;
        while (p < num){
            String TBA = "";
            Options = Options + letters[p] + ") " + choices[p] + "    ";
            p++;
        }
        return getPrompt() +" Please give "+ getNumAnswers()+" choices: " + "\n" + Options;

    }

    @Override
    public String[] getAnswers() {
        return answers;
    }


    @Override

    public String getAnswer() {
        return answer;
    }


}
