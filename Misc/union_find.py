# find union
def find_parent(parent, x):
    # if it is not root, do recursive call until find it.
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

# Merge unions


def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if(a < b):
        parent[b] = a
    else:
        parent[a] = b


# Get inputs
v, e = map(int, input().split())
parent = [0]*(v+1)

# Initialize parents
for i in range(1, v+1):
    parent[i] = i

# Flag to check cycle
cycle = False

for i in range(e):
    a, b = map(int, input().split())
    # if there is a cycle, terminate
    if find_parent(parent, a) == find_parent(parent, b):
        cycle = True
        break
    else:
        union_parent(parent, a, b)

if cycle:
    print("Detected Cycle")
else:
    print("No cycle")
