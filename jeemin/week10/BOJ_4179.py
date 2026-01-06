from collections import deque
import sys
R, C = map(int, input().split())

maze = [[] for _ in range(R)]
fIdx = []
jIdx = (0, 0)
for i in range(R):
    s = input()
    for j in range(C):
        maze[i].append(s[j])
        if s[j] == 'F': fIdx.append((i, j))
        if s[j] == 'J': jIdx = (i, j)

# 처음부터 지호가 가장자리에 있는지 
if jIdx[0] in (0, R - 1) or jIdx[1] in (0, C - 1):
    print(1)
    sys.exit(0)

dirct = [(1, 0), (-1, 0), (0, 1), (0, -1)]

maze[jIdx[0]][jIdx[1]] = '.'
# 불 퍼뜨리는 bfs
q = deque()
for fx, fy in fIdx:
    q.append(((fx, fy), 1))
    maze[fx][fy] = 0
while q:
    cur, cnt = q.popleft()
    for dx, dy in dirct:
        x = dx + cur[0]
        y = dy + cur[1]
        if 0 <= x < R and 0 <= y < C and maze[x][y] == '.':
            maze[x][y] = cnt
            q.append(((x, y), cnt + 1))

# print(maze)

ans = 'IMPOSSIBLE'
# 지훈이 이동 bfs
visited = [[False for _ in range(C)] for __ in range(R)]
visited[jIdx[0]][jIdx[1]] = True
q.append((jIdx, 1))
while q:
    cur, time = q.popleft()

    if cur[0] in (0, R - 1) or cur[1] in (0, C - 1):
        ans = time
        break

    for dx, dy in dirct:
        x = dx + cur[0]
        y = dy + cur[1]

        if 0 <= x < R and 0 <= y < C and maze[x][y] != '#' and time < maze[x][y] and not visited[x][y]:
            visited[x][y] = True
            q.append(((x, y), time + 1))

print(ans)