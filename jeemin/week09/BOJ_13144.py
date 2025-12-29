N = int(input())
nums = list(map(int, input().split()))

ans = 1

l = 0
r = 1
dict = {}
dict[nums[0]] = 0
while r < N:
    if nums[r] in dict and l <= dict[nums[r]] < r:  # 중복 숫자가 있을 경우
        l = dict[nums[r]] + 1   # 중복 없애기
        
    else:
        dict[nums[r]] = r
        r += 1
        ans += r - l

print(ans)