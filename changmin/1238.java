import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class node implements Comparable<node> {
	int dist, cur;
	
	public node(int dist, int cur) {
		this.dist = dist;
		this.cur = cur;
	}
	
	@Override
    public int compareTo(node o) {
        return this.dist - o.dist;
    }
}

public class Main {
	private static int dp[] = new int[1001], ret[] = new int[1001];
	private static int root[][] = new int[1001][1001];
	private static ArrayList<Integer>[] child = new ArrayList[1001];
	
	private static void dijkstra(int n, int k) {
		Arrays.fill(dp, 1000000000);
		
		dp[n] = 0;
		
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(0, n));
		
		while(!pq.isEmpty()) {
			node tmp = pq.poll();
			int dist = tmp.dist, cur = tmp.cur;
						
			if(n != k && cur == k) return;
			
			for(int i = 0; i < child[cur].size(); i++) {
				int next = child[cur].get(i);
				
				if(dp[next] > dist + root[cur][next]) {
					dp[next] = dist + root[cur][next];
					pq.offer(new node(dp[next], next));
				}
			}
		}
	}
	 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n, m, k;
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= n; i++)
			child[i] = new ArrayList<>();
		
		int a, b, c;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			root[a][b] = c;
			child[a].add(b);
		}
		
		for(int i = 1; i <= n; i++) {
			dijkstra(i, k);
			
			if(i != k)
				ret[i] += dp[k];
			else {
				for(int j = 1; j <= n; j++)
					ret[j] += dp[j];
			}
		}
		
		int ans = -1;
		for(int i = 1; i <= n; i++)
			ans = Math.max(ans, ret[i]);
		
		System.out.print(ans);
	}
}
