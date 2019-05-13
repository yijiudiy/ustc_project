#include<iostream>
#include<vector>
#include<string>
using namespace std;
int main()
{
          string rsp;
    do
    {
        string s1,s2;

        cin>>s1>>s2;
        cout << endl;
        if(s1.size()>s2.size())
            cout<<s1<<endl;
        else
            cout << s2 <<endl;
        cout << "Would you want to continue?(yes/no)"<<endl;
        cin>>rsp;
    }while(rsp == "yes");
    return 0;
}
