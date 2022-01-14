public class ShortAnswer extends Essay implements java.io.Serializable{

    private static final long serialVersionUID = 208338474535416L;
    ShortAnswer() {

    }


    @Override
    public String toString(){
        return getPrompt();
    }

}
