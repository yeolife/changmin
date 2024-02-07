#include <bits/stdc++.h>
using namespace std;

int main() {
  
  ios::sync_with_stdio(0); cin.tie(NULL);
  
  priority_queue<int, vector<int>, greater<int> > pq;
  
  int n;
  cin >> n;
  
  int num;
  for(int i = 0; i < n; i++) {
    cin >> num;
    pq.push(num);
  }
	
	int ans = 0;
  while(pq.size() >= 2) {
    int a = 0, b = 0;
    a = pq.top();
    pq.pop();
    
    b = pq.top();
    pq.pop();
    
    int c = a + b;
    pq.push(c);
    ans += c;
  }
  
  cout << ans;
  
  return 0;
}
