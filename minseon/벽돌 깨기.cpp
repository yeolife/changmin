#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<string.h>
#include<queue>
#include<vector>

using namespace std;

int n, w, h;
int map[16][13];
int dy[4] = { -1,0,0,1 };
int dx[4] = { 0,1,-1,0 };
queue<pair<int, int>> q;
int maxi;
pair<int,int> path[10];


int destroy(int y, int x) {
	int c_map[16][13] = { 0, }; 
	memcpy(c_map, map, sizeof(map)); //c_map에 map copy -> 진짜 터뜨리는건 map에서

	int tmp = 0;
	while (!q.empty()) {
		
		int nowy = q.front().first;
		int nowx = q.front().second;
		int nowRange = c_map[nowy][nowx]; // 범위는 c_map에서체크
		q.pop();


		for (int i = 0; i < 4; i++) { // 한 방향 잡아서
			for (int j = 0; j < nowRange; j++) { // nowRange만큼 가본다
				int ny = nowy + dy[i]*j;
				int nx = nowx + dx[i]*j;
				if (ny < 0 || ny >= h || nx < 0 || nx >= w) continue;

				// 1이상인 칸 나오면 큐에 담고, 0으로 만들기
				if (map[ny][nx] > 0) {
					map[ny][nx] = 0;
					tmp++;
					q.push({ ny,nx });
				}
			}

		}
	}
	return tmp;
}

void drop() {
	for (int i = 0; i < w; i++) { // 열
		vector<int> v;
		for (int j = h - 1; j >= 0; j--) { //행
			if (map[j][i] > 0) {
				
				v.push_back(map[j][i]);
				map[j][i] = 0;
			}
		}
		int len = v.size();
		for (int j = 0; j < len; j++) {
			map[h-1-j][i] = v[j];
		}
	}
}

void solve(int lev, int destroyed) { 
	if (lev == n) {
		// 답 갱신
		if (destroyed > maxi) {
			maxi = destroyed;
		}
		return;
	}

	int c_map[16][13] = { 0, };
	
	for (int i = 0; i < w; i++) {
		for (int j = 0; j < h; j++) {
			if (map[j][i] > 0) {
				memcpy(c_map, map, sizeof(map));

				q.push({ j,i });
				int ret = destroy(j,i); // 터뜨리기
				drop(); // 떨어뜨리기
				path[lev] = { j,i };
				solve(lev + 1, destroyed + ret);
				path[lev] = { 0,0 };

				memcpy(map, c_map, sizeof(map)); // 파괴 전으로 다시 되돌리기
				break;
			}
		}
	}
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	freopen("input.txt", "r", stdin);
	cin >> T;
	
	for (test_case = 1; test_case <= T; ++test_case)
	{
		memset(map, 0, sizeof(map));
		maxi = -0x7f7f7f;

		int cnt = 0;
		cin >> n >> w >> h;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				cin >> map[i][j];
				if (map[i][j] > 0) cnt++;
			}
		}

		solve(0, 0);
		if (cnt == w*h) maxi = cnt;
		printf("#%d %d\n", test_case, cnt - maxi);
	}
	return 0;
}