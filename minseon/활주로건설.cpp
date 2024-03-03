#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<string.h>
#include<math.h>

using namespace std;

int n, x;
int map[21][21];
int tmp[21];
int visited[21];

int solve() {
	memset(visited, 0, sizeof(visited));

	for (int i = 0; i < n - 1; i++) {
		if (abs(tmp[i] - tmp[i + 1]) >= 2) {
			return 0;
		}

		// 1 차이
		// x+1 만큼 확인해야됨

		if (tmp[i + 1] == tmp[i] + 1) { // 증가 : 이전 확인
			// memcpy(c_visited, visited, sizeof(visited));
			for (int j = 0; j > -x; j--) {
				if (i + j < 0) return 0;
				if (visited[i + j] == 1) return 0;
				if (tmp[i + j] != tmp[i]) {
					// memcpy(visited, c_visited, sizeof(visited));
					return 0;
				}
				visited[i + j] = 1;
			}

		}
		else if (tmp[i + 1] == tmp[i] - 1) { // 감소 : 이후 확인
			//memcpy(c_visited, visited, sizeof(visited));
			for (int j = 1; j <= x; j++) {
				if (i + j >= n) return 0;
				// if (visited[i + j] == 1) return 0;
				if (tmp[i + j] != tmp[i + 1]) {
					//memcpy(visited, c_visited, sizeof(visited));
					return 0;
				}
				visited[i + j] = 1;
			}


			i = i + x - 1;

		}
	}

	return 1;
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	//freopen("input.txt", "r", stdin);
	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		memset(map, 0, sizeof(map));
		memset(tmp, 0, sizeof(tmp));

		cin >> n >> x;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}

		int ans = 0;
		//가로
		for (int i = 0; i < n; i++) {
			memcpy(tmp, map[i], sizeof(map[i]));
			int ret = 0;
			int flag = 1;
			for (int j = 0; j < n - 1; j++) {
				if (tmp[j] != tmp[j + 1]) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				ans++; // 다 평평
				ret = 1;
			}
			else {
				ret = solve();
				if (ret == 1)
					ans++;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmp[j] = map[j][i];
			}

			int ret = 0;
			int flag = 1;
			for (int j = 0; j < n - 1; j++) {
				if (tmp[j] != tmp[j + 1]) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				ans++; // 다 평평
				ret = 1;
			}
			else {
				ret = solve();
				if (ret == 1)
					ans++;
			}
		}


		int d = 0;
		printf("#%d %d\n", test_case, ans);
	}

	return 0;
}