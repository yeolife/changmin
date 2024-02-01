#include <bits/stdc++.h>
using namespace std;

int n, ans = 1e9;
bool prevBoard[101][101];
int board[101][101];
bool visited[101][101];

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

void set_board(int x, int y, int flag) { // 섬에 고유번호를 부여
    queue<pair<int, int> > q;
    q.push({x, y});

    visited[x][y] = true;
    board[x][y] = flag;

    while(!q.empty()) {
        int nx, ny;
        tie(nx, ny) = q.front();
        q.pop();

        for(int i = 0; i < 4; i++) {
            int xx = nx + dx[i];
            int yy = ny + dy[i];

            if(visited[xx][yy]) continue;
            if(!prevBoard[xx][yy]) continue;

            visited[xx][yy] = true;
            board[xx][yy] = flag;
            q.push({xx, yy});
        }
    }
}

bool OOB(int x, int y) {
    if(x >= 0 && y >= 0 && x < n && y < n) return true;
    return false;
}

int check(int x, int y) { // 출발 가능한 바다인지 확인
    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(!OOB(nx, ny)) continue;

        if(board[nx][ny] >= 1) 
            return board[nx][ny];
    }

    return -1;
}

void bfs(int x, int y, int flag) {
    fill(&visited[0][0], &visited[100][101], 0);

    queue<tuple<int, int, int> > q;
    q.push({x, y, 0});
    visited[x][y] = true;

    while(!q.empty()) {
        int nx, ny, dist;
        tie(nx, ny, dist) = q.front();
        q.pop();

        if(dist >= ans) return;
        if(board[nx][ny] >= 1 && board[nx][ny] != flag) {
            ans = min(ans, dist);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int xx = nx + dx[i];
            int yy = ny + dy[i];
            
            if(!OOB(xx, yy)) continue;
            if(visited[xx][yy]) continue;
            if(board[xx][yy] == flag) continue;
            
            visited[xx][yy] = true;
            q.push({xx, yy, dist+1});
        }
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(NULL);

    cin >> n;

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++)
            cin >> prevBoard[i][j];
    }

    int cnt = 1;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(prevBoard[i][j] && !visited[i][j])
                set_board(i, j, cnt++);
        }
    }

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(!board[i][j]) {
                int country = check(i, j);

                if(country != -1)
                    bfs(i, j, country);
            }
        }
    }

    cout << ans;

    return 0;
}
