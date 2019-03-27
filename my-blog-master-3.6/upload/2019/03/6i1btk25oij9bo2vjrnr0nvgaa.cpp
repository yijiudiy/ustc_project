#include<iostream>
#include<vector>
#include<ctime>
using namespace std;
int findMax(vector<int> &a){
	if(a.size() == 0){
		return	0;
	}
	int max = a[0];
	for(int i=1; i<a.size(); i++){
		if(a[i]>max){
			max = a[i];
		}
	}
	return max;
}

int findMin(vector<int> &a){
	if(a.size() == 0){
		return 0;
	}
	int min = a[0];
	for(int i=1; i<a.size(); i++){
		if(a[i]<min){
			min = a[i];
		}
	}
	return min;
}


int main(){
	srand(time(0));
	vector<int> a;
	cout<< "输入数字的个数 : " ;
	int n ;
	cin >> n;
	for(int i=0; i<n; i++){
		int t = rand()%100;
		a.push_back(t);
	} 
	
	for(int i=0; i<a.size(); i++){
		cout << a[i]<<'\t';
	}
	cout << endl;
	
	int max = findMax(a);
	int min = findMin(a);
	
	cout << "max = " << max << endl;
	cout << "min = " << min << endl;
	
	return 0;
	
}
