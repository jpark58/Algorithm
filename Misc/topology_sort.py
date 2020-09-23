from collections import deque

# Get inputs
v, e = map(int, input().split())
# Initialize indegree with 0
indegree = [0]*(v+1)
# Initialize graph
graph = [[] for i in rnage(v+1)]

# Get edges' info
for _ in range(e):
    a, b = map(int, input().split())
    graph[a].append(b)
    indegre[b] += 1


def topology_sort():
    result = []
    q = deque()

    for i in range(1, v+1):
        if indegree[i] == 0:
            q.append(i)

    while q:
        now = q.popleft()
        reulst.append(now)
        for i in graph[now]:
            indegree[i] -= 1
            if indegre[i] == 0:
                q.append(i)

    for i in result:
        print(i, end=' ')


topology_sort()
