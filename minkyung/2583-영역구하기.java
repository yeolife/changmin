import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int m, n, k;
    static int arr[][];
    static boolean visited[][];
    static int dy[] = {-1, 1, 0,0};
    static int dx[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); //5
        n = Integer.parseInt(st.nextToken()); // 7
        k = Integer.parseInt(st.nextToken()); // 3

        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<k;i++) {
            st= new StringTokenizer(br.readLine());
            int sX = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int eX = Integer.parseInt(st.nextToken());
            int eY = Integer.parseInt(st.nextToken());

            for(int j=sY;j<eY;j++) {
                for(int l=sX;l<eX;l++) {
                    arr[l][j]=1;
                }
            }
        }

        int ret = 0;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!visited[i][j] && arr[i][j]==0) {
                    list.add(dfs(i,j));
                    ret++;
                }
            };
        }

        Collections.sort(list);

        System.out.println(ret);
        for(int i=0;i<list.size();i++) {
            System.out.print(list.get(i)+ " ");
        }

    }

    public static int dfs(int y, int x) {
        int cnt=1;
        visited[y][x] = true;
        for(int i=0;i<4;i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if(ny<0 || nx<0 || ny>=n || nx>=m) continue;
            if(!visited[ny][nx] && arr[ny][nx]==0) {
                cnt+=dfs(ny,nx);
            }
        }
        return cnt;
    }


}