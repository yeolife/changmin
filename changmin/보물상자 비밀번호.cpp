#include <bits/stdc++.h>
using namespace std;

map<char, int> mp { 
    {'A', 10}, {'B', 11}, {'C', 12},
    {'D', 13}, {'E', 14}, {'F', 15}};

// 16진수 변환
int dtoh(vector<int> vec, int idx) {
    int ret = 0, po = 1;

    for(int i = idx - 1; i >= 0; i--) {
        ret += vec[i] * po;
        po *= 16;
    }

    return ret;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(NULL);

    int tc;
    cin >> tc;

    for(int t = 1; t <= tc; t++) {
        int n, k, idx = 0, ans = 0;
        string str;
        deque<int> dq;
        priority_queue<int> pq;

        cin >> n >> k >> str;

        for(int i = 0; i < n; i++) {
            if(isalpha(str[i]))
                dq.push_back(mp[str[i]]);
            else
                dq.push_back(str[i] - '0');
        }

        // 비밀번호 계산
        idx = n / 4;
        for(int i = 0; i < idx; i++) {
            for(int j = 0; j < n; j += idx) {
                vector<int> vec(dq.begin() + j, dq.begin() + (j + idx));
                pq.push(dtoh(vec, idx));
            }

            dq.push_front(dq.back());
            dq.pop_back();
        }

        // k번째 큰수 구하기
        int cnt = 0, prev = -1;
        while(!pq.empty() && cnt < k) {
            int num = pq.top();
            pq.pop();

            if(prev == num) continue;

            prev = num;
            cnt++;
        }

        ans = prev;

        cout << '#' << t << ' ' << ans << '\n';
    }

    return 0;
}
