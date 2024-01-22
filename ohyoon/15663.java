import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static ArrayList<Integer> nums;
    static StringBuilder sb;
    static int[] val;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new ArrayList<>();
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        val = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }
        nums.sort(Comparator.naturalOrder());

        dfs(0);

        System.out.println(sb);
        br.close();
    }

    static void dfs(int depth) {

        if (depth == M) {
            for (int i : val) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for (int i = 0; i < N; i++) {
            int cur = nums.get(i);
            if(prev != cur && !visited[i]) {
                val[depth] = cur;
                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
                prev = cur;
            }
        }
    }

}