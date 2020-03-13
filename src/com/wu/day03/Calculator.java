package com.wu.day03;

/**
 * @author lynn
 * @date 2020/3/10 - 10:33
 */
public class Calculator {
    public static void main(String[] args) {
        //根据前面老师思路，完成表达式的运算
        String expression = "70+2*6-4";//18//如何处理多位数的问题
        //创建两个栈
        ArrayStack2 operStack = new ArrayStack2(10);//存放运算符
        ArrayStack2 numStack = new ArrayStack2(10);//存放数字
        //定义变量
        int index = 0;//用于遍历数组
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int oper = 0;
        char ch = ' ';//用于保存获取到的字符
        String keepNum = "";//用于拼接多位数
        //开始循环扫描表达式
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            //判断是字符还是操作符
            if (operStack.isOper(ch)) {
                //判断有没有栈内操作符
                if (operStack.isEmpty()) {
                    //直接存入
                    operStack.push(ch);
                } else {
                    //判断当前字符与栈顶操作符的优先级
                    //查看栈顶的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //从数栈弹出两个数字 进行运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //栈顶优先级比传入的优先级低
                        //直接传入
                        operStack.push(ch);
                    }
                }
            } else {
                //直接存入数栈
                // numStack.push(ch - 48);//ascii
                //分析思路//1.当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                // 2.在处理数，需要向expression的表达式的index后再看一位,如果是数就进行扫描，如果是符号才入栈
                // 3.因此我们需要定义一个变量字符串，用于拼接
                keepNum += ch;
                //判断 下一个字符是不是数字
                //如果是 则继续扫描，不是就入栈

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是 则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!,keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index+1,并判断是否扫描到expression最后.
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //扫描完毕，从数字栈和符号栈取出数据，如果 符号栈为空 则数字栈只有一个数据
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("表达式%s的结果为%d", expression, res2);
    }
}

//定义一个数组 表示栈
class ArrayStack2 {
    public int maxSize; //数组大小
    public int[] array; //数组
    public int top = -1;//表示栈顶

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }
        top++;
        array[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法出栈");
        }
        int value = array[top];
        top--;
        return value;
    }

    //显示，注意是从上往下
    public void list() {
        //判断栈kong
        if (isEmpty()) {
            System.out.println("栈空，没有数据显示");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }

    //查看栈顶的操作符，不取出
    public int peek() {
        return array[top];
    }

    //遍历栈 由上往下
    public void showStack() {
        //判空
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i > 0; i--) {
            System.out.printf("array[%d]的值为%d", i, array[i]);
        }
    }

    //判断运算符的优先级，根据数字来比较
    //数字越大 优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return 1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char ch) {
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    //进行运算
    public int cal(int num1, int num2, int oper) {
        int res = 0; //用于存放结果；
        switch (oper) {
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 * num1;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            default:
                break;
        }
        return res;
    }
}