package calculator;

public class StringCalculator {
    public static int add(String numbers) {
        /** Takes a string of integers, and returns their sum.
         *  Args
         *      numbers (String) - string of numbers separated by comma
         *  Returns
         *      (int) - sum of the numbers
         */

        String[] number_list = numbers.split(",");
        
        // when the no. of numbers is 0
        if(number_list[0].length() == 0) return 0;

        int number1, number2;
        number1 = Integer.parseInt(number_list[0]);
        // when the no. of numbers is 1
        if(number_list.length == 1) return number1;

        // when the no. of numbers is greater than 1, i.e. assuming max length is 2
        number2 = Integer.parseInt(number_list[1]);
        return number1 + number2;
    }
}
