import java.io.File;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Validation {


    public static boolean isValidMC(MC mc, String string){

        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        List<String> stringList = Arrays.asList(letters);
        int numChoices = mc.getOptions().length;
        List<String> firstNElementsList = stringList.stream().limit(numChoices).collect(Collectors.toList());
        if (firstNElementsList.contains(string)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isValidTF(String string){
        return string.toLowerCase().equals("true") || string.toLowerCase().equals("false");
    }

    public static boolean isValidYN(String string){
        return string.toLowerCase().equals("yes") || string.toLowerCase().equals("no");
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static boolean isValidInt(int input){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        boolean integerExists = integers.contains(input);
        if (integerExists){
            return true;
        } else{
            return false;
        }

    }

    public static boolean isValidIntTest(int input){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        boolean integerExists = integers.contains(input);
        if (integerExists){
            return true;
        } else{
            return false;
        }

    }
    public static boolean fileExists(String file){
        File f = new File("Surveys/" + file);
        if(f.exists()){
            return true;
        }
        else{
            return false;
        }

    }
    public static boolean fileExistsTR(String file){
        File f = new File("TestResponses/" + file);
        if(f.exists()){
            return true;
        }
        else{
            return false;
        }

    }
    public static boolean fileExistsT(String file){
        File f = new File("Tests/" + file);
        if(f.exists()){
            return true;
        }
        else{
            return false;
        }

    }

    public static boolean isValidDate(String string) {
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        boolean bool = matcher.matches();
        if(bool) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidIntQues(int input){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        boolean integerExists = integers.contains(input);
        if (integerExists){
            return true;
        } else{
            return false;
        }

    }

}
