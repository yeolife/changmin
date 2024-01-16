#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using pii = pair<int, int>;
using tiii = tuple<int, int, int>;
const int MOD = 1e9+7;

int n, m, ans = 1e9;
int arr[300001];

bool pram(int mid) {
    if(arr[m-1] < mid || mid == 0) return true;

    int cnt = 0;

    for(int i = 0; i < m; i++) {
        // arr[i]/mid개로 사람들한테 배분.
        // 만약에 모든 사람한테 배분 가능하면 성공
        cnt += arr[i] / mid;

        if(arr[i] % mid > 0) cnt++;
    }

    if(cnt > n) 
        return false;
    else {
        ans = min(ans, mid);
        return true;
    }

    return false;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(NULL);

    cin >> n >> m;

    for(int i = 0; i < m; i++)
        cin >> arr[i];

    sort(arr, arr+m);

    int lo = 0, hi = 1e9+1;
    while(lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        
        if(pram(mid))
            hi = mid;
        else
            lo = mid;
    }

    cout << ans;

    return 0;
}
