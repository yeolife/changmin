import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, ret;
    static int a[][];
    static int out[][];
    static boolean visited[];
    static int tmp[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        visited = new boolean[m];
        tmp = new int[m];
        out = new int [n][3];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<m;i++) {
            tmp[i]=i+1;
        }

        combi(tmp, 0,0);

        System.out.print(ret);

    }

    public static void combi(int tmp[], int start, int depth) {
        if(depth==3) {
            String str="";
            int sum=0;
            for(int i=0;i<tmp.length;i++) {
                if (visited[i]) {
                    str += (tmp[i] + " ");
                }
            }
            String cTmp[] = str.split(" ");
            for(int i=0;i<n;i++) {
                for(int j=0;j<3;j++) {
                    out[i][j] = a[i][Integer.parseInt(cTmp[j])-1];
                }
            }
            for(int i=0;i<n;i++) {
                int max=Integer.MIN_VALUE;
                for(int j=0;j<3;j++) {
                    max = Math.max(max, out[i][j]);
                }
                sum+=max;
            }
            ret = Math.max(ret, sum);
            return;
        }

        for(int i=start;i<tmp.length;i++) {
            if(!visited[i]) {
                visited[i] = true;
                combi(tmp, i+1, depth+1);
                visited[i] = false;
            }
        }
    }

}