#include <iostream>
#include<vector>
#include<string>
using namespace std;

int main()
{
    vector<string> s;
    string t;
    int i = 0;
    while(cin >> t && t!= "quit")
    {
        s.push_back(t);
    }
    string t_word_max = s[0];
    string t_word2 = s[0];

    int dmax = 0;
    int d2 = 0;
    for(i=0;i<s.size();i++)
    {
        if(s[i] == t_word_max)
        {
            dmax++;
        }
        else
        {
            t_word2 = s[i];
            d2++;
            if(d2>dmax)
            {
                dmax = d2;
                t_word_max = t_word2;
            }
        }
    }
    cout << t_word_max << " : " << dmax << endl;
}
