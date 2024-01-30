import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static Queue<Integer> q;
    static int visited[] = new int[100004];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if(n==k) {
            System.out.println(0);
        }else {
            bfs(n);
        }


    }

    public static void bfs(int n) {
        q = new LinkedList<>();
        q.add(n);
        visited[n]=1;

        while(!q.isEmpty()) {
            int x = q.poll();

            for(int i=0;i<3;i++) {
                int next;
                if(i==0) {
                    next = x-1;
                }else if(i==1) {
                    next = x+1;
                }else {
                    next = 2*x;
                }

                if(next==k) {
                    System.out.println(visited[x]);
                    return;
                }

                if(next>=0 && next<100004 && visited[next]==0) { // 방문 하지 않았 다면
                    q.add(next); // next 를 큐에 넣고
                    visited[next] = visited[x]+1;
                }
            }
        }

    }

}