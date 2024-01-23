import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int l,c;
    static char[] alphabet;
    static char[] path;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        alphabet = new char[c];
        path = new char[l];

        st = new StringTokenizer(br.readLine()); // st 또 새로 생성해줘야함!
        for(int i=0; i<c; i++){
            alphabet[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabet);

        for(int i=0; i<c; i++){
            path[0] = alphabet[i];
            if(isMo(alphabet[i]))
                solve(0,i,1,0);
            else
                solve(0,i,0,1);
        }
    }

    public static boolean isMo(char c){
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        return false;
    }

    public static void solve(int lev, int start, int cnt_mo, int cnt_ja) throws IOException {
        if (lev == l - 1) {
            if (cnt_mo >= 1 && cnt_ja >= 2) {
                sb.setLength(0);
                for (int i = 0; i < l; i++) {
                    sb.append(path[i]);
                }
                //br.close();
                sb.append("\n");
                bw.write(sb.toString());
                bw.flush();
                //bw.close();
            }
            return;
        }

        for (int i = start + 1; i < c; i++) {
            path[lev + 1] = alphabet[i];
            if (isMo(alphabet[i]))
                solve(lev + 1, i, cnt_mo + 1, cnt_ja);
            else
                solve(lev + 1, i, cnt_mo, cnt_ja + 1);
            path[lev + 1] = 0;
        }
    }
}