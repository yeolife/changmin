import java.util.*;
import java.io.*;

public class Main {
    static int L, C;
    static String[] letters;
    static StringBuilder result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        result = new StringBuilder();
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        letters = new String[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            letters[i] = st.nextToken();
        }

        Arrays.sort(letters);
        for (int i = 0; i < C; i++) {
            dfs(i, "");
        }

        System.out.println(result);
        br.close();
    }

    static void dfs(int n, String s) {
        s = s + letters[n];

        if (s.length() == L) {
            // letterCheck()가 false이면 sortedCheck()는 미실행
            if (letterCheck(s) && sortedCheck(s)) {
                result.append(s).append("\n");
            }
        }

        for (int i = n + 1; i < C; i++) {
            dfs(i, s);
        }
    }

    // 최소 한개 이상의 모음, 두 개의 이상의 자음으로 구성되어 있는지 체크
    static boolean letterCheck(String s) {
        int moumCnt = 0;
        int zaumCnt = 0;
        List<Character> moums = List.of('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < s.length(); i++) {
            if (moums.contains(s.charAt(i)))
                moumCnt++;
            else
                zaumCnt++;
        }

        return moumCnt >= 1 && zaumCnt >= 2;
    }

    // 알파벳이 정렬되어 있는지 체크
    static boolean sortedCheck(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}