N = int(input())
ans = 0

if N >= 3:
    nums = list(map(int, input().split()))
    nums.sort()
    for i in range(N):
        l = 0
        r = N - 1
        while 1:
            if l == i: l += 1
            if r == i: r -= 1
            if l >= r: break
            if nums[l] + nums[r] == nums[i]: 
                ans += 1
            
                break
            elif nums[l] + nums[r] < nums[i]: l += 1
            else: r -= 1

print(ans)
    