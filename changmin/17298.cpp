#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(NULL);
	
	  vector<int> vec, ans;
    stack<int> st;
    
    int n;
    cin >> n;
    
    int num = 0;
    for(int i = 0; i < n; i++) {
    	cin >> num;
		vec.push_back(num);
	}

	for(int i = n - 1; i >= 0; i--) {
		while(!st.empty() && st.top() <= vec[i])
			st.pop();
		
		if(!st.empty())
			ans.push_back(st.top());
		else
			ans.push_back(-1);
		
		st.push(vec[i]);
	}
	
	reverse(ans.begin(), ans.end());
	
	for(int i = 0; i < ans.size(); i++)
		cout << ans[i] << ' ';

    return 0;
}
