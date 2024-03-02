import java.util.*;

class Solution {
    static int N, M;
    static ArrayList<Integer>[] relation;
    static int result;
    static boolean[] visited;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            result = 0;
            visited = new boolean[N + 1];
            relation = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                relation[i] = new ArrayList();
            }

            for (int i = 0; i < M; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();

                relation[from].add(to);
                relation[to].add(from);
            }

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    search(i);
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }

    static void search(int n) {
        visited[n] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int friend : relation[now]) {
                if (!visited[friend]) {
                    q.offer(friend);
                    visited[friend] = true;
                }
            }
        }

        result++;
    }
}