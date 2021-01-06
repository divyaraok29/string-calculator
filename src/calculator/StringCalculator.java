package calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static int count_invokes_to_add;

    StringCalculator() {
        count_invokes_to_add = 0;
    }

    public static int add(String numbers) throws NegativesNotAllowedException {
        /** Takes a string of integers, and returns their sum.
         *  Args
         *      numbers (String) - string of numbers separated by delimiter
         *  Returns
         *      (int) - sum of the numbers
         */

        count_invokes_to_add++;

        // when there are no numbers
        if(numbers == "") return 0;

        // when atleast 1 number is given
        String delimiters = ",|\n";

        ArrayList<String> result = new ArrayList<>();
        result = getDefaultDelimiter(numbers);

        String default_delimiter = "", clean_numbers = numbers;

        if(result.size() != 0){
            default_delimiter = result.get(0);
            clean_numbers = result.get(1);
        }

        if(default_delimiter != "") {
            if(!delimiters.contains(default_delimiter)){
                delimiters += "|\\Q" + default_delimiter + "\\E";
            }
            numbers = clean_numbers;
        }

        String[] number_list;
        // when first number after the delimiter regex is a negative integer
        if(numbers.startsWith("-")){
            numbers = numbers.substring(1);
            number_list = numbers.split(delimiters);
            number_list[0] = "-" + number_list[0];
        }
        else{
            number_list = numbers.split(delimiters);
        }
        int sum_numbers = 0;
        boolean found_negative = false;
        String negatives = "";

        for(int index=0; index<number_list.length; index++) {
            int number = Integer.parseInt(number_list[index]);

            // if any number is negative, throwing NegativesNotAllowedException
            if(number < 0){
                if(! found_negative){
                    found_negative = true;
                    negatives = number_list[index];
                    continue;
                }
                negatives += "," + number_list[index];
                continue;
            }

            // if any number is greater than 1000, ignoring it
            if(number > 1000) continue;

            sum_numbers += number;
        }

        if(found_negative) {
            throw new NegativesNotAllowedException("negatives not allowed - [" + negatives + "]");
        }

        return sum_numbers;
    }

    private static ArrayList<String> getDefaultDelimiter(String numbers) {
        /** Takes a string of numbers and returns the default delimiter and
         *  cleaned string of numbers.
         *  Default delimiter would be of pattern - "//[delimiter]\n[numbers…]"
         *  Ex: "//;\n1;2" - default delimiter is ';'
         *      "//[***]\n1***2" - default delimiter is '***'
         *  Args
         *      numbers (String) - string of numbers separated by delimiter
         *  Returns
         *      ArrayList(String) - default delimiter used to split the numbers
         *                          and cleaned numbers without the delimiter identifier
         */

        ArrayList<String> result = new ArrayList<>();

        String match_multiple_delimiter = "(^//\\[(.|\n)+?\\]\n(\\d|-)|";
        String match_single_delimiter = "^//(.|\n)+?\n(\\d|-))";
        String match_combined_delimiter = match_multiple_delimiter + match_single_delimiter;
        
        Pattern pattern = Pattern.compile(match_combined_delimiter, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(numbers);

        if(matcher.find()) {
            String delimiter = matcher.group(0);
            // the above group would end with a digit, so removing
            // it to get our required pattern
            delimiter = delimiter.substring(0,delimiter.length()-1);
            // before moving forward, we calculate our actual set
            // of numbers
            String actual_numbers = numbers.substring(delimiter.length());

            if(delimiter.endsWith("]\n")){
                // follows multi delimiter pattern
                delimiter = delimiter.substring(3, delimiter.length()-2);
            }else {
                // follows single delimiter pattern
                delimiter = delimiter.substring(2, delimiter.length()-1);
            }
            
            result.add(delimiter);
            result.add(actual_numbers);
        }
        return result;
    }

    public static int getCalledCount() {
        /** This method returns the no. of calls to above Add method. */
        return count_invokes_to_add;
    }
}

class NegativesNotAllowedException extends Exception {
    NegativesNotAllowedException(String message) {
        super(message);
    }
}
