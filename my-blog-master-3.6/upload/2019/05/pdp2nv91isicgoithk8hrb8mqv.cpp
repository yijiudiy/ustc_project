#include<iostream>
#include<ctime>
#include<vector>
#define K 20 
using namespace std;

void countingSort(vector<int> &a){ // a中的元素值0~19 
//	vector<int> b(a.size());
	unsigned long c[20] = {};
	for(int i= 0; i<a.size(); i++){
//		b[i] = a[i];
		c[a[i]]++;
	}// c中存放了每种数的个数，从而可以很容易得出每种数的排名
	int n = a.size()-1;
	for(int i = K-1; i>=0; i--){	// 这里的思路是将数字从大到小倒序放置 ，直接放，从后往前紧大的放。 而不是像书上的还要统计小于自己的数。 
		for(int j=0; j<c[i]; j++){
			a[n--] = i;
		}
	}
}

int main(){
	srand(time(0));
	vector<int> a;
	int n;
	cin>>n;

	for(int i=0; i<n; i++){
		a.push_back(rand()%20);
	}
	cout << "随机数 ："; 
	for(int i=0; i<a.size(); i++){
		cout << a[i] << '\t';
	}
	cout << endl;
	
	countingSort(a);		 //********计数排序**********************************

	cout << "排序后 ：";
	for(int i=0;i<a.size();i++)	
		cout << a[i]  << "\t" ;
	return 0;
}
