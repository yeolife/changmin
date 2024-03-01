#include <bits/stdc++.h>
using namespace std;

int n, k;
int board[21][21];
int dx[2] = { 1, 0 };
int dy[2] = { 0, 1 };

bool dfs(int x, int y, int dir, bool down, int cnt) {
	int nx = x + dx[dir];
	int ny = y + dy[dir];

	if (nx >= n || ny >= n) {
		if (down && cnt < k) return false;
		return true;
	}

	bool ret = false;
	if (down && cnt == k) // init
		ret = dfs(x, y, dir, 0, 0);
	else if (board[x][y] == board[nx][ny]) // 같음
		ret = dfs(nx, ny, dir, down, cnt + 1);
	else { // 다름
		if (down && cnt < k) return false;

		if (board[x][y] + 1 == board[nx][ny]) { // 높음
			if (cnt < k) return false;

			ret = dfs(nx, ny, dir, 0, 1);
		}
		else if (board[x][y] == board[nx][ny] + 1) // 낮음
			ret = dfs(nx, ny, dir, 1, 1);
		else // 2 이상
			return false;
	}

	return ret;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(NULL);

	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> n >> k;

		int ans = 0;
		memset(board, 0, sizeof(board));

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				cin >> board[i][j];
		}

		for (int i = 0; i < n; i++) {
			ans += dfs(0, i, 0, 0, 1);
			ans += dfs(i, 0, 1, 0, 1);
		}

		cout << '#' << t << ' ' << ans << '\n';
	}

	return 0;
}
