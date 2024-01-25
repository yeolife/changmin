import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, c, w, max;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        arr = new int[n];
        max = Integer.MIN_VALUE;

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<n;i++) {
            max = Math.max(max, arr[i]);
        }
        System.out.print(ret(arr));

    }

    public static long ret(int a[]) {
        long sum=0, maxSum=Integer.MIN_VALUE, price=0, cnt=0;
        for(int i=1;i<=max;i++) { // 왜 max 로 해두냐? 자르는 비용이 너무 클 경우 하나만 파는 게 가장 최대 니까
            sum=0;
            for(int j=0;j<a.length;j++) {
                cnt=0;
                if(a[j]>=i) {
                    if(a[j]%i==0)
                        cnt = a[j]/i-1; // 나머지 가 0 이면 몫에서 1 빼줘야 함
                        // ex) 28/2=14 이지만, 총 자른 횟수는 13회 이기 때문
                    else
                        cnt = a[j]/i;
                    price = ((a[j]/i)*w*i - (cnt*c));
                    if(price > 0) sum+=price;
                }
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

}