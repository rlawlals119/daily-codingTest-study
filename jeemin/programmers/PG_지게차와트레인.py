import copy
from collections import deque
def solution(storage, requests):
    answer = 0
    n = len(storage)
    m = len(storage[0])
    dirct = [(1, 0), (0, 1), (-1, 0), (0, -1)]
    
    arr = [['' for _ in range(m)] for _ in range(n)]
    for i in range(n):
        for j in range(m):
            arr[i][j] = storage[i][j]
            
    def connOutside(i, j):
        q = deque([(i, j)])
        visited = [[False for _ in range(m)] for __ in range(n)]
        visited[i][j] = True
        while q:
            a, b = q.popleft()
            
            for dx, dy in dirct:
                x = a + dx
                y = b + dy
                
                if x >= 0 and x < n and y >= 0 and y < m and arr[x][y] == '' and not visited[x][y]:
                    if x == 0 or x == n - 1 or y == 0 or y == m - 1:
                        return True
                    q.append((x, y))
                    visited[x][y] = True
        return False
    
    def delivery(arr, req):
        s = copy.deepcopy(arr)
        for i in range(n):
                for j in range(m):
                    if arr[i][j] == req[0]:
                        
                        if len(req) > 1:    # 요청이 알파벳 여러개일때
                            s[i][j] = ''
                        else:   # 요청이 알파벳 하나일때
                            if (i == 0 or j == 0 or i == n - 1 or j == m - 1) or connOutside(i, j):
                                s[i][j] = ''
                                
        return s
    
    for r in requests:
        arr = delivery(arr, r)
    
    for ar in arr:
        for a in ar:
            if a:
                answer += 1
    return answer