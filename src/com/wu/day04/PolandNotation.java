package com.wu.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lynn
 * @date 2020/3/10 - 19:55
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成中缀转后缀的功能
        //1.将1+((2+3)*4)-5 转为 1, +, (, (, 2, +, 3, ), *, 4, ), -, 5
        //2.因为直接对str就行操作 不方便 因此先将其转为对应的list
        //3.将得到的中缀list转为对应的后缀list =》ArrayList[1,2,3,+,4,*,+,5,-]
        String infixExpression = "1+((2+3)*4)-5";
        List<String> stringList = toInfixExpressionList(infixExpression);//中缀表达式
        System.out.println("infixExpression="+stringList);
        List<String> parseSuffixExpression = parseSuffixExpression(stringList);//后表达式
        System.out.println("parseSuffixExpression="+parseSuffixExpression);
        //逆波兰表达式 后缀
        //3*5-6+7  为了方便计算 将数字之间以空格分开 得 “3 5 * 6 - 7 +” =16
        //(30+4)×5-6=>304+5×6-=>164
//        String suffixExpression = "3 5 * 6 - 7 +";
        /*String suffixExpression = "30 4 + 5 * 6 -";
        //为了方便取值，定义一个集合
        List<String> list = getList(suffixExpression);
        System.out.println(list);
        int cal = cal(list);
        System.out.println(cal);*/

    }

    //3.将得到的中缀list转为对应的后缀list =》ArrayList[1,2,3,+,4,*,+,5,-]
    public static List<String> parseSuffixExpression(List<String> ls) {
        Stack<String> s1 = new Stack<>();//用于存放符号
        //因为s2在整个转换过程中没有pop操作 且最后还需要逆序输出
        //所以这里直接用ArrayList
//        Stack<String> s2 = new Stack<>();//用于存放中间结果
        ArrayList<String> s2 = new ArrayList<>();//存放结果
        //遍历
        for (String item : ls) {
            //如果是一个数字 则直接入s2
            if (item.matches("\\d+")) {
                s2.add(item);//入栈
            } else if (item.equals("(")) {
                //直接入s1中
                s1.push(item);
            } else if (item.equals(")")) {
                //依次弹出并加入s2中 知道看见下一个（
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());//依次弹入s2
                }
                //将s1的（弹出
                s1.pop();
            } else {
                //当运算符比s1栈顶的优先级低，则弹出s1的运算符加入到s2中，并返回s1比较
                while (s1.size() != 0 && Operation.getOper(s1.peek()) >= Operation.getOper(item) ) {
                    s2.add(s1.pop());
                }
                //还需要将item压入s1中
                s1.push(item);
            }
        }
        //将s1内得元素依次压入s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//对应的后缀表达式
    }

    //将中缀表达式转成对应的list
    //s="1+（（2+3）*4）-5
    public static List<String> toInfixExpressionList(String s) {
        //定义一个list  存放中缀表达式对应的额内容
        ArrayList<String> ls = new ArrayList<>();
        int i = 0;//用于遍历字符串
        String str;// 用于拼接多位数的字符串
        char c; // 用于存放遍历到的每个字符
        do {
            //如果是一个非数字，则直接存入list中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(c + "");
                i++;
            } else {
                //如果是数字，则考虑多位数
                str = "";//置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    public static List<String> getList(String sufficExpression) {
        ArrayList<String> list = new ArrayList<>();
        //将字符串存入数组
        String[] split = sufficExpression.split(" ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //计算
    public static int cal(List<String> ls) {
        //利用栈
        Stack<String> stack = new Stack<>();
        //遍历
        for (String s : ls) {
            //判断是不是数字
            if (s.matches("\\d+")) {//表示1-多位数
                stack.push(s);//存入栈中
            } else {
                //如果为运算符
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());//注意弹出顺序 为后减前一位
                int res = 0;
                //弹出两个数字
                if (s.equals("+")) {
                    res = num2 + num1;
                } else if (s.equals("-")) {
                    res = num2 - num1;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符格式有误");
                }
                //将计算结果存入栈中
                stack.push(res + "");
            }
        }
        //输出结果
        return Integer.parseInt(stack.pop());

    }
}

//返回优先级
class Operation {
    public static final int ADD = 1;//加
    public static final int SUB = 1;//减
    public static final int MUL = 2;//乘
    public static final int DIV = 2;//除

    public static int getOper(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}