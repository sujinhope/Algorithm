#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int n, ans = -1;
int arr[1001][3];
vector<int> v, v1;

int main() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i][0]);
	}

	v.push_back(arr[0][0]);
	for (int i = 1; i < n; i++) {
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

	v1.push_back(arr[n - 1][0]);
	for (int i = n - 2; i >= 0; i--) {
		if (arr[i][0] > v1[v1.size() - 1]) {
			v1.push_back(arr[i][0]);
			arr[i][2] = v1.size() - 1;
		}
		else {
			int idx = lower_bound(v1.begin(), v1.end(), arr[i][0]) - v1.begin();
			v1[idx] = arr[i][0];
			arr[i][2] = idx;
		}
	}

	for (int i = 0; i < n; i++) {
		ans = max(arr[i][1] + arr[i][2], ans);
	}
	printf("%d\n", ans + 1);
	return 0;
}