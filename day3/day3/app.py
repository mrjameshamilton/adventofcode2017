# Each square on a grid is allocated in a spiral pattern starting
# at a location marked 1 and then counting up while spiraling outward.

# How many steps are required to move from n to 1? Can only move up, down, left, or right (Manhatten distance)
def part1(n):
    if n <= 1:
        return 0

    layer, max_in_layer = get_layer_info(n)

    distance_to_add = -layer
    while max_in_layer > n:
        if distance_to_add == layer:
            distance_to_add = -layer
        distance_to_add += 1
        max_in_layer -= 1

    return layer + abs(distance_to_add)


def get_layer_info(n):
    layer = 0
    max_in_layer = 0
    while n > 1:
        n = n - ((layer + 1) * 8)
        max_in_layer = max_in_layer + (layer * 8)
        layer += 1

    max_in_layer = max_in_layer + (layer * 8)

    return layer, max_in_layer + 1


def main():
    print part1(368078)


if __name__ == "__main__": main()
