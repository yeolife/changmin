#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;

int n, m;
int kevin[101];
vector<int> frie[101];

void bfs(int node) {
    int ret = 0;
    queue<pii> q;
    q.push({node, 0});
  
    int visited[101] = {0, };
    visited[node] = true;

    while(!q.empty()) {
        int cur, dist;
        tie(cur, dist) = q.front();
        q.pop();

        for(int i = 0; i < frie[cur].size(); i++) {
            int next = frie[cur][i];

            if(visited[next]) continue;
            else
                ret += dist;

            visited[next] = true;
            q.push({next, dist + 1});
        }
    }

    kevin[node] = ret;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(NULL);

    cin >> n >> m;

    int a, b;
    for(int i = 0; i < m; i++) {
        cin >> a >> b;

        frie[a].push_back(b);
        frie[b].push_back(a);
    }

    for(int i = 1; i <= n; i++)
        bfs(i);
    
    int tmp = 1e9, ans = 0;
    for(int i = 1; i <= n; i++) {
        if(tmp > kevin[i]) {
            ans = i;
            tmp = kevin[i];
        }
    }

    cout << ans;

    return 0;
}
