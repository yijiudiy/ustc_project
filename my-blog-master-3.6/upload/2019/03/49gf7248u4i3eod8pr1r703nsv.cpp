#include<iostream>
#include<vector>
using namespace std;
void csort(vector<int> &a, vector<int> &res, int k){
	vector<int > c(k+1,0);
	for(int i=0; i<a.size(); i++)
	{
		c[a[i]]++;
	}     //给a中的每个数计数  
	for(int i=2; i<k+1; i++){
		c[i] = c [i] + c[i-1];
	}
//	for(int i=a.size()-1; i>=0; i--) //倒序遍历 
	for(int i=0; i<a.size(); i++) //顺序遍历 
	{
		res[c[a[i]]-1] = a[i];
		c[a[i]]--;
	}
}


int main(){
	vector<int> a = {2, 5, 2 , 4, 1, 5, 4, 6, 2,1};
	cout << a.size()<< endl << endl;
	vector<int> res(a.size());
	csort(a, res, 6);
	for(auto x:res)
		cout << x << '\t';
} 
