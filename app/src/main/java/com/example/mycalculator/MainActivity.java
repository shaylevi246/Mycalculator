package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SyncStatusObserver;
import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private int total = 0;
    private String bbb="";
    TextView scoreTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTV = findViewById(R.id.tview);

        Button btn_one = findViewById(R.id.one);
        Button btn_two = findViewById(R.id.two);
        Button btn_three = findViewById(R.id.three);
        Button btn_four = findViewById(R.id.four);
        Button btn_five = findViewById(R.id.five);
        Button btn_six = findViewById(R.id.six);
        Button btn_seven = findViewById(R.id.seven);
        Button btn_eight = findViewById(R.id.eight);
        Button btn_nine = findViewById(R.id.nine);
        Button btn_zero = findViewById(R.id.zero);
        Button btn_clear = findViewById(R.id.clear);
        Button btn_delete = findViewById(R.id.delete);
        Button btn_divider = findViewById(R.id.divider);
        Button btn_multiplier = findViewById(R.id.multy);
        Button btn_substruct = findViewById(R.id.substruct);
        Button btn_add = findViewById(R.id.adding);
        Button btn_result = findViewById(R.id.result);
        Button btn_decimel = findViewById(R.id.decimel);

        btn_one.setOnClickListener(new MyTextListener());
        btn_two.setOnClickListener(new MyTextListener());
        btn_three.setOnClickListener(new MyTextListener());
        btn_four.setOnClickListener(new MyTextListener());
        btn_five.setOnClickListener(new MyTextListener());
        btn_six.setOnClickListener(new MyTextListener());
        btn_seven.setOnClickListener(new MyTextListener());
        btn_eight.setOnClickListener(new MyTextListener());
        btn_nine.setOnClickListener(new MyTextListener());
        btn_zero.setOnClickListener(new MyTextListener());
        btn_multiplier.setOnClickListener(new MyTextListener());
        btn_substruct.setOnClickListener(new MyTextListener());
        btn_divider.setOnClickListener(new MyTextListener());
        btn_decimel.setOnClickListener(new MyTextListener());
        btn_add.setOnClickListener(new MyTextListener());
        btn_delete.setOnClickListener(new MyTextListener());
        btn_clear.setOnClickListener(new MyTextListener());
        btn_result.setOnClickListener(new MyResultListener());


    }
    private class MyTextListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String aaa = "0";
            switch (v.getId()){
                case R.id.one: aaa = "1";
                                break;
                case R.id.two: aaa = "2";
                               break;
                case R.id.three: aaa = "3";
                                break;
                case R.id.four: aaa = "4";
                                break;
                case R.id.five: aaa = "5";
                                break;
                case R.id.six: aaa = "6";
                               break;
                case R.id.seven: aaa = "7";
                                break;
                case R.id.eight: aaa = "8";
                                  break;
                case R.id.nine: aaa = "9";
                                 break;
                case R.id.zero: aaa = "0";
                                 break;
                case R.id.divider: aaa = "/";
                                   break;
                case R.id.multy: aaa = "x";
                                 break;
                case R.id.substruct: aaa = "-";
                                      break;
                case R.id.decimel: aaa = ".";
                                   break;
                case R.id.adding: aaa = "+";
                                  break;
                case R.id.clear: aaa = "";
                                  bbb = "";
                                  break;
                case R.id.delete: aaa = "";
                                  String temp = bbb.substring(0,bbb.length()-1);
                                  bbb = temp;
                                  break;
                default:break;
            }
            bbb = bbb + aaa;
            scoreTV.setText(bbb);
        }
    }

    private static void Update_Symbols(ArrayList<String> numbers, ArrayList<String> symbols, String sign){
        int location = symbols.indexOf(sign);
        String a = numbers.get(location+1);
        String b = sign.substring(1);
        String c = b + a;
        numbers.remove(location+1);
        numbers.add(location+1, c);
        symbols.remove(location);
        String temp = sign.substring(0,sign.length()-b.length());
        symbols.add(location, temp);
    }
   /* private static void Update_Complex_Symbols(ArrayList<String> numbers, ArrayList<String> symbols, String sign){
        int location = symbols.indexOf(sign);
        String a = numbers.get(location+1);
        String b = "-." + a;
        numbers.remove(location+1);
        numbers.add(location+1, b);
        symbols.remove(location);
        String temp = sign.substring(0,sign.length()-2);
        symbols.add(location, temp);
    }*/

    private class MyResultListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ArrayList<String> array_of_numbers = new ArrayList<String>();
            ArrayList<String> array_of_Symbols = new ArrayList<String>();
            String[] array_of_Numbers1 = bbb.split("[+-/|x.]+");
            String[] array_of_Symbol1 = bbb.split("[0-9]+");
            for (int i = 0 ; i< array_of_Numbers1.length ; i++){
                array_of_numbers.add(array_of_Numbers1[i]);
            }
            for (int i = 0 ; i< array_of_Symbol1.length ; i++){
                array_of_Symbols.add(array_of_Symbol1[i]);
            }
            if((array_of_numbers.isEmpty())&&(array_of_Symbols.isEmpty())){
                return;
            }
            if(array_of_numbers.isEmpty()){
                scoreTV.setText("syntax ERROR");
                return;
            }
            if(array_of_Symbols.isEmpty()) {
                    scoreTV.setText(bbb);
                    return;
            }
            if(array_of_Symbols.get(0).equals("")){           //checks if the array starts with empty place,if it is,
                array_of_Symbols.remove(0);             //that means that there are no signs before the first number
            }
            else {
                array_of_numbers.remove(0);
                if((array_of_Symbols.get(0).equals("-"))||(array_of_Symbols.get(0).equals("."))||(array_of_Symbols.get(0).equals("-."))
                        ||(array_of_Symbols.get(0).equals("+"))||(array_of_Symbols.get(0).equals("+."))){
                    String first_letter= array_of_numbers.get(0);    //upper line checks if the first number is negative number or with decimal point
                    String sign = array_of_Symbols.get(0);           //that means, there are signs before the first number
                    String combine = sign + first_letter;
                    array_of_numbers.remove(0);
                    array_of_numbers.add(0,combine);
                    array_of_Symbols.remove(0);
                }else{
                    scoreTV.setText("syntax ERROR"); //there are unwelcome signs before first number
                }
            }

            while(array_of_Symbols.contains(".")){     //checks if there is a decimal point and adjust it to its number
                int location = array_of_Symbols.indexOf(".");
                String a = array_of_numbers.get(location);
                if(location+1>array_of_numbers.size()-1){
                    array_of_Symbols.remove(location);
                }
                else {
                    String b = array_of_numbers.get(location + 1);
                    String c = a + "." + b;
                    array_of_numbers.remove(location + 1);
                    array_of_numbers.remove(location);
                    array_of_numbers.add(location, c);
                    array_of_Symbols.remove(location);
                }
            }
            int counter_of_signs_without_decimal = 0;
            for(String a : array_of_Symbols){
                if(a != "."){
                    counter_of_signs_without_decimal++;
                }
            }
            if (array_of_numbers.size() <= counter_of_signs_without_decimal){
                scoreTV.setText("syntax ERROR");
                return;
            }


            while(array_of_Symbols.contains("x-")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"x-");
            }
            while(array_of_Symbols.contains("/-")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"/-");
            }
            while(array_of_Symbols.contains("--")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"--");
            }
            while(array_of_Symbols.contains("+-")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"+-");
            }
            while(array_of_Symbols.contains("x.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"x.");
            }
            while(array_of_Symbols.contains("/.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"/.");
            }
            while(array_of_Symbols.contains("-.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"-.");
            }
            while(array_of_Symbols.contains("+.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"+.");
            }
            while(array_of_Symbols.contains("x-.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"x-.");
            }
            while(array_of_Symbols.contains("/-.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"/-.");
            }
            while(array_of_Symbols.contains("--.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"--.");
            }
            while(array_of_Symbols.contains("+-.")){
                Update_Symbols(array_of_numbers,array_of_Symbols,"+-.");
            }
            for(String a : array_of_Symbols){
                if(a.length() != 1){
                    scoreTV.setText("syntax ERROR");
                    return;
                }
            }
            for(String a : array_of_numbers){
                int counter_of_decimal_in_number = 0;
                for(int i=0; i< a.length();i++){
                    if(a.charAt(i) == '.'){
                        counter_of_decimal_in_number++;
                    }
                }
                if(counter_of_decimal_in_number>1){
                    scoreTV.setText("syntax ERROR");
                    return;
                }
            }


            int count = array_of_Symbols.size();
            float temp_result = count;
            int i=0;

            while(count>0){
                if ((array_of_Symbols.get(i).equalsIgnoreCase("x"))||(array_of_Symbols.get(i).equalsIgnoreCase("/"))) {
                    float a = Float.parseFloat(array_of_numbers.get(i));
                    float b = Float.parseFloat(array_of_numbers.get(i + 1));
                    if (array_of_Symbols.get(i).equalsIgnoreCase("x")){
                        temp_result = a*b;
                    }else {
                        if(b!=0) {
                            temp_result = a / b;
                        }
                        else {
                            scoreTV.setText("syntax ERROR");
                            return;
                        }
                    }
                    array_of_numbers.remove(i+1);
                    array_of_numbers.remove(i);
                    array_of_numbers.add(i,String.valueOf(temp_result));
                    array_of_Symbols.remove(i);
                    count--;
                }
                else{
                    if ((array_of_Symbols.get(i).equalsIgnoreCase("-"))||(array_of_Symbols.get(i).equalsIgnoreCase("+"))){
                        i++;
                        count--;
                    }
                }
            }
            count = array_of_Symbols.size();
            temp_result= count;
            i=0;
            while(count>0) {
                if ((array_of_Symbols.get(i).equalsIgnoreCase("-")) || (array_of_Symbols.get(i).equalsIgnoreCase("+"))) {
                    float a = Float.parseFloat(array_of_numbers.get(i));
                    float b = Float.parseFloat(array_of_numbers.get(i + 1));
                    if (array_of_Symbols.get(i).equalsIgnoreCase("-")) {
                        temp_result = a - b;
                    } else {
                        temp_result = a + b;
                    }
                    array_of_numbers.remove(i + 1);
                    array_of_numbers.remove(i);
                    array_of_numbers.add(i, String.valueOf(temp_result));
                    array_of_Symbols.remove(i);
                    count--;
                }
            }
            bbb = "";
            for (String a : array_of_numbers){
                bbb = bbb + a;
            }
            scoreTV.setText(bbb);
        }
    }

}
