import java.util.*;

/**
 * 1. 3개씩 숫자를 묶어가며 16진수 만들기
 * 2. 10진수로 변환하여 우선순위큐 추가
 */
class Solution {
    static String[] arr;
    static int N, K;
    static int sideNum;
    static Set<String> set;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            K = sc.nextInt();
            sideNum = N / 4;
            sc.nextLine();
            arr = sc.nextLine().split("");
            set = new TreeSet<>(Comparator.reverseOrder());

            // 회전에 따라 시작지점 정해주기
            for (int start = 0; start < sideNum; start++) {
                // 각 숫자의 처음 지점 정해주기
                for (int i = start; i < N; ) {
                    StringBuilder sb = new StringBuilder(sideNum);
                    // 숫자 만들기
                    for (int j = 0; j < sideNum; j++) {
                        sb.append(arr[i++ % N]);
                    }
                    set.add(sb.toString());
                }
            }

            LinkedList<String> list = new LinkedList<>(set);
            list.sort(Comparator.reverseOrder());
            System.out.println("#" + test_case + " " + toHexadecimal(list.get(K - 1)));
        }
    }

    static int toHexadecimal(String num) {
        int ret = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            char cur = num.charAt(i);
            int multiple = 0;

            if (cur < 'A') {
                multiple = Integer.parseInt(String.valueOf(cur));
            } else {
                switch (cur) {
                    case 'A':
                        multiple = 10;
                        break;
                    case 'B':
                        multiple = 11;
                        break;
                    case 'C':
                        multiple = 12;
                        break;
                    case 'D':
                        multiple = 13;
                        break;
                    case 'E':
                        multiple = 14;
                        break;
                    case 'F':
                        multiple = 15;
                        break;
                }
            }
            ret += (int) Math.pow(16, num.length() - i - 1) * multiple;
        }
        return ret;
    }

}