import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] path = new int[8];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Solve(0);
        bw.flush();
        bw.close();
    }

    private static void Solve(int lev) throws IOException {
        if (lev == m) {
            for (int i = 0; i < m; i++) {
                bw.write(path[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            path[lev] = i;
            Solve(lev + 1);
            path[lev] = 0;

        }
    }
}
