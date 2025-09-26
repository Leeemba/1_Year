import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {

        long [] y = new long[14];

        for( int i = 0; i<y.length;i++){
            y[i] = 19 - i;

        }

        double [] x = new double[10];

        for (int j = 0; j<x.length; j++){
            x[j] = (double) (Math.random() *(4.0 - (-8.0)) +(-8.0));
        }


        double[][] p = new double[14][10];
        int [] z = {8, 10, 11, 14, 17, 18, 19};

        for( int i = 0;i<14;i++){
            for ( int j = 0;j<10; j++){
                for(int k = 0; k < z.length;k++ ){
                    if (y[i] == 16){
                        p[i][j] = cbrt(cbrt(pow((3/4 * x[j]),3)));
                    } else if( y[i] == z[k] ) {
                        p[i][j] = cbrt(pow(exp(x[j]/2),2));
                    }else {
                        p[i][j] = pow(0.25/(log1p(pow(sin(log1p(abs(x[j]))),2))),(pow(pow(sin(x[j]),2),(cbrt(x[j])*(log1p(abs(x[j]))-1))/(0.5+log1p(exp(x[j]))) )));
                    }
                }
            }
        }

        for( int i = 0;i<14;i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%10.2f |",p[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }
    }
}