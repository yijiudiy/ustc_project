#include<iostream>
#include<string>
using namespace std;
class BC {
	public:
		BC(){
			x = -1;
			y = -1;
		}
	protected:
		int getx() const{
			return x;
		}
		int gety()const{
			return y;
		}
	private:
		int x;
		int y;
		
}; 
class DC:public BC{
	public:
		int write(){
			cout << getx() * gety() << endl;
		}
};

int main()
{
	DC obj;
	obj.write();
	
	return 0; 
}
