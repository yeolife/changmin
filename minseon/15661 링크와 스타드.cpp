#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

int n;
int val[21][21];
int used[21];
vector<int> team1;
vector<int> team2;
int mini = 0x7f7f7f;

int getSum(vector<int> team) {
	int len = team.size();
	int sum = 0;
	for (int i = 0; i < len; i++) {
		for (int j = 0; j < len; j++) {
			if (team[i] == team[j]) continue;
			sum += val[team[i]][team[j]];
		}
	}
	return sum;
}

void solve(int lev, int start, int targetN) {
	if (lev == targetN) {
		for (int i = 0; i < n; i++) {
			if (used[i] == 0) {
				team2.push_back(i);
			}
		}
		int team1_val = getSum(team1);
		int team2_val = getSum(team2);
		team2.clear();

		if (abs(team1_val - team2_val) < mini)
			mini = abs(team1_val - team2_val);
		return;
	}

	for (int i = start; i < n; i++) {
		if (used[i]) continue;

		// i¹ø ¼±ÅÃ 
		used[i] = 1;
		team1.push_back(i);
		solve(lev + 1, i + 1, targetN);
		team1.pop_back();
		used[i] = 0;
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> val[i][j];
		}
	}

	for (int len = 1; len <= n / 2; len++) {
		solve(0, 0, len);
	}

	printf("%d\n", mini);

	return 0;
}