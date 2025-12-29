N, C = map(int, input().split())

router = []
for _ in range(N):
    r = int(input())
    router.append(r)

router.sort()

start = 1
end = router[-1] - router[0]
ans = 0
while start <= end:
    mid = (start + end) // 2

    idx = 0
    cnt = 1
    for i in range(1, N):
        if router[i] - router[idx] >= mid:
            idx = i
            cnt += 1
    if cnt < C: end = mid - 1
    if cnt >= C: 
        ans = max(ans, mid)
        start = mid + 1

print(ans)