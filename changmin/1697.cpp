#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;

int ans = 0x7f7f7f7f;
bool visited[100001];

void dijk(int n, int k) {
	priority_queue<pii, vector<pii>, greater<pii> > q;
	q.push({0, n});
	
	while(!q.empty()) {
    int dist, cur;
    tie(dist, cur) = q.top();

		q.pop();
		
		if(cur == k) {
			ans = dist;
			return;
		}
		
		if(cur + 1 <= k && !visited[cur + 1]) { // 앞
			visited[cur + 1] = true;
			q.push({dist+1, cur+1});
		}
		if(cur - 1 >= 0 && !visited[cur - 1]) { // 뒤
			visited[cur - 1] = true;
			q.push({dist+1, cur-1});
		}
		if(cur * 2 <= k + 1 && !visited[cur * 2]) { // 점프
			visited[cur * 2] = true;
			q.push({dist+1, cur*2});
		}
	}
}

int main() {
	int n, k;
	
	cin >> n >> k;
	
	dijk(n, k);
	
	cout << ans;
	
	return 0;
}
