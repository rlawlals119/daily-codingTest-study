from collections import deque

R, C = map(int, input().split())

alp = []
for _ in range(R):
    alp.append(input())

ans = 1
bit = 1 << (ord(alp[0][0]) - ord('A'))

dirct = [(1, 0), (0, 1), (-1, 0), (0, -1)]
def dfs(cnt, x, y, mask):
    global ans
    for dx, dy in dirct:
        a = x + dx
        b = y + dy

        if 0 <= a < R and 0 <= b < C:
            bit = 1 << (ord(alp[a][b]) - ord('A'))
            if not (bit & mask):    # 아직 방문하지 않은 경우
                new_mask = mask | bit
                dfs(cnt + 1, a, b, new_mask)
            else:   # 이미 방문한 경우
                ans = max(ans, cnt)

dfs(1, 0, 0, bit)

print(ans)