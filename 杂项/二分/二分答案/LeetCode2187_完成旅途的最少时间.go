func minimumTime(time []int, totalTrips int) int64 {

    check := func(mid int) bool {
        cnt := 0
        for _, t := range time {
            cnt += mid/t
            // 没想到是在这里溢出了，WA了好几次
            if cnt >= totalTrips {
                return true
            }
        }
        return cnt >= totalTrips
    }

    left, right := 0, 1<<63 -1
    ans := right
    for left <= right {
        mid := left + (right-left)/2
        if check(mid) {
            ans = mid
            right = mid - 1
        } else {
            left = mid + 1
        }
    
    }
    return int64(ans)
}