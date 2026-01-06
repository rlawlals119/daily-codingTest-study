N = int(input())
words = []
hashMap = {}
for i in range(N):
    w = input()
    words.append(w)
    hashMap[w] = i

a = words[0]
b = words[1]
sortedW = sorted(words)

maxCnt = -1
S = ''
T = ''
idx = 20001

sameWord = ''
for i in range(N - 1):
    cnt = 0
    for j in range(min(len(sortedW[i]), len(sortedW[i + 1]))):
        if sortedW[i][j] != sortedW[i + 1][j]: break
        cnt += 1
    if cnt > maxCnt:
        maxCnt = cnt
        sameWord = sortedW[i][:cnt]
        idx = min(hashMap[sortedW[i]], hashMap[sortedW[i + 1]])
    elif cnt == maxCnt and min(hashMap[sortedW[i]], hashMap[sortedW[i + 1]]) < idx:
        if hashMap[sortedW[i]] < hashMap[sortedW[i + 1]]:
            sameWord = sortedW[i][:cnt]
        else:
            sameWord = sortedW[i + 1][:cnt]
        idx = min(hashMap[sortedW[i]], hashMap[sortedW[i + 1]])

for w in words:
    if w[:maxCnt] == sameWord:
        if S == '': S = w
        else:
            T = w
            break

print(S)
print(T)