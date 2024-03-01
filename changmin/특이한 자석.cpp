#include <bits/stdc++.h>
using namespace std;

deque<bool> dq[4];

// 회전
void rotate(int idx, bool dir) {
	if (dir == 1) { // 시계
		dq[idx].push_front(dq[idx].back());
		dq[idx].pop_back();
	}
	else { // 반시계
		dq[idx].push_back(dq[idx].front());
		dq[idx].pop_front();
	}
}

// 영향 확인
void dfs(int prev, int cur, int lr, bool dir, int prevMag) {
	if (cur < 0 || cur > 3) return;

	int curMag = 8 - prevMag;

	if (dq[prev][prevMag] != dq[cur][curMag]) {
		dfs(cur, cur + lr, lr, !dir, prevMag);

		rotate(cur, !dir);
	}
}

int main() {
	ios::sync_with_stdio(0); cin.tie(NULL);

	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		int n, ans = 0;
		cin >> n;

		for (int i = 0; i < 4; i++) {
			dq[i].assign(8, 0);
			for (int j = 0; j < 8; j++)
				cin >> dq[i][j];
		}

		int idx, dir;
		for (int i = 0; i < n; i++) {
			cin >> idx >> dir;

			idx--;
			dir = (dir == -1 ? 0 : dir);

			dfs(idx, idx + 1, 1, dir, 2); // 오른쪽
			dfs(idx, idx - 1, -1, dir, 6); // 왼쪽

			rotate(idx, dir);
		}

		for (int i = 0; i < 4; i++)
			ans += (dq[i][0] ? (1 << i) : 0);

		cout << '#' << t << ' ' << ans << '\n';
	}
	return 0;
}
