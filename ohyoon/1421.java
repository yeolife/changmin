import java.util.*;
import java.io.*;

public class Main {
    static int N, C, W;
    static int[] woods;
    static long max;
    static int maxLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        woods = new int[N];
        maxLen = Integer.MIN_VALUE;
        max = Long.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int len = Integer.parseInt(br.readLine());
            maxLen = Math.max(maxLen, len);
            woods[i] = len;
        }

        for (int i = 1; i <= maxLen; i++) {
            int std = i;

            long sum = 0; // 총 판매금
            for (int j = 0; j < N; j++) {
                int total = 0; // 팔 수 있는 나무 조각 개수
                int cut = 0; // 자른 횟수

                int cur = woods[j];

                // 기준 길이보다 같거나 길어서 판매 가능한 경우
                if (cur >= std) {
                    double divided = (double) cur / std;
                    total += (int) divided;
                    cut += (int) Math.ceil(divided - 1);
                }

                // 잘랐을때 더 이득이면 팔고 아니면 안 팔기
                int estimate = (std * total * W) - (cut * C);
                if (estimate > 0) {
                    sum += estimate;
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);

        br.close();
    }
}
