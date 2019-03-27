#include<iostream>
#include<string>
using namespace std;
class Person{
	public:
		Person(){
			name = "unknown";
		} 
		explicit Person(const string& n){
			name = n;
		}
		Person(const char* n){
			name = n;
		}
		const string getname()const{
			return name;
		}
	private :
		string name;
}; 

void f(Person sheng){
	cout << "i love you, " <<  sheng.getname() << endl;
}
int main()
{
	Person Soprano("liu jin");
	f(Soprano);
	string s = "shengxueqing";
	f(s);
}
