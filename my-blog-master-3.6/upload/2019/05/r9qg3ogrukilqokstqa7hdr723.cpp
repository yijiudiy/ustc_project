#include<iostream>
#include<string>
#include<string>
using namespace std;
class Emp{
	public:
		Emp(){
			name = "unknown";
		}
		explicit Emp(const char* n){
			name = n;
			cout << name << " constructing." << endl;
		}
		~Emp(){
			cout << name <<  " destructing." << endl;
		}
	private:
		string name;
};

int main()
{
	Emp liu("liujin");
	{
		Emp c1;
		Emp c2;
		cout << endl;	
	}
	Emp* ptr = new Emp();
	delete ptr;
	return 0;
}











