func distinctNames(ideas []string) int64 {
    suffix := make(map[string]int)
    // suffix[s][i]: 以s为后缀的所有字符 首字母mask
    for _, idea := range ideas {
        suffix[idea[1:]] |= 1 << (idea[0] - byte('a'))
    }

    var cnt [256][256]int
    for _, idea := range ideas {
        mask := suffix[idea[1:]]
        for c := byte('a'); c <= byte('z'); c++ {
            if (mask>>(c-byte('a')))&1 == 0 {
                // c 不在 mask 中，可以交换
                cnt[idea[0]][c]++
            }
        }
    }

    ans := 0
    for _, idea := range ideas {
        mask := suffix[idea[1:]]
        for c := byte('a'); c <= byte('z'); c++ {
            if (mask>>(c-byte('a')))&1 == 0 {
                // 互补
                ans += cnt[c][idea[0]]
            }
        }
    }
    return int64(ans)
}

// // 800ms 在TLE的边缘
// func distinctNames(ideas []string) int64 {
//     m := make(map[string]int)
//     set := make(map[string]bool)
//     prefix := make(map[byte][]string)
//     for _, idea := range ideas {
//         prefix[idea[0]] = append(prefix[idea[0]], idea)
//         set[idea] = true
//     }

//     for i := byte('a'); i <= byte('z'); i++ {
//         for _, idea := range ideas {
//             t := string(i) + idea[1:]
//             if !set[t] {
//                 m[idea] |= 1 << (i - byte('a'))
//             }
//         }
//     }
//     var must [256][256]int
//     for k, ss := range prefix {
//         for _, v := range ss {
//             for i := byte('a'); i <= byte('z'); i++ {
//                 if ((1 << (i - byte('a'))) & m[v]) != 0 {
//                     must[k][i]++
//                 }
//             }
//         }
//     }

//     cnt := 0
//     for _, idea := range ideas {
//         mask := m[idea]
//         for i := byte('a'); i <= byte('z'); i++ {
//             if ((1 << (i - byte('a'))) & mask) == 0 {
//                 continue
//             }
//             // idea 和 i 开头的v交换
//             // for _, v := range prefix[i] {
//             //     // v 和 idea[0]开头交换
//             //     if ((1<<(idea[0]-byte('a'))) & m[v]) != 0 {
//             //         cnt++
//             //     }
//             // }
//             cnt += must[i][idea[0]]
//         }
//     }
//     return int64(cnt)
// }