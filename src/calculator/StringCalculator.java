package calculator;

public class StringCalculator {
    public static int add(String numbers) {
        /** Takes a string of integers, and returns their sum.
         *  Args
         *      numbers (String) - string of numbers separated by comma
         *  Returns
         *      (int) - sum of the numbers
         */

        // when there are no numbers
        if(numbers == "") return 0;

        // when atleast 1 number is given
        String delimiters = ",|\n";
        String[] number_list = numbers.split(delimiters);
        int sum_numbers = 0;

        for(int index=0; index<number_list.length; index++) {
            sum_numbers += Integer.parseInt(number_list[index]);
        }

        return sum_numbers;
    }
}
