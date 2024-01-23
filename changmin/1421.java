import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int n, c, w;
	private static long ans;
	private static int[] forest = new int[51];

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			forest[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(forest, 0, (int)n);
		
		for(int tree = 1; tree <= forest[n-1]; tree++) { // 가장 긴 나무의 길이까지만
			long cnt = 0, cut = 0, save_money = 0;
			
			for(int i = 0; i < n; i++) {
				cnt = forest[i] / tree;
				cut = (forest[i] % tree > 0) ? cnt : cnt-1;
				
				long money = (tree * cnt * w) - (cut * c);
				
				if(money > 0)
					save_money += money;
			}
			
			ans = Math.max(ans, save_money);
		}
		
		System.out.print(ans);
	}
}
