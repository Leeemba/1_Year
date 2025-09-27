import static java.lang.Math.*;

public class Main1_3 {
    public static void main(String[] args){

        long [] a = new long[11];

        for(int i = 0; i < a.length; i++){
            a[i] = 23 - 2*i;
        }

        float [] x = new float[11];

        for(int j = 0; j < x.length; j++){
            x[j] = (float) (random()*(2.0 - (-7.0)) +(-7.0));
        }

        float[][] q = new float[11][11];
        for(int i = 0;i<11;i++){
            for (int j = 0;j<11;j++){
                switch ((int) a[i]){
                    case 19:
                        q[i][j] = (float) exp(atan(exp(-(abs(x[j])))));
                        break;
                    case 3,9,15,17,23:
                        q[i][j] = (float) pow((PI - asin(0.2*((x[j]-2.5)/9)))/pow((cos(x[j])-2/3)/(pow((x[j]+1)/x[j],2)),3),exp(tan(x[j])));
                        break;
                    default:
                        q[i][j] = (float) pow(pow(pow(log1p(x[j]),((0.75-x[j])/2*x[j])),(0.25)*4+pow((0.5/0.5+x[j]),(2*x[j]/PI-cos(x[j])))),asin(1/exp(PI*(pow(tan(x[j]),2)+1)))*(1-asin(exp(cbrt(-(pow(tan(x[j]),2)))))) );
                }
            }
        }
        for(int i = 0;i<11;i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%10.5f |",q[i][j]);
                System.out.print("");
            }
            System.out.println();
        }



    }
}
