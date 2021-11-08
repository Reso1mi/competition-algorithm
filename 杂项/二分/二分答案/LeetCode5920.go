package main

//5920. 分配给商店的最多商品的最小值
//https://leetcode-cn.com/problems/minimized-maximum-of-products-distributed-to-any-store/
func minimizedMaximum(n int, quantities []int) int {
	var left = 1
	var right = int(0x3f3f3f3f)
	var ans = right
	var check = func(m int) bool {
		var cnt = 0
		for _, c := range quantities {
			cnt += (c + m - 1) / m
		}
		return cnt <= n
	}
	for left <= right {
		mid := left + (right-left)/2
		if check(mid) {
			ans = mid
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	return ans
}
