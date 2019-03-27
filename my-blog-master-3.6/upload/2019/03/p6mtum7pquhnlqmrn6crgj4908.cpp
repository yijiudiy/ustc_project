#include <stdio.h>
#include<iostream>
#define ARRAY_SIZE 10

int select(int a[], int l, int r, int k);
int partition(int a[], int p, int r, int pivot);
void insertsort(int a[], int low, int high);
void swap(int a[], int i, int j);

int main(void)
{
	int a[ARRAY_SIZE] = { 25,31,89,12,67,53,44,59,71,19 };

	printf("%d\n", select(a, 0, ARRAY_SIZE - 1, 6));

	system("pause");
}

int select(int a[], int l, int r, int k)
{
	int group;
	int i;
	int left, right, mid;
	int pivot;
	int q, left_num;

	if (r - l + 1 <= 5) {   // 所给定区间总共连5个数都没有
		insertsort(a, l, r);   // 那么将这个区间内的数直接插入排序
		return a[l + k - 1];	// 然后返回第k个数就好了
	}

	group = (r - l + 1 + 5) / 5;    // 否则说明不止5个数，那我看看要分几个组，group是组数
	for (i = 0; i < group; i++) {   // 循环group次
		left = l + 5 * i;
		right = (left + 4) > r ? r : left + 4; //超出右边界就使用右边界赋值
		mid = (left + right) / 2;
		insertsort(a, left, right);
		//将各组中位数与前i个元素互换位置，方便递归select寻找中位数的中位数
		swap(a, l + i, mid);
	}  // 循环完了之后，前group个数的身份都是中位数
	pivot = select(a, l, l + group - 1, (group + 1) / 2); // 递归求出中位数的中位数，也就是求出前group个数的中位数
	//注意pivot就是那个数值本身，而不是下标
	// 用中位数的中位数作为基准的位置
	q = partition(a, l, r, pivot);    // 找出新的划分位置

	//left_num = q - l;
	if (k == q)
		return a[q];
	else if (k <= q-1) //k在低区
		return select(a, l, q - 1, k);
	else //k在高区
		return select(a, q + 1, r, k - q);
}

int partition(int a[], int p, int r, int pivot)
{
	int x;
	int i = p - 1;
	int j, tmp;

	for (j = p; j < r; j++) {//首先遍历一遍，将pivot和最后一个元素互换，
		if (a[j] == pivot) {
			swap(a, j, r);
		}
	}    //这样后面就和一般的partition一样的算法了
	x = a[r];

	for (j = p; j < r; j++) {
		if (a[j] <= x) {
			i++;
			tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
	}
	tmp = a[r];
	a[r] = a[i + 1];
	a[i + 1] = tmp;  
	return i + 1;
}

// 插入排序
void insertsort(int a[], int low, int high)
{
	int i, j;
	int key;

	for (i = low + 1; i <= high; i++) {
		key = a[i];
		for (j = i - 1; j >= low && key < a[j]; j--){
			a[j + 1] = a[j];
		}
		a[j + 1] = key;
	}
}

void swap(int a[], int i, int j)
{
	int tmp = a[i];

	a[i] = a[j];
	a[j] = tmp;
}