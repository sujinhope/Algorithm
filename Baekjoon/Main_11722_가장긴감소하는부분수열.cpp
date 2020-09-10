#include<iostream>
#include<cstring>
#include<cstdio>
#include<string>
#include<stack>
#include<queue>
#include<vector>
#include<algorithm>
#include<functional>
using namespace std;

int N;
int arr[1001][2];
vector<int> v;
vector<int> ans;

int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &arr[N - 1 - i][0]);
	}

	v.push_back(arr[0][0]);
	for (int i = 1; i < N; i++) {
		if (arr[i][0] > v[v.size() - 1]) {
			v.push_back(arr[i][0]);
			arr[i][1] = v.size() - 1;
		}
		else {
			int idx = lower_bound(v.begin(), v.end(), arr[i][0]) - v.begin();
			v[idx] = arr[i][0];
			arr[i][1] = idx;		
		}
	}
	int cnt = v.size() - 1;
	for (int i = N - 1; i >= 0; i--) {
		if (arr[i][1] == cnt) {
			ans.push_back(arr[i][0]);
			cnt--;
		}
	}

	printf("%d\n", ans.size());

	return 0;
}