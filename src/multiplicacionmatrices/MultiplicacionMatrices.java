package multiplicacionmatrices;

public class MultiplicacionMatrices {
    public static int matrixChainMultiplyR(int A[],int s[][], int i, int j){
        if(i==j){
            return A[i];
        }
        if((i+1)==j){
            return (A[i]*A[j]);
        }
        int b=matrixChainMultiplyR(A,s,i,s[i][j]);
        int c=matrixChainMultiplyR(A,s,s[i][j]+1,j);
        return (b*c);
    }
    public static Par<int[][], int[][]> chainOrder(int p[]) {
        int n = p.length - 1;
        int m[][] = new int[n + 1][n + 1];
        int s[][] = new int[n + 1][n + 1];
        int q = 0;
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    q = m[i][k] + m[k + 1][j] + (p[i - 1] * p[k] * p[j]);
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        return new Par<int[][], int[][]>(m, s);
    }

    public static String printOptimalParens(int s[][], int i, int j) {
        if (i == j) {
            return "A" + i;
        } else {
            return "("
                    + printOptimalParens(s, i, s[i][j])
                    + printOptimalParens(s, s[i][j] + 1, j)
                    + ")";
        }
    }
    public static void main(String[] args) {
        int p[] = {30, 35, 15, 5, 10, 20, 25};
        Par<int[][], int[][]> ar;
        ar = chainOrder(p);

        System.out.println(printOptimalParens(ar.snd, 1, 6));
    }  
}
class Par<S, T> {

    public S fst;
    public T snd;

    public Par(S s, T t) {
        this.fst = s;
        this.snd = t;
    }
}