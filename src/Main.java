import jdk.jfr.Unsigned;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args){
        System.out.println(getIntegersFromList(Arrays.asList(154, 2, "acv", "b", 13)));               //Task1
        System.out.println(first_non_repeating_letter("goosSdgksgkoTd"));                       //Task2
        System.out.println(digital_root(1065871));                                                 //Task3
        System.out.println(count_the_pairs_for(new int[]{1, 3, 6, 2, 2, 0, 4, 5}, 5));          //Task4
        System.out.println(meeting("Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;" +
                "Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill"));                       //Task5
    }

    //    Task1
    public static List<Integer> getIntegersFromList(List<?> list){
        List<Integer> newList = new ArrayList<Integer>();
        for (Object o : list)
            if (o instanceof Integer){
                newList.add((Integer) o);
            }
        return newList;
    }

    //    Task2
    public static String first_non_repeating_letter(String string){
        String s;
        for (int i = 0; i<string.length(); i++){
            s = Character.toString(string.charAt(i));
            string = string.substring(1);
            if (string.toLowerCase().contains(s.toLowerCase())){
                string = string.replace(s.toLowerCase(), "");
                string = string.replace(s.toUpperCase(), "");
                i--;
            } else
                return s;
        }
        return "";
    }

    //    Task3
    public static int digital_root(int n){
        int sum = 0;
        do{
            if (n == 0){
                n = n + sum;
                sum = 0;
            }
            do{
                sum = sum + n%10;
                n = n/10;
            } while (n/10>0);
        } while (sum/10>0 || n>0);
        return sum;
    }

    //    Task4
    public static int count_the_pairs_for(int[] arr, int target){
        int count = 0;
        boolean check;
        for (int i = 0; i<arr.length; i++){
            check = false;
            for (int j = i + 1; j<arr.length; j++){
                if (target - arr[i] == arr[j] && ! check){
                    count++;
                    check = true;
                }
                if (check && j != arr.length - 1)
                    arr[j] = arr[j + 1];
            }
            if (check)
                arr = Arrays.copyOf(arr, arr.length - 1);
        }
        return count;
    }

    //    Task5
    public static String meeting(String s){
        s = s.toUpperCase();
        String[] firstSplit = s.split(";");
        int n = s.split(";").length;
        Person[] person = new Person[n];
        for (int i = 0; i<n; i++){
            String secondSplit = firstSplit[i];
            String[] name = secondSplit.split(":");
            person[i] = new Person(name[0], name[1]);
        }
        Arrays.sort(person);
        String newS = "";
        for (Person p : person){
            newS = newS + "("+p.getLastName()+", "+p.getFirstName()+")";
        }
        return newS;
    }

    public static class Person implements Comparable<Person> {
        String firstName;
        String lastName;

        public Person(){
        }

        public Person(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName(){
            return firstName;
        }

        public void setFirstName(String firstName){
            this.firstName = firstName;
        }

        public String getLastName(){
            return lastName;
        }

        public void setLastName(String lastName){
            this.lastName = lastName;
        }

        @Override
        public int compareTo(Person o){
            int result = this.lastName.compareTo(o.lastName);
            if (result == 0){
                result = this.firstName.compareTo(o.firstName);
            }
            return result;
        }
    }

}