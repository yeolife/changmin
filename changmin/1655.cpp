#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
  
    int n, num;
    priority_queue<int> pq1;
    priority_queue<int, vector<int>, greater<int> > pq2;
    cin >> n;
    for(int i=0; i<n; i++) {
        cin >> num;
      
        if(i%2 == 0)
            pq1.push(num);
        else
            pq2.push(num);
      
        if(!pq1.empty() && !pq2.empty() && pq1.top() > pq2.top()) {
            int tmp = pq1.top();
            pq1.pop();
            pq1.push(pq2.top());
            pq2.pop();
            pq2.push(tmp);
        }
        cout << pq1.top() << '\n';
    }
}
