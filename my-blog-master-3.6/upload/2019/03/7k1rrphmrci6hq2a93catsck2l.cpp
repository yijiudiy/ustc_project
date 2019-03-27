#include "stdafx.h"
#include "CppUnitTest.h"
#include "../MyConvertion/Rmb.h"
using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTest1CheckNum
{		
	TEST_CLASS(UnitTestCheckNum)
	{
	public:	
		TEST_METHOD(TestCheckNum1)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "112a";
			Assert::IsFalse(m.checkNum(s));
		}
		TEST_METHOD(TestCheckNum2)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "1.2354.4";
			Assert::IsFalse(m.checkNum(s));
		}
		TEST_METHOD(TestCheckNum3)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "1.23544";
			Assert::IsTrue(m.checkNum(s));
		}
	};
}

namespace UnitTestTest2RoundString
{
	TEST_CLASS(UnitTestRoundString)
	{
	public:
		TEST_METHOD(TestRoundString1)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "1234567890123456";
			string r = m.roundString(s);
			string ts = "";
			Assert::AreEqual(ts, r);
		}
		TEST_METHOD(TestRoundString2)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "11111.34967";
			string r = m.roundString(s);
			string ts = "1111135";
			Assert::AreEqual(ts, r);
		}
		TEST_METHOD(TestRoundString3)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "12345";
			string r = m.roundString(s);
			string ts = "1234500";
			Assert::AreEqual(ts, r);
		}
		TEST_METHOD(TestRoundString4)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "000345";
			string r = m.roundString(s);
			string ts = "34500";
			Assert::AreEqual(ts, r);
		}
	};
}

namespace UnitTest3Convert
{
	TEST_CLASS(UnitTestConvert)
	{
	public:
		TEST_METHOD(TestConvert1)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "12";
			string ts = m.convert(1,s);
			string rs = "壹拾贰元";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestConvert2)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "12";
			string ts = m.convert(2, s);
			string rs = "壹角贰分";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestConvert3)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "1003";
			string ts = m.convert(1, s);
			string rs = "壹仟零佰零拾叁元";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestConvert4)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "04";
			string ts = m.convert(2, s);
			string rs = "零角肆分";
			Assert::AreEqual(ts, rs);
		}
	};
}

namespace UnitTest4SplitConvertedNum
{
	TEST_CLASS(UnitTestSplitConvertedNum)
	{
	public:
		TEST_METHOD(TestSplitConvertedNum1)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "";
			string ts = m.splitConvertedNum(s);
			string shoulds = "";
			Assert::AreEqual(ts, shoulds);
		}
		TEST_METHOD(TestSplitConvertedNum2)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "12345";
			string ts = m.splitConvertedNum(s);
			string shoulds = "壹佰贰拾叁元肆角伍分";
			Assert::AreEqual(ts, shoulds);
		}
		TEST_METHOD(TestSplitConvertedNum3)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "123456";
			string ts = m.splitConvertedNum(s);
			string shoulds = "壹仟贰佰叁拾肆元伍角陆分";
			Assert::AreEqual(ts, shoulds);
		}
		TEST_METHOD(TestSplitConvertedNum4)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "1004506";
			string ts = m.splitConvertedNum(s);
			string shoulds = "壹万零仟零佰肆拾伍元零角陆分";
			Assert::AreEqual(ts, shoulds);
		}
		TEST_METHOD(TestSplitConvertedNum5)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "0000000000";
			string ts = m.splitConvertedNum(s);
			string shoulds = "零仟零佰零拾零万零仟零佰零拾零元零角零分";
			Assert::AreEqual(ts, shoulds);
		}
	};
}

namespace UnitTest5ReplaceAll
{
	TEST_CLASS(UnitTestReplaceAll)
	{
	public:
		TEST_METHOD(TestReplaceAll_l)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "零零零零零";
			string ts = m.replaceAll(s,"零零零", "零");
			string rs = "零";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestReplaceAll_2)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "壹万零仟零佰肆拾伍元零角陆分";
			string ts = m.replaceAll(s, "零仟", "零");
			ts = m.replaceAll(ts, "零佰", "零");
			ts = m.replaceAll(ts, "零角", "零");
			//ts = m.replaceAll(ts, "零零", "零");
			string rs = "壹万零零肆拾伍元零陆分";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestReplaceAll_3)
		{
			Rmb m;
			string s = "壹万零零肆拾伍元零陆分";
			string ts = m.replaceAll(s, "零零", "零");
			string rs = "壹万零肆拾伍元零陆分";
			Assert::AreEqual(ts, rs);
		}
	};
}

namespace UnitTest6ClearZero
{
	TEST_CLASS(UnitTestClearZero)
	{
	public:
		TEST_METHOD(TestClearZero1)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "壹万零仟零佰肆拾伍元零角陆分";
			string ts = m.clearZero(s);
			string rs = "壹万零肆拾伍元零陆分";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestClearZero2)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "贰佰零拾零元零角零分";
			string ts = m.clearZero(s);
			string rs = "贰佰元";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestClearZero3)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "壹仟贰佰叁拾肆元伍角陆分";
			string ts = m.clearZero(s);
			string rs = "壹仟贰佰叁拾肆元伍角陆分";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestClearZero4)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "壹万零仟零佰零拾零元零角零分";
			string ts = m.clearZero(s);
			string rs = "壹万元";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestClearZero5)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "壹万零仟零佰零拾零元伍角零分";
			string ts = m.clearZero(s);
			string rs = "壹万元伍角";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestClearZero6)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "壹万叁仟零佰零拾零元伍角零分";
			string ts = m.clearZero(s);
			string rs = "壹万叁仟元伍角";
			Assert::AreEqual(ts, rs);
		}
	};
}


namespace UnitTest7add
{
	TEST_CLASS(UnitTestAdd)
	{
	public:
		TEST_METHOD(TestAdd1)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "贰佰元";
			string ts = m.add(s);
			string rs = "人民币贰佰元整";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestAdd2)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "贰佰元叁角";
			string ts = m.add(s);
			string rs = "人民币贰佰元叁角";
			Assert::AreEqual(ts, rs);
		}
		TEST_METHOD(TestAdd3)
		{
			// TODO: 在此输入测试代码
			Rmb m;
			string s = "叁角伍分";
			string ts = m.add(s);
			string rs = "人民币叁角伍分";
			Assert::AreEqual(ts, rs);
		}
	};
}