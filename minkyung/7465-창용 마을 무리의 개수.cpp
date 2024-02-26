#include<iostream>
#include<vector>
#include<queue>
using namespace std;
int n, m;
vector<vector<int>> v;
int visited[101];

void bfs(int u) {
    queue<int> q;
    q.push(u);
    visited[u]=1;

    while(!q.empty()) {
        int x = q.front();
        q.pop();
        for(int i=0;i<v[x].size();i++) {
            int next = v[x][i];
            if(!visited[next]) {
                visited[next]=1;
                q.push(next);
            }
        }
    }
}

int main(int argc, char** argv)
{
    int test_case;
    int T;

    cin>>T; // 테스트 케이스

    for(test_case = 1; test_case <= T; ++test_case)
    {
        n=0, m=0;
        cin >> n >> m; // N, M

        // vector 초기화
        v.assign(101, vector<int>(101,0));
        fill(&visited[0], &visited[101], 0);

        int x=0, y=0;
        for(int i=0;i<m;i++) {
            cin >> x >> y;
            v[x].push_back(y);
            v[y].push_back(x);
        }

        int cnt=0;
        for(int i=1;i<=n;i++) {
            if(!visited[i]) {
                bfs(i);
                cnt++;
            }
        }

        cout << "#" << test_case << " " << cnt << '\n';

    }
    return 0;
}