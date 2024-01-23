import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        double repeat = Math.pow(N, M);
        int[] val = new int[M];

        Arrays.fill(val, 1);

        for (int i = 0; i < repeat; i++) {
            for (int j = M - 1; j >= 0; j--) {
                if (val[j] > N) {
                    val[j] = 1;
                    val[j - 1] = val[j - 1] + 1;
                } else {
                    break;
                }
            }

            for (int num : val) {
                sb.append(num + " ");
            }
            val[M - 1] = val[M - 1] + 1;
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}