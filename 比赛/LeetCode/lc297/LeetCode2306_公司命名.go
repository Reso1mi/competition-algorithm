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
    // for _, idea := range ideas {
    //     mask := suffix[idea[1:]]
    //     for c := byte('a'); c <= byte('z'); c++ {
    //         if (mask>>(c-byte('a')))&1 == 0 {
    //             // 互补
    //             ans += cnt[c][idea[0]]
    //         }
    //     }
    // }

    // 等价于下面
    for i := byte('a'); i <= byte('z'); i++ {
        for j := byte('a'); j <= byte('z'); j++ {
            ans += cnt[i][j] * cnt[j][i]
        }
    }
    return int64(ans)
}

// // 800ms 在TLE的边缘
// func distinctNames(ideas []string) int64 {
//     m := make(map[string]int)
//     set := make(map[string]bool)
//     for _, idea := range ideas {
//         set[idea] = true
//     }

//     for _, idea := range ideas {
//         for c := byte('a'); c <= byte('z'); c++ {
//             t := string(c) + idea[1:]
//             if !set[t] {
//                 m[idea] |= 1 << (c - byte('a'))
//             }
//         }
//     }

//     var must [256][256]int
//     for _, idea := range ideas {
//         for c := byte('a'); c <= byte('z'); c++ {
//             if ((1 << (c - byte('a'))) & m[idea[0]]) != 0 {
//                 must[idea[0]][c]++
//             }
//         }
//     }

//     cnt := 0
//     for _, idea := range ideas {
//         mask := m[idea]
//         for c := byte('a'); c <= byte('z'); c++ {
//             if ((1 << (c - byte('a'))) & mask) == 0 {
//                 continue
//             }
//             // idea 和 c 开头的v交换，c和idea[0]开头的换，互补
//             cnt += must[c][idea[0]]
//         }
//     }
//     return int64(cnt)
// }