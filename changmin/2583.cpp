#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;

int n, m, k;
bool board[101][101];
bool visited[101][101];
int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

bool OOB(int x, int y) {
    if(x >= 0 && y >= 0 && x < n && y < m) return true;
    return false;
}

int bfs(int x, int y) {
    int ret = 1;

    queue<pii> q;
    q.push({x, y});
    visited[x][y] = true;

    while(!q.empty()) {
        int nx, ny;
        tie(nx, ny) = q.front();
        q.pop();

        for(int i = 0; i < 4; i++) {
            int xx = nx + dx[i];
            int yy = ny + dy[i];

            if(!OOB(xx, yy)) continue; // 범위 확인
            if(visited[xx][yy]) continue; // 재방문 확인
            if(board[xx][yy]) continue; // 모눈종이 확인

            visited[xx][yy] = true;

            q.push({xx, yy});
            ret++;
        }
    }

    return ret;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(NULL);

    cin >> n >> m >> k;

    int x1, y1, x2, y2;
    for(int i = 0; i < k; i++) {
        cin >> x1 >> y1 >> x2 >> y2;

        for(int x = y1; x < y2; x++) {
            for(int y = x1; y < x2; y++)
                board[x][y] = true;
        }
    }

    int cnt = 0;
    vector<int> ans;

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            if(visited[i][j] || board[i][j]) continue;

            ans.push_back(bfs(i, j));
            cnt++;
        }
    }

    sort(ans.begin(), ans.end());

    cout << cnt << '\n';

    for(auto& it: ans)
        cout << it << ' ';

    return 0;
}
