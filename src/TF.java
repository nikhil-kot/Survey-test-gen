public class TF extends Question implements java.io.Serializable{
    String answer;
    private static final long serialVersionUID = -3188832209316813721L;
    public final String[] choices = {"True", "False"};


    @Override
    protected void setOptions() {

    }

    @Override
    protected void setAnswers(String[] answers) {

    }

    @Override
    public String toString(){
        return getPrompt() + "\n" + "T/F";
    }

    @Override
    public String[] getAnswers() {
        return null;
    }

    @Override
    public String[] getCorrAnswers() {
        return new String[0];
    }
}
