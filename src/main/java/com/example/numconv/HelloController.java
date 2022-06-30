package com.example.numconv;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;

public class HelloController implements Initializable {

    @FXML
    private ComboBox basein;

    @FXML
    private ComboBox baseout;

    @FXML
    private Button button;

    @FXML
    private TextField numIn;

    @FXML
    private TextField numout;

    @FXML
    void action(ActionEvent event) {

        if(check(numIn.getText(),Integer.parseInt(basein.getValue().toString()))){
            String a=convDecToBaseOut( convBaseInToDec( numIn.getText().toLowerCase(Locale.ROOT) , basein.getValue().toString() ) , baseout.getValue().toString());
            numout.setText(a);
        }else{
            System.out.println("error: wrong input");
        }



    }

    public static boolean check(String num, int base){
        boolean check=true;
        for (int i = 0; i < num.length(); i++) {
            int part = CharToNumber(num.charAt(i));

            if(!(part < base)){
                /*
                *ako ima chislo razli4no ot celite chislata 1 do f vru6ta false
                 */
                check = false;
            }
        }
        return check;
    }

    public String convDecToBaseOut(int dec, String b){
        int base = Integer.parseInt(b);
        Stack<Integer> RawResult = new Stack<>();

        /*
        * im using stack because it is reversing the order, so I can read bottom to top later
        * while the base is higher than the number we divide and add the leftover in the stack
        * the leftover is always smaller than the base for obvious reasons
         */

        do {
            RawResult.push(dec%base);
            dec/=base;
        }while(dec>base);
        RawResult.push(dec);
        /*
        * now we just add the string together
        * im just peeking then popping the stack
         */
        String result="";
        while(!RawResult.isEmpty()){
            result+=NumberToString(RawResult.peek());
            RawResult.pop();
        }
        return result;
    }

    public int convBaseInToDec(String num,String b){
        String a=num;
        int base=Integer.parseInt(b);
        
        int res=0;
        for (int i = 0; i < a.length(); i++) {
            res += CharToNumber(a.charAt(a.length()-i-1))*Math.pow(base,i) ;

            /*
            *  getting the numbers back to front and
            *  multiplying them by the base by the power of i
            *  and adding them up in the result value
             */
        }
        return res;
    }

    public static int CharToNumber(char a){
        switch (a){
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'a': return 10;
            case 'b': return 11;
            case 'c': return 12;
            case 'd': return 13;
            case 'e': return 14;
            case 'f': return 15;
            default:
                System.out.println("error: wrong input number source: char to number ");
                return 100;
            /*
            * napraveni sa s case za po lesna proverka za validnost
             */
        }
    }

    public static String NumberToString(int a){
        switch (a){
            case 0: return "0";
            case 1: return "1";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
            case 5: return "5";
            case 6: return "6";
            case 7: return "7";
            case 8: return "8";
            case 9: return "9";
            case 10: return "a";
            case 11: return "b";
            case 12: return "c";
            case 13: return "d";
            case 14: return "e";
            case 15: return "f";


            default:
                System.out.println("error: wrong input number source: number to string ");
                return "0";
            /*
             * napraveni sa s case za po lesna proverka za validnost
             */
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        basein.getItems().removeAll(basein.getItems());
        basein.getItems().addAll("2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16");
        basein.getSelectionModel().select("2");
        baseout.getItems().removeAll(baseout.getItems());
        baseout.getItems().addAll("2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16");
        baseout.getSelectionModel().select("2");
    }

}
