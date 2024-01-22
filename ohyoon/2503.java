import java.util.*;
import java.io.*;

public class Main {

    static int[][] answer;
    static int N;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = new int[N][3];
        result = 0;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            answer[i][0] = Integer.parseInt(st.nextToken());
            answer[i][1] = Integer.parseInt(st.nextToken());
            answer[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int first = 1; first <= 9; first++) {
            for (int second = 1; second <= 9; second++) {
                if (second == first)
                    continue;
                for (int third = 1; third <= 9; third++) {
                    if (third == first || third == second)
                        continue;

                    String candidate = "" + first + second + third; // 정답 후보들
                    boolean flag = true;
                    for (int i = 0; i < answer.length; i++) {
                        int strike = 0;
                        int ball = 0;
                        String ans = String.valueOf(answer[i][0]);
                        for (int j = 0; j < 3; j++) {
                            if (candidate.charAt(j) == ans.charAt(j)) {
                                strike++;
                            } else if (ans.contains(String.valueOf(candidate.charAt(j)))) {
                                ball++;
                            }
                        }
                        if (answer[i][1] != strike || answer[i][2] != ball) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}