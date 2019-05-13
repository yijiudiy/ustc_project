#include<iostream>
#include<string>
#include<vector>

using namespace std;
void swap(int *a, int* b){
	int t = *a;
	*a = *b;
	*b = t;
}
void permute(int s[], int n){
	if(n == 1) {
		for(int i = 0; i<4; i++)
			cout << s[i]; 
		cout << endl;
	}
	else {
//		permute(s, n-1);
		for(int i=n-1; i>=0; i--){
			swap(&s[i], &s[n-1]);
			permute(s,n-1);
			swap(&s[i], &s[n-1]);
		}
	}
} 

int main()
{
	int s[] = {1,2,3,4};    
	permute(s, 4);
	return 0;
}
