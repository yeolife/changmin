import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.FileInputStream;
 
class Solution
{
    static int T, n, sx, sy, max=-1, cnt=1;
    static int dx[] = {1, -1, -1, 1};
    static int dy[] = {1, 1, -1, -1};
    static int map[][], visited[];
    static List<Integer> list;
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
 
            map = new int[n][n];
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            max = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited = new int[101]; // 디저트 체크 배열
                    visited[map[i][j]] = 1; // 체크해 처음꺼
                    // 시작 인덱스 담아
                    sx = i;
                    sy = j;
                    // 재귀 돌아
                    dfs(i, j, -1, -1, 0);
                    // 어짜피 처음 체크할 꺼니까 1로 해줘 
                    cnt=1;
                }
            }
 
            System.out.println("#" + test_case + " " + max);
 
        }
    }
 
    public static void dfs(int x, int y, int px, int py, int sd) {
        if((x==0 && y==0) || (x==0 && y==n-1) || (x==n-1 && y==0) || (x==n-1 && y==n-1)) return;
 
        for(int i=sd;i<4;i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx>=0 && nx<n && ny<n && ny>=0) {
                // 이전 카페로 돌아 가는 경우
                if(nx==px && ny==py) continue;
                // 시작점 으로 도착할 경우
                if(nx==sx && ny==sy) {
                    max = Math.max(max, cnt); // 최대 찾아
                    return; // 나가 
                }
                // 먹지 않은 디저트 일 경우 만
                if(visited[map[nx][ny]]==0) {
                    visited[map[nx][ny]] = 1; // 디저트 먹어
                    cnt++; // 개수 올려
                    dfs(nx, ny, x, y, i);
                    visited[map[nx][ny]] = 0; // 다시 뱉어
                    cnt--; // 그냥 카악 퉤
                }
            }
        }
    }
}
