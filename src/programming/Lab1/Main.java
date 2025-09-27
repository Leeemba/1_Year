import static java.lang.Math.*;

public class Main {
    public static void main(String[] args){
        short[] c = new short[14]; //Объявление и инициализация массивов
        float[] x = new float[20];
        double[][] k = new double[14][20];

        for (int i = 0; i < 14; i++) {
            c[i]=(short)(i+2);
        }
        for (int j = 0; j < 20; j++) {
            x[j] =(float) (random()*7.0-2.0);
        }

        for(int i = 0; i < 14; i++) {
            for(int j = 0; j < 20; j++) {
                if(c[i] == 4) {
                    k[i][j]=sin(pow(log(abs(x[j])),(((double)3/4)/log(abs(x[j])))));
                }
                else if (c[i] == 3 ||
                        c[i] == 5 ||
                        c[i] == 7 ||
                        c[i] == 8 ||
                        c[i] == 10 ||
                        c[i] == 13 ||
                        c[i] == 14) {
                    k[i][j]=pow(exp(1),(pow(pow(x[j]/3,2)-1,3)));
                }
                else{
                    k[i][j]=pow(exp(1),asin(pow(exp(1),pow(-1*(PI/2*(abs(x[j]))),x[j]/3))));
                }
            }
        }
        for(int i = 0; i < 14; i++) {
            for(int j = 0; j < 20; j++) {
                System.out.printf("%10.5f |", k[i][j]);
                System.out.print("");
            }
            System.out.println();
        }

    }
}



