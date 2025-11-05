package Sentence;

import Interfaces.SentenceBrace;

import java.io.IOException;

public class Sentence implements SentenceBrace {





    @Override
    public void capitalized(String str) {
        if (str.isBlank()){
            try {
                throw new IOException();
            }catch (IOException e){
                System.err.println("Ошибка строка не может быть пустой");
            }
        }else{
            System.out.print(str.substring(0,1).toUpperCase()+str.substring(1));
        }

    }

    @Override
    public void dot() {
        System.out.print(".\n");
    }

    @Override
    public void dotWithoutNL() {
        System.out.print(".");
    }
}
