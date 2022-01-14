public class Essay extends Question implements java.io.Serializable{
    String answer;

    @Override
    protected void setOptions() {
    }


    @Override
    protected void setAnswers(String[] answers) {

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
