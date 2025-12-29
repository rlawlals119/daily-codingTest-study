import heapq
t = 0
while 1:
    N = int(input())
    if N == 0: break
    t += 1

    cave = []
    for _ in range(N):
        cave.append(list(map(int, input().split())))

    dirct = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    hq = []
    heapq.heappush(hq, [cave[0][0], 0, 0])
    visited = [[False for _ in range(N)] for __ in range(N)]
    visited[0][0] = True
    while hq:
        rupee, x, y = heapq.heappop(hq)
        if (x == N - 1 and y == N - 1): # 출구 도착시
            print(f"Problem {t}: {rupee}")
            break

        for dx, dy in dirct:
            a = x + dx
            b = y + dy

            if (a >= 0 and a < N and b >= 0 and b < N and not visited[a][b]):
                heapq.heappush(hq, [rupee + cave[a][b], a, b])
                visited[a][b] = True