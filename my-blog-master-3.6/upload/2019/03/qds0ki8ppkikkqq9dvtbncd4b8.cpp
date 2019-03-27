#include<iostream>
#include<vector>
using namespace std;
void sort(vector<int> &a , vector<int> &res, int k){
	vector<int> c(k+1);
	
	cout << endl;

	for(int i=0; i<a.size(); i++)
	{
		c[a[i]]++;
	}     //给a中的每个数计数 
	for(int i=1 ; i<k+1; i++) // a数组不要了，现在只关注c数组，c数组既包含a数组的值，也包含值之间的顺序，还包含a数组中的值有几个。 
	{
		if(c[i])
			for(int j=0, t=0; j<c[i];j++)
				res.push_back(i);
	}
} 

int main(){
	vector<int> a = {2, 5, 2 , 4, 1, 5, 4, 6, 2,1};
	vector<int> res={};
	sort(a, res, 6);
	for(int i=0; i<res.size(); i++)
		cout << res[i] << '\t';
} 
