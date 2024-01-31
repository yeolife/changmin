#include <bits/stdc++.h>
using namespace std;

int n, m, ans = -1;
int board[21][21];
bool visited[21][21];
bool ch[27];

int dx[4] = {0, 0, -1 ,1};
int dy[4] = {-1, 1, 0, 0};

bool OOB(int x, int y) {
    if(x >= 0 && y >= 0 && x < n && y < m) return true;
    return false;
}

void dfs(int x, int y, int depth) {
    ans = max(ans, depth);

    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(!OOB(nx ,ny)) continue;
        if(visited[nx][ny]) continue;
        if(ch[board[nx][ny]]) continue;

        visited[nx][ny] = true;
        ch[board[nx][ny]] = true;

        dfs(nx, ny, depth + 1);
        
        visited[nx][ny] = false;
        ch[board[nx][ny]] = false;
    }
}

int main() {
    cin >> n >> m;

    string str;
    for(int i = 0; i < n; i++) {
        cin >> str;
        for(int j = 0; j < str.length(); j++)
            board[i][j] = str[j] - 'A';
    }

    visited[0][0] = true;
    ch[board[0][0]] = true;

    dfs(0, 0, 1);

    cout << ans;
}
