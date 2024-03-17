import java.util.*;
import java.io.*;

class Solution {
    static int N; // 맵 크기
    static int X; // 경사로 길이
    static int[][] map;
    static int result;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            X = sc.nextInt();
            map = new int[N][N];
            result = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            // 가로보기
            for (int i = 0; i < N; i++) {
                int prev = map[i][0];
                int flat = 1;
                boolean possible = true;
                // 한줄씩 쭉 봄
                for (int j = 1; j < N; j++) {
                    // 이전과 높이 같으면 그냥 플랫++
                    if (map[i][j] == prev) {
                        flat++;
                    } else if (prev == map[i][j] - 1) { // 이전이 더 낮으면
                        if (flat >= X) {
                            flat = 1;
                        } else {
                            possible = false;
                            break;
                        }
                    } else if (prev - 1 == map[i][j]) { // 이전이 더 높으면
                        if (j + X - 1 >= N) {
                            possible = false;
                            break;
                        }
                        for (int k = 1; k < X; k++) {
                            if (map[i][j] != map[i][j + k]) {
                                possible = false;
                                break;
                            }
                        }

                        // flat은 현재 (i,j) 이전까지 평평한 땅의 개수!!!
                        flat = 0;
                        prev = map[i][j + X - 1];
                        j += X - 1; // 맞나?
                        continue;

                    } else { // 높이 차이가 2이상 나므로 다음줄 ㄱㄱ
                        possible = false;
                        break;
                    }
                    prev = map[i][j];
                }
                if (possible) {
                    result++;
                }
//                System.out.println(i + "번째 줄 : " + result);
            }

//            System.out.println();
//            System.out.println("세로 시작");

            // 세로 보기
            for (int j = 0; j < N; j++) {
                int prev = map[0][j];
                int flat = 1;
                boolean possible = true;
                // 한줄씩 쭉 봄
                for (int i = 1; i < N; i++) {
                    // 이전과 높이 같으면 그냥 플랫++
                    if (map[i][j] == prev) {
                        flat++;
                    } else if (prev == map[i][j] - 1) { // 이전이 더 낮으면
                        if (flat >= X) {
                            flat = 1;
                        } else {
                            possible = false;
                            break;
                        }
                    } else if (prev - 1 == map[i][j]) { // 이전이 더 높으면
                        if (i + X - 1 >= N) {
                            possible = false;
                            break;
                        }
                        for (int k = 1; k < X; k++) {
                            if (map[i][j] != map[i + k][j]) {
                                possible = false;
                                break;
                            }
                        }

                        // flat은 현재 (i,j) 이전까지 평평한 땅의 개수!!!
                        flat = 0;
                        prev = map[i + X - 1][j];
                        i += X - 1; // 맞나?
                        continue;

                    } else { // 높이 차이가 2이상 나므로 다음줄 ㄱㄱ
                        possible = false;
                        break;
                    }
                    prev = map[i][j];
                }
                if (possible) {
                    result++;
                }
//                System.out.println(j + "번째 줄 : " + result);
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}