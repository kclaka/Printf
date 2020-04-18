package edu.sbcc.cs105;
import java.util.Formatter;
import java.util.regex.*;

public class Main {


    public static void main(String[] args) {
        System.out.println(format("Hello %s", "Kira"));
    }

    static public String format(String format, Object... values) {
        try{
            return new Formatter().format(format, values).toString();
        }catch (IllegalArgumentException e ){
            throw e;
        }
    }



//We dont need to build a RegEx method from scratch  to solve 97% of this problem

  /*  static boolean checkMatch(String inputText) {
        String regExPattern = "%(\\\\d+)?(\\\\.(\\\\d+))?([SsDdFfXx])";
        Pattern p = Pattern.compile(regExPattern);
        Matcher matcher = p.matcher(inputText);
        boolean match = matcher.matches();
        return match;
   }*/


}











