import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, ret=Integer.MAX_VALUE;
    static int arr[];
    static List<Integer> list[];
    static boolean visited[], check[];
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n];
        list = new ArrayList[n];
        visited = new boolean[n];
        check = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            list[i] = new ArrayList<>();
        }

        int cnt=0;
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            for(int j=0;j<cnt;j++) {
                int tmp = Integer.parseInt(st.nextToken());
                list[i].add(tmp-1);
            }
        }

        combi(0,0);

        if(ret==Integer.MAX_VALUE) {
            ret = -1;
        }

        System.out.println(ret);

    }

    public static void combi (int start, int depth) {
        int gCnt=0;
        int aTeam=0, bTeam=0;
        Arrays.fill(check, false);
        // team 분리
        for(int i=0;i<n;i++) {
            if(visited[i]) aTeam+=arr[i];
            else bTeam+=arr[i];
        }
        // 연결 되어 있는 지 check
        for(int i=0;i<n;i++) {
            if(!check[i]) {
                bfs(i, visited[i]);
                gCnt++;
            }
        }
        if(gCnt==2) {
            ret = Math.min(ret, Math.abs(aTeam-bTeam));
        }

        for(int i=start;i<n;i++) {
            if(!visited[i]) {
                visited[i] = true;
                combi(i, depth+1);
                visited[i] = false;
            }
        }
    }

    public static void bfs(int u, boolean team) {
        q = new LinkedList<>();
        q.add(u);
        check[u] = true;

        while(!q.isEmpty()) {
            int x = q.poll();

            for(int i=0;i<list[x].size();i++) {
                int next = list[x].get(i);
                if(!check[next] && visited[next]==team) {
                    q.add(next);
                    check[next]=true;
                }
            }
        }
    }

}