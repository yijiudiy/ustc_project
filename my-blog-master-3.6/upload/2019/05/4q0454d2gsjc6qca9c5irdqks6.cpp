#include<iostream>
#include<ctime>
#include<vector>
#define K 20 
using namespace std;

void countingSort(vector<int> &a){ // a中的元素值0~19 
	vector<int> b(a.size());
	unsigned long c[K] = {};
	for(int i= 0; i<a.size(); i++){
		b[i] = a[i];
		c[b[i]]++;
	}// c中存放了每种数的个数，从而可以很容易得出每种数的排名
	for(int i=1; i<K;i++){
		c[i] += c[i-1];
	}
	for(int i=0; i<b.size(); i++){
		a[c[b[i]]-1]=b[i];	//为什么要减一？ 
		c[b[i]]--;
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
