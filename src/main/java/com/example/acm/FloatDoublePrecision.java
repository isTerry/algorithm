package com.example.acm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FloatDoublePrecision {
    public static void main(String args[]){

    }

    /*
byte 1字节
short 2字节
int 4字节
long 8字节
float 4字节
double 8字节
char 2字节
boolean 1字节

11.int、long、float、double值范围，超出范围怎么办（BigInteger、BigDecimal）？
1、
Byte
Byte数据类型是8位
取值范围：(-128 - 127、即-2^7 - 2^7-1)

short
short数据类型是16位
取值范围：（-32768-32767、即-2^15 - 2^15-1)
65535？？？等于无符号的Short最大值、2^16-1
2、
基本类型：int 二进制位数：32
包装类：java.lang.Integer
最小值：Integer.MIN_VALUE= -2147483648 （-2的31次方）
最大值：Integer.MAX_VALUE= 2147483647  （2的31次方-1）
（1亿=100000000(8个0）=10^8，int最大，大概20亿、10的9次方）
3、
基本类型：long 二进制位数：64
包装类：java.lang.Long
最小值：Long.MIN_VALUE=-9223372036854775808 （-2的63次方）
最大值：Long.MAX_VALUE=9223372036854775807 （2的63次方-1）
（最大值大概百亿*亿级别、10的18次方）
4、
基本类型：float 二进制位数：32
包装类：java.lang.Float
最小值：Float.MIN_VALUE=1.4E-45  （2的-149次方）
（1.4乘于10的负45次方 此处的E并非自然对数,而是10的次方的意思，
平方使用Math.pow(1.4,2)）
最大值：Float.MAX_VALUE=3.4028235E38 （2的128次方-1）
5、
基本类型：double 二进制位数：64
包装类：java.lang.Double
最小值：Double.MIN_VALUE=4.9E-324 （2的-1074次方）
最大值：Double.MAX_VALUE=1.7976931348623157E308 （2的1024次方-1）

整数类型：最大值就是最大正整数，最小值就是负的最大值(相差1)
浮点数类型：最大值就是最大正浮点数，最小值是比0大的最小浮点数，而不是负的最大值

F的二进制码为 1111
7的二进制码为 0111
这样一来，整个整数 0x7FFFFFFF 的二进制表示就是除了首位是 0，其余都是1
就是说，这是最大的整型数 int（因为第一位是符号位，0 表示他是正数）

0x80000000 的二进制位
原码 1000 0000 0000 0000 0000 0000 0000 0000
若最高位为符号位，则为-0，可是输出int i = 0x80000000 发现i= -(2^31)
原因是在十六进制中负数的二进制原码的最高位是符号位，后面的31位为序号位，不是值位。
1后面的000 0000 0000 0000 0000 0000 0000 0000，表示序号1，表示负数中，从小到大的第一位。\

指数位决定了大小范围，因为指数位能表示的数越大则能表示的数越大嘛！
而小数位决定了计算精度，因为小数位能表示的数越大，则能计算的精度越大咯！
float的小数位只有23位，即二进制的23位，能表示的最大的十进制数为2的23次方，即8388608，
即十进制的7位，严格点，精度"只能百分百保证"十进制的6位运算。
double的小数位有52位，对应十进制最大值为4 503 599 627 370 496，这个数有16位，
所以计算精度"只能百分百保证十进制"的15位运算。
     */
    public static void maxMin(){
        System.out.println("Byte min:"+Byte.MIN_VALUE);
        System.out.println("Byte max:"+Byte.MAX_VALUE);

        System.out.println("Short min:"+Short.MIN_VALUE);
        System.out.println("Short max:"+Short.MAX_VALUE);

        System.out.println("Interger min:"+Integer.MIN_VALUE);
        System.out.println("Interger max:"+Integer.MAX_VALUE);

        System.out.println("Long min:"+Long.MIN_VALUE);
        System.out.println("Long max:"+Long.MAX_VALUE);

        System.out.println("Float min:"+Float.MIN_VALUE);
        System.out.println("Float max:"+Float.MAX_VALUE);

        System.out.println("Double min:"+Double.MIN_VALUE);
        System.out.println("Double max:"+Double.MAX_VALUE);

//        double d = 4.9E-325;
        double d = 4.9E-324;
        double s = Math.pow(3.2,-2);
        System.out.println("Double d:"+d);
        System.out.println("Double s:"+s);
    }

    public static void division(){
        int a;
        double b;
        float c;
        a = 1/3;
        System.out.println(a);

        //整数相除，赋给double
        b = 1/3;
        System.out.println(b);

        //double相除（存在double自动向上转型），赋给double
        b = 1.0/3;
        System.out.println(b);
        b = 1/3.0;
        System.out.println(b);
        b = 1.0/3.0;
        System.out.println(b);
        b = 2;
        System.out.print(b/3);

        c = (float)(1/3.0);
        System.out.println(c);
        /*
0
0.0
0.3333333333333333
0.3333333333333333
0.3333333333333333
0.33333334
         */
    }

    public static void standardFormat(){
        System.out.println(0.05+0.01);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(123.3/100);
        System.out.println("--------------------------");

        DecimalFormat decimalFormat = new DecimalFormat("#.000000%");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(decimalFormat.format(123.123));
    }

    //Decimalformat
    // 如果不想麻烦，对于一般的运算采取指定的RoundingMode格式化数据后，都会返回可以预见的近似值
    //“0” 指定位置不存在数字则显示为0 123.123 ->0000.0000 ->0123.1230
    //“#” 指定位置不存在数字则不显示 123.123 -> ####.#### ->123.123
    //“.” 小数点
    //“%” 会将结果数字乘以100 后面再加上%，最后再处理小数位的四舍五入 123.123 ->#.00->12312.30%
    public static void decimalFormat(){
        double pi=3.1415927;
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));//03.142
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//3
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%
        //取八位小数，不足补0
        System.out.println(new DecimalFormat("#.00000000").format(pi));//3.1415927
        //以百分比方式计数，取八位小数，不足补0
        System.out.println(new DecimalFormat("#.00000000%").format(pi));//314.15927000%

        //强制保留两位小数，整数部分每三位以逗号分隔，整数部分至少一位
        DecimalFormat format = new DecimalFormat("#,##0.00");
        System.out.println(format.getRoundingMode());
        format.setRoundingMode(RoundingMode.HALF_UP);//默认不是四舍五入，而是HALF_EVEN

        System.out.println("--------------------------------");
        System.out.println(format.format(123456789.56735));
        System.out.println(format.format(0.05 + 0.01)); //0.06
        System.out.println(format.format(1.0 - 0.42)); //0.58
        System.out.println(format.format(4.015 * 100)); // 401.50
        System.out.println(format.format(123.3 / 100)); //1.23
    }

    /*
    Java大数类的RoundingMode（舍入模式）

        java.math.RoundingMode：这是一种枚举类型，它定义了8种数据的舍入模式。它与java.math.BigDecimal类中定义的8个同名静态常量的作用相同，可用BigDecimal.setScale(int newScale, RoundingMode roundingMode)来设置数据的精度和舍入模式。
        
1、ROUND_UP：向远离零的方向舍入。

        若舍入位为非零，则对舍入部分的前一位数字加1；若舍入位为零，则直接舍弃。即为向外取整模式。

2、ROUND_DOWN：向接近零的方向舍入。

        不论舍入位是否为零，都直接舍弃。即为向内取整模式。

3、ROUND_CEILING：向正无穷大的方向舍入。

        若 BigDecimal 为正，则舍入行为与 ROUND_UP 相同；若为负，则舍入行为与 ROUND_DOWN 相同。即为向上取整模式。

4、ROUND_FLOOR：向负无穷大的方向舍入。

        若 BigDecimal 为正，则舍入行为与 ROUND_DOWN 相同；若为负，则舍入行为与 ROUND_UP 相同。即为向下取整模式。

!!!5、ROUND_HALF_UP：向“最接近的”整数舍入。

        若舍入位大于等于5，则对舍入部分的前一位数字加1；若舍入位小于5，则直接舍弃。
即为四舍五入模式。

6、ROUND_HALF_DOWN：向“最接近的”整数舍入。

        若舍入位大于5，则对舍入部分的前一位数字加1；若舍入位小于等于5，则直接舍弃。即为五舍六入模式。

7、ROUND_HALF_EVEN：向“最接近的”整数舍入。

        若（舍入位大于5）或者（舍入位等于5且前一位为奇数），则对舍入部分的前一位数字加1；

        若（舍入位小于5）或者（舍入位等于5且前一位为偶数），则直接舍弃。即为银行家舍入模式。

8、ROUND_UNNECESSARY

        断言请求的操作具有精确的结果，因此不需要舍入。

        如果对获得精确结果的操作指定此舍入模式，则抛出ArithmeticException。
     */


    //在涉及到浮点数计算的，可以使用使用BigDecimal
    public static void bigDecimal(){
        double addValue = BigDecimal.valueOf(0.05).add(BigDecimal.valueOf(0.01)).doubleValue();
        //0.05+0.01=0.060000000000000005  0.06
        System.out.println("0.05+0.01=" + (0.05 + 0.01) + "  " + addValue);

        double subtractValue = BigDecimal.valueOf(1.0).subtract(BigDecimal.valueOf(0.42)).doubleValue();
        //1.0-0.42=0.5800000000000001  0.58
        System.out.println("1.0-0.42=" + (1.0 - 0.42) + "  " + subtractValue);

        double multiplyValue = BigDecimal.valueOf(4.015).multiply(BigDecimal.valueOf(100)).doubleValue();
        //4.015*100=401.49999999999994  401.5
        System.out.println("4.015*100=" + (4.015 * 100) + "  " + multiplyValue);

        double divideValue = BigDecimal.valueOf(123.3).divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP).doubleValue();
        //123.3/100=1.2329999999999999  1.233
        System.out.println("123.3/100=" + (123.3 / 100) + "  " + divideValue);
    }
}
