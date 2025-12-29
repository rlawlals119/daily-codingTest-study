s = input()
bomb = input()
bombList = []

for b in bomb:
    bombList.append(b)

stack = []
for ch in s:
    stack.append(ch)
    if len(stack) >= len(bomb) and stack[-len(bomb):] == bombList:
        for _ in range(len(bomb)):
            stack.pop()

if not stack: print("FRULA")
else: print(''.join(stack))