from day10 import app as day10


def get_hash_for_row(input, n):
    return day10.part2(input + "-" + str(n))


def part1(input):
    return reduce(lambda sum, hash: sum + reduce(lambda count, n: count + bin(int(n, 16)).count("1"), hash, 0),
                  map(lambda n: list(get_hash_for_row(input, n)), range(0, 128)), 0)


def part2(input):
    to_visit = []
    for i in range(0, 128):
        row = "".join(map(lambda x: bin(int(x, 16))[2:].zfill(4), list(get_hash_for_row(input, i))))
        for j in range(0, 128):
            if row[j] == "1":
                to_visit += [(i, j)]

    curr = 0
    while to_visit:
        queue = [to_visit[0]]
        while queue:
            (i, j) = queue.pop()
            if (i, j) in to_visit:
                to_visit.remove((i, j))
                queue += [(i - 1, j), (i + 1, j), (i, j + 1), (i, j - 1)]
        curr += 1

    return curr


def main():
    input = "stpzcrnm"
    print part1(input)
    print part2(input)


if __name__ == '__main__': main()
