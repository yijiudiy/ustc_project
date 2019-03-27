#include<iostream>
#include<algorithm>
#include<vector>
#include<string>
using namespace std;
struct node{
	char value = 0;
	int weight = 0;
	node* left = nullptr;
	node* right = nullptr;	
};

bool cmpNode(node *a, node* b){
	return a->weight <= b->weight;
}

node* creat(vector<node*> vctNode){
	
	while(vctNode.size() > 1){
		sort(vctNode.begin(),vctNode.end(),cmpNode);
		node* first = vctNode[0];
		node* second = vctNode[1];
		node* t = new node;
		t->left = first;
		t->right = second;
		t->weight = first->weight + second->weight;
		vctNode.erase(vctNode.begin(), vctNode.begin()+2);
		vctNode.push_back(t);
	}
	return vctNode[0];	
}

void decode(node* root,string code){
	if(!root->left && !root->right){
		cout << root->value << " : ";
		for(auto c:code)
			cout << c;
		cout << endl;
		return  ;
	}
	decode(root->left,code+"0");
	decode(root->right,code+"1");
}


int main(){
	vector<node*> vctNode;
	char tc ;
	while((tc = getchar())!= '\n'){
		if(tc == ' ')
			continue;
		node* tNode = new node;
		tNode->value = tc;
		vctNode.push_back(tNode);
	}
	
	int w = 0;
	for(int i=0; i<vctNode.size(); i++){
		cin >> w;
		vctNode[i]->weight = w;
	}
	
	node* root = creat(vctNode);
	string code;
	decode(root,code);
}
