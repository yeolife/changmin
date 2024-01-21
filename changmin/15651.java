import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<Integer> vec = new ArrayList<Integer>();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static void dfs(int n, int depth) throws IOException {
		if(depth == 0) {
			for(int i = 0; i < vec.size(); i++)
				bw.write(vec.get(i) + " ");
			bw.write("\n");
			
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			vec.add(i);
			dfs(n, depth-1);
			vec.remove(vec.size()-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
	
		dfs(n, m);
		
		bw.flush();
		bw.close();
	}
}
