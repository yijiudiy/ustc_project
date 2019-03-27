#include<iostream>
#include<ctime>
#include<vector>
#define K 20 
using namespace std;

void insertToBucket(vector<vector<double> > &b, vector<double > &a, int i){
	int pos = a[i] * a.size();
//	cout << pos << endl;
	b[pos].push_back(INT_MAX);
	if(b[pos].size() == 1){
		b[pos][0] = a[i];
	}
	else{
		int j = b[pos].size()-2;
		while(j>=0 && a[i]<b[pos][j]){
			b[pos][j+1] = b[pos][j];
			j--;
		}
		b[pos][j+1] = a[i];
	}
	
}


void concentrate(vector<vector<double> > &b, vector<double > &a){
	int k = 0;
	for(int i = 0; i<a.size(); i++){// 遍历每个桶 ,每个桶是一个vector<double> 
		for(int j=0; j<b[i].size(); j++){
			a[k++] = b[i][j];
		}
	}
}

void bucketSort(vector<double> &a){
	int n = a.size();
	vector<vector<double> > b(n);
	for(int i=0; i<a.size(); i++){
//		int pos = a[i]*n;
		insertToBucket(b,a,i);
	}
	concentrate(b,a);
}

int main(){
	srand(time(0));
	vector<double> a;
	cout << "输入生成随机数的个数："; 
	int n;
	cin>>n;

	for(int i=0; i<n; i++){
		a.push_back(rand()%100/100.0);
	}
	cout << "随机数 ："; 
	for(int i=0; i<a.size(); i++){
		cout << a[i] << '\t';
	}
	cout << endl;

	bucketSort(a);		 //***********桶排序**********************************

	cout << "排序后 ：";
	for(int i=0;i<a.size();i++)	
		cout << a[i]  << "\t" ;
	return 0;
}
