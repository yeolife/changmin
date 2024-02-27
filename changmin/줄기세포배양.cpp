#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;
using tiii = tuple<int, int, int>;

int board[351][351][2]; // 첫번째는 확정된 세포, 두번째는 임시로 배양한 것을 계산해서 첫번째로 옮김
bool visited[351][351];
int dx[4] = { -1, 1, 0, 0 };
int dy[4] = { 0, 0, -1, 1 };

int main() {
	ios::sync_with_stdio(0); cin.tie(NULL);

	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		int n, m, k, ans = 0;
		queue<tiii> q;

		cin >> n >> m >> k;

		// 1. 입력
		memset(board, -1, sizeof(board));
		for (int i = 150; i < n + 150; i++) {   // 300초의 절반인 150이 최대 배양길이
			for (int j = 150; j < m + 150; j++) {
				cin >> board[i][j][1];

				if (board[i][j][1] != 0) {
					q.push({ board[i][j][1], i, j });
					board[i][j][1] *= 2; // 비활성 + 삶
				}
				else board[i][j][1] = -1;
			}
		}

    // 2. k 시간만큼 배양
		for (int i = 0; i < k; i++) {
			int sz = q.size(); // 활성 + 비활성된 세포의 수

			queue<pii> tq;
			for (int i = 0; i < sz; i++) {
				int dist, x, y;
				tie(dist, x, y) = q.front();
				q.pop();

        // 3. 현재 시간에 활성화 상태가 된다면
				if (board[x][y][1] == dist) { 
					for (int j = 0; j < 4; j++) {
						int nx = x + dx[j];
						int ny = y + dy[j];

						if (board[nx][ny][1] != -1) continue;

            // 3-1. 같은 시간에 동시 배양이면 큰 세포가 차지
						if (board[nx][ny][0] < dist) {
							if (board[nx][ny][0] == -1)
								tq.push({ nx, ny });

							board[nx][ny][0] = dist;
						}
					}
				}
        
				board[x][y][1]--;

        // 4. 다음 시간에도 살아남을 수 있으면 큐에 담음
				if (board[x][y][1])
					q.push({ dist, x, y });
			}

      // 5. 임시로 배양된 세포를 첫번째 배열에 확정함
			while (!tq.empty()) {
				int nx, ny, ndist;
				tie(nx, ny) = tq.front();
				tq.pop();

				ndist = board[nx][ny][0];
				board[nx][ny][1] = ndist * 2;
				q.push({ ndist, nx, ny });
			}
		}

		ans = q.size();

		cout << '#' << t << ' ' << ans << '\n';
	}

	return 0;
}
