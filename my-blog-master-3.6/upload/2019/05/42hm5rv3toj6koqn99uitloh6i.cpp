#include<iostream>
#include<string>
using namespace std;
class Base{
	private:
		int n;
	public:
		Base(int a){
			cout << "bc" << endl;
			n = a;
			cout << "n = "<< n << endl;;
		}
	
};

class subs:public Base{
	private:
		Base bobj;
		int m;
	public:
		subs(int a, int b, int c):Base(a),bobj(c){
			cout << "cs" << endl;
			m = b;
			cout << "m = " << m << endl;
		}
		~subs(){
			cout << "ds" << endl;
		}	
};

int main()
{
	subs s(1,2,3);
	return 0;
}









