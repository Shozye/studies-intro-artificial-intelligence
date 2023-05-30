
dim = 12

base_map = [
    ["1." for _ in range(dim)] for _ in range(dim)
]

text = ""
for i in range(dim):
    text += " ".join(base_map[i])
    text += "\n"

with open("map.txt", 'w+') as file:
    file.write(text)
    