N, S = map(int, input().split())
nums = list(map(int, input().split()))
ans = 100001

start = 0
end = 0
sum = nums[0]

start = 0
end = 0

while(1):
    if sum >= S:
        ans = min(ans, end - start + 1)
        sum -= nums[start]
        start += 1
    
    elif end == N - 1:
        break
    
    else:
        end += 1
        sum += nums[end]

if ans == 100001: print(0)
else: print(ans)