n = int(input())

lines = []
width = 0
height = 0
for _ in range(n):
    li = list(map(int, input().split()))
    lines.append(li[1])
lines.append(0)

stack = [lines[0]]
ans = 0
for i in range(1, len(lines)):
    s = set()
    while stack:
        top = stack.pop()

        if (lines[i] < top):
            s.add(top)
        else:
            stack.append(top)
            break
    stack.append(lines[i])
    ans += len(s)
    print(s, stack, ans)

print(ans)





# 6
# 1 2
# 2 5
# 3 7
# 4 3
# 5 5
# 6 3

# 5
# 1 1
# 2 0
# 3 1
# 4 0
# 5 1

# 3






# 1 1 -> 1
# 2 2 -> 2
# 5 1 
# 6 3 -> 3
# 8 1
# 11 0
# 15 2 -> 4
# 17 3 -> 5
# 20 2
# 22 1 -> 6