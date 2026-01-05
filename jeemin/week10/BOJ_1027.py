from collections import deque
import sys
N = int(input())
buildings = list(map(int, input().split()))

if N < 3:
    print(N - 1)
    sys.exit(0)

result = 0
for i in range(N):
    ans = 0
    if i > 0: ans += 1
    if i < N - 1: ans += 1
    last = i - 1
    for j in range(i - 2, -1, -1):
        if (buildings[i] - buildings[j]) * (i - last) < (buildings[i] - buildings[last]) * (i - j):
            ans += 1
            last = j

    last = i + 1
    for j in range(i + 2, N):
        if (buildings[j] - buildings[i]) * (last - i) > (buildings[last] - buildings[i]) * (j - i):
            ans += 1
            last = j
    result = max(result, ans)

print(result)