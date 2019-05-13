/** 
 * 程序目的： 
 *   从命令行接收一个数，并将其转化为中文金额的大写方式（C++版） 
 * 例如 123.45 --> 壹佰贰拾叁元肆角伍分 
 * @author LovinChan 
 * 
 *   前一段时间做了个 Java 版的。突然有天心血来潮做个 C++ 版本的，实现的功能 
 * 跟 Java 版本的没什么区别，不过由于我对 C++ 的了解还不是很多，还是学习阶段， 
 * 写出来的东西还有很多问题和不合规范的地方，希望各位批评指出来。 
 *  
 *   程序的注释我尽量写得详细一点，如果觉得这个程序哪里有问题或者是哪里有改进的 
 * 地方欢迎随时跟我交流。 
 *  
 *   我附上了编译以后的 .exe 文件跟 .bat 文件，还有项目源码，供大家测评。 
 * 
 * 工具：Microsoft Visual Studio 2005 
 * 编译环境：Microsoft Visual Studio 2005 
 * 
 * 我的msn:egg.chenlw@gmail.com 
 *    QQ:372133556(注上为什么加我就可以了) 
 * 我的blog:http://hi.baidu.com/egg_chen 
 * 欢迎交流 
 */  
#include <iostream>  
#include <string>  

using namespace std;  

// 表示整数部分的标志  
const int INT_ONLY = 1;  
// 表示小数部分的标志  
const int SMALL_ONLY = 2;  
  
/** 
 * 从命令行接收一个数，在其中调用 checkNum() 方法对其进行 
 * 验证，并返回相应的值 
 * @return 如果输入合法，返回输入的这个数 
 */  
string getNum() {  
    string s;  
    cout << "请输入一个数字（精确到小数点后两位）：" << endl;  
    // 从命令行输入这个浮点数  
    cin >> s;  
    // 清除输入流状态标志  
    cin.clear();  
    return s;  
}  
  
/** 
* 判断用户输入的数据是否合法，用户只能输入大于零的数字，不能输入其它字符 
* @param s string 
* @return 如果用户输入数据合法，返回 true，否则返回 false 
*/  
bool checkNum(string s) {  
    // atof(s.c_str()) 方法的功能是将字符串 s 转换成一个双精度数值并返回结果  
    double d = atof(s.c_str());  
    // 只有当用户输入一个大于0的数时，才会返回true  
    if(d > 0) {  
        return true;  
    }
    return false; 
}  

/**
 * 对传入的数进行四舍五入操作
 * @param s string，从命令行输入的那个数
 * @return 四舍五入后的新值
 */
string roundString(string s) {
    // 将这个数转换成 double 类型，并对其进行四舍五入操作  
    // 先转换这个数的整数部分  
    // atof(s.c_str()) 方法的功能是将字符串 s 转换成一个双精度数值并返回结果  
    // c_str()函数返回一个指向正规C字符串的指针, 内容与本字符串相同  
    double d = atof(s.c_str());  
    // 将这个数进行四舍五入，保留到小数点后两位  
    // 再将这个数转换成字符串，等待转换 
    int dec, sign;
    // 注意：当这个数转换成字符串以后不会显示小数点，并且会以四舍五入的形式只保留小数点后两位  
    s = fcvt(d, 2, &dec, &sign);  
    // 规定数值的最大长度只能是15位（到万亿位）  
    if(s.length() > 15) {  
        cout << "输入数据过大！（整数部分最多13位！）" << endl;
        return "";
    }
    return s;  
}
  
/** 
 * 把传入的数转换为中文金额大写形式 
 * @param flag int 标志位，1 表示转换整数部分，0 表示转换小数部分 
 * @param s string 要转换的字符串 
 * @return 转换好的带单位的中文金额大写形式 
 */  
string formatChinese(int flag, string s) {  
    int sLength = s.length();  
    // 货币大写形式  
    string bigLetter[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};  
    // 货币单位  
    string unit[] = {"元", "拾", "佰", "仟", "万",   
                // 拾万位到仟万位  
                "拾", "佰", "仟",  
                // 亿位到万亿位  
                "亿", "拾", "佰", "仟", "万"};  
    string small[] = {"分", "角"};  
    // 用来存放转换后的新字符串  
    string newS = "";  
    // 逐位替换为中文大写形式 
    for(int i = 0; i < sLength; i ++) {
        if(flag == INT_ONLY) {
            // 转换整数部分为中文大写形式（带单位）  
            newS = newS + bigLetter[s.at(i) - 48] + unit[sLength - i - 1]; 
        } else if(flag == SMALL_ONLY) {
            // 转换小数部分（带单位）
            newS = newS + bigLetter[s.at(i) - 48] + small[sLength - i - 1];  
        }
    }
    return newS;
}

