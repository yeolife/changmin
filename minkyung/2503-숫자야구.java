import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int ret, an, n, s, b, size;
    static List<String> aList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            an = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            String str = "", tmp = "";
            str += (an + "");

            if(i==0) {
                for (int j = 123; j <= 987; j++) {
                    int sn = 0, bn = 0;
                    if (check(j)) {
                        tmp = "";
                        tmp += (j + "");
                        for (int k = 0; k < 3; k++) {
                            if (str.charAt(k) == tmp.charAt(k)) {
                                sn++;
                            } else if (str.charAt(k) == tmp.charAt(0) || str.charAt(k) == tmp.charAt(1) || str.charAt(k) == tmp.charAt(2)) {
                                bn++;
                            }
                        }
                        if (sn == s && bn == b) {
                            aList.add(tmp);
                            size++;
                        }
                    }
                }
            } else {
                for(int j=0;j<size;j++) {
                    int sn = 0, bn = 0;
                    if (check(Integer.parseInt(aList.get(j)))) {
                        tmp = aList.get(j);
                        for (int k = 0; k < 3; k++) {
                            if (str.charAt(k) == tmp.charAt(k)) {
                                sn++;
                            } else if (str.charAt(k) == tmp.charAt(0) || str.charAt(k) == tmp.charAt(1) || str.charAt(k) == tmp.charAt(2)) {
                                bn++;
                            }
                        }
                        if (sn != s || bn != b) {
                            aList.remove(aList.get(j));
                            size--;
                            j--;
                        }
                    }
                }
            }
        }

        System.out.println(size);

    }

    public static boolean check(int a) {
        String str = "";
        str += (a + "");

        if ((str.charAt(0) == str.charAt(1)) || (str.charAt(1) == str.charAt(2)) || (str.charAt(0) == str.charAt(2))) {
            return false;
        } else {
            for(int i=0;i<str.length();i++) {
                if(str.charAt(i)=='0') {
                    return false;
                }
            }
            return true;
        }
    }

}