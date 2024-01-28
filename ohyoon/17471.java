import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] population;
    static ArrayList<Integer>[] connect;
    static boolean[] visited;
    static int[] team1;
    static int[] team2;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N];
        connect = new ArrayList[N];
        visited = new boolean[N];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            connect[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                connect[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        // i == team1 인원
        for (int i = 1; i <= N / 2; i++) {
            team1 = new int[i];
            dfs(0, 0);
        }

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }
        System.out.println(result);
        br.close();
    }

    static boolean bfs(int n, int[] team) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        boolean[] check = new boolean[N];
        check[n] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : connect[cur]) {
                if (!check[next] && arrayContains(team, next)) {
                    q.offer(next);
                    check[next] = true;
                }
            }
        }

        for (int j : team) {
            if (!check[j])
                return false;
        }
        return true;
    }

    static void dfs(int depth, int start) {
        if (depth == team1.length) {

            // team2 구성
            team2 = new int[N - team1.length];
            int idx = 0;
            for (int i = 0; i < N; i++) {
                if (!arrayContains(team1, i)) {
                    team2[idx++] = i;
                }
            }

            if (!bfs(team1[0], team1))
                return;

            if (!bfs(team2[0], team2))
                return;

            int difference = Math.abs(sum(team1) - sum(team2));
            result = Math.min(result, difference);

            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                team1[depth] = i;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }

    }

    static int sum(int[] team) {
        int sum = 0;
        for (int i : team) {
            sum += population[i];
        }
        return sum;
    }

    static boolean arrayContains(int[] arr, int a) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == a) {
                return true;
            }
        }
        return false;
    }

}