import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] ability;
    static int[] team1, team2;
    static boolean[] visited;
    static int gap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        gap = Integer.MAX_VALUE;

        StringTokenizer st;
        ability = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (2, 3)으로 나누는 것은 (3, 2)로 나누는 것과 같으므로 N/2까지만 해도 됨
        for (int team1Size = 1; team1Size <= N / 2; team1Size++) {

            team1 = new int[team1Size];
            team2 = new int[N - team1Size];
            dfs(-1, 0);
        }

        System.out.println(gap);

        br.close();
    }

    /**
     *
     * @param prevIdx - 중복멤버가 존재하는 팀을 만들지 않기 위한 파라미터
     * @param depth - 팀이 모두 구성되었는지 알기 위한 파라미터
     */
    static void dfs(int prevIdx, int depth) {
        if (depth == team1.length) {
            //팀2 구성해주기
            int idx = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    team2[idx++] = i;
                }
                if (idx == team2.length)
                    break;
            }

            //팀1 능력치 구하기
            int team1Ability = calcSum(team1);
            //팀2 능력치 구하기
            int team2Ability = calcSum(team2);
            // 차이 구하기
            int difference = Math.abs(team1Ability - team2Ability);
            gap = Math.min(gap, difference);

            return;
        }

        for (int i = prevIdx + 1; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                team1[depth] = i;
                dfs(i, depth + 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 팀 능력치 총 합 구하는 함수
     */
    static int calcSum(int[] team) {
        int sum = 0;

        for (int i = 0; i < team.length - 1; i++) {
            for (int j = i + 1; j < team.length; j++) {
                int m1 = team[i];
                int m2 = team[j];
                sum += (ability[m1][m2] + ability[m2][m1]);
            }
        }

        return sum;
    }
}