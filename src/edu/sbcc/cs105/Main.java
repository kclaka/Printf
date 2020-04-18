package edu.sbcc.cs105;
import java.util.regex.*;
import java.util.IllegalFormatConversionException;

public class Main {


    public static void main(String[] args) {
        try{
            System.out.println(format("hello world", checkMatch("%3f")));
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    static public String format(String format, Object... values) {

        checkMatch(format);

        return format;

    }


    static boolean checkMatch(String inputText) {
        String regExPattern = "%(\\\\d+)?(\\\\.(\\\\d+))?([SsDdFfXx])";
        Pattern p = Pattern.compile(regExPattern);
        Matcher matcher = p.matcher(inputText);
        boolean match = matcher.matches();
       
        return match;
    }


}











