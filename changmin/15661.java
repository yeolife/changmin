import java.io.*;
import java.util.StringTokenizer;

public class Main {
	private static int n, ans = 1000000000;
	private static int[][] board = new int[21][21];
	private static int[] team1 = new int[21];
	private static int[] team2 = new int[21];
	private static boolean[] visited = new boolean[21];

	private static void dfs(int depth, int idx){
		if(depth <= n / 2) { // 절반만 탐색 
			int tcnt = 0;
			for(int i = 0; i < n; i++) {
				if(!visited[i])
					team2[tcnt++] = i; 
			}

			int aTeam = 0, bTeam = 0;
			for(int i = 0; i < depth; i++) {
				for(int j = i + 1; j < depth; j++)
					aTeam += board[team1[i]][team1[j]];
			}

			for(int i = 0; i < n - depth; i++) {
				for(int j = i + 1; j < n - depth; j++)
					bTeam += board[team2[i]][team2[j]];
			}

			ans = Math.min(ans, Math.abs(aTeam - bTeam));
		}
		else
			return;

		// 중복 제거 + 순번 오름차순
		for(int i = idx; i < n; i++) {
			if(visited[i]) continue;

			visited[i] = true;
			team1[depth] = i;

			dfs(depth + 1, i + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++){
				int k = Integer.parseInt(st.nextToken());

				if(i > j) board[j][i] += k;
				else board[i][j] += k;
			}
		}

		dfs(0, 0);

		System.out.print(ans);
	}
}
