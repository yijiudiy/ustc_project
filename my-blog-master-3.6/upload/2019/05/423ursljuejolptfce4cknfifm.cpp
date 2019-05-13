#include<iostream>
#include<string>
using namespace std;
class C{
	public:
		C(){
			x = 0;
		}
		const int getc(){
			return c;
		}
	private:
		int x;
		const int c = 0;
};

int main()
{
	C obj;
	const int a = obj.getc();
	a = 8;
	cout << a << endl;
	return 0;
}
