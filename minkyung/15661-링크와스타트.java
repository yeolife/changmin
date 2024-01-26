import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int arr[][];
    static boolean visited[];
    static int ret = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n]; // 2차원 배열 (입력값)
        visited = new boolean[n]; // 방문 체크 배열

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<n;i++) {
            combi(0, 0, i); // 조합 활용
        }

        System.out.print(ret); // 최종 결과 출력

    }

    public static void combi(int start, int depth, int r) {
        if(depth==r) { // r : 몇 개를 뽑을 지
            int sTeam=0, lTeam=0; // 스타트 팀, 링크 팀 
            for(int i=0;i<n-1;i++) { 
                for(int j=i+1;j<n;j++) { // 어짜피 i=j 이면, 0 이므로 i+1 부터
                    // true 랑 false 로 구분 해서 나눔
                    if(visited[i] == true && visited[j]==true) { 
                        sTeam += arr[i][j] + arr[j][i];
                    }
                    else if(visited[i]==false && visited[j]==false) {
                        lTeam += arr[i][j] + arr[j][i];
                    }
                }
            }
            // 차이 중 최소값
            ret = Math.min(ret, Math.abs((sTeam-lTeam)));
            return;
        }

        for(int i=start;i<n;i++) {
            if(!visited[i]) {
                visited[i] = true;
                combi(i+1, depth+1, r);
                visited[i] = false;
            }
        }

    }



}