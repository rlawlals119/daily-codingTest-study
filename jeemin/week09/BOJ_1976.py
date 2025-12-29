import copy
def solution():
    N = int(input())
    M = int(input())

    cities = []
    for _ in range(N):
        cities.append(list(map(int, input().split())))
    plan = list(map(int, input().split()))

    visited = [False for _ in range(N)]
    visited[plan[0] - 1] = True
    def dfs(loc):
        for i in range(N):
            if not visited[i] and cities[loc][i] == 1:
                visited[i] = True
                dfs(i)
        return

    dfs(plan[0] - 1)
    for i in range(1, M):
        if not visited[plan[i] - 1]:
            return "NO"
    return "YES"

print(solution())