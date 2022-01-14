import java.util.Scanner;

public class Matching extends Question implements java.io.Serializable{
    public int choices;
    public String[] column1;
    public String[] column2;
    public String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] answer;
    public  String[] CorrAnswer;
    private static final long serialVersionUID = -7964985668734173630L;


    protected String[] getColumn1() {
        return column1;
    }

    protected String[] getColumn2() {
        return column2;
    }
    protected void setCorrAnswer(String[] answer){
        this.CorrAnswer = answer;
    }

    public String[] getCorrAnswer(){
        return CorrAnswer;
    }


    protected void setAnswers(String[] answers) {
        this.answer = answers;
    }


    protected void setOptions() {
        int choice;
        Scanner UserNum = new Scanner(System.in);
        System.out.println("Enter the number of choices for your question: ");
        choices = Integer.parseInt(UserNum.nextLine());
        column1 = new String[choices];
        column2 = new String[choices];
        String[] answer = this.answer;


        System.out.println("Choices for Number Column: ");
        for (int i = 0; i < choices; i++) {
            choice = i + 1;
            Scanner UserNum2 = new Scanner(System.in);
            System.out.println("Please enter choice #" + choice);
            column1[i] = UserNum2.nextLine();

        }

        System.out.println("Choices for Letter Column: ");
        for (int i = 0; i < choices; i++) {
            choice = i + 1;
            Scanner UserNum3 = new Scanner(System.in);
            System.out.println("Please enter choice " + letters[i]);
            column2[i] = UserNum3.nextLine();
        }

    }

    protected void setAnswer(String[] answer){
        this.answer = answer;
    }
    @Override
    public String toString() {
        String options = "";
        int num = getColumn1().length;
        String[] USRletters = getColumn2();
        String[] USRnumbers = getColumn1();
        int p = 0;
        String Final = null;
        while (p < num) {
            String TBA = "";
            String Left = letters[p] + ") " + USRletters[p];
            String Right = (p + 1) + ") " + USRnumbers[p] + "\n";
            //options = options + (letters[p] + ") " + USRletters[p] + "        " + (p+1) +") " + USRnumbers[p] + "\n");
            Final = String.format("%-15s %15s", Left, Right);
            options = options + Final;
            p++;
        }
        return getPrompt() + "\n \n" + options;

    }


    public String[] getAnswers() {
        return answer;
    }

    @Override
    public String[] getCorrAnswers() {
        return new String[0];
    }


    protected void clearAnswer(){
        this.answer = null;
    }
}
