package calculator;

public class StringCalculator {
    public static int add(String numbers) throws NegativesNotAllowedException {
        /** Takes a string of integers, and returns their sum.
         *  Args
         *      numbers (String) - string of numbers separated by delimiter
         *  Returns
         *      (int) - sum of the numbers
         */

        // when there are no numbers
        if(numbers == "") return 0;

        // when atleast 1 number is given
        String delimiters = ",|\n";
        String default_delimiter = getDefaultDelimiter(numbers);
        
        if(default_delimiter != "") {
            if(!delimiters.contains(default_delimiter)){
                delimiters += "|" + default_delimiter;
            }
            numbers = numbers.replace("//"+default_delimiter+"\n", "");
        }
        
        String[] number_list = numbers.split(delimiters);
        int sum_numbers = 0;

        for(int index=0; index<number_list.length; index++) {
            int number = Integer.parseInt(number_list[index]);
            if(number < 0){
                throw new NegativesNotAllowedException("negatives not allowed - " + number);
            }
            sum_numbers += number;
        }

        return sum_numbers;
    }

    private static String getDefaultDelimiter(String numbers) {
        /** Takes a string of numbers and returns the default delimiter.
         *  Default delimiter would be of pattern - "//[delimiter]\n[numbers…]""
         *  Ex: "//;\n1;2" - default delimiter is ';'
         *
         *  Args
         *      numbers (String) - string of numbers separated by delimiter
         *  Returns
         *      (String) - default delimiter used to split the numbers
         */
        if(numbers.startsWith("//")) {
            int start_index = 2, end_index = numbers.indexOf("\n");
            // when the default delimiter is '\n', find its second occurrence
            if(start_index == end_index) {
                end_index = numbers.indexOf("\n", end_index+1);
            }
            return numbers.substring(start_index, end_index);
        }
        return "";
    }
}

class NegativesNotAllowedException extends Exception {
    NegativesNotAllowedException(String message) {
        super(message);
    }
}
