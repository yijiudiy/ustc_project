#include<iostream>
#include<vector>
#include<ctime>
using namespace std;

void doubleFind(vector<int>&a, int *max, int *min){
	if(a.size() == 0){
		return ;
	}
	int low, high;
	int i = 0;
	if(a.size()%2 == 0){
		;
	}
	else{
		*min = a[0];
		*max = a[0];
		i++;
	}
	while(i<a.size()){
		if(a[i] < a[i+1]){
			low = a[i];
			high = a[i+1];
		}else {
			low = a[i+1];
			high = a[i];
		}
		if(low < *min){
			*min = low;
		}
		if(high > *max){
			*max = high;
		}
		i = i+2;
	}	
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
	int max[1] = {INT_MIN};
	int min[1] = {INT_MAX};
	
	doubleFind(a,max,min);
	
	cout << "max = " << *max << endl;
	cout << "min = " << *min << endl;
	
	return 0;
	
}
