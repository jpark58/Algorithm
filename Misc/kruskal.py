# find parent
def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

# Merge two sets


def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


# Get inputs
v, e = map(int, input().split())
parent = [0]*(v+1)

# A list to store edges and variable to store total cost
edges = []
result = 0

# Initialize parents
for i in range(1, v+1):
    parent[i] = i

# Get info of edges
for _ in range(e):
    a, b, cost = map(int, input().split())
    edges.append((cost, a, b))

# Sort in ascending order
edges.sort()

# Algorithm starts here
for edge in edges:
    cost, a, b = edge
    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        result += cost

print(result)
