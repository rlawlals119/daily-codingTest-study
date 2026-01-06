def solution(diffs, times, limit):
    answer = 0
    s = 1
    e = max(diffs)
    mid = 0
    while(1):
        mid = (e + s) // 2
        if s == e:
            break
        time = times[0]
        for i in range(1, len(diffs)):
            if diffs[i] <= mid:
                time += times[i]
            else:
                time = time + ((times[i - 1] + times[i]) * (diffs[i] - mid) + times[i])
            if time > limit: break
        # print(s, e, mid, time)
        if time > limit: s = mid + 1
        else: e = mid
    answer = mid
            
    return answer