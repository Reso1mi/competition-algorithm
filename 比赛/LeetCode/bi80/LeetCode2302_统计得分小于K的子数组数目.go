type ii = int64

func countSubarrays(nums []int, k int64) int64 {
    n := len(nums)
    var cnt ii = 0
    var sum ii = 0
    for left, right := 0, 0; right < n; right++ {
        sum += ii(nums[right])
        for left <= right && sum*ii(right-left+1) >= k {
            sum -= ii(nums[left])
            left++
        }
        cnt += ii(right - left + 1)
    }
    return ii(cnt)
}

// func countSubarrays(nums []int, k int64) int64 {
//     n := len(nums)
//     sum := make([]ii, n+1)
//     for i := 0; i < n; i++ {
//         sum[i+1] = ii(sum[i]) + ii(nums[i])
//     }

//     // score[i,j] = (sum[j+1]-sum[i]) * (j-i+1)，score单调递增，二分找最值
//     var cnt ii = 0
//     for i := 0; i < n; i++ {
//         left, right := i, n-1
//         ans := i - 1
//         for left <= right {
//             mid := left + (right-left)/2
//             if (sum[mid+1]-sum[i])*ii(mid-i+1) < k {
//                 ans = mid
//                 left = mid + 1
//             } else {
//                 right = mid - 1
//             }
//         }
//         cnt += ii(ans - i + 1)
//     }
//     return cnt
// }
