#include<iostream>
#include<vector>
#include<string>
using namespace std;
int main()
{
    vector<int> v1={1,2,3,5,4,8,9,0,76};
    vector<int> v2={1,2,3,5,4};
    auto it = v2.begin(), it2 = v1.begin();
    for(; it!=v2.end() && it2 != v1.end(); it++,it2++)
    {
        if(*it == *it2)
            ;
        else
            break;
    }
    if(it == v2.end() || it2 == v1.end())
    {
        if(v1.size()>v2.size())
        {
            cout << "v2 is v1 de qianzhui" << endl;
        }
        else if(v1.size() == v2.size())
        {
            cout << "v1 is equal to v2" << endl;
        }
        else
        {
            cout << "v1 is v2 de qianzhui" << endl;
        }
    }
    else
    {
        cout << "no one is the other's qianzhui" << endl;
    }

    return 0;
}
