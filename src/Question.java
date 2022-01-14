
public abstract class Question implements java.io.Serializable{
    int ID;
    String answer;
    String corrAnswer;
    String[] options;
    private String prompt;
    private static final long serialVersionUID = 1584251193249090786L;

    protected void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    protected abstract void setOptions();
    protected void clearAnswer(){
        this.answer = null;
    }

    protected String getPrompt() {
        return prompt;
    }

    public String[] getOptions() {
        return options;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setCorrAnswer(String answer){
        this.corrAnswer = answer;
    }

    public String[] getCorrAnswer() {
        return new String[]{corrAnswer};
    }

    protected abstract void setAnswers(String[] answers);

    public String toString(){
        return getPrompt();
    }

    public String[] getAnswers(){
        return new String[]{answer};
    }

    public abstract String[] getCorrAnswers();
}