/** 
 * 把用户输入的数以小数点为界分割开来，并调用 numFormat() 方法 
 * 进行相应的中文金额大写形式的转换 
 * 注：传入的这个数应该是经过 roundString() 方法进行了四舍五入操作的 
 * @param s string 
 * @return 转换好的中文金额大写形式的字符串 
 */  
string splitNum(string s) {  
    // 如果传入的是空串则继续返回空串  
    if("" == s) {  
        return "";  
    }  
    // 截取输入数字的整数部分  
    string intOnly = s.substr(0, s.size() - 2);  
    string intPart = formatChinese(INT_ONLY, intOnly);  
  
    // 截取这个数的小数部分  
    string smallOnly = s.substr(s.size() - 2, s.size());  
    string smallPart = formatChinese(SMALL_ONLY, smallOnly);  
  
    // 把转换好了的整数部分和小数部分重新拼凑一个新的字符串  
    string newS = intPart + smallPart;  
  
    return newS;  
}  
  
/** 
 * 使用给定的 replacement 替换此字符串所有匹配给定的 regex 的子字符串。 
 * @param src - 待操作的源字符串 
 * @param regex - 用来匹配此字符串的正则表达式 
 * @param replacement - 用来替换每个匹配项的字符串  
 * @return 替换后的字符串  
 */  
string replaceAll(string src, string regex, string replacement) {  
    int length = regex.length();  
    while(src.find(regex) < src.length()) {  
        // 替换 src 字符串中从第一个匹配 regex 的字符串索引开始的 length 个字符为 replacement 字符串          
        src.replace(src.find(regex), length, replacement);  
    } 
    return src;  
}  

/** 
 * 把已经转换好的中文金额大写形式加以改进，清理这个字 
 * 符串里面多余的零，让这个字符串变得更加可观 
 * 注：传入的这个数应该是经过 splitNum() 方法进行处理，这个字 
 * 符串应该已经是用中文金额大写形式表示的 
 * @param s string 已经转换好的字符串 
 * @return 改进后的字符串 
 */  

string cleanZero(string s) {  
    // 如果传入的是空串则继续返回空串  
    if("" == s) {  
        return "";  
    }
    // 字符串中存在多个'零'在一起的时候只读出一个'零'，并省略多余的单位  
    /* 由于本人对算法的研究太菜了，只能用4个正则表达式去转换了，各位大虾别介意哈... */  
    string regex1[] = {"零仟", "零佰", "零拾"};  
    string regex2[] = {"零亿", "零万", "零元"};  
    string regex3[] = {"亿", "万", "元"};  
    string regex4[] = {"零角", "零分"}; 

    // 第一轮转换把 "零仟", 零佰","零拾"等字符串替换成一个"零"  
    for(int i = 0; i < 3; i ++) {
        s = replaceAll(s, regex1[i], "零");  
    }   
    // 第二轮转换考虑 "零亿","零万","零元"等情况  
    // "亿","万","元"这些单位有些情况是不能省的，需要保留下来  
    for(int i = 0; i < 3; i ++) {  
        // 当第一轮转换过后有可能有很多个零叠在一起  
        // 要把很多个重复的零变成一个零  
        s = replaceAll(s, "零零零", "零");  
        s = replaceAll(s, "零零", "零");  
        s = replaceAll(s, regex2[i], regex3[i]);  
    }  
    // 第三轮转换把"零角","零分"字符串省略  
    for(int i = 0; i < 2; i ++) {  
        s = replaceAll(s, regex4[i], "");  
    }  
//     当"万"到"亿"之间全部是"零"的时候，忽略"亿万"单位，只保留一个"亿"  
    s = replaceAll(s, "亿万", "亿");  
    return s;  
}  
  
int main() {  
    cout << "\n------------将数字转换成中文金额的大写形式（C++）------------\n" << endl;  
    string s = getNum();  
    if(checkNum(s)) {  
        s = roundString(s);  
        s = splitNum(s); 
        s = cleanZero(s);  
        cout << "转换成中文后为：" << s << endl;  
    } else {  
        cout << "非法输入，程序即将退出" << endl;  
    } 
    cout << "\n--------------------------------------------------------------" << endl;  
}
