import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static char[] alpha = new char[16];
	private static boolean[] isMo = new boolean[27];
	private static boolean[] visited = new boolean[16];
	
	private static void dfs(int idx, int depth, String str) {
		if(depth == n) {
			int mo_cnt = 0, ja_cnt = 0;
			
			for(int i = 0; i < str.length(); i++) {
				if(isMo[str.charAt(i) - 'a']) mo_cnt++; // 모음이면
				else ja_cnt++; // 자음이면
			}
			
			if(mo_cnt >= 1 && ja_cnt >= 2)
				System.out.println(str);
			
			return;
		}
		
		for(int i = idx; i < m; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			dfs(i, depth + 1, str + alpha[i]);
			visited[i] = false;
		}
	}

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 알파벳 26개 중에서 모음만 true, 나머지는 false
		isMo['a' - 'a'] = true; isMo['e' - 'a'] = true; 
		isMo['i' - 'a'] = true; isMo['o' - 'a'] = true; 
		isMo['u' - 'a'] = true;
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++)
			alpha[i] = st.nextToken().charAt(0);

		Arrays.sort(alpha, 0, m);
		
		dfs(0, 0, "");
	}
}
