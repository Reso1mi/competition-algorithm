
// [5,3,4,4,7,3,6,11,8,5,11]：8 11 7 5
// [7,14,4,14,13,2,6,13] --> [7,14,14,6,13]
// func totalSteps(nums []int) int {
// 	var stack []int
// 	var res = 0
// 	var maxDepth = 0
// 	for i := len(nums) - 1; i >= 0; i-- {
// 		for len(stack) > 0 && nums[stack[len(stack)-1]] < nums[i] {
// 			stack = stack[:len(stack)-1]
// 			res = Max(res, maxDepth-len(stack))
// 		}
// 		stack = append(stack, i)
// 		maxDepth = Max(maxDepth, len(stack))
// 	}
// 	return res
// }

// func Max(a, b int) int {
// 	if a > b {
// 		return a
// 	}
// 	return b
// }

func totalSteps(nums []int) int {
	nums = append(nums, 0x3f3f3f3f)
	next := make([]int, len(nums))
	var lis []int
	for i := 0; i < len(nums)-1; i++ {
		next[i] = i + 1
	}

	// 存放可以吞噬其它数的数，注意逆序存
	// 3->2->1 逆序才能一次删除2和1
	for i := len(nums) - 2; i >= 0; i-- {
		if nums[i] > nums[i+1] {
			lis = append(lis, i)
		}
	}

	for op := 0; ; op++ {
		var temp []int
		for _, i := range lis {
			if nums[i] > nums[next[i]] {
				// 吞噬后面的数
				next[i] = next[next[i]]
				// 还可能继续吞
				temp = append(temp, i)
			}
		}
		// 没有能吞的了
		if len(temp) == 0 {
			return op
		}
		lis = temp
	}
	return -1
}