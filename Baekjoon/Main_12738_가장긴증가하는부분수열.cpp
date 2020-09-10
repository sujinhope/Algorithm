#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<tuple>
using namespace std;

int A;
int arr[1000000];
vector<int> v;

int main() {
	scanf("%d", &A);
	for (int i = 0; i < A; i++)
		scanf("%d", &arr[i]);

	v.push_back(arr[0]);
	for (int i = 1; i < A; i++) {
		if (arr[i] > v[v.size() - 1])
			v.push_back(arr[i]);
		else {
			int idx = lower_bound(v.begin(), v.end(), arr[i]) - v.begin() + 1;
			//v.insert(lower_bound(v.begin(), v.end(), 20), arr[i]);
			v[idx - 1] = arr[i];
		}

	}
	printf("%d\n", v.size());

	return 0;
}