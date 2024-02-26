#include<bits/stdc++.h>

using namespace std;

int root[101] = {0,};
bool visited[101];

int find(int a) {
    if(root[a] == 0)
    	return a;

	return root[a] =  find(root[a]);
}

void merge(int x, int y) {
	x = find(x);
	y = find(y);
	if (x == y) return;
	root[y] = x;
}

int main() {
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; test_case++) {
        fill(root, root+101, 0);
        fill(visited, visited+101, 0);
        
		int n, m;
        int ans = 0;
        cin >> n >> m;
        
        for(int i = 0; i < m; i++) {
        	int a, b;
            
            cin >> a >> b;
            
            if(a > b) swap(a, b);
            
            merge(a, b);
        }
        
        for(int i = 1; i <= n; i++) {
        	int p = find(i);
            
            if(!visited[p]) {
            	visited[p] = true;
                ans++;
            }
        }
        
        cout << '#' << test_case << ' ' << ans << '\n';
	}
    
	return 0;
}
