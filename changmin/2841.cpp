#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(NULL);

    stack<int> st[7];

    int n, p, ans = 0;
    cin >> n >> p;

    int a, b;
    for(int i = 0; i < n; i++) {
        cin >> a >> b; // 줄, 플랫

        while(!st[a].empty() && st[a].top() > b) {
            ans++;
            st[a].pop();
        }

        if(!st[a].empty() && st[a].top() == b) 
            continue;

        st[a].push(b);
        ans++;
    }

    cout << ans;

    return 0;
}
