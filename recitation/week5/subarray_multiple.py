


def contiguous_subarray_multiple(nums, K):
    prefix_sum = 0
    hash_set = set()
    hash_set.add(0)

    for num in nums:
        prefix_sum = (prefix_sum + num) % K
        if prefix_sum in hash_set:
            return True
        hash_set.add(prefix_sum)

    return False
    




if __name__ == "__main__":
    arr = [0, -1, 3, 4, -1, 2]
    K = 7

    print(contiguous_subarray_multiple(arr, K))





