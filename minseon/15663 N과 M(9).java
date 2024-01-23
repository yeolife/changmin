import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] path;
    static int[] nums;
    static int[] cnt = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        path = new int[m];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            cnt[nums[i]]++;
        }

        Arrays.sort(nums);
        Solve(0);
    }

    public static void Solve(int lev) {
        if (lev == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println();

            return;
        }

        for (int i = 0; i < n; i++) {
            if (cnt[nums[i]] == 0) continue;
            if (lev == 0 && i >= 1 && nums[i - 1] == nums[i]) continue;
            if (lev >= 1 && i >= 1 && nums[i - 1] == nums[i]) continue;

            path[lev] = nums[i];
            cnt[nums[i]]--;
            Solve(lev + 1);
            path[lev] = 0;
            cnt[nums[i]]++;
        }
    }


}