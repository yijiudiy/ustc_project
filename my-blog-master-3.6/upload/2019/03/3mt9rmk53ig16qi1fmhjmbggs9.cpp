#include<iostream>
#include<queue>
using namespace std;
struct treenode{
    int weight;
    int value;
    int level;
    int flag;
};
queue<treenode> que;
int enQueue(int w,int v,int level,int flag,int n,int* bestvalue)
{
    treenode node;
    node.weight = w;
    node.value = v;
    node.level = level;
    node.flag = flag;
    if (level == n)
    {
        if (node.value > *bestvalue)
        {
            *bestvalue = node.value;
        }
        return 0;
    }else
    {
        que.push(node);
    }
}
//w为重量数组，v为价值数组，n为物品个数，c为背包容量，bestValue为全局最大价值
int bbfifoknap(int w[],int v[],int n,int c,int* bestValue)
{
    //初始化结点
    int i=1;
    treenode tag, livenode;
    livenode.weight = 0;
    livenode.value = 0;
    livenode.level = 1;
    livenode.flag = 0;//初始为0
    tag.weight = -1;
    tag.value = 0;
    tag.level = 0;
    tag.flag = 0;//初始为0
    que.push(tag);
    while (1)
    {
        if (livenode.weight + w[i - 1] <= c)
        {
            enQueue(livenode.weight + w[i - 1], livenode.value + v[i - 1], i, 1,n,bestValue);
        }
        enQueue(livenode.weight,livenode.value, i, 0,n,bestValue);
        livenode = que.front();
        que.pop();
        if (livenode.weight == -1)
        {
            if (que.empty() == 1)
            {
                break;
            }
            livenode = que.front();
            que.pop();
            que.push(tag);
            i++;
        }

    }
    return 0;
}
int main()
{
    int w[] = { 16, 15, 15 };
    int v[] = { 45, 25, 25 };
    int n = 3;
    int c = 30;
    int bestValue=0;
    bbfifoknap(w, v,n,c,&bestValue);
    cout << bestValue<<endl;
    return 0;
}

