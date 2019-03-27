#include<iostream>
 
using namespace std;
 
struct result {
	int max;
	int min;
};
 
/*
 * 求一个数组，位于start和end(包括start和end)位置之间的所有数的最大和最小值，时间复杂度为O(3*n/2)
 */
result* Get_Min_Max(int array[], int start, int end) {
 
	int len = end - start + 1;
 
	if (end < start) {
		return NULL;
	}
 
	result* res = new result();
 
	int max, min;
 
	if (len % 2 == 0) {
		//偶数的情况
		res->max =
				array[start] > array[start + 1] ?
						array[start] : array[start + 1];
		res->min =
				array[start] < array[start + 1] ?
						array[start] : array[start + 1];
 
		start = start + 2; //如果是偶数，则需要让i从第三个元素开始
	} else {
		//奇数的情况
		res->max = array[start];
		res->min = array[start];
 
		start = start + 1; //如果是奇数，则需要让i从第二个元素开始
	}
 
	while (start <= end) {
		max = array[start] > array[start + 1] ? array[start] : array[start + 1];
		min = array[start] < array[start + 1] ? array[start] : array[start + 1];
 
		res->max = res->max > max ? res->max : max;
		res->min = res->min < min ? res->min : min;
 
		start = start + 2;
	}
 
	return res;
 
}
 
int main() {
 
	int a[9] = { 5, 8, 0, -89, 9, 22, -1, -31, 98 };
	result* r1 = Get_Min_Max(a, 0, 8);
	cout << "最大值为：" << r1->max << "，最小值为：" << r1->min << endl;
 
	int b[10] = { 5, 8, 0, -89, 9, 22, -1, -31, 98, 2222 };
	result* r2 = Get_Min_Max(b, 0, 9);
	cout << "最大值为：" << r2->max << "，最小值为：" << r2->min << endl;
 
	delete r1;
	delete r2;
 
	return 0;
}

