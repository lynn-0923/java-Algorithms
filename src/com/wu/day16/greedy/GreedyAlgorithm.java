package com.wu.day16.greedy;

import java.util.*;

/**
 * 贪心算法实现集合覆盖问题
 *
 * @author lynn
 * @date 2020/3/24 - 10:59
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到map中
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        //将每个电台放入broadcasts中
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");


        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("重庆");

        HashSet<String> hashSet6 = new HashSet<>();
        hashSet6.add("杭州");
        hashSet6.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        broadcasts.put("K6", hashSet6);

        //allAreas 存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        allAreas.add("重庆");
        //测试
       /* Iterator<String> iterator = allAreas.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        //创建一个ArrayList 存放可选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合 存放在遍历过程中覆盖的地区和当前还没有覆盖的地区的交际
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey 存放在遍历过程中，存放能够覆盖最大的地区对应电台的key
        String maxKey = null;
        //如果 maxKey不为null，则会加入到selects中

        int count = 0;// 用于记录最大未覆盖的数量
        while (allAreas.size() > 0) {//则表示还没有覆盖到所有地区
            maxKey = null; //重置！！1！
            count = 0;// 重置！！！！
            //遍历broadcasts 取出对应的key
            for (String key : broadcasts.keySet()) {
                tempSet.clear(); //重置！！！！
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比上个指向的集合地区还多
                //重置maxKey 体现出贪心算法的特点，每次都选择最优
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > count)) {
                    count = tempSet.size();
                    maxKey = key;
                }
            }
            //如果maxKey不等于空 则将其加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区从allAreas去除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的结果：" + selects);
    }
}
