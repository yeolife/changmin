import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언
	private static ArrayList<Integer> vec = new ArrayList<Integer>();
	private static int[] arr = new int[8];
	private static boolean[] visited = new boolean[8];
	
	private static void dfs(int depth, int n, int m) throws IOException {
		if(depth == m) {
			for(int i = 0; i < m; i++)
				bw.write(vec.get(i) + " ");
			bw.write("\n");
			return;
		}
		
		int tmp = -1;
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			if(tmp == arr[i]) continue;
			
			tmp = arr[i];
			
			vec.add(arr[i]);
			visited[i] = true;
			
			dfs(depth + 1, n, m);
			
			vec.remove(vec.size()-1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr, 0, n);
		
		dfs(0, n, m);
		
		bw.flush();
		bw.close();
	}

}
