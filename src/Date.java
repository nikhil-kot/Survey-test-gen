public class Date extends ShortAnswer implements java.io.Serializable{
    @Override
    public String toString(){
        return getPrompt() + "\nA date should be entered in the following format: MM/DD/YYYY";
    }

}
