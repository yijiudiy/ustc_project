#include<iostream>
using namespace std;

int partition(int a[], int p, int r){
	int x = a[r];
	int i = p-1;
	for(int j = p; j<r; j++)
	{
		if(a[j] <= x){
			i = i+1;
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
	 int t = a[r];
	 a[r] = a[i+1];
	 a[i+1] = t;
	 
	 return i+1;
}

void quicksort(int a[], int p, int r){
	if(p<r){
		int q = partition(a,p,r);
		quicksort(a,p,q-1);
		quicksort(a,q+1,r);
	}
}

int main()
{
	int a[] = {4,5, 8, 23 , 56, 33, 22 , -2 , -8,45};
	for(int i=0; i<10; i++)
		cout << a[i] << '\t';
	cout << endl << endl;
	
	
	quicksort(a,0,9);
	
	
	for(int i=0; i<10; i++)
		cout << a[i] << '\t';
		
	return 0;
} 
