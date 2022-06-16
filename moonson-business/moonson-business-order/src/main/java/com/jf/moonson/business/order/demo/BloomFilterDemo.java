package com.jf.moonson.business.order.demo;

import cn.hutool.bloomfilter.BitMapBloomFilter;

/**
 * https://juejin.cn/post/6844903832577654797#heading-3
 */
public class BloomFilterDemo {

    public static void test() {
        // 初始化
        BitMapBloomFilter filter = new BitMapBloomFilter(10);
        filter.add("123");
        filter.add("abc");
        filter.add("ddd");

        // 查找
        System.out.println(filter.contains("abc"));
    }


    public static void main(String[] args) {
        test();
    }

}
