#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string.h>
using namespace std;
using ll = long long;
using pii = pair<int, int>;
using tiii = tuple<int, int, int>;
const int MOD = 1e9 + 7;

int n, m, c;
int map[11][11];
//int visited[11][11];
int honey[2][5];
int maxi, ans;

int isPossible(int y, int x) {
	for (int i = 0; i < m; i++) {
		if (x + i >= n) return -1;
	}
	return 1;
}

void setVisited(int y, int x, int val, int lev) {
	for (int i = 0; i < m; i++) {
		if (val != 0)
			honey[lev][i] = map[y][x + i];
		else if (val == 0)
			honey[lev][i] = 0;
	}
}

void calcMax(int* arr, int lev, int start, int c_sum, int honey_sum) {
	if (c_sum > c) return;
	if (honey_sum > maxi)
		maxi = honey_sum;

	for (int i = start; i < m; i++) {
		calcMax(arr, lev + 1, i + 1, c_sum + arr[i], honey_sum + arr[i] * arr[i]);
	}
}

void solve(int lev, int starty, int startx) {
	if (lev == 2) {
		int tmp = 0;
		for (int i = 0; i < 2; i++) {
			maxi = -0x7f7f7f;
			calcMax(honey[i], 0, 0, 0, 0);
			tmp += maxi;
		}
		if (tmp > ans)
			ans = tmp;
		return;
	}

	if (starty < 0 || starty >= n || startx < 0 || startx >= n) return;

	for (int i = starty; i < n; i++) {
		for (int j = (i > starty ? 0 : startx); j <= n - m; j++) {
			if (isPossible(i, j) != 1) continue;

			setVisited(i, j, 1, lev);
			solve(lev + 1, ((j + m) >= n ? i + 1 : i), ((j + m) >= n ? 0 : j + m));
			setVisited(i, j, 0, lev);
		}
	}
}

int main() {
	ios::sync_with_stdio(0); cin.tie(NULL);
	freopen("input.txt", "r", stdin);

	int T;
	cin >> T;

	for (int test_case = 1; test_case <= T; test_case++) {

		memset(map, 0, sizeof(map));
		//memset(visited, 0, sizeof(visited));
		memset(honey, 0, sizeof(honey));
		maxi = -0x7f7f7f;
		ans = -0x7f7f7f;

		cin >> n >> m >> c;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}

		solve(0, 0, 0);
		printf("#%d %d\n", test_case, ans);
	}
	return 0;
}
