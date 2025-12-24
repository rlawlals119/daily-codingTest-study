import copy
N = int(input())

cur = list(map(int, list(input())))
goal = list(map(int, list(input())))

def press(idx, s):
    if idx > 0: s[idx - 1] = 1 - s[idx - 1]
    if idx < N - 1: s[idx + 1] = 1 - s[idx + 1]
    s[idx] = 1 - s[idx]
    return 

light1 = copy.deepcopy(cur)
light2 = copy.deepcopy(cur)
press(0, light2)

ans = 100001
cnt1 = 0
cnt2 = 1

for i in range(1, N):
    if light1[i - 1] != goal[i - 1]:
        press(i, light1)
        cnt1 += 1
    if light2[i - 1] != goal[i - 1]:
        press(i, light2)
        cnt2 += 1

if light1 == goal: 
    ans = cnt1

elif light2 == goal:
    ans = min(ans, cnt2)
print(ans)