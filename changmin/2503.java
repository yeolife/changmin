import java.io.*;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int n = 0, ans = 0;
	private static int[][] minsu = new int[101][3];
	private static int[] balls = new int[101];
	private static int[] strikes = new int[101];
	private static boolean[] visited = new boolean[10];
	
	
	private static boolean check(int sum) {
		int youngsu[] = new int[3];
		
		for(int i = 0; i < 3; i++) {
			youngsu[i] = sum % 10;
			sum /= 10;
		}
		
		for(int i = 0; i < n; i++) {
			int strike = 0, ball = 0;
			
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					if(youngsu[j] == minsu[i][k] && j == k) // 스트라이트
						strike++;
					else if(youngsu[j] == minsu[i][k]) // 볼
						ball++;
				}
			}
			
			if(strikes[i] != strike || balls[i] != ball)
				return false;
		}
		
		return true;
	}
	
	// 현재 생성된 순열이 모두 strike라고 가정하고 완탐.
	private static void dfs(int sum, int depth) throws IOException {
		if(depth >= 1000) { // 3자리를 넘어서면 안됨
			if(check(sum)) // 모순 확인
				ans++;
	
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			
			dfs(sum + (depth * i), depth * 10);
			
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int quest = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 3; j++) {
				minsu[i][j] = quest % 10;
				quest /= 10;
			}
			
			strikes[i] = Integer.parseInt(st.nextToken());
			balls[i] = Integer.parseInt(st.nextToken());
		}
	
		dfs(0, 1);
		
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}
}
